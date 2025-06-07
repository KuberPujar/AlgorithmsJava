package graphalgorithms.greedy;
/*
Activity Selection
Problem Description: Max Tasks Done

Raman is going through a very rough phase in his life, and he is determined to earn as much money as possible. He comes across a job where he is allowed to solve as many problems as he can. The problems are given in the form of two arrays of size N, containing the starting and ending day of each problem. Help Raman maximize the number of problems he can solve.

Input:

The first line contains an integer, N (1 ≤ N ≤ 2*10^5), representing the number of problems.
The next two lines contain two arrays of size N each:
The first array contains the starting day of each problem, where 1 ≤ start[i] ≤ 10^9.
The second array contains the ending day of each problem, where 1 ≤ end[i] ≤ 10^9.
Output:

Output a single integer representing the maximum number of problems Raman can solve.
Example:

Input:
5
1 2 3 4 5
3 4 5 6 7

Output:
3

Explanation:
Raman can solve the problems starting on days 1, 3, and 5, and finish on days 3, 5, and 7, respectively. Therefore, the maximum number of problems he can solve is 3.
Input:
4
1 2 3 6
3 5 9 8

Output:
2

Explanation:
This input consists of 4 problems with starting days [1, 2, 3, 6] and ending days [3, 5, 9, 8].

To maximize the number of problems Raman can solve, we consider each problem as a tuple of (start, end):

(1, 3), (2, 5), (3, 9), (6, 8).
After sorting by ending days and selecting non-overlapping problems:

Raman can solve Problem 1 (1, 3) and Problem 4 (6, 8).
Thus, the maximum number of problems he can solve is 2.
Constraints:

1 ≤ N ≤ 2*10^5
1 ≤ start[i] ≤ end[i] ≤ 10^9
Note:The function should return the result. The driver code will handle printing the output.
 */

import java.util.Arrays;
import java.util.Scanner;

class Problem {
    int start;
    int end;

    Problem(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class ActivitySelection {
    public static int maxProblems(int n, int[] start, int[] end) {
        // Create array of problems
        Problem[] problems = new Problem[n];
        for (int i = 0; i < n; i++) {
            problems[i] = new Problem(start[i], end[i]);
        }

        // Sort problems by ending day (greedy choice)
        Arrays.sort(problems, (a, b) -> Integer.compare(a.end, b.end));

        int count = 0;
        int lastEndTime = 0;

        // Select problems greedily
        for (int i = 0; i < n; i++) {
            // If current problem starts after the last selected problem ends
            if (problems[i].start >= lastEndTime) {
                count++;
                lastEndTime = problems[i].end;
            }
        }

        return count;
    }

    // Driver code for testing
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Test case 1
        System.out.println("Test Case 1:");
        int n1 = 5;
        int[] start1 = {1, 2, 3, 4, 5};
        int[] end1 = {3, 4, 5, 6, 7};
        System.out.println("Input: n=" + n1);
        System.out.println("Start: " + Arrays.toString(start1));
        System.out.println("End: " + Arrays.toString(end1));
        System.out.println("Output: " + maxProblems(n1, start1, end1));
        System.out.println();

        // Test case 2
        System.out.println("Test Case 2:");
        int n2 = 4;
        int[] start2 = {1, 2, 3, 6};
        int[] end2 = {3, 5, 9, 8};
        System.out.println("Input: n=" + n2);
        System.out.println("Start: " + Arrays.toString(start2));
        System.out.println("End: " + Arrays.toString(end2));
        System.out.println("Output: " + maxProblems(n2, start2, end2));

        // Interactive input
        System.out.println("\n--- Interactive Mode ---");
        System.out.print("Enter number of problems: ");
        int n = sc.nextInt();

        int[] start = new int[n];
        int[] end = new int[n];

        System.out.println("Enter starting days:");
        for (int i = 0; i < n; i++) {
            start[i] = sc.nextInt();
        }

        System.out.println("Enter ending days:");
        for (int i = 0; i < n; i++) {
            end[i] = sc.nextInt();
        }

        System.out.println("Maximum problems Raman can solve: " + maxProblems(n, start, end));

        sc.close();
    }
}
