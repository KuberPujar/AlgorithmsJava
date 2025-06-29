package stacks.divideandconquer;

import java.util.*;

/*
You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

Create a root node whose value is the maximum value in nums.
Recursively build the left subtree on the subarray prefix to the left of the maximum value.
Recursively build the right subtree on the subarray suffix to the right of the maximum value.
Return the maximum binary tree built from nums.



Example 1:


Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]
Explanation: The recursive calls are as follow:
- The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
    - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
        - Empty array, so no child.
        - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
    - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.
Example 2:


Input: nums = [3,2,1]
Output: [3,null,2,null,1]


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000
All integers in nums are unique.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaximumBinaryTree {
    /**
     * Constructs a Maximum Binary Tree from an array of unique integers.
     * This is the entry point for the construction process.
     *
     * @param nums An array of unique integers.
     * @return The root node of the constructed Maximum Binary Tree.
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // Handle edge case of an empty or null input array.
        if (nums == null || nums.length == 0) {
            return null;
        }
        // Start the recursive construction with the entire array range.
        return buildTree(nums, 0, nums.length - 1);
    }

    /**
     * A recursive helper function that implements the divide and conquer algorithm.
     * It builds a tree from the subarray defined by the start and end indices.
     *
     * @param nums The original array of numbers.
     * @param start The starting index of the subarray for the current recursive call.
     * @param end The ending index of the subarray for the current recursive call.
     * @return The root of the Maximum Binary Tree for the given subarray.
     */
    private TreeNode buildTree(int[] nums, int start, int end) {
        // Base case: If the start index is greater than the end index,
        // it means the subarray is empty, so we return null.
        if (start > end) {
            return null;
        }

        // --- Divide Step ---
        // Find the index of the maximum value in the current subarray [start, end].
        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Create a new root node with the maximum value.
        TreeNode root = new TreeNode(nums[maxIndex]);

        // --- Conquer Step ---
        // Recursively build the left subtree using the prefix of the subarray
        // to the left of the maximum value.
        root.left = buildTree(nums, start, maxIndex - 1);

        // Recursively build the right subtree using the suffix of the subarray
        // to the right of the maximum value.
        root.right = buildTree(nums, maxIndex + 1, end);

        // Return the constructed root node for this subarray.
        return root;
    }

    public static void main(String[] args) {
        MaximumBinaryTree solution = new MaximumBinaryTree();

        // --- Example 1 ---
        int[] nums1 = {3, 2, 1, 6, 0, 5};
        TreeNode root1 = solution.constructMaximumBinaryTree(nums1);
        System.out.println("Example 1 Input: " + Arrays.toString(nums1));
        System.out.println("Output (Level Order): " + treeToString(root1)); // Expected: [6, 3, 5, null, 2, 0, null, null, 1]
        System.out.println("------------------------------------");

        // --- Example 2 ---
        int[] nums2 = {3, 2, 1};
        TreeNode root2 = solution.constructMaximumBinaryTree(nums2);
        System.out.println("Example 2 Input: " + Arrays.toString(nums2));
        System.out.println("Output (Level Order): " + treeToString(root2)); // Expected: [3, null, 2, null, 1]
        System.out.println("------------------------------------");
    }

    /**
     * Helper function to convert a tree to a string using level order traversal,
     * which helps in visualizing and verifying the tree structure.
     * @param root The root of the tree.
     * @return A string representation of the tree.
     */
    public static String treeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        List<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean levelHasNodes = false;
            List<String> levelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    levelNodes.add(String.valueOf(node.val));
                    queue.offer(node.left);
                    queue.offer(node.right);
                    if(node.left != null || node.right != null) levelHasNodes = true;
                } else {
                    levelNodes.add("null");
                }
            }
            list.addAll(levelNodes);
            if(!levelHasNodes) break;
        }

        // Trim trailing nulls for a cleaner output that matches the problem's format.
        int lastNonNull = list.size() - 1;
        while (lastNonNull >= 0 && list.get(lastNonNull).equals("null")) {
            lastNonNull--;
        }
        list = list.subList(0, lastNonNull + 1);

        return list.toString();
    }
}
