package binarytree;

import java.util.*;

/*
Diameter of Binary Tree
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

Input :

[ 1 2 3 4 5 -1 -1 -1 -1 -1 -1]

Output:
3

        1
       / \
      2   3
     / \
    4   5
Input:

[10 5 20 3 7 -1 25 -1 -1 -1 -1 -1 -1]

Output:
4

         10
        /  \
       5    20
      / \     \
     3   7     25
Constraints:

The number of nodes in the tree is in the range [1, 3 * 10^4].
1 <= Node.val <= 1000
 */
public class DiameterOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private static int maxDiameter;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        input = input.replaceAll("[\\[\\]]", ""); // Remove brackets if present
        String[] parts = input.split("\\s+");
        List<Integer> valuesList = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                valuesList.add(Integer.parseInt(part));
            }
        }
        Integer[] values = valuesList.toArray(new Integer[0]);
        TreeNode root = buildTree(values);
        System.out.println(diameterOfBinaryTree(root));
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        height(root);
        return maxDiameter;
    }

    private static int height(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight + 2);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == -1) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();

            // Process left child
            if (i < values.length) {
                Integer leftVal = values[i++];
                if (leftVal != -1) {
                    current.left = new TreeNode(leftVal);
                    queue.add(current.left);
                }
            }

            // Process right child
            if (i < values.length) {
                Integer rightVal = values[i++];
                if (rightVal != -1) {
                    current.right = new TreeNode(rightVal);
                    queue.add(current.right);
                }
            }
        }

        return root;
    }
}
