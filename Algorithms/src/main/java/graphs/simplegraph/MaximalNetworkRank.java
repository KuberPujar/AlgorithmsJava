package graphs.simplegraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.

The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.

The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.

Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.



Example 1:



Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
Output: 4
Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
Example 2:



Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
Output: 5
Explanation: There are 5 roads that are connected to cities 1 or 2.
Example 3:

Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
Output: 5
Explanation: The network rank of 2 and 5 is 5. Notice that all the cities do not have to be connected.


Constraints:

2 <= n <= 100
0 <= roads.length <= n * (n - 1) / 2
roads[i].length == 2
0 <= ai, bi <= n-1
ai != bi
Each pair of cities has at most one road connecting them.
 */
public class MaximalNetworkRank {

    // Java solution to find the maximal network rank of a given infrastructure of cities and roads
    public int maximalNetworkRank(int n, int[][] roads) {
        // Initialize degree count for each city
        int[] degree = new int[n];
        // Create adjacency matrix to check direct connections
        boolean[][] connected = new boolean[n][n];

        // Calculate degrees and mark connections
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            degree[a]++;
            degree[b]++;
            connected[a][b] = true;
            connected[b][a] = true;
        }

        int maxRank = 0;
        // Check all pairs of cities
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degree[i] + degree[j];
                // Subtract 1 if they are directly connected
                if (connected[i][j]) {
                    rank--;
                }
                maxRank = Math.max(maxRank, rank);
            }
        }

        return maxRank;
    }

    // Alternative solution using HashMap and Set for connections
    public int maximalNetworkRank1(int n, int[][] roads) {
        // Map to store connected cities for each city
        Map<Integer, Set<Integer>> connections = new HashMap<>();
        int[] degree = new int[n];

        for (int i = 0; i < n; i++) {
            connections.put(i, new HashSet<>());
        }

        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            degree[a]++;
            degree[b]++;
            connections.get(a).add(b);
            connections.get(b).add(a);
        }

        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degree[i] + degree[j];
                if (connections.get(i).contains(j)) {
                    rank--;
                }
                maxRank = Math.max(maxRank, rank);
            }
        }

        return maxRank;
    }

    public static void main(String[] args) {
        MaximalNetworkRank solution = new MaximalNetworkRank();

        // Example 1
        int[][] roads1 = {{0, 1}, {0, 3}, {1, 2}, {1, 3}};
        System.out.println(solution.maximalNetworkRank(4, roads1)); // Output: 4

        // Example 2
        int[][] roads2 = {{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}};
        System.out.println(solution.maximalNetworkRank(5, roads2)); // Output: 5

        // Example 3
        int[][] roads3 = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}};
        System.out.println(solution.maximalNetworkRank(8, roads3)); // Output: 5
    }
}

