package linkedlist.reordering;

import linkedlist.ListNode;

/*
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.



Example 1:


Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
Example 2:


Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]


Constraints:

The number of nodes in the linked list is in the range [0, 104].
-106 <= Node.val <= 106
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        // Handle edge cases
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize pointers for odd and even lists
        ListNode odd = head;           // Start with first node (odd index)
        ListNode even = head.next;     // Start with second node (even index)
        ListNode evenHead = even;      // Keep reference to start of even list

        // Traverse and separate odd and even indexed nodes
        while (even != null && even.next != null) {
            // Connect current odd node to next odd node
            odd.next = even.next;
            odd = odd.next;

            // Connect current even node to next even node
            even.next = odd.next;
            even = even.next;
        }

        // Connect the end of odd list to the start of even list
        odd.next = evenHead;

        return head;
    }

    public static void main(String[] args) {
        OddEvenLinkedList solution = new OddEvenLinkedList();

        // Test Example 1: [1,2,3,4,5] -> [1,3,5,2,4]
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Example 1:");
        System.out.print("Input:  ");
        printList(head1);
        ListNode result1 = solution.oddEvenList(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Test Example 2: [2,1,3,5,6,4,7] -> [2,3,6,7,1,5,4]
        ListNode head2 = createList(new int[]{2, 1, 3, 5, 6, 4, 7});
        System.out.println("Example 2:");
        System.out.print("Input:  ");
        printList(head2);
        ListNode result2 = solution.oddEvenList(head2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Test edge cases
        System.out.println("Edge Cases:");

        // Empty list
        ListNode empty = null;
        System.out.print("Empty list: ");
        printList(solution.oddEvenList(empty));

        // Single node
        ListNode single = new ListNode(1);
        System.out.print("Single node: ");
        printList(solution.oddEvenList(single));

        // Two nodes
        ListNode two = createList(new int[]{1, 2});
        System.out.print("Two nodes: ");
        printList(solution.oddEvenList(two));
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
