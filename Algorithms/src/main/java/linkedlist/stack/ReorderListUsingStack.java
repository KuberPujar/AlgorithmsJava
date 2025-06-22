package linkedlist.stack;

import linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.



Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]


Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
 */
public class ReorderListUsingStack {
    /**
     * Reorder linked list using stack approach
     * Pattern: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(n) for the stack storage
     *
     * @param head The head of the linked list
     */
    public void reorderListSt(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Push all nodes into stack to get reverse order access
        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;
        int totalNodes = 0;

        // Count total nodes and push to stack
        while (current != null) {
            stack.push(current);
            current = current.next;
            totalNodes++;
        }

        // Step 2: Reorder by alternating between start and end
        current = head;
        int processedNodes = 0;

        // Process pairs: take one from start, one from end
        while (processedNodes < totalNodes / 2) {
            // Get the node from end (stack top)
            ListNode nodeFromEnd = stack.pop();

            // Store the next node from start before modification
            ListNode nextFromStart = current.next;

            // Connect current node to node from end
            current.next = nodeFromEnd;

            // Connect node from end to next node from start
            nodeFromEnd.next = nextFromStart;

            // Move to the next pair
            current = nextFromStart;
            processedNodes++;
        }

        // Step 3: Terminate the list properly
        // The current pointer is now at the middle (for odd) or second middle (for even)
        current.next = null;
    }

// Alternative solution with explicit step-by-step approach for better understanding
    /**
     * Reorder linked list using stack with detailed steps
     * This version shows the algorithm more explicitly
     */
    public void reorderListStAltr(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Count nodes and store in stack
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        int count = 0;

        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
            count++;
        }

        // Step 2: Process first half nodes
        ListNode current = head;

        // We need to process count/2 pairs
        for (int i = 0; i < count / 2; i++) {
            // Pop the last unprocessed node
            ListNode last = stack.pop();

            // Store next node in forward direction
            ListNode next = current.next;

            // Reorder: current -> last -> next
            current.next = last;
            last.next = next;

            // Move current to next for next iteration
            current = next;
        }

        // Step 3: Cut off the remaining connection
        current.next = null;
    }

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
     * Convert linked list to array for easy printing and verification
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

    /**
     * Print array for comparison
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

    public static void main(String[] args) {
        ReorderListUsingStack solution = new ReorderListUsingStack();

        System.out.println("=== Reorder Linked List Using Stack ===\n");

        // Test Example 1: [1,2,3,4] -> [1,4,2,3]
        System.out.println("Example 1:");
        int[] input1 = {1, 2, 3, 4};
        System.out.print("Input:  ");
        printArray(input1);
        System.out.println("Expected: [1,4,2,3]");

        ListNode head1 = createLinkedList(input1);
        solution.reorderListSt(head1);
        System.out.print("Output: ");
        printLinkedList(head1);
        System.out.println();

        // Test Example 2: [1,2,3,4,5] -> [1,5,2,4,3]
        System.out.println("Example 2:");
        int[] input2 = {1, 2, 3, 4, 5};
        System.out.print("Input:  ");
        printArray(input2);
        System.out.println("Expected: [1,5,2,4,3]");

        ListNode head2 = createLinkedList(input2);
        solution.reorderListSt(head2);
        System.out.print("Output: ");
        printLinkedList(head2);
        System.out.println();

        // Test edge case: single node
        System.out.println("Edge case - Single node:");
        int[] input3 = {1};
        System.out.print("Input:  ");
        printArray(input3);
        System.out.println("Expected: [1]");

        ListNode head3 = createLinkedList(input3);
        solution.reorderListSt(head3);
        System.out.print("Output: ");
        printLinkedList(head3);
        System.out.println();

        // Test edge case: two nodes
        System.out.println("Edge case - Two nodes:");
        int[] input4 = {1, 2};
        System.out.print("Input:  ");
        printArray(input4);
        System.out.println("Expected: [1,2]");

        ListNode head4 = createLinkedList(input4);
        solution.reorderListSt(head4);
        System.out.print("Output: ");
        printLinkedList(head4);
        System.out.println();

        // Test larger example: [1,2,3,4,5,6] -> [1,6,2,5,3,4]
        System.out.println("Additional test - Six nodes:");
        int[] input5 = {1, 2, 3, 4, 5, 6};
        System.out.print("Input:  ");
        printArray(input5);
        System.out.println("Expected: [1,6,2,5,3,4]");

        ListNode head5 = createLinkedList(input5);
        solution.reorderListSt(head5);
        System.out.print("Output: ");
        printLinkedList(head5);

        // Show algorithm explanation
        System.out.println("\n=== Algorithm Explanation ===");
        System.out.println("1. Push all nodes into a stack to access them in reverse order");
        System.out.println("2. For each node from the start, pair it with a node from the end (stack)");
        System.out.println("3. Reorder connections: current -> nodeFromEnd -> nextFromStart");
        System.out.println("4. Process n/2 pairs and terminate the list properly");
        System.out.println("5. Time: O(n), Space: O(n) for stack storage");
    }
}
