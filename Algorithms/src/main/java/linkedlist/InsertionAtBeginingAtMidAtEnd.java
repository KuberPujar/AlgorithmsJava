package linkedlist;
/*
Insertion at beginning, middle, end
You are given the head of a doubly linked list and a value val. Your task is to implement a function addVal that inserts a new node with the given value at:

The beginning of the list. The middle of the list. The end of the list. After the insertions, return the modified doubly linked list. Note that the insertion order must strictly follow the sequence: beginning → middle → end.

The middle is defined as the node just after the midpoint if the list has an even number of nodes. Ignore -1, which represents the end of the list, in the input.

Input:

head: The head of a non-empty doubly linked list with integers as node values. The list contains at least 1 node and at most 1000 nodes.

val: An integer in the range [-1000, 1000] that needs to be inserted into the list.

Output:

Return the head of the modified doubly linked list.
Examples:

Example 1:

Input:

Linkedlist = 2 4 5 -1,  val = 5
Output:

5 2 5 4 5 5
Explanation
Insert 5 at the beginning: 5 2 4 5
Insert 5 in the middle: 5 2 5 4 5
Insert 5 at the end: 5 2 5 4 5 5
Example 2:

Input:

 Linkedlist = 1 2 -1,  val = 9
Output:

9 1 9 2 9
Insert 9 at the beginning: 9 1 2
Insert 9 in the middle: 9 1 9 2
Insert 9 at the end: 9 1 9 2 9
Constraints:

The number of nodes in the doubly linked list (excluding -1) is between 1 and 1000.
The value of each node and val are integers in the range [-1000, 1000].
Note:The function should return the result. The driver code will handle printing the output.
 */
public class InsertionAtBeginingAtMidAtEnd {
    public static void main(String[] args) {
        // Example usage
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.prev = head;
        head.next.next = new ListNode(5);
        head.next.next.prev = head.next;

        int val = 5;

        ListNode modifiedHead = addVal(head, val);
        printList(modifiedHead); // Output: 5 2 5 4 5 5
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode(int x) {
            this.val = x;
            this.prev=null;
            this.next=null;
        }
    }


   private static ListNode addVal(ListNode head, int val) {
        if (head == null) {
            return new ListNode(val);
        }

        // Step 1: Insert at beginning
        ListNode newHead = insertAtBeginning(head, val);

        // Step 2: Insert in middle
        newHead = insertInMiddle(newHead, val);

        // Step 3: Insert at end
        newHead = insertAtEnd(newHead, val);

        return newHead;
    }

    private static ListNode insertAtBeginning(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head.prev = newNode;
        return newNode;
    }

    private static ListNode insertInMiddle(ListNode head, int val) {
        int length = getLength(head);
        int middlePosition = length / 2;

        ListNode current = head;
        for (int i = 0; i < middlePosition; i++) {
            current = current.next;
        }

        ListNode newNode = new ListNode(val);
        newNode.next = current;
        newNode.prev = current.prev;
        if (current.prev != null) {
            current.prev.next = newNode;
        } else {
            head = newNode; // Update head if inserting at the very beginning
        }
        current.prev = newNode;

        return head;
    }

    private static ListNode insertAtEnd(ListNode head, int val) {
        ListNode newNode = new ListNode(val);

        if (head == null) {
            return newNode;
        }

        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        newNode.prev = current;

        return head;
    }

    private static int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
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
