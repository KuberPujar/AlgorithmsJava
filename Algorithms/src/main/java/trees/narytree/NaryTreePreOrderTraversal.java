package trees.narytree;
/*
Given the root of an n-ary tree, return the preorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)



Example 1:



Input: root = [1,null,3,2,4,null,5,6]
Output: [1,3,5,6,2,4]
Example 2:



Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]


Constraints:

The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The height of the n-ary tree is less than or equal to 1000.


Follow up: Recursive solution is trivial, could you do it iteratively?
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}


public class NaryTreePreOrderTraversal {

    // Iterative preorder traversal
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);
            // Push children in reverse order
            List<Node> children = node.children;
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
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

        NaryTreePreOrderTraversal traversal = new NaryTreePreOrderTraversal();
        List<Integer> result = traversal.preorder(root);
        System.out.println(result); // Output: [1, 3, 5, 6, 2, 4]
    }
}
