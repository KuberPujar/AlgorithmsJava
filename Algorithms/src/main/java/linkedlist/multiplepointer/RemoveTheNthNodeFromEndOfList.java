package linkedlist.multiplepointer;

import linkedlist.ListNode;

/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.



Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

 */
public class RemoveTheNthNodeFromEndOfList {
    /**
     * Remove nth node from end using two-pointer technique
     *
     * Strategy: Maintain gap of n between two pointers
     * - Fast pointer moves n steps ahead first
     * - Then both pointers move together
     * - When fast reaches end, slow is at node before target
     *
     * Time Complexity: O(sz) - single pass
     * Space Complexity: O(1) - only two pointers
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create dummy node to handle edge cases (removing head)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Two pointers with gap of n
        ListNode slow = dummy;
        ListNode fast = dummy;

        // Move fast pointer n+1 steps ahead to create gap
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches end
        // slow will be at node before the target node
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the nth node from end
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        RemoveTheNthNodeFromEndOfList solution = new RemoveTheNthNodeFromEndOfList();

        // Test Example 1: [1,2,3,4,5], n=2 -> [1,2,3,5]
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Example 1:");
        System.out.print("Input:  ");
        printList(head1);
        System.out.println("Remove 2nd from end");
        ListNode result1 = solution.removeNthFromEnd(head1, 2);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Test Example 2: [1], n=1 -> []
        ListNode head2 = createList(new int[]{1});
        System.out.println("Example 2:");
        System.out.print("Input:  ");
        printList(head2);
        System.out.println("Remove 1st from end");
        ListNode result2 = solution.removeNthFromEnd(head2, 1);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Test Example 3: [1,2], n=1 -> [1]
        ListNode head3 = createList(new int[]{1, 2});
        System.out.println("Example 3:");
        System.out.print("Input:  ");
        printList(head3);
        System.out.println("Remove 1st from end");
        ListNode result3 = solution.removeNthFromEnd(head3, 1);
        System.out.print("Output: ");
        printList(result3);
        System.out.println();

        // Additional test cases
        System.out.println("Additional Test Cases:");

        // Remove first node (head): [1,2,3], n=3 -> [2,3]
        ListNode head4 = createList(new int[]{1, 2, 3});
        System.out.print("Remove 3rd from end [1,2,3]: ");
        printList(solution.removeNthFromEnd(head4, 3));

        // Remove middle node: [1,2,3,4,5], n=3 -> [1,2,4,5]
        ListNode head5 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Remove 3rd from end [1,2,3,4,5]: ");
        printList(solution.removeNthFromEnd(head5, 3));

        // Remove last node: [1,2,3,4], n=1 -> [1,2,3]
        ListNode head6 = createList(new int[]{1, 2, 3, 4});
        System.out.print("Remove 1st from end [1,2,3,4]: ");
        printList(solution.removeNthFromEnd(head6, 1));

        // Two nodes, remove head: [1,2], n=2 -> [2]
        ListNode head7 = createList(new int[]{1, 2});
        System.out.print("Remove 2nd from end [1,2]: ");
        printList(solution.removeNthFromEnd(head7, 2));
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
