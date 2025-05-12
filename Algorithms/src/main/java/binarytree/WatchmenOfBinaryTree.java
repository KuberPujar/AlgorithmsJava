package binarytree;

import java.util.*;

/*
Watchmen of Binary Tree
Given HeyCoach land’s Binary Tree Society, we have to place watchmen to keep an eye on the whole Society, each watchman can keep an eye on itself, parent node and immediate children. Calculate minimum number of Watchmen required to keep an eye on all houses of the HeyCoach Land.

For example: Given the Binary Tree

       1
     /   \
   2       3
  /
 6
The minimum number of watchmen would be two which can be placed at node value '2' and '3'.

Input Format:
A single line that represents the value of the nodes and the value of '- 1' denotes NULL node.
Output Format:
Return a single integer representing the minimum number of watchmen required to keep an eye on the society.
Sample Input 1:
1 2 3 6 -1 -1 -1 -1 -1
Sample Output 1:
2
Explanation :
In the given binary tree (1 2 3 6 -1 -1 -1 -1 -1), placing a watchman at node '2' covers itself, node '6', and the root node '1'. Another watchman at node '3' covers itself and the root, resulting in a total of 2 watchmen.

Sample Input 2:
1 2 3 -1 -1 -1 -1
Sample Output 2:
1
Explanation :
Placing a watchman at the root node 1 covers the entire binary tree because the root node can monitor itself and its two children (2 and 3). Therefore, only 1 watchman is needed to monitor all the nodes in the tree.

Constraints:

0 <= N <= 10^4

0 <= data <= 10^3

Where 'N' denotes the total number of nodes and 'data' denotes the value of the node
Note: Function should return the result.
 */
public class WatchmenOfBinaryTree {
    // States:
    // 0: Node needs coverage (not covered by self or child watchman)
    // 1: Node has a watchman placed on it
    // 2: Node is covered by a watchman placed on a child (but has no watchman itself)
    private static final int NEEDS_COVERAGE = 0;
    private static final int HAS_WATCHMAN = 1;
    private static final int COVERED_BY_CHILD = 2;

    // Use an array to pass the count somewhat like pass-by-reference
    private int[] watchmenCount;

    /**
     * Recursive helper function (post-order traversal).
     * Determines the state of the subtree rooted at 'node' and updates the watchman count.
     *
     * @param node  The current node being processed.
     * @return The state of the 'node' (0, 1, or 2).
     */
    private int dfs(TreeNode node) {
        // Base case: Null nodes don't need cameras and are considered 'covered'
        // Returning state 2 prevents forcing the parent to place a camera unnecessarily.
        if (node == null) {
            return COVERED_BY_CHILD; // State 2
        }

        // Recursively find the state of left and right children
        int leftState = dfs(node.left);
        int rightState = dfs(node.right);

        // Decision Logic based on children's states:

        // Case 1: If either child NEEDS_COVERAGE (state 0),
        // we MUST place a watchman at the current node 'node'.
        if (leftState == NEEDS_COVERAGE || rightState == NEEDS_COVERAGE) {
            watchmenCount[0]++; // Increment the global count
            // This node now has a watchman, covering itself, parent, and children.
            return HAS_WATCHMAN; // State 1
        }

        // Case 2: If neither child needs coverage (i.e., not state 0),
        // check if either child HAS_WATCHMAN (state 1).
        // If yes, the current node 'node' is covered by that child's watchman.
        if (leftState == HAS_WATCHMAN || rightState == HAS_WATCHMAN) {
            // This node is covered, but doesn't have a watchman itself.
            return COVERED_BY_CHILD; // State 2
        }

        // Case 3: If we reach here, it means:
        // - Neither child needs coverage (both are state 1 or 2)
        // - Neither child has a watchman (both are state 2)
        // Therefore, both children must be COVERED_BY_CHILD (state 2).
        // The current node 'node' is NOT covered by its children.
        // It relies on its parent for coverage.
        return NEEDS_COVERAGE; // State 0
    }

    /**
     * Calculates the minimum number of watchmen required for the entire tree.
     *
     * @param root The root of the binary tree.
     * @return The minimum number of watchmen.
     */
    public int minWatchmen(TreeNode root) {
        // Handle empty tree case
        if (root == null) {
            return 0;
        }
        watchmenCount = new int[]{0}; // Initialize count holder

        // Perform DFS traversal starting from the root
        int rootState = dfs(root);

        // Final check: If the root node itself ended up needing coverage (state 0),
        // it means no watchman placed in its subtrees covers it. Since it has no
        // parent, we must place one additional watchman at the root.
        if (rootState == NEEDS_COVERAGE) {
            watchmenCount[0]++;
        }

        return watchmenCount[0];
    }

    /**
     * Builds a binary tree from level order input string.
     * -1 represents a null node.
     *
     * @param nodesStr Space-separated string of node values.
     * @return The root of the built binary tree.
     */
    public static TreeNode buildTree(String nodesStr) {
        if (nodesStr == null || nodesStr.trim().isEmpty() || nodesStr.trim().equals("-1")) {
            return null;
        }
        String[] nodeValues = nodesStr.trim().split("\\s+");


        TreeNode root = new TreeNode(Integer.parseInt(nodeValues[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < nodeValues.length) {
            TreeNode current = queue.poll();

            // Process left child
            if (i < nodeValues.length) {
                String leftVal = nodeValues[i++];
                if (!leftVal.equals("-1")) {
                    current.left = new TreeNode(Integer.parseInt(leftVal));
                    queue.add(current.left);
                }
            }

            // Process right child
            if (i < nodeValues.length) {
                String rightVal = nodeValues[i++];
                if (!rightVal.equals("-1")) {
                    current.right = new TreeNode(Integer.parseInt(rightVal));
                    queue.add(current.right);
                }
            }
        }
        return root;
    }

    // Main method for testing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter tree nodes (level order, -1 for null, space-separated):");
        // Example input: 5 3 9 2 4 6 -1 -1 -1 -1 -1 7 8 -1 -1 -1 -1
        // Example input: 1 2 3 6 -1 -1 -1 -1 -1
        // Example input: 1 2 3 -1 -1 -1 -1
        String input = scanner.nextLine();

        TreeNode root = buildTree(input);

        WatchmenOfBinaryTree sol = new WatchmenOfBinaryTree();
        int result = sol.minWatchmen(root);

        System.out.println("Minimum watchmen required: " + result);

        scanner.close();
    }
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
