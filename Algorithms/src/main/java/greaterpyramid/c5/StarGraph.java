package greaterpyramid.c5;
/*
here is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.

You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.

Example 1
image

Input
edges = [[1,2],[2,3],[4,2]]
Output
2
Explanation:
As shown in the figure above, node 2 is connected to every other node, so 2 is the center.

Input Format:

The first line contains an integer n, representing the number of nodes in the star graph.
Following this, there are n - 1 lines, each containing two space-separated integers ui and vi, representing an edge between the nodes ui and vi.
Output Format:

Output a single integer representing the label of the center node in the given star graph.
Sample Input 1:

18
8 2
8 3
4 8
1 8
8 5
6 8
7 8
9 8
11 8
10 8
8 12
13 8
14 8
8 15
16 8
17 8
8 18
19 8
Sample Output 1:

8
Constraints:
3 <= n <= 10^5
edges.length == n - 1
edges[i].length == 2
1 <= u, v <= n
u != v
The given edges represent a valid star graph.
 */
public class StarGraph {
    public static void main(String[] args) {
        int[][] edges = {
                {8, 2},
                {8, 3},
                {4, 8},
                {1, 8},
                {8, 5},
                {6, 8},
                {7, 8},
                {9, 8},
                {11, 8},
                {10, 8},
                {8, 12},
                {13, 8},
                {14, 8},
                {8, 15},
                {16, 8},
                {17, 8},
                {8, 18},
                {19, 8}
        };
        System.out.println(findCenter(edges)); // Output: 8
    }

    private static int findCenter(int[][] edges) {
        int center;
        if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            center = edges[0][0];
        } else {
            center = edges[0][1];
        }
return center;
    }
}
