package graphs.simplegraph;

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
public class MinimumNoOfVerticesToReachAllNodes {

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // Array to track in-degree of each node
        int[] inDegree = new int[n];

        // Calculate in-degree for each node
        for (List<Integer> edge : edges) {
            int to = edge.get(1);
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

    // Alternative solution using boolean array to track nodes with incoming edges
    public List<Integer> findSmallestSetOfVertices1(int n, List<List<Integer>> edges) {
        boolean[] hasIncomingEdge = new boolean[n];

        // Mark nodes that have incoming edges
        for (List<Integer> edge : edges) {
            hasIncomingEdge[edge.get(1)] = true;
        }

        // Collect nodes with no incoming edges
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!hasIncomingEdge[i]) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MinimumNoOfVerticesToReachAllNodes solution = new MinimumNoOfVerticesToReachAllNodes();

        // Example 1
        List<List<Integer>> edges1 = new ArrayList<>();
        edges1.add(List.of(0, 1));
        edges1.add(List.of(0, 2));
        edges1.add(List.of(2, 5));
        edges1.add(List.of(3, 4));
        edges1.add(List.of(4, 2));
        System.out.println(solution.findSmallestSetOfVertices(6, edges1)); // Output: [0, 3]

        // Example 2
        List<List<Integer>> edges2 = new ArrayList<>();
        edges2.add(List.of(0, 1));
        edges2.add(List.of(2, 1));
        edges2.add(List.of(3, 1));
        edges2.add(List.of(1, 4));
        edges2.add(List.of(2, 4));
        System.out.println(solution.findSmallestSetOfVertices(5, edges2)); // Output: [0, 2, 3]
    }
}
