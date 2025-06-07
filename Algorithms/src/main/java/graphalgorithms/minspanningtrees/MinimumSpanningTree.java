package graphalgorithms.minspanningtrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Minimum Spanning Tree
Given a connected, undirected graph with weighted edges, find and return the minimum spanning tree (MST) using Kruskal's algorithm.

Input Format

An integer n (2 ≤ n ≤ 1000) representing the number of vertices in the graph.
An integer m (1 ≤ m ≤ n * (n - 1) / 2) representing the number of edges in the graph.
The next m lines contain three space-separated integers u, v, w, representing an undirected edge between vertices u and v with weight w (1 ≤ u, v ≤ n, 1 ≤ w ≤ 1000).
Output Format
Return a 2D array representing the edges of the minimum spanning tree (MST). Each edge should be represented as [u, v, w], where u and v are the vertices connected by the edge and w is the weight of the edge. The order of the edges in the result does not matter.

Example 1
Input

4 5
1 2 1
1 3 2
1 4 3
2 3 5
3 4 4
Output

1 2 1
1 3 2
1 4 3
Explanation
The given graph has 4 vertices and 5 edges. Using Kruskal’s algorithm, the edges forming the MST are:

(1,2) with weight 1
(1,3) with weight 2
(1,4) with weight 3
These edges connect all vertices with the minimum possible total weight (1 + 2 + 3 = 6).

Example 2
Input

5 7
1 2 1
1 3 2
1 4 3
2 3 4
2 5 5
3 5 6
4 5 7
Output

1 2 1
1 3 2
1 4 3
2 5 5
Explanation
The given graph has 5 vertices and 7 edges. Using Kruskal’s algorithm, the edges forming the MST are:

(1,2) with weight 1
(1,3) with weight 2
(1,4) with weight 3
(2,5) with weight 5
These edges connect all vertices with the minimum possible total weight (1 + 2 + 3 + 5 = 11).

Constraints

The input graph is connected and has no self-loops or parallel edges.
All edge weights are distinct.
The input graph may contain cycles.
The result should be a valid minimum spanning tree.
Note: The function should return the result as a 2D array. The driver code will handle printing the output.
 */
public class MinimumSpanningTree {
    private int[] parent;
    private int[] rank;

    public MinimumSpanningTree(int n) {
        parent = new int[n + 1]; // n+1 to handle 1-indexed vertices
        rank = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            parent[i] = i;
        }
    }

    // Find operation with path compression
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union operation with union by rank
    private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // Already in same set, would create cycle
        }

        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    public int[][] minimumSpanningTree(int n, int m, int[][] edges) {
        // Create a list of edges for sorting
        List<int[]> edgeList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            edgeList.add(new int[]{edges[i][0], edges[i][1], edges[i][2]});
        }

        // Sort edges by weight in ascending order
        edgeList.sort((a, b) -> Integer.compare(a[2], b[2]));

        List<int[]> mst = new ArrayList<>();

        // Apply Kruskal's algorithm
        for (int[] edge : edgeList) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            if (union(u, v)) {
                mst.add(new int[]{u, v, weight});
                // If we have n-1 edges, we have a complete MST
                if (mst.size() == n - 1) {
                    break;
                }
            }
        }

        // Convert result to 2D array
        int[][] result = new int[mst.size()][3];
        for (int i = 0; i < mst.size(); i++) {
            result[i] = mst.get(i);
        }

        return result;
    }

    // Driver code for testing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Create Solution instance
        MinimumSpanningTree solution = new MinimumSpanningTree(n);

        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
            edges[i][2] = scanner.nextInt();
        }

        int[][] mst = solution.minimumSpanningTree(n, m, edges);

        // Print the result
        for (int[] edge : mst) {
            System.out.println(edge[0] + " " + edge[1] + " " + edge[2]);
        }

        scanner.close();
    }
}
