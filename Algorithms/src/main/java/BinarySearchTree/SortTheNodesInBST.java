package BinarySearchTree;

import java.util.*;

/*
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
public class SortTheNodesInBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine(); // Consume the newline after reading t

        for (int testCase = 0; testCase < t; testCase++) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println();
                continue;
            }
            String[] parts = input.split("\\s+");
            TreeNode root = buildTree(parts);
            List<Integer> result = new ArrayList<>();
            inorder(root, result);
            printList(result);
        }
    }

    private static TreeNode buildTree(String[] parts) {
        if (parts.length == 0 || parts[0].equals("N")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int index = 1;
        while (index < parts.length && !queue.isEmpty()) {
            TreeNode current = queue.poll();

            // Process left child
            if (index < parts.length && !parts[index].equals("N")) {
                current.left = new TreeNode(Integer.parseInt(parts[index]));
                queue.add(current.left);
            }
            index++;

            // Process right child
            if (index < parts.length && !parts[index].equals("N")) {
                current.right = new TreeNode(Integer.parseInt(parts[index]));
                queue.add(current.right);
            }
            index++;
        }
        return root;
    }

    private static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private static void printList(List<Integer> list) {
        if (list.isEmpty()) {
            System.out.println();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
