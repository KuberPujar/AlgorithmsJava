package tries;
/*
XOR Pair in Trie
You are given an array containing n number of elements. The elements of the array are inserted in a Trie data structure in the form of binary representation for example 1, 2 and 3 will be represented as:

                    0                   2nd bit
                  /   \
                 0      1               1st bit
                 \     / \
                  1   0    1            0th bit
Note that the Trie is for 32-bit representation and out of those only first three are shown in the example above.

Your task is to use the trie in order to find the max XOR pair. This can also be done without using Trie but I encourage you to try and solve the question using trie.

Input Format:

The first line contains an integer n, representing the number of elements in the array.
The second line contains n space-separated integers representing the elements of the array.
Output Format:

Print a single integer, the maximum XOR value between any elements in the array.
Sample Input 1
3
1 2 3
Sample Output 1
3
Explanation
Max XOR pair is 1 and 2 whose XOR is equal to 3.
Sample Input 2
6
8 1 2 5 10 15
Sample Output 2
15
Explanation
The maximum XOR pair is 10 and 5, whose XOR is 10 ^ 5 = 15.
Constraints:
2 <= n <= 10^4
2^0 <= A[i] <=2^31
Note:The function should return the result. The driver code will handle printing the output.
 */
public class XORPairInTrie {
    public static void main(String[] args) {
        // Example usage
        int[] arr = {8, 1, 2, 5, 10, 15};
        int result = findMaxXOR(arr);
        System.out.println(result); // Output: 15
    }

    public static int findMaxXOR(int[] arr) {
        TrieNode root = new TrieNode();
        for (int num : arr) {
            insert(root, num);
        }
        int maxXOR = Integer.MIN_VALUE;
        for (int num : arr) {
            maxXOR = Math.max(maxXOR, findMaxXORPair(root, num));
        }
        return maxXOR;
    }

    private static void insert(TrieNode root, int num) {
        TrieNode curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (curr.left == null) {
                    curr.left = new TrieNode();
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new TrieNode();
                }
                curr = curr.right;
            }
        }
    }

    private static int findMaxXORPair(TrieNode root, int num) {
        TrieNode curr = root;
        int maxXOR = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (curr.right != null) {
                    maxXOR |= (1 << i);
                    curr = curr.right;
                } else {
                    curr = curr.left;
                }
            } else {
                if (curr.left != null) {
                    maxXOR |= (1 << i);
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
        }
        return maxXOR;
    }

    static class TrieNode {
        TrieNode left;
        TrieNode right;
    }
}
