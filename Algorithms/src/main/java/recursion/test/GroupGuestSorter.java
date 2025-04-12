package recursion.test;
/*
Group Guests
Imagine you're organizing a grand party with three different rooms for your guests. Each guest belongs to a specific group (1, 2, or 3). But things get messy! Some guests end up in the wrong rooms, and the rooms themselves are quite unorganized.

You are given an array where the ith element defines the room number of the ith guest. In a single operation you can change a number to any number to 1, 2 or 3. Return the minimum number of operations required so that all the guests are seated in a sorted manner.

Input:
An array nums of length n, where each element nums[i] represents the group number (1, 2, or 3) of guest i.
Output:
An integer representing the minimum number of updates needed to make the final seating arrangement sorted.
Example Input/Output:
Input:
[2, 1, 3, 2, 1]

Output:
3 (updatenums[0], nums[2], and nums[3] to group 1)

Input:
[1, 3, 2, 1, 3, 3]

Output:
2 (update nums[1] and nums[2] to group 1)

Input:
[2, 2, 2, 2, 3, 3]

Output:
0 (No updates needed; already sorted)

Constraints:
1 <= n <= 100
1 <= nums[i] <= 3
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GroupGuestSorter {


    public int minOperationsToSort(int[] nums) {

        int n = nums.length;
        // dp[i][g] = min operations to sort nums[0..i] ending with group g
        int[][] dp = new int[n][4];

        // Initialize with infinity
        for (int i = 0; i < n; i++) {
            for (int g = 0; g <= 3; g++) {
                dp[i][g] = Integer.MAX_VALUE;
            }
        }

        // Base case: first element can be changed to any group
        for (int g = 1; g <= 3; g++) {
            dp[0][g] = (nums[0] == g) ? 0 : 1;
        }

        for (int i = 1; i < n; i++) {
            for (int prev = 1; prev <= 3; prev++) {
                if (dp[i-1][prev] == Integer.MAX_VALUE) continue;

                for (int curr = prev; curr <= 3; curr++) {
                    int cost = (nums[i] == curr) ? 0 : 1;
                    if (dp[i][curr] > dp[i-1][prev] + cost) {
                        dp[i][curr] = dp[i-1][prev] + cost;
                    }
                }
            }
        }

        // Find the minimum among the last element's possibilities
        int min = dp[n-1][1];
        if (dp[n-1][2] < min) min = dp[n-1][2];
        if (dp[n-1][3] < min) min = dp[n-1][3];
        return min;
    }
        public static void main (String[]args){
            GroupGuestSorter sorter = new GroupGuestSorter();

            // Test cases
            int[] test1 = {2, 1, 3, 2, 1};
            System.out.println(sorter.minOperationsToSort(test1)); // Output: 3

            int[] test2 = {1, 3, 2, 1, 3, 3};
            System.out.println(sorter.minOperationsToSort(test2)); // Output: 2

            int[] test3 = {2, 2, 2, 2, 3, 3};
            System.out.println(sorter.minOperationsToSort(test3)); // Output: 0

            int[] test4 = {3, 2, 1, 2, 3, 1};
            System.out.println(sorter.minOperationsToSort(test4)); // Output: 4

            int[] test5 = {2, 3, 1, 3, 2, 3, 1, 2, 1, 3, 2, 1, 3, 2, 3, 2, 2, 3, 1, 2, 1, 2, 3, 2, 1, 1, 3, 2, 2, 2, 1, 3, 3, 1, 2, 2, 2, 3, 3, 3, 2, 1, 1, 2, 3, 3, 2, 2, 2, 2, 1, 3, 2, 2, 2, 2, 2, 2, 3, 1, 2, 2, 1, 1, 1, 1, 3, 2, 3, 1};
            System.out.println(sorter.minOperationsToSort(test5)); // Output: 38
        }

}