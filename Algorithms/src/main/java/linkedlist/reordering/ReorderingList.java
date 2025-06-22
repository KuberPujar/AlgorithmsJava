package linkedlist.reordering;

import linkedlist.ListNode;

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
public class ReorderingList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find the middle of the linked list
        ListNode middle = findMiddle(head);

        // Step 2: Split the list into two halves
        ListNode secondHalf = middle.next;
        middle.next = null; // Break the connection

        // Step 3: Reverse the second half
        ListNode reversedSecondHalf = reverseList(secondHalf);

        // Step 4: Merge the two halves alternately
        mergeLists(head, reversedSecondHalf);
    }

    /**
     * Find the middle node using slow and fast pointer technique
     */
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Move fast pointer 2 steps and slow pointer 1 step
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * Reverse a linked list
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        return prev;
    }

    /**
     * Merge two lists alternately
     * first -> second -> first -> second -> ...
     */
    private void mergeLists(ListNode first, ListNode second) {
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            first.next = second;
            second.next = firstNext;

            first = firstNext;
            second = secondNext;
        }
    }


    public static void main(String[] args) {
        ReorderingList solution = new ReorderingList();

        // Test Example 1: [1,2,3,4] -> [1,4,2,3]
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);

        System.out.println("Before reorder: ");
        printList(head1);
        solution.reorderList(head1);
        System.out.println("After reorder: ");
        printList(head1);
        System.out.println();

        // Test Example 2: [1,2,3,4,5] -> [1,5,2,4,3]
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);

        System.out.println("Before reorder: ");
        printList(head2);
        solution.reorderList(head2);
        System.out.println("After reorder: ");
        printList(head2);
    }

    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
}