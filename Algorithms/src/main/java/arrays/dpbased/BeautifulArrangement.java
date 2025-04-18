package arrays.dpbased;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    public static void main(String[] args) {
        int n = 2;
        System.out.println(countArrangement(n)); // Output: 2
        n = 1;
        System.out.println(countArrangement(n)); // Output: 1
    }

    private static int countArrangement(int n) {
        return backtrack(n, 1, new boolean[n + 1], new HashMap<>());
    }

    private static int backtrack(int n, int pos, boolean[] visited, Map<String, Integer> memo) {
        if (pos > n) return 1;

        String key = Arrays.toString(visited) + pos;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int count = 0;
        for (int num = 1; num <= n; num++) {
            if (!visited[num] && (num % pos == 0 || pos % num == 0)) {
                visited[num] = true;
                count += backtrack(n, pos + 1, visited, memo);
                visited[num] = false;
            }
        }

        memo.put(key, count);
        return count;
    }
}
