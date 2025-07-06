package graphs.unionfind;
/*
There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.



Example 1:


Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:


Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.


Constraints:

1 <= n <= 105
1 <= connections.length <= min(n * (n - 1) / 2, 105)
connections[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated connections.
No two computers are connected by more than one cable.
 */
public class NumberOfOperationsToMakeNetworkConnected {
    private int[] parent;
    private int[] rank;

    public int makeConnected(int n, int[][] connections) {
        // If we don't have enough cables to connect all computers
        if (connections.length < n - 1) {
            return -1;
        }

        parent = new int[n];
        rank = new int[n];

        // Initialize Union-Find
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int redundantConnections = 0;

        // Process each connection
        for (int[] connection : connections) {
            int a = connection[0];
            int b = connection[1];

            if (find(a) != find(b)) {
                union(a, b);
            } else {
                redundantConnections++;
            }
        }

        // Count the number of connected components
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                components++;
            }
        }

        // The minimum operations needed is (components - 1)
        // We can do this if we have enough redundant connections
        int needed = components - 1;
        return redundantConnections >= needed ? needed : -1;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        // Union by rank
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public static void main(String[] args) {
                NumberOfOperationsToMakeNetworkConnected solution = new NumberOfOperationsToMakeNetworkConnected();

                // Test Case 1
                int n1 = 4;
                int[][] connections1 = {{0,1},{0,2},{1,2}};
                int expected1 = 1;
                int result1 = solution.makeConnected(n1, connections1);
                System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL") +
                        " (Expected: " + expected1 + ", Actual: " + result1 + ")");

                // Test Case 2
                int n2 = 6;
                int[][] connections2 = {{0,1},{0,2},{0,3},{1,2},{1,3}};
                int expected2 = 2;
                int result2 = solution.makeConnected(n2, connections2);
                System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL") +
                        " (Expected: " + expected2 + ", Actual: " + result2 + ")");

                // Test Case 3
                int n3 = 6;
                int[][] connections3 = {{0,1},{0,2},{0,3},{1,2}};
                int expected3 = -1;
                int result3 = solution.makeConnected(n3, connections3);
                System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL") +
                        " (Expected: " + expected3 + ", Actual: " + result3 + ")");

                // Additional Test Case 4
                int n4 = 5;
                int[][] connections4 = {{0,1},{0,2},{3,4}};
                int expected4 = 1;
                int result4 = solution.makeConnected(n4, connections4);
                System.out.println("Test Case 4: " + (result4 == expected4 ? "PASS" : "FAIL") +
                        " (Expected: " + expected4 + ", Actual: " + result4 + ")");

                // Additional Test Case 5
                int n5 = 3;
                int[][] connections5 = {{1,2}};
                int expected5 = 1;
                int result5 = solution.makeConnected(n5, connections5);
                System.out.println("Test Case 5: " + (result5 == expected5 ? "PASS" : "FAIL") +
                        " (Expected: " + expected5 + ", Actual: " + result5 + ")");
    }
}
