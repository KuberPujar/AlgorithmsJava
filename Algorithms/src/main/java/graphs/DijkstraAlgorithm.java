package graphs;

import java.util.*;

/*
Dijkstra's Algorithm
Given a weighted directed graph with N nodes and E edges, you need to find the shortest path from a given source node to all other nodes in the graph using Dijkstra's algorithm. The nodes are indexed from 0 to N−1, and the graph's edges have non-negative weights.

Input format
The first line contains two integers, N (number of nodes) and E (number of edges).
The next E lines each contain three integers, u, v, and w, representing an edge from node u to node v with a weight w.
The last line contains an integer S representing the source node.
Output format
The output consists of N lines where each line contains two integers, the node index and its shortest distance from the source node. If a node is unreachable, its distance should be infinity.
Sample Input-1:
5 7
0 1 2
0 2 4
1 2 1
1 3 7
2 3 3
2 4 5
3 4 2
Sample Output-1:
0 0
1 2
2 3
3 6
4 8
Explanation:
Starting from node 0:

Node 0: Distance = 0 (starting point)
Node 1: Distance = 2 (0 → 1)
Node 2: Distance = 3 (0 → 1 → 2)
Node 3: Distance = 6 (0 → 1 → 2 → 3)
Node 4: Distance = 8 (0 → 1 → 2 → 3 → 4)
Sample input-2:
4 5
0 1 1
1 2 2
2 3 3
0 2 4
1 3 5
0
Sample Output-2
0 0
1 1
2 3
3 6
Explanation:
Node 0: Distance = 0 (starting point)
Node 1: Distance = 2 (Path: 0 → 1)
Node 2: Distance = 3 (Path: 0 → 1 → 2)
Node 3: Distance = 6 (Path: 0 → 1 → 2 → 3)
Node 4: Distance = 8 (Path: 0 → 1 → 2 → 3 → 4)
Dijkstra's algorithm calculates the shortest path by traversing nodes and updating the minimum distances using priority queues.
Constraints:
1 <= N <= 10^4 (number of nodes)

0 <= E <= 10^5 (number of edges)

0 <= u, v < N (node indices)

-10^9 <= weight <= 10^9 (edge weights)

The graph is guaranteed to be connected.

Note: The function should return and prepare the result instead of printing it.
 */
class Node implements Comparable<Node> {
    int vertex;
    long distance;

    public Node(int vertex, long distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Long.compare(this.distance, other.distance);
    }
}
public class DijkstraAlgorithm {
    /**
     * Implements Dijkstra's algorithm to find shortest paths from source to all nodes
     * @param n Number of nodes in the graph
     * @param graph Adjacency list where graph.get(u) contains lists of [destination, weight]
     * @param start Source node
     * @return List of strings in format "node distance" for all nodes
     */
    public static List<String> dijkstra(int n, List<List<List<Integer>>> graph, int start) {
        // Initialize distances array
        long[] distances = new long[n];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[start] = 0;

        // Priority queue to store nodes with their current distances
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        // Track visited nodes
        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            // Skip if already visited
            if (visited[u]) {
                continue;
            }

            visited[u] = true;

            // Explore neighbors
            if (u < graph.size()) {
                for (List<Integer> edge : graph.get(u)) {
                    if (edge.size() >= 2) {
                        int v = edge.get(0);          // destination node
                        long weight = edge.get(1);    // edge weight

                        // Relaxation step
                        if (v < n && !visited[v] && distances[u] != Long.MAX_VALUE &&
                                distances[u] + weight < distances[v]) {
                            distances[v] = distances[u] + weight;
                            pq.offer(new Node(v, distances[v]));
                        }
                    }
                }
            }
        }

        // Prepare result
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (distances[i] == Long.MAX_VALUE) {
                // Unreachable node - use "infinity" to represent unreachable
                result.add(i + " infinity");
            } else {
                result.add(i + " " + (int)distances[i]);
            }
        }

        return result;
    }

    /**
     * Helper method to build graph from edge list format
     * @param n Number of nodes
     * @param edges Array of edges where each edge is [u, v, weight]
     * @return Adjacency list representation
     */
    public static List<List<List<Integer>>> buildGraph(int n, int[][] edges) {
        List<List<List<Integer>>> graph = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            if (u < n && v < n) {
                List<Integer> edgeInfo = Arrays.asList(v, weight);
                graph.get(u).add(edgeInfo);
            }
        }

        return graph;
    }

    /**
     * Helper method to parse input and run Dijkstra's algorithm
     * @param input String containing the complete input
     * @return List of strings in format "node distance"
     */
    public static List<String> solve(String input) {
        Scanner scanner = new Scanner(input.trim());

        int n = scanner.nextInt();
        int e = scanner.nextInt();

        int[][] edges = new int[e][3];
        for (int i = 0; i < e; i++) {
            edges[i][0] = scanner.nextInt(); // u
            edges[i][1] = scanner.nextInt(); // v
            edges[i][2] = scanner.nextInt(); // weight
        }

        int source = scanner.nextInt();
        scanner.close();

        // Build graph and run algorithm
        List<List<List<Integer>>> graph = buildGraph(n, edges);
        return dijkstra(n, graph, source);
    }

    /**
     * Utility method to format output as string
     * @param result List of strings in format "node distance"
     * @return Formatted output string
     */
    public static String formatOutput(List<String> result) {
        StringBuilder sb = new StringBuilder();
        for (String line : result) {
            sb.append(line).append("\n");
        }
        return sb.toString().trim();
    }

    // Test method to verify the implementation
    public static void main(String[] args) {
        // Test case 1: Using the new parameter format
        List<List<List<Integer>>> graph1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            graph1.add(new ArrayList<>());
        }

        // Add edges: (u, v, weight)
        graph1.get(0).add(Arrays.asList(1, 2));  // 0 -> 1 (weight 2)
        graph1.get(0).add(Arrays.asList(2, 4));  // 0 -> 2 (weight 4)
        graph1.get(1).add(Arrays.asList(2, 1));  // 1 -> 2 (weight 1)
        graph1.get(1).add(Arrays.asList(3, 7));  // 1 -> 3 (weight 7)
        graph1.get(2).add(Arrays.asList(3, 3));  // 2 -> 3 (weight 3)
        graph1.get(2).add(Arrays.asList(4, 5));  // 2 -> 4 (weight 5)
        graph1.get(3).add(Arrays.asList(4, 2));  // 3 -> 4 (weight 2)

        List<String> result1 = dijkstra(5, graph1, 0);
        System.out.println("Test Case 1 (Direct graph input):");
        System.out.println(formatOutput(result1));
        System.out.println();

        // Test case 2: Using input parsing
        String input2 = """
            4 5
            0 1 1
            1 2 2
            2 3 3
            0 2 4
            1 3 5
            0
            """;

        List<String> result2 = solve(input2);
        System.out.println("Test Case 2 (Parsed input):");
        System.out.println(formatOutput(result2));
    }
}
