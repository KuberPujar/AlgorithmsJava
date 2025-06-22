package linkedlist.iterativemanner;

import linkedlist.ListNode;

/*
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.



Example 1:


Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
Example 2:


Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.


Constraints:

The number of nodes in the list is in the range [1, 100].
1 <= Node.val <= 100
 */
public class MiddleOfLinkedListIterative {
    /**
     * Find the middle node of a singly linked list using two-pointer approach
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - constant extra space
     *
     * @param head The head of the linked list
     * @return The middle node (second middle if even number of nodes)
     */
    public ListNode middleNode(ListNode head) {
        // Handle edge case - empty list
        if (head == null) {
            return null;
        }

        // Initialize two pointers
        ListNode slow = head;  // moves one step at a time
        ListNode fast = head;  // moves two steps at a time

        // Move pointers until fast reaches the end
        // When fast moves 2 steps, slow moves 1 step
        // So when fast reaches end, slow will be at middle
        while (fast != null && fast.next != null) {
            slow = slow.next;      // move slow pointer one step
            fast = fast.next.next; // move fast pointer two steps
        }

        // At this point, slow pointer is at the middle node
        return slow;
    }

// Helper class for testing
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
     * Convert linked list to array for easy printing
     */
    public static int[] linkedListToArray(ListNode head) {
        if (head == null) return new int[0];

        // Count nodes first
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Convert to array
        int[] result = new int[count];
        temp = head;
        for (int i = 0; i < count; i++) {
            result[i] = temp.val;
            temp = temp.next;
        }

        return result;
    }

    /**
     * Print linked list from given node
     */
    public static void printFromNode(ListNode node) {
        if (node == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(",");
            }
            node = node.next;
        }
        System.out.println("]");
    }

// Test class to demonstrate the solution
    public static void main(String[] args) {
        MiddleOfLinkedListIterative solution = new MiddleOfLinkedListIterative();

        // Test Example 1: [1,2,3,4,5] -> expected output: [3,4,5]
        System.out.println("Example 1:");
        int[] input1 = {1, 2, 3, 4, 5};
        ListNode head1 = createLinkedList(input1);
        System.out.print("Input: ");
        printFromNode(head1);

        ListNode middle1 = solution.middleNode(head1);
        System.out.print("Output: ");
        printFromNode(middle1);
        System.out.println();

        // Test Example 2: [1,2,3,4,5,6] -> expected output: [4,5,6]
        System.out.println("Example 2:");
        int[] input2 = {1, 2, 3, 4, 5, 6};
        ListNode head2 = createLinkedList(input2);
        System.out.print("Input: ");
        printFromNode(head2);

        ListNode middle2 = solution.middleNode(head2);
        System.out.print("Output: ");
       printFromNode(middle2);
        System.out.println();

        // Test edge case: single node
        System.out.println("Edge case - Single node:");
        int[] input3 = {1};
        ListNode head3 = createLinkedList(input3);
        System.out.print("Input: ");
        printFromNode(head3);

        ListNode middle3 = solution.middleNode(head3);
        System.out.print("Output: ");
        printFromNode(middle3);
    }
}
