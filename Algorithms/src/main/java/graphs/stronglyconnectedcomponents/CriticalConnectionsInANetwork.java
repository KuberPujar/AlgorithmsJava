package graphs.stronglyconnectedcomponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.



Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]


Constraints:

2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.
 */
public class CriticalConnectionsInANetwork {

    private List<List<Integer>> adj;
    private int[] ids;
    private int[] low;
    private int id;
    private List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Initialize adjacency list
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the graph
        for (List<Integer> connection : connections) {
            int a = connection.get(0);
            int b = connection.get(1);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        // Initialize Tarjan's algorithm variables
        ids = new int[n];
        low = new int[n];
        id = 0;
        result = new ArrayList<>();
        Arrays.fill(ids, -1); // -1 means unvisited

        // Perform DFS to find bridges
        for (int i = 0; i < n; i++) {
            if (ids[i] == -1) {
                dfs(i, -1);
            }
        }

        return result;
    }

    private void dfs(int at, int parent) {
        ids[at] = low[at] = id++;

        for (int to : adj.get(at)) {
            if (to == parent) continue; // Skip parent to avoid immediate back edge

            if (ids[to] == -1) { // Unvisited node
                dfs(to, at);
                low[at] = Math.min(low[at], low[to]);

                // If there's no back edge from 'to' to 'at' or its ancestors
                if (ids[at] < low[to]) {
                    result.add(Arrays.asList(at, to));
                }
            } else {
                // Visited node - update low link value
                low[at] = Math.min(low[at], ids[to]);
            }
        }
    }

    public static void main(String[] args) {
        CriticalConnectionsInANetwork solution = new CriticalConnectionsInANetwork();
        List<List<Integer>> connections = Arrays.asList(
            Arrays.asList(0, 1),
            Arrays.asList(1, 2),
            Arrays.asList(2, 0),
            Arrays.asList(1, 3)
        );
        List<List<Integer>> result = solution.criticalConnections(4, connections);
        System.out.println(result); // Output: [[1, 3]]
    }

}
