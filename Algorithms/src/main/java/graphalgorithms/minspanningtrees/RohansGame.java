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


/**
 * The Line class represents a connection (an edge) between two balls (nodes).
 * It stores the two connected balls (p1, p2) and the length of the line.
 * It implements the Comparable interface to allow sorting a list of lines based on their length.
 */
class Line {
    int length;
    int p1; // Represents the index of the first ball
    int p2; // Represents the index of the second ball

    public Line(int p1, int p2, int length) {
        this.p1 = p1;
        this.p2 = p2;
        this.length = length;
    }
}

/**
 * The DSU (Disjoint Set Union) or Union-Find data structure.
 * It's used to efficiently track which balls are connected in the same component
 * and to detect if adding a new line would form a cycle.
 */
class DSU {
    int[] parent;

    /**
     * Initializes the DSU structure.
     * @param n The number of elements (balls) to manage.
     * Each ball is initially in its own separate set.
     */
    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each node is its own parent initially
        }
    }

    /**
     * Finds the representative (or root) of the set that element 'i' belongs to.
     * It uses path compression for optimization, making subsequent finds faster.
     * @param i The element to find.
     * @return The representative of the set.
     */
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Path compression: set the parent directly to the root
        return parent[i] = find(parent[i]);
    }

    /**
     * Merges the sets containing elements 'i' and 'j'.
     * @param i An element in the first set.
     * @param j An element in the second set.
     */
    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }
}

/**
 * Main class to run the program for Rohan's Game.
 */
public class RohansGame {

    /**
     * Solves the problem by finding the Minimum Spanning Tree (MST) using Kruskal's algorithm.
     * @param n The total number of balls (nodes).
     * @param lines A list of all possible lines (edges) between the balls.
     */
    public static void findMinimumLines(int n, Line[] lines) {
        // Step 1 of Kruskal's: Sort all lines by length in non-decreasing order.
        Arrays.sort(lines, Comparator.comparingInt(line -> line.length));

        // Find the maximum node ID to size the DSU array robustly.
        int maxNodeId = 0;
        for (Line line : lines) {
            maxNodeId = Math.max(maxNodeId, Math.max(line.p1, line.p2));
        }

        DSU dsu = new DSU(maxNodeId + 1);
        List<Line> mst = new ArrayList<>(); // This will store the final lines in the MST.
        int edgesCount = 0;

        // Step 2: Iterate through sorted lines and add to MST if no cycle is formed.
        for (Line line : lines) {
            // Check if the two balls of the current line are already connected.
            // If their roots in the DSU are different, they are in different components.
            if (dsu.find(line.p1) != dsu.find(line.p2)) {
                // If not connected, add this line to our MST.
                mst.add(line);

                // Merge the two components.
                dsu.union(line.p1, line.p2);

                edgesCount++;
                // An MST for a graph with 'n' nodes will have 'n-1' edges.
                // We can stop once we have found enough edges.
                if (edgesCount == n - 1) {
                    break;
                }
            }
        }

        // Step 3: Print the resulting lines that form the minimal structure.
        for (Line line : mst) {
            System.out.println(line.p1 + " " + line.p2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of balls 'n' and total number of lines 'l'.
        int n = scanner.nextInt();
        int l = scanner.nextInt();

        Line[] allLines = new Line[l];

        // Read all 'l' lines from the input.
        for (int i = 0; i < l; i++) {
            int p1 = scanner.nextInt();
            int p2 = scanner.nextInt();
            int length = scanner.nextInt();
            allLines[i]=new Line(p1, p2, length);
        }

        // Call the function to find and print the solution.
        findMinimumLines(n, allLines);

        scanner.close();
    }
}
