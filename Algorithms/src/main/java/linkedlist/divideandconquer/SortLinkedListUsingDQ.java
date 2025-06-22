package linkedlist.divideandconquer;

import linkedlist.ListNode;

/*
Given the head of a linked list, return the list after sorting it in ascending order.



Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:


Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []


Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-105 <= Node.val <= 105


Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortLinkedListUsingDQ {
    /**
     * Sorts a linked list using merge sort (divide and conquer approach)
     * Time Complexity: O(n log n)
     * Space Complexity: O(1) - constant extra space
     *
     * @param head The head of the linked list to sort
     * @return The head of the sorted linked list
     */
    public ListNode sortList(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Divide: Split the list into two halves
        ListNode mid = getMiddle(head);
        ListNode secondHalf = mid.next;
        mid.next = null; // Break the connection

        // Conquer: Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(secondHalf);

        // Combine: Merge the sorted halves
        return merge(left, right);
    }

    /**
     * Finds the middle node of the linked list using slow-fast pointer technique
     * Returns the last node of the first half
     */
    private ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        // Move fast pointer 2 steps and slow pointer 1 step
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return prev; // Return the last node of first half
    }

    /**
     * Merges two sorted linked lists into one sorted list
     *
     * @param l1 First sorted linked list
     * @param l2 Second sorted linked list
     * @return Head of the merged sorted list
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge the two lists by comparing values
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Append remaining nodes
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return dummy.next;
    }

    // Helper method to create a linked list from array for testing
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

    // Helper method to print linked list
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

    // Test the implementation
    public static void main(String[] args) {
        SortLinkedListUsingDQ solution = new SortLinkedListUsingDQ();

        // Example 1: [4,2,1,3]
        int[] arr1 = {4, 2, 1, 3};
        ListNode head1 = createList(arr1);
        System.out.print("Input: ");
        printList(head1);
        ListNode sorted1 = solution.sortList(head1);
        System.out.print("Output: ");
        printList(sorted1);
        System.out.println();

        // Example 2: [-1,5,3,4,0]
        int[] arr2 = {-1, 5, 3, 4, 0};
        ListNode head2 = createList(arr2);
        System.out.print("Input: ");
        printList(head2);
        ListNode sorted2 = solution.sortList(head2);
        System.out.print("Output: ");
        printList(sorted2);
        System.out.println();

        // Example 3: []
        int[] arr3 = {};
        ListNode head3 = createList(arr3);
        System.out.print("Input: ");
        printList(head3);
        ListNode sorted3 = solution.sortList(head3);
        System.out.print("Output: ");
        printList(sorted3);
    }
}
