package linkedlist.multiplepointer;

import linkedlist.ListNode;

/*
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.



Example 1:


Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]


Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
 */
public class PartitionList  {
    public ListNode partition(ListNode head, int x) {
        // Create dummy nodes for two separate lists
        ListNode lessHead = new ListNode(0);  // For nodes < x
        ListNode greaterHead = new ListNode(0);  // For nodes >= x

        // Pointers to track the end of each list
        ListNode less = lessHead;
        ListNode greater = greaterHead;

        // Traverse the original list
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                // Add to the "less than x" list
                less.next = current;
                less = less.next;
            } else {
                // Add to the "greater than or equal to x" list
                greater.next = current;
                greater = greater.next;
            }
            current = current.next;
        }

        // Connect the two lists
        less.next = greaterHead.next;

        // Important: Set the end of the greater list to null
        // to avoid cycles in the linked list
        greater.next = null;

        // Return the head of the partitioned list (skip dummy node)
        return lessHead.next;
    }
}

// Helper class for testing
class LinkedListHelper {
    // Create linked list from array
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

    // Print linked list
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
        PartitionList solution = new PartitionList();

        // Test Case 1: [1,4,3,2,5,2], x = 3
        System.out.println("Test Case 1:");
        int[] arr1 = {1, 4, 3, 2, 5, 2};
        ListNode head1 = createList(arr1);
        System.out.print("Input: ");
        printList(head1);
        System.out.println("x = 3");

        ListNode result1 = solution.partition(head1, 3);
        System.out.print("Output: ");
        printList(result1);
        System.out.println("Expected: [1,2,2,4,3,5]");
        System.out.println();

        // Test Case 2: [2,1], x = 2
        System.out.println("Test Case 2:");
        int[] arr2 = {2, 1};
        ListNode head2 = createList(arr2);
        System.out.print("Input: ");
        printList(head2);
        System.out.println("x = 2");

        ListNode result2 = solution.partition(head2, 2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println("Expected: [1,2]");
        System.out.println();

        // Test Case 3: Empty list
        System.out.println("Test Case 3:");
        System.out.println("Input: []");
        System.out.println("x = 1");
        ListNode result3 = solution.partition(null, 1);
        System.out.print("Output: ");
        printList(result3);
        System.out.println("Expected: []");
    }
}
