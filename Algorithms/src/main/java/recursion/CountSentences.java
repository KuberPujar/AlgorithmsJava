package recursion;
/*
Sentence Count
Given a string s and a dictionary of words dict of length n, add spaces in s to construct a sentence where each word is a valid dictionary word. Each dictionary word can be used more than once. Return the number of such possible sentences.

Input Format:

The first line contains the number of words in the dictionary denoted by the integer n.
The second line contains n space-separated strings for the dictionary.
The third line contains the string s which is to be transformed into a sentence.
Output Format:

A single integer denoting the number of possible sentences.
Sample Input 1:

8
he hebrew brew bible isa book is a
hebrewbibleisabook
Sample Output 1:

4
Explanation:

The possible sentences are:

he brew bible isa book
hebrew bible isa book
hebrew bible is a book
he brew bible is a book
Constraints:

(1 <= n <= 20)
(1 <= dict[i] <= 15)
(1 <= |s| <= 500)
Note:The function should return the result.
 */
import java.util.*;

public class CountSentences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> dict = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dict.add(scanner.next());
        }
        String s = scanner.next();
        scanner.close();

        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        int result = countSentences(s, dict, 0,memo);
        System.out.println(result);
    }

    private static int countSentences(String s, List<String> dict, int start, int[] memo) {

        if (start == s.length()) {
            return 1;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        int count = 0;
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (dict.contains(word)) {
                count += countSentences(s, dict, end,memo);
            }
        }
        memo[start] = count;
        return count;
    }
}