package stacks.binarytree;

import java.util.*;

/*
Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only one right child.



Example 1:


Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
Example 2:


Input: root = [5,1,7]
Output: [1,null,5,null,7]


Constraints:

The number of nodes in the given tree will be in the range [1, 100].
0 <= Node.val <= 1000
 */
public class IncreasingOrderSearch {
    /**
     * Rearranges BST to right-skewed tree using stack-based in-order traversal
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree (for stack)
     */

    static class TreeNode {
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

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;

        // Stack to perform in-order traversal
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> inorderValues = new ArrayList<>();

        // Perform in-order traversal using stack
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process current node
            current = stack.pop();
            inorderValues.add(current.val);

            // Move to right subtree
            current = current.right;
        }

        // Build right-skewed tree from in-order values
        return buildRightSkewedTree(inorderValues);
    }

    /**
     * Alternative approach: Rearrange nodes in-place using stack
     */
    public TreeNode increasingBSTInPlace(TreeNode root) {
        if (root == null) return null;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode dummy = new TreeNode(0);
        TreeNode current = dummy;
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            // Process current node
            node = stack.pop();

            // Remove left child and attach to right-skewed structure
            node.left = null;
            current.right = node;
            current = node;

            // Move to right subtree
            node = node.right;
        }

        return dummy.right;
    }

    /**
     * Helper method to build right-skewed tree from sorted values
     */
    private TreeNode buildRightSkewedTree(List<Integer> values) {
        if (values.isEmpty()) return null;

        TreeNode root = new TreeNode(values.get(0));
        TreeNode current = root;

        for (int i = 1; i < values.size(); i++) {
            current.right = new TreeNode(values.get(i));
            current = current.right;
        }

        return root;
    }

    /**
     * Helper method to print tree structure for testing
     */
    public void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.add("null");
            }
        }

        // Remove trailing nulls
        while (!result.isEmpty() && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }

        System.out.println(result);
    }

    /**
     * Test method with examples
     */
    public static void main(String[] args) {
        IncreasingOrderSearch solution = new IncreasingOrderSearch();

        // Example 1: [5,3,6,2,4,null,8,1,null,null,null,7,9]
        System.out.println("Example 1:");
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(8);
        root1.left.left.left = new TreeNode(1);
        root1.right.right.left = new TreeNode(7);
        root1.right.right.right = new TreeNode(9);

        System.out.print("Input: ");
        solution.printTree(root1);

        TreeNode result1 = solution.increasingBST(root1);
        System.out.print("Output: ");
        solution.printTree(result1);

        // Example 2: [5,1,7]
        System.out.println("\nExample 2:");
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(7);

        System.out.print("Input: ");
        solution.printTree(root2);

        TreeNode result2 = solution.increasingBST(root2);
        System.out.print("Output: ");
        solution.printTree(result2);

        // Test in-place approach
        System.out.println("\nTesting in-place approach:");
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(7);

        TreeNode result3 = solution.increasingBSTInPlace(root3);
        System.out.print("In-place result: ");
        solution.printTree(result3);
    }
}
