package trees.stringmatching;
/*
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.



Example 1:


Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
Example 2:


Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false


Constraints:

The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-104 <= root.val <= 104
-104 <= subRoot.val <= 104
 */
public class SubTreeOfAnotherTree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String rootStr = serialize(root);
        String subRootStr = serialize(subRoot);
        return rootStr.contains(subRootStr);
    }

    private String serialize(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(node, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(",#");
            return;
        }
        sb.append(",").append(node.val);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    public static void main(String[] args) {
        SubTreeOfAnotherTree solution = new SubTreeOfAnotherTree();

        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);

        TreeNode subRoot1 = new TreeNode(4);
        subRoot1.left = new TreeNode(1);
        subRoot1.right = new TreeNode(2);

        System.out.println(solution.isSubtree(root1, subRoot1)); // Output: true

        // Example 2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(2);
        root2.left.right.right = new TreeNode(0);

        TreeNode subRoot2 = new TreeNode(4);
        subRoot2.left = new TreeNode(1);
        subRoot2.right = new TreeNode(2);

        System.out.println(solution.isSubtree(root2, subRoot2)); // Output: false
        // Additional test cases can be added here

    }
}
