package heaps.sorting;

import java.util.Arrays;

/*
You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.



Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation:
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
Example 2:

Input: stones = [1]
Output: 1


Constraints:

1 <= stones.length <= 30
1 <= stones[i] <= 1000
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        while (n > 1) {
            Arrays.sort(stones, 0, n); // Sort only the active part
            int y = stones[n - 1];
            int x = stones[n - 2];
            if (x == y) {
                n -= 2; // Both destroyed
            } else {
                stones[n - 2] = y - x; // Replace with the difference
                n -= 1; // One stone destroyed
            }
        }
        return n == 1 ? stones[0] : 0;
    }

    public static void main(String[] args) {
        LastStoneWeight solution = new LastStoneWeight();
        int[] stones1 = {2, 7, 4, 1, 8, 1};
        System.out.println(solution.lastStoneWeight(stones1)); // Output: 1

        int[] stones2 = {1};
        System.out.println(solution.lastStoneWeight(stones2)); // Output: 1

        int[] stones3 = {3, 5, 7, 9};
        System.out.println(solution.lastStoneWeight(stones3)); // Output: 0
    }
}
