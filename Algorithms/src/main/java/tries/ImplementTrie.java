package tries;
/*
Implement Trie
You are given a list of words and a root node of the TrieNode class. Your task is to implement a Trie (prefix tree) data structure and insert these words into it. You are passed a root node in which you have to implement the trie in that and not return anything.

The output will be in the form of "Yes" and "No" which represent if the word was inserted successfully and unsuccessfully respectively.

Input Format:

The first line contains an integer n, representing the number of words to be inserted into the Trie.
The second line contains n space-separated words that are to be inserted.
Output Format:

Implement the trie with the root node provided and return nothing, the system will check your answer, yes represents that it is correct.
Input 1
3
big brigadier bison
Output 1
Yes
Yes
Yes
Explanation
Each of the three words big, brigadier, and bison have been successfully inserted into the Trie, resulting in "Yes" being printed three times.
Input 2
4
harsh harshit harshita mohan
Output 2
Yes
Yes
Yes
Yes
Explanation
All four words harsh, harshit, harshita, and mohan have been successfully inserted into the Trie, hence "Yes" is printed for each word.

Constraints:

1 <= n <= 2000
1 <= word.length <= 2000
Note: The insertWord function inserts all the words from the provided list into the Trie structure. It does not return any value but modifies the Trie in-place. The driver code will handle printing the output.
 */
public class ImplementTrie {
    public static void main(String[] args) {
        // Example usage
        TrieNode root = new TrieNode(' ');
        String[] words = {"big", "brigadier", "bison"};
            insertWord(root, words);
    }
//write a function to insert a array of words into the trie
    public static void insertWord(TrieNode root, String[] words) {
        for (String word : words) {
            insert(root, word);
        }
    }
//write a function to insert a word into the trie
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
        curr.isend = true;
        System.out.println("Yes");
    }

    static class TrieNode {
        char data;
        TrieNode[] children;
        boolean isend;

        TrieNode(char data) {
            this.data = data;
            children = new TrieNode[26];
            isend = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }
}
