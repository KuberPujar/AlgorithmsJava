package trees.dfsbased;

import java.util.Stack;
/*
Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].



Example 1:


Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
Example 2:


Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.


Constraints:

The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 105
1 <= low <= high <= 105
All Node.val are unique.
 */
public class RangeSumOfBST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) continue;
            if (node.val >= low && node.val <= high) {
                sum += node.val;
            }
            stack.push(node.left);
            stack.push(node.right);
        }
        return sum;
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        RangeSumOfBST solution = new RangeSumOfBST();
        int low = 7, high = 15;
        int result = solution.rangeSumBST(root, low, high);
        System.out.println("Range Sum of BST: " + result); // Output: 32
    }
}
