package greaterpyramid.c0;

import java.util.Scanner;

public class StringOps {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        int M = scanner.nextInt();
        int[] A = new int[M];
        for (int i = 0; i < M; i++) {
            A[i] = scanner.nextInt();
        }

        char[] chars = S.toCharArray();
        for (int ai : A) {
            int start = ai - 1; // Convert to 0-based index
            int end = chars.length - ai; // End position (0-based)
            reverseSubstring(chars, start, end);
        }

        System.out.println(new String(chars));
    }

    private static void reverseSubstring(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
