package linkedlist.multiplepointer;

import linkedlist.ListNode;
import linkedlist.removing.DeleteNodeInLinkedList;

/*
You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.


Example 1:


Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node.
Example 2:


Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.
Example 3:


Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.


Constraints:

The number of nodes in the list is in the range [1, 105].
1 <= Node.val <= 105
 */
public class DeleteTheMiddleNodeOfLinkedList {
    /**
     * Delete the middle node of a linked list.
     * Middle node is at index ⌊n/2⌋ using 0-based indexing.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode deleteMiddle(ListNode head) {
        // Handle edge case: single node
        if (head == null || head.next == null) {
            return null;
        }

        // Use two pointers to find the middle node
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;  // Keep track of node before middle

        // Move fast pointer 2 steps and slow pointer 1 step
        // prev will point to the node before the middle node
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete the middle node by updating prev.next
        prev.next = slow.next;

        return head;
    }

    public static void main(String[] args) {
        DeleteTheMiddleNodeOfLinkedList solution = new DeleteTheMiddleNodeOfLinkedList();

        // Test Example 1: [1,3,4,7,1,2,6] -> [1,3,4,1,2,6]
        // n=7, middle index = ⌊7/2⌋ = 3, value = 7
        ListNode head1 = createList(new int[]{1, 3, 4, 7, 1, 2, 6});
        System.out.println("Example 1:");
        System.out.print("Input:  ");
        printListWithIndices(head1);
        ListNode result1 = solution.deleteMiddle(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println("Deleted middle node at index 3 (value 7)");
        System.out.println();

        // Test Example 2: [1,2,3,4] -> [1,2,4]
        // n=4, middle index = ⌊4/2⌋ = 2, value = 3
        ListNode head2 = createList(new int[]{1, 2, 3, 4});
        System.out.println("Example 2:");
        System.out.print("Input:  ");
        printListWithIndices(head2);
        ListNode result2 = solution.deleteMiddle(head2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println("Deleted middle node at index 2 (value 3)");
        System.out.println();

        // Test Example 3: [2,1] -> [2]
        // n=2, middle index = ⌊2/2⌋ = 1, value = 1
        ListNode head3 = createList(new int[]{2, 1});
        System.out.println("Example 3:");
        System.out.print("Input:  ");
        printListWithIndices(head3);
        ListNode result3 = solution.deleteMiddle(head3);
        System.out.print("Output: ");
        printList(result3);
        System.out.println("Deleted middle node at index 1 (value 1)");
        System.out.println();

        // Additional test cases
        System.out.println("Additional Test Cases:");

        // Single node: [1] -> []
        ListNode single = new ListNode(1);
        System.out.print("Single node [1]: ");
        printList(solution.deleteMiddle(single));

        // Three nodes: [1,2,3] -> [1,3]
        // n=3, middle index = ⌊3/2⌋ = 1, value = 2
        ListNode three = createList(new int[]{1, 2, 3});
        System.out.print("Three nodes [1,2,3]: ");
        printList(solution.deleteMiddle(three));

        // Five nodes: [1,2,3,4,5] -> [1,2,4,5]
        // n=5, middle index = ⌊5/2⌋ = 2, value = 3
        ListNode five = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Five nodes [1,2,3,4,5]: ");
        printList(solution.deleteMiddle(five));

        // Six nodes: [1,2,3,4,5,6] -> [1,2,4,5,6]
        // n=6, middle index = ⌊6/2⌋ = 3, value = 4
        ListNode six = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Six nodes [1,2,3,4,5,6]: ");
        printList(solution.deleteMiddle(six));
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

    /**
     * Helper method to print a linked list with indices
     */
    private static void printListWithIndices(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        ListNode current = head;
        int index = 0;
        while (current != null) {
            System.out.print(current.val + "(i=" + index + ")");
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
            index++;
        }
        System.out.println("]");
    }
}
