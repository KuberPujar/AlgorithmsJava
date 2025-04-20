package strings.backtracking;

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


 // Definition for a binary tree node.
  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

public class BinaryTreePaths {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        List<String> paths = binaryTreePaths(root);
        System.out.println(paths); // Output: ["1->2->5","1->3"]
    }

    private static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) return paths;

        backtrack(root, new StringBuilder(), paths);
        return paths;
    }

    private static void backtrack(TreeNode node, StringBuilder currentPath, List<String> paths) {
        if (node == null) return;

        int length = currentPath.length();

        // Append current node's value
        if (length != 0) {
            currentPath.append("->");
        }
        currentPath.append(node.val);

        // Check if it's a leaf node
        if (node.left == null && node.right == null) {
            paths.add(currentPath.toString());
        } else {
            // Continue with left and right children
            backtrack(node.left, currentPath, paths);
            backtrack(node.right, currentPath, paths);
        }

        // Backtrack: remove the current node from path
        currentPath.setLength(length);
    }
}
