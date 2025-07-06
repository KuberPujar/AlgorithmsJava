package graphs.bfs;

import java.util.*;

/*
In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.



Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]


Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.
 */
public class ReduntantConnection {
        // BFS approach to find the redundant connection
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            List<List<Integer>> adj = new ArrayList<>();

            // Initialize adjacency list
            for (int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
            }

            // We'll check each edge one by one
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                // Check if adding this edge would create a cycle
                if (hasPath(adj, u, v, new HashSet<>())) {
                    return edge;
                }

                // Add the edge to the graph
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            return new int[0]; // Shouldn't reach here per problem statement
        }

        // BFS to check if there's already a path between u and v
        private boolean hasPath(List<List<Integer>> adj, int u, int v, Set<Integer> visited) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(u);
            visited.add(u);

            while (!queue.isEmpty()) {
                int current = queue.poll();

                if (current == v) {
                    return true;
                }

                for (int neighbor : adj.get(current)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }

            return false;
        }

        //Union-Find approach to find the redundant connection
        public int[] findRedundantConnection1(int[][] edges) {
            int n = edges.length;
            int[] parent = new int[n + 1];

            // Initialize parent array
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                int rootU = find(parent, u);
                int rootV = find(parent, v);

                if (rootU == rootV) {
                    return edge;
                }

                parent[rootV] = rootU;
            }

            return new int[0];
        }

        private int find(int[] parent, int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]]; // Path compression
                x = parent[x];
            }
            return x;
        }

    public static void main(String[] args) {
        ReduntantConnection solution = new ReduntantConnection();
        int[][] edges1 = {{1, 2}, {1, 3}, {2, 3}};
        int[] result1 = solution.findRedundantConnection(edges1);
        System.out.println(Arrays.toString(result1)); // Output: [2, 3]

        int[][] edges2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[] result2 = solution.findRedundantConnection(edges2);
        System.out.println(Arrays.toString(result2)); // Output: [1, 4]
    }
}
