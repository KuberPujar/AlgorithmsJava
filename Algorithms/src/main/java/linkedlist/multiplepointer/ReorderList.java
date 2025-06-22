package linkedlist.multiplepointer;

import linkedlist.ListNode;
import linkedlist.reordering.ReorderingList;

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
public class ReorderList {
    /**
     * Reorder linked list using pure two-pointer technique throughout
     * L0 → L1 → … → Ln-1 → Ln becomes L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
     *
     * Phase 1: Two-pointer to find middle (slow/fast)
     * Phase 2: Two-pointer to reverse second half (prev/current)
     * Phase 3: Two-pointer to merge alternately (first/second)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Phase 1: Two-pointer technique to find middle
        ListNode slow = head;
        ListNode fast = head;

        // Fast moves 2x speed of slow - when fast reaches end, slow is at middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split: second half starts after middle
        ListNode secondHalf = slow.next;
        slow.next = null;

        // Phase 2: Two-pointer technique to reverse second half
        ListNode prev = null;
        ListNode current = secondHalf;

        // Reverse using two pointers moving forward
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Phase 3: Two-pointer technique to merge alternately
        ListNode first = head;
        ListNode second = prev; // prev is now head of reversed second half

        // Merge using two pointers advancing through both lists
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            // Connect: first → second → firstNext
            first.next = second;
            second.next = firstNext;

            // Advance both pointers
            first = firstNext;
            second = secondNext;
        }
    }

    public static void main(String[] args) {
        ReorderingList solution = new ReorderingList();

        // Test Example 1: [1,2,3,4] -> [1,4,2,3]
        ListNode head1 = createList(new int[]{1, 2, 3, 4});
        System.out.println("Example 1:");
        System.out.print("Input:  ");
        printList(head1);
        solution.reorderList(head1);
        System.out.print("Output: ");
        printList(head1);
        System.out.println();

        // Test Example 2: [1,2,3,4,5] -> [1,5,2,4,3]
        ListNode head2 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Example 2:");
        System.out.print("Input:  ");
        printList(head2);
        solution.reorderList(head2);
        System.out.print("Output: ");
        printList(head2);
        System.out.println();

        // Additional test cases
        System.out.println("Additional Test Cases:");

        // Single node: [1] -> [1]
        ListNode single = new ListNode(1);
        System.out.print("Single node [1]: ");
        solution.reorderList(single);
        printList(single);

        // Two nodes: [1,2] -> [1,2]
        ListNode two = createList(new int[]{1, 2});
        System.out.print("Two nodes [1,2]: ");
        solution.reorderList(two);
        printList(two);

        // Three nodes: [1,2,3] -> [1,3,2]
        ListNode three = createList(new int[]{1, 2, 3});
        System.out.print("Three nodes [1,2,3]: ");
        solution.reorderList(three);
        printList(three);

        // Six nodes: [1,2,3,4,5,6] -> [1,6,2,5,3,4]
        ListNode six = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Six nodes [1,2,3,4,5,6]: ");
        solution.reorderList(six);
        printList(six);

        // Seven nodes: [1,2,3,4,5,6,7] -> [1,7,2,6,3,5,4]
        ListNode seven = createList(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.print("Seven nodes [1,2,3,4,5,6,7]: ");
        solution.reorderList(seven);
        printList(seven);
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
