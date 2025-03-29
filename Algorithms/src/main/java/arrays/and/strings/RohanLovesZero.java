package arrays.and.strings;

import java.util.Scanner;

public class RohanLovesZero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        System.out.println(hasSubarrayWithZeroSum(nums) ? "True" : "False");
    }

    public static boolean hasSubarrayWithZeroSum(int[] nums) {
        int[] prefixSums = new int[nums.length];
        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            prefixSums[i] = prefixSum;
            if (prefixSum == 0) {
                return true;
            }
            for (int j = 0; j < i; j++) {
                if (prefixSums[j] == prefixSum) {
                    return true;
                }
            }
        }

        return false;
    }
}