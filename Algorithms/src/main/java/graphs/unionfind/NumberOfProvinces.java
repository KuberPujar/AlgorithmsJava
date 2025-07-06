package graphs.unionfind;
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
        UnionFind uf = new UnionFind(n);

        // Iterate through the adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.getCount();
    }

    // Union-Find (Disjoint Set Union) implementation
    class UnionFind {
        private int[] parent;
        private int[] rank;
        private int count;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            count = size;

            // Initialize each element as its own parent
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot == yRoot) return; // Already connected

            // Union by rank
            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
            count--; // Reduce count when merging sets
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        NumberOfProvinces numberOfProvinces=new NumberOfProvinces();
        //Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
        int[][] isConnected={{1,1,0},{1,1,0},{0,0,1}};
        int output=numberOfProvinces.findCircleNum(isConnected);
        System.out.println(output);//Output: 2
    }
}
