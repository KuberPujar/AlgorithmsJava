package tries;
/*
Searching in Trie
You are given a list of words and the root node of an already implemented Trie. Your task is to check whether each word from a given list is present in the Trie or not. For each word, return True if the word is present in the Trie, otherwise return False.

Note: You have to return true or false, even though the output will be in form of yes and no.

Input Format:

The first line contains an integer n, representing the number of words that are present in the Trie.
The second line contains n space-separated words that are present in the Trie.
The third line contains a list of words that need to be checked for their presence in the Trie.
Output Format:

For each word in the list to be checked, output "Yes" if the word is found in the Trie and "No" if it is not found, with each result on a new line.
Input 1
3
ramesh suresh kamlesh
ronny kamlesh faruq
Output 1
No
Yes
No
Explanation
The word ronny is not present in the Trie, so the output is "No".
The word kamlesh is found in the Trie, so the output is "Yes".
The word faruq is not found in the Trie, so the output is "No".
Input 2
4
harsh harshit harshita mohan
harshi har mohanan mohan
Output 2
No
No
No
Yes
Explanation
The words harshi, har, and mohanan are not present in the Trie, so the output is "No" for each.
The word mohan is found in the Trie, so the output is "Yes".
Constraints:

1 <= n <= 1000

1 <= word.length <= 100

Note:The function should return the result. The driver code will handle printing the output.
 */
public class SearchingInTrie {
    public static void main(String[] args) {
        // Example usage
        TrieNode root = new TrieNode(' ');
        String[] words = {"ramesh", "suresh", "kamlesh"};
        insertWord(root, words);
        String[] searchWords = {"ronny", "kamlesh", "faruq"};
        boolean[] results = searchWordsInTrie(root, searchWords);
        for (boolean result : results) {
            System.out.println(result ? "Yes" : "No");
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

    public static boolean[] searchWordsInTrie(TrieNode root, String[] words) {
        boolean[] results = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            results[i] = search(root, words[i]);
        }
        return results;
    }

    public static boolean search(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isEndOfWord;
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
