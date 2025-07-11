package graphs.dfs;

import java.util.ArrayList;
import java.util.List;

/*
Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.

Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.

Notice that you can return the vertices in any order.



Example 1:



Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
Output: [0,3]
Explanation: It's not possible to reach all the nodes from a single vertex. From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].
Example 2:



Input: n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
Output: [0,2,3]
Explanation: Notice that vertices 0, 3 and 2 are not reachable from any other node, so we must include them. Also any of these vertices can reach nodes 1 and 4.


Constraints:

2 <= n <= 10^5
1 <= edges.length <= min(10^5, n * (n - 1) / 2)
edges[i].length == 2
0 <= fromi, toi < n
All pairs (fromi, toi) are distinct.
 */
public class MinimumNumberOfVerticesToReachAllNodes {
    // This problem can be solved using Depth First Search (DFS) to find the smallest set of vertices from which all nodes in a directed acyclic graph (DAG) are reachable.
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Track in-degree for each node
        int[] inDegree = new int[n];

        // Populate adjacency list and in-degree counts
        for (List<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);
            adj.get(from).add(to);
            inDegree[to]++;
        }

        // Nodes with in-degree 0 must be in our result set
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                result.add(i);
            }
        }

        return result;
    }

    // Alternative DFS approach to find the smallest set of vertices
    public List<Integer> findSmallestSetOfVertices1(int n, List<List<Integer>> edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> edge : edges) {
            adj.get(edge.get(0)).add(edge.get(1));
        }

        // Track reachable nodes
        boolean[] reachable = new boolean[n];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!reachable[i]) {
                result.add(i);
                dfs(adj, i, reachable);
            }
        }

        return result;
    }

    private void dfs(List<List<Integer>> adj, int node, boolean[] reachable) {
        reachable[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!reachable[neighbor]) {
                dfs(adj, neighbor, reachable);
            }
        }
    }

    public static void main(String[] args) {
        MinimumNumberOfVerticesToReachAllNodes solution = new MinimumNumberOfVerticesToReachAllNodes();

        // Example usage
        int n = 6;
        List<List<Integer>> edges = List.of(
            List.of(0, 1),
            List.of(0, 2),
            List.of(2, 5),
            List.of(3, 4),
            List.of(4, 2)
        );

        List<Integer> result = solution.findSmallestSetOfVertices(n, edges);
        System.out.println(result); // Output: [0, 3]
    }
}
