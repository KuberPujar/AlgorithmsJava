package stacks.divideandconquer;
/*
Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.

It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.

A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.



Example 1:


Input: preorder = [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
Example 2:

Input: preorder = [1,3]
Output: [1,null,3]


Constraints:

1 <= preorder.length <= 100
1 <= preorder[i] <= 1000
All the values of preorder are unique.
 */


import java.util.*;

public class ConstructBSTFromPrOrderTraversalDC {
    // A global index to keep track of the current element in the preorder array.
    // This allows us to "consume" elements from the preorder array as we build the tree.
    private int preIndex = 0;

    /**
     * Constructs a Binary Search Tree from a preorder traversal array.
     * This is the public-facing method that initiates the process.
     *
     * @param preorder An array of integers representing the preorder traversal of a BST.
     * @return The root node of the constructed BST.
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        // Start the recursive construction. The initial bounds are Integer.MIN_VALUE
        // and Integer.MAX_VALUE as any node value will fall within this range.
        return buildTree(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * The recursive helper function that uses a divide and conquer approach.
     * It constructs a subtree based on the valid range of values [lower, upper].
     *
     * @param preorder The preorder traversal array.
     * @param lower The lower bound for node values in the current subtree.
     * @param upper The upper bound for node values in the current subtree.
     * @return The root node of the constructed subtree.
     */
    private TreeNode buildTree(int[] preorder, int lower, int upper) {
        // --- Base Cases for Recursion ---

        // 1. If we have used all elements in the preorder array, we are done.
        if (preIndex == preorder.length) {
            return null;
        }

        int rootVal = preorder[preIndex];

        // 2. If the current value is outside the valid range for this subtree,
        // it means this node belongs to a different branch (e.g., a right child of a
        // parent further up the tree). We return null to indicate no node is formed here.
        if (rootVal < lower || rootVal > upper) {
            return null;
        }

        // --- Divide and Conquer Step ---

        // The current value is valid for this subtree, so create the root.
        TreeNode root = new TreeNode(rootVal);
        // Consume the current element by advancing the preorder index.
        preIndex++;

        // Recursively build the left subtree.
        // For the left child, its value must be greater than the current lower bound
        // but less than its parent's value (the new upper bound).
        root.left = buildTree(preorder, lower, rootVal);

        // Recursively build the right subtree.
        // For the right child, its value must be greater than its parent's value
        // (the new lower bound) but less than the current upper bound.
        root.right = buildTree(preorder, rootVal, upper);

        return root;
    }

    public static void main(String[] args) {
        // --- Example 1 ---
        ConstructBSTFromPrOrderTraversalDC solution1 = new ConstructBSTFromPrOrderTraversalDC(); // New instance for each test
        int[] preorder1 = {8, 5, 1, 7, 10, 12};
        TreeNode root1 = solution1.bstFromPreorder(preorder1);
        System.out.println("Example 1 Input: " + Arrays.toString(preorder1));
        System.out.println("Output (Level Order): " + treeToString(root1)); // Expected: [8, 5, 10, 1, 7, null, 12]
        System.out.println("------------------------------------");

        // --- Example 2 ---
        ConstructBSTFromPrOrderTraversalDC solution2 = new ConstructBSTFromPrOrderTraversalDC(); // New instance for each test
        int[] preorder2 = {1, 3};
        TreeNode root2 = solution2.bstFromPreorder(preorder2);
        System.out.println("Example 2 Input: " + Arrays.toString(preorder2));
        System.out.println("Output (Level Order): " + treeToString(root2)); // Expected: [1, null, 3]
        System.out.println("------------------------------------");
    }

    /**
     * Helper function to convert a tree to a string using level order traversal,
     * matching the format in the problem description.
     * @param root The root of the tree.
     * @return A string representation of the tree.
     */
    public static String treeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        List<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                list.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                list.add("null");
            }
        }

        // Trim trailing nulls for a cleaner output
        int lastNonNull = list.size() - 1;
        while (lastNonNull >= 0 && list.get(lastNonNull).equals("null")) {
            lastNonNull--;
        }
        list = list.subList(0, lastNonNull + 1);

        return list.toString();
    }
}
