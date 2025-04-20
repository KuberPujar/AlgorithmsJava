package strings.sorting;

import java.util.Arrays;

/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.



Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

Output: false



Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.


Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t)); // Output: true

        s = "rat";
        t = "car";
        System.out.println(isAnagram(s, t)); // Output: false
    }

    private static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // Convert strings to char arrays
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        // Sort both char arrays (using O(n log n) sorting)
        mergeSort(sChars);
        mergeSort(tChars);

        // Compare sorted arrays
        return Arrays.equals(sChars, tChars);
    }

    // Merge Sort implementation for char arrays
    private static void mergeSort(char[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            char[] left = Arrays.copyOfRange(arr, 0, mid);
            char[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
        }
    }

    private static void merge(char[] arr, char[] left, char[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
