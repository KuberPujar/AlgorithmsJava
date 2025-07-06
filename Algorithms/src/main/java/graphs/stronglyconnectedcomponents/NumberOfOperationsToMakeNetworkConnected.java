package graphs.stronglyconnectedcomponents;
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
    // This method calculates the minimum number of operations required to connect all computers in a network.
    public int makeConnected(int n, int[][] connections) {
        // If not enough cables to connect all computers
        if (connections.length < n - 1) {
            return -1;
        }

        int[] parent = new int[n];
        // Initialize parent array
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int extraCables = 0;
        int components = n;

        for (int[] connection : connections) {
            int a = connection[0];
            int b = connection[1];

            int rootA = find(parent, a);
            int rootB = find(parent, b);

            if (rootA != rootB) {
                parent[rootB] = rootA;
                components--;
            } else {
                extraCables++;
            }
        }

        // Required moves = number of components - 1
        int requiredMoves = components - 1;

        // Check if we have enough extra cables
        return (extraCables >= requiredMoves) ? requiredMoves : -1;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]]; // Path compression
            x = parent[x];
        }
        return x;
    }

    public static void main(String[] args) {
        NumberOfOperationsToMakeNetworkConnected solution = new NumberOfOperationsToMakeNetworkConnected();

        // Example 1
        int n1 = 4;
        int[][] connections1 = {{0, 1}, {0, 2}, {1, 2}};
        System.out.println(solution.makeConnected(n1, connections1)); // Output: 1

        // Example 2
        int n2 = 6;
        int[][] connections2 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        System.out.println(solution.makeConnected(n2, connections2)); // Output: 2

        // Example 3
        int n3 = 6;
        int[][] connections3 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}};
        System.out.println(solution.makeConnected(n3, connections3)); // Output: -1
    }
}
