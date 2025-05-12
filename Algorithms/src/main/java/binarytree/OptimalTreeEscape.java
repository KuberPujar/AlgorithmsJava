package binarytree;
/*
Optimal Tree Escape
Objective: Determine who escapes first from a binary tree by either always moving left (Ram) or always moving right (Shyam).

Given:

A binary tree where each node represents a position in a unique training course, with two children nodes (left and right) except for leaf nodes, which have no children.
Two characters, Ram and Shyam, start from the root of the binary tree. Ram always moves to the left child, and Shyam always moves to the right child. Their goal is to reach any leaf node to escape the tree.
Input Format:

root: A binary tree where each node has a value. Leaf nodes are represented by -1 (no children). The structure of the binary tree is provided as a list in level-order traversal.
Output Format:

If both Ram and Shyam escape the tree simultaneously, return 0.
If Ram escapes first, return -1.
If Shyam escapes first, return 1.
Examples:

Input: root = [1]
Output: 0

Explanation: Both start and end at the root node (the only node), escaping simultaneously.

Input: root = [10, 5, -1, -1, 15, 12, -1, -1, -1]
Output: 1

Explanation: Shyam escapes the tree first by always moving to the right child.

Input: root = [3, 9, 20, -1, -1, 15, 7, -1, -1, -1, -1]
Output: -1

Explanation: Ram escapes the tree first by always moving to the left child.

Solution Approach:

The solutions provided traverse the binary tree based on Ram's and Shyam's strategies, tracking the depth of traversal to determine who reaches a leaf node first. The depth comparison between the leftmost and rightmost paths effectively decides the output according to the specified rules.

Constraints:

The number of nodes in the tree is in the range [0, 200000].
Node values are within [-1000, 1000].
Note:The function should return the result. The driver code will handle printing the output.
 */
public class OptimalTreeEscape {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int result = escapeTime(root);
        System.out.println(result); // Output: -1
    }

    public static int escapeTime(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ramDepth = findDepth(root, true); // Ram always moves left
        int shyamDepth = findDepth(root, false); // Shyam always moves right

        if (ramDepth == shyamDepth) {
            return 0;
        } else if (ramDepth < shyamDepth) {
            return -1;
        } else {
            return 1;
        }
    }

    private static int findDepth(TreeNode node, boolean isRam) {
        if (node == null) {
            return 0;
        }
        if (isRam) {
            return 1 + findDepth(node.left, isRam);
        } else {
            return 1 + findDepth(node.right, isRam);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
