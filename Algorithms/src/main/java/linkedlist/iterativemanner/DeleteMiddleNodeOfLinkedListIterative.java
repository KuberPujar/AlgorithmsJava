package linkedlist.iterativemanner;

import linkedlist.ListNode;

/*
You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.


Example 1:


Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node.
Example 2:


Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.
Example 3:


Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.


Constraints:

The number of nodes in the list is in the range [1, 105].
1 <= Node.val <= 105
 */
public class DeleteMiddleNodeOfLinkedListIterative {
    /**
     * Delete the middle node of a singly linked list using two-pointer approach
     * Middle node is at index ⌊n/2⌋ using 0-based indexing
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - constant extra space
     *
     * @param head The head of the linked list
     * @return The head of the modified linked list after deleting middle node
     */
    public ListNode deleteMiddle(ListNode head) {
        // Edge case: single node - delete it and return null
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize pointers
        ListNode slow = head;      // will point to middle node
        ListNode fast = head;      // moves twice as fast
        ListNode prev = null;      // will point to node before middle

        // Move pointers to find middle node and its predecessor
        // Fast pointer moves 2 steps, slow moves 1 step
        while (fast != null && fast.next != null) {
            prev = slow;           // keep track of previous node
            slow = slow.next;      // move slow pointer one step
            fast = fast.next.next; // move fast pointer two steps
        }

        // At this point:
        // - slow points to the middle node to be deleted
        // - prev points to the node before the middle node

        // Delete the middle node by updating the previous node's next pointer
        prev.next = slow.next;

        return head;
    }

// Helper class for testing and utility functions
    /**
     * Create a linked list from array of values
     */
    public static ListNode createLinkedList(int[] values) {
        if (values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Convert linked list to array for easy printing and verification
     */
    public static int[] linkedListToArray(ListNode head) {
        if (head == null) return new int[0];

        // Count nodes first
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Convert to array
        int[] result = new int[count];
        temp = head;
        for (int i = 0; i < count; i++) {
            result[i] = temp.val;
            temp = temp.next;
        }

        return result;
    }

    /**
     * Print linked list
     */
    public static void printLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    /**
     * Get the middle index for verification (⌊n/2⌋)
     */
    public static int getMiddleIndex(int n) {
        return n / 2;  // Integer division gives us ⌊n/2⌋
    }


// Test class to demonstrate the solution
    public static void main(String[] args) {
        DeleteMiddleNodeOfLinkedListIterative solution = new DeleteMiddleNodeOfLinkedListIterative();

        // Test Example 1: [1,3,4,7,1,2,6] -> [1,3,4,1,2,6]
        System.out.println("Example 1:");
        int[] input1 = {1, 3, 4, 7, 1, 2, 6};
        System.out.println("Input: [1,3,4,7,1,2,6]");
        System.out.println("Length: " + input1.length + ", Middle index: " +getMiddleIndex(input1.length) +
                " (value 7)");

        ListNode head1 = createLinkedList(input1);
        ListNode result1 = solution.deleteMiddle(head1);
        System.out.print("Output: ");
        printLinkedList(result1);
        System.out.println();

        // Test Example 2: [1,2,3,4] -> [1,2,4]
        System.out.println("Example 2:");
        int[] input2 = {1, 2, 3, 4};
        System.out.println("Input: [1,2,3,4]");
        System.out.println("Length: " + input2.length + ", Middle index: " +
                getMiddleIndex(input2.length) +
                " (value 3)");

        ListNode head2 = createLinkedList(input2);
        ListNode result2 = solution.deleteMiddle(head2);
        System.out.print("Output: ");
        printLinkedList(result2);
        System.out.println();

        // Test Example 3: [2,1] -> [2]
        System.out.println("Example 3:");
        int[] input3 = {2, 1};
        System.out.println("Input: [2,1]");
        System.out.println("Length: " + input3.length + ", Middle index: " +
                getMiddleIndex(input3.length) +
                " (value 1)");

        ListNode head3 = createLinkedList(input3);
        ListNode result3 = solution.deleteMiddle(head3);
        System.out.print("Output: ");
        printLinkedList(result3);
        System.out.println();

        // Test edge case: single node [5] -> []
        System.out.println("Edge case - Single node:");
        int[] input4 = {5};
        System.out.println("Input: [5]");
        System.out.println("Length: " + input4.length + ", Middle index: " +
                getMiddleIndex(input4.length) +
                " (value 5)");

        ListNode head4 = createLinkedList(input4);
        ListNode result4 = solution.deleteMiddle(head4);
        System.out.print("Output: ");
        printLinkedList(result4);
        System.out.println();

        // Test additional case: [1,2,3,4,5] -> [1,2,4,5]
        System.out.println("Additional test - Odd length:");
        int[] input5 = {1, 2, 3, 4, 5};
        System.out.println("Input: [1,2,3,4,5]");
        System.out.println("Length: " + input5.length + ", Middle index: " +
                getMiddleIndex(input5.length) +
                " (value 3)");

        ListNode head5 = createLinkedList(input5);
        ListNode result5 = solution.deleteMiddle(head5);
        System.out.print("Output: ");
        printLinkedList(result5);
    }
}
