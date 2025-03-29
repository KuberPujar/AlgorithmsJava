package arrays.and.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RohanLovesZero2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(scanner.nextInt());
        }
        scanner.close();

        List<Integer> result = findIndexesWithEqualPrefixAndSuffixSum(nums, n);
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int index : result) {
                System.out.print(index + " ");
            }
        }
    }

    public static List<Integer> findIndexesWithEqualPrefixAndSuffixSum(List<Integer> nums, int n) {
        int[] prefixSums = new int[n];
        int[] suffixSums = new int[n];
        List<Integer> result = new ArrayList<>();

        // Calculate prefix sums
        prefixSums[0] = nums.get(0);
        for (int i = 1; i < n; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums.get(i);
        }

        // Calculate suffix sums
        suffixSums[n - 1] = nums.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + nums.get(i);
        }

        // Find indexes where prefix sum equals suffix sum
        for (int i = 0; i < n; i++) {
            if (prefixSums[i] == suffixSums[i]) {
                result.add(i);
            }
        }

        return result;
    }
}