package graphs.simplegraph;
/*
There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.

You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.



Example 1:


Input: edges = [[1,2],[2,3],[4,2]]
Output: 2
Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
Example 2:

Input: edges = [[1,2],[5,1],[1,3],[1,4]]
Output: 1


Constraints:

3 <= n <= 105
edges.length == n - 1
edges[i].length == 2
1 <= ui, vi <= n
ui != vi
The given edges represent a valid star graph.
 */
public class FindCenterOfStarGraph {
    public int findCenter(int[][] edges) {
        // The center node will appear in every edge
        // So we just need to check the first two edges

        // Check if the first node of first edge appears in second edge
        if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            return edges[0][0];
        }

        // Otherwise, it must be the second node of first edge
        return edges[0][1];
    }

    public static void main(String[] args) {
        FindCenterOfStarGraph finder = new FindCenterOfStarGraph();

        // Example 1
        int[][] edges1 = {{1, 2}, {2, 3}, {4, 2}};
        System.out.println(finder.findCenter(edges1)); // Output: 2

        // Example 2
        int[][] edges2 = {{1, 2}, {5, 1}, {1, 3}, {1, 4}};
        System.out.println(finder.findCenter(edges2)); // Output: 1
    }
}
