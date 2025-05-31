package graphalgorithms.bfsdfs;

import java.util.*;

/*
Word Ladder
Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find the length of the shortest transformation sequence from startWord to targetWord. Keep the following conditions in mind:

A word can only consist of lowercase characters. Only one letter can be changed in each transformation. Each transformed word must exist in the wordList including the targetWord. startWord may or may not be part of the wordList The second part of this problem can be found here.

Note: If no possible way to transform sequence from startWord to targetWord return 0

Input Format:

The first line contains an integer N, representing the number of words in the wordList.
The second line contains N space-separated words representing the wordList.
The third line contains two space-separated strings, startWord and targetWord.
Output Format:

Return a single integer representing the length of the shortest transformation sequence. If no sequence exists, return 0.
Expected Time Compelxity:
O(N2 * M)

Expected Auxiliary Space:
O(N * M) where N = length of wordList and M = |wordListi|

Sample Input 1:

5

des der dfr dgt dfs

der dfs
Sample Output 1:
3

Explanation
The shortest transformation sequence is der -> dfr -> dfs, which takes 3 steps.

Sample Input 2:

6

glue glut geut neut next hill

blue next
Sample Output:
6

Explanation
The shortest transformation sequence is blue -> glue -> glut -> geut -> neut -> next, which takes 6 steps.

Constraints:
1 ≤ N ≤ 100
1 ≤ M ≤ 10
Note:The function should return the result. The driver code will handle printing the output.
 */
public class WordLadder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        String[] wordList = scanner.nextLine().split(" ");
        String[] startTarget = scanner.nextLine().split(" ");
        String startWord = startTarget[0];
        String targetWord = startTarget[1];
        System.out.println(ladderLength(startWord, targetWord, wordList));
        scanner.close();
    }

    public static int ladderLength(String startWord, String targetWord, String[] wordList) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(wordList));
        if (!wordSet.contains(targetWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(startWord);
        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                assert current != null;
                if (current.equals(targetWord)) {
                    return steps;
                }
                char[] chars = current.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[j] = c;
                        String transformed = new String(chars);
                        if (wordSet.contains(transformed)) {
                            queue.add(transformed);
                            wordSet.remove(transformed);
                        }
                    }
                    chars[j] = original;
                }
            }
            steps++;
        }
        return 0;
    }
}
