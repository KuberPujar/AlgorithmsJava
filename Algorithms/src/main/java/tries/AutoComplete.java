package tries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Autocomplete
You've likely experienced the autocomplete feature on your smartphone. Your task is to implement this feature using a Trie data structure. You are given n words and an incomplete word w. Your goal is to auto-complete the word w by finding and storing all possible words from the list that can be formed using the prefix w in an array and then return that array.

Input Format:

The first line contains an integer n, representing the number of words in the list.
The next n lines contain the words to be inserted into the Trie.
The last line contains the incomplete word w that needs to be auto-completed.
Output Format:

return an array of strings containing all possible words that can be formed using the incomplete word w, sorted in ascending order.
Sample Input 1 :

7

do
dont
no
not
note
notes
den

The word needs to be autocompleted is "no"

Sample Output 1:

no not note notes

Explanation

The words no, not, note, and notes can all be formed using the prefix no, and they are printed in sorted order.

Sample Input 2:

3

den
denmark
don

Sample Output 2:

-1 (Empty)

Explanation

There are no words that can be auto-completed using the prefix de, so the output is -1.

Constraints:

1 <= word.length <= 2000

Note:The function should return the result. The driver code will handle printing the output.
 */
public class AutoComplete {
    public static void main(String[] args) {
        // Example usage
        TrieNode root = new TrieNode(' ');
        String[] words = {"do", "dont", "no", "not", "note", "notes", "den"};
        insertWord(root, words);
        String incompleteWord = "no";
        List<String> result = autoComplete(root, incompleteWord);
        for (String word : result) {
            System.out.print(word + " ");
        }
    }

    public static List<String> autoComplete(TrieNode root, String incompleteWord) {
        List<String> result = new ArrayList<>();
        TrieNode curr = root;
        for (char ch : incompleteWord.toCharArray()) {
            int index = ch - 'a';
            if (curr.children[index] == null) {
                return List.of("-1");
            }
            curr = curr.children[index];
        }
        findWords(curr, incompleteWord, result);
        Collections.sort(result);
        return result.isEmpty() ? List.of("-1") : result;
    }

    private static void findWords(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord) {
            result.add(prefix);
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                findWords(node.children[i], prefix + (char) (i + 'a'), result);
            }
        }
    }

    public static void insertWord(TrieNode root, String[] words) {
        for (String word : words) {
            insert(root, word);
        }
    }

    public static void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode(ch);
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    static class TrieNode {
        char data;
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode(char data) {
            this.data = data;
            children = new TrieNode[26];
            isEndOfWord = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }
}
