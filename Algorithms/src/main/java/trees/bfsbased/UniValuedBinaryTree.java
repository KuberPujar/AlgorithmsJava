package trees.bfsbased;

import java.util.LinkedList;
import java.util.Queue;

/*
A binary tree is uni-valued if every node in the tree has the same value.

Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.



Example 1:


Input: root = [1,1,1,1,1,null,1]
Output: true
Example 2:


Input: root = [2,2,2,5,2]
Output: false


Constraints:

The number of nodes in the tree is in the range [1, 100].
0 <= Node.val < 100
 */
public class UniValuedBinaryTree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        int value = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val != value) return false;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return true;
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);

        UniValuedBinaryTree tree = new UniValuedBinaryTree();
        System.out.println(tree.isUnivalTree(root)); // Output: true

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(5);
        System.out.println(tree.isUnivalTree(root2)); // Output: false
    }
}
