package graphalgorithms.bfsdfs;

import java.util.*;

/*
Shortest path
You are tasked with analyzing a directed graph representing the road network of a country. The graph consists of cities connected by one-way roads. Your objective is to find the length of the shortest path that forms a cycle starting and ending at the same city for each city in the graph. A cycle means you must return to the starting city after travelling through a series of one-way roads. If no cycle is possible from a city, the shortest path length for that city should be marked as -1.

Input Format:

The first line contains an integer N, representing the number of cities in the country (1 <= N <= 2000).
The next Nlines each containNintegers, representing anN x Nadjacency matrix where thej-thinteger in thei-thline is1if there is a direct road from cityito cityj, and 0 otherwise.
Output Format:

Output Nintegers, where thei-thinteger represents the length of the shortest cycle starting and ending at cityi. If no cycle is possible for city i, output -1 for that city. Examples:
Input:

5
0 1 1 1 1
1 0 0 0 1
0 0 1 1 0
0 0 1 0 0
0 0 0 1 0
Output:

2 2 1 2 -1
Explanation
For the given input, the shortest cycle for city `3` is `1`, as it can directly return to itself. For cities `1`, `2`, and `4`, the shortest cycles have a length of `2`. City `5` does not have any outgoing roads, so it has no cycle, resulting in `-1`.
Input:

5
0 1 0 0 1
0 0 1 0 0
0 0 0 1 0
0 0 0 0 1
1 0 0 0 0
Output:

2 5 5 5 2
Explanation
City 0: Shortest cycle length is 2 (Path: 0 → 1 → 0)
City 1: Shortest cycle length is 5 (Path: 1 → 2 → 3 → 4 → 0 → 1)
City 2: Shortest cycle length is 5 (Path: 2 → 3 → 4 → 0 → 1 → 2)
City 3: Shortest cycle length is 5 (Path: 3 → 4 → 0 → 1 → 2 → 3)
City 4: Shortest cycle length is 2 (Path: 4 → 0 → 4)
Note: For each city, the goal is to find the shortest path that starts and ends at the same city, taking into account possible cycles in the directed graph. If no cycle exists, the output is -1.
Constraints:

0 < N ≤ 2000, where N is the number of cities.
roads[i][j] is 0 or 1, indicating the absence or presence of a direct road from city i to city j.
The graph may contain cycles, and you need to find the shortest cycle for each city.
Note:The function should return the result. The driver code will handle printing the output.
 */
public class ShortestPath {
    public static int[] findShortestCycles(int[][] adjMatrix) {
        int n = adjMatrix.length;
        int[] result = new int[n];

        // For each city, find the shortest cycle starting and ending at that city
        for (int start = 0; start < n; start++) {
            result[start] = findShortestCycleFromCity(adjMatrix, start);
        }

        return result;
    }

    private static int findShortestCycleFromCity(int[][] adjMatrix, int startCity) {
        int n = adjMatrix.length;

        // First check for self-loop (cycle of length 1)
        if (adjMatrix[startCity][startCity] == 1) {
            return 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        // Initialize distances to -1 (unvisited)
        Arrays.fill(distance, -1);

        // Start BFS from all direct neighbors of startCity (except startCity itself)
        for (int neighbor = 0; neighbor < n; neighbor++) {
            if (adjMatrix[startCity][neighbor] == 1 && neighbor != startCity) {
                queue.offer(neighbor);
                distance[neighbor] = 1;
                visited[neighbor] = true;
            }
        }

        // BFS to find shortest path back to startCity
        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Check if we can reach back to startCity
            if (adjMatrix[current][startCity] == 1) {
                return distance[current] + 1;
            }

            // Explore neighbors
            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (adjMatrix[current][neighbor] == 1 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        // No cycle found
        return -1;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        // Test case 1
        int[][] graph1 = {
                {0, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0}
        };

        int[] result1 = findShortestCycles(graph1);
        System.out.println("Test Case 1 Result: " + Arrays.toString(result1));
        // Expected: [2, 2, 1, 2, -1]

        // Test case 2
        int[][] graph2 = {
                {0, 1, 0, 0, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0}
        };

        int[] result2 = findShortestCycles(graph2);
        System.out.println("Test Case 2 Result: " + Arrays.toString(result2));
        // Expected: [2, 5, 5, 5, 2]
    }
}
