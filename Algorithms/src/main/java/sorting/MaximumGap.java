package sorting;

import java.util.*;

public class MaximumGap {
    public static int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        // Find the minimum and maximum values in the array
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }

        // If all elements are the same, the gap is 0
        if (minVal == maxVal) {
            return 0;
        }

        int n = nums.length;
        int bucketSize = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketCount = (maxVal - minVal) / bucketSize + 1;

        // Initialize buckets
        int[] minBucket = new int[bucketCount];
        int[] maxBucket = new int[bucketCount];
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        Arrays.fill(maxBucket, Integer.MIN_VALUE);

        // Put numbers into buckets
        for (int num : nums) {
            int bucketIdx = (num - minVal) / bucketSize;
            minBucket[bucketIdx] = Math.min(minBucket[bucketIdx], num);
            maxBucket[bucketIdx] = Math.max(maxBucket[bucketIdx], num);
        }

        // Calculate maximum gap
        int maxGap = 0;
        int prevMax = minVal;
        for (int i = 0; i < bucketCount; i++) {
            if (minBucket[i] == Integer.MAX_VALUE) continue; // Skip empty bucket
            maxGap = Math.max(maxGap, minBucket[i] - prevMax);
            prevMax = maxBucket[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(maximumGap(nums));
    }
}
