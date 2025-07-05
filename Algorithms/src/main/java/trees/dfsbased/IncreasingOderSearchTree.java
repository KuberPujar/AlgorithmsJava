package trees.dfsbased;
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
public class IncreasingOderSearchTree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    private TreeNode prev = null;
    private TreeNode newRoot = null;

    public TreeNode increasingBST(TreeNode root) {
        inorder(root);
        return newRoot;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev == null) {
            newRoot = node;
        } else {
            prev.right = node;
        }
        node.left = null;
        prev = node;
        inorder(node.right);
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(8);
        root.left.left.left = new TreeNode(1);
        root.right.right.left = new TreeNode(7);
        root.right.right.right = new TreeNode(9);

        IncreasingOderSearchTree solution = new IncreasingOderSearchTree();
        TreeNode result = solution.increasingBST(root);

        // Print the result tree in order
        printInOrder(result); // Should print 1, 2, 3, 4, 5, 6, 7, 8, 9
    }

    private static void printInOrder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printInOrder(node.right); // Only right child should be present
    }
}
