package bitmanipulation.backtracking;
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
public class BeatifulArrangement {

    public int countArrangement(int n) {
        return backtrack(n, 1, 0);
    }

    // pos: current position (1-indexed), used: bitmask of used numbers
    private int backtrack(int n, int pos, int used) {
        if (pos > n) return 1;
        int count = 0;
        for (int num = 1; num <= n; num++) {
            int mask = 1 << (num - 1);
            if ((used & mask) == 0 && (num % pos == 0 || pos % num == 0)) {
                count += backtrack(n, pos + 1, used | mask);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        BeatifulArrangement solution = new BeatifulArrangement();
        int n = 2;
        int result = solution.countArrangement(n);
        System.out.println("Number of beautiful arrangements for n = " + n + ": " + result); // Output: 2
    }
}
