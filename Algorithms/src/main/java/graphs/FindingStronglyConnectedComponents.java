package graphs;

import java.util.*;

/*
Finding Strongly Connected Components
Problem: Finding the Number of Strongly Connected Components (SCC) in a Directed Graph

Note: Strongly Connected Components (SCC)
A Strongly Connected Component (SCC) of a directed graph is a maximal subgraph where any two vertices are reachable from each other. In other words, every vertex in an SCC can reach every other vertex in that SCC.
Example
Consider the following directed graph:

A → B → C
↑   ↓   ↓
D ← E → F
Input:
First line contains two integers N and E, where:
N is the number of nodes in the graph.
E is the number of directed edges.
Next E lines contain two integers u and v representing a directed edge from node u to node v.
Output:

Print a single integer denoting the number of strongly connected components in the graph.
Sample input 1:
5 6
1 2
2 3
3 1
3 4
4 5
5 4
Sample output 1:
2
Explanation
There are 2 strongly connected components in this graph.
The first component contains nodes {1, 2, 3}, where each node is reachable from every other node.
The second component contains nodes {4, 5}, where both nodes are mutually reachable.
Sample input 2:
3 3
1 2
2 3
3 1
Sample output 2:
1
Explanation
All the nodes {1, 2, 3} form one strongly connected component since every node can reach every other node.
Constraints:
1 <= N <= 10^5 (number of nodes)

0 <= E <= 10^6 (number of edges)

0 <= u, v < N (node indices)

Note: The function should return the result instead of printing it.
 */
public class FindingStronglyConnectedComponents {
    /**
     * Finds the number of strongly connected components (SCCs) in a directed graph.
     *
     * @param n The number of nodes in the graph.
     * @param a The adjacency list representation of the directed graph.
     * @return The number of strongly connected components in the graph.
     */
    public static int findSCCs(int n, List<List<Integer>> a) {
        if (n <= 0) {
            return 0; // No nodes means no SCCs, or handle invalid input.
        }

        List<List<Integer>> transposeGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            transposeGraph.add(new ArrayList<>());
        }

        for (int u = 0; u < n; u++) {
            for (int v : a.get(u)) {
                transposeGraph.get(v).add(u);
            }
        }

        boolean[] visited = new boolean[n];
        java.util.Deque<Integer> finishingOrder = new java.util.ArrayDeque<>();

        // First pass to determine finishing order using iterative DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
                stack.push(i);
                visited[i]=true;
                java.util.Deque<Integer> recursionStack = new java.util.ArrayDeque<>();

                while (!stack.isEmpty()) {
                    int u = stack.pop();
                    recursionStack.push(u);

                    for (int v : a.get(u)) {
                        if (!visited[v]) {
                            visited[v]=true;
                            stack.push(v);
                        }
                    }
                }

                while (!recursionStack.isEmpty()) {
                    finishingOrder.push(recursionStack.pop());
                }
            }
        }

        // Second pass on the transpose graph
        boolean[] visitedTranspose = new boolean[n];
        int sccCount = 0;

        while (!finishingOrder.isEmpty()) {
            int u = finishingOrder.pop();
            if (!visitedTranspose[u]) {
                sccCount++;
                java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
                stack.push(u);
                visitedTranspose[u] = true;

                while (!stack.isEmpty()) {
                    int current = stack.pop();
                    for (int v : transposeGraph.get(current)) {
                        if (!visitedTranspose[v]) {
                            visitedTranspose[v] = true;
                            stack.push(v);
                        }
                    }
                }
            }
        }

        return sccCount;
    }

    /**
     * Main method for example usage and testing.
     * This part is for demonstration and would not typically be part of the
     * class if it's intended to be used as a library or in a coding platform.
     */
    public static void main(String[] args) {

        // Sample Input 1:
        // N = 5, E = 6
        // Edges (1-indexed in problem, converted to 0-indexed for List 'a'):
        // 1 2  -> 0 1
        // 2 3  -> 1 2
        // 3 1  -> 2 0
        // 3 4  -> 2 3
        // 4 5  -> 3 4
        // 5 4  -> 4 3
        int n1 = 5;
        List<List<Integer>> a1 = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            a1.add(new ArrayList<>());
        }
        a1.get(0).add(1);
        a1.get(1).add(2);
        a1.get(2).add(0);
        a1.get(2).add(3);
        a1.get(3).add(4);
        a1.get(4).add(3);
        System.out.println("Sample 1 - Number of SCCs: " + findSCCs(n1, a1)); // Expected: 2

        // Sample Input 2:
        // N = 3, E = 3
        // Edges (0-indexed):
        // 1 2  -> 0 1
        // 2 3  -> 1 2
        // 3 1  -> 2 0
        int n2 = 3;
        List<List<Integer>> a2 = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            a2.add(new ArrayList<>());
        }
        a2.get(0).add(1);
        a2.get(1).add(2);
        a2.get(2).add(0);
        System.out.println("Sample 2 - Number of SCCs: " + findSCCs(n2, a2)); // Expected: 1

        // Additional Test Cases:
        // No edges
        int n3 = 3;
        List<List<Integer>> a3 = new ArrayList<>();
        for (int i = 0; i < n3; i++) {
            a3.add(new ArrayList<>());
        }
        System.out.println("No edges (3 nodes) - Number of SCCs: " + findSCCs(n3, a3)); // Expected: 3

        // Single node
        int n4 = 1;
        List<List<Integer>> a4 = new ArrayList<>();
        a4.add(new ArrayList<>());
        System.out.println("Single node - Number of SCCs: " + findSCCs(n4, a4)); // Expected: 1

        // Empty graph
        int n5 = 0;
        List<List<Integer>> a5 = new ArrayList<>();
        System.out.println("Empty graph (0 nodes) - Number of SCCs: " + findSCCs(n5, a5)); // Expected: 0
    }
}
