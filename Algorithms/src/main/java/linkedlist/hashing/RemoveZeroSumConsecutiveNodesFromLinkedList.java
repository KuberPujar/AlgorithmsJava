package linkedlist.hashing;

import linkedlist.ListNode;

import java.util.HashMap;

/*
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.



(Note that in the examples below, all sequences are serializations of ListNode objects.)

Example 1:

Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.
Example 2:

Input: head = [1,2,3,-3,4]
Output: [1,2,4]
Example 3:

Input: head = [1,2,3,-3,-2]
Output: [1]


Constraints:

The given linked list will contain between 1 and 1000 nodes.
Each node in the linked list has -1000 <= node.val <= 1000.
 */
public class RemoveZeroSumConsecutiveNodesFromLinkedList {
    /**
     * Given the head of a linked list, repeatedly deletes consecutive sequences of nodes
     * that sum to 0 until there are no such sequences.
     * After doing so, returns the head of the final linked list.
     *
     * @param head The head of the input linked list.
     * @return The head of the modified linked list after removing zero-sum sequences.
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        // Create a dummy node to handle cases where the head of the list might be removed.
        // This simplifies operations at the beginning of the list.
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // HashMap to store the mapping from prefix sum to the last ListNode encountered
        // that resulted in that prefix sum.
        // Key: prefix sum (cumulative sum of node values from dummy node to current node)
        // Value: ListNode at which this prefix sum was achieved.
        HashMap<Integer, ListNode> prefixSumMap = new HashMap<>();

        // Initialize the map with the dummy node and its prefix sum (0).
        prefixSumMap.put(0, dummy);

        int currentSum = 0; // Tracks the cumulative sum of node values.
        ListNode current = dummy.next; // Start traversing from the actual head of the list (after dummy).

        // Iterate through the linked list.
        while (current != null) {
            currentSum += current.val; // Update the current cumulative sum.

            // Check if the current sum already exists in our map.
            if (prefixSumMap.containsKey(currentSum)) {
                // If it exists, it means the sum of nodes between
                // prefixSumMap.get(currentSum) and current is 0.
                ListNode nodeBeforeRemoval = prefixSumMap.get(currentSum);

                // We need to remove all entries from the map that fall within this
                // zero-sum segment to prevent incorrect future matches.
                // Start from the node *after* nodeBeforeRemoval.
                ListNode temp = nodeBeforeRemoval.next;
                int sumToRemoveFromMap = currentSum; // This will track sums to be removed from map

                // Traverse the segment that sums to zero, removing their prefix sum entries from the map.
                // We stop at 'current' (exclusive of current) because 'current' itself will be bypassed.
                while (temp != current) {
                    sumToRemoveFromMap += temp.val; // Calculate the sum leading up to 'temp'
                    prefixSumMap.remove(sumToRemoveFromMap); // Remove this intermediate prefix sum
                    temp = temp.next;
                }

                // Bypass the zero-sum segment by connecting nodeBeforeRemoval to current.next.
                // This effectively "deletes" the segment.
                nodeBeforeRemoval.next = current.next;
            } else {
                // If the current sum is not in the map, add it along with the current node.
                prefixSumMap.put(currentSum, current);
            }
            // Move to the next node in the list.
            current = current.next;
        }

        // The final list starts from dummy.next.
        return dummy.next;
    }

    // Helper function to print a linked list (for testing purposes)
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    // Main method for testing the solution
    public static void main(String[] args) {
        RemoveZeroSumConsecutiveNodesFromLinkedList solver = new RemoveZeroSumConsecutiveNodesFromLinkedList();

        // Example 1: Input: head = [1,2,-3,3,1], Output: [3,1] (or [1,2,1])
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(-3);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(1);
        System.out.println("--- Example 1 ---");
        System.out.print("Original list: ");
        printList(head1);
        ListNode result1 = solver.removeZeroSumSublists(head1);
        System.out.print("Modified list: ");
        printList(result1); // Expected: [3 -> 1] or [1 -> 2 -> 1] (depending on which zero sum is removed first by hashing order)
        System.out.println();

        // Example 2: Input: head = [1,2,3,-3,4], Output: [1,2,4]
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(-3);
        head2.next.next.next.next = new ListNode(4);
        System.out.println("--- Example 2 ---");
        System.out.print("Original list: ");
        printList(head2);
        ListNode result2 = solver.removeZeroSumSublists(head2);
        System.out.print("Modified list: ");
        printList(result2); // Expected: [1 -> 2 -> 4]
        System.out.println();

        // Example 3: Input: head = [1,2,3,-3,-2], Output: [1]
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        head3.next.next.next = new ListNode(-3);
        head3.next.next.next.next = new ListNode(-2);
        System.out.println("--- Example 3 ---");
        System.out.print("Original list: ");
        printList(head3);
        ListNode result3 = solver.removeZeroSumSublists(head3);
        System.out.print("Modified list: ");
        printList(result3); // Expected: [1]
        System.out.println();

        // Additional Test Case: List with multiple zero-sum sequences, some overlapping
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(-1);
        head4.next.next = new ListNode(2);
        head4.next.next.next = new ListNode(-2);
        head4.next.next.next.next = new ListNode(3);
        System.out.println("--- Additional Test Case 1 ---");
        System.out.print("Original list: ");
        printList(head4);
        ListNode result4 = solver.removeZeroSumSublists(head4);
        System.out.print("Modified list: ");
        printList(result4); // Expected: [3]
        System.out.println();

        // Additional Test Case: Entire list sums to zero
        ListNode head5 = new ListNode(1);
        head5.next = new ListNode(2);
        head5.next.next = new ListNode(-3);
        System.out.println("--- Additional Test Case 2 (Entire list sums to zero) ---");
        System.out.print("Original list: ");
        printList(head5);
        ListNode result5 = solver.removeZeroSumSublists(head5);
        System.out.print("Modified list: ");
        printList(result5); // Expected: []
        System.out.println();
    }
}
