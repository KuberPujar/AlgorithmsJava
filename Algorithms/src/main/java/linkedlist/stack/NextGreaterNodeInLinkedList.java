package linkedlist.stack;

import linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
You are given the head of a linked list with n nodes.

For each node in the list, find the value of the next greater node. That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.

Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.



Example 1:


Input: head = [2,1,5]
Output: [5,5,0]
Example 2:


Input: head = [2,7,4,3,5]
Output: [7,0,5,5,0]


Constraints:

The number of nodes in the list is n.
1 <= n <= 104
1 <= Node.val <= 109
 */
public class NextGreaterNodeInLinkedList {
    /**
     * Find next greater node for each node in linked list using monotonic stack
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(n) for the stack and result array
     *
     * @param head The head of the linked list
     * @return Array where answer[i] is the next greater element for ith node (1-indexed)
     */
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) return new int[0];

        // Step 1: Convert linked list to array for easier indexing
        List<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        int n = values.size();
        int[] result = new int[n];  // Initialize with 0s (default for no greater element)

        // Step 2: Use monotonic decreasing stack to find next greater elements
        // Stack stores indices of elements for which we haven't found next greater element yet
        Stack<Integer> stack = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {
            // While stack is not empty and current element is greater than
            // the element at index stored at stack top
            while (!stack.isEmpty() && values.get(i) > values.get(stack.peek())) {
                int index = stack.pop();
                result[index] = values.get(i);  // Found next greater element
            }

            // Push current index to stack
            stack.push(i);
        }

        // Remaining elements in stack don't have next greater element (already 0 in result)
        return result;
    }
}

// Alternative solution that processes linked list directly without conversion
class SolutionDirect {
    /**
     * Process linked list directly using stack of nodes and their indices
     */
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) return new int[0];

        // First pass: count nodes
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int[] result = new int[count];

        // Stack to store pairs of (index, value) for pending elements
        Stack<int[]> stack = new Stack<>();  // [index, value]

        ListNode current = head;
        int index = 0;

        while (current != null) {
            // Process all elements in stack that are smaller than current
            while (!stack.isEmpty() && current.val > stack.peek()[1]) {
                int[] pair = stack.pop();
                result[pair[0]] = current.val;  // Set next greater element
            }

            // Add current element to stack
            stack.push(new int[]{index, current.val});

            current = current.next;
            index++;
        }

        // Elements remaining in stack have no next greater element (already 0)
        return result;
    }
}

// Detailed solution with step-by-step visualization
class SolutionDetailed1 {
    /**
     * Solution with detailed step tracking for educational purposes
     */
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) return new int[0];

        // Convert to list for easier processing
        List<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        int n = values.size();
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        System.out.println("=== Step-by-step Next Greater Element Process ===");
        System.out.println("Input values: " + values);
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("Step %d: Processing element %d at index %d%n", i + 1, values.get(i), i);
            System.out.println("Stack before: " + stack);

            // Process stack
            while (!stack.isEmpty() && values.get(i) > values.get(stack.peek())) {
                int index = stack.pop();
                result[index] = values.get(i);
                System.out.printf("  Found next greater for index %d (value %d): %d%n",
                        index, values.get(index), values.get(i));
            }

            stack.push(i);
            System.out.println("Stack after: " + stack);
            System.out.println("Result so far: " + Arrays.toString(result));
            System.out.println();
        }

        System.out.println("Final result: " + Arrays.toString(result));
        return result;
    }
}

// Helper class for testing and utility functions
class LinkedListHelper1 {
    /**
     * Create a linked list from array of values
     */
    public static ListNode createLinkedList(int[] values) {
        if (values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Convert linked list to array for printing
     */
    public static int[] linkedListToArray(ListNode head) {
        if (head == null) return new int[0];

        List<Integer> result = new ArrayList<>();
        ListNode current = head;

        while (current != null) {
            result.add(current.val);
            current = current.next;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Print array in format [a,b,c]
     */
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    /**
     * Print linked list
     */
    public static void printLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}

// Test class to demonstrate the solution
class TestNextGreaterNode {
    public static void main(String[] args) {
        NextGreaterNodeInLinkedList solution = new NextGreaterNodeInLinkedList();
        SolutionDirect directSolution = new SolutionDirect();
        SolutionDetailed1 detailedSolution = new SolutionDetailed1();

        System.out.println("=== Next Greater Node in Linked List Using Stack ===\n");

        // Test Example 1: [2,1,5] -> [5,5,0]
        System.out.println("Example 1:");
        int[] input1 = {2, 1, 5};
        System.out.print("Input: ");
        LinkedListHelper1.printArray(input1);
        System.out.println("Expected: [5,5,0]");

        ListNode head1 = LinkedListHelper1.createLinkedList(input1);
        int[] result1 = solution.nextLargerNodes(head1);
        System.out.print("Output: ");
        LinkedListHelper1.printArray(result1);

        // Verify with direct solution
        ListNode head1_direct = LinkedListHelper1.createLinkedList(input1);
        int[] result1_direct = directSolution.nextLargerNodes(head1_direct);
        System.out.print("Direct method: ");
        LinkedListHelper1.printArray(result1_direct);
        System.out.println();

        // Test Example 2: [2,7,4,3,5] -> [7,0,5,5,0]
        System.out.println("Example 2:");
        int[] input2 = {2, 7, 4, 3, 5};
        System.out.print("Input: ");
        LinkedListHelper1.printArray(input2);
        System.out.println("Expected: [7,0,5,5,0]");

        ListNode head2 = LinkedListHelper1.createLinkedList(input2);
        int[] result2 = solution.nextLargerNodes(head2);
        System.out.print("Output: ");
        LinkedListHelper1.printArray(result2);
        System.out.println();

        // Test edge case: single element
        System.out.println("Edge case - Single element:");
        int[] input3 = {5};
        System.out.print("Input: ");
        LinkedListHelper1.printArray(input3);
        System.out.println("Expected: [0]");

        ListNode head3 = LinkedListHelper1.createLinkedList(input3);
        int[] result3 = solution.nextLargerNodes(head3);
        System.out.print("Output: ");
        LinkedListHelper1.printArray(result3);
        System.out.println();

        // Test increasing sequence
        System.out.println("Test - Increasing sequence:");
        int[] input4 = {1, 2, 3, 4, 5};
        System.out.print("Input: ");
        LinkedListHelper1.printArray(input4);
        System.out.println("Expected: [2,3,4,5,0]");

        ListNode head4 = LinkedListHelper1.createLinkedList(input4);
        int[] result4 = solution.nextLargerNodes(head4);
        System.out.print("Output: ");
        LinkedListHelper.printArray(result4);
        System.out.println();

        // Test decreasing sequence
        System.out.println("Test - Decreasing sequence:");
        int[] input5 = {5, 4, 3, 2, 1};
        System.out.print("Input: ");
        LinkedListHelper1.printArray(input5);
        System.out.println("Expected: [0,0,0,0,0]");

        ListNode head5 = LinkedListHelper1.createLinkedList(input5);
        int[] result5 = solution.nextLargerNodes(head5);
        System.out.print("Output: ");
        LinkedListHelper1.printArray(result5);
        System.out.println();

        // Detailed walkthrough for educational purposes
        System.out.println("=== Detailed Walkthrough ===");
        ListNode headDetailed = LinkedListHelper1.createLinkedList(new int[]{2, 1, 5});
        int[] resultDetailed = detailedSolution.nextLargerNodes(headDetailed);

        // Algorithm explanation
        System.out.println("\n=== Algorithm Explanation ===");
        System.out.println("1. Use a monotonic decreasing stack to store indices");
        System.out.println("2. For each element, pop stack while current > stack.top element");
        System.out.println("3. For each popped index, current element is the next greater");
        System.out.println("4. Push current index to stack");
        System.out.println("5. Elements remaining in stack have no next greater (stay 0)");
        System.out.println("6. Time: O(n) - each element pushed/popped at most once");
        System.out.println("7. Space: O(n) - for stack and result array");

        System.out.println("\n=== Key Insights ===");
        System.out.println("• Stack maintains indices in decreasing order of their values");
        System.out.println("• When we find a larger element, it's the 'next greater' for all smaller elements in stack");
        System.out.println("• This avoids nested loops and achieves O(n) time complexity");
        System.out.println("• Works for any sequence pattern (increasing, decreasing, mixed)");
    }
}
