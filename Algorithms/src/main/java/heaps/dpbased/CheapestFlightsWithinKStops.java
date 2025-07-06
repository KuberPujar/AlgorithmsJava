package heaps.dpbased;

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
        // Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.putIfAbsent(flight[0], new ArrayList<>());
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // DP table: dp[i][j] = min cost to reach city j with at most i stops
        int[][] dp = new int[k + 2][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][src] = 0; // Base case: 0 stops at src costs 0

        // Fill DP table
        for (int stops = 1; stops <= k + 1; stops++) {
            // Copy previous row to current row (case where we don't take any flight)
            System.arraycopy(dp[stops - 1], 0, dp[stops], 0, n);

            // Update costs for all flights
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];

                // If we can reach 'from' with stops-1 stops
                if (dp[stops - 1][from] != Integer.MAX_VALUE) {
                    // Update cost to reach 'to' with stops stops
                    dp[stops][to] = Math.min(dp[stops][to], dp[stops - 1][from] + price);
                }
            }
        }

        // Find the minimal cost among all possible stops (0 to k+1)
        int minCost = Integer.MAX_VALUE;
        for (int stops = 0; stops <= k + 1; stops++) {
            if (dp[stops][dst] < minCost) {
                minCost = dp[stops][dst];
            }
        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops solution = new CheapestFlightsWithinKStops();
        int n = 4;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int src = 0;
        int dst = 3;
        int k = 1;
        int result = solution.findCheapestPrice(n, flights, src, dst, k);
        System.out.println("The cheapest price from " + src + " to " + dst + " with at most " + k + " stops is: " + result); // Output: 700
    }
}
