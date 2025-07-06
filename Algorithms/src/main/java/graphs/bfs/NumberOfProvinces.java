package graphs.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void bfs(int[][] isConnected, boolean[] visited, int startCity) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startCity);
        visited[startCity] = true;

        while (!queue.isEmpty()) {
            int currentCity = queue.poll();

            for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
                if (isConnected[currentCity][neighbor] == 1 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    // Alternative BFS implementation using adjacency list
        public int findCircleNum1(int[][] isConnected) {
            int n = isConnected.length;
            List<List<Integer>> adj = new ArrayList<>();

            // Convert matrix to adjacency list
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
                for (int j = 0; j < n; j++) {
                    if (isConnected[i][j] == 1 && i != j) {
                        adj.get(i).add(j);
                    }
                }
            }

            boolean[] visited = new boolean[n];
            int provinces = 0;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    bfs(adj, visited, i);
                    provinces++;
                }
            }

            return provinces;
        }

        private void bfs(List<List<Integer>> adj, boolean[] visited, int startCity) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(startCity);
            visited[startCity] = true;

            while (!queue.isEmpty()) {
                int currentCity = queue.poll();

                for (int neighbor : adj.get(currentCity)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
        }

    public static void main(String[] args) {
        NumberOfProvinces solution = new NumberOfProvinces();
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.findCircleNum(isConnected1)); // Output: 2

        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(solution.findCircleNum(isConnected2)); // Output: 3
    }
}
