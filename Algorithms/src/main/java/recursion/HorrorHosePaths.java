package recursion;
/*
Bunty in the horror house
Bunty is stuck in a horror house that consists of an (N * N) maze with Bunty starting at room ([0][0]). Find the total number of paths that Bunty can follow to reach the exit, i.e., room ([N-1][N-1]).
He can move in any direction (left, right, up, and down). The value of every room in the maze can either be 0 or 1. Rooms with value 0 are blocked and cannot be accessed, while rooms with value 1 are open.

Input Format:

The first line of input contains an integer N representing the dimension of the maze.
The next N lines of input contain N space-separated integers representing the type of the cell.
Output Format:

For each test case, print the total number of paths possible from start to exit.
Sample Input 1:

3
1 0 1
1 0 1
1 1 1
Sample Output 1:

1
Explanation: Only 1 path is possible which is:

1 0 0
1 0 0
1 1 1
(1 representing the path he took) which is printed from left to right and then top to bottom in one line.

Constraints:

(0 < N < 11)
(0 <= Maze[i][j] <= 1)
Note: The function should print the result.
 */
import java.util.Scanner;

public class HorrorHosePaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] maze=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                maze[i][j]=scanner.nextInt();
            }
        }
        scanner.close();
        boolean[][] visited=new boolean[n][n];
        System.out.println(countPaths(maze,0,0,visited));
    }

    private static int countPaths(int[][] maze, int i, int j, boolean[][] visited) {
        int N = maze.length;
        if (i < 0 || i >= N || j < 0 || j >= N || maze[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        if (i == N - 1 && j == N - 1) {
            return 1;
        }
        visited[i][j] = true;
        int count = 0;
        count += countPaths(maze, i + 1, j, visited); // Down
        count += countPaths(maze, i - 1, j, visited); // Up
        count += countPaths(maze, i, j + 1, visited); // Right
        count += countPaths(maze, i, j - 1, visited); // Left
        visited[i][j] = false;
        return count;
    }
}
