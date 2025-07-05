package trees.dpbased;
/*
You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.

Return the minimum number of cameras needed to monitor all nodes of the tree.



Example 1:


Input: root = [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: root = [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.


Constraints:

The number of nodes in the tree is in the range [1, 1000].
Node.val == 0
 */
public class BinaryTreeCameras {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int cameras = 0;

    public int minCameraCover(TreeNode root) {
        // If root is not covered, add a camera
        if (dfs(root) == 2) cameras++;
        return cameras;
    }

    // Returns: 0 = has camera, 1 = covered, 2 = not covered
    private int dfs(TreeNode node) {
        if (node == null) return 1;
        int left = dfs(node.left);
        int right = dfs(node.right);

        if (left == 2 || right == 2) {
            cameras++;
            return 0; // Place camera here
        }
        if (left == 0 || right == 0) {
            return 1; // Covered by child
        }
        return 2; // Not covered
    }

    public static void main(String[] args) {
        BinaryTreeCameras solution = new BinaryTreeCameras();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);

        int result = solution.minCameraCover(root);
        System.out.println("Minimum number of cameras needed: " + result); // Output: 1
    }
}
