package graphs;

import java.util.*;

/*
Shortest Path between two nodes
Write a program to find the shortest path between two nodes in an unweighted graph.

Input format:
The number of nodes (N).
The number of edges (E).
E pairs of integers representing edges.
Two integers, source node (S) and destination node (D).
Output format:
Shortest path length between S and D.
Sample input 1:
6 7
0 1
0 2
1 2
1 3
2 4
3 4
3 5
0 5
Sample Output 1:`
3
Explanation
Start at node 0.
From node 0, you can move to nodes 1 or 2.
From node 2, you can move to node 4.
From node 4, you can move to node 3.
From node 3, you can move to node 5.
The shortest path is 0 -> 2 -> 4 -> 5, which has a total length of 3.
Sample input 2:
5 5
0 1
1 2
1 3
2 3
3 4
0 4
Sample Output 2:`
3
Explanation
Start at node 0.
From node 0, move to node 1.
From node 1, move to node 3.
From node 3, move to node 4.
The shortest path is 0 -> 1 -> 3 -> 4, which has a total length of 3.
Constraints:
1 <= N <= 10^4 (number of nodes)

0 <= E <= 10^5 (number of edges)

0 <= u, v < N (node indices)

The graph is guaranteed to be connected.

Note:
The function should return the answer instead of printing it.
 */
public class ShortestPathBetweenTwoNodes {
    /**
     * Finds the shortest path length between two nodes in an unweighted graph using BFS.
     *
     * @param N         The total number of nodes in the graph (Nodes are 0-indexed from 0 to N-1).
     * @param edges     A 2D array where each inner array edges[i] = {u, v} represents an unweighted, undirected edge between node u and node v.
     * @param S         The starting node for the path.
     * @param D         The destination node for the path.
     * @return The length of the shortest path (number of edges). Returns 0 if S equals D.
     *         Returns -1 if D is unreachable from S (though the problem often guarantees connectivity).
     */
    public static int findShortestPath(int N, int[][] edges, int S, int D) {
        // Case 1: Start node is the same as the end node
        if (S == D) {
            return 0;
        }

        // 1. Build Adjacency List from the edges array
        List<List<Integer>> adj = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // Ensure nodes are within bounds (optional, but good practice if not guaranteed)
            // if (u >= 0 && u < N && v >= 0 && v < N) {
            adj.get(u).add(v);
            adj.get(v).add(u); // Since the graph is undirected
            // }
        }

        // 2. Perform BFS
        // Queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();

        // Array to store the shortest distance from S to each node.
        // It also serves to mark visited nodes (a node `i` is visited if distances[i] != -1).
        int[] distances = new int[N];
        Arrays.fill(distances, -1); // Initialize all distances to -1 (meaning unvisited/unreachable)

        // Start BFS from the startNode (S)
        queue.offer(S);
        distances[S] = 0; // Distance from S to itself is 0

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            // Explore all neighbors of the current node
            if (adj.get(currentNode) != null) { // Check if node has neighbors
                for (int neighbor : adj.get(currentNode)) {
                    // If the neighbor has not been visited yet
                    if (distances[neighbor] == -1) {
                        distances[neighbor] = distances[currentNode] + 1; // Update distance
                        queue.offer(neighbor); // Add neighbor to the queue for further exploration

                        // Optimization: If this neighbor is the endNode (D), we have found the shortest path
                        // because BFS explores layer by layer.
                        if (neighbor == D) {
                            return distances[D];
                        }
                    }
                }
            }
        }

        // If the loop completes, it means the endNode D was not reached from S.
        // Given the problem constraint that the graph is connected, this part should ideally
        // not be reached if D is a valid node within the connected component of S.
        // It would return -1 if D was unreachable.
        return distances[D]; // Will be -1 if D was not reached
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample Input 1:
        // 6 7
        // 0 1
        // 0 2
        // 1 2
        // 1 3
        // 2 4
        // 3 4
        // 3 5
        // 0 5
        // Expected Output 1: 3

        // Sample Input 2:
        // 5 5
        // 0 1
        // 1 2
        // 1 3
        // 2 3
        // 3 4
        // 0 4
        // Expected Output 2: 3

        System.out.println("Enter number of nodes (N):");
        int N_nodes = sc.nextInt();
        System.out.println("Enter number of edges (E):");
        int E_edges = sc.nextInt();

        int[][] edges_array = new int[E_edges][2];
        System.out.println("Enter E pairs of integers representing edges (u v):");
        for (int i = 0; i < E_edges; i++) {
            edges_array[i][0] = sc.nextInt();
            edges_array[i][1] = sc.nextInt();
        }

        System.out.println("Enter source node (S):");
        int S_node = sc.nextInt();
        System.out.println("Enter destination node (D):");
        int D_node = sc.nextInt();
        int shortestPathLength = findShortestPath(N_nodes, edges_array, S_node, D_node);

        System.out.println("Shortest path length: " + shortestPathLength);

        sc.close();

        // Test with Sample 1:
        // int N1 = 6;
        // int[][] edges1 = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 4}, {3, 4}, {3, 5}};
        // int S1 = 0;
        // int D1 = 5;
        // System.out.println("Sample 1 Output: " + solver.findShortestPath(N1, edges1, S1, D1)); // Expected: 3

        // Test with Sample 2:
        // int N2 = 5;
        // int[][] edges2 = {{0, 1}, {1, 2}, {1, 3}, {2, 3}, {3, 4}};
        // int S2 = 0;
        // int D2 = 4;
        // System.out.println("Sample 2 Output: " + solver.findShortestPath(N2, edges2, S2, D2)); // Expected: 3
    }
}
