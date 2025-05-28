package graphs;

import java.util.List;

/*
Graph Valid Tree
Given N nodes labelled from 0 to N - 1 and a list of undirected edges (where each edge is a pair of nodes), determine if these edges form a valid tree. A graph is considered a valid tree if it satisfies the following properties:

It is connected: There is a path between every pair of vertices.
It has no cycles: There are no loops or circular paths.
Input Format:
The first line contains an integer N, representing the number of nodes in the graph.
The next line contains an integer M, representing the number of edges in the graph.
The following M lines contain two space-separated integers each, representing the undirected edges between the nodes.
Output Format:
Print "True" if the edges form a valid tree; otherwise, print "False".
Example:
Input:

5
4
0 1
0 2
0 3
1 4
Output:

True

Constraints:
1 <= T <= 5 where T is the number of test cases.
1 <= N <= 10^3
1 <= M <= 10^3
Explanation:
In this case, the graph has 5nodes and4 edges. The edges connect the nodes as follows: [0, 1], [0, 2], [0, 3], [1, 4]. This graph is connected (there's a path between any two nodes) and acyclic (it contains no cycles), which meets the criteria for a valid tree.

Note: The function should return boolean value.
 */
public class GraphValidTree {
    public boolean validTree(List<List<Integer>> edges, int n, int m) {
        if (edges.size() != n - 1) {
            return false;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int rootU = find(parent, u);
            int rootV = find(parent, v);
            if (rootU == rootV) {
                return false;
            }
            parent[rootV] = rootU;
        }
        return true;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public static void main(String[] args) {
        GraphValidTree gvt = new GraphValidTree();
        // Example 1
        List<List<Integer>> edges = List.of(List.of(0, 1), List.of(0, 2), List.of(0, 3), List.of(1, 4));
        System.out.println(gvt.validTree(edges,5, 4)); // Output: true
    }
}
