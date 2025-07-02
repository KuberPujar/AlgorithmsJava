package hashtable.treebased;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.



Example 1:


Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.
Example 2:


Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.


Constraints:

1 <= descriptions.length <= 104
descriptions[i].length == 3
1 <= parenti, childi <= 105
0 <= isLefti <= 1
The binary tree described by descriptions is valid.
 */
public class CreateBinaryFromDescriptions {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] desc : descriptions) {
            int parentVal = desc[0], childVal = desc[1], isLeft = desc[2];
            nodeMap.putIfAbsent(parentVal, new TreeNode(parentVal));
            nodeMap.putIfAbsent(childVal, new TreeNode(childVal));
            if (isLeft == 1) {
                nodeMap.get(parentVal).left = nodeMap.get(childVal);
            } else {
                nodeMap.get(parentVal).right = nodeMap.get(childVal);
            }
            children.add(childVal);
        }

        // The root is the node that is never a child
        for (int val : nodeMap.keySet()) {
            if (!children.contains(val)) {
                return nodeMap.get(val);
            }
        }
        return null; // Should not happen for valid input
    }

    public static void main(String[] args) {
        CreateBinaryFromDescriptions solution = new CreateBinaryFromDescriptions();
        int[][] descriptions = {
            {20, 15, 1},
            {20, 17, 0},
            {50, 20, 1},
            {50, 80, 0},
            {80, 19, 1}
        };
        TreeNode root = solution.createBinaryTree(descriptions);
        // You can add code here to print or verify the structure of the tree
        System.out.println("Root value: " + root.val); // Should print 50
        System.out.println("Left child of root: " + root.left.val); // Should print 20
        System.out.println("Right child of root: " + root.right.val); // Should print 80
        System.out.println("Left child of 20: " + root.left.left.val); // Should print 15
        System.out.println("Right child of 20: " + root.left.right.val); // Should print 17
        System.out.println("Left child of 80: " + root.right.left.val); // Should print 19
        System.out.println("Right child of 80: " + (root.right.right == null ? "null" : root.right.right.val)); // Should print null
        // Additional checks can be added to verify the entire tree structure
        System.out.println("Tree created successfully.");

    }
}
