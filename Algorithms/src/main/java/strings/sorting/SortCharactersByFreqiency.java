package strings.sorting;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.



Example 1:

Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.


Constraints:

1 <= s.length <= 5 * 105
s consists of uppercase and lowercase English letters and digits.
 */
public class SortCharactersByFreqiency {
    public static void main(String[] args) {
        String s = "tree";
        System.out.println(sortCharactersByFrequency(s)); // Output: "eert"

        s = "cccaaa";
        System.out.println(sortCharactersByFrequency(s)); // Output: "aaaccc"

        s = "Aabb";
        System.out.println(sortCharactersByFrequency(s)); // Output: "bbAa"
    }
private static String sortCharactersByFrequency(String s) {
    // Step 1: Count character frequencies
    int[] freq = new int[128];
        for (char c : s.toCharArray()) {
        freq[c]++;
    }

    // Step 2: Create list of character-frequency pairs
    List<CharFreq> charFreqList = new ArrayList<>();
        for (char c = 0; c < 128; c++) {
        if (freq[c] > 0) {
            charFreqList.add(new CharFreq(c, freq[c]));
        }
    }

    // Step 3: Sort using custom merge sort (O(n log n))
    mergeSort(charFreqList, 0, charFreqList.size() - 1);

    // Step 4: Build the result string
    StringBuilder result = new StringBuilder();
        for (CharFreq cf : charFreqList) {
        for (int i = 0; i < cf.freq; i++) {
            result.append(cf.c);
        }
    }

        return result.toString();
}

// Custom class to hold character-frequency pairs
private static class CharFreq {
    char c;
    int freq;

    CharFreq(char c, int freq) {
        this.c = c;
        this.freq = freq;
    }
}

// Merge Sort implementation
private static void mergeSort(List<CharFreq> list, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(list, left, mid);
        mergeSort(list, mid + 1, right);
        merge(list, left, mid, right);
    }
}

private static void merge(List<CharFreq> list, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    List<CharFreq> leftList = new ArrayList<>(list.subList(left, left + n1));
    List<CharFreq> rightList = new ArrayList<>(list.subList(mid + 1, mid + 1 + n2));

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (compare(leftList.get(i), rightList.get(j))) {
            list.set(k++, leftList.get(i++));
        } else {
            list.set(k++, rightList.get(j++));
        }
    }

    while (i < n1) {
        list.set(k++, leftList.get(i++));
    }

    while (j < n2) {
        list.set(k++, rightList.get(j++));
    }
}

// Custom comparison: first by frequency (desc), then by character (asc)
private static boolean compare(CharFreq a, CharFreq b) {
    if (a.freq != b.freq) {
        return a.freq > b.freq;
    }
    return a.c < b.c;
}
}
