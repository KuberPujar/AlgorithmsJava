package stacks.binarytree;

import java.util.*;

/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.



Example 1:

Input: root = [1,null,2,3]

Output: [1,3,2]

Explanation:



Example 2:

Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]

Output: [4,2,6,5,7,1,3,9,8]

Explanation:



Example 3:

Input: root = []

Output: []

Example 4:

Input: root = [1]

Output: [1]



Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100


Follow up: Recursive solution is trivial, could you do it iteratively?
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
public class BinaryInorderTraversal {
    /**
     * Iterative inorder traversal using stack
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree
     */
    public List<Integer> inorderTraversal(TreeNode root) {
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
     * Alternative approach using a single while loop with explicit state tracking
     */
    public List<Integer> inorderTraversalAlternative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            // If left child exists and hasn't been processed, go left
            if (node.left != null) {
                stack.push(node.left);
                node.left = null; // Mark as processed to avoid infinite loop
            } else {
                // Process current node
                node = stack.pop();
                result.add(node.val);

                // If right child exists, push it
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
        }

        return result;
    }

    /**
     * Morris Inorder Traversal (O(1) space) - Bonus approach without stack
     */
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                // Find inorder predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Make current as right child of its inorder predecessor
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Revert the changes made
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }

        return result;
    }

    /**
     * Recursive approach for comparison
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderHelper(node.left, result);
            result.add(node.val);
            inorderHelper(node.right, result);
        }
    }

    /**
     * Helper method to build tree from array representation
     */
    public TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode node = queue.poll();

            if (i < arr.length && arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                queue.offer(node.left);
            }
            i++;

            if (i < arr.length && arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

    /**
     * Helper method to print tree structure
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
     * Test method with all examples
     */
    public static void main(String[] args) {
        BinaryInorderTraversal solution = new BinaryInorderTraversal();

        // Example 1: [1,null,2,3]
        System.out.println("Example 1:");
        TreeNode root1 = solution.buildTree(new Integer[]{1, null, 2, 3});
        System.out.print("Input: ");
        solution.printTree(root1);
        System.out.println("Iterative Output: " + solution.inorderTraversal(root1));

        // Rebuild tree for alternative method (since it modifies the tree)
        root1 = solution.buildTree(new Integer[]{1, null, 2, 3});
        System.out.println("Alternative Output: " + solution.inorderTraversalAlternative(root1));

        // Example 2: [1,2,3,4,5,null,8,null,null,6,7,9]
        System.out.println("\nExample 2:");
        TreeNode root2 = solution.buildTree(new Integer[]{1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9});
        System.out.print("Input: ");
        solution.printTree(root2);
        System.out.println("Iterative Output: " + solution.inorderTraversal(root2));

        // Example 3: []
        System.out.println("\nExample 3:");
        TreeNode root3 = solution.buildTree(new Integer[]{});
        System.out.print("Input: ");
        solution.printTree(root3);
        System.out.println("Iterative Output: " + solution.inorderTraversal(root3));

        // Example 4: [1]
        System.out.println("\nExample 4:");
        TreeNode root4 = solution.buildTree(new Integer[]{1});
        System.out.print("Input: ");
        solution.printTree(root4);
        System.out.println("Iterative Output: " + solution.inorderTraversal(root4));

        // Compare with recursive approach
        System.out.println("Recursive Output: " + solution.inorderTraversalRecursive(root4));

        // Demonstrate Morris Traversal (O(1) space)
        root4 = solution.buildTree(new Integer[]{1});
        System.out.println("Morris Traversal Output: " + solution.inorderTraversalMorris(root4));

        // Performance test with larger tree
        System.out.println("\nPerformance Test:");
        TreeNode largeTree = solution.buildTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        System.out.print("Large tree: ");
        solution.printTree(largeTree);

        long start = System.nanoTime();
        List<Integer> iterativeResult = solution.inorderTraversal(largeTree);
        long iterativeTime = System.nanoTime() - start;

        // Rebuild for recursive test
        largeTree = solution.buildTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        start = System.nanoTime();
        List<Integer> recursiveResult = solution.inorderTraversalRecursive(largeTree);
        long recursiveTime = System.nanoTime() - start;

        System.out.println("Iterative result: " + iterativeResult + " (Time: " + iterativeTime + " ns)");
        System.out.println("Recursive result: " + recursiveResult + " (Time: " + recursiveTime + " ns)");
        System.out.println("Results match: " + iterativeResult.equals(recursiveResult));
    }
}
