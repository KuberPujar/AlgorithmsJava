package graphalgorithms;
/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:

Input:

[1,2,3,4,5,null,null,null,null,null,null]

Output:

[4,2,5,1,3]
    1
   / \
  2   3
 / \
4   5
Example 2:

Input:

[10,5,20,3,7,null,25,null,null,null,null,null,null]
Output:

[3,5,7,10,20,25]

    10
    /  \
   5    20
  / \     \
 3   7     25
Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */

import java.util.*;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class InOrderTraversalOfBT {
    /**
     * Approach 1: Recursive Inorder Traversal
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (recursion stack)
     */
    public static List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        // Traverse left subtree
        inorderHelper(node.left, result);

        // Process current node
        result.add(node.val);

        // Traverse right subtree
        inorderHelper(node.right, result);
    }

    /**
     * Approach 2: Iterative Inorder Traversal using Stack
     * Time Complexity: O(n)
     * Space Complexity: O(h) for the stack
     */
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Current must be null at this point
            current = stack.pop();
            result.add(current.val);

            // We have visited the node and its left subtree, now visit right subtree
            current = current.right;
        }

        return result;
    }

    /**
     * Approach 3: Morris Traversal (Space-efficient)
     * Time Complexity: O(n)
     * Space Complexity: O(1) - no extra space for recursion or stack
     */
    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                // No left subtree, process current node and go right
                result.add(current.val);
                current = current.right;
            } else {
                // Find the inorder predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Make current as right child of its inorder predecessor
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Revert the changes made to restore the original tree
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }

        return result;
    }

    /**
     * Helper method to build a binary tree from array representation
     * Uses level-order construction where null represents missing nodes
     */
    public static TreeNode buildTreeFromArray(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            // Add left child
            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;

            // Add right child
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    /**
     * Helper method to print the tree structure for visualization
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        System.out.println("Tree structure:");
        printTreeHelper(root, "", true);
    }

    private static void printTreeHelper(TreeNode node, String prefix, boolean isLast) {
        if (node == null) return;

        System.out.println(prefix + (isLast ? "└── " : "├── ") + node.val);

        if (node.left != null || node.right != null) {
            if (node.right != null) {
                printTreeHelper(node.right, prefix + (isLast ? "    " : "│   "), node.left == null);
            }
            if (node.left != null) {
                printTreeHelper(node.left, prefix + (isLast ? "    " : "│   "), true);
            }
        }
    }

    /**
     * Main method to test all approaches
     */
    public static void main(String[] args) {
        System.out.println("=== Binary Tree Inorder Traversal ===\n");

        // Test Case 1: Example 1 from problem
        System.out.println("Test Case 1:");
        Integer[] arr1 = {1, 2, 3, 4, 5, null, null};
        TreeNode root1 = buildTreeFromArray(arr1);
        System.out.println("Input: " + Arrays.toString(arr1));
        printTree(root1);

        List<Integer> result1Recursive = inorderTraversalRecursive(root1);
        List<Integer> result1Iterative = inorderTraversalIterative(root1);
        List<Integer> result1Morris = inorderTraversalMorris(root1);

        System.out.println("Recursive result: " + result1Recursive);
        System.out.println("Iterative result: " + result1Iterative);
        System.out.println("Morris result: " + result1Morris);
        System.out.println("Expected: [4, 2, 5, 1, 3]");
        System.out.println();

        // Test Case 2: Example 2 from problem
        System.out.println("Test Case 2:");
        Integer[] arr2 = {10, 5, 20, 3, 7, null, 25};
        TreeNode root2 = buildTreeFromArray(arr2);
        System.out.println("Input: " + Arrays.toString(arr2));
        printTree(root2);

        List<Integer> result2Recursive = inorderTraversalRecursive(root2);
        List<Integer> result2Iterative = inorderTraversalIterative(root2);
        List<Integer> result2Morris = inorderTraversalMorris(root2);

        System.out.println("Recursive result: " + result2Recursive);
        System.out.println("Iterative result: " + result2Iterative);
        System.out.println("Morris result: " + result2Morris);
        System.out.println("Expected: [3, 5, 7, 10, 20, 25]");
        System.out.println();

        // Test Case 3: Single node
        System.out.println("Test Case 3: Single node");
        Integer[] arr3 = {42};
        TreeNode root3 = buildTreeFromArray(arr3);
        System.out.println("Input: " + Arrays.toString(arr3));
        printTree(root3);

        List<Integer> result3 = inorderTraversalRecursive(root3);
        System.out.println("Result: " + result3);
        System.out.println("Expected: [42]");
        System.out.println();

        // Test Case 4: Empty tree
        System.out.println("Test Case 4: Empty tree");
        TreeNode root4 = null;
        List<Integer> result4 = inorderTraversalRecursive(root4);
        System.out.println("Result: " + result4);
        System.out.println("Expected: []");
        System.out.println();

        // Test Case 5: Left skewed tree
        System.out.println("Test Case 5: Left skewed tree");
        Integer[] arr5 = {1, 2, null, 3, null, null, null};
        TreeNode root5 = buildTreeFromArray(arr5);
        System.out.println("Input: " + Arrays.toString(arr5));
        printTree(root5);

        List<Integer> result5 = inorderTraversalRecursive(root5);
        System.out.println("Result: " + result5);
        System.out.println("Expected: [3, 2, 1]");
        System.out.println();

        // Performance comparison
        System.out.println("=== Performance Analysis ===");
        System.out.println("Recursive: O(n) time, O(h) space - Simple and intuitive");
        System.out.println("Iterative: O(n) time, O(h) space - Uses explicit stack");
        System.out.println("Morris: O(n) time, O(1) space - Most space-efficient");
        System.out.println("Where n = number of nodes, h = height of tree");
    }
}

/*
 * Algorithm Explanation:
 *
 * Inorder Traversal Order: Left -> Root -> Right
 *
 * 1. Recursive Approach:
 *    - Recursively traverse left subtree
 *    - Process current node
 *    - Recursively traverse right subtree
 *    - Simple but uses system stack
 *
 * 2. Iterative Approach:
 *    - Use explicit stack to simulate recursion
 *    - Go leftmost, then process node, then go right
 *    - More control over stack usage
 *
 * 3. Morris Traversal:
 *    - Uses threading to avoid extra space
 *    - Temporarily modifies tree structure
 *    - Restores original structure during traversal
 *    - Most space-efficient: O(1) space
 *
 * Use Cases:
 * - Recursive: Most readable, good for small trees
 * - Iterative: When you need to avoid recursion stack overflow
 * - Morris: When memory is extremely constrained
 */
