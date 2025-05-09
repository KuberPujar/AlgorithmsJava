package arrays.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.



Example 1:

Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation:
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1).
For nums[3]=2 there exist one smaller number than it (1).
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
Example 2:

Input: nums = [6,5,4,8]
Output: [2,1,0,3]
Example 3:

Input: nums = [7,7,7,7]
Output: [0,0,0,0]


Constraints:

2 <= nums.length <= 500
0 <= nums[i] <= 100
 */
public class NoOfNumbersSmallerThanCurrentNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter the elements of the array");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] result = smallerNumbersThanCurrentBruteForce(nums);
        System.out.println("The number of smaller numbers than current number are: ");
        for (int j : result) {
            System.out.print(j + " ");
        }
    }

    // Brute Force Approach
    public static int[] smallerNumbersThanCurrentBruteForce(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (j != i && nums[j] < nums[i]) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    // Optimized Approach Using Sorting and Hash Map
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < sortedNums.length; i++) {
            // Put the first occurrence of each number with its count of smaller numbers
            if (!countMap.containsKey(sortedNums[i])) {
                countMap.put(sortedNums[i], i);
            }
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = countMap.get(nums[i]);
        }
        return result;
    }
}
