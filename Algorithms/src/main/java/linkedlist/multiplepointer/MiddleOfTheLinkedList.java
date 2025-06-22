package linkedlist.multiplepointer;

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
public class MiddleOfTheLinkedList {
    /**
     * Find the middle node of a linked list using slow and fast pointer technique.
     * If there are two middle nodes, return the second one.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode middleNode(ListNode head) {
        // Handle edge case (though constraints guarantee at least 1 node)
        if (head == null) {
            return null;
        }

        ListNode slow = head;  // Moves 1 step at a time
        ListNode fast = head;  // Moves 2 steps at a time

        // When fast reaches the end, slow will be at the middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        MiddleOfTheLinkedList solution = new MiddleOfTheLinkedList();

        // Test Example 1: [1,2,3,4,5] -> [3,4,5]
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Example 1:");
        System.out.print("Input:  ");
        printList(head1);
        ListNode result1 = solution.middleNode(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println("Middle node value: " + result1.val);
        System.out.println();

        // Test Example 2: [1,2,3,4,5,6] -> [4,5,6]
        ListNode head2 = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Example 2:");
        System.out.print("Input:  ");
        printList(head2);
        ListNode result2 = solution.middleNode(head2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println("Middle node value: " + result2.val);
        System.out.println();

        // Additional test cases
        System.out.println("Additional Test Cases:");

        // Single node
        ListNode single = new ListNode(1);
        System.out.print("Single node [1]: ");
        printList(solution.middleNode(single));

        // Two nodes
        ListNode two = createList(new int[]{1, 2});
        System.out.print("Two nodes [1,2]: ");
        printList(solution.middleNode(two));

        // Three nodes
        ListNode three = createList(new int[]{1, 2, 3});
        System.out.print("Three nodes [1,2,3]: ");
        printList(solution.middleNode(three));

        // Four nodes
        ListNode four = createList(new int[]{1, 2, 3, 4});
        System.out.print("Four nodes [1,2,3,4]: ");
        printList(solution.middleNode(four));
    }

    /**
     * Helper method to create a linked list from an array
     */
    private static ListNode createList(int[] values) {
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
     * Helper method to print a linked list
     */
    private static void printList(ListNode head) {
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
