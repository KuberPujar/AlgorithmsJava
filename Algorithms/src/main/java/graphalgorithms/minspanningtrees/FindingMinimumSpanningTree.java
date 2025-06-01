package graphalgorithms.minspanningtrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
Finding Minimum Spanning Tree
Given a weighted directed graph, write a program to find the minimum spanning tree (MST) using Kruskal's algorithm.

Input:
The number of nodes (N).
The number of edges (E).
Each edge is represented by a triplet of source, destination, and weight in (edges).
Output:
The minimum spanning tree total weight.
Example:
Input:

4
5
0 1 10
1 3 15
2 3 4
2 0 6
0 3 5

Output:

19
Explanation
In this graph, the edges with weights [0, 2, 5, 6] will form the MST. The MST has a total weight of 19.
Input:

4
3
0 1 5
1 2 3
0 2 1

Output:

4
Explanation
For this graph, the edges [0, 2, 1] and [1, 2, 3] form the MST. The MST has a total weight of 4.
Constraints:
1 <= N <= 10^4 (number of nodes)
0 <= E <= 10^5 (number of edges)
0 <= u, v < N (node indices)
1 <= weight <= 10^9 (edge weights)
The graph is guaranteed to be connected.
Note:The function should return the result. The driver code will handle printing the output.
 */
public class FindingMinimumSpanningTree {
    // Edge class to represent weighted edges
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        // Compare edges by weight for sorting
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public String toString() {
            return "(" + src + "->" + dest + ", weight: " + weight + ")";
        }
    }

    // Union-Find (Disjoint Set Union) class
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            // Initialize each node as its own parent
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Find with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        // Union by rank
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            // If they're already in the same set, adding this edge would create a cycle
            if (rootX == rootY) {
                return false;
            }

            // Union by rank - attach smaller tree under root of higher tree
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
    }

    // Main method to find MST using Kruskal's algorithm
    public static long findMST(int n, List<Edge> edges) {
        // Sort edges by weight in ascending order
        Collections.sort(edges);

        // Initialize Union-Find structure
        UnionFind uf = new UnionFind(n);

        long mstWeight = 0;
        int edgesAdded = 0;
        List<Edge> mstEdges = new ArrayList<>();

        // Process edges in sorted order
        for (Edge edge : edges) {
            // If adding this edge doesn't create a cycle
            if (uf.union(edge.src, edge.dest)) {
                mstWeight += edge.weight;
                mstEdges.add(edge);
                edgesAdded++;

                // MST should have exactly n-1 edges
                if (edgesAdded == n - 1) {
                    break;
                }
            }
        }

        // Debug: Print MST edges
        System.out.println("MST Edges:");
        for (Edge edge : mstEdges) {
            System.out.println(edge);
        }

        return mstWeight;
    }

    // Alternative method that returns both weight and edges
    public static class MSTResult {
        long totalWeight;
        List<Edge> edges;

        public MSTResult(long totalWeight, List<Edge> edges) {
            this.totalWeight = totalWeight;
            this.edges = edges;
        }
    }

    public static MSTResult findMSTWithEdges(int n, List<Edge> edges) {
        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);

        long mstWeight = 0;
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge edge : edges) {
            if (uf.union(edge.src, edge.dest)) {
                mstWeight += edge.weight;
                mstEdges.add(edge);

                if (mstEdges.size() == n - 1) {
                    break;
                }
            }
        }

        return new MSTResult(mstWeight, mstEdges);
    }

    // Method to create edges from input format
    public static List<Edge> createEdges(int[][] edgeData) {
        List<Edge> edges = new ArrayList<>();
        for (int[] data : edgeData) {
            edges.add(new Edge(data[0], data[1], data[2]));
        }
        return edges;
    }

    // Test method
    public static void main(String[] args) {
        // Test case 1
        System.out.println("Test Case 1:");
        int n1 = 4;
        int[][] edgeData1 = {
                {0, 1, 10},
                {1, 3, 15},
                {2, 3, 4},
                {2, 0, 6},
                {0, 3, 5}
        };

        List<Edge> edges1 = createEdges(edgeData1);
        System.out.println("Input edges:");
        for (Edge edge : edges1) {
            System.out.println(edge);
        }

        long result1 = findMST(n1, edges1);
        System.out.println("MST Total Weight: " + result1);
        System.out.println("Expected: 19");
        System.out.println();

        // Test case 2
        System.out.println("Test Case 2:");
        int n2 = 4;
        int[][] edgeData2 = {
                {0, 1, 5},
                {1, 2, 3},
                {0, 2, 1}
        };

        List<Edge> edges2 = createEdges(edgeData2);
        System.out.println("Input edges:");
        for (Edge edge : edges2) {
            System.out.println(edge);
        }

        long result2 = findMST(n2, edges2);
        System.out.println("MST Total Weight: " + result2);
        System.out.println("Expected: 4");
        System.out.println();

        // Test case 3 - Larger example
        System.out.println("Test Case 3 (Larger graph):");
        int n3 = 5;
        int[][] edgeData3 = {
                {0, 1, 2},
                {0, 3, 6},
                {1, 2, 3},
                {1, 3, 8},
                {1, 4, 5},
                {2, 4, 7},
                {3, 4, 9}
        };

        List<Edge> edges3 = createEdges(edgeData3);
        MSTResult result3 = findMSTWithEdges(n3, edges3);

        System.out.println("Input edges:");
        for (Edge edge : edges3) {
            System.out.println(edge);
        }

        System.out.println("MST Edges:");
        for (Edge edge : result3.edges) {
            System.out.println(edge);
        }
        System.out.println("MST Total Weight: " + result3.totalWeight);
        System.out.println();

        // Performance test
        System.out.println("Performance Test:");
        testPerformance();
    }

    // Performance testing method
    private static void testPerformance() {
        int n = 1000;
        List<Edge> edges = new ArrayList<>();
        Random rand = new Random(42); // Fixed seed for reproducible results

        // Generate random edges
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (rand.nextDouble() < 0.1) { // 10% edge probability
                    edges.add(new Edge(i, j, rand.nextInt(1000) + 1));
                }
            }
        }

        long startTime = System.currentTimeMillis();
        long mstWeight = findMST(n, edges);
        long endTime = System.currentTimeMillis();

        System.out.println("Graph with " + n + " nodes and " + edges.size() + " edges");
        System.out.println("MST Weight: " + mstWeight);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
