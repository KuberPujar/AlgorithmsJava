package hashtable.treebased;

import java.util.HashMap;
import java.util.Map;

/*
Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.


Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest leaf-nodes of the tree.
Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.
Example 2:

Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree, and it's the lca of itself.
Example 3:

Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest leaf node in the tree is 2, the lca of one node is itself.


Constraints:

The number of nodes in the tree will be in the range [1, 1000].
0 <= Node.val <= 1000
The values of the nodes in the tree are unique.


Note: This question is the same as 865: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 */
public class LowestCommonAnscestorOfDeepestLeaves {
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static class Result {
        TreeNode node;
        int depth;
        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    //approach 1: DFS to find the deepest leaves and their LCA
    public TreeNode lcaDeepestLeaves1(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        if (node == null) return new Result(null, 0);
        Result left = dfs(node.left);
        Result right = dfs(node.right);
        if (left.depth == right.depth) {
            return new Result(node, left.depth + 1);
        }
        return left.depth > right.depth ?
                new Result(left.node, left.depth + 1) :
                new Result(right.node, right.depth + 1);
    }

    //approach 2: DFS to find the depth of each node and then find the LCA of the deepest leaves
    private final Map<TreeNode, Integer> depthMap = new HashMap<>();

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return lca(root);
    }

    // Fill depthMap with the depth of each node
    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        depthMap.put(node, depth);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    // Find the LCA of the deepest leaves
    private TreeNode lca(TreeNode node) {
        if (node == null) return null;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        if (leftDepth == rightDepth) return node;
        return leftDepth > rightDepth ? lca(node.left) : lca(node.right);
    }

    private int maxDepth(TreeNode node) {
        if (node == null) return depthMap.getOrDefault(node, 0) - 1;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Math.max(depthMap.get(node), Math.max(left, right));
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        LowestCommonAnscestorOfDeepestLeaves solution = new LowestCommonAnscestorOfDeepestLeaves();
        TreeNode lca = solution.lcaDeepestLeaves(root);
        System.out.println(lca.val); // Output: 2
        // You can also test the second approach
        TreeNode lca2 = solution.lcaDeepestLeaves1(root);
        System.out.println(lca2.val); // Output: 2
    }
}
