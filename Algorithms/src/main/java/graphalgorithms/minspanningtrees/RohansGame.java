package graphalgorithms.minspanningtrees;

import java.util.*;

/*
Rohan's game
Rohan is 5 years old and is strong enough to carry a basket containing balls, unfortunately one day he loses his balance and all the balls get spilled on the floor. He thinks of turning this into a game, he numbers the balls from starting from 0 to n. He then draws a line between every ball and starts hopping on that line jumping from one ball to another, he observes that he can eliminate some lines from the ones that are drawn and still be able to reach every ball. Help him eliminate the lines in such a way that he is able to reach every ball and the total of all the distance between the balls is minimum. Return all the lines that will exist in the final structure, the format for printing is as follows :-

(index of ball 1) (index of ball 2)

class Line{ public: int length; int p1; int p2; };

Input Format:
First line contains two integers 'n' and 'l' denoting the number of balls and total number of lines between them.

The next 'l' lines contains the three information the two balls in between which the line exists and the length of that line.

Output Format:
Output all the lines that will exist in the final structure, by printing the index of the balls in between which they will exist.
Sample Input 1:
3 3

1 2 3

2 3 2

1 3 5
Sample Output:
1 2

2 3
Explanation
             1
            / \
           2---3

distance from 1-2 is 3

distance from 2-3 is 2

distance from 1-3 is 5

The final structure will look like below.

             1
            /
           2----3


The total distance is 2 + 3 = 5 because we have eliminated the line between 1-3 and at the same time every ball is part of the structure.
Sample Input 2
4 6
0 1 2
0 2 6
0 3 4
2 3 5
1 3 3
1 2 3
Sample Output 2
0 1
1 3
1 2
Explanation
Represent the Graph:
Each ball is a node in the graph.
Each line between two balls is an edge with a weight (the length of the line).
Find the MST:
Use Kruskal's algorithm to find the MST of the graph. Kruskal's algorithm works as follows:
Sort all edges by their weight in ascending order.
Initialize a Union-Find structure to manage the merging of sets.
Iterate through the sorted edges and add each edge to the MST if it does not form a cycle (using the Union-Find structure to check this).
Output the edges that are included in the MST.
Constraints:
1 <= n <= 1000

n-1 <= l <= (n*(n-1))/2

0 <= length of line <= 1000

Note:The function should print the result.
 */



public class RohansGame {
    static class Edge {
        int u, v, weight, index;
        public Edge(int u, int v, int weight, int index) {
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.index = index;
        }
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    public static List<Edge> findMST(int n, List<Edge> edges) {
        Collections.sort(edges, (a, b) -> {
            if (a.weight != b.weight) {
                return Integer.compare(a.weight, b.weight);
            } else {
                return Integer.compare(a.index, b.index);
            }
        });

        UnionFind uf = new UnionFind(n);
        List<Edge> mstEdges = new ArrayList<>(n - 1);

        for (Edge edge : edges) {
            if (mstEdges.size() == n - 1) break;
            if (uf.union(edge.u, edge.v)) {
                mstEdges.add(edge);
            }
        }

        Collections.sort(mstEdges, Comparator.comparingInt(a -> a.index));
        return mstEdges;
    }

    public static void main(String[] args) {
        // Static stub for testing
        int n = 4;
        int l = 6;
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 2, 0),
                new Edge(0, 2, 6, 1),
                new Edge(0, 3, 4, 2),
                new Edge(2, 3, 5, 3),
                new Edge(1, 3, 3, 4),
                new Edge(1, 2, 3, 5)
        );

        List<Edge> mstEdges = findMST(n, edges);

        for (Edge edge : mstEdges) {
            System.out.println(edge.u + " " + edge.v);
        }
    }
}
