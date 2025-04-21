package linkedlist;
/*
Middle of linked list
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the first middle node.

Sample Input:

5
1 2 3 4 5
Sample Output:
3

Sample Input:

4
1 2 3 4
Sample Output:
2

Constraints:

The number of nodes in the list is in the range [1, 100].
1 <= Node.val <= 100
Note: The function should print the result.
 */
public class MiddleOfLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode middleNode = middleNode(head);
        System.out.println(middleNode.val); // Output the value of the middle node

        // Test with even number of nodes
        ListNode headEven = new ListNode(1);
        headEven.next = new ListNode(2);
        headEven.next.next = new ListNode(3);
        headEven.next.next.next = new ListNode(4);
        ListNode middleNodeEven = middleNode(headEven);
        System.out.println(middleNodeEven.val); // Output the value of the first middle node

        // Test with a single node
        ListNode headSingle = new ListNode(1);
        ListNode middleNodeSingle = middleNode(headSingle);
        System.out.println(middleNodeSingle.val); // Output the value of the single node
        // Test with null head
        ListNode headNull = null;
        ListNode middleNodeNull = middleNode(headNull);
        System.out.println(middleNodeNull); // Output should be null
    // Test with a long list
        ListNode headLong = new ListNode(1);
        ListNode current = headLong;
        for (int i = 2; i <= 100; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        ListNode middleNodeLong = middleNode(headLong);
        System.out.println(middleNodeLong.val); // Output the value of the middle node
        // Test with a long list with even number of nodes
        ListNode headLongEven = new ListNode(1);
        current = headLongEven;
        for (int i = 2; i <= 100; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        ListNode middleNodeLongEven = middleNode(headLongEven);
        System.out.println(middleNodeLongEven.val); // Output the value of the first middle node
        // Test with a long list with odd number of nodes
        ListNode headLongOdd = new ListNode(1);
        current = headLongOdd;
        for (int i = 2; i <= 99; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        current.next = new ListNode(100);
        ListNode middleNodeLongOdd = middleNode(headLongOdd);
        System.out.println(middleNodeLongOdd.val); // Output the value of the middle node

    }

    private static ListNode middleNode(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Fast pointer moves twice as fast as slow pointer
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // If even number of nodes, return first middle
        if (fast.next != null && fast.next.next == null) {
            return slow;
        }

        return slow;
    }
}
