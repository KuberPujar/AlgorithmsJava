package trees.divideandconquer;
/*
Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.



Example 1:


Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:


Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
 */
public class ConvertedSortedArrayToBinarySearchTree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = build(nums, left, mid - 1);
        node.right = build(nums, mid + 1, right);
        return node;
    }

    public static void main(String[] args) {
        ConvertedSortedArrayToBinarySearchTree converter = new ConvertedSortedArrayToBinarySearchTree();
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = converter.sortedArrayToBST(nums);
        // You can add code here to print or verify the structure of the tree
        // For example, you can implement a method to print the tree in-order
        // or pre-order.
        // Example: printInOrder(root);
        // printInOrder(root);
        printTree(root);
    }

    private static void printTree(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printTree(node.left);
        printTree(node.right);
    }
}
