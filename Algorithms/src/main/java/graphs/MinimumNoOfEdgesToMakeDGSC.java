package graphs;

import java.util.*;

/*
Minimum Number of Edges to Make a Directed Graph Strongly Connected
Write a program to find the minimum number of edges to add to a directed graph to make it strongly connected.

A directed graph is strongly connected if there is a path from every node to every other node in the graph. In other words, for every pair of nodes (u, v), there should be a directed path from u to v and from v to u.

Input:
The first line of input contains two space separated integers 'N' and 'M' which are the number of nodes and edges in the graph
The next line contains 'M' space separated integers denoting the source node of each edge.
The next line contains 'M' space separated integers denoting the destination node of each edge.
Output:
The minimum number of edges to add or "Not possible" if it's not possible to make the graph strongly connected.

Sample input 1:
5 5
1 3 1 3 4
2 2 3 4 5
Sample Output 1:
2
Explanation:
You can make the graph strongly connected by inserting a directed edge from 2 to 5 and another from 5 to 1. The graph formed now allows you to go from every vertex to every other vertex along the directed edges and thus is a strongly connected graph.

Sample input 2:
5 5
1 1 2 3 4
2 3 3 4 5
Sample Output 2:
1
Explanation:
You can make the graph strongly connected by adding a directed edge from node 5 to node 1. After adding this edge, every node can be reached from every other node, thus making the graph strongly connected.

Constraints:
1 <= N <= 10^5 (number of nodes)

0 <= E <= 10^6 (number of edges)

0 <= u, v < N (node indices)

The graph is guaranteed to be directed but not necessarily strongly connected.

Note:
The function should return the results instead of printing.
 */
public class MinimumNoOfEdgesToMakeDGSC {
    private static class Pair {
        int node;
        boolean processed;

        Pair(int node, boolean processed) {
            this.node = node;
            this.processed = processed;
        }
    }

    public static int minEdges(int N, int M, int[] sources, int[] destinations) {
        // Convert input to 0-based indices
        for (int i = 0; i < M; i++) {
            sources[i]--;
            destinations[i]--;
        }

        // Build adjacency list and transpose
        List<List<Integer>> adj = new ArrayList<>(N);
        List<List<Integer>> transpose = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
            transpose.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = sources[i];
            int v = destinations[i];
            adj.get(u).add(v);
            transpose.get(v).add(u);
        }

        // Kosaraju's algorithm to find SCCs
        boolean[] visited = new boolean[N];
        Deque<Integer> orderStack = new ArrayDeque<>();

        // First pass to determine the order of processing
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfsIterative(i, adj, visited, orderStack);
            }
        }

        // Second pass to find SCCs
        Arrays.fill(visited, false);
        int[] sccId = new int[N];
        int currentComponent = 0;

        while (!orderStack.isEmpty()) {
            int u = orderStack.pop();
            if (!visited[u]) {
                Deque<Integer> componentStack = new ArrayDeque<>();
                componentStack.push(u);
                visited[u] = true;
                sccId[u] = currentComponent;

                while (!componentStack.isEmpty()) {
                    int node = componentStack.pop();
                    for (int v : transpose.get(node)) {
                        if (!visited[v]) {
                            visited[v] = true;
                            sccId[v] = currentComponent;
                            componentStack.push(v);
                        }
                    }
                }
                currentComponent++;
            }
        }

        if (currentComponent == 1) {
            return 0;
        }

        // Build condensation graph and calculate in-degree and out-degree
        int[] inDegree = new int[currentComponent];
        int[] outDegree = new int[currentComponent];
        List<Set<Integer>> condEdges = new ArrayList<>(currentComponent);
        for (int i = 0; i < currentComponent; i++) {
            condEdges.add(new HashSet<>());
        }

        for (int i = 0; i < M; i++) {
            int u = sources[i];
            int v = destinations[i];
            int sccU = sccId[u];
            int sccV = sccId[v];
            if (sccU != sccV) {
                if (!condEdges.get(sccU).contains(sccV)) {
                    condEdges.get(sccU).add(sccV);
                    inDegree[sccV]++;
                    outDegree[sccU]++;
                }
            }
        }

        // Count sources and sinks in the condensation graph
        int sourcesCount = 0;
        int sinksCount = 0;
        for (int i = 0; i < currentComponent; i++) {
            if (inDegree[i] == 0) {
                sourcesCount++;
            }
            if (outDegree[i] == 0) {
                sinksCount++;
            }
        }

        return Math.max(sourcesCount, sinksCount);
    }

    private static void dfsIterative(int start, List<List<Integer>> adj, boolean[] visited, Deque<Integer> orderStack) {
        Deque<Pair> dfsStack = new ArrayDeque<>();
        dfsStack.push(new Pair(start, false));

        while (!dfsStack.isEmpty()) {
            Pair pair = dfsStack.pop();
            int u = pair.node;
            boolean processed = pair.processed;

            if (processed) {
                orderStack.push(u);
                continue;
            }

            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            dfsStack.push(new Pair(u, true));

            // Push children in reverse order to process them in the correct order
            List<Integer> neighbors = adj.get(u);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int v = neighbors.get(i);
                if (!visited[v]) {
                    dfsStack.push(new Pair(v, false));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] sources = new int[M];
        int[] destinations = new int[M];
        for (int i = 0; i < M; i++) {
            sources[i] = scanner.nextInt();
        }
        for (int i = 0; i < M; i++) {
            destinations[i] = scanner.nextInt();
        }

        int result = minEdges(N, M, sources, destinations);
        System.out.println(result);
    }
}
