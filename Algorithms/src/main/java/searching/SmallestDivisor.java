package searching;

import java.util.Scanner;

public class SmallestDivisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int limit = sc.nextInt();
        sc.close();

        System.out.println(smallestDivisor(nums, limit));
    }

    public static int smallestDivisor(int[] nums, int limit) {
        int left = 1;
        int right = getMax(nums);
        int result = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValidDivisor(nums, mid, limit)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private static int getMax(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static boolean isValidDivisor(int[] nums, int divisor, int limit) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor; // Equivalent to Math.ceil((double) num / divisor)
            if (sum > limit) {
                return false;
            }
        }
        return true;
    }
}
