package arrays.and.strings;

import java.util.Scanner;

public class DiwaliParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        String S = scanner.next();
        scanner.close();

        System.out.println(longestWorkingLightsSubstring(N, K, S));
    }

    public static int longestWorkingLightsSubstring(int N, int K, String S) {
        int maxLength = 0;
        int left = 0;
        int countDamaged = 0;

        for (int right = 0; right < N; right++) {
            if (S.charAt(right) == '.') {
                countDamaged++;
            }

            while (countDamaged > K) {
                if (S.charAt(left) == '.') {
                    countDamaged--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
