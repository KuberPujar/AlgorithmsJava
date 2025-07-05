package trees.binarysearchtree;
/*
Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:


Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
Example 2:

Input: root = [0,null,1]
Output: [1,null,1]


Constraints:

The number of nodes in the tree is in the range [1, 100].
0 <= Node.val <= 100
All the values in the tree are unique.


Note: This question is the same as 538: https://leetcode.com/problems/convert-bst-to-greater-tree/
 */
public class BSTToGreaterSumTree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    // Reverse inorder traversal: right -> root -> left
    private void traverse(TreeNode node) {
        if (node == null) return;
        traverse(node.right);
        sum += node.val;
        node.val = sum;
        traverse(node.left);
    }

    public static void main(String[] args) {
        // Example usage
        BSTToGreaterSumTree converter = new BSTToGreaterSumTree();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);

        TreeNode greaterTreeRoot = converter.convertBST(root);
        // You can add code here to print or verify the structure of the Greater Sum Tree
        System.out.println("Root of the Greater Sum Tree: " + greaterTreeRoot.val);
    }
}
