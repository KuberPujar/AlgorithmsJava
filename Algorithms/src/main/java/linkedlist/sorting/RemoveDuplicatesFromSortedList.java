package linkedlist.sorting;
/*
    Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.



Example 1:


Input: head = [1,1,2]
Output: [1,2]
Example 2:


Input: head = [1,1,2,3,3]
Output: [1,2,3]


Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
 */

import linkedlist.ListNode;

public class RemoveDuplicatesFromSortedList {
    /**
     * Removes duplicates from a sorted linked list
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - only using constant extra space
     */
    public ListNode deleteDuplicates(ListNode head) {
        // Handle edge case: empty list
        if (head == null) {
            return null;
        }

        ListNode current = head;

        // Traverse the list
        while (current != null && current.next != null) {
            // If current node's value equals next node's value
            if (current.val == current.next.val) {
                // Skip the duplicate node
                current.next = current.next.next;
            } else {
                // Move to next node only if no duplicate was found
                current = current.next;
            }
        }

        return head;
    }

    // Helper method to create a linked list from array (for testing)
    public static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    // Helper method to print linked list (for testing)
    public static void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    // Test the solution
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList solution = new RemoveDuplicatesFromSortedList();

        // Test case 1: [1,1,2] -> [1,2]
        System.out.println("Test case 1:");
        ListNode head1 = createList(new int[]{1, 1, 2});
        System.out.print("Input: ");
        printList(head1);
        ListNode result1 = solution.deleteDuplicates(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Test case 2: [1,1,2,3,3] -> [1,2,3]
        System.out.println("Test case 2:");
        ListNode head2 = createList(new int[]{1, 1, 2, 3, 3});
        System.out.print("Input: ");
        printList(head2);
        ListNode result2 = solution.deleteDuplicates(head2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Test case 3: Empty list
        System.out.println("Test case 3:");
        ListNode head3 = createList(new int[]{});
        System.out.print("Input: ");
        printList(head3);
        ListNode result3 = solution.deleteDuplicates(head3);
        System.out.print("Output: ");
        printList(result3);
        System.out.println();

        // Test case 4: Single element
        System.out.println("Test case 4:");
        ListNode head4 = createList(new int[]{1});
        System.out.print("Input: ");
        printList(head4);
        ListNode result4 = solution.deleteDuplicates(head4);
        System.out.print("Output: ");
        printList(result4);
        System.out.println();

        // Test case 5: All duplicates
        System.out.println("Test case 5:");
        ListNode head5 = createList(new int[]{1, 1, 1, 1});
        System.out.print("Input: ");
        printList(head5);
        ListNode result5 = solution.deleteDuplicates(head5);
        System.out.print("Output: ");
        printList(result5);
    }
}
