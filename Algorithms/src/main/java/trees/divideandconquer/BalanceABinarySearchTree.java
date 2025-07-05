package trees.divideandconquer;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.



Example 1:


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:


Input: root = [2,1,3]
Output: [2,1,3]


Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 105
 */
public class BalanceABinarySearchTree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        inorder(root, nodes);
        return buildBalancedBST(nodes, 0, nodes.size() - 1);
    }

    private void inorder(TreeNode node, List<Integer> nodes) {
        if (node == null) return;
        inorder(node.left, nodes);
        nodes.add(node.val);
        inorder(node.right, nodes);
    }

    private TreeNode buildBalancedBST(List<Integer> nodes, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nodes.get(mid));
        node.left = buildBalancedBST(nodes, left, mid - 1);
        node.right = buildBalancedBST(nodes, mid + 1, right);
        return node;
    }

    public static void main(String[] args) {
        BalanceABinarySearchTree balancer = new BalanceABinarySearchTree();
        // Example tree: [1,null,2,null,3,null,4,null,null]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        TreeNode balancedRoot = balancer.balanceBST(root);
        // You can add code here to print or verify the structure of the balanced tree
        // For example, you can implement a method to print the tree in-order
        // or pre-order.
        // Example: printInOrder(balancedRoot);
        printTree(balancedRoot);
    }

    private static void printTree(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printTree(node.left);
        printTree(node.right);
    }
}
