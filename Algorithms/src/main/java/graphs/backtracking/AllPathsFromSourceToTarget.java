package graphs.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
/*
Explanation:

    Initialization:

        We start with a path containing just the source node (0).

        The result list will store all valid paths we find.

    Backtracking Implementation:

        The backtrack method recursively explores all paths:

            Base case: when we reach the target node (n-1), we add the current path to results

            For each neighbor of the current node:

                Add the neighbor to the current path

                Recursively continue the search

                Remove the neighbor from the path (backtracking step)

    DAG Property:

        Since the graph is acyclic, we don't need to track visited nodes (no cycles means no infinite recursion)

        This simplifies the backtracking implementation compared to general graphs

Key Features:

    Time Complexity: O(2^n) in worst case (exponential number of possible paths in DAG)

    Space Complexity: O(n) for recursion stack (excluding the space needed for the result)

    Correctness:

        Guaranteed to find all paths from source to target

        Properly handles backtracking to explore all possibilities

    Edge Cases:

        Works with graphs that have multiple paths to target

        Handles the maximum constraint size (n = 15)
 */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0); // Start with node 0
        backtrack(graph, 0, graph.length - 1, currentPath, result);
        return result;
    }

    private void backtrack(int[][] graph, int currentNode, int target,
                           List<Integer> currentPath, List<List<Integer>> result) {
        // Base case: reached target node
        if (currentNode == target) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        // Explore all neighbors
        for (int neighbor : graph[currentNode]) {
            currentPath.add(neighbor); // Make choice
            backtrack(graph, neighbor, target, currentPath, result);
            currentPath.remove(currentPath.size() - 1); // Undo choice (backtrack)
        }
    }

    //Alternative Implementation (Using Path Tracking):
    //Here's a version that creates new path objects at each step to avoid explicit backtracking:
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(graph, 0, new ArrayList<>(Arrays.asList(0)), result);
        return result;
    }

    private void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> result) {
        if (node == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int neighbor : graph[node]) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(neighbor);
            dfs(graph, neighbor, newPath, result);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{1,2},{3},{3},{}};
        AllPathsFromSourceToTarget allPathsFromSourceToTarget= new AllPathsFromSourceToTarget();
        List<List<Integer>> lists = allPathsFromSourceToTarget.allPathsSourceTarget(graph);
        System.out.println(lists);// Output: [[0,1,3],[0,2,3]]

        int[][] graph1 = {{4,3,1},{3,2,4},{3},{4},{}};
        List<List<Integer>> lists1 = allPathsFromSourceToTarget.allPathsSourceTarget(graph1);
        System.out.println(lists1);//Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
    }
}
