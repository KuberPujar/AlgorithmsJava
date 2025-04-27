package dynamicprmng;
/*
Egg dropping
You are given N identical eggs and a building with K floors numbered from 1 to K. There exists a critical floor f (where 0 <= f <= K) such that:

Any egg dropped from a floor higher than f will break.
Any egg dropped from or below floor f will not break.
The goal is to determine the value of f using the minimum number of moves while adhering to the following rules:

An egg that survives a fall can be used again.
A broken egg must be discarded.
The effect of a fall is the same for all eggs.
If an egg doesn't break at a certain floor, it will not break at any floor below it.
If an egg breaks at a certain floor, it will break at any floor above it.
Your task is to find the minimum number of attempts required to determine the critical floor f with certainty.

Input Format

The first line contains an integer T, the number of test cases.
For each test case, there are two integers:
N: the number of eggs.
K: the number of floors.
Output Format

For each test case, output an integer denoting the minimum number of attempts required to find the critical floor f.
Sample Input 1

1
1 2
Sample Output 1

2
Explanation

Drop the egg from floor 1:
If it breaks, f = 0.
If it does not break, move to the next step.
Drop the egg from floor 2:
If it breaks, f = 1.
If it does not break, f = 2.
Hence, the minimum number of moves required is 2.

Sample Input 2

1
2 10
Sample Output 2

4
Explanation
Using 2 eggs and dynamic programming:

Start with the middle floor (floor 5). Drop an egg:
If it breaks, test floors 1-4 with the remaining egg.
If it does not break, move to floors 6-10 with the remaining moves.
Repeat the process to minimize the worst-case scenario.
The minimum number of moves required is 4.

Constraints

(1 <= T <= 100)
(1 <= N <= 100)
(1 <= K <= 10^3)
 */
public class EggDropping {
    public static void main(String[] args) {
        int T = 1; // Number of test cases
        int[][] testCases = {
                {1, 2},
                {2, 10}
        };

        for (int i = 0; i < T; i++) {
            int N = testCases[i][0]; // Number of eggs
            int K = testCases[i][1]; // Number of floors
            System.out.println(minAttempts(N, K));
        }
    }

    public static int minAttempts(int N, int K) {
        int[][] dp = new int[N + 1][K + 1];

        // Base cases
        for (int i = 1; i <= N; i++) {
            dp[i][1] = 1;
        }
        for (int j = 1; j <= K; j++) {
            dp[1][j] = j;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int x = 1; x <= j; x++) {
                    int res = 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]);
                    if (res < dp[i][j]) {
                        dp[i][j] = res;
                    }
                }
            }
        }

        return dp[N][K];
    }
}
