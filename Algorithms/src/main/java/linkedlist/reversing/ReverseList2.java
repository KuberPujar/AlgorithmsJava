package linkedlist.reversing;

import linkedlist.ListNode;

/*
Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.



Example 1:


Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
Example 2:

Input: head = [5], left = 1, right = 1
Output: [5]


Constraints:

The number of nodes in the list is n.
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n


Follow up: Could you do it in one pass?
 */
public class ReverseList2 {
    /**
     * Reverses nodes from position left to right in one pass
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - only using constant extra space
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        // Create dummy node to handle edge case where left = 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 1: Find the node before the left position
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // Step 2: Start reversing from left position
        ListNode current = prev.next;  // This is the node at 'left' position
        ListNode nextNode = current.next;

        // Step 3: Reverse nodes from left to right
        for (int i = 0; i < right - left; i++) {
            current.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
            nextNode = current.next;
        }

        return dummy.next;
    }

    /**
     * Alternative approach: Two-pass solution (easier to understand)
     * First pass: locate the segment, Second pass: reverse the segment
     */
    public ListNode reverseBetweenTwoPass(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 1: Find the node before left position
        ListNode prevLeft = dummy;
        for (int i = 0; i < left - 1; i++) {
            prevLeft = prevLeft.next;
        }

        // Step 2: Find the left node and collect nodes to reverse
        ListNode leftNode = prevLeft.next;
        ListNode current = leftNode;

        // Step 3: Find the right node and the node after right
        for (int i = 0; i < right - left; i++) {
            current = current.next;
        }
        ListNode rightNode = current;
        ListNode postRight = rightNode.next;

        // Step 4: Disconnect the segment
        prevLeft.next = null;
        rightNode.next = null;

        // Step 5: Reverse the segment
        ListNode reversedHead = reverseList(leftNode);

        // Step 6: Reconnect the segments
        prevLeft.next = reversedHead;
        leftNode.next = postRight;  // leftNode is now the tail after reversal

        return dummy.next;
    }

    /**
     * Helper method to reverse a complete linked list
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    /**
     * Recursive approach for partial reversal
     */
    public ListNode reverseBetweenRecursive(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseFirstN(head, right);
        }

        head.next = reverseBetweenRecursive(head.next, left - 1, right - 1);
        return head;
    }

    private ListNode successor = null;

    /**
     * Reverse first n nodes of the list
     */
    private ListNode reverseFirstN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }

        ListNode last = reverseFirstN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * Detailed step-by-step approach for learning
     */
    public ListNode reverseBetweenDetailed(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        System.out.println("Reversing from position " + left + " to " + right);
        System.out.print("Original list: ");
        printList(head);

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Find the node before left position
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        System.out.println("Node before left position: " +
                (prev.next == null ? "null" : prev.next.val));

        // Start reversing
        ListNode current = prev.next;
        ListNode nextNode = current.next;

        System.out.println("Starting reversal process:");
        int step = 1;

        for (int i = 0; i < right - left; i++) {
            System.out.println("Step " + step + ": Moving " + nextNode.val +
                    " to the front of reversed segment");
            current.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
            nextNode = current.next;

            System.out.print("Current state: ");
            printList(dummy.next);
            step++;
        }

        System.out.println("Reversal complete!\n");
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

    // Helper method to copy a list (since reversal modifies the original)
    public static ListNode copyList(ListNode head) {
        if (head == null) return null;

        ListNode newHead = new ListNode(head.val);
        ListNode current = newHead;
        ListNode original = head.next;

        while (original != null) {
            current.next = new ListNode(original.val);
            current = current.next;
            original = original.next;
        }

        return newHead;
    }

    // Test the solution
    public static void main(String[] args) {
        ReverseList2 solution = new ReverseList2();

        // Test case 1: [1,2,3,4,5], left = 2, right = 4 -> [1,4,3,2,5]
        System.out.println("=== ONE PASS APPROACH ===");
        System.out.println("Test case 1:");
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head1);
        System.out.println("left = 2, right = 4");
        ListNode result1 = solution.reverseBetween(head1, 2, 4);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Test case 2: [5], left = 1, right = 1 -> [5]
        System.out.println("Test case 2:");
        ListNode head2 = createList(new int[]{5});
        System.out.print("Input: ");
        printList(head2);
        System.out.println("left = 1, right = 1");
        ListNode result2 = solution.reverseBetween(head2, 1, 1);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Test case 3: Reverse entire list
        System.out.println("Test case 3:");
        ListNode head3 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head3);
        System.out.println("left = 1, right = 5 (entire list)");
        ListNode result3 = solution.reverseBetween(head3, 1, 5);
        System.out.print("Output: ");
        printList(result3);
        System.out.println();

        // Test case 4: Reverse from beginning
        System.out.println("Test case 4:");
        ListNode head4 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head4);
        System.out.println("left = 1, right = 3");
        ListNode result4 = solution.reverseBetween(head4, 1, 3);
        System.out.print("Output: ");
        printList(result4);
        System.out.println();

        // Test case 5: Reverse at the end
        System.out.println("Test case 5:");
        ListNode head5 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head5);
        System.out.println("left = 3, right = 5");
        ListNode result5 = solution.reverseBetween(head5, 3, 5);
        System.out.print("Output: ");
        printList(result5);
        System.out.println();

        // Test two-pass approach
        System.out.println("=== TWO PASS APPROACH ===");
        System.out.println("Test case (Two Pass):");
        ListNode head6 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head6);
        System.out.println("left = 2, right = 4");
        ListNode result6 = solution.reverseBetweenTwoPass(head6, 2, 4);
        System.out.print("Output: ");
        printList(result6);
        System.out.println();

        // Test recursive approach
        System.out.println("=== RECURSIVE APPROACH ===");
        System.out.println("Test case (Recursive):");
        ListNode head7 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head7);
        System.out.println("left = 2, right = 4");
        ListNode result7 = solution.reverseBetweenRecursive(head7, 2, 4);
        System.out.print("Output: ");
        printList(result7);
        System.out.println();

        // Test detailed approach (for learning)
        System.out.println("=== DETAILED STEP-BY-STEP ===");
        ListNode head8 = createList(new int[]{1, 2, 3, 4, 5});
        ListNode result8 = solution.reverseBetweenDetailed(head8, 2, 4);

        // Performance comparison
        System.out.println("=== PERFORMANCE COMPARISON ===");
        System.out.println("One Pass: O(n) time, O(1) space - OPTIMAL");
        System.out.println("Two Pass: O(n) time, O(1) space - easier to understand");
        System.out.println("Recursive: O(n) time, O(n) space - elegant but uses stack");
        System.out.println("Recommended: Use one-pass approach for optimal performance");
    }
}
