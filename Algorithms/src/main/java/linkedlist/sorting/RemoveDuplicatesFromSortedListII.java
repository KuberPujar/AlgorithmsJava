package linkedlist.sorting;

import linkedlist.ListNode;

/*
Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.



Example 1:


Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
Example 2:


Input: head = [1,1,1,2,3]
Output: [2,3]


Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedListII {
    /**
     * Removes all nodes that have duplicate numbers from a sorted linked list
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - only using constant extra space
     */
    public ListNode deleteDuplicates(ListNode head) {
        // Create a dummy node to handle edge cases where head needs to be removed
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;  // Previous node to current
        ListNode current = head;  // Current node being examined

        while (current != null) {
            // Check if current node has duplicates
            if (current.next != null && current.val == current.next.val) {
                // Skip all nodes with the same value
                int duplicateValue = current.val;
                while (current != null && current.val == duplicateValue) {
                    current = current.next;
                }
                // Connect previous node to the next distinct node
                prev.next = current;
            } else {
                // No duplicate found, move prev pointer
                prev = current;
                current = current.next;
            }
        }

        return dummy.next;
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
        RemoveDuplicatesFromSortedListII  solution = new RemoveDuplicatesFromSortedListII ();

        // Test case 1: [1,2,3,3,4,4,5] -> [1,2,5]
        System.out.println("Test case 1:");
        ListNode head1 = createList(new int[]{1, 2, 3, 3, 4, 4, 5});
        System.out.print("Input: ");
        printList(head1);
        ListNode result1 = solution.deleteDuplicates(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Test case 2: [1,1,1,2,3] -> [2,3]
        System.out.println("Test case 2:");
        ListNode head2 = createList(new int[]{1, 1, 1, 2, 3});
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
        ListNode head5 = createList(new int[]{1, 1, 2, 2, 3, 3});
        System.out.print("Input: ");
        printList(head5);
        ListNode result5 = solution.deleteDuplicates(head5);
        System.out.print("Output: ");
        printList(result5);
        System.out.println();

        // Test case 6: No duplicates
        System.out.println("Test case 6:");
        ListNode head6 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head6);
        ListNode result6 = solution.deleteDuplicates(head6);
        System.out.print("Output: ");
        printList(result6);
        System.out.println();

        // Test case 7: Head has duplicates
        System.out.println("Test case 7:");
        ListNode head7 = createList(new int[]{1, 1, 2, 3});
        System.out.print("Input: ");
        printList(head7);
        ListNode result7 = solution.deleteDuplicates(head7);
        System.out.print("Output: ");
        printList(result7);
    }
}
