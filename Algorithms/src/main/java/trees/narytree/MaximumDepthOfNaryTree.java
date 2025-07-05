package trees.narytree;
/*
Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).



Example 1:



Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Example 2:



Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 5


Constraints:

The total number of nodes is in the range [0, 104].
The depth of the n-ary tree is less than or equal to 1000.
 */
public class MaximumDepthOfNaryTree {

    public int maxDepth(Node root) {
        if (root == null) return 0;
        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max, maxDepth(child));
        }
        return max + 1;
    }

    public static void main(String[] args) {
        // Example usage
        Node root = new Node(1);
        Node child1 = new Node(3);
        Node child2 = new Node(2);
        Node child3 = new Node(4);
        Node grandChild1 = new Node(5);
        Node grandChild2 = new Node(6);

        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);
        child1.children.add(grandChild1);
        child1.children.add(grandChild2);

        MaximumDepthOfNaryTree solution = new MaximumDepthOfNaryTree();
        int depth = solution.maxDepth(root);
        System.out.println("Maximum Depth: " + depth); // Output: 3
    }
}
