package greaterpyramid.c1;
/*
Board Filling
You have given a rectangular board of M × N squares. You also have unlimited number of standard block pieces of  2 x1 squares. You are allowed to rotate the pieces. You are asked to place as many blocks as possible on the board so as to meet the following conditions:

Each block completely covers two squares.

No two blocks overlap.

Each block lies entirely inside the board. It is allowed to touch the edges of the board.

Find the maximum number of blocks, which can be placed under these restrictions.

Input
In a single line you are given two integers M and N — board sizes in squares.
Output
Output one number — the maximal number of block of wood, which can be placed.
Sample Input
2 4
Sample Output
4
Explanation
We are given the board of size 2x4 which is given as:
----
----

We can place two blocks which are placed horizontally, in each row making a total of 4 in this way:
==
==

Constraints
1 ≤ M ≤ N ≤ 16
 */

import java.util.Scanner;

public class BoardFilling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();

        // Each block covers 2 squares, so maximum blocks is floor((M*N)/2)
        int maxBlocks = (M * N) / 2;

        System.out.println(maxBlocks);
    }
}
