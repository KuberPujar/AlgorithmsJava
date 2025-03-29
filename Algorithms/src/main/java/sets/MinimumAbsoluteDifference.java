package sets;

import java.util.Scanner;

public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        scanner.close();

        System.out.println(findMinimumAbsoluteDifference(nums, k));
    }

    public static int findMinimumAbsoluteDifference(int[] nums, int k) {
        int minDifference = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + k; j < nums.length; j++) {
                int difference = Math.abs(nums[i] - nums[j]);
                if (difference < minDifference) {
                    minDifference = difference;
                }
            }
        }

        return minDifference;
    }
}
