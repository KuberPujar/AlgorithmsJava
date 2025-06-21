package linkedlist.sorting;

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
public class SortList {
    /**
     * Sorts a linked list using merge sort algorithm
     * Time Complexity: O(n log n)
     * Space Complexity: O(1) - iterative approach with constant space
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Get the length of the linked list
        int length = getLength(head);

        // Create a dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Bottom-up merge sort
        for (int size = 1; size < length; size *= 2) {
            ListNode prev = dummy;
            ListNode current = dummy.next;

            while (current != null) {
                // Split the list into two sublists of size 'size'
                ListNode left = current;
                ListNode right = split(left, size);
                current = split(right, size);

                // Merge the two sublists and connect to previous part
                prev = merge(left, right, prev);
            }
        }

        return dummy.next;
    }

    /**
     * Gets the length of the linked list
     */
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * Splits the linked list and returns the head of the second part
     * The first part will have at most 'size' nodes
     */
    private ListNode split(ListNode head, int size) {
        if (head == null) return null;

        // Move to the end of the first part
        for (int i = 1; i < size && head.next != null; i++) {
            head = head.next;
        }

        // Split the list
        ListNode second = head.next;
        head.next = null;
        return second;
    }

    /**
     * Merges two sorted linked lists and connects them to prev
     * Returns the tail of the merged list
     */
    private ListNode merge(ListNode list1, ListNode list2, ListNode prev) {
        ListNode current = prev;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Append remaining nodes
        if (list1 != null) {
            current.next = list1;
            while (current.next != null) {
                current = current.next;
            }
        }
        if (list2 != null) {
            current.next = list2;
            while (current.next != null) {
                current = current.next;
            }
        }

        return current;
    }

    // Alternative recursive approach (uses O(log n) space due to recursion stack)
    public ListNode sortListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Find middle and split the list
        ListNode mid = getMid(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;

        // Recursively sort both halves
        left = sortListRecursive(left);
        right = sortListRecursive(right);

        // Merge sorted halves
        return mergeRecursive(left, right);
    }

    /**
     * Finds the middle node using slow-fast pointer technique
     */
    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return prev;
    }

    /**
     * Merges two sorted linked lists recursively
     */
    private ListNode mergeRecursive(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val <= list2.val) {
            list1.next = mergeRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeRecursive(list1, list2.next);
            return list2;
        }
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
        SortList solution = new SortList();

        // Test case 1: [4,2,1,3] -> [1,2,3,4]
        System.out.println("Test case 1:");
        ListNode head1 = createList(new int[]{4, 2, 1, 3});
        System.out.print("Input: ");
        printList(head1);
        ListNode result1 = solution.sortList(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Test case 2: [-1,5,3,4,0] -> [-1,0,3,4,5]
        System.out.println("Test case 2:");
        ListNode head2 = createList(new int[]{-1, 5, 3, 4, 0});
        System.out.print("Input: ");
        printList(head2);
        ListNode result2 = solution.sortList(head2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Test case 3: [] -> []
        System.out.println("Test case 3:");
        ListNode head3 = createList(new int[]{});
        System.out.print("Input: ");
        printList(head3);
        ListNode result3 = solution.sortList(head3);
        System.out.print("Output: ");
        printList(result3);
        System.out.println();

        // Test case 4: Single element
        System.out.println("Test case 4:");
        ListNode head4 = createList(new int[]{1});
        System.out.print("Input: ");
        printList(head4);
        ListNode result4 = solution.sortList(head4);
        System.out.print("Output: ");
        printList(result4);
        System.out.println();

        // Test case 5: Already sorted
        System.out.println("Test case 5:");
        ListNode head5 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head5);
        ListNode result5 = solution.sortList(head5);
        System.out.print("Output: ");
        printList(result5);
        System.out.println();

        // Test case 6: Reverse sorted
        System.out.println("Test case 6:");
        ListNode head6 = createList(new int[]{5, 4, 3, 2, 1});
        System.out.print("Input: ");
        printList(head6);
        ListNode result6 = solution.sortList(head6);
        System.out.print("Output: ");
        printList(result6);
        System.out.println();

        // Test case 7: Duplicates
        System.out.println("Test case 7:");
        ListNode head7 = createList(new int[]{3, 1, 4, 1, 5, 9, 2, 6});
        System.out.print("Input: ");
        printList(head7);
        ListNode result7 = solution.sortList(head7);
        System.out.print("Output: ");
        printList(result7);

        // Test recursive approach
        System.out.println("\nTesting recursive approach:");
        ListNode head8 = createList(new int[]{4, 2, 1, 3});
        System.out.print("Input: ");
        printList(head8);
        ListNode result8 = solution.sortListRecursive(head8);
        System.out.print("Output: ");
        printList(result8);
    }
}
