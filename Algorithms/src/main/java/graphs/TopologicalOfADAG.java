package graphs;

import java.util.*;

/*
opological Sorting of a Directed Acyclic Graph
You are given a directed acyclic graph (DAG) represented by its nodes and edges. Implement a program to find and print the topological sorting of the nodes using Kahn's algorithm.

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u-v, vertex u comes before v in the ordering.

Input:

The first line contains two integers, N representing the number of nodes and E representing the number of directed edges in the graph.
The following E lines each contain two integers u and v , where u is the starting node and v is the ending node of a directed edge.
. Output:

A list of elements representing the topological sort.
Examples: Input 1:

6 6
5 2
5 0
4 0
4 1
2 3
3 1
Output 1:

4 5 2 0 3 1
Explanation:

The topological sorting of this graph is a linear ordering of nodes where for every directed edge u → v, node u comes before node v in the ordering.

In the output 4 5 2 0 3 1:

4 and 5 have no incoming edges, so they start the order (4 chosen first here).

5 before 2 and 0 (due to edges 5 → 2, 5 → 0).

4 before 0 and 1 (due to edges 4 → 0, 4 → 1).

2 before 3 (due to 2 → 3).

3 before 1 (due to 3 → 1).

Order 4 5 2 0 3 1 is valid.


Input 2:

5 2
1 0
4 3
Output 2:

1 2 4 0 3
Explanation:

The output 1 2 4 0 3 is a valid topological order where:

1 before 0 (due to 1 → 0).

4 before 3 (due to 4 → 3).

2 has no edges, so it can appear anywhere.

Order 1 2 4 0 3 is valid.


Constraints:

1 <= N <= 10^5 (number of nodes)

0 <= E <= 10^6 (number of edges)

0 <= u, v < N (node indices)

The graph is guaranteed to be a directed acyclic graph (DAG).

Note:
The function should return the result.
 */
public class TopologicalOfADAG {
    public static List<Integer> topologicalSort(int N, int E, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[N];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result.add(u);
            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int E = scanner.nextInt();
        int[][] edges = new int[E][2];
        for (int i = 0; i < E; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }

        List<Integer> sorted = topologicalSort(N, E, edges);
        for (int i = 0; i < sorted.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(sorted.get(i));
        }
        System.out.println();
    }
}
