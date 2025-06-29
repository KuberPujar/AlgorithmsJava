package stacks.binarytree;

import java.util.*;

/*
Given the root of a binary tree, return the preorder traversal of its nodes' values.



Example 1:

Input: root = [1,null,2,3]

Output: [1,2,3]

Explanation:



Example 2:

Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]

Output: [1,2,4,5,6,7,3,8,9]

Explanation:



Example 3:

Input: root = []

Output: []

Example 4:

Input: root = [1]

Output: [1]



Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100


Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreOrderTraversal {


        /**
         * Iterative preorder traversal using stack - Standard approach
         * Time Complexity: O(n) where n is number of nodes
         * Space Complexity: O(h) where h is height of tree
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                result.add(node.val); // Process root first

                // Push right child first (so left child is processed first)
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return result;
        }

        /**
         * Alternative approach using stack with current pointer
         * Similar to inorder but processes node immediately when encountered
         */
        public List<Integer> preorderTraversalAlternative(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = root;

            while (current != null || !stack.isEmpty()) {
                if (current != null) {
                    result.add(current.val); // Process root immediately

                    // If right child exists, save it for later
                    if (current.right != null) {
                        stack.push(current.right);
                    }

                    // Move to left child
                    current = current.left;
                } else {
                    // Get the saved right child
                    current = stack.pop();
                }
            }

            return result;
        }

        /**
         * Approach using stack with explicit command pattern
         * Uses commands to specify when to process vs when to explore
         */
        public List<Integer> preorderTraversalCommand(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<Command> stack = new Stack<>();
            stack.push(new Command("go", root));

            while (!stack.isEmpty()) {
                Command cmd = stack.pop();

                if (cmd.action.equals("print")) {
                    result.add(cmd.node.val);
                } else { // "go"
                    // Push in reverse order: right, left, print
                    if (cmd.node.right != null) {
                        stack.push(new Command("go", cmd.node.right));
                    }
                    if (cmd.node.left != null) {
                        stack.push(new Command("go", cmd.node.left));
                    }
                    stack.push(new Command("print", cmd.node));
                }
            }

            return result;
        }

        /**
         * Helper class for command pattern approach
         */
        class Command {
            String action;
            TreeNode node;

            Command(String action, TreeNode node) {
                this.action = action;
                this.node = node;
            }
        }

        /**
         * Morris Preorder Traversal (O(1) space) - Advanced approach without stack
         */
        public List<Integer> preorderTraversalMorris(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            TreeNode current = root;

            while (current != null) {
                if (current.left == null) {
                    result.add(current.val);
                    current = current.right;
                } else {
                    // Find inorder predecessor
                    TreeNode predecessor = current.left;
                    while (predecessor.right != null && predecessor.right != current) {
                        predecessor = predecessor.right;
                    }

                    if (predecessor.right == null) {
                        // First time visiting - create thread and process current
                        result.add(current.val);
                        predecessor.right = current;
                        current = current.left;
                    } else {
                        // Second time visiting - remove thread and move right
                        predecessor.right = null;
                        current = current.right;
                    }
                }
            }

            return result;
        }

        /**
         * Iterative approach using while loop without explicit stack manipulation
         */
        public List<Integer> preorderTraversalIterative(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = root;

            while (true) {
                // Go to leftmost node and process each node
                while (current != null) {
                    result.add(current.val); // Process root
                    stack.push(current);
                    current = current.left;
                }

                if (stack.isEmpty()) break;

                // Pop and go to right subtree
                current = stack.pop();
                current = current.right;
            }

            return result;
        }

        /**
         * Recursive approach for comparison
         */
        public List<Integer> preorderTraversalRecursive(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            preorderHelper(root, result);
            return result;
        }

        private void preorderHelper(TreeNode node, List<Integer> result) {
            if (node != null) {
                result.add(node.val);        // Root
                preorderHelper(node.left, result);  // Left
                preorderHelper(node.right, result); // Right
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
         * Utility method to visualize tree traversal step by step
         */
        public void visualizeTraversal(TreeNode root) {
            System.out.println("Step-by-step Preorder Traversal:");
            if (root == null) {
                System.out.println("Empty tree");
                return;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            int step = 1;

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                System.out.println("Step " + step + ": Process node " + node.val);

                if (node.right != null) {
                    stack.push(node.right);
                    System.out.println("         Push right child " + node.right.val + " to stack");
                }
                if (node.left != null) {
                    stack.push(node.left);
                    System.out.println("         Push left child " + node.left.val + " to stack");
                }

                System.out.print("         Stack contents: ");
                Stack<TreeNode> temp = new Stack<>();
                while (!stack.isEmpty()) {
                    temp.push(stack.pop());
                }
                while (!temp.isEmpty()) {
                    TreeNode tempNode = temp.pop();
                    stack.push(tempNode);
                    System.out.print(tempNode.val + " ");
                }
                System.out.println();
                step++;
            }
        }

        /**
         * Test method with all examples
         */
        public static void main(String[] args) {
            BinaryTreePreOrderTraversal solution = new BinaryTreePreOrderTraversal();

            // Example 1: [1,null,2,3]
            System.out.println("Example 1:");
            TreeNode root1 = solution.buildTree(new Integer[]{1, null, 2, 3});
            System.out.print("Input: ");
            solution.printTree(root1);
            System.out.println("Standard Stack Output: " + solution.preorderTraversal(root1));
            System.out.println("Alternative Output: " + solution.preorderTraversalAlternative(root1));
            System.out.println("Command Pattern Output: " + solution.preorderTraversalCommand(root1));
            System.out.println("Iterative Output: " + solution.preorderTraversalIterative(root1));

            // Example 2: [1,2,3,4,5,null,8,null,null,6,7,9]
            System.out.println("\nExample 2:");
            TreeNode root2 = solution.buildTree(new Integer[]{1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9});
            System.out.print("Input: ");
            solution.printTree(root2);
            System.out.println("Standard Stack Output: " + solution.preorderTraversal(root2));
            System.out.println("Morris Traversal Output: " + solution.preorderTraversalMorris(root2));

            // Example 3: []
            System.out.println("\nExample 3:");
            TreeNode root3 = solution.buildTree(new Integer[]{});
            System.out.print("Input: ");
            solution.printTree(root3);
            System.out.println("Standard Stack Output: " + solution.preorderTraversal(root3));

            // Example 4: [1]
            System.out.println("\nExample 4:");
            TreeNode root4 = solution.buildTree(new Integer[]{1});
            System.out.print("Input: ");
            solution.printTree(root4);
            System.out.println("Standard Stack Output: " + solution.preorderTraversal(root4));
            System.out.println("Recursive Output: " + solution.preorderTraversalRecursive(root4));

            // Visualization example
            System.out.println("\nVisualization for small tree [1,2,3]:");
            TreeNode visTree = solution.buildTree(new Integer[]{1, 2, 3});
            solution.visualizeTraversal(visTree);

            // Performance comparison
            System.out.println("\nPerformance Test:");
            TreeNode perfTree = solution.buildTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
            System.out.print("Test tree: ");
            solution.printTree(perfTree);

            long start = System.nanoTime();
            List<Integer> standardResult = solution.preorderTraversal(perfTree);
            long standardTime = System.nanoTime() - start;

            start = System.nanoTime();
            List<Integer> alternativeResult = solution.preorderTraversalAlternative(perfTree);
            long alternativeTime = System.nanoTime() - start;

            start = System.nanoTime();
            List<Integer> commandResult = solution.preorderTraversalCommand(perfTree);
            long commandTime = System.nanoTime() - start;

            start = System.nanoTime();
            List<Integer> recursiveResult = solution.preorderTraversalRecursive(perfTree);
            long recursiveTime = System.nanoTime() - start;

            start = System.nanoTime();
            List<Integer> morrisResult = solution.preorderTraversalMorris(perfTree);
            long morrisTime = System.nanoTime() - start;

            System.out.println("Standard Stack: " + standardResult + " (Time: " + standardTime + " ns)");
            System.out.println("Alternative: " + alternativeResult + " (Time: " + alternativeTime + " ns)");
            System.out.println("Command Pattern: " + commandResult + " (Time: " + commandTime + " ns)");
            System.out.println("Recursive: " + recursiveResult + " (Time: " + recursiveTime + " ns)");
            System.out.println("Morris (O(1) space): " + morrisResult + " (Time: " + morrisTime + " ns)");

            // Verify all methods produce same result
            boolean allMatch = standardResult.equals(alternativeResult) &&
                    alternativeResult.equals(commandResult) &&
                    commandResult.equals(recursiveResult) &&
                    recursiveResult.equals(morrisResult);
            System.out.println("All methods produce same result: " + allMatch);
        }
}
