package graphs.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.



Example 1:


Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:


Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.


Constraints:

1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.
 */
public class FindIfPathExistsInGraph {
   //Depth-First Search (DFS) to determine if there's a valid path between source and destination in an undirected graph
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // If source and destination are the same
        if (source == destination) {
            return true;
        }

        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u); // Since graph is undirected
        }

        // DFS setup
        boolean[] visited = new boolean[n];
        return dfs(adj, visited, source, destination);
    }

    private boolean dfs(List<List<Integer>> adj, boolean[] visited, int current, int destination) {
        // Base case: found destination
        if (current == destination) {
            return true;
        }

        visited[current] = true;

        // Explore all neighbors
        for (int neighbor : adj.get(current)) {
            if (!visited[neighbor] && dfs(adj, visited, neighbor, destination)) {
                return true;
            }
        }

        return false;
    }

    //non-recursive version using a stack
    public boolean validPath1(int n, int[][] edges, int source, int destination) {
        if (source == destination) return true;

        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Iterative DFS with stack
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int neighbor : adj.get(current)) {
                if (neighbor == destination) {
                    return true;
                }
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        FindIfPathExistsInGraph graph = new FindIfPathExistsInGraph();
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        int source = 0;
        int destination = 2;

        boolean result = graph.validPath(n, edges, source, destination);
        System.out.println("Path exists: " + result); // Output: true

        result = graph.validPath1(n, edges, source, destination);
        System.out.println("Path exists (non-recursive): " + result); // Output: true
    }
}
