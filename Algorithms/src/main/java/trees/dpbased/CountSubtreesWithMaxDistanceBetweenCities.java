package trees.dpbased;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
There are n cities numbered from 1 to n. You are given an array edges of size n-1, where edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi. There exists a unique path between each pair of cities. In other words, the cities form a tree.

A subtree is a subset of cities where every city is reachable from every other city in the subset, where the path between each pair passes through only the cities from the subset. Two subtrees are different if there is a city in one subtree that is not present in the other.

For each d from 1 to n-1, find the number of subtrees in which the maximum distance between any two cities in the subtree is equal to d.

Return an array of size n-1 where the dth element (1-indexed) is the number of subtrees in which the maximum distance between any two cities is equal to d.

Notice that the distance between the two cities is the number of edges in the path between them.



Example 1:



Input: n = 4, edges = [[1,2],[2,3],[2,4]]
Output: [3,4,0]
Explanation:
The subtrees with subsets {1,2}, {2,3} and {2,4} have a max distance of 1.
The subtrees with subsets {1,2,3}, {1,2,4}, {2,3,4} and {1,2,3,4} have a max distance of 2.
No subtree has two nodes where the max distance between them is 3.
Example 2:

Input: n = 2, edges = [[1,2]]
Output: [1]
Example 3:

Input: n = 3, edges = [[1,2],[2,3]]
Output: [2,1]


Constraints:

2 <= n <= 15
edges.length == n-1
edges[i].length == 2
1 <= ui, vi <= n
All pairs (ui, vi) are distinct.
 */
public class CountSubtreesWithMaxDistanceBetweenCities {

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        // Build adjacency matrix
        boolean[][] adj = new boolean[n][n];
        for (int[] e : edges) {
            adj[e[0] - 1][e[1] - 1] = true;
            adj[e[1] - 1][e[0] - 1] = true;
        }
        int[] res = new int[n - 1];
        // Enumerate all subsets (except singletons)
        for (int mask = 1; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) < 2) continue;
            // Check if connected
            if (!isConnected(mask, adj, n)) continue;
            // Compute max distance using Floyd-Warshall
            int maxDist = getMaxDist(mask, adj, n);
            if (maxDist > 0) res[maxDist - 1]++;
        }
        return res;
    }

    private boolean isConnected(int mask, boolean[][] adj, int n) {
        int start = Integer.numberOfTrailingZeros(mask);
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.offer(start);
        visited[start] = true;
        int seen = 1;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < n; v++) {
                if (((mask >> v) & 1) == 1 && adj[u][v] && !visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                    seen++;
                }
            }
        }
        return seen == Integer.bitCount(mask);
    }

    private int getMaxDist(int mask, boolean[][] adj, int n) {
        int[][] dist = new int[n][n];
        final int INF = 1000;
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], INF);
        for (int i = 0; i < n; i++) {
            if (((mask >> i) & 1) == 1) {
                dist[i][i] = 0;
                for (int j = 0; j < n; j++) {
                    if (((mask >> j) & 1) == 1 && adj[i][j]) {
                        dist[i][j] = 1;
                    }
                }
            }
        }
        // Floyd-Warshall
        for (int k = 0; k < n; k++) {
            if (((mask >> k) & 1) == 0) continue;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (((mask >> j) & 1) == 0) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (((mask >> i) & 1) == 0) continue;
            for (int j = i + 1; j < n; j++) {
                if (((mask >> j) & 1) == 0) continue;
                if (dist[i][j] < 1000) max = Math.max(max, dist[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        CountSubtreesWithMaxDistanceBetweenCities solution = new CountSubtreesWithMaxDistanceBetweenCities();
        int n = 4;
        int[][] edges = {{1, 2}, {2, 3}, {2, 4}};
        int[] result = solution.countSubgraphsForEachDiameter(n, edges);
        System.out.println(Arrays.toString(result)); // Output: [3, 4, 0]
    }
}
