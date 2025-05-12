package linkedlist;
/*
Given the head of a linked list, rotate the list to the right by k places.

Input:
head: The head of the linked list. (0 <= length of the list <= 500)
k: The number of places to rotate the list. (0 <= k <= 2 * 10^9)

Output:
Return the head of the rotated linked list.

Examples:

Input:

head = [1,2,3,4,5], k = 2

Output:
[4,5,1,2,3]
Input:

head = [0,1,2], k = 4

Output:

[2,0,1]

Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
 */
public class RotateTheLinkedListToRightByKPlaces {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Find the length and the tail node
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Compute effective k
        k = k % length;
        if (k == 0) {
            return head;
        }

        // Make the list circular
        tail.next = head;

        // Find the new tail which is (length - k - 1) steps from head
        int stepsToNewTail = length - k - 1;
        ListNode newTail = head;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        ListNode root=new ListNode(1);
        root.next=new ListNode(2);
        root.next.next=new ListNode(3);
        root.next.next.next=new ListNode(4);
        root.next.next.next.next=new ListNode(5);
        ListNode rotated=rotateRight(root,2);
        printList(rotated);
    }

    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
