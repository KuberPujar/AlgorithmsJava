package tries;

import java.util.Scanner;
/*
Disha wants to create a Log Query Interface . While Disha is Working on list , she found that she is spending more time on searching for a word in that list. So, I avoid spending of more time on searching the entire list to get something , she decided to build a log and query interface , where she could find the desired word by finding all the words with that prefix.
But the task of Disha is not to just find the words , she wants to know what is the count of words in the list have a word as prefix.

Sample Input
15 7
apple
app
apricot
apricots
orange
orangutan
banana
bananas
grape
grapes
grapefruit
dog
dogged
dogs
dogg
do
Sample Output
4
4
2
3
1
0
1
Explanation
Initialize Trie:

Create an empty trie with a root node.

Insert Strings into Trie:

Insert "apple":
Trie: a -> ap -> app -> appl -> apple (count: 1)
Insert "app":
Trie: a -> ap -> app (count: 2) -> appl -> apple (count: 1)
Insert "apricot":
Trie: a -> ap -> app (count: 2) -> apr -> apric -> aprico -> apricot (count: 1)
Insert "apricots":
Trie: a -> ap -> app (count: 2) -> apr -> apric -> aprico -> apricot (count: 1), apricots (count: 1)
Insert "orange":
Trie: o -> or -> ora -> orang -> orange (count: 1)
Insert "orangutan":
Trie: o -> or -> ora -> orang -> orange (count: 1), orangutan (count: 1)
Insert "banana":
Trie: b -> ba -> ban -> bana -> banan -> banana (count: 1)
Insert "bananas":
Trie: b -> ba -> ban -> bana -> banan -> banana (count: 1), bananas (count: 1)
Insert "grape":
Trie: g -> gr -> gra -> grap -> grape (count: 1)
Insert "grapes":
Trie: g -> gr -> gra -> grap -> grape (count: 1), grapes (count: 1)
Insert "grapefruit":
Trie: g -> gr -> gra -> grap -> grape (count: 1), grapes (count: 1), grapefruit (count: 1)
Insert "dog":
Trie: d -> do -> dog (count: 1)
Insert "dogged":
Trie: d -> do -> dog (count: 1), dogged (count: 1)
Insert "dogs":
Trie: d -> do -> dog (count: 1), dogged (count: 1), dogs (count: 1)
Insert "dogg":
Trie: d -> do -> dog (count: 1), dogged (count: 1), dogs (count: 1), dogg (count: 1)
Insert "do":
Trie: d -> do (count: 2), dog (count: 1), dogged (count: 1), dogs (count: 1), dogg (count: 1)

Process Queries:

Query "apple" → Count: 4 (Trie: a -> ap -> app -> appl -> apple (count: 1))
Query "app" → Count: 4 (Trie: a -> ap -> app (count: 2) -> appl -> apple (count: 1))
Query "apricots" → Count: 2 (Trie: a -> ap -> app (count: 2) -> apr -> apric -> aprico -> apricot (count: 1), apricots (count: 1))
Query "orange" → Count: 3 (Trie: o -> or -> ora -> orang -> orange (count: 1), orangutan (count: 1))
Query "orangutan" → Count: 1 (Trie: o -> or -> ora -> orang -> orange (count: 1), orangutan (count: 1))
Query "banana" → Count: 0 (Not in the trie)
Query "do" → Count: 1 (Trie: d -> do (count: 2), dog (count: 1), dogged (count: 1), dogs (count: 1), dogg (count: 1))
Input Format:

The first line contains N, Q: the number words in list and number of queries.

N lines follow, with words (of list) consisting of lowercase letters. The sum of their lengths won't be greater than 10^6

Q lines follow, with words (queries) consisting of lowercase letters. The sum of their lengths won't be greater than 10^6
Output Format:

For each query print the number of words in list which have actual word as prefix.
Constraints

1 ≤ N,Q ≤ 10^5
The sum of the lengths of words in the list won't be greater than 10^6.
The sum of the lengths of words in the queries won't be greater than 10^6.
All words (both in the list and queries) consist of lowercase letters.
 */
public class LogQueryInterface {
    static class TrieNode {
        TrieNode[] arr;
        long count;

        TrieNode() {
            arr = new TrieNode[26];
            count = 0;
        }
    }
   //take input from above example as 15 7
   //apple
   //app
   //apricot
   //apricots
   //orange
   //orangutan
   //banana
   //bananas
   //grape
   //grapes
   //grapefruit
   //dog
   //dogged
   //dogs
   //dogg
   //do
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        String[] words = {"apple", "app", "apricot", "apricots", "orange", "orangutan", "banana", "bananas", "grape", "grapes", "grapefruit", "dog", "dogged", "dogs", "dogg", "do"};
        for (String word : words) {
            insert(root, word);
        }
        String[] queries = {"apple", "app", "apricots", "orange", "orangutan", "banana", "do"};
        for (String query : queries) {
            System.out.println(query(root, query));
        }
    }

    private static void insert(TrieNode root, String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.arr[index] == null) {
                current.arr[index] = new TrieNode();
            }
            current = current.arr[index];
            current.count++;
        }
    }

    private static long query(TrieNode root, String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (current.arr[index] == null) {
                return 0;
            }
            current = current.arr[index];
        }
        return current.count;
    }
}
