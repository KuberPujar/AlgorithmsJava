package graphalgorithms;

import java.util.*;

/*
Isolated or Interconnected
You've landed on a mysterious island inhabited by two distinct tribes, cloaked in secrecy and suspicion. Each tribe occupies a hidden village within the island's lush jungle, connected by ancient trails (represented by the graph array). Your mission? Determine if any path exists between the two villages, revealing whether the tribes are truly separate or secretly intertwined.

You're given an array graph, where each element graph[u] represents the villages neighbouring village u. Imagine these as hidden pathways through the jungle.
The island possesses these unique characteristics:
No village resides alone (every graph[u] has at least one element).
No loop exists within a village (a village cannot have a path back to itself).
Two villages are truly separate if no hidden path connects them (graph is not connected).
Your mission is to identify if the two tribes live in complete isolation (the graph is not bipartite) or if, somewhere within the jungle, a hidden path unites them (the graph is bipartite). Remember, uncovering this secret connection paves the way for peaceful interaction and understanding between the tribes.

Input/Output Format:

Input:

A nested array graph representing the village connections (each element graph[u] contains integers representing connected villages). i.e. the graph is in the form of an adjacency list.

Output:

A boolean value indicates whether the two tribes are fully isolated (False) or interconnected (True).

Examples:

Input:

[[0,1],[0,2],[1,2]] (Island map with separate villages)

Output:

False (No path connects the villagers, making the tribes remain divided.)

Input:

[[0,1],[1,2]] (Another island map with a hidden connection)

Output:

True (A hidden path exists between villages 1 and 2, hinting at a potential connection between the tribes.)

Constraints:

1 <= n (number of villages on the island) <= 100
Each element graph[u] has at least one value but less than n values.
graph[u] does not contain u (villages have no loop paths).
If v is in graph[u], then u is in graph[v] (graph is undirected).
 */
public class IsolatedOrInterConnected {
    /**
     * Main solution using BFS to check if graph is bipartite
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public static boolean isBipartite(List<List<Integer>> graph) {
        int n = graph.size();

        // Color array: 0 = uncolored, 1 = tribe1, -1 = tribe2
        int[] colors = new int[n];

        // Check each component of the graph (in case graph is disconnected)
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!bfsCheck(graph, i, colors)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * BFS helper method to check bipartiteness starting from a node
     */
    private static boolean bfsCheck(List<List<Integer>> graph, int start, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = 1; // Start with tribe 1

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Check all neighbors
            for (int neighbor : graph.get(current)) {
                if (colors[neighbor] == 0) {
                    // Uncolored neighbor - color with opposite color
                    colors[neighbor] = -colors[current];
                    queue.offer(neighbor);
                } else if (colors[neighbor] == colors[current]) {
                    // Same color as current node - not bipartite
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Alternative solution using DFS
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public static boolean isBipartiteDFS(List<List<Integer>> graph) {
        int n = graph.size();
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!dfsCheck(graph, i, 1, colors)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * DFS helper method to check bipartiteness
     */
    private static boolean dfsCheck(List<List<Integer>> graph, int node, int color, int[] colors) {
        colors[node] = color;

        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == 0) {
                // Uncolored neighbor - color with opposite color
                if (!dfsCheck(graph, neighbor, -color, colors)) {
                    return false;
                }
            } else if (colors[neighbor] == color) {
                // Same color as current node - not bipartite
                return false;
            }
        }

        return true;
    }

    /**
     * Detailed solution with step-by-step explanation
     */
    public static boolean isBipartiteDetailed(List<List<Integer>> graph) {
        int n = graph.size();
        int[] colors = new int[n];

        System.out.println("=== Bipartite Check Analysis ===");
        System.out.println("Graph adjacency list:");
        for (int i = 0; i < n; i++) {
            System.out.println("Village " + i + ": " + graph.get(i));
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                System.out.println("Starting BFS from village " + i);
                if (!bfsCheckDetailed(graph, i, colors)) {
                    System.out.println("❌ Graph is NOT bipartite - tribes cannot be separated!");
                    return false;
                }
            }
        }

        System.out.println("✅ Graph is bipartite - tribes can be separated!");
        printTribes(colors);
        return true;
    }

    /**
     * Detailed BFS with step-by-step logging
     */
    private static boolean bfsCheckDetailed(List<List<Integer>> graph, int start, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = 1;

        System.out.println("  Assigning village " + start + " to Tribe 1");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            String currentTribe = colors[current] == 1 ? "Tribe 1" : "Tribe 2";

            System.out.println("  Processing village " + current + " (" + currentTribe + ")");

            for (int neighbor : graph.get(current)) {
                if (colors[neighbor] == 0) {
                    // Uncolored neighbor
                    colors[neighbor] = -colors[current];
                    String neighborTribe = colors[neighbor] == 1 ? "Tribe 1" : "Tribe 2";
                    System.out.println("    -> Assigning village " + neighbor + " to " + neighborTribe);
                    queue.offer(neighbor);
                } else if (colors[neighbor] == colors[current]) {
                    // Same color conflict
                    System.out.println("    -> ❌ CONFLICT: Village " + neighbor +
                            " already belongs to " + currentTribe +
                            " but is connected to village " + current +
                            " (same tribe)");
                    return false;
                } else {
                    // Different color - good
                    String neighborTribe = colors[neighbor] == 1 ? "Tribe 1" : "Tribe 2";
                    System.out.println("    -> ✅ Village " + neighbor + " (" + neighborTribe +
                            ") correctly belongs to different tribe");
                }
            }
        }

        return true;
    }

    /**
     * Print the final tribe assignments
     */
    private static void printTribes(int[] colors) {
        List<Integer> tribe1 = new ArrayList<>();
        List<Integer> tribe2 = new ArrayList<>();

        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 1) {
                tribe1.add(i);
            } else if (colors[i] == -1) {
                tribe2.add(i);
            }
        }

        System.out.println("\n=== Final Tribe Assignments ===");
        System.out.println("Tribe 1 (Hidden Villages): " + tribe1);
        System.out.println("Tribe 2 (Mystic Villages): " + tribe2);
    }

    /**
     * Solution using Union-Find (Alternative approach)
     * Time Complexity: O(V + E * α(V)) where α is inverse Ackermann function
     * Space Complexity: O(V)
     */
    public static boolean isBipartiteUnionFind(List<List<Integer>> graph) {
        int n = graph.size();
        UnionFind uf = new UnionFind(2 * n); // Create 2n nodes for bipartite check

        for (int i = 0; i < n; i++) {
            for (int neighbor : graph.get(i)) {
                // If i and neighbor are in same set, not bipartite
                if (uf.find(i) == uf.find(neighbor)) {
                    return false;
                }

                // Union i with neighbor's opposite set
                // Union neighbor with i's opposite set
                uf.union(i, neighbor + n);
                uf.union(neighbor, i + n);
            }
        }

        return true;
    }

    /**
     * Union-Find data structure for bipartite check
     */
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    /**
     * Helper method to convert adjacency list format
     */
    public static int[][] convertGraph(int[][] input) {
        // The input format seems to be different from standard adjacency list
        // Let's assume it's already in the correct format
        return input;
    }

    public static void main(String[] args) {
        // Test case 1: Not bipartite (contains odd cycle)
        List<List<Integer>> graph1 = Arrays.asList(
            Arrays.asList(1, 3),
            Arrays.asList(0, 2),
            Arrays.asList(1, 3),
            Arrays.asList(0, 2)
        );
        System.out.println("=== Test Case 1: Triangle Graph ===");
        System.out.println("Expected: False (contains odd cycle)");
        System.out.println("BFS Result: " + isBipartite(graph1));
        System.out.println("DFS Result: " + isBipartiteDFS(graph1));
        System.out.println();

        // Test case 2: Bipartite
        List<List<Integer>> graph2 = Arrays.asList(
            Arrays.asList(1, 3),
            Arrays.asList(0, 2),
            Arrays.asList(1, 3),
            Arrays.asList(0, 2)
        );
        System.out.println("=== Test Case 2: Square Graph ===");
        System.out.println("Expected: True (bipartite)");
        System.out.println("BFS Result: " + isBipartite(graph2));
        System.out.println();

        // Test case 3: Simple bipartite
        List<List<Integer>> graph3 = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(0, 2),
            Arrays.asList(1)
        );
        System.out.println("=== Test Case 3: Simple Path ===");
        System.out.println("Expected: True (bipartite)");
        System.out.println("Result: " + isBipartite(graph3));
        System.out.println();

        // Test case 4: Detailed analysis
        List<List<Integer>> graph4 = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(0, 2),
            Arrays.asList(0, 1, 3),
            Arrays.asList(0, 2)
        );
        System.out.println("=== Test Case 4: Detailed Analysis ===");
        isBipartiteDetailed(graph4);
        System.out.println();

        // Test case 5: Odd cycle (not bipartite)
        List<List<Integer>> graph5 = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(0, 2),
            Arrays.asList(0, 1)
        );
        System.out.println("=== Test Case 5: Triangle (Odd Cycle) ===");
        System.out.println("Expected: False (odd cycle)");
        isBipartiteDetailed(graph5);
        System.out.println();

        // Test case 6: Even cycle (bipartite)
        List<List<Integer>> graph6 = Arrays.asList(
           Arrays.asList(1, 3),
          Arrays.asList(0, 2),
         Arrays.asList(1, 3),
        Arrays.asList(0, 2));
        System.out.println("=== Test Case 6: Square (Even Cycle) ===");
        System.out.println("Expected: True (even cycle)");
        isBipartiteDetailed(graph6);
        System.out.println();

        // Performance comparison
        System.out.println("=== Performance Comparison ===");
        long start = System.nanoTime();
        boolean result1 = isBipartite(graph1);
        long time1 = System.nanoTime() - start;

        start = System.nanoTime();
        boolean result2 = isBipartiteDFS(graph1);
        long time2 = System.nanoTime() - start;

        start = System.nanoTime();
        boolean result3 = isBipartiteUnionFind(graph1);
        long time3 = System.nanoTime() - start;

        System.out.println("BFS: " + result1 + " (Time: " + time1/1000 + " µs)");
        System.out.println("DFS: " + result2 + " (Time: " + time2/1000 + " µs)");
        System.out.println("Union-Find: " + result3 + " (Time: " + time3/1000 + " µs)");
    }

    /**
     * Main solution function
     */
    public static boolean solve(List<List<Integer>> graph) {
        return isBipartite(graph);
    }
}
