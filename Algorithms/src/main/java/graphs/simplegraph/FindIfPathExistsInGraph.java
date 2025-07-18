package graphs.simplegraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    //Java solution using Breadth-First Search (BFS) to determine if there's a valid path between source and destination in an undirected graph
    public boolean validPathBfs(int n, int[][] edges, int source, int destination) {
        // If source and destination are same
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
            adj.get(v).add(u);
        }

        // BFS setup
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Check all neighbors
            for (int neighbor : adj.get(current)) {
                if (neighbor == destination) {
                    return true;
                }
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }

    //Depth-First Search (DFS) version for comparison:
    public boolean validPathDfs(int n, int[][] edges, int source, int destination) {
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

        boolean[] visited = new boolean[n];
        return dfs(adj, visited, source, destination);
    }

    private boolean dfs(List<List<Integer>> adj, boolean[] visited, int current, int destination) {
        if (current == destination) return true;
        visited[current] = true;

        for (int neighbor : adj.get(current)) {
            if (!visited[neighbor] && dfs(adj, visited, neighbor, destination)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FindIfPathExistsInGraph finder = new FindIfPathExistsInGraph();

        // Example 1
        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 0}};
        int source1 = 0;
        int destination1 = 2;
        System.out.println(finder.validPathBfs(n1, edges1, source1, destination1)); // Output: true

        // Example 2
        int n2 = 6;
        int[][] edges2 = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        int source2 = 0;
        int destination2 = 5;
        System.out.println(finder.validPathBfs(n2, edges2, source2, destination2)); // Output: false
    }
}
