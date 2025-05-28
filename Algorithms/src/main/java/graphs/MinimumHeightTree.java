package graphs;

import java.util.*;

/*
Minimum Height Tree
Given an undirected tree comprising n nodes connected by a series of edges, the task is to determine which node(s), when used as the root, produce the minimum possible height for the tree.

Input Format:

An integernthat denotes the total number of nodes in the tree. Each node is uniquely numbered from0ton-1.
A list of mpairs of integers[u, v], each representing an undirected edge that connects nodes uandv, thus forming the tree.
Output Format:

A list containing the number(s) of the node(s) that, if chosen as the root, will minimize the tree's height. If multiple nodes meet this criterion, return a list of these nodes.
Definitions:

Tree Height: The maximum length of a path from the root node down to the farthest leaf node.
Leaf Node: A node that is connected to exactly one other node, making it an endpoint in the tree.
Example:

Input:
n = 6
edges = [[0, 3], [1, 3], [2, 3], [3, 4], [4, 5]]
Output:
[3, 4]

Explanation:
By choosing either node 3 or node 4 as the tree's root, the resultant tree height is minimized to 2. Selecting any other node as the root results in a taller tree.
Constraints:

1≤n≤100
0≤number of edges=m≤200, with m=n−1 ensuring the structure is a tree.
The edges list contains no duplicates or self-loops, ensuring a valid, undirected tree representation.
Note: The function should return the result.
 */
public class MinimumHeightTree {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        int remainingNodes = n;
        while (remainingNodes > 2) {
            int size = queue.size();
            remainingNodes -= size;
            for (int i = 0; i < size; i++) {
                if(queue.isEmpty()) break; // Safety check
                int leaf = queue.poll();
                for (int neighbor : graph.get(leaf)) {
                    if (--degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>(queue);
    }

    public static void main(String[] args) {
        MinimumHeightTree mht = new MinimumHeightTree();
        int n = 6;
        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {3, 4}, {4, 5}};
        List<Integer> result = mht.findMinHeightTrees(n, edges);
        System.out.println(result); // Output: [3, 4]
    }
}
