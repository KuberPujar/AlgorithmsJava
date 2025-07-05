package trees.dpbased;
/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.



Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.


Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
 */
public class BinaryTreeMaximumPathSum {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    // Returns the maximum path sum starting from this node and going down
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, dfs(node.left));   // Ignore negative paths
        int right = Math.max(0, dfs(node.right));
        // Update maxSum with the best path passing through this node
        maxSum = Math.max(maxSum, node.val + left + right);
        // Return the best path sum starting from this node
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int result = solution.maxPathSum(root);
        System.out.println("Maximum Path Sum: " + result); // Output: 42
    }
}
