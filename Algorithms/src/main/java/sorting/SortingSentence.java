package sorting;

import java.util.Arrays;

/*
A sentence is a list of words that are separated by a single space with no
 leading or trailing spaces. Each word consists of lowercase and uppercase
 English letters.

A sentence can be shuffled by appending the 1-indexed word position to each
word then rearranging the words in the sentence.

For example, the sentence "This is a sentence" can be shuffled as "sentence4
a3 is2 This1" or "is2 sentence4 This1 a3".
Given a shuffled sentence s containing no more than 9 words, reconstruct and
return the original sentence.



Example 1:

Input: s = "is2 sentence4 This1 a3"
Output: "This is a sentence"
Explanation: Sort the words in s to their original positions "This1 is2 a3 sentence4", then remove the numbers.
Example 2:

Input: s = "Myself2 Me1 I4 and3"
Output: "Me Myself and I"
Explanation: Sort the words in s to their original positions "Me1 Myself2 and3 I4", then remove the numbers.


Constraints:

2 <= s.length <= 200
s consists of lowercase and uppercase English letters, spaces, and digits from 1 to 9.
The number of words in s is between 1 and 9.
The words in s are separated by a single space.
s contains no leading or trailing spaces.
 */
public class SortingSentence {
    public static void main(String[] args) {
        String s = "is2 sentence4 This1 a3";
        System.out.println(sortSentence(s)); // Output: "This is a sentence"
        s = "Myself2 Me1 I4 and3";
        System.out.println(sortSentence(s)); // Output: "Me Myself and I"
    }

    private static String sortSentence(String s) {
            // Split the string into words
            String[] words = s.split(" ");

            // Perform merge sort on the words based on their numeric suffix
            mergeSort(words, 0, words.length - 1);

            // Remove the numeric suffix from each word and build the result
            StringBuilder result = new StringBuilder();
            for (String word : words) {
                result.append(word.substring(0, word.length() - 1)).append(" ");
            }

            // Remove the trailing space and return
            return result.toString().trim();
        }

        // Merge Sort implementation
        private static void mergeSort(String[] arr, int left, int right) {
            if (left < right) {
                int mid = left + (right - left) / 2;

                // Sort first and second halves
                mergeSort(arr, left, mid);
                mergeSort(arr, mid + 1, right);

                // Merge the sorted halves
                merge(arr, left, mid, right);
            }
        }

        private static void merge(String[] arr, int left, int mid, int right) {
            // Create temporary arrays
            String[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
            String[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

            // Initial indexes of the subarrays
            int i = 0, j = 0, k = left;

            // Merge the temp arrays
            while (i < leftArr.length && j < rightArr.length) {
                int numLeft = getSuffixNumber(leftArr[i]);
                int numRight = getSuffixNumber(rightArr[j]);

                if (numLeft <= numRight) {
                    arr[k++] = leftArr[i++];
                } else {
                    arr[k++] = rightArr[j++];
                }
            }

            // Copy remaining elements of leftArr if any
            while (i < leftArr.length) {
                arr[k++] = leftArr[i++];
            }

            // Copy remaining elements of rightArr if any
            while (j < rightArr.length) {
                arr[k++] = rightArr[j++];
            }
        }

        // Helper method to extract the numeric suffix from a word
        private static  int getSuffixNumber(String word) {
            return Integer.parseInt(word.substring(word.length() - 1));
        }
}
