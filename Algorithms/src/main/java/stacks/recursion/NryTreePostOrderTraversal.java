package stacks.recursion;

import java.util.ArrayList;
import java.util.List;

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


Follow up: Recursive solution is trivial, could you do it iteratively?
 */
// Definition for a Node.
// This class represents a node in an n-ary tree.
class Node {
    public int val; // Value of the node
    public List<Node> children; // List of child nodes

    // Default constructor
    public Node() {}

    // Constructor with value
    public Node(int _val) {
        val = _val;
    }

    // Constructor with value and children
    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class NryTreePostOrderTraversal {
    /**
     * Performs a postorder traversal of an n-ary tree recursively.
     * In postorder traversal, we visit all children of a node first, and then the node itself.
     * The results are collected in a list.
     *
     * @param root The root of the n-ary tree.
     * @return A list of node values in postorder traversal.
     */
    public List<Integer> postorder(Node root) {
        // Initialize an empty list to store the results of the traversal.
        List<Integer> result = new ArrayList<>();
        // Call the helper function to perform the recursive traversal.
        traverse(root, result);
        // Return the accumulated list of node values.
        return result;
    }

    /**
     * Helper function for recursive postorder traversal.
     * This function recursively visits children and then adds the current node's value to the list.
     *
     * @param node The current node being visited.
     * @param result The list to accumulate the node values.
     */
    private void traverse(Node node, List<Integer> result) {
        // Base case: If the current node is null, there's nothing to traverse.
        if (node == null) {
            return;
        }

        // Recursively visit all children.
        // For each child in the 'children' list of the current node,
        // call the 'traverse' function on that child.
        if (node.children != null) { // Ensure children list is not null
            for (Node child : node.children) {
                traverse(child, result);
            }
        }

        // After visiting all children, add the value of the current node to the result list.
        // This is the "postorder" part: node value is added after its children.
        result.add(node.val);
    }

    public static void main(String[] args) {
        // Example usage of the NryTreePostOrderTraversal class.
        // Create a sample n-ary tree:
        Node root = new Node(1);
        Node child1 = new Node(3);
        Node child2 = new Node(2);
        Node child3 = new Node(4);
        List<Node> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        children.add(child3);
        root.children = children;

        // Add children to child1
        child1.children = new ArrayList<>();
        child1.children.add(new Node(5));
        child1.children.add(new Node(6));

        // Create an instance of the traversal class and perform postorder traversal.
        NryTreePostOrderTraversal traversal = new NryTreePostOrderTraversal();
        List<Integer> result = traversal.postorder(root);

        // Print the result of the postorder traversal.
        System.out.println(result); // Output: [5, 6, 3, 2, 4, 1]
    }
}
