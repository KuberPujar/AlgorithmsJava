package graphs;

import java.util.*;

/*
DAG
Design a program that determines whether a directed graph is a Directed Acyclic Graph (DAG). A DAG is a directed graph with no directed cycles, meaning there is no way to start at any node and follow a consistently directed path that eventually loops back to the same node.

Input Format:

The first line contains an integer N, representing the number of nodes in the graph.
The second line contains an integer E, indicating the number of directed edges.
The next E lines each contain two space-separated integers u and v, denoting a directed edge from node u to node v.
Output Format:

Return true if the graph is a Directed Acyclic Graph.
Otherwise, return false".
Sample Input:

4 4
0 1
1 2
2 3
3 1
Sample Output:

No, it's not a DAG.
Explanation:
The sample input represents a graph with 4 nodes and 4 edges. There's a cycle formed by the edges (0,1) -> (1,2) -> (2,3) -> (3,1), hence it's not a DAG.

Sample Input:

6 8
0 1
1 2
2 3
3 4
4 5
5 1
3 5
0 4
Sample Output:

false.
Explanation:
The graph has edges forming a directed cycle: 1 → 2 → 3 → 4 → 5 → 1. This cycle shows that it is possible to return to the same node, confirming the graph is not a Directed Acyclic Graph (DAG).

Why it's not a DAG:

A Directed Acyclic Graph (DAG) has no cycles, but in this case, starting from node 1, you can follow the path 1 → 2 → 3 → 4 → 5 and return back to 1, which forms a cycle.

Constraints:

1 <= N <= 10^5 (number of nodes)
0 <= E <= 10^6 (number of edges)
0 <= u, v < N (node indices)
Note: The function should return the answer.
 */
public class DirectedAcyclicGraph {
    // Node states for DFS
    private static final int WHITE = 0; // Unvisited
    private static final int GRAY = 1;  // Currently being processed (in recursion stack)
    private static final int BLACK = 2; // Completely processed

    /**
     * Determines if a directed graph is a DAG (Directed Acyclic Graph)
     * @param n Number of nodes in the graph
     * @param edges List of directed edges where each edge is [u, v]
     * @return true if graph is a DAG, false otherwise
     */
    public static boolean isDAG(int n, List<int[]> edges) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (u < n && v < n) {
                graph.get(u).add(v);
            }
        }

        // Initialize node colors
        int[] colors = new int[n];
        Arrays.fill(colors, WHITE);

        // Check for cycles using DFS from each unvisited node
        for (int i = 0; i < n; i++) {
            if (colors[i] == WHITE) {
                if (hasCycleDFS(graph, i, colors)) {
                    return false; // Cycle found, not a DAG
                }
            }
        }

        return true; // No cycles found, it's a DAG
    }

    /**
     * DFS helper method to detect cycles using three-color approach
     * @param graph Adjacency list representation of the graph
     * @param node Current node being processed
     * @param colors Array tracking the state of each node
     * @return true if cycle is detected, false otherwise
     */
    private static boolean hasCycleDFS(List<List<Integer>> graph, int node, int[] colors) {
        // Mark current node as being processed (GRAY)
        colors[node] = GRAY;

        // Explore all neighbors
        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == GRAY) {
                // Back edge found - cycle detected
                return true;
            }

            if (colors[neighbor] == WHITE && hasCycleDFS(graph, neighbor, colors)) {
                // Cycle found in recursive call
                return true;
            }
        }

        // Mark current node as completely processed (BLACK)
        colors[node] = BLACK;
        return false;
    }

    /**
     * Alternative implementation using Kahn's algorithm (Topological Sort)
     * @param n Number of nodes in the graph
     * @param edges List of directed edges where each edge is [u, v]
     * @return true if graph is a DAG, false otherwise
     */
    public static boolean isDAGKahn(int n, List<int[]> edges) {
        // Build adjacency list and calculate in-degrees
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges and calculate in-degrees
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (u < n && v < n) {
                graph.get(u).add(v);
                inDegree[v]++;
            }
        }

        // Queue for nodes with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processedNodes = 0;

        // Process nodes with in-degree 0
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processedNodes++;

            // Reduce in-degree of neighbors
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If all nodes are processed, it's a DAG
        return processedNodes == n;
    }

    /**
     * Helper method to parse input and check if graph is DAG
     * @param input String containing the complete input
     * @return true if DAG, false otherwise
     */
    public static boolean solve(String input) {
        Scanner scanner = new Scanner(input.trim());

        int n = scanner.nextInt();
        int e = scanner.nextInt();

        int[][] edgesArray = new int[e][2];
        for (int i = 0; i < e; i++) {
            edgesArray[i][0] = scanner.nextInt(); // u
            edgesArray[i][1] = scanner.nextInt(); // v
        }

        scanner.close();

        // Convert array to List<int[]>
        List<int[]> edges = new ArrayList<>();
        for (int[] edge : edgesArray) {
            edges.add(edge);
        }

        return isDAG(n, edges);
    }

    /**
     * Format the result according to the problem requirements
     * @param isDAG boolean result
     * @return formatted string
     */
    public static String formatResult(boolean isDAG) {
        return isDAG ? "Yes, it's a DAG." : "No, it's not a DAG.";
    }

    // Test method to verify the implementation
    public static void main(String[] args) {
        // Test case 1: Graph with cycle
        String input1 = """
            4 4
            0 1
            1 2
            2 3
            3 1
            """;

        boolean result1 = solve(input1);
        System.out.println("Test Case 1:");
        System.out.println("Input: Graph with 4 nodes and cycle (0->1->2->3->1)");
        System.out.println("Result: " + formatResult(result1));
        System.out.println("Expected: No, it's not a DAG.");
        System.out.println();

        // Test case 2: Graph with cycle
        String input2 = """
            6 8
            0 1
            1 2
            2 3
            3 4
            4 5
            5 1
            3 5
            0 4
            """;

        boolean result2 = solve(input2);
        System.out.println("Test Case 2:");
        System.out.println("Input: Graph with 6 nodes and cycle (1->2->3->4->5->1)");
        System.out.println("Result: " + formatResult(result2));
        System.out.println("Expected: No, it's not a DAG.");
        System.out.println();

        // Test case 3: Valid DAG
        String input3 = """
            4 4
            0 1
            0 2
            1 3
            2 3
            """;

        boolean result3 = solve(input3);
        System.out.println("Test Case 3:");
        System.out.println("Input: Valid DAG with 4 nodes");
        System.out.println("Result: " + formatResult(result3));
        System.out.println("Expected: Yes, it's a DAG.");
        System.out.println();

        // Test case 4: Single node (trivial DAG)
        String input4 = """
            1 0
            """;

        boolean result4 = solve(input4);
        System.out.println("Test Case 4:");
        System.out.println("Input: Single node with no edges");
        System.out.println("Result: " + formatResult(result4));
        System.out.println("Expected: Yes, it's a DAG.");
        System.out.println();

        // Demonstrate both algorithms give same result
        System.out.println("Comparing DFS vs Kahn's algorithm:");
        System.out.println("Test Case 1 - DFS: " + solve(input1));

        List<int[]> testEdges = new ArrayList<>();
        testEdges.add(new int[]{0, 1});
        testEdges.add(new int[]{1, 2});
        testEdges.add(new int[]{2, 3});
        testEdges.add(new int[]{3, 1});
        System.out.println("Test Case 1 - Kahn: " + isDAGKahn(4, testEdges));
    }
}
