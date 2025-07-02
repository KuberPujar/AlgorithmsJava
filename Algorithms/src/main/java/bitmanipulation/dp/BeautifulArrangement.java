package bitmanipulation.dp;
/*
Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:

perm[i] is divisible by i.
i is divisible by perm[i].
Given an integer n, return the number of the beautiful arrangements that you can construct.



Example 1:

Input: n = 2
Output: 2
Explanation:
The first beautiful arrangement is [1,2]:
    - perm[1] = 1 is divisible by i = 1
    - perm[2] = 2 is divisible by i = 2
The second beautiful arrangement is [2,1]:
    - perm[1] = 2 is divisible by i = 1
    - i = 2 is divisible by perm[2] = 1
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 15
 */
public class BeautifulArrangement {

    public int countArrangement(int n) {
        int size = 1 << n;
        int[] dp = new int[size];
        dp[0] = 1; // base case: no numbers used

        for (int mask = 0; mask < size; mask++) {
            int pos = Integer.bitCount(mask) + 1;
            for (int num = 1; num <= n; num++) {
                int bit = 1 << (num - 1);
                if ((mask & bit) == 0 && (num % pos == 0 || pos % num == 0)) {
                    dp[mask | bit] += dp[mask];
                }
            }
        }
        return dp[size - 1];
    }

    public static void main(String[] args) {
        BeautifulArrangement solution = new BeautifulArrangement();
        int n = 2;
        int result = solution.countArrangement(n);
        System.out.println("Number of beautiful arrangements for n = " + n + ": " + result); // Output: 2
    }
}
