package graphs;

import java.util.*;

/*
Find Eventual Safe States
Given a directed graph with V vertices and E edges represented as an adjacency list, your task is to find all the "safe" nodes in the graph. A node is considered safe if every possible path starting from that node leads to a terminal node. A terminal node is one from which no outgoing edges are present, implying it doesn't lead to any other node.

Definitions:
Directed Graph: A graph where the edges have a direction, indicating the relationship flows from one vertex to another.
Adjacency List: A way of representing a graph where a list or an array is used to represent the immediate neighbors that each vertex can go to.
Safe Node: A node from which all possible paths lead to a terminal node.
Terminal Node: A node that has no outgoing edges.
Input Format:
The first line contains two space-separated integers, V(the number of vertices) andE (the number of edges), representing the graph's vertices and edges, respectively.
The next Elines contain two integers each,uandv, representing a directed edge from vertex uto vertexv.
Output Format:
Output a single line containing the safe nodes of the graph sorted in ascending order. Each node should be represented by its integer label.
`Constraints:
1 <= V <= 10^4
1 <= E <= 4 * 10^4
0 <= u, v < V
The graph may contain self-loops and multiple edges.
adj[i]is sorted in a strictly increasing order for any nodei.
Example:
Input:

7 7
0 1
0 2
1 2
1 3
3 0
4 5
2 5
Output:

2 4 5 6
Explanation:
In the given graph, nodes 5 and 6 are terminal nodes as there are no outgoing edges from them.
Starting from node 2, one can reach terminal node 5.
Nodes 4 and 6 are also terminal nodes, thus considered safe.
Therefore, the safe nodes in the graph are 2, 4, 5, and 6.
Constraints:
1 <= V <= 10^4 (The number of vertices can go up to 10,000)
1 <= E <= 4 * 10^4 (The number of edges can go up to 40,000)
0 <= u, v < V (Each edge is between nodes u and v, both within the range of the total vertices)
The graph may contain self-loops and multiple edges.
The adjacency list for any node i is sorted in strictly increasing order.
Note: The function should return a boolean value.
 */
public class FindEventualSafeStates {
    @SuppressWarnings("unchecked")
    public static List<Integer> findSafeNodes(int V, List<List<Integer>> adj) {
        int[] out = new int[V];
        List<Integer>[] revAdj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            revAdj[i] = new ArrayList<>();
        }

        for (List<Integer> integers : adj) {
            int u = integers.get(0);
            int v = integers.get(1);
            if (u < 0 || u >= V || v < 0 || v >= V) {
                continue;
            }
            out[u]++;
            revAdj[v].add(u);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] safe = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (out[i] == 0) {
                queue.add(i);
                safe[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : revAdj[v]) {
                out[u]--;
                if (out[u] == 0) {
                    safe[u] = true;
                    queue.add(u);
                }
            }
        }

        List<Integer> safeNodesList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (safe[i]) {
                safeNodesList.add(i);
            }
        }

        return safeNodesList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V = scanner.nextInt();
        int E = scanner.nextInt();

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            List<Integer> edge = new ArrayList<>();
            edge.add(scanner.nextInt());
            edge.add(scanner.nextInt());
            edges.add(edge);
        }

        List<Integer> safeNodes = findSafeNodes(V, edges);

        for (int i = 0; i < safeNodes.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(safeNodes.get(i));
        }
        System.out.println();
    }
}
