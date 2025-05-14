package BinarySearchTree;

import com.sun.source.tree.Tree;

/*
Finding the Size of the Largest Binary Search Tree (BST)
Given a binary tree. Find the size of its largest subtree that is a Binary Search Tree. Note: Here Size is equal to the number of nodes in the subtree.

Input Format:

The input consists of a binary tree provided in level-order traversal format.
The nodes are represented by integers, and N denotes a null node (or no child).
Output Format:

Print a single integer representing the size of the largest subtree that is also a BST.
Example 1:
Input:
    1
   /  \
  4    4
 /      \
6         8
Output:
2

Explanation:
There's no sub-tree with size greater than 1 which forms a BST. All the leaf Nodes are the BSTs with size equal to 1.

Example 2:
Input: 6 6 3 N 2 9 3 N 8 8 2
        6
    /       \
   6         3
    \      /   \
     2    9     3
      \  /  \
      8 8    2
Output:
2

Explanation:
The following sub-tree is a BST of size 2

   2
/    \
N      8
Your Task:
You don't need to read input or print anything. Your task is to complete the function largestBst() that takes the root node of the Binary Tree as its input and returns the size of the largest subtree which is also the BST. If the complete Binary Tree is a BST, return the size of the complete Binary Tree.

Expected Time Complexity:
O(N).
Expected Auxiliary Space:
O(Height of the BST).
Constraints:
1 ≤ Number of nodes ≤ 10^5

1 ≤ Data of a node ≤ 10^6

Note:The function should return the result. The driver code will handle printing the output.
 */
public class FindSizeOfLargestBinaryTree {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        int largestBSTSize = largestBst(root);
        System.out.println("Size of the largest BST: " + largestBSTSize);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(4);
        root2.left.left = new TreeNode(6);
        root2.right.right = new TreeNode(8);
        int largestBSTSize2 = largestBst(root2);
        System.out.println("Size of the largest BST: " + largestBSTSize2);

        TreeNode root3 = new TreeNode(6);
        root3.left = new TreeNode(6);
        root3.right = new TreeNode(3);
        root3.left.right = new TreeNode(2);
        root3.right.left = new TreeNode(9);
        root3.right.right = new TreeNode(3);
        root3.left.right.right = new TreeNode(8);
        root3.left.right.right.left = new TreeNode(8);
        root3.left.right.right.right = new TreeNode(2);
        int largestBSTSize3 = largestBst(root3);
        System.out.println("Size of the largest BST: " + largestBSTSize3);
    }

    public static int largestBst(TreeNode root) {
        return findLargestBST(root).size;
    }

    private static Result findLargestBST(TreeNode node) {
        if (node == null) {
            return new Result(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Result leftResult = findLargestBST(node.left);
        Result rightResult = findLargestBST(node.right);

        if (leftResult.isBST && rightResult.isBST && leftResult.max < node.val && rightResult.min > node.val) {
            int size = leftResult.size + rightResult.size + 1;
            int min = Math.min(node.val, leftResult.min);
            int max = Math.max(node.val, rightResult.max);
            return new Result(true, size, min, max);
        } else {
            return new Result(false, Math.max(leftResult.size, rightResult.size), 0, 0);
        }
    }

    private static class Result {
        boolean isBST;
        int size;
        int min;
        int max;

        Result(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
}
