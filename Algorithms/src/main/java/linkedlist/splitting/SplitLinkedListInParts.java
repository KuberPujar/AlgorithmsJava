package linkedlist.splitting;

import linkedlist.ListNode;

/*
Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.

The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.

The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.

Return an array of the k parts.



Example 1:


Input: head = [1,2,3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but its string representation as a ListNode is [].
Example 2:


Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
Output: [[1,2,3,4],[5,6,7],[8,9,10]]
Explanation:
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.


Constraints:

The number of nodes in the list is in the range [0, 1000].
0 <= Node.val <= 1000
1 <= k <= 50
 */
public class SplitLinkedListInParts {
    public ListNode[] splitListToParts(ListNode head, int k) {
        // First, count the total number of nodes
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Calculate the size of each part
        int baseSize = length / k;  // Minimum size each part should have
        int extraNodes = length % k;  // Number of parts that get one extra node

        // Initialize the result array
        ListNode[] result = new ListNode[k];

        current = head;

        // Create each part
        for (int i = 0; i < k; i++) {
            // If current is null, remaining parts will be null
            if (current == null) {
                result[i] = null;
                continue;
            }

            // Start of current part
            result[i] = current;

            // Calculate size of current part
            // First 'extraNodes' parts get baseSize + 1 nodes
            // Remaining parts get baseSize nodes
            int currentPartSize = baseSize + (i < extraNodes ? 1 : 0);

            // Move to the end of current part
            for (int j = 0; j < currentPartSize - 1; j++) {
                if (current != null) {
                    current = current.next;
                }
            }

            // Break the link to next part
            if (current != null) {
                ListNode nextPartStart = current.next;
                current.next = null;
                current = nextPartStart;
            }
        }

        return result;
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
            System.out.print("[]");
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
        System.out.print("]");
    }

    // Test method
    public static void main(String[] args) {
        SplitLinkedListInParts solution = new SplitLinkedListInParts();

        // Test Example 1: [1,2,3], k = 5
        System.out.println("Example 1:");
        ListNode head1 = createLinkedList(new int[]{1, 2, 3});
        ListNode[] result1 = solution.splitListToParts(head1, 5);
        System.out.print("Output: [");
        for (int i = 0; i < result1.length; i++) {
            printList(result1[i]);
            if (i < result1.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");

        // Test Example 2: [1,2,3,4,5,6,7,8,9,10], k = 3
        System.out.println("\nExample 2:");
        ListNode head2 = createLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        ListNode[] result2 = solution.splitListToParts(head2, 3);
        System.out.print("Output: [");
        for (int i = 0; i < result2.length; i++) {
            printList(result2[i]);
            if (i < result2.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");

        // Test edge case: empty list
        System.out.println("\nEdge case - Empty list:");
        ListNode head3 = null;
        ListNode[] result3 = solution.splitListToParts(head3, 3);
        System.out.print("Output: [");
        for (int i = 0; i < result3.length; i++) {
            printList(result3[i]);
            if (i < result3.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
