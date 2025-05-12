package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Concatenate the Nodes
Given a Binary Tree of N Nodes having integer values . Your Task is to find out the Largest Number that could be formed by concatenating all its nodes values.

For example:

Given the Binary Tree

           5
         /    \
      34       47
     /
    6

The answer would be 654734 since by concatenating the node values this is the highest number possible.

Input Format:

A single line that represents the value of the nodes and the value of '- 1' denotes NULL node.
Output Format:

Print the integer that represents the largest number that could be formed by concatenating all its nodes given in a Binary Tree.
Sample Input:

5 34 47 6 -1 -1 -1 -1 -1
Sample Output:

654734
Explanation
5 → 34 → 6 → 47 → 53647 (not optimal)
By arranging the numbers: 654734 is the largest possible concatenation.
The largest number is formed by rearranging the nodes as 6 → 5 → 47 → 34 which results in 654734.
Sample Input:

9 933 96 -1 -1 -1 -1
Sample Output:

996933
Explanation
9 → 933 → 96 → 993396 (not optimal)
By arranging the numbers: 996933 is the largest possible concatenation.
The largest number is formed by rearranging the nodes as 96 → 9 → 933 which results in 996933.
Constraints:

0 <= N <= 10^4

0 <= data <= 10^3

Where 'N' denotes the total number of nodes and 'data' denotes the value of the node.

Note: Print the answer in the Concatenate function and don't return the answer as an array.
 */
public class CancatenateTheNodes {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(34);
        root.right = new TreeNode(47);
        root.left.left = new TreeNode(6);

        concatenateNodes(root); // Output: 654734
    }

    public static void concatenateNodes(TreeNode root) {
        if (root == null) {
            System.out.println(0);
            return;
        }

        List<String> nodeValues = new ArrayList<>();
        collectNodeValues(root, nodeValues);

        // Custom comparator to sort the strings to form the largest number
        Collections.sort(nodeValues, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);
            }
        });

        StringBuilder result = new StringBuilder();
        for (String num : nodeValues) {
            result.append(num);
        }

        // Handle leading zeros (though constraints say node values are >=0)
        String res = result.toString();
        if (res.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(res);
        }
    }

    private static void collectNodeValues(TreeNode node, List<String> nodeValues) {
        if (node == null) {
            return;
        }
        nodeValues.add(String.valueOf(node.val));
        collectNodeValues(node.left, nodeValues);
        collectNodeValues(node.right, nodeValues);
    }

    // Helper method to build the tree from the input array
    public static TreeNode buildTree(Integer[] nodes, int index) {
        if (index >= nodes.length || nodes[index] == -1) {
            return null;
        }
        TreeNode root = new TreeNode(nodes[index]);
        root.left = buildTree(nodes, 2 * index + 1);
        root.right = buildTree(nodes, 2 * index + 2);
        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
