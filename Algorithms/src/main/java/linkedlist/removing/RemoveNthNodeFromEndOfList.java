package linkedlist.removing;

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
public class RemoveNthNodeFromEndOfList {
    /**
     * Removes the nth node from the end of the list using two-pointer technique
     * @param head - head of the linked list
     * @param n - position from the end (1-indexed)
     * @return new head of the modified linked list
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases (like removing the head)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Initialize two pointers
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast pointer n+1 steps ahead
        // This creates a gap of n nodes between fast and slow
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        // When fast is at the end, slow will be at the node before the target
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        // Return the new head
        return dummy.next;
    }

    /**
     * Alternative approach: Two-pass solution
     * First pass to count nodes, second pass to remove
     */
    public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
        // First pass: count the total number of nodes
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Calculate the position from the beginning
        int positionFromStart = length - n;

        // Special case: removing the head node
        if (positionFromStart == 0) {
            return head.next;
        }

        // Second pass: find the node before the target and remove the target
        current = head;
        for (int i = 0; i < positionFromStart - 1; i++) {
            current = current.next;
        }

        // Remove the target node
        current.next = current.next.next;

        return head;
    }

    /**
     * Recursive approach
     */
    public ListNode removeNthFromEndRecursive(ListNode head, int n) {
        int[] count = new int[1]; // Using array to pass by reference
        return removeNthHelper(head, n, count);
    }

    private ListNode removeNthHelper(ListNode node, int n, int[] count) {
        if (node == null) {
            return null;
        }

        // Recursively process the rest of the list
        node.next = removeNthHelper(node.next, n, count);

        // Increment count on the way back
        count[0]++;

        // If this is the nth node from the end, skip it
        if (count[0] == n) {
            return node.next;
        }

        return node;
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

    // Helper method to get list length (for testing)
    public static int getListLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    // Test method
    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();

        // Test Example 1: [1,2,3,4,5], n = 2
        System.out.println("Example 1:");
        ListNode head1 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 4, 5}));
        System.out.println("Remove 2nd from end");
        ListNode result1 = solution.removeNthFromEnd(head1, 2);
        System.out.print("Output: ");
        printList(result1);

        // Test Example 2: [1], n = 1
        System.out.println("\nExample 2:");
        ListNode head2 = createLinkedList(new int[]{1});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1}));
        System.out.println("Remove 1st from end");
        ListNode result2 = solution.removeNthFromEnd(head2, 1);
        System.out.print("Output: ");
        printList(result2);

        // Test Example 3: [1,2], n = 1
        System.out.println("\nExample 3:");
        ListNode head3 = createLinkedList(new int[]{1, 2});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2}));
        System.out.println("Remove 1st from end");
        ListNode result3 = solution.removeNthFromEnd(head3, 1);
        System.out.print("Output: ");
        printList(result3);

        // Test removing head node
        System.out.println("\nEdge Case - Remove Head:");
        ListNode head4 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 4, 5}));
        System.out.println("Remove 5th from end (head)");
        ListNode result4 = solution.removeNthFromEnd(head4, 5);
        System.out.print("Output: ");
        printList(result4);

        // Test with two-pass approach
        System.out.println("\n--- Testing Two-Pass Approach ---");
        ListNode head5 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 4, 5}));
        System.out.println("Remove 2nd from end");
        ListNode result5 = solution.removeNthFromEndTwoPass(head5, 2);
        System.out.print("Output: ");
        printList(result5);

        // Test with recursive approach
        System.out.println("\n--- Testing Recursive Approach ---");
        ListNode head6 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2, 3, 4, 5}));
        System.out.println("Remove 2nd from end");
        ListNode result6 = solution.removeNthFromEndRecursive(head6, 2);
        System.out.print("Output: ");
        printList(result6);

        // Test edge case: two nodes, remove first
        System.out.println("\nEdge Case - Two nodes, remove first:");
        ListNode head7 = createLinkedList(new int[]{1, 2});
        System.out.print("Input: ");
        printList(createLinkedList(new int[]{1, 2}));
        System.out.println("Remove 2nd from end (head)");
        ListNode result7 = solution.removeNthFromEnd(head7, 2);
        System.out.print("Output: ");
        printList(result7);
    }
}
