package trees.narytree;
/*
Given the root of an n-ary tree, return the postorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)



Example 1:


Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]
Example 2:


Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]


Constraints:

The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The height of the n-ary tree is less than or equal to 1000.

 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class NaryTreePostorderTraversal {

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        for (Node child : node.children) {
            postorderHelper(child, result);
        }
        result.add(node.val);
    }

    // Iterative postorder traversal using two stacks
    public List<Integer> postorderIterative(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<Node> stack1 = new ArrayDeque<>();
        Deque<Node> stack2 = new ArrayDeque<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            stack2.push(node);
            for (Node child : node.children) {
                stack1.push(child);
            }
        }

        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }

        return result;
    }

    public static void main(String[] args) {
        // Example usage
        Node root = new Node(1);
        Node child1 = new Node(3);
        child1.children.add(new Node(5));
        child1.children.add(new Node(6));
        root.children.add(child1);
        root.children.add(new Node(2));
        root.children.add(new Node(4));

        NaryTreePostorderTraversal traversal = new NaryTreePostorderTraversal();
        List<Integer> result = traversal.postorder(root);
        System.out.println(result); // Output: [5, 6, 3, 2, 4, 1]
    }
}
