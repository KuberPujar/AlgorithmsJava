package heaps.graphbased;

import java.util.*;

/*
There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.



Example 1:


Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
Example 3:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.


Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst
 */
public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build the adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.putIfAbsent(flight[0], new ArrayList<>());
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // Priority queue: [cost, current city, stops remaining]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.offer(new int[]{0, src, k + 1}); // k stops means k+1 steps allowed

        // To keep track of visited nodes with remaining stops
        Map<Integer, Integer> visited = new HashMap<>();

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int cost = current[0];
            int city = current[1];
            int stops = current[2];

            // If we've reached the destination
            if (city == dst) {
                return cost;
            }

            // If no more stops allowed, skip
            if (stops == 0) {
                continue;
            }

            // Check if we've already visited this city with more or equal stops remaining
            if (visited.containsKey(city) && visited.get(city) >= stops) {
                continue;
            }
            visited.put(city, stops);

            // Process neighbors if they exist
            if (graph.containsKey(city)) {
                for (int[] neighbor : graph.get(city)) {
                    int nextCity = neighbor[0];
                    int nextCost = cost + neighbor[1];
                    minHeap.offer(new int[]{nextCost, nextCity, stops - 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops solution = new CheapestFlightsWithinKStops();
        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println(solution.findCheapestPrice(4, flights1, 0, 3, 1)); // Output: 700

        int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println(solution.findCheapestPrice(3, flights2, 0, 2, 1)); // Output: 200

        int[][] flights3 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println(solution.findCheapestPrice(3, flights3, 0, 2, 0)); // Output: 500
    }
}
