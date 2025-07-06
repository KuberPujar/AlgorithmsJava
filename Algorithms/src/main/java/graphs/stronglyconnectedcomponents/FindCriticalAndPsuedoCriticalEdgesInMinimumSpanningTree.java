package graphs.stronglyconnectedcomponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.



Example 1:



Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:

Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
Example 2:



Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.


Constraints:

2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= ai < bi < n
1 <= weighti <= 1000
All pairs (ai, bi) are distinct.
 */
public class FindCriticalAndPsuedoCriticalEdgesInMinimumSpanningTree {

    // This method finds critical and pseudo-critical edges in a minimum spanning tree (MST) using Kruskal's algorithm with Union find.
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // Add original index to each edge before sorting
        int[][] extendedEdges = new int[edges.length][4];
        for (int i = 0; i < edges.length; i++) {
            extendedEdges[i][0] = edges[i][0];
            extendedEdges[i][1] = edges[i][1];
            extendedEdges[i][2] = edges[i][2];
            extendedEdges[i][3] = i; // original index
        }

        // Sort edges by weight
        Arrays.sort(extendedEdges, (a, b) -> Integer.compare(a[2], b[2]));

        // Find MST weight using Kruskal's algorithm
        int mstWeight = kruskal(n, extendedEdges, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();

        // Check each edge
        for (int i = 0; i < extendedEdges.length; i++) {
            int[] edge = extendedEdges[i];
            int index = edge[3];

            // Check if edge is critical (must be included in all MSTs)
            int weightWithout = kruskal(n, extendedEdges, -1, index);
            if (weightWithout > mstWeight) {
                critical.add(index);
                continue;
            }

            // Check if edge is pseudo-critical (can be included in some MST)
            int weightWith = kruskal(n, extendedEdges, index, -1);
            if (weightWith == mstWeight) {
                pseudoCritical.add(index);
            }
        }

        // Sort the results for consistent output (optional)
        Collections.sort(critical);
        Collections.sort(pseudoCritical);

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudoCritical);
        return result;
    }

    private int kruskal(int n, int[][] edges, int includeEdge, int excludeEdge) {
        UnionFind uf = new UnionFind(n);
        int weight = 0;
        int edgesUsed = 0;

        // Force include specific edge if required
        if (includeEdge != -1) {
            for (int[] edge : edges) {
                if (edge[3] == includeEdge) {
                    uf.union(edge[0], edge[1]);
                    weight += edge[2];
                    edgesUsed++;
                    break;
                }
            }
        }

        // Process all edges in sorted order
        for (int[] edge : edges) {
            if (edge[3] == excludeEdge) continue; // Skip excluded edge
            if (edge[3] == includeEdge) continue; // Already included if needed

            if (uf.union(edge[0], edge[1])) {
                weight += edge[2];
                edgesUsed++;
                if (edgesUsed == n - 1) break; // MST complete
            }
        }

        return edgesUsed == n - 1 ? weight : Integer.MAX_VALUE;
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot == yRoot) return false; // Already connected

            // Union by rank
            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        FindCriticalAndPsuedoCriticalEdgesInMinimumSpanningTree solution = new FindCriticalAndPsuedoCriticalEdgesInMinimumSpanningTree();

        // Example 1
        int n1 = 5;
        int[][] edges1 = {{0, 1, 1}, {1, 2, 1}, {2, 3, 2}, {0, 3, 2}, {0, 4, 3}, {3, 4, 3}, {1, 4, 6}};
        System.out.println(solution.findCriticalAndPseudoCriticalEdges(n1, edges1));

        // Example 2
        int n2 = 4;
        int[][] edges2 = {{0, 1, 1}, {1, 2, 1}, {2, 3, 1}, {0, 3, 1}};
        System.out.println(solution.findCriticalAndPseudoCriticalEdges(n2, edges2));
    }
}
