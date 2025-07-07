package graphs.coloring;

import java.util.*;

/*
You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.



Example 1:

Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]


Constraints:

1 <= n <= 100
0 <= redEdges.length, blueEdges.length <= 400
redEdges[i].length == blueEdges[j].length == 2
0 <= ai, bi, uj, vj < n
 */
public class ShortestPathWithAlternatingColors {

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }
        for (int[] e : redEdges) redGraph[e[0]].add(e[1]);
        for (int[] e : blueEdges) blueGraph[e[0]].add(e[1]);

        int[] res = new int[n];
        Arrays.fill(res, -1);

        // [node, color], color: 0=red, 1=blue
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][2];
        queue.offer(new int[]{0, 0}); // start with red
        queue.offer(new int[]{0, 1}); // start with blue
        visited[0][0] = visited[0][1] = true;
        int dist = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0], color = curr[1];
                if (res[node] == -1) res[node] = dist;
                List<Integer>[] nextGraph = color == 0 ? blueGraph : redGraph;
                int nextColor = 1 - color;
                for (int nei : nextGraph[node]) {
                    if (!visited[nei][nextColor]) {
                        visited[nei][nextColor] = true;
                        queue.offer(new int[]{nei, nextColor});
                    }
                }
            }
            dist++;
        }
        return res;
    }

    public static void main(String[] args) {
        ShortestPathWithAlternatingColors solution = new ShortestPathWithAlternatingColors();
        int n1 = 3;
        int[][] redEdges1 = {{0, 1}, {1, 2}};
        int[][] blueEdges1 = {};
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(n1, redEdges1, blueEdges1))); // Output: [0, 1, -1]

        int n2 = 3;
        int[][] redEdges2 = {{0, 1}};
        int[][] blueEdges2 = {{2, 1}};
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(n2, redEdges2, blueEdges2))); // Output: [0, 1, -1]
    }
}
