package graphalgorithms;

import java.util.*;

/*
Count the Number of Complete Components
You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array of edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.

Examples:

Input:

 n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output:

3
Input:

n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output:

1
Constraints:

1<= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
There are no repeated edges.
 */
public class CountNumberOfCompleteComponents {
    public static int countCompleteComponents(int n, int[][] edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        // Find all connected components and check if they are complete
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);

                if (isComplete(component, adj)) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private static void dfs(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }

    private static boolean isComplete(List<Integer> component, List<List<Integer>> adj) {
        int size = component.size();

        // A complete component with n vertices should have n*(n-1)/2 edges total
        // Each vertex should be connected to exactly (n-1) other vertices
        for (int vertex : component) {
            if (adj.get(vertex).size() != size - 1) {
                return false;
            }

            // Check if this vertex is connected to all other vertices in the component
            Set<Integer> neighbors = new HashSet<>(adj.get(vertex));
            for (int other : component) {
                if (other != vertex && !neighbors.contains(other)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test case 1
        int n1 = 6;
        int[][] edges1 = {{0,1},{0,2},{1,2},{3,4}};
        System.out.println("Test case 1:");
        System.out.println("Input: n = " + n1 + ", edges = " + Arrays.deepToString(edges1));
        System.out.println("Output: " + countCompleteComponents(n1, edges1));
        System.out.println("Expected: 3");
        System.out.println();

        // Test case 2
        int n2 = 6;
        int[][] edges2 = {{0,1},{0,2},{1,2},{3,4},{3,5}};
        System.out.println("Test case 2:");
        System.out.println("Input: n = " + n2 + ", edges = " + Arrays.deepToString(edges2));
        System.out.println("Output: " + countCompleteComponents(n2, edges2));
        System.out.println("Expected: 1");
        System.out.println();

        // Additional test case - single vertex
        int n3 = 3;
        int[][] edges3 = {{0,1}};
        System.out.println("Test case 3:");
        System.out.println("Input: n = " + n3 + ", edges = " + Arrays.deepToString(edges3));
        System.out.println("Output: " + countCompleteComponents(n3, edges3));
        System.out.println("Expected: 1 (isolated vertex is complete)");
    }
}
