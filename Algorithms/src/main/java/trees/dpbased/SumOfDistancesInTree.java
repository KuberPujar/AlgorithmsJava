package trees.dpbased;

import java.util.ArrayList;
import java.util.List;

/*
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.



Example 1:


Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.
Example 2:


Input: n = 1, edges = []
Output: [0]
Example 3:


Input: n = 2, edges = [[1,0]]
Output: [1,1]


Constraints:

1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
The given input represents a valid tree.
 */
public class SumOfDistancesInTree {

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) tree.add(new ArrayList<>());
        for (int[] e : edges) {
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }
        int[] res = new int[n];
        int[] count = new int[n];
        // Post-order: compute count and res for root
        dfs(0, -1, tree, count, res);
        // Pre-order: compute res for all nodes
        dfs2(0, -1, tree, count, res, n);
        return res;
    }

    private void dfs(int node, int parent, List<List<Integer>> tree, int[] count, int[] res) {
        count[node] = 1;
        for (int child : tree.get(node)) {
            if (child == parent) continue;
            dfs(child, node, tree, count, res);
            count[node] += count[child];
            res[node] += res[child] + count[child];
        }
    }

    private void dfs2(int node, int parent, List<List<Integer>> tree, int[] count, int[] res, int n) {
        for (int child : tree.get(node)) {
            if (child == parent) continue;
            res[child] = res[node] - count[child] + (n - count[child]);
            dfs2(child, node, tree, count, res, n);
        }
    }

    public static void main(String[] args) {
        SumOfDistancesInTree solution = new SumOfDistancesInTree();
        int n = 6;
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        int[] result = solution.sumOfDistancesInTree(n, edges);
        for (int dist : result) {
            System.out.print(dist + " ");
        }
        // Output: [8, 12, 6, 10, 10, 10]
    }
}
