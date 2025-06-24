package stacks.monotonikstack;
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

import java.util.*;

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
     * Builds maximum binary tree using monotonic decreasing stack.
     * Time: O(n), Space: O(n)
     *
     * Key insight: Use a monotonic decreasing stack to maintain nodes.
     * When we encounter a larger element, it becomes the parent of smaller elements.
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();

        for (int num : nums) {
            TreeNode current = new TreeNode(num);

            // While stack is not empty and current value is greater than stack top
            // The current node will be the parent of nodes in stack
            while (!stack.isEmpty() && num > stack.peek().val) {
                // Pop smaller elements and make them left child of current node
                current.left = stack.pop();
            }

            // If stack is not empty, current node becomes right child of stack top
            if (!stack.isEmpty()) {
                stack.peek().right = current;
            }

            stack.push(current);
        }

        // The bottom element of stack is the root (maximum element processed first)
        return stack.get(0);
    }

    /**
     * Alternative recursive approach for comparison (less efficient but intuitive)
     * Time: O(n^2) in worst case, Space: O(n) for recursion stack
     */
    public TreeNode constructMaximumBinaryTreeRecursive(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) return null;

        // Find maximum element in current range
        int maxIdx = findMaxIndex(nums, left, right);

        // Create root with maximum value
        TreeNode root = new TreeNode(nums[maxIdx]);

        // Recursively build left and right subtrees
        root.left = buildTree(nums, left, maxIdx - 1);
        root.right = buildTree(nums, maxIdx + 1, right);

        return root;
    }

    private int findMaxIndex(int[] nums, int left, int right) {
        int maxIdx = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    /**
     * Helper method to print tree in level order for testing
     */
    public void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<String> result = new ArrayList<>();

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
        while (!result.isEmpty() && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }

        System.out.println(result);
    }

    /**
     * Helper method to validate tree structure
     */
    public boolean isValidMaximumBinaryTree(TreeNode root, int[] nums, int left, int right) {
        if (root == null) return left > right;

        // Find the index of root value in the array
        int rootIdx = -1;
        for (int i = left; i <= right; i++) {
            if (nums[i] == root.val) {
                rootIdx = i;
                break;
            }
        }

        if (rootIdx == -1) return false;

        // Check if this is the maximum in current range
        for (int i = left; i <= right; i++) {
            if (nums[i] > root.val) return false;
        }

        // Recursively validate left and right subtrees
        return isValidMaximumBinaryTree(root.left, nums, left, rootIdx - 1) &&
                isValidMaximumBinaryTree(root.right, nums, rootIdx + 1, right);
    }

    public static void main(String[] args) {
        MaximumBinaryTree solution = new MaximumBinaryTree();

        // Example 1: nums = [3,2,1,6,0,5]
        int[] nums1 = {3, 2, 1, 6, 0, 5};
        TreeNode result1 = solution.constructMaximumBinaryTree(nums1);
        System.out.println("Example 1 - Input: " + Arrays.toString(nums1));
        System.out.print("Output (Monotonic Stack): ");
        solution.printLevelOrder(result1);
        System.out.println("Valid tree: " + solution.isValidMaximumBinaryTree(result1, nums1, 0, nums1.length - 1));

        // Compare with recursive approach
        TreeNode result1Recursive = solution.constructMaximumBinaryTreeRecursive(nums1);
        System.out.print("Output (Recursive): ");
        solution.printLevelOrder(result1Recursive);

        System.out.println();

        // Example 2: nums = [3,2,1]
        int[] nums2 = {3, 2, 1};
        TreeNode result2 = solution.constructMaximumBinaryTree(nums2);
        System.out.println("Example 2 - Input: " + Arrays.toString(nums2));
        System.out.print("Output (Monotonic Stack): ");
        solution.printLevelOrder(result2);
        System.out.println("Valid tree: " + solution.isValidMaximumBinaryTree(result2, nums2, 0, nums2.length - 1));

        System.out.println();

        // Example 3: Single element
        int[] nums3 = {1};
        TreeNode result3 = solution.constructMaximumBinaryTree(nums3);
        System.out.println("Example 3 - Input: " + Arrays.toString(nums3));
        System.out.print("Output: ");
        solution.printLevelOrder(result3);

        System.out.println();

        // Example 4: Increasing sequence
        int[] nums4 = {1, 2, 3, 4, 5};
        TreeNode result4 = solution.constructMaximumBinaryTree(nums4);
        System.out.println("Example 4 - Input: " + Arrays.toString(nums4));
        System.out.print("Output: ");
        solution.printLevelOrder(result4);

        System.out.println();

        // Example 5: Decreasing sequence
        int[] nums5 = {5, 4, 3, 2, 1};
        TreeNode result5 = solution.constructMaximumBinaryTree(nums5);
        System.out.println("Example 5 - Input: " + Arrays.toString(nums5));
        System.out.print("Output: ");
        solution.printLevelOrder(result5);
    }
}
