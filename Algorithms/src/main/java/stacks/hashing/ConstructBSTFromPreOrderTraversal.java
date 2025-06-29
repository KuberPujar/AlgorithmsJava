package stacks.hashing;

import java.util.*;

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

public class ConstructBSTFromPreOrderTraversal {
    // Global index for the preorder array. This tracks the current root element.
    private int preIndex = 0;
    // The HashMap will store inorder values and their indices for quick lookups.
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    /**
     * Constructs a Binary Search Tree from a preorder traversal array.
     * This method orchestrates the construction process.
     *
     * @param preorder An array of integers representing the preorder traversal of a BST.
     * @return The root node of the constructed BST.
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        // Handle edge case of an empty or null input array.
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        // Step 1: Create the inorder traversal. For a BST, the inorder traversal
        // is the sorted version of its elements.
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);

        // Step 2: Use Hashing to create a map for O(1) lookups of inorder indices.
        // This map is essential for quickly finding the root in the inorder traversal,
        // which helps determine the size of the left and right subtrees.
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Step 3: Build the tree using a recursive helper. The recursion uses the
        // call stack to manage building the subtrees, fulfilling the "stack" requirement.
        return buildTreeHelper(preorder, 0, preorder.length - 1);
    }

    /**
     * A recursive helper function to construct the tree.
     *
     * @param preorder The preorder traversal array.
     * @param inStart  The starting index for the current subtree in the inorder traversal.
     * @param inEnd    The ending index for the current subtree in the inorder traversal.
     * @return The root node of the constructed subtree.
     */
    private TreeNode buildTreeHelper(int[] preorder, int inStart, int inEnd) {
        // Base case for the recursion: if the start index crosses the end index,
        // it means this subtree is empty.
        if (inStart > inEnd) {
            return null;
        }

        // The first element in the current preorder sequence is the root of the subtree.
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find the root's position in the inorder traversal using the hash map.
        // All elements to the left of this position in the inorder array belong to
        // the left subtree, and all elements to the right belong to the right subtree.
        int inIndex = inorderMap.get(rootVal);

        // Recursively build the left subtree.
        // It's crucial to build the left subtree first because the preIndex is advancing
        // through the preorder array, which lists left subtree nodes before right ones.
        root.left = buildTreeHelper(preorder, inStart, inIndex - 1);

        // Recursively build the right subtree.
        root.right = buildTreeHelper(preorder, inIndex + 1, inEnd);

        return root;
    }


    /**
     * Main class to test the solution.
     */
    public static void main(String[] args) {
        // Create an instance of the solution class.
        ConstructBSTFromPreOrderTraversal solution = new ConstructBSTFromPreOrderTraversal();

        // --- Example 1 ---
        int[] preorder1 = {8, 5, 1, 7, 10, 12};
        TreeNode root1 = solution.bstFromPreorder(preorder1);
        System.out.println("Example 1 Input: " + Arrays.toString(preorder1));
        System.out.println("Output (Level Order): " + treeToString(root1)); // Expected: [8, 5, 10, 1, 7, null, 12]
        System.out.println("------------------------------------");

        // --- Example 2 ---
        // We need a new Solution object because the previous one holds state (preIndex, map)
        ConstructBSTFromPreOrderTraversal solution2 = new ConstructBSTFromPreOrderTraversal();
        int[] preorder2 = {1, 3};
        TreeNode root2 = solution2.bstFromPreorder(preorder2);
        System.out.println("Example 2 Input: " + Arrays.toString(preorder2));
        System.out.println("Output (Level Order): " + treeToString(root2)); // Expected: [1, null, 3]
        System.out.println("------------------------------------");
    }

    /**
     * Helper function to convert a tree to a string using level order traversal,
     * matching the format in the problem description.
     *
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