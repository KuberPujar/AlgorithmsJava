package graphs;

import java.util.*;

/*
Identifying Redundant Connection
You're given a graph initially forming a tree with n nodes (labelled 1 to n) and one extra edge added. Find and return the edge to remove, making it a valid tree again. If multiple options, return the last one in the input array edges.

Example: 1
Input:

Edges: [[1,2],[1,3],[2,3]]
Output:

Redundant Connection: [2,3]
Explanation: Removing the edge [2,3] will make the graph a valid tree.
Example : 2

Input

Edges: [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output:

Redundant Connection: [1,4]
Explanation: Removing the edge [1,4] will make the graph a valid tree.
Constraints:

1 <= Number of edges <= 1,000

The function should return the result.
 */
public class IdentifyingRedundantConnections {
    @SuppressWarnings("unchecked")
    public static int[] findRedundantConnection(int[][] edges) {
        int n = 0;
        for (int[] edge : edges) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }

        List<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n+1];
        int[] parent = new int[n+1];
        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);

        Set<String> cycleEdges = new HashSet<>();

        if (dfs(1, -1, graph, visited, parent, cycleEdges)) {
            for (int i = edges.length - 1; i >= 0; i--) {
                int[] edge = edges[i];
                String key = Math.min(edge[0], edge[1]) + ":" + Math.max(edge[0], edge[1]);
                if (cycleEdges.contains(key)) {
                    return edge;
                }
            }
        }
        return new int[0];
    }

    private static boolean dfs(int u, int par, List<Integer>[] graph, boolean[] visited, int[] parent, Set<String> cycleEdges) {
        visited[u] = true;
        for (int v : graph[u]) {
            if (v == par) {
                continue;
            }
            if (visited[v]) {
                addEdge(cycleEdges, u, v);
                int cur = u;
                while (cur != v) {
                    addEdge(cycleEdges, cur, parent[cur]);
                    cur = parent[cur];
                }
                return true;
            }
            parent[v] = u;
            if (dfs(v, u, graph, visited, parent, cycleEdges)) {
                return true;
            }
        }
        return false;
    }

    private static void addEdge(Set<String> cycleEdges, int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        cycleEdges.add(min + ":" + max);
    }

    public static void main(String[] args) {
        int[][] edges1 = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println(Arrays.toString(findRedundantConnection(edges1))); // Output: [2, 3]

        int[][] edges2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        System.out.println(Arrays.toString(findRedundantConnection(edges2))); // Output: [1, 4]
    }
}
