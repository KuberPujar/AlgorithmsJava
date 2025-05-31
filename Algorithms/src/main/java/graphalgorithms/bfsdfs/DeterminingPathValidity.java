package graphalgorithms.bfsdfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Determining Path Validity
Given a directed graph, determine if there is a valid path from a specified source node to a destination node such that all possible paths starting from the source node lead exclusively to the destination node, without any cycles or divergent paths.
Note: Do not recreate the adjacency list from the edge list — it is already built and passed to your function by the driver code.

Input Format:

An integer nrepresenting the number of nodes in the graph, where the nodes are numbered from 0 ton-1.
An integer m representing the number of directed edges in the graph.
m pairs of integers where each pair [u, v]represents a directed edge from nodeuto nodev.
Two distinct integers sourceanddestination representing the starting node and the target node, respectively.
Output Format

A boolean value false if there is any cycle involving the source node, or if there exists any path from the source that does not lead to the destination.
Otherwise, return true.
Example:

Input:

3 3
0 1
1 2
2 0
0 2
Output:

false
Explanation

Although there is a path from node 0 to node 2, there is also a cycle formed by the edges `0 -> 1 -> 2 -> 0`. Since there exists a cycle that includes the source node, not all paths from node 0 lead to node 2. The correct output is `false`.
Input:

5 5
0 1
1 2
2 3
3 4
4 0
0 4
Output:

false
Explanation

There is a cycle in the graph formed by the edges 0 -> 1 -> 2 -> 3 -> 4 -> 0. Since this cycle involves the source node, the output is false.
Constraints:`

1 <= n <= 100
0 <= m <= 1,000
0 <= u, v < n
Note:The function should return the result. The driver code will handle printing the output.
 */
public class DeterminingPathValidity {

    public static boolean isValidPath(List<List<Integer>> graph, int source, int destination) {
        int n = graph.size();

        if (!graph.get(destination).isEmpty()) {
            return false;
        }

        List<List<Integer>> reversed = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reversed.add(new ArrayList<>());
        }
        for (int u = 0; u < n; u++) {
            for (int v : graph.get(u)) {
                reversed.get(v).add(u);
            }
        }

        boolean[] reachableFromDest = new boolean[n];
        Queue<Integer> queueRev = new LinkedList<>();
        queueRev.offer(destination);
        reachableFromDest[destination] = true;
        while (!queueRev.isEmpty()) {
            int node = queueRev.poll();
            for (int neighbor : reversed.get(node)) {
                if (!reachableFromDest[neighbor]) {
                    reachableFromDest[neighbor] = true;
                    queueRev.offer(neighbor);
                }
            }
        }

        boolean[] reachableFromSource = new boolean[n];
        Queue<Integer> queueSource = new LinkedList<>();
        queueSource.offer(source);
        reachableFromSource[source] = true;
        while (!queueSource.isEmpty()) {
            int node = queueSource.poll();
            for (int neighbor : graph.get(node)) {
                if (!reachableFromSource[neighbor]) {
                    reachableFromSource[neighbor] = true;
                    queueSource.offer(neighbor);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (reachableFromSource[i] && !reachableFromDest[i]) {
                return false;
            }
        }

        int[] state = new int[n];
        for (int i = 0; i < n; i++) {
            if (reachableFromSource[i] && state[i] == 0) {
                if (hasCycle(i, graph, state, reachableFromSource)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean hasCycle(int node, List<List<Integer>> graph, int[] state, boolean[] inComponent) {
        state[node] = 1;
        for (int neighbor : graph.get(node)) {
            if (!inComponent[neighbor]) {
                continue;
            }
            if (state[neighbor] == 0) {
                if (hasCycle(neighbor, graph, state, inComponent)) {
                    return true;
                }
            } else if (state[neighbor] == 1) {
                return true;
            }
        }
        state[node] = 2;
        return false;
    }

    public static void main(String[] args) {
        // Example usage
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(List.of(1)); // 0 -> 1
        graph.add(List.of(2)); // 1 -> 2
        graph.add(List.of(0)); // 2 -> 0 (cycle)
        int source = 0;
        int destination = 2;
        boolean result = isValidPath(graph, source, destination);
        System.out.println(result); // Output: false
    }
}
