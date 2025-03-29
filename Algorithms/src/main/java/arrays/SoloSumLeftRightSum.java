package arrays;

import java.util.Scanner;

public class SoloSumLeftRightSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int[] result = findLeftRightSum(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static int[] findLeftRightSum(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        int[] ans = new int[n];

        leftSum[0] = 0;
        for (int i = 1; i < n; i++) {
            leftSum[i] = leftSum[i - 1] + nums[i - 1];
        }

        rightSum[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            ans[i] = leftSum[i] + rightSum[i];
        }

        return ans;
    }
}