package graphs;

import java.util.*;

/*
Maximum Flow in a Flow Network
Implement a program to calculate the maximum flow from a source node to a sink node in a given flow network. The network is represented as a directed graph where each edge has a capacity, and the flow must not exceed this capacity for any edge. Utilize the Ford-Fulkerson algorithm for this purpose.

Input Format:

The first line contains two integers, N and M, where N represents the number of nodes in the graph, and M indicates the number of edges.
The next M lines each contain three integers u, v, and w, describing an edge from node u to node v with a capacity of w.
Nodes are numbered from 1 to N, where 1 is considered the source node and N the sink node.
Output Format:

Output a single integer representing the maximum flow from the source node to the sink node in the given flow network.
Sample Input 1:

4 5
1 2 100
1 3 50
2 4 50
3 4 100
2 3 50
Sample Output 1:

150
Explanation:
The sample input represents a flow network with 4 nodes and 5 edges. The maximum flow from the source node 1 to the sink node 4 is 150.

Sample Input 2:

4 6
1 2 3
1 3 2
1 4 4
2 3 1
2 4 7
3 4 5
Sample Output 2:

9
Explanation:
The sample input represents a flow network with 4 nodes and 6 edges. The maximum possible flow from the source node 1 to the sink node 4 is 9. This maximum flow is determined by considering the capacities of the edges and finding the optimal way to send as much flow as possible from the source to the sink.

Constraints:

1 <= N <= 10^4
0 <= M <= 10^5
1 <= u, v < N
0 <= w <= 10^9
Note: The function should return the answer instead of printing it.
 */
public class MaximumFlowInAFlowNetwork {
    static class Edge {
        int to;
        int capacity;
        Edge reverse;

        public Edge(int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
        }
    }

    public static int maxFlow(int N, int M, int[][] edges) {
        int source = 0;
        int sink = N - 1;

        List<List<Edge>> adj = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0] - 1; // Convert to 0-based
            int v = edge[1] - 1;
            int capacity = edge[2];

            Edge forward = new Edge(v, capacity);
            Edge backward = new Edge(u, 0);
            forward.reverse = backward;
            backward.reverse = forward;
            adj.get(u).add(forward);
            adj.get(v).add(backward);
        }

        int maxFlow = 0;

        while (true) {
            int[] parentNodes = new int[N];
            Edge[] parentEdges = new Edge[N];
            Arrays.fill(parentNodes, -1);

            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            parentNodes[source] = source;

            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (Edge e : adj.get(u)) {
                    if (parentNodes[e.to] == -1 && e.capacity > 0) {
                        parentNodes[e.to] = u;
                        parentEdges[e.to] = e;
                        queue.add(e.to);
                        if (e.to == sink) {
                            queue.clear();
                            break;
                        }
                    }
                }
            }

            if (parentNodes[sink] == -1) {
                break;
            }

            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parentNodes[v]) {
                pathFlow = Math.min(pathFlow, parentEdges[v].capacity);
            }

            for (int v = sink; v != source; v = parentNodes[v]) {
                Edge e = parentEdges[v];
                e.capacity -= pathFlow;
                e.reverse.capacity += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
            edges[i][2] = scanner.nextInt();
        }
        System.out.println(maxFlow(N, M, edges));
    }
}
