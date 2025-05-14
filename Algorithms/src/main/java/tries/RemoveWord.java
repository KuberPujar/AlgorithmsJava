package tries;

/*
Remove word
You are given a list of words and a root node of an already implemented Trie. You are given another set of words and your task is to remove these words from the implemented trie.

Input Format:
The first line contains two space seperated integers denoting the number of words in the trie and the number of words to be removed respectively.

The second line contains 'n' number of words that are to be implemented in trie.

The third line contains 'm' number of words which are to be removed from trie.

Output format:
Return nothing just write a function to remove the word from the given trie.
Sample Input 1:
4
2
Harsh Subhash Chahal Aakash
Harsh Aakash

Sample Output 1:
Removed
Removed

Explanation:
The words Harsh and Aakash are successfully removed from the Trie, so "Removed" is printed twice.

Sample Input 2:
5 1 rainbow tired trick jojo robert

tired

Sample Output 2:
Removed

Explanation:
The word tired is successfully removed from the Trie, so "Removed" is printed once.

Constraints:
There are no specific constraints on the number of words or their lengths, but all input words are unique.

Note:The function should return the result. The driver code will handle printing the output.
 */
//TreeNode class with following  char data;
//        TrieNode[] children;
//        boolean isEnd;parameters
public class RemoveWord {
    // This version assumes the Trie stores words in UPPERCASE
    // and uses 'A' as the base for character indexing (0-25 for 'A'-'Z').
    public boolean removeWordFromTrie(TrieNode root, String word) {
        if (root == null || word == null || word.isEmpty()) {
            return false;
        }

        String processedWord = word.toUpperCase(); // Convert word to UPPERCASE

        TrieNode currentNode = root;
        for (int i = 0; i < processedWord.length(); i++) {
            char ch = processedWord.charAt(i);

            // Validate if the character is an uppercase English letter
            if (ch < 'A' || ch > 'Z') {
                return false; // Character is not 'A'-'Z', invalid for this Trie setup
            }
            int index = ch - 'A'; // Calculate index based on 'A' (0 for 'A', 1 for 'B', ...)

            if (currentNode.children[index] == null) {
                return false; // Word not found in Trie
            }
            currentNode = currentNode.children[index];
        }

        if (!currentNode.isEnd) {
            return false; // Word path exists, but it's not marked as a complete word
        }

        // Word confirmed to exist. Perform recursive deletion.
        recursiveDeleteHelper(root, processedWord, 0); // Pass the UPPERCASE word
        return true; // Signifies word was found and removal process initiated
    }

    /**
     * Helper function to recursively delete a word from the Trie.
     * Assumes 'word' parameter is already processed (e.g., UPPERCASE) and validated.
     */
    private boolean recursiveDeleteHelper(TrieNode currentNode, String word, int depth) {
        if (currentNode == null) {
            return false; // Should not happen if called correctly
        }

        if (depth == word.length()) {
            // Reached the node corresponding to the last character of the word.
            currentNode.isEnd = false; // Unmark the word

            // This node can be deleted if it has no children.
            return !hasChildren(currentNode);
        }

        char ch = word.charAt(depth);

        // Defensive check, though 'word' should already consist of 'A'-'Z' characters
        // if validated by removeWordFromTrie.
        if (ch < 'A' || ch > 'Z') {
            return false; // Should ideally not be reached
        }
        int index = ch - 'A'; // Calculate index based on 'A'


        TrieNode childNode = currentNode.children[index];
        if (childNode == null) {
            // Path broken or inconsistent state, should not happen if word was verified.
            return false;
        }

        boolean shouldDeleteChild = recursiveDeleteHelper(childNode, word, depth + 1);

        if (shouldDeleteChild) {
            currentNode.children[index] = null; // Prune the child node

            // Check if the current node itself can now be deleted:
            // 1. It's not marked as the end of another word.
            // 2. It has no other children remaining.
            return !currentNode.isEnd && !hasChildren(currentNode);
        }

        return false; // Child node (or its descendants) could not be deleted
    }

    /**
     * Checks if a TrieNode has any children.
     */
    private boolean hasChildren(TrieNode node) {
        if (node == null) return false;
        for (int i = 0; i < 26; i++) { // Check all 26 possible child slots
            if (node.children[i] != null) {
                return true;
            }
        }
        return false;
    }

    static class TrieNode {
        char data;
        TrieNode[] children;
        boolean isEnd;

        TrieNode(char data) {
            this.data = data;
            children = new TrieNode[26];
            isEnd = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    public static void main(String[] args) {


        String[] wordsToInsert = {"Harsh","Subhash","Chahal","Aakash"};


        String[] wordsToRemove = {"Harsh","Aakash"};

        TrieNode root = new TrieNode(' '); // Create the root node of the Trie
        RemoveWord trieOperations = new RemoveWord();

        // Insert words into the Trie
        for (String word : wordsToInsert) {
            insertWordIntoTrie(root, word);
        }

        // Remove words from the Trie
        for (String word : wordsToRemove) {
            if (trieOperations.removeWordFromTrie(root, word)) {
                System.out.println("Removed");
            } else {
                System.out.println("Not Found");
            }
        }
    }

    public static void insertWordIntoTrie(TrieNode root, String word) {
        if (root == null || word == null || word.isEmpty()) {
            // System.out.println("Cannot insert null/empty word or use null root."); // Optional: for debugging
            return;
        }

        String processedWord = word.toUpperCase(); // Standardize to uppercase
        TrieNode currentNode = root;

        for (int i = 0; i < processedWord.length(); i++) {
            char ch = processedWord.charAt(i);

            // Validate if the character is an uppercase English letter
            if (ch < 'A' || ch > 'Z') {
                // System.out.println("Word contains invalid character: " + ch); // Optional: for debugging
                return; // Skip insertion if word has non-uppercase-alpha characters
            }
            int index = ch - 'A'; // Calculate index (0 for 'A', 1 for 'B', ...)

            if (currentNode.children[index] == null) {
                // If child node doesn't exist for this character, create it
                currentNode.children[index] = new TrieNode(ch);
            }
            // Move to the child node
            currentNode = currentNode.children[index];
        }
        // After iterating through all characters, mark the last node as the end of a word
        currentNode.isEnd = true;
    }
}

