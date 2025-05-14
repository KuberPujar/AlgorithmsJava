package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/*
Sort the Nodes in BST
Given a Binary Search Tree with the values of all the nodes pairwise distinct. Return the sorted array of all the Nodes present in the Binary Search Tree.

Binary Search Tree is a node-based binary tree data structure which has the following properties: The left subtree of a node contains only nodes with keys lesser than the node’s key. The right subtree of a node contains only nodes with keys greater than the node’s key. The left and right subtree each must also be a binary search tree.

Input Format:
t, number of test cases.
string s, input tree in the form of binary search tree
Output Format:
print sorted array for nodes in BST.
Sample Input 1:
1
2 1 3
Sample Output 1:
1 2 3

Explanation:
Sorted array for nodes in BST (2,1,3) --> (1,2,3)

Sample Input 2:
1
10 5 15 2 7 N 20
Sample Output 2:
2 5 7 10 15 20

Explanation:
Sorted array for nodes in BST (10, 5, 15, 2, 7, N, 20) --> (2, 5, 7, 10, 15, 20)

Constraints:
0 <= t <= 100
0 <= N <= 100000, number of nodes in BST.
Note:The function should return the result. The driver code will handle printing the output.
 */
public class SortNodesInBST {

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        ArrayList<Integer> sortedNodes= sortNodesInBST(root);
        for (int node : sortedNodes) {
            System.out.print(node + " ");
        }
    }

    public static ArrayList<Integer> sortNodesInBST(TreeNode root) {
        List<Integer> nodesList = new ArrayList<>();
        inOrderTraversal(root, nodesList);
        return new ArrayList<>(nodesList);
    }

    private static void inOrderTraversal(TreeNode node, List<Integer> nodesList) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, nodesList);
        nodesList.add(node.val);
        inOrderTraversal(node.right, nodesList);
    }

    static class TreeNode {
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
