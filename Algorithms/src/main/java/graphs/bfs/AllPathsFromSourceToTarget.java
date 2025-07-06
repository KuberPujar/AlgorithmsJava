package graphs.bfs;

import java.util.*;

/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).



Example 1:


Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Example 2:


Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]


Constraints:

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i (i.e., there will be no self-loops).
All the elements of graph[i] are unique.
The input graph is guaranteed to be a DAG.
 */
public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        int target = graph.length - 1;

        // Queue to store paths
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(0)); // Start with path containing just node 0

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int lastNode = currentPath.get(currentPath.size() - 1);

            // If we've reached the target, add to results
            if (lastNode == target) {
                result.add(currentPath);
                continue;
            }

            // Explore all neighbors
            for (int neighbor : graph[lastNode]) {
                // Create new path by extending current path
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbor);
                queue.offer(newPath);
            }
        }

        return result;
    }

    // Alternative BFS implementation using LinkedList for paths more efficiently
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        int target = graph.length - 1;

        Queue<LinkedList<Integer>> queue = new LinkedList<>();
        LinkedList<Integer> initialPath = new LinkedList<>();
        initialPath.add(0);
        queue.offer(initialPath);

        while (!queue.isEmpty()) {
            LinkedList<Integer> currentPath = queue.poll();
            int lastNode = currentPath.getLast();

            if (lastNode == target) {
                result.add(new ArrayList<>(currentPath));
                continue;
            }

            for (int neighbor : graph[lastNode]) {
                LinkedList<Integer> newPath = new LinkedList<>(currentPath);
                newPath.add(neighbor);
                queue.offer(newPath);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        AllPathsFromSourceToTarget solution = new AllPathsFromSourceToTarget();
        int[][] graph1 = {{1, 2}, {3}, {3}, {}};
        System.out.println(solution.allPathsSourceTarget(graph1)); // [[0, 1, 3], [0, 2, 3]]

        int[][] graph2 = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        System.out.println(solution.allPathsSourceTarget(graph2)); // [[0, 4], [0, 3, 4], [0, 1, 3, 4], [0, 1, 2, 3, 4], [0, 1, 4]]
    }
}
