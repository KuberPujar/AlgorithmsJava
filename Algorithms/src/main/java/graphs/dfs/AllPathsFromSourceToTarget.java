package graphs.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        // Check for empty graph
        if (graph == null || graph.length == 0) {
            return result;
        }

        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0); // Start with node 0
        dfs(graph, 0, graph.length - 1, currentPath, result);
        return result;
    }

    private void dfs(int[][] graph, int currentNode, int target,
                     List<Integer> currentPath, List<List<Integer>> result) {
        // Base case: reached target node
        if (currentNode == target) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        // Check if current node has neighbors
        if (graph[currentNode] == null || graph[currentNode].length == 0) {
            return; // Dead end
        }

        // Explore all neighbors
        for (int neighbor : graph[currentNode]) {
            // Validate neighbor index
            if (neighbor < 0 || neighbor >= graph.length) {
                continue; // Skip invalid nodes
            }
            currentPath.add(neighbor);
            dfs(graph, neighbor, target, currentPath, result);
            currentPath.remove(currentPath.size() - 1); // Backtrack
        }
    }

    //Iterative version
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();

        // Start with path containing just node 0
        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(0);
        stack.push(initialPath);

        while (!stack.isEmpty()) {
            List<Integer> currentPath = stack.pop();
            int lastNode = currentPath.get(currentPath.size() - 1);

            if (lastNode == graph.length - 1) {
                result.add(new ArrayList<>(currentPath));
                continue;
            }

            for (int neighbor : graph[lastNode]) {
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbor);
                stack.push(newPath);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        AllPathsFromSourceToTarget solution = new AllPathsFromSourceToTarget();
        int[][] graph1 = {{1, 2}, {3}, {3}, {}};
        System.out.println(solution.allPathsSourceTarget(graph1)); // [[0, 1, 3], [0, 2, 3]]

        int[][] graph2 = {{4, 3, 1}, {3, 2, 4}, {3}, {4}};
        System.out.println(solution.allPathsSourceTarget(graph2)); // [[0, 4], [0, 3, 4], [0, 1, 3, 4], [0, 1, 2, 3, 4], [0, 1, 4]]
    }
}
