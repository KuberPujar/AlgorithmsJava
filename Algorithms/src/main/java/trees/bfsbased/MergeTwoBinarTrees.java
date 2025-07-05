package trees.bfsbased;

import java.util.LinkedList;
import java.util.Queue;

/*
ou are given two binary trees root1 and root2.

Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.

Return the merged tree.

Note: The merging process must start from the root nodes of both trees.



Example 1:


Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
Output: [3,4,5,5,4,null,7]
Example 2:

Input: root1 = [1], root2 = [1,2]
Output: [2,2]


Constraints:

The number of nodes in both trees is in the range [0, 2000].
-104 <= Node.val <= 104
 */
public class MergeTwoBinarTrees {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        TreeNode mergedRoot = new TreeNode(root1.val + root2.val);
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue.add(mergedRoot);
        queue1.add(root1);
        queue2.add(root2);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            // Left children
            TreeNode left1 = node1.left;
            TreeNode left2 = node2.left;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    node.left = new TreeNode(left1.val + left2.val);
                    queue.add(node.left);
                    queue1.add(left1);
                    queue2.add(left2);
                } else if (left1 != null) {
                    node.left = left1;
                } else {
                    node.left = left2;
                }
            }

            // Right children
            TreeNode right1 = node1.right;
            TreeNode right2 = node2.right;
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    node.right = new TreeNode(right1.val + right2.val);
                    queue.add(node.right);
                    queue1.add(right1);
                    queue2.add(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }
        return mergedRoot;
    }

    public static void main(String[] args) {
        // Example usage
        MergeTwoBinarTrees merger = new MergeTwoBinarTrees();

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);

        TreeNode mergedRoot = merger.mergeTrees(root1, root2);
        // Output the merged tree or perform further operations
        // You can implement a method to print the tree or verify the result
        // For example, you can print the merged tree in pre-order traversal
        printTree(mergedRoot);
    }

    public static void printTree(TreeNode node) {
        if (node == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(node.val + " ");
        printTree(node.left);
        printTree(node.right);
    }
}
