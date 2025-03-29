package sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JetFighterCaptain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int globalMissing = findSmallestMissingNonNegative(arr);
        int segmentCount = 0;
        int currentStart = 0;

        for (int i = 0; i < n; i++) {
            int[] subArray = Arrays.copyOfRange(arr, currentStart, i + 1);
            int segmentMissing = findSmallestMissingNonNegative(subArray);
            if (segmentMissing == globalMissing) {
                segmentCount++;
                currentStart = i + 1;
            }
        }

        if (segmentCount >= k) {
            System.out.println("Attack");
        } else {
            System.out.println("Wait");
        }
    }

    private static int findSmallestMissingNonNegative(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        int missing = 0;
        while (set.contains(missing)) {
            missing++;
        }
        return missing;
    }
}