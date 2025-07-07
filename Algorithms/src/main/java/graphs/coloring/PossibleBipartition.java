package graphs.coloring;

import java.util.ArrayList;
import java.util.List;

/*
We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.



Example 1:

Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: The first group has [1,4], and the second group has [2,3].
Example 2:

Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.


Constraints:

1 <= n <= 2000
0 <= dislikes.length <= 104
dislikes[i].length == 2
1 <= ai < bi <= n
All the pairs of dislikes are unique.
 */
public class PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] d : dislikes) {
            graph[d[0]].add(d[1]);
            graph[d[1]].add(d[0]);
        }
        int[] color = new int[n + 1]; // 0: uncolored, 1: color1, -1: color2
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0 && !dfs(i, 1, color, graph)) return false;
        }
        return true;
    }

    private boolean dfs(int node, int c, int[] color, List<Integer>[] graph) {
        color[node] = c;
        for (int nei : graph[node]) {
            if (color[nei] == c) return false;
            if (color[nei] == 0 && !dfs(nei, -c, color, graph)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PossibleBipartition solution = new PossibleBipartition();
        int n1 = 4;
        int[][] dislikes1 = {{1, 2}, {1, 3}, {2, 4}};
        System.out.println(solution.possibleBipartition(n1, dislikes1)); // Output: true

        int n2 = 3;
        int[][] dislikes2 = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println(solution.possibleBipartition(n2, dislikes2)); // Output: false
    }
}
