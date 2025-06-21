package linkedlist.removing;

import linkedlist.ListNode;

/*
Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.



Example 1:


Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
Example 2:

Input: head = [], val = 1
Output: []
Example 3:

Input: head = [7,7,7,7], val = 7
Output: []


Constraints:

The number of nodes in the list is in the range [0, 104].
1 <= Node.val <= 50
0 <= val <= 50
 */
public class RemoveLinkedListElement {
    /**
     * Removes all nodes with value equal to val from the linked list
     * @param head - head of the linked list
     * @param val - value to be removed
     * @return new head of the modified linked list
     */
    public ListNode removeElements(ListNode head, int val) {
        // Handle empty list
        if (head == null) {
            return null;
        }

        // Skip all nodes at the beginning that have the target value
        while (head != null && head.val == val) {
            head = head.next;
        }

        // If all nodes were removed
        if (head == null) {
            return null;
        }

        // Now head points to a node that doesn't have the target value
        ListNode current = head;

        // Traverse the rest of the list
        while (current.next != null) {
            if (current.next.val == val) {
                // Skip the node with target value
                current.next = current.next.next;
            } else {
                // Move to next node only if we didn't remove a node
                current = current.next;
            }
        }

        return head;
    }

    /**
     * Alternative solution using dummy node approach
     * This approach is cleaner as it handles head removal uniformly
     */
    public ListNode removeElementsWithDummy(ListNode head, int val) {
        // Create a dummy node pointing to head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = dummy;

        // Traverse the list
        while (current.next != null) {
            if (current.next.val == val) {
                // Remove the node
                current.next = current.next.next;
            } else {
                // Move to next node
                current = current.next;
            }
        }

        // Return the new head (dummy.next)
        return dummy.next;
    }

    /**
     * Recursive solution
     */
    public ListNode removeElementsRecursive(ListNode head, int val) {
        // Base case
        if (head == null) {
            return null;
        }

        // Recursively process the rest of the list
        head.next = removeElementsRecursive(head.next, val);

        // If current node should be removed, return next node
        // Otherwise, return current node
        return head.val == val ? head.next : head;
    }

    // Helper method to create a linked list from array (for testing)
    public static ListNode createLinkedList(int[] arr) {
        if (arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    // Helper method to print a linked list (for testing)
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(",");
            }
            head = head.next;
        }
        System.out.println("]");
    }

    // Test method
    public static void main(String[] args) {
        RemoveLinkedListElement solution = new RemoveLinkedListElement();

        // Test Example 1: [1,2,6,3,4,5,6], val = 6
        System.out.println("Example 1:");
        ListNode head1 = createLinkedList(new int[]{1, 2, 6, 3, 4, 5, 6});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 6, 3, 4, 5, 6}));
        ListNode result1 = solution.removeElements(head1, 6);
        System.out.print("Output: ");
        printList(result1);

        // Test Example 2: [], val = 1
        System.out.println("\nExample 2:");
        ListNode head2 = createLinkedList(new int[]{});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{}));
        ListNode result2 = solution.removeElements(head2, 1);
        System.out.print("Output: ");
        printList(result2);

        // Test Example 3: [7,7,7,7], val = 7
        System.out.println("\nExample 3:");
        ListNode head3 = createLinkedList(new int[]{7, 7, 7, 7});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{7, 7, 7, 7}));
        ListNode result3 = solution.removeElements(head3, 7);
        System.out.print("Output: ");
        printList(result3);

        // Test with dummy node approach
        System.out.println("\n--- Testing Dummy Node Approach ---");
        ListNode head4 = createLinkedList(new int[]{1, 2, 6, 3, 4, 5, 6});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 6, 3, 4, 5, 6}));
        ListNode result4 = solution.removeElementsWithDummy(head4, 6);
        System.out.print("Output: ");
        printList(result4);

        // Test with recursive approach
        System.out.println("\n--- Testing Recursive Approach ---");
        ListNode head5 = createLinkedList(new int[]{1, 2, 6, 3, 4, 5, 6});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 6, 3, 4, 5, 6}));
        ListNode result5 = solution.removeElementsRecursive(head5, 6);
        System.out.print("Output: ");
        printList(result5);

        // Edge case: Remove head node
        System.out.println("\n--- Edge Case: Remove Head ---");
        ListNode head6 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 4, 5}));
        ListNode result6 = solution.removeElements(head6, 1);
        System.out.print("Output: ");
        printList(result6);
    }
}
