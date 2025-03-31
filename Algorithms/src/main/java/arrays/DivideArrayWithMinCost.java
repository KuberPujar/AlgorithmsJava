package arrays;

import java.util.*;

public class DivideArrayWithMinCost {
    public static int minCostSum(int[] nums) {
        int n = nums.length;
        int minSum = Integer.MAX_VALUE;

        // We need to choose two split points i and j where 0 < i < j < n
        // This divides the array into [0..i-1], [i..j-1], [j..n-1]
        for (int i = 1; i < n-1 ; i++) {
            for (int j = i + 1; j < n; j++) {
                int cost1 = nums[0];          // First subarray always starts at 0
                int cost2 = nums[i];          // Second subarray starts at i
                int cost3 = nums[j];         // Third subarray starts at j
                int currentSum = cost1 + cost2 + cost3;

                if (currentSum < minSum) {
                    minSum = currentSum;
                }
            }
        }

        return minSum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(minCostSum(nums));
    }
}
