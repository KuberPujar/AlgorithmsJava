package searching;

import java.util.Scanner;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] nums1 = new int[m];
        int[] nums2 = new int[n];
        for (int i = 0; i < m; i++) {
            nums1[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            nums2[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(findMedianSortedArrays(nums1, nums2, m, n));
    }

    public static int findMedianSortedArrays(int[] ar1, int[] ar2, int n, int m) {
        if (n > m) {
            return findMedianSortedArrays(ar2, ar1, m, n);
        }

        int left = 0, right = n;
        int median = 0;

        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = (n + m + 1) / 2 - partition1;

            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : ar1[partition1 - 1];
            int minRight1 = (partition1 == n) ? Integer.MAX_VALUE : ar1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : ar2[partition2 - 1];
            int minRight2 = (partition2 == m) ? Integer.MAX_VALUE : ar2[partition2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((n + m) % 2 == 0) {
                    median = (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
                } else {
                    median = Math.max(maxLeft1, maxLeft2);
                }
                break;
            } else if (maxLeft1 > minRight2) {
                right = partition1 - 1;
            } else {
                left = partition1 + 1;
            }
        }

        return median;
    }
}
