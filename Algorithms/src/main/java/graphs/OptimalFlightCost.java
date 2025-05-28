package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
Optimal Flight Cost
Determine the cheapest flight cost from a source city to a destination city, given a maximum number of allowed stops.

Input Format:

n: An integer representing the number of cities (1 <= n <= 100).
flights: A list of flights, where flights[i] = [from_i, to_i, price_i] indicates that there is a flight from city from_i to city to_i with cost price_i.
src: An integer representing the source city (0 <= ``src < n).
dst: An integer representing the destination city (0 <= dst < n, src != dst).
k: An integer representing the maximum number of stops allowed on the route (0 <= k < n).
Output Format:

An integer representing the cheapest price to travel from srctodstwith at mostk stops. If no such route exists, return -1.
Examples:

Input:
n = 3,

flights = [[0,1,100],[1,2,100],[0,2,500]],

src = 0, dst = 2, k = 1

Output:
200

Explanation: The cheapest route from city 0 to city 2 with at most 1 stop is via city 1. The total cost is 100 (0 to 1) + 100 (1 to 2) = 200.

Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= from_i, to_i < n, from_i != to_i
1 <= price_i <= 10^4
There will not be any multiple flights between two cities.
0 <= src, dst, k < n, src != dst
Note: The function should return the result.
 */
public class OptimalFlightCost {
    public static int findCheapestPriceUsingDP(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 100000000;
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dp, n);
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int w = flight[2];
                if (dp[u] != INF) {
                    if (dp[u] + w < temp[v]) {
                        temp[v] = dp[u] + w;
                    }
                }
            }
            dp = temp;
        }

        return dp[dst] == INF ? -1 : dp[dst];
    }

    @SuppressWarnings("unchecked")
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            graph[from].add(new int[]{to, price});
        }

        int[][] minCost = new int[n][k+2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minCost[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minCost[src][0] = 0;
        pq.offer(new int[]{0, src, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int city = cur[1];
            int edges = cur[2];

            if (city == dst) {
                return cost;
            }

            if (edges == k + 1) {
                continue;
            }

            if (cost > minCost[city][edges]) {
                continue;
            }

            for (int[] neighbor : graph[city]) {
                int nextCity = neighbor[0];
                int price = neighbor[1];
                int newCost = cost + price;
                int newEdges = edges + 1;
                if (newCost < minCost[nextCity][newEdges]) {
                    minCost[nextCity][newEdges] = newCost;
                    pq.offer(new int[]{newCost, nextCity, newEdges});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        System.out.println(findCheapestPrice(n, flights, src, dst, k)); // Output: 200
    }
}
