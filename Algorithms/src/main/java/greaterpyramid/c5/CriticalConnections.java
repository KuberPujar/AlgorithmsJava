package greaterpyramid.c5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a network of n servers connected by undirected server-to-server connections, identify all the critical connections in the network. A critical connection is defined as a connection that, if removed, would result in some servers being unable to reach other servers.

Definitions:

Servers are numbered from 0ton - 1.
Connections are represented by pairs of integers [ai, bi], indicating a bidirectional connection between servers aiandbi.
A server can reach another server if there is a path of connections from one to the other.
Input Format:

The first line contains two space-separated integers nandm, where nis the number of servers andm is the number of connections.
The next m lines each contain two space-separated integers representing a connection between two servers.
Output Format:

Print the critical connections in any order. Each critical connection should be printed as two space-separated integers per line.
If there are no critical connections, return an empty array.
Sample Input:

7 8
6 1
4 2
2 5
1 5
0 1
1 2
2 0
1 3
Sample Output:

1 6
2 4
1 3
Constraints:

2 <= n <= 10^5
n - 1 <= connections.length <= 10^5
0 <= ai, bi <= n - 1
ai != bi
Connections are unique and undirected.
Explanation: In this example, the network consists of 7 servers and 8 connections. The critical connections are between servers 1-6, 2-4, and 1-3. Removing any of these connections would result in at least one server being isolated from the rest of the network.
 */
public class CriticalConnections {
    private static int time = 0;
    public static void main(String[] args) {
        int n = 7;
        //convert the connections to List<List<Integer>>
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(6, 1));
        connections.add(Arrays.asList(4, 2));
        connections.add(Arrays.asList(2, 5));
        connections.add(Arrays.asList(1, 5));
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));
        //int[][] connections = {{6, 1}, {4, 2}, {2, 5}, {1, 5}, {0, 1}, {1, 2}, {2, 0}, {1, 3}};

        List<List<Integer>> result = criticalConnections(n, connections);
        for (List<Integer> connection : result) {
            System.out.println(connection.get(0) + " " + connection.get(1));
        }

    }

        public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            List<List<Integer>> result = new ArrayList<>();
            ArrayList[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (List<Integer> connection : connections) {
                int a = connection.get(0);
                int b = connection.get(1);
                graph[a].add(b);
                graph[b].add(a);
            }

            int[] disc = new int[n];
            int[] low = new int[n];
            Arrays.fill(disc, -1);

            for (int i = 0; i < n; i++) {
                if (disc[i] == -1) {
                    dfs(i, -1, disc, low, graph, result);
                }
            }
            return result;
        }

        private static void dfs(int u, int parent, int[] disc, int[] low, List<Integer>[] graph, List<List<Integer>> result) {
            disc[u] = low[u] = ++time;
            for (int v : graph[u]) {
                if (v == parent) {
                    continue;
                }
                if (disc[v] == -1) {
                    dfs(v, u, disc, low, graph, result);
                    low[u] = Math.min(low[u], low[v]);
                    if (low[v] > disc[u]) {
                        result.add(Arrays.asList(u, v));
                    }
                } else {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }

}
