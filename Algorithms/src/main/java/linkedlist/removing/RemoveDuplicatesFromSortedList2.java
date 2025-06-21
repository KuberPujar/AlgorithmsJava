package linkedlist.removing;

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
public class RemoveDuplicatesFromSortedList2 {
    /**
     * Removes all nodes that have duplicate numbers from a sorted linked list
     * @param head - head of the sorted linked list
     * @return new head of the modified linked list with only distinct numbers
     */
    public ListNode deleteDuplicates(ListNode head) {
        // Handle empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Create a dummy node to handle edge cases (like removing head)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev points to the last node in the result list
        ListNode prev = dummy;
        ListNode current = head;

        while (current != null) {
            // Check if current node has duplicates
            if (current.next != null && current.val == current.next.val) {
                // Skip all nodes with the same value
                int duplicateValue = current.val;
                while (current != null && current.val == duplicateValue) {
                    current = current.next;
                }
                // Connect prev to the next different node
                prev.next = current;
            } else {
                // No duplicates, move prev pointer
                prev = current;
                current = current.next;
            }
        }

        return dummy.next;
    }

    /**
     * Alternative approach using a flag to track duplicates
     */
    public ListNode deleteDuplicatesWithFlag(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head;

        while (current != null) {
            boolean hasDuplicate = false;

            // Check if current value has duplicates
            while (current.next != null && current.val == current.next.val) {
                current = current.next;
                hasDuplicate = true;
            }

            if (hasDuplicate) {
                // Skip all nodes with duplicate values
                prev.next = current.next;
            } else {
                // Keep the node, move prev pointer
                prev = current;
            }

            current = current.next;
        }

        return dummy.next;
    }

    /**
     * Recursive approach
     */
    public ListNode deleteDuplicatesRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // If current node has duplicates
        if (head.val == head.next.val) {
            int duplicateValue = head.val;
            // Skip all nodes with the same value
            while (head != null && head.val == duplicateValue) {
                head = head.next;
            }
            // Recursively process the rest
            return deleteDuplicatesRecursive(head);
        } else {
            // No duplicates for current node, keep it and process rest
            head.next = deleteDuplicatesRecursive(head.next);
            return head;
        }
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

    // Helper method to convert list to array for easier comparison (for testing)
    public static int[] listToArray(ListNode head) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    // Test method
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList2 solution = new RemoveDuplicatesFromSortedList2();

        // Test Example 1: [1,2,3,3,4,4,5]
        System.out.println("Example 1:");
        ListNode head1 = createLinkedList(new int[]{1, 2, 3, 3, 4, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 3, 4, 4, 5}));
        ListNode result1 = solution.deleteDuplicates(head1);
        System.out.print("Output: ");
        printList(result1);

        // Test Example 2: [1,1,1,2,3]
        System.out.println("\nExample 2:");
        ListNode head2 = createLinkedList(new int[]{1, 1, 1, 2, 3});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 1, 1, 2, 3}));
        ListNode result2 = solution.deleteDuplicates(head2);
        System.out.print("Output: ");
        printList(result2);

        // Test Edge Case: Empty list
        System.out.println("\nEdge Case - Empty List:");
        ListNode head3 = createLinkedList(new int[]{});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{}));
        ListNode result3 = solution.deleteDuplicates(head3);
        System.out.print("Output: ");
        printList(result3);

        // Test Edge Case: All duplicates
        System.out.println("\nEdge Case - All Duplicates:");
        ListNode head4 = createLinkedList(new int[]{1, 1, 1, 1});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 1, 1, 1}));
        ListNode result4 = solution.deleteDuplicates(head4);
        System.out.print("Output: ");
        printList(result4);

        // Test Edge Case: No duplicates
        System.out.println("\nEdge Case - No Duplicates:");
        ListNode head5 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 4, 5}));
        ListNode result5 = solution.deleteDuplicates(head5);
        System.out.print("Output: ");
        printList(result5);

        // Test with flag approach
        System.out.println("\n--- Testing Flag Approach ---");
        ListNode head6 = createLinkedList(new int[]{1, 2, 3, 3, 4, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 3, 4, 4, 5}));
        ListNode result6 = solution.deleteDuplicatesWithFlag(head6);
        System.out.print("Output: ");
        printList(result6);

        // Test with recursive approach
        System.out.println("\n--- Testing Recursive Approach ---");
        ListNode head7 = createLinkedList(new int[]{1, 2, 3, 3, 4, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 3, 4, 4, 5}));
        ListNode result7 = solution.deleteDuplicatesRecursive(head7);
        System.out.print("Output: ");
        printList(result7);

        // Test Edge Case: Single node
        System.out.println("\nEdge Case - Single Node:");
        ListNode head8 = createLinkedList(new int[]{1});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1}));
        ListNode result8 = solution.deleteDuplicates(head8);
        System.out.print("Output: ");
        printList(result8);

        // Test Edge Case: Duplicates at beginning
        System.out.println("\nEdge Case - Duplicates at Beginning:");
        ListNode head9 = createLinkedList(new int[]{1, 1, 2, 3, 4});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 1, 2, 3, 4}));
        ListNode result9 = solution.deleteDuplicates(head9);
        System.out.print("Output: ");
        printList(result9);

        // Test Edge Case: Duplicates at end
        System.out.println("\nEdge Case - Duplicates at End:");
        ListNode head10 = createLinkedList(new int[]{1, 2, 3, 4, 4});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 4, 4}));
        ListNode result10 = solution.deleteDuplicates(head10);
        System.out.print("Output: ");
        printList(result10);
    }
}
