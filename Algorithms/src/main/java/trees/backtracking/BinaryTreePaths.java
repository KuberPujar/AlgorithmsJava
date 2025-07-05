package trees.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.



Example 1:


Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
Example 2:

Input: root = [1]
Output: ["1"]


Constraints:

The number of nodes in the tree is in the range [1, 100].
-100 <= Node.val <= 100
 */
public class BinaryTreePaths {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        backtrack(root, new StringBuilder(), result);
        return result;
    }

    private void backtrack(TreeNode node, StringBuilder path, List<String> result) {
        int len = path.length();
        if (path.length() > 0) path.append("->");
        path.append(node.val);

        if (node.left == null && node.right == null) {
            result.add(path.toString());
        } else {
            if (node.left != null) backtrack(node.left, path, result);
            if (node.right != null) backtrack(node.right, path, result);
        }
        path.setLength(len); // backtrack
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        BinaryTreePaths solution = new BinaryTreePaths();
        List<String> paths = solution.binaryTreePaths(root);
        System.out.println(paths); // Output: ["1->2->5", "1->3"]
    }
}
