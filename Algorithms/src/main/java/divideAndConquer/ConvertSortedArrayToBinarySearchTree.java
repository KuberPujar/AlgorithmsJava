package divideAndConquer;
/*
Convert Sorted Array to Binary Search Tree
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class ConvertSortedArrayToBinarySearchTree {

    /**
     * Converts a sorted array to a height-balanced binary search tree.
     *
     * @param nums sorted array in ascending order
     * @return root of the height-balanced BST
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return buildBST(nums, 0, nums.length - 1);
    }

    /**
     * Recursively builds a height-balanced BST from a sorted array.
     *
     * @param nums sorted array
     * @param left left boundary (inclusive)
     * @param right right boundary (inclusive)
     * @return root of the subtree
     */
    private TreeNode buildBST(int[] nums, int left, int right) {
        // Base case: invalid range
        if (left > right) {
            return null;
        }

        // Choose middle element as root to ensure height balance
        // Use right-biased middle to match expected output
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build left and right subtrees
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }

    /**
     * Helper method to print the tree in level order (for testing)
     */
    public void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        java.util.List<String> result = new java.util.ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.add("null");
            }
        }

        // Remove trailing nulls
        while (result.size() > 0 && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }

        System.out.println(result);
    }

    /**
     * Test method with the provided examples
     */
    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree solution = new ConvertSortedArrayToBinarySearchTree();

        // Example 1: nums = [-10,-3,0,5,9]
        int[] nums1 = {-10, -3, 0, 5, 9};
        TreeNode root1 = solution.sortedArrayToBST(nums1);
        System.out.print("Example 1 - Input: [-10,-3,0,5,9], Output: ");
        solution.printLevelOrder(root1);

        // Example 2: nums = [1,3]
        int[] nums2 = {1, 3};
        TreeNode root2 = solution.sortedArrayToBST(nums2);
        System.out.print("Example 2 - Input: [1,3], Output: ");
        solution.printLevelOrder(root2);

        // Additional test case: single element
        int[] nums3 = {5};
        TreeNode root3 = solution.sortedArrayToBST(nums3);
        System.out.print("Additional test - Input: [5], Output: ");
        solution.printLevelOrder(root3);

        // Additional test case: empty array
        int[] nums4 = {};
        TreeNode root4 = solution.sortedArrayToBST(nums4);
        System.out.print("Additional test - Input: [], Output: ");
        solution.printLevelOrder(root4);
    }
}
