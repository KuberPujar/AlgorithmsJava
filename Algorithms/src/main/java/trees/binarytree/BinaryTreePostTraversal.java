package trees.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
Given the root of a binary tree, return the postorder traversal of its nodes' values.



Example 1:

Input: root = [1,null,2,3]

Output: [3,2,1]

Explanation:



Example 2:

Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]

Output: [4,6,7,5,2,9,8,3,1]

Explanation:



Example 3:

Input: root = []

Output: []

Example 4:

Input: root = [1]

Output: [1]



Constraints:

The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100


Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    // Recursive postorder traversal
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.val);
    }

    // Iterative postorder traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode lastVisited = null;
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right != null && lastVisited != peek.right) {
                    curr = peek.right;
                } else {
                    result.add(peek.val);
                    lastVisited = stack.pop();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        BinaryTreePostTraversal solution = new BinaryTreePostTraversal();
        List<Integer> resultRecursive = solution.postorderTraversalRecursive(root);
        List<Integer> resultIterative = solution.postorderTraversal(root);
        System.out.println("Postorder Traversal (Recursive): " + resultRecursive); // Output: [3, 2, 1]
        System.out.println("Postorder Traversal (Iterative): " + resultIterative); // Output: [3, 2, 1]
        // You can add more test cases to verify the implementation
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        List<Integer> result2 = solution.postorderTraversal(root2);

        System.out.println("Postorder Traversal of another tree: " + result2); // Output: [4, 5, 2, 3, 1]
        // You can add more test cases to verify the implementation
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(4);
        root3.left.right = new TreeNode(5);
        root3.right.left = new TreeNode(6);
        root3.right.right = new TreeNode(7);
        List<Integer> result3 = solution.postorderTraversal(root3);
        System.out.println("Postorder Traversal of a larger tree: " + result3); // Output: [4, 5, 2, 6, 7, 3, 1]
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        root4.right.left = new TreeNode(6);
        root4.right.right = new TreeNode(7);
        List<Integer> result4 = solution.postorderTraversal(root4);
        System.out.println("Postorder Traversal of another larger tree: " + result4); // Output: [4, 5, 2, 6, 7, 3, 1]
    }
}
