package stacks.recursion;
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

import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
// This class represents a node in an n-ary tree.
class Node1 {
    public int val; // Value of the node
    public List<Node1> children; // List of child nodes

    // Default constructor
    public Node1() {}

    // Constructor with value
    public Node1(int _val) {
        val = _val;
    }

    // Constructor with value and children
    public Node1(int _val, List<Node1> _children) {
        val = _val;
        children = _children;
    }
};

class NryTreePreOrderTraversal {
    /**
     * Performs a preorder traversal of an n-ary tree recursively.
     * In preorder traversal, we visit the current node first, and then recursively visit its children.
     * The results are collected in a list.
     *
     * @param root The root of the n-ary tree.
     * @return A list of node values in preorder traversal.
     */
    public List<Integer> preorder(Node1 root) {
        // Initialize an empty list to store the results of the traversal.
        List<Integer> result = new ArrayList<>();
        // Call the helper function to perform the recursive traversal.
        traverse(root, result);
        // Return the accumulated list of node values.
        return result;
    }

    /**
     * Helper function for recursive preorder traversal.
     * This function first adds the current node's value to the list, and then
     * recursively visits its children.
     *
     * @param node The current node being visited.
     * @param result The list to accumulate the node values.
     */
    private void traverse(Node1 node, List<Integer> result) {
        // Base case: If the current node is null, there's nothing to traverse.
        if (node == null) {
            return;
        }

        // 1. Visit the current node: Add its value to the result list.
        // This is the "preorder" part: node value is added before its children.
        result.add(node.val);

        // 2. Recursively visit all children.
        // Ensure the children list is not null before iterating.
        if (node.children != null) {
            // Iterate through each child in the 'children' list of the current node.
            // For each child, recursively call the 'traverse' function.
            for (Node1 child : node.children) {
                traverse(child, result);
            }
        }
    }
}
