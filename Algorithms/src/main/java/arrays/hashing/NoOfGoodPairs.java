package arrays.hashing;
/*
write a java code for following scenario Number of Good Pairs Given an array of integers nums, return the number of good pairs.

A pair (i, j) is called good if nums[i] == nums[j] and i < j.



Example 1:

Input: nums = [1,2,3,1,1,3]
Output: 4
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
Example 2:

Input: nums = [1,1,1,1]
Output: 6
Explanation: Each pair in the array are good.
Example 3:

Input: nums = [1,2,3]
Output: 0


Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NoOfGoodPairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.println("Number of good pairs (brute force): " + numIdenticalPairsBruteForce(nums));
        System.out.println("Number of good pairs (optimized): " + numIdenticalPairs(nums));
        scanner.close();
    }

    // Brute Force Approach
    public static int numIdenticalPairsBruteForce(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimized Approach Using Hash Map
    public static int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        for (int freq : frequencyMap.values()) {
            count += freq * (freq - 1) / 2;
        }

        return count;
    }
}

