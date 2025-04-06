package arrays;

import java.util.Scanner;

public class ShuffleTheArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            nums[i] = scanner.nextInt();
        }
        int[] result = shuffle(nums, n);
        for (int j : result) {
            System.out.print(j + " ");
        }
    }

    private static int[] shuffle(int[] nums, int n) {
        int[] shuffledArray = new int[2 * n];
        for (int i = 0; i < n; i++) {
            shuffledArray[2*i] = nums[i];
            shuffledArray[2* i + 1] = nums[i + n];
        }
        return shuffledArray;
    }
}
