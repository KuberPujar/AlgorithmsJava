package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
Zigzagging through the Binary Tree
At HeyCoach, a dedicated mentor aims to assist students,
 who are organized in a tree-like structure where each student is represented
 as a node in a binary tree. The mentor can move between nodes in a zigzag pattern,
  meaning:

If the mentor moves to the left child of a node, the next move must be to the
 right child of the subsequent node. Similarly, if the mentor moves to the
 right child first, the next move must be to the left child. The mentor’s
 goal is to maximize the number of students he can help in a single zigzag
 traversal. Your task is to determine the maximum number of nodes the mentor
 can visit following the zigzag pattern.

Input Format:

root: The root of the binary tree where each node contains an integer value.
 If the tree is empty, the root is null.
Output Format:

Return the maximum number of nodes the mentor can visit in a zigzag traversal.
Example 1
Input
root = [3,9,20,N, N,15,7]

      3
     / \
    9   20
       / \
      15  7

Output:
3
Explanation :
Ram is travelling in the path:- 3->20->15 20 is the right child of 3 15 is the left child of 20

Thus, the total nodes travelled: 3

Example 2:
Input:
root = []
Output:

0
Explnation:
Since the tree is empty, there are no nodes to visit.

Constraints:
The number of nodes in the tree is in the range [0, 200000].

-1000 <= Node.val <= 1000

Note:The function should return the result. The driver code will handle printing the output.
 */
public class ZigzaggingThroughBinaryTree {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int result = longestZigZag(root);
        System.out.println(result); // Output: 3

        // Input: 1 2 3 N 5 N N 6 7 N N N 8 N
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.left.right.left = new TreeNode(6);
        root1.left.right.left.right = new TreeNode(7);
        root1.left.right.left.right.right = new TreeNode(8);
        int result1 = longestZigZag(root1);
        System.out.println(result1); // Output: 3
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static int longestZigZag(TreeNode root) {
        if (root == null) return 0;

        int max = 0;
        Deque<Object[]> stack = new ArrayDeque<>();
        Map<TreeNode, int[]> nodeMap = new HashMap<>();

        stack.push(new Object[]{root, false});

        while (!stack.isEmpty()) {
            Object[] entry = stack.pop();
            TreeNode node = (TreeNode) entry[0];
            boolean visited = (Boolean) entry[1];
            if (!visited) {
                stack.push(new Object[]{node, true});
                if (node.right != null) {
                    stack.push(new Object[]{node.right, false});
                }
                if (node.left != null) {
                    stack.push(new Object[]{node.left, false});
                }
            } else {
                int currentLeft = 1;
                if (node.left != null) {
                    int[] leftChild = nodeMap.get(node.left);
                    currentLeft = 1 + leftChild[1];
                }
                int currentRight = 1;
                if (node.right != null) {
                    int[] rightChild = nodeMap.get(node.right);
                    currentRight = 1 + rightChild[0];
                }
                nodeMap.put(node, new int[]{currentLeft, currentRight});
                max = Math.max(max, Math.max(currentLeft, currentRight));
            }
        }

        return max;
    }
}
