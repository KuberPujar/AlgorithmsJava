package arrays.hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Given an integer array nums and an integer k, return the number of pairs (i, j) where i < j such that |nums[i] - nums[j]| == k.

The value of |x| is defined as:

x if x >= 0.
-x if x < 0.


Example 1:

Input: nums = [1,2,2,1], k = 1
Output: 4
Explanation: The pairs with an absolute difference of 1 are:
- [1,2,2,1]
- [1,2,2,1]
- [1,2,2,1]
- [1,2,2,1]
Example 2:

Input: nums = [1,3], k = 3
Output: 0
Explanation: There are no pairs with an absolute difference of 3.
Example 3:

Input: nums = [3,2,1,5,4], k = 2
Output: 3
Explanation: The pairs with an absolute difference of 2 are:
- [3,2,1,5,4]
- [3,2,1,5,4]
- [3,2,1,5,4]


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
1 <= k <= 99
 */
public class CountNumbersOfPairsWithAbsoluteDifferenceK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter the elements of the array");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println("Enter the value of k");
        int k = sc.nextInt();
        int result = countKDifferenceBruteForce(nums, k);
        System.out.println("The number of pairs with absolute difference of " + k + " are: " + result);
        result = countKDifference(nums, k);
        System.out.println("The number of pairs with absolute difference of " + k + " are: " + result);
    }

    // Brute Force Approach
    public static int countKDifferenceBruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimized Approach Using Hash Map
    public static int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            // Check for num + k
            count += frequencyMap.getOrDefault(num + k, 0);
            // Check for num - k
            count += frequencyMap.getOrDefault(num - k, 0);
            // Update the frequency map
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        return count;
    }
}
