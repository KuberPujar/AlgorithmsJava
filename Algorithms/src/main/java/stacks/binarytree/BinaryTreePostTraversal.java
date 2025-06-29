package stacks.binarytree;
/*
Given the root of a binary tree, return the postorder traversal of its nodes' values.



Example 1:

Input: root = [1,null,2,3]

Output: [3,2,1]

Explanation:



Example 2:

Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]

Output: [4,6,7,5,2,9,8,3,1]

Explanation:



Example 3:

Input: root = []

Output: []

Example 4:

Input: root = [1]

Output: [1]



Constraints:

The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100


Follow up: Recursive solution is trivial, could you do it iteratively?
 */


import java.util.*;

public class BinaryTreePostTraversal {

        /**
         * Iterative postorder traversal using single stack
         * Time Complexity: O(n) where n is number of nodes
         * Space Complexity: O(h) where h is height of tree
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<TreeNode> stack = new Stack<>();
            TreeNode lastVisited = null;
            TreeNode current = root;

            while (current != null || !stack.isEmpty()) {
                if (current != null) {
                    // Go to leftmost node
                    stack.push(current);
                    current = current.left;
                } else {
                    // Peek at the node on top of stack
                    TreeNode peekNode = stack.peek();

                    // If right child exists and hasn't been processed yet
                    if (peekNode.right != null && lastVisited != peekNode.right) {
                        current = peekNode.right;
                    } else {
                        // Process the node
                        result.add(peekNode.val);
                        lastVisited = stack.pop();
                    }
                }
            }

            return result;
        }

        /**
         * Alternative approach using two stacks
         * Easier to understand - uses reverse of preorder (Root-Right-Left) then reverse result
         */
        public List<Integer> postorderTraversalTwoStacks(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();

            stack1.push(root);

            // First stack performs Root-Right-Left traversal
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                stack2.push(node);

                // Push left first, then right (opposite of preorder)
                if (node.left != null) {
                    stack1.push(node.left);
                }
                if (node.right != null) {
                    stack1.push(node.right);
                }
            }

            // Second stack gives us Left-Right-Root (postorder)
            while (!stack2.isEmpty()) {
                result.add(stack2.pop().val);
            }

            return result;
        }

        /**
         * Approach using single stack with modified preorder
         * Generate Root-Right-Left, then reverse to get Left-Right-Root
         */
        public List<Integer> postorderTraversalReverse(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                result.add(node.val);

                // Push left first, then right (for Root-Right-Left)
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }

            // Reverse to get Left-Right-Root (postorder)
            Collections.reverse(result);
            return result;
        }

        /**
         * Approach using stack with visited flag
         */
        public List<Integer> postorderTraversalWithFlag(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<NodeWithFlag> stack = new Stack<>();
            stack.push(new NodeWithFlag(root, false));

            while (!stack.isEmpty()) {
                NodeWithFlag current = stack.pop();

                if (current.visited) {
                    // If node is visited, add to result
                    result.add(current.node.val);
                } else {
                    // Mark as visited and push back
                    stack.push(new NodeWithFlag(current.node, true));

                    // Push right child first, then left child
                    if (current.node.right != null) {
                        stack.push(new NodeWithFlag(current.node.right, false));
                    }
                    if (current.node.left != null) {
                        stack.push(new NodeWithFlag(current.node.left, false));
                    }
                }
            }

            return result;
        }

        /**
         * Helper class for approach with visited flag
         */
        class NodeWithFlag {
            TreeNode node;
            boolean visited;

            NodeWithFlag(TreeNode node, boolean visited) {
                this.node = node;
                this.visited = visited;
            }
        }

        /**
         * Recursive approach for comparison
         */
        public List<Integer> postorderTraversalRecursive(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            postorderHelper(root, result);
            return result;
        }

        private void postorderHelper(TreeNode node, List<Integer> result) {
            if (node != null) {
                postorderHelper(node.left, result);
                postorderHelper(node.right, result);
                result.add(node.val);
            }
        }

        /**
         * Helper method to build tree from array representation
         */
        public TreeNode buildTree(Integer[] arr) {
            if (arr == null || arr.length == 0) return null;

            TreeNode root = new TreeNode(arr[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int i = 1;
            while (!queue.isEmpty() && i < arr.length) {
                TreeNode node = queue.poll();

                if (i < arr.length && arr[i] != null) {
                    node.left = new TreeNode(arr[i]);
                    queue.offer(node.left);
                }
                i++;

                if (i < arr.length && arr[i] != null) {
                    node.right = new TreeNode(arr[i]);
                    queue.offer(node.right);
                }
                i++;
            }

            return root;
        }

        /**
         * Helper method to print tree structure
         */
        public void printTree(TreeNode root) {
            if (root == null) {
                System.out.println("Empty tree");
                return;
            }

            List<String> result = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    result.add(String.valueOf(node.val));
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    result.add("null");
                }
            }

            // Remove trailing nulls
            while (!result.isEmpty() && result.get(result.size() - 1).equals("null")) {
                result.remove(result.size() - 1);
            }

            System.out.println(result);
        }

        /**
         * Test method with all examples
         */
        public static void main(String[] args) {
            BinaryTreePostTraversal solution = new BinaryTreePostTraversal();

            // Example 1: [1,null,2,3]
            System.out.println("Example 1:");
            TreeNode root1 = solution.buildTree(new Integer[]{1, null, 2, 3});
            System.out.print("Input: ");
            solution.printTree(root1);
            System.out.println("Single Stack Output: " + solution.postorderTraversal(root1));
            System.out.println("Two Stacks Output: " + solution.postorderTraversalTwoStacks(root1));
            System.out.println("Reverse Method Output: " + solution.postorderTraversalReverse(root1));
            System.out.println("Flag Method Output: " + solution.postorderTraversalWithFlag(root1));

            // Example 2: [1,2,3,4,5,null,8,null,null,6,7,9]
            System.out.println("\nExample 2:");
            TreeNode root2 = solution.buildTree(new Integer[]{1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9});
            System.out.print("Input: ");
            solution.printTree(root2);
            System.out.println("Single Stack Output: " + solution.postorderTraversal(root2));
            System.out.println("Two Stacks Output: " + solution.postorderTraversalTwoStacks(root2));

            // Example 3: []
            System.out.println("\nExample 3:");
            TreeNode root3 = solution.buildTree(new Integer[]{});
            System.out.print("Input: ");
            solution.printTree(root3);
            System.out.println("Single Stack Output: " + solution.postorderTraversal(root3));

            // Example 4: [1]
            System.out.println("\nExample 4:");
            TreeNode root4 = solution.buildTree(new Integer[]{1});
            System.out.print("Input: ");
            solution.printTree(root4);
            System.out.println("Single Stack Output: " + solution.postorderTraversal(root4));
            System.out.println("Recursive Output: " + solution.postorderTraversalRecursive(root4));

            // Performance comparison
            System.out.println("\nPerformance Test:");
            TreeNode perfTree = solution.buildTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
            System.out.print("Test tree: ");
            solution.printTree(perfTree);

            long start = System.nanoTime();
            List<Integer> singleStackResult = solution.postorderTraversal(perfTree);
            long singleStackTime = System.nanoTime() - start;

            start = System.nanoTime();
            List<Integer> twoStacksResult = solution.postorderTraversalTwoStacks(perfTree);
            long twoStacksTime = System.nanoTime() - start;

            start = System.nanoTime();
            List<Integer> reverseResult = solution.postorderTraversalReverse(perfTree);
            long reverseTime = System.nanoTime() - start;

            start = System.nanoTime();
            List<Integer> recursiveResult = solution.postorderTraversalRecursive(perfTree);
            long recursiveTime = System.nanoTime() - start;

            System.out.println("Single Stack: " + singleStackResult + " (Time: " + singleStackTime + " ns)");
            System.out.println("Two Stacks: " + twoStacksResult + " (Time: " + twoStacksTime + " ns)");
            System.out.println("Reverse Method: " + reverseResult + " (Time: " + reverseTime + " ns)");
            System.out.println("Recursive: " + recursiveResult + " (Time: " + recursiveTime + " ns)");

            // Verify all methods produce same result
            boolean allMatch = singleStackResult.equals(twoStacksResult) &&
                    twoStacksResult.equals(reverseResult) &&
                    reverseResult.equals(recursiveResult);
            System.out.println("All methods produce same result: " + allMatch);
        }
}
