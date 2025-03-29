package arrays.and.strings;

import java.util.Scanner;

public class CountOccurrencesOfAnagrams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        String C = scanner.next();
        scanner.close();

        System.out.println(countAnagrams(S, C));
    }

    public static int countAnagrams(String S, String C) {
        int[] charCountC = new int[26];
        int[] charCountWindow = new int[26];
        int count = 0;

        // Initialize the frequency array for string C
        for (char c : C.toCharArray()) {
            charCountC[c - 'a']++;
        }

        int windowSize = C.length();
        int n = S.length();

        // Initialize the frequency array for the first window
        for (int i = 0; i < windowSize; i++) {
            charCountWindow[S.charAt(i) - 'a']++;
        }

        // Check if the first window is an anagram
        if (areArraysEqual(charCountC, charCountWindow)) {
            count++;
        }

        // Slide the window over the string S
        for (int i = windowSize; i < n; i++) {
            // Add the new character to the window
            charCountWindow[S.charAt(i) - 'a']++;
            // Remove the character that is no longer in the window
            charCountWindow[S.charAt(i - windowSize) - 'a']--;

            // Check if the current window is an anagram
            if (areArraysEqual(charCountC, charCountWindow)) {
                count++;
            }
        }

        return count;
    }

    // Helper method to compare two frequency arrays
    public static boolean areArraysEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}