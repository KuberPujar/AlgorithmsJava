package arrays;

import java.util.Scanner;

public class MoveTwos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        moveTwos(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void moveTwos(int[] nums) {
        int n = nums.length;
        int index = 0;

        // Move all non-2 elements to the front
        for (int i = 0; i < n; i++) {
            if (nums[i] != 2) {
                nums[index++] = nums[i];
            }
        }

        // Fill the remaining positions with 2's
        while (index < n) {
            nums[index++] = 2;
        }
    }
}
