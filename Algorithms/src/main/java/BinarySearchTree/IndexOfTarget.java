package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/*

 */
public class IndexOfTarget {

   private static class Node{
        int val;
        Node left, right;

        Node(int item) {
            val = item;
            left = right = null;
        }
   }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String[] elementsStr = scanner.nextLine().split(" ");
        List<Integer> elements = new ArrayList<>();
        for (String s : elementsStr) {
            elements.add(Integer.parseInt(s));
        }

        int target = scanner.nextInt();

        // Build BST using iterative insertion
        Node root = null;
        for (int val : elements) {
            root = insertIterative(root, val);
        }

        // Perform in-order traversal iteratively
        List<Integer> inOrderList = new ArrayList<>();
        inOrderTraversalIterative(root, inOrderList);

        // Find the index of the target
        int index = inOrderList.indexOf(target);
        if (index != -1) {
            System.out.println(index + 1);
        } else {
            System.out.println(-1);
        }
    }

    private static Node insertIterative(Node root, int val) {
        Node newNode = new Node(val);
        if (root == null) {
            return newNode;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (val < parent.val) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return root;
    }

    private static void inOrderTraversalIterative(Node root, List<Integer> list) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(current.val);
            current = current.right;
        }
    }
}
