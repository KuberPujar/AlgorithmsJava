package heaps.graphbased;

import java.util.*;

/*
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.



Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1


Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime {
    /**
     * Main solution using Dijkstra's algorithm with min-heap
     * Time Complexity: O(E log V) where E = edges, V = vertices
     * Space Complexity: O(V + E)
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list representation of the graph
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] time : times) {
            int source = time[0];
            int target = time[1];
            int weight = time[2];

            graph.computeIfAbsent(source, x -> new ArrayList<>()).add(new int[]{target, weight});
        }

        // Min-heap to store (distance, node)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Distance array to track shortest distance to each node
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        // Set to track visited nodes
        Set<Integer> visited = new HashSet<>();

        // Start from source node k
        distances[k] = 0;
        minHeap.offer(new int[]{0, k});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentDist = current[0];
            int currentNode = current[1];

            // Skip if already visited (important for optimization)
            if (visited.contains(currentNode)) {
                continue;
            }

            // Mark as visited
            visited.add(currentNode);

            // Process all neighbors
            if (graph.containsKey(currentNode)) {
                for (int[] neighbor : graph.get(currentNode)) {
                    int nextNode = neighbor[0];
                    int edgeWeight = neighbor[1];

                    // Skip if already visited
                    if (visited.contains(nextNode)) {
                        continue;
                    }

                    // Calculate new distance
                    int newDist = currentDist + edgeWeight;

                    // Update if we found a shorter path
                    if (newDist < distances[nextNode]) {
                        distances[nextNode] = newDist;
                        minHeap.offer(new int[]{newDist, nextNode});
                    }
                }
            }
        }

        // Find the maximum distance (time for all nodes to receive signal)
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return -1; // Some node is unreachable
            }
            maxTime = Math.max(maxTime, distances[i]);
        }

        return maxTime;
    }

    /**
     * Alternative solution using Floyd-Warshall algorithm
     * Time Complexity: O(V³)
     * Space Complexity: O(V²)
     * Good for dense graphs or when we need all-pairs shortest paths
     */
    public int networkDelayTimeFloydWarshall(int[][] times, int n, int k) {
        // Initialize distance matrix
        int[][] dist = new int[n + 1][n + 1];

        // Initialize with infinity
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0; // Distance to self is 0
        }

        // Fill in the given edges
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            dist[u][v] = w;
        }

        // Floyd-Warshall algorithm
        for (int k_node = 1; k_node <= n; k_node++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k_node] != Integer.MAX_VALUE &&
                            dist[k_node][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k_node] + dist[k_node][j]);
                    }
                }
            }
        }

        // Find maximum distance from source k
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[k][i] == Integer.MAX_VALUE) {
                return -1; // Node i is unreachable from k
            }
            maxTime = Math.max(maxTime, dist[k][i]);
        }

        return maxTime;
    }

    /**
     * Alternative solution using Bellman-Ford algorithm
     * Time Complexity: O(V × E)
     * Space Complexity: O(V)
     * Can handle negative weights (not needed here but good to know)
     */
    public int networkDelayTimeBellmanFord(int[][] times, int n, int k) {
        // Initialize distances
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        // Relax edges n-1 times
        for (int i = 0; i < n - 1; i++) {
            boolean updated = false;

            for (int[] time : times) {
                int u = time[0];
                int v = time[1];
                int w = time[2];

                if (distances[u] != Integer.MAX_VALUE) {
                    if (distances[u] + w < distances[v]) {
                        distances[v] = distances[u] + w;
                        updated = true;
                    }
                }
            }

            // Early termination if no updates
            if (!updated) break;
        }

        // Find maximum distance
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxTime = Math.max(maxTime, distances[i]);
        }

        return maxTime;
    }

    /**
     * DFS-based solution for comparison
     * Time Complexity: O(V + E) for each node, O(V × (V + E)) overall
     * Space Complexity: O(V + E)
     */
    public int networkDelayTimeDFS(int[][] times, int n, int k) {
        // Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] time : times) {
            int source = time[0];
            int target = time[1];
            int weight = time[2];
            graph.computeIfAbsent(source, x -> new ArrayList<>()).add(new int[]{target, weight});
        }

        // Distance array to store minimum distance to each node
        int[] minDistances = new int[n + 1];
        Arrays.fill(minDistances, Integer.MAX_VALUE);

        // DFS from source with memoization
        dfs(graph, k, 0, minDistances);

        // Find maximum distance
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (minDistances[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxTime = Math.max(maxTime, minDistances[i]);
        }

        return maxTime;
    }

    private void dfs(Map<Integer, List<int[]>> graph, int node, int currentDist, int[] minDistances) {
        // If we've found a longer path to this node, skip
        if (currentDist >= minDistances[node]) {
            return;
        }

        // Update minimum distance to this node
        minDistances[node] = currentDist;

        // Explore neighbors
        if (graph.containsKey(node)) {
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int edgeWeight = neighbor[1];
                dfs(graph, nextNode, currentDist + edgeWeight, minDistances);
            }
        }
    }

    /**
     * Utility method to print the graph for debugging
     */
    public void printGraph(int[][] times, int n) {
        System.out.println("Graph representation:");
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] time : times) {
            int source = time[0];
            int target = time[1];
            int weight = time[2];
            graph.computeIfAbsent(source, x -> new ArrayList<>()).add(new int[]{target, weight});
        }

        for (int i = 1; i <= n; i++) {
            System.out.print("Node " + i + ": ");
            if (graph.containsKey(i)) {
                for (int[] edge : graph.get(i)) {
                    System.out.print("(" + edge[0] + "," + edge[1] + ") ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Test method with all provided examples and additional test cases
     */
    public static void main(String[] args) {
        NetworkDelayTime solution = new NetworkDelayTime();

        System.out.println("=== Network Delay Time Solutions ===\n");

        // Test Case 1
        int[][] times1 = {{2,1,1},{2,3,1},{3,4,1}};
        int n1 = 4, k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Times: " + Arrays.deepToString(times1));
        System.out.println("n = " + n1 + ", k = " + k1);
        solution.printGraph(times1, n1);

        System.out.println("Dijkstra: " + solution.networkDelayTime(times1, n1, k1));
        System.out.println("Floyd-Warshall: " + solution.networkDelayTimeFloydWarshall(times1, n1, k1));
        System.out.println("Bellman-Ford: " + solution.networkDelayTimeBellmanFord(times1, n1, k1));
        System.out.println("DFS: " + solution.networkDelayTimeDFS(times1, n1, k1));
        System.out.println("Expected: 2\n");

        // Test Case 2
        int[][] times2 = {{1,2,1}};
        int n2 = 2, k2 = 1;
        System.out.println("Test Case 2:");
        System.out.println("Times: " + Arrays.deepToString(times2));
        System.out.println("n = " + n2 + ", k = " + k2);
        solution.printGraph(times2, n2);

        System.out.println("Dijkstra: " + solution.networkDelayTime(times2, n2, k2));
        System.out.println("Floyd-Warshall: " + solution.networkDelayTimeFloydWarshall(times2, n2, k2));
        System.out.println("Bellman-Ford: " + solution.networkDelayTimeBellmanFord(times2, n2, k2));
        System.out.println("DFS: " + solution.networkDelayTimeDFS(times2, n2, k2));
        System.out.println("Expected: 1\n");

        // Test Case 3
        int[][] times3 = {{1,2,1}};
        int n3 = 2, k3 = 2;
        System.out.println("Test Case 3:");
        System.out.println("Times: " + Arrays.deepToString(times3));
        System.out.println("n = " + n3 + ", k = " + k3);
        solution.printGraph(times3, n3);

        System.out.println("Dijkstra: " + solution.networkDelayTime(times3, n3, k3));
        System.out.println("Floyd-Warshall: " + solution.networkDelayTimeFloydWarshall(times3, n3, k3));
        System.out.println("Bellman-Ford: " + solution.networkDelayTimeBellmanFord(times3, n3, k3));
        System.out.println("DFS: " + solution.networkDelayTimeDFS(times3, n3, k3));
        System.out.println("Expected: -1\n");

        // Additional Test Cases

        // Test Case 4: Complex network
        int[][] times4 = {{1,2,1},{1,3,4},{2,3,2},{2,4,5},{3,4,1}};
        int n4 = 4, k4 = 1;
        System.out.println("Test Case 4 (Complex Network):");
        System.out.println("Times: " + Arrays.deepToString(times4));
        System.out.println("n = " + n4 + ", k = " + k4);
        solution.printGraph(times4, n4);

        System.out.println("Dijkstra: " + solution.networkDelayTime(times4, n4, k4));
        System.out.println("Floyd-Warshall: " + solution.networkDelayTimeFloydWarshall(times4, n4, k4));
        System.out.println("Bellman-Ford: " + solution.networkDelayTimeBellmanFord(times4, n4, k4));
        System.out.println("DFS: " + solution.networkDelayTimeDFS(times4, n4, k4));
        System.out.println();

        // Test Case 5: Single node
        int[][] times5 = {};
        int n5 = 1, k5 = 1;
        System.out.println("Test Case 5 (Single Node):");
        System.out.println("Times: " + Arrays.deepToString(times5));
        System.out.println("n = " + n5 + ", k = " + k5);

        System.out.println("Dijkstra: " + solution.networkDelayTime(times5, n5, k5));
        System.out.println("Floyd-Warshall: " + solution.networkDelayTimeFloydWarshall(times5, n5, k5));
        System.out.println("Bellman-Ford: " + solution.networkDelayTimeBellmanFord(times5, n5, k5));
        System.out.println("DFS: " + solution.networkDelayTimeDFS(times5, n5, k5));
        System.out.println("Expected: 0\n");

        // Performance comparison
        System.out.println("=== Performance Comparison ===");
        testPerformance(solution);
    }

    /**
     * Performance testing method
     */
    private static void testPerformance(NetworkDelayTime solution) {
        // Create a larger test case
        int n = 50;
        int k = 1;
        List<int[]> timesList = new ArrayList<>();

        // Create a connected graph with random weights
        Random rand = new Random(42);

        // Ensure connectivity by creating a spanning tree first
        for (int i = 2; i <= n; i++) {
            int parent = rand.nextInt(i - 1) + 1;
            int weight = rand.nextInt(10) + 1;
            timesList.add(new int[]{parent, i, weight});
        }

        // Add some additional random edges
        for (int i = 0; i < 100; i++) {
            int u = rand.nextInt(n) + 1;
            int v = rand.nextInt(n) + 1;
            if (u != v) {
                int weight = rand.nextInt(10) + 1;
                timesList.add(new int[]{u, v, weight});
            }
        }

        int[][] times = timesList.toArray(new int[timesList.size()][]);

        // Test Dijkstra
        long start = System.nanoTime();
        int result1 = solution.networkDelayTime(times, n, k);
        long dijkstraTime = System.nanoTime() - start;

        // Test Bellman-Ford
        start = System.nanoTime();
        int result2 = solution.networkDelayTimeBellmanFord(times, n, k);
        long bellmanTime = System.nanoTime() - start;

        // Test DFS
        start = System.nanoTime();
        int result3 = solution.networkDelayTimeDFS(times, n, k);
        long dfsTime = System.nanoTime() - start;

        System.out.println("Large test case (n=" + n + ", edges=" + times.length + "):");
        System.out.println("Dijkstra: " + result1 + " (Time: " + dijkstraTime/1000000 + " ms)");
        System.out.println("Bellman-Ford: " + result2 + " (Time: " + bellmanTime/1000000 + " ms)");
        System.out.println("DFS: " + result3 + " (Time: " + dfsTime/1000000 + " ms)");

        // Note: Floyd-Warshall is O(n³) so it would be very slow for n=50

        // Verify all approaches give same result
        if (result1 == result2 && result2 == result3) {
            System.out.println("✓ All approaches give consistent results");
        } else {
            System.out.println("✗ Results differ between approaches");
        }
    }
}
