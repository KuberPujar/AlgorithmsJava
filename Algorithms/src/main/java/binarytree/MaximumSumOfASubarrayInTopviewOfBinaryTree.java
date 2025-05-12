package binarytree;

import java.util.*;

/*
Maximum Sum of a Subarray in Topview of Binary Tree
Given a binary tree, find the maximum sum of a subarray in its top view. The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.

A subarray in the top view is defined as the sum of values of nodes visible from the top at any specific horizontal distance.

Input:

The function takes a pointer to the root of the binary tree root (1 <= nodes <= 10^4).
Output:

Return an integer representing the maximum sum of a subarray in the top view.

Example:

Input:
      1
     /\
    2   3
   / \  / \
  4   5 6   7

[1 2 3 4 5 6 7 -1 -1 -1 -1 -1 -1 -1 -1]

Output:
17

Explanation:
The top view is [4, 2, 1, 3, 7], and the maximum sum subarray is [4, 2, 1, 3, 7] with a sum of 17.

Note:

Nodes of the binary tree have values in the range [-1000, 1000].
The binary tree will not be empty.
Ensure that your solution has a time complexity of O(N), where N is the number of nodes in the binary tree.
Return the answer in the function and don't print it.
Constraints:
The number of nodes in the binary tree is in the range 1 < nodes < 10^4

The binary tree will not be empty.

Note:The function should return the result. The driver code will handle printing the output.
 */
public class MaximumSumOfASubarrayInTopviewOfBinaryTree {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int result = maxSumSubarrayInTopView(root);
        System.out.println(result); // Output: 17
    }
    public static int maxSumSubarrayInTopView(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Step 1: Get the top view nodes
        Map<Integer, Integer> topViewMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;

            if (!topViewMap.containsKey(hd)) {
                topViewMap.put(hd, node.val);
            }

            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }

        // Step 2: Extract values in order of horizontal distance
        List<Integer> topViewValues = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : topViewMap.entrySet()) {
            topViewValues.add(entry.getValue());
        }

        // Step 3: Find the maximum subarray sum using Kadane's algorithm
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int num : topViewValues) {
            currentSum = Math.max(num, currentSum + num);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
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
