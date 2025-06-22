package linkedlist.recursion;

import linkedlist.ListNode;

/*
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)



Example 1:

Input: head = [1,2,3,4]

Output: [2,1,4,3]

Explanation:



Example 2:

Input: head = []

Output: []

Example 3:

Input: head = [1]

Output: [1]

Example 4:

Input: head = [1,2,3]

Output: [2,1,3]



Constraints:

The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
 */
public class SwapTwoNodesInPair {
    /**
     * Recursive approach to swap every two adjacent nodes
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(n) - due to recursion stack
     *
     * Algorithm:
     * 1. Base case: if head is null or only one node, return head
     * 2. Store the second node
     * 3. Recursively swap the rest of the list starting from the third node
     * 4. Point first node to the result of recursive call
     * 5. Point second node to first node
     * 6. Return second node as the new head of this pair
     */
    public ListNode swapPairs(ListNode head) {
        // Base case: if less than 2 nodes, return as is
        if (head == null || head.next == null) {
            return head;
        }

        // Store the second node (will become the new head of this pair)
        ListNode second = head.next;

        // Recursively swap the rest of the list starting from third node
        // and connect the first node to the result
        head.next = swapPairs(second.next);

        // Complete the swap: second node points to first node
        second.next = head;

        // Return the new head (which was the second node)
        return second;
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
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    // Test method with all examples
    public static void main(String[] args) {
        SwapTwoNodesInPair solution = new SwapTwoNodesInPair();

        // Example 1: [1,2,3,4] -> [2,1,4,3]
        System.out.println("Example 1:");
        ListNode head1 = createList(new int[]{1, 2, 3, 4});
        System.out.print("Input: ");
        printList(head1);
        ListNode result1 = solution.swapPairs(head1);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Example 2: [] -> []
        System.out.println("Example 2:");
        ListNode head2 = createList(new int[]{});
        System.out.print("Input: ");
        printList(head2);
        ListNode result2 = solution.swapPairs(head2);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Example 3: [1] -> [1]
        System.out.println("Example 3:");
        ListNode head3 = createList(new int[]{1});
        System.out.print("Input: ");
        printList(head3);
        ListNode result3 = solution.swapPairs(head3);
        System.out.print("Output: ");
        printList(result3);
        System.out.println();

        // Example 4: [1,2,3] -> [2,1,3]
        System.out.println("Example 4:");
        ListNode head4 = createList(new int[]{1, 2, 3});
        System.out.print("Input: ");
        printList(head4);
        ListNode result4 = solution.swapPairs(head4);
        System.out.print("Output: ");
        printList(result4);
        System.out.println();

        // Additional test case with even number of nodes
        System.out.println("Additional test - 6 nodes:");
        ListNode head5 = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Input: ");
        printList(head5);
        ListNode result5 = solution.swapPairs(head5);
        System.out.print("Output: ");
        printList(result5);
    }
}
