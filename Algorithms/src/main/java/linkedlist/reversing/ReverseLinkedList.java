package linkedlist.reversing;

import linkedlist.ListNode;

/* Given the head of a singly linked list, reverse the list, and return the reversed list.



Example 1:


Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:


Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []


Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000


Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
*/
public class ReverseLinkedList {
    /**
     * Reverses a linked list iteratively
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - only using constant extra space
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;  // Store next node
            current.next = prev;               // Reverse the link
            prev = current;                    // Move prev forward
            current = nextNode;                // Move current forward
        }

        return prev; // prev is now the new head
    }

    /**
     * Reverses a linked list recursively
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(n) due to recursion stack
     */
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the rest of the list
        ListNode reversedHead = reverseListRecursive(head.next);

        // Reverse the current connection
        head.next.next = head;
        head.next = null;

        return reversedHead;
    }

    /**
     * Alternative recursive approach with helper function
     */
    public ListNode reverseListRecursiveHelper(ListNode head) {
        return reverseHelper(head, null);
    }

    private ListNode reverseHelper(ListNode current, ListNode prev) {
        // Base case: reached the end
        if (current == null) {
            return prev;
        }

        ListNode nextNode = current.next;
        current.next = prev;
        return reverseHelper(nextNode, current);
    }

    /**
     * Stack-based approach (for educational purposes)
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public ListNode reverseListStack(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        java.util.Stack<ListNode> stack = new java.util.Stack<>();
        ListNode current = head;

        // Push all nodes to stack
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        // Pop nodes and rebuild the list
        ListNode newHead = stack.pop();
        current = newHead;

        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }

        current.next = null; // Important: set last node's next to null
        return newHead;
    }

    /**
     * In-place reversal with detailed step tracking (for debugging)
     */
    public ListNode reverseListDetailed(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode current = head;
        int step = 0;

        System.out.println("Starting reversal:");
        printStep(prev, current, step++);

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
            printStep(prev, current, step++);
        }

        System.out.println("Reversal complete!\n");
        return prev;
    }

    private void printStep(ListNode prev, ListNode current, int step) {
        System.out.print("Step " + step + " - ");
        System.out.print("prev: " + (prev == null ? "null" : prev.val));
        System.out.print(", current: " + (current == null ? "null" : current.val));
        System.out.println();
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
        ReverseLinkedList solution = new ReverseLinkedList();

        // Test case 1: [1,2,3,4,5] -> [5,4,3,2,1]
        System.out.println("=== ITERATIVE APPROACH ===");
        System.out.println("Test case 1:");
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head1);
        ListNode result1 = solution.reverseList(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Test case 2: [1,2] -> [2,1]
        System.out.println("Test case 2:");
        ListNode head2 = createList(new int[]{1, 2});
        System.out.print("Input: ");
        printList(head2);
        ListNode result2 = solution.reverseList(head2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Test case 3: [] -> []
        System.out.println("Test case 3:");
        ListNode head3 = createList(new int[]{});
        System.out.print("Input: ");
        printList(head3);
        ListNode result3 = solution.reverseList(head3);
        System.out.print("Output: ");
        printList(result3);
        System.out.println();

        // Test case 4: Single element
        System.out.println("Test case 4:");
        ListNode head4 = createList(new int[]{42});
        System.out.print("Input: ");
        printList(head4);
        ListNode result4 = solution.reverseList(head4);
        System.out.print("Output: ");
        printList(result4);
        System.out.println();

        // Test recursive approach
        System.out.println("=== RECURSIVE APPROACH ===");
        System.out.println("Test case 1 (Recursive):");
        ListNode head5 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input: ");
        printList(head5);
        ListNode result5 = solution.reverseListRecursive(head5);
        System.out.print("Output: ");
        printList(result5);
        System.out.println();

        // Test recursive helper approach
        System.out.println("Test case 2 (Recursive Helper):");
        ListNode head6 = createList(new int[]{1, 2, 3});
        System.out.print("Input: ");
        printList(head6);
        ListNode result6 = solution.reverseListRecursiveHelper(head6);
        System.out.print("Output: ");
        printList(result6);
        System.out.println();

        // Test stack approach
        System.out.println("=== STACK APPROACH ===");
        System.out.println("Test case (Stack):");
        ListNode head7 = createList(new int[]{1, 2, 3, 4});
        System.out.print("Input: ");
        printList(head7);
        ListNode result7 = solution.reverseListStack(head7);
        System.out.print("Output: ");
        printList(result7);
        System.out.println();

        // Test detailed approach (for learning)
        System.out.println("=== DETAILED STEP-BY-STEP ===");
        ListNode head8 = createList(new int[]{1, 2, 3});
        System.out.print("Input: ");
        printList(head8);
        ListNode result8 = solution.reverseListDetailed(head8);
        System.out.print("Final Output: ");
        printList(result8);

        // Performance comparison note
        System.out.println("=== PERFORMANCE NOTES ===");
        System.out.println("Iterative: O(n) time, O(1) space - OPTIMAL");
        System.out.println("Recursive: O(n) time, O(n) space - due to call stack");
        System.out.println("Stack-based: O(n) time, O(n) space - educational purpose");
        System.out.println("Recommended: Use iterative approach for production code");
    }
}
