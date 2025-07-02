package hashtable.treebased;

import java.util.HashMap;
import java.util.Map;

/*
Given the root of a binary tree, the depth of each node is the shortest distance to the root.

Return the smallest subtree such that it contains all the deepest nodes in the original tree.

A node is called the deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest nodes of the tree.
Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.
Example 2:

Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree.
Example 3:

Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.


Constraints:

The number of nodes in the tree will be in the range [1, 500].
0 <= Node.val <= 500
The values of the nodes in the tree are unique.


Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 */
public class SmallestSubTreeWithAllTheDeepestNodes {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) { this.val = val; }
    }

    private final Map<TreeNode, Integer> depthMap = new HashMap<>();
    private int maxDepth = 0;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        fillDepthMap(root, 0);
        maxDepth = depthMap.values().stream().max(Integer::compareTo).orElse(0);
        return findLCA(root);
    }

    // Fill the depth map with DFS
    private void fillDepthMap(TreeNode node, int depth) {
        if (node == null) return;
        depthMap.put(node, depth);
        fillDepthMap(node.left, depth + 1);
        fillDepthMap(node.right, depth + 1);
    }

    // Find the LCA of all deepest nodes
    private TreeNode findLCA(TreeNode node) {
        if (node == null) return null;
        int depth = depthMap.get(node);
        if (depth == maxDepth) return node;
        TreeNode left = findLCA(node.left);
        TreeNode right = findLCA(node.right);
        if (left != null && right != null) return node;
        return left != null ? left : right;
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

        SmallestSubTreeWithAllTheDeepestNodes solution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode result = solution.subtreeWithAllDeepest(root);
        System.out.println(result.val); // Should print the value of the smallest subtree with all deepest nodes
        // Expected output: 2
        // You can add more test cases to verify the solution
        // Example: Testing with a single node tree
        TreeNode singleNodeRoot = new TreeNode(1);
        SmallestSubTreeWithAllTheDeepestNodes singleNodeSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode singleNodeResult = singleNodeSolution.subtreeWithAllDeepest(singleNodeRoot);
        System.out.println(singleNodeResult.val); // Should print 1, as it's the only node and thus the deepest
        // Example: Testing with a tree where all nodes are at the same depth
        TreeNode balancedRoot = new TreeNode(10);
        balancedRoot.left = new TreeNode(5);
        balancedRoot.right = new TreeNode(15);
        balancedRoot.left.left = new TreeNode(3);
        balancedRoot.left.right = new TreeNode(7);
        balancedRoot.right.left = new TreeNode(12);
        balancedRoot.right.right = new TreeNode(18);
        SmallestSubTreeWithAllTheDeepestNodes balancedSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode balancedResult = balancedSolution.subtreeWithAllDeepest(balancedRoot);
        System.out.println(balancedResult.val); // Should print 10, as it contains all deepest nodes (3, 7, 12, 18)
        // Example: Testing with a tree where the deepest nodes are not at the same level
        TreeNode unevenRoot = new TreeNode(20);
        unevenRoot.left = new TreeNode(15);
        unevenRoot.right = new TreeNode(25);
        unevenRoot.left.left = new TreeNode(10);
        unevenRoot.left.right = new TreeNode(18);
        unevenRoot.right.right = new TreeNode(30);
        unevenRoot.left.left.left = new TreeNode(5);
        unevenRoot.left.left.right = new TreeNode(12);
        SmallestSubTreeWithAllTheDeepestNodes unevenSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode unevenResult = unevenSolution.subtreeWithAllDeepest(unevenRoot);
        System.out.println(unevenResult.val); // Should print 15, as it contains the deepest nodes (5, 12, 18, 30)
        // Example: Testing with a tree where the root is the deepest node
        TreeNode rootDeepest = new TreeNode(100);
        rootDeepest.left = new TreeNode(50);
        rootDeepest.right = new TreeNode(150);
        rootDeepest.left.left = new TreeNode(25);
        rootDeepest.left.right = new TreeNode(75);
        rootDeepest.right.left = new TreeNode(125);
        rootDeepest.right.right = new TreeNode(175);
        SmallestSubTreeWithAllTheDeepestNodes rootDeepestSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode rootDeepestResult = rootDeepestSolution.subtreeWithAllDeepest(rootDeepest);
        System.out.println(rootDeepestResult.val); // Should print 100, as it is the root and contains all deepest nodes
        // Example: Testing with a tree where all nodes are on the left side
        TreeNode leftHeavyRoot = new TreeNode(30);
        leftHeavyRoot.left = new TreeNode(20);
        leftHeavyRoot.left.left = new TreeNode(10);
        leftHeavyRoot.left.left.left = new TreeNode(5);
        SmallestSubTreeWithAllTheDeepestNodes leftHeavySolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode leftHeavyResult = leftHeavySolution.subtreeWithAllDeepest(leftHeavyRoot);
        System.out.println(leftHeavyResult.val); // Should print 5, as it is the deepest node and the only one in its subtree
        // Example: Testing with a tree where all nodes are on the right side
        TreeNode rightHeavyRoot = new TreeNode(40);
        rightHeavyRoot.right = new TreeNode(50);
        rightHeavyRoot.right.right = new TreeNode(60);
        rightHeavyRoot.right.right.right = new TreeNode(70);
        SmallestSubTreeWithAllTheDeepestNodes rightHeavySolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode rightHeavyResult = rightHeavySolution.subtreeWithAllDeepest(rightHeavyRoot);
        System.out.println(rightHeavyResult.val); // Should print 70, as it is the deepest node and the only one in its subtree
        // Example: Testing with a tree where the deepest nodes are at different levels
        TreeNode mixedRoot = new TreeNode(80);
        mixedRoot.left = new TreeNode(60);
        mixedRoot.right = new TreeNode(100);
        mixedRoot.left.left = new TreeNode(50);
        mixedRoot.left.right = new TreeNode(70);
        mixedRoot.right.left = new TreeNode(90);
        mixedRoot.right.right = new TreeNode(110);
        mixedRoot.left.left.left = new TreeNode(40);
        mixedRoot.left.left.right = new TreeNode(55);
        SmallestSubTreeWithAllTheDeepestNodes mixedSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode mixedResult = mixedSolution.subtreeWithAllDeepest(mixedRoot);
        System.out.println(mixedResult.val); // Should print 60, as it contains the deepest nodes (40, 55, 70, 90, 110)
        // Example: Testing with a tree where the deepest nodes are all at the same level
        TreeNode sameLevelRoot = new TreeNode(90);
        sameLevelRoot.left = new TreeNode(80);
        sameLevelRoot.right = new TreeNode(100);
        sameLevelRoot.left.left = new TreeNode(70);
        sameLevelRoot.left.right = new TreeNode(85);
        sameLevelRoot.right.left = new TreeNode(95);
        sameLevelRoot.right.right = new TreeNode(110);
        SmallestSubTreeWithAllTheDeepestNodes sameLevelSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode sameLevelResult = sameLevelSolution.subtreeWithAllDeepest(sameLevelRoot);
        System.out.println(sameLevelResult.val); // Should print 90, as it contains all deepest nodes (70, 85, 95, 110)
        // Example: Testing with a tree where the deepest nodes are all leaf nodes
        TreeNode leafRoot = new TreeNode(120);
        leafRoot.left = new TreeNode(110);
        leafRoot.right = new TreeNode(130);
        leafRoot.left.left = new TreeNode(100);
        leafRoot.left.right = new TreeNode(105);
        leafRoot.right.left = new TreeNode(125);
        leafRoot.right.right = new TreeNode(135);
        SmallestSubTreeWithAllTheDeepestNodes leafSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode leafResult = leafSolution.subtreeWithAllDeepest(leafRoot);
        System.out.println(leafResult.val); // Should print 120, as it contains all deepest nodes (100, 105, 125, 135)
        // Example: Testing with a tree where the deepest nodes are not leaf nodes
        TreeNode nonLeafRoot = new TreeNode(140);
        nonLeafRoot.left = new TreeNode(130);
        nonLeafRoot.right = new TreeNode(150);
        nonLeafRoot.left.left = new TreeNode(120);
        nonLeafRoot.left.right = new TreeNode(135);
        nonLeafRoot.right.left = new TreeNode(145);
        nonLeafRoot.right.right = new TreeNode(160);
        nonLeafRoot.left.left.left = new TreeNode(110);
        nonLeafRoot.left.left.right = new TreeNode(115);
        SmallestSubTreeWithAllTheDeepestNodes nonLeafSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode nonLeafResult = nonLeafSolution.subtreeWithAllDeepest(nonLeafRoot);
        System.out.println(nonLeafResult.val); // Should print 130, as it contains the deepest nodes (110, 115, 135, 145, 160)
        // Example: Testing with a tree where the deepest nodes are at different branches
        TreeNode branchRoot = new TreeNode(160);
        branchRoot.left = new TreeNode(150);
        branchRoot.right = new TreeNode(170);
        branchRoot.left.left = new TreeNode(140);
        branchRoot.left.right = new TreeNode(155);
        branchRoot.right.left = new TreeNode(165);
        branchRoot.right.right = new TreeNode(180);
        branchRoot.left.left.left = new TreeNode(130);
        branchRoot.left.left.right = new TreeNode(135);
        SmallestSubTreeWithAllTheDeepestNodes branchSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode branchResult = branchSolution.subtreeWithAllDeepest(branchRoot);
        System.out.println(branchResult.val); // Should print 150, as it contains the deepest nodes (130, 135, 155, 165, 180)
        // Example: Testing with a tree where the deepest nodes are at different levels and branches
        TreeNode complexRoot = new TreeNode(200);
        complexRoot.left = new TreeNode(180);
        complexRoot.right = new TreeNode(220);
        complexRoot.left.left = new TreeNode(160);
        complexRoot.left.right = new TreeNode(190);
        complexRoot.right.left = new TreeNode(210);
        complexRoot.right.right = new TreeNode(240);
        complexRoot.left.left.left = new TreeNode(140);
        complexRoot.left.left.right = new TreeNode(150);
        complexRoot.left.right.left = new TreeNode(170);
        complexRoot.left.right.right = new TreeNode(175);
        SmallestSubTreeWithAllTheDeepestNodes complexSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode complexResult = complexSolution.subtreeWithAllDeepest(complexRoot);
        System.out.println(complexResult.val); // Should print 180, as it contains the deepest nodes (140, 150, 170, 175, 210, 240)
        // Example: Testing with a tree where the deepest nodes are at different branches and levels
        TreeNode mixedBranchesRoot = new TreeNode(300);
        mixedBranchesRoot.left = new TreeNode(250);
        mixedBranchesRoot.right = new TreeNode(350);
        mixedBranchesRoot.left.left = new TreeNode(200);
        mixedBranchesRoot.left.right = new TreeNode(275);
        mixedBranchesRoot.right.left = new TreeNode(320);
        mixedBranchesRoot.right.right = new TreeNode(400);
        mixedBranchesRoot.left.left.left = new TreeNode(180);
        mixedBranchesRoot.left.left.right = new TreeNode(190);
        mixedBranchesRoot.left.right.left = new TreeNode(260);
        mixedBranchesRoot.left.right.right = new TreeNode(280);
        mixedBranchesRoot.right.left.left = new TreeNode(310);
        mixedBranchesRoot.right.left.right = new TreeNode(330);
        mixedBranchesRoot.right.right.left = new TreeNode(370);
        mixedBranchesRoot.right.right.right = new TreeNode(420);
        SmallestSubTreeWithAllTheDeepestNodes mixedBranchesSolution = new SmallestSubTreeWithAllTheDeepestNodes();
        TreeNode mixedBranchesResult = mixedBranchesSolution.subtreeWithAllDeepest(mixedBranchesRoot);
        System.out.println(mixedBranchesResult.val); // Should print 250, as it contains the deepest nodes (180, 190, 260, 280, 310, 330, 370, 420)
    }
}
