package BinarySearchTree;

import java.util.*;

/*
Minimum Risk Path in a BST
Given the root of a Binary Search Tree (BST), calculate its risk, defined as the minimum absolute difference between the values of any two nodes in the tree. The goal is to compute this minimum absolute difference and return it.

Input Format

The first line contains an integer n denoting the number of nodes in the BST.
The second line contains n space-separated integers representing the elements of the BST in level-order insertion order. If a node is null, it is represented by -1.
Output Format

Print a single integer, which represents the risk of the BST (the minimum absolute difference).
Examples

Example 1:

Input:

7 4 9 3 5 -1 -1 -1 -1 -1 -1
Output:

1
Explanation:
The BST constructed from the level-order input is:

       7
      / \
     4   9
    / \
   3   5
The in-order traversal gives: [3, 4, 5, 7, 9].
The minimum absolute difference is 1 (between 3 and 4, or 4 and 5).

Constraints:

( 1 <= n <= 100 )
( 0 <= Node.val <= 10^5 )
The tree will always be a valid BST.
Notes:Function should calculate and return the result; the driver code will handle printing the output.
 */
public class MinimumRiskPathInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static int calculateRisk(int[] arr) {
        TreeNode root = buildTree(arr);
        List<Integer> inOrder = new ArrayList<>();
        inOrderTraversal(root, inOrder);
        if (inOrder.size() < 2) {
            return 0;
        }
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < inOrder.size(); i++) {
            int diff = Math.abs(inOrder.get(i) - inOrder.get(i - 1));
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }

    private static TreeNode buildTree(int[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == -1) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < arr.length) {
            TreeNode current = queue.poll();
            if (index < arr.length) {
                int leftVal = arr[index++];
                if (leftVal != -1) {
                    current.left = new TreeNode(leftVal);
                    queue.add(current.left);
                }
            }
            if (index < arr.length) {
                int rightVal = arr[index++];
                if (rightVal != -1) {
                    current.right = new TreeNode(rightVal);
                    queue.add(current.right);
                }
            }
        }
        return root;
    }

    private static void inOrderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inOrderTraversal(root.left, list);
        list.add(root.val);
        inOrderTraversal(root.right, list);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(calculateRisk(arr));
    }
}
