package linkedlist.recursion;

import linkedlist.ListNode;

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
public class RemoveDuplicatesFromSortedList {
    /**
     * Recursive approach to remove duplicates from sorted linked list
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(n) - due to recursion stack in worst case
     *
     * Algorithm:
     * 1. Base case: if head is null, return null
     * 2. Recursively process the rest of the list
     * 3. If current node's value equals next node's value, skip current node
     * 4. Otherwise, keep current node and connect to processed rest
     * 5. Return the head of processed list
     */
    public ListNode deleteDuplicates(ListNode head) {
        // Base case: empty list
        if (head == null) {
            return null;
        }

        // Recursively process the rest of the list
        head.next = deleteDuplicates(head.next);

        // If current node has same value as next node, skip current node
        if (head.next != null && head.val == head.next.val) {
            return head.next; // Skip current node, return next
        }

        // Otherwise, keep current node
        return head;
    }

    /**
     * Alternative recursive approach - process from the perspective of keeping unique nodes
     * This version is more intuitive in terms of logic flow
     */
    public ListNode deleteDuplicatesAlternative(ListNode head) {
        // Base case: if head is null or only one node, return head
        if (head == null || head.next == null) {
            return head;
        }

        // If current and next nodes have same value
        if (head.val == head.next.val) {
            // Skip the duplicate and recursively process from next node
            return deleteDuplicatesAlternative(head.next);
        } else {
            // Keep current node and recursively process rest
            head.next = deleteDuplicatesAlternative(head.next);
            return head;
        }
    }

    // Helper method to create a linked list from an array
    public static ListNode createList(int[] values) {
        if (values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        boolean first = true;
        while (current != null) {
            if (!first) {
                System.out.print(",");
            }
            System.out.print(current.val);
            first = false;
            current = current.next;
        }
        System.out.println("]");
    }

    // Helper method to convert linked list to array for easy comparison
    public static int[] listToArray(ListNode head) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        ListNode current = head;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // Test method with all examples
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList solution = new RemoveDuplicatesFromSortedList();

        // Example 1: [1,1,2] -> [1,2]
        System.out.println("Example 1:");
        ListNode head1 = createList(new int[]{1, 1, 2});
        System.out.print("Input:  ");
        printList(head1);
        ListNode result1 = solution.deleteDuplicates(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Example 2: [1,1,2,3,3] -> [1,2,3]
        System.out.println("Example 2:");
        ListNode head2 = createList(new int[]{1, 1, 2, 3, 3});
        System.out.print("Input:  ");
        printList(head2);
        ListNode result2 = solution.deleteDuplicates(head2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Additional test cases
        System.out.println("Additional Test Cases:");

        // Empty list
        System.out.println("Empty list:");
        ListNode head3 = createList(new int[]{});
        System.out.print("Input:  ");
        printList(head3);
        ListNode result3 = solution.deleteDuplicates(head3);
        System.out.print("Output: ");
        printList(result3);
        System.out.println();

        // Single node
        System.out.println("Single node:");
        ListNode head4 = createList(new int[]{1});
        System.out.print("Input:  ");
        printList(head4);
        ListNode result4 = solution.deleteDuplicates(head4);
        System.out.print("Output: ");
        printList(result4);
        System.out.println();

        // All duplicates
        System.out.println("All duplicates:");
        ListNode head5 = createList(new int[]{1, 1, 1, 1});
        System.out.print("Input:  ");
        printList(head5);
        ListNode result5 = solution.deleteDuplicates(head5);
        System.out.print("Output: ");
        printList(result5);
        System.out.println();

        // No duplicates
        System.out.println("No duplicates:");
        ListNode head6 = createList(new int[]{1, 2, 3, 4});
        System.out.print("Input:  ");
        printList(head6);
        ListNode result6 = solution.deleteDuplicates(head6);
        System.out.print("Output: ");
        printList(result6);
        System.out.println();

        // Testing alternative approach
        System.out.println("Testing alternative recursive approach:");
        ListNode head7 = createList(new int[]{1, 1, 2, 3, 3, 4});
        System.out.print("Input:  ");
        printList(head7);
        ListNode result7 = solution.deleteDuplicatesAlternative(head7);
        System.out.print("Output: ");
        printList(result7);
    }
}
