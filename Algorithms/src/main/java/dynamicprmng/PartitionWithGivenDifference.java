package dynamicprmng;
/*
Partition with given difference
Given an integer array nums, representing the number of chocolates in the ith bag where 1 <= i <= N and N is the length of Array nums, Alice and Bob are best friends. They want to share chocolates in such a way that the absolute difference between their set of chocolates is d, also make sure that Alice always gets the bigger or equal part. Count how many possible ways there are for such partitions. Since the answer can be large, print it with modulo 10^9+7.

Note: that they can pick the chocolates in any order and same numbers are not considered as identical.

Constraints:
1 ≤ N ≤ 50
0 ≤ D ≤ 2500
1 ≤ ARR[i] ≤ 50
Example:
Input:

N = 4
d = 3
arr = [5, 2, 6, 4]
Output:

1
Explanation: The way they can distribute is for Bob we can pick chocolates at index 0,1 and for Alice chocolates at index 2,3 which is the only way we can get a total minimum difference between them to be 3 (abs(6 + 4 - 5 - 2) = 3).

Input:

N = 4
d = 0
arr = [1, 1, 1, 1]
Output:

6
Explanaton: There are multiple ways these chocolates can be distributed. Below are all the ways the chocolates will be distributed to Alice and the remaining would go to Bob.

Alice can take chocolates at index 0,1 (1st way)
Alice can take chocolates at index 0,2 (2nd way)
at index 0,3 (3rd way)
at index 1,2 (4th way)
at index 1,3 (5th way)
at index 2,3 (6th way)
Note: that it is asking us to seperate them in sets.

Note: The function should return the result.
 */
public class PartitionWithGivenDifference {
    private static final int MOD = 1000000007;
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 4};
        int d = 3;
        System.out.println(countPartitions(d, arr)); // Output: 1

        int[] arr2 = {1, 1, 1, 1};
        int d2 = 0;
        System.out.println(countPartitions(d2, arr2)); // Output: 6
    }

    public static int countPartitions(int d,int[] arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }
        // Check if (totalSum + d) is even and non-negative
        if ((totalSum + d) % 2 != 0 || totalSum < d) {
            return 0;
        }
        int S1 = (totalSum + d) / 2;
        // To avoid negative S1 which is not possible
        if (S1 < 0) {
            return 0;
        }
        int[] dp = new int[S1 + 1];
        dp[0] = 1;
        for (int num : arr) {
            for (int i = S1; i >= num; i--) {
                dp[i] = (dp[i] + dp[i - num]) % MOD;
            }
        }
        return dp[S1];
    }
}
