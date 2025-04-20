package strings.sorting;
/*
A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of lowercase and uppercase English letters.

A sentence can be shuffled by appending the 1-indexed word position to each word then rearranging the words in the sentence.

For example, the sentence "This is a sentence" can be shuffled as "sentence4 a3 is2 This1" or "is2 sentence4 This1 a3".
Given a shuffled sentence s containing no more than 9 words, reconstruct and return the original sentence.



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
public class SortingASentence {
    public static void main(String[] args) {
        String s = "is2 sentence4 This1 a3";
        System.out.println(sortSentence(s));
    }

    private static String sortSentence(String s) {
        String[] words = s.split(" ");
        mergeSort(words, 0, words.length - 1);

        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word.substring(0, word.length() - 1)).append(" ");
        }

        return result.toString().trim();
    }

    private static void mergeSort(String[] words, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(words, left, mid);
            mergeSort(words, mid + 1, right);
            merge(words, left, mid, right);
        }
    }

    private static void merge(String[] words, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] leftArray = new String[n1];
        String[] rightArray = new String[n2];

        System.arraycopy(words, left, leftArray, 0, n1);
        System.arraycopy(words, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            int num1 = leftArray[i].charAt(leftArray[i].length() - 1) - '0';
            int num2 = rightArray[j].charAt(rightArray[j].length() - 1) - '0';
            if (num1 <= num2) {
                words[k++] = leftArray[i++];
            } else {
                words[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            words[k++] = leftArray[i++];
        }

        while (j < n2) {
            words[k++] = rightArray[j++];
        }
    }
}
