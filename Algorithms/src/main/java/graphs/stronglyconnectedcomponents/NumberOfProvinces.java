package graphs.stronglyconnectedcomponents;
/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.



Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3


Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[n];

        // Initialize each city as its own parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int provinces = n; // Start with each city as its own province

        // Iterate through the adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int rootI = find(parent, i);
                    int rootJ = find(parent, j);

                    // If roots are different, union them and reduce province count
                    if (rootI != rootJ) {
                        parent[rootJ] = rootI;
                        provinces--;
                    }
                }
            }
        }

        return provinces;
    }

    // Path compression implementation of find
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]); // Path compression
        }
        return parent[x];
    }

    // Depth-first search (DFS) implementation to find provinces
    public int findCircleNum1(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfProvinces solution = new NumberOfProvinces();

        // Example 1
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.findCircleNum(isConnected1)); // Output: 2

        // Example 2
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(solution.findCircleNum(isConnected2)); // Output: 3
    }
}
