package trees.binarytree;
/*
You are given the root of a binary tree that consists of exactly 3 nodes: the root, its left child, and its right child.

Return true if the value of the root is equal to the sum of the values of its two children, or false otherwise.



Example 1:


Input: root = [10,4,6]
Output: true
Explanation: The values of the root, its left child, and its right child are 10, 4, and 6, respectively.
10 is equal to 4 + 6, so we return true.
Example 2:


Input: root = [5,3,1]
Output: false
Explanation: The values of the root, its left child, and its right child are 5, 3, and 1, respectively.
5 is not equal to 3 + 1, so we return false.


Constraints:

The tree consists only of the root, its left child, and its right child.
-100 <= Node.val <= 100
 */
public class RootEqualsSumeOfChildrens {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean checkTree(TreeNode root) {
        if (root == null || root.left == null || root.right == null) return false;
        return root.val == root.left.val + root.right.val;
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);

        RootEqualsSumeOfChildrens solution = new RootEqualsSumeOfChildrens();
        boolean result = solution.checkTree(root);
        System.out.println(result); // Output: true
    }
}
