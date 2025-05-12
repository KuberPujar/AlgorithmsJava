package BinarySearchTree;

import java.util.*;

/*
Three Numbers Product - Input is a BST
Given the root of a binary search tree and an integer k, return true if there exist three elements in the BST such that their product is equal to k, or false otherwise.

Input Format:

The first line contains an integer n, representing the number of nodes in the Binary Search Tree (BST).
The second line contains n space-separated integers representing the values of the nodes in the BST (given in level-order traversal).
The third line contains an integer k, representing the target product.
Output Format:

Return true if there exist three elements in the BST such that their product equals k and otherwise false.
Sample Input 1:

7
5 3 6 2 4 -1 7
60
Sample Output 1:

true
Explanation:

The BST has nodes with values [5, 3, 6, 2, 4, null, 7]. There exist three nodes with values 3, 4, and 5 whose product is 60, so the output is true.

Sample Input 2:

7
5 3 6 2 4 -1 7
28
Sample Output 2:

false
Explanation:

For the same BST, there are no three nodes whose product is 28, so the output is false.

Constraints:

The number of nodes n in the BST is in the range [1, 10^4].

−10^4 <= Node.val <= 10^4

−10^5 <= Node.val <= 10^5

Note:The function should return the result. The driver code will handle printing the output.
 */
public class ThreeNumberProductInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static boolean calculateRisk(int n, int[] arr, int k) {
        TreeNode root = buildTree(arr);
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                long product = (long) nums.get(i) * nums.get(j);
                if (product == 0) {
                    if (k == 0 && j < size - 1) {
                        return true;
                    }
                } else {
                    if (k % product != 0) {
                        continue;
                    }
                    int zVal = (int) (k / product);
                    int index = Collections.binarySearch(nums, zVal);
                    if (index >= 0 && index > j) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void inOrder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }

    private static TreeNode buildTree(int[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int idx = 1;
        while (!queue.isEmpty() && idx < arr.length) {
            TreeNode node = queue.poll();
            if (idx < arr.length) {
                int leftVal = arr[idx++];
                if (leftVal != -1) {
                    node.left = new TreeNode(leftVal);
                    queue.offer(node.left);
                }
            }
            if (idx < arr.length) {
                int rightVal = arr[idx++];
                if (rightVal != -1) {
                    node.right = new TreeNode(rightVal);
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(calculateRisk(n, arr, k));
    }
}
