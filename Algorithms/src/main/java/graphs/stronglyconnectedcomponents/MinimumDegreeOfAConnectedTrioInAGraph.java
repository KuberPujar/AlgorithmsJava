package graphs.stronglyconnectedcomponents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.

A connected trio is a set of three nodes where there is an edge between every pair of them.

The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.

Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.



Example 1:


Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
Output: 3
Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.
Example 2:


Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
Output: 0
Explanation: There are exactly three trios:
1) [1,4,3] with degree 0.
2) [2,5,6] with degree 2.
3) [5,6,7] with degree 2.


Constraints:

2 <= n <= 400
edges[i].length == 2
1 <= edges.length <= n * (n-1) / 2
1 <= ui, vi <= n
ui != vi
There are no repeated edges.
 */
public class MinimumDegreeOfAConnectedTrioInAGraph {
    //Time Complexity: O(n^3) in the worst case, as we check all combinations of three nodes.
    // This method finds the minimum degree of a connected trio in a graph using an adjacency matrix and degree counts.
    public int minTrioDegree(int n, int[][] edges) {
        // Create adjacency matrix and degree array
        boolean[][] adj = new boolean[n + 1][n + 1];
        int[] degree = new int[n + 1];

        // Initialize adjacency matrix and degrees
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u][v] = true;
            adj[v][u] = true;
            degree[u]++;
            degree[v]++;
        }

        int minDegree = Integer.MAX_VALUE;

        // Check all possible trios
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (!adj[i][j]) continue;
                for (int k = j + 1; k <= n; k++) {
                    if (adj[i][k] && adj[j][k]) { // Found a trio
                        // Calculate degree of the trio
                        int trioDegree = degree[i] + degree[j] + degree[k] - 6;
                        minDegree = Math.min(minDegree, trioDegree);
                    }
                }
            }
        }

        return minDegree == Integer.MAX_VALUE ? -1 : minDegree;
    }

    // This method finds the minimum degree of a connected trio in a graph using adjacency lists and degree counts.
    public int minTrioDegree1(int n, int[][] edges) {
        // Create adjacency lists and degree array
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new HashSet<>());
        }
        int[] degree = new int[n + 1];

        // Initialize adjacency lists and degrees
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        int minDegree = Integer.MAX_VALUE;

        // Check all possible trios
        for (int i = 1; i <= n; i++) {
            for (int j : adj.get(i)) {
                if (j <= i) continue; // Avoid duplicates
                for (int k : adj.get(j)) {
                    if (k <= j) continue; // Avoid duplicates
                    if (adj.get(i).contains(k)) { // Found a trio
                        int trioDegree = degree[i] + degree[j] + degree[k] - 6;
                        minDegree = Math.min(minDegree, trioDegree);
                    }
                }
            }
        }

        return minDegree == Integer.MAX_VALUE ? -1 : minDegree;
    }

    public static void main(String[] args) {
        MinimumDegreeOfAConnectedTrioInAGraph solution = new MinimumDegreeOfAConnectedTrioInAGraph();

        // Example 1
        int n1 = 6;
        int[][] edges1 = {{1, 2}, {1, 3}, {3, 2}, {4, 1}, {5, 2}, {3, 6}};
        System.out.println(solution.minTrioDegree(n1, edges1)); // Output: 3

        // Example 2
        int n2 = 7;
        int[][] edges2 = {{1, 3}, {4, 1}, {4, 3}, {2, 5}, {5, 6}, {6, 7}, {7, 5}, {2, 6}};
        System.out.println(solution.minTrioDegree(n2, edges2)); // Output: 0
    }
}
