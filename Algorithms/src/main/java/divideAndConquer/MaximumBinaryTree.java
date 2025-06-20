package divideAndConquer;
/*
You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

Create a root node whose value is the maximum value in nums.
Recursively build the left subtree on the subarray prefix to the left of the maximum value.
Recursively build the right subtree on the subarray suffix to the right of the maximum value.
Return the maximum binary tree built from nums.



Example 1:


Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]
Explanation: The recursive calls are as follow:
- The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
    - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
        - Empty array, so no child.
        - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
    - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.
Example 2:


Input: nums = [3,2,1]
Output: [3,null,2,null,1]


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000
All integers in nums are unique.
 */
import java.util.*;

public class MaximumBinaryTree {

    // Solution 1: Recursive Approach - O(n²) time, O(n) space
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        // Base case: empty subarray
        if (left > right) {
            return null;
        }

        // Find the index of maximum element in current range
        int maxIndex = findMaxIndex(nums, left, right);

        // Create root node with maximum value
        TreeNode root = new TreeNode(nums[maxIndex]);

        // Recursively build left and right subtrees
        root.left = buildTree(nums, left, maxIndex - 1);
        root.right = buildTree(nums, maxIndex + 1, right);

        return root;
    }

    private int findMaxIndex(int[] nums, int left, int right) {
        int maxIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Solution 2: Optimized Recursive with Single Pass Max Finding
    public TreeNode constructMaximumBinaryTreeOptimized(int[] nums) {
        return buildTreeOptimized(nums, 0, nums.length - 1);
    }

    private TreeNode buildTreeOptimized(int[] nums, int left, int right) {
        if (left > right) return null;

        // Find max value and its index in one pass
        int maxVal = nums[left];
        int maxIndex = left;

        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(maxVal);
        root.left = buildTreeOptimized(nums, left, maxIndex - 1);
        root.right = buildTreeOptimized(nums, maxIndex + 1, right);

        return root;
    }

    // Solution 3: Stack-based Iterative Approach - O(n) time, O(n) space
    public TreeNode constructMaximumBinaryTreeStack(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();

        for (int num : nums) {
            TreeNode current = new TreeNode(num);

            // Pop smaller elements and make them left children
            while (!stack.isEmpty() && stack.peek().val < num) {
                current.left = stack.pop();
            }

            // If stack is not empty, current becomes right child of top
            if (!stack.isEmpty()) {
                stack.peek().right = current;
            }

            stack.push(current);
        }

        // Find the root (bottom of stack)
        TreeNode root = null;
        while (!stack.isEmpty()) {
            root = stack.pop();
        }

        return root;
    }

    // Solution 4: Deque-based Approach (Alternative to Stack)
    public TreeNode constructMaximumBinaryTreeDeque(int[] nums) {
        Deque<TreeNode> deque = new ArrayDeque<>();

        for (int num : nums) {
            TreeNode current = new TreeNode(num);

            while (!deque.isEmpty() && deque.peekLast().val < num) {
                current.left = deque.pollLast();
            }

            if (!deque.isEmpty()) {
                deque.peekLast().right = current;
            }

            deque.offerLast(current);
        }

        return deque.peekFirst();
    }

    // Helper methods for tree visualization and testing
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        List<String> result = new ArrayList<>();
        printTreeLevel(root, result);
        System.out.println(result);
    }

    private static void printTreeLevel(TreeNode root, List<String> result) {
        if (root == null) return;

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
    }

    // Method to print tree structure visually
    public static void printTreeStructure(TreeNode root) {
        printTreeStructure(root, "", true);
    }

    private static void printTreeStructure(TreeNode root, String prefix, boolean isLast) {
        if (root != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + root.val);

            if (root.left != null || root.right != null) {
                if (root.left != null) {
                    printTreeStructure(root.left, prefix + (isLast ? "    " : "│   "), root.right == null);
                }
                if (root.right != null) {
                    printTreeStructure(root.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }

    // Method to demonstrate step-by-step construction
    private static void demonstrateConstruction(int[] nums) {
        System.out.println("=== Step-by-Step Construction ===");
        System.out.println("Array: " + Arrays.toString(nums));
        demonstrateRecursive(nums, 0, nums.length - 1, 0);
    }

    private static void demonstrateRecursive(int[] nums, int left, int right, int depth) {
        String indent = "  ".repeat(depth);

        if (left > right) {
            System.out.println(indent + "Empty range [" + left + "," + right + "] -> null");
            return;
        }

        // Find max
        int maxIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        System.out.println(indent + "Range [" + left + "," + right + "]: " +
                Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));
        System.out.println(indent + "Max value: " + nums[maxIndex] + " at index " + maxIndex);

        if (left <= maxIndex - 1) {
            System.out.println(indent + "Left subtree:");
            demonstrateRecursive(nums, left, maxIndex - 1, depth + 1);
        }

        if (maxIndex + 1 <= right) {
            System.out.println(indent + "Right subtree:");
            demonstrateRecursive(nums, maxIndex + 1, right, depth + 1);
        }
    }

    // Method to verify tree correctness
    public static boolean verifyMaximumBinaryTree(TreeNode root, int[] nums) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);

        // The inorder traversal should match the original array order
        // for elements that exist in the tree
        return verifyStructure(root, nums, 0, nums.length - 1);
    }

    private static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    private static boolean verifyStructure(TreeNode root, int[] nums, int left, int right) {
        if (left > right) return root == null;
        if (root == null) return false;

        // Find the index of root value in the array
        int rootIndex = -1;
        int maxVal = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (int i = left; i <= right; i++) {
            if (nums[i] == root.val) rootIndex = i;
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }

        // Root should be the maximum in its range
        if (rootIndex != maxIndex || root.val != maxVal) return false;

        // Verify left and right subtrees
        return verifyStructure(root.left, nums, left, rootIndex - 1) &&
                verifyStructure(root.right, nums, rootIndex + 1, right);
    }

    public static void main(String[] args) {
        MaximumBinaryTree solution = new MaximumBinaryTree();

        // Example 1: [3,2,1,6,0,5]
        System.out.println("=== Example 1: [3,2,1,6,0,5] ===");
        int[] nums1 = {3, 2, 1, 6, 0, 5};

        TreeNode tree1 = solution.constructMaximumBinaryTree(nums1);
        System.out.print("Recursive result: ");
        printTree(tree1);

        TreeNode tree1Stack = solution.constructMaximumBinaryTreeStack(nums1);
        System.out.print("Stack result: ");
        printTree(tree1Stack);

        System.out.println("\nTree structure:");
        printTreeStructure(tree1);

        System.out.println("\nVerification: " + verifyMaximumBinaryTree(tree1, nums1));

        // Example 2: [3,2,1]
        System.out.println("\n=== Example 2: [3,2,1] ===");
        int[] nums2 = {3, 2, 1};

        TreeNode tree2 = solution.constructMaximumBinaryTree(nums2);
        System.out.print("Result: ");
        printTree(tree2);

        System.out.println("\nTree structure:");
        printTreeStructure(tree2);

        // Demonstrate step-by-step construction
        demonstrateConstruction(nums1);

        // Performance comparison
        System.out.println("\n=== Performance Analysis ===");
        performanceTest(solution);

        // Edge cases
        System.out.println("\n=== Edge Cases ===");
        testEdgeCases(solution);

        System.out.println("\n=== Algorithm Comparison ===");
        System.out.println("1. Recursive: O(n²) time, O(n) space - Easy to understand");
        System.out.println("2. Stack-based: O(n) time, O(n) space - Optimal performance");
        System.out.println("3. Deque-based: O(n) time, O(n) space - Alternative to stack");
        System.out.println("\nFor interview: Start with recursive, then optimize to stack-based");
    }

    private static void performanceTest(MaximumBinaryTree solution) {
        int[] testArray = new int[100];
        for (int i = 0; i < 100; i++) {
            testArray[i] = i;
        }

        long startTime, endTime;

        // Test recursive approach
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            solution.constructMaximumBinaryTree(testArray);
        }
        endTime = System.nanoTime();
        System.out.println("Recursive (1000 iterations): " + (endTime - startTime) / 1000000.0 + " ms");

        // Test stack approach
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            solution.constructMaximumBinaryTreeStack(testArray);
        }
        endTime = System.nanoTime();
        System.out.println("Stack-based (1000 iterations): " + (endTime - startTime) / 1000000.0 + " ms");
    }

    private static void testEdgeCases(MaximumBinaryTree solution) {
        // Single element
        int[] single = {5};
        TreeNode singleTree = solution.constructMaximumBinaryTree(single);
        System.out.print("Single element [5]: ");
        printTree(singleTree);

        // Ascending order
        int[] ascending = {1, 2, 3, 4, 5};
        TreeNode ascTree = solution.constructMaximumBinaryTree(ascending);
        System.out.print("Ascending [1,2,3,4,5]: ");
        printTree(ascTree);

        // Descending order
        int[] descending = {5, 4, 3, 2, 1};
        TreeNode descTree = solution.constructMaximumBinaryTree(descending);
        System.out.print("Descending [5,4,3,2,1]: ");
        printTree(descTree);
    }
}

/*
Algorithm Explanations:

1. Recursive Approach:
   - Find maximum element in current range
   - Create root with maximum value
   - Recursively build left subtree with elements before max
   - Recursively build right subtree with elements after max
   - Time: O(n²) worst case (when array is sorted), O(n log n) average
   - Space: O(n) for recursion stack

2. Stack-based Approach (Optimal):
   - Process elements left to right
   - Maintain stack of nodes in decreasing order of values
   - For each new element:
     * Pop smaller elements and make them left children
     * Current element becomes right child of remaining top element
   - Time: O(n) - each element pushed and popped at most once
   - Space: O(n) for stack

3. Key Insights:
   - Maximum element is always the root of its range
   - Stack approach works because we maintain decreasing order
   - When we see a larger element, all smaller elements to its left
     become part of its left subtree
   - The relative order of elements is preserved in the final tree

4. Stack Algorithm Logic:
   - Stack maintains potential roots in decreasing order
   - When new element is larger than stack top:
     * All smaller elements become left subtree of new element
   - When new element is smaller:
     * It becomes right child of the last larger element

The stack-based solution is particularly elegant because it processes
the array in a single pass while maintaining the correct tree structure
through the stack's decreasing order property.
*/
