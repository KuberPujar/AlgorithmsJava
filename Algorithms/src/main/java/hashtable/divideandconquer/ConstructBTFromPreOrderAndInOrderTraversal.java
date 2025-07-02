package hashtable.divideandconquer;

import java.util.HashMap;
import java.util.Map;

/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConstructBTFromPreOrderAndInOrderTraversal {
    private Map<Integer, Integer> inorderMap;
    private int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Create a hash map to store value to index mapping for inorder array
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int inStart, int inEnd) {
        // Base case
        if (inStart > inEnd) {
            return null;
        }

        // The current root is the next element in preorder traversal
        int rootValue = preorder[preIndex++];
        TreeNode root = new TreeNode(rootValue);

        // If this node has no children, return it
        if (inStart == inEnd) {
            return root;
        }

        // Find the index of this root in inorder traversal
        int inIndex = inorderMap.get(rootValue);

        // Recursively build left and right subtrees
        root.left = buildTreeHelper(preorder, inStart, inIndex - 1);
        root.right = buildTreeHelper(preorder, inIndex + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        ConstructBTFromPreOrderAndInOrderTraversal solution = new ConstructBTFromPreOrderAndInOrderTraversal();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = solution.buildTree(preorder, inorder);
        // You can add code here to print or verify the constructed tree
        System.out.println("Tree constructed successfully.");
        // Example of printing the root value
        System.out.println("Root value: " + root.val);
        // You can implement a method to print the tree structure if needed
        // For example, you can implement a method to print the tree in-order
        printInOrder(root);

    }

    private static void printInOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }
}
