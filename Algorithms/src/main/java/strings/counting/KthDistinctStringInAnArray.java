package strings.counting;

import java.util.HashMap;
import java.util.Map;

/*
A distinct string is a string that is present only once in an array.

Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".

Note that the strings are considered in the order in which they appear in the array.



Example 1:

Input: arr = ["d","b","c","b","c","a"], k = 2
Output: "a"
Explanation:
The only distinct strings in arr are "d" and "a".
"d" appears 1st, so it is the 1st distinct string.
"a" appears 2nd, so it is the 2nd distinct string.
Since k == 2, "a" is returned.
Example 2:

Input: arr = ["aaa","aa","a"], k = 1
Output: "aaa"
Explanation:
All strings in arr are distinct, so the 1st string "aaa" is returned.
Example 3:

Input: arr = ["a","b","a"], k = 3
Output: ""
Explanation:
The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".


Constraints:

1 <= k <= arr.length <= 1000
1 <= arr[i].length <= 5
arr[i] consists of lowercase English letters.
 */
public class KthDistinctStringInAnArray {
    public static void main(String[] args) {
        String[] arr = {"d", "b", "c", "b", "c", "a"};
        int k = 2;
        // Example 1
        System.out.println(kthDistinct(arr, k)); // Output: "a"
        arr = new String[]{"aaa", "aa", "a"};
        k = 1;
        // Example 2
        System.out.println(kthDistinct(arr, k)); // Output: "aaa"
        arr = new String[]{"a", "b", "a"};
        k = 3;
        // Example 3
        System.out.println(kthDistinct(arr, k)); // Output: ""
    }

    private static String kthDistinct(String[] arr, int k) {
        // First pass: count occurrences
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String s : arr) {
            frequencyMap.put(s, frequencyMap.getOrDefault(s, 0) + 1);
        }

        // Second pass: find kth distinct string
        int count = 0;
        for (String s : arr) {
            if (frequencyMap.get(s) == 1) {
                count++;
                if (count == k) {
                    return s;
                }
            }
        }

        return "";
    }
}
