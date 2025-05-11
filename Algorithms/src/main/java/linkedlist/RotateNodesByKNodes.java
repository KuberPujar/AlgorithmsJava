package linkedlist;
/*
Rotate Nodes by K nodes
You are given a doubly linked list and an integer K.
 Your task is to rotate the list clockwise by K positions and
 return the new head of the rotated list.

Clockwise rotation by 1 position means moving the last node of the
 list to the beginning. If K is greater than the number of nodes N
 in the list, the rotation should be performed K % N times.

Input Format
head: The head of a doubly linked list, where the number of nodes N satisfies
k: An integer representing the number of rotations.
Output Format
Return the new head of the rotated doubly linked list.
"Example 1:

Input:

Example 1:

List: 1 ⇄ 2 ⇄ 3 ⇄ 4 ⇄ 5
head = [1,2,3,4,5], k = 2
Output:

 4 ⇄ 5 ⇄ 1 ⇄ 2 ⇄ 3
 [4,5,1,2,3]
Explanation
After 1 rotation: 5 ⇄ 1 ⇄ 2 ⇄ 3 ⇄ 4
After 2 rotations: 4 ⇄ 5 ⇄ 1 ⇄ 2 ⇄ 3
Example 2:
Input:

n=3, head = [0,1,2], k = 4
Output:

[2,0,1]
Explanation
We rotate k % n = 4 % 3 = 1.
After 1 rotation: 2 ⇄ 0 ⇄ 1
Constraints:
1≤N≤1000 (Number of nodes in the doubly linked list).
1≤k≤10^5 (Number of rotations).
Note:The function should return the result. The driver code will handle printing the output.
 */
public class RotateNodesByKNodes {
    public static void main(String[] args) {
        // Example usage
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;

        int k = 2;

        Node newHead = rotate(head, k);

        // Print the rotated list
        Node current = newHead;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    private static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static Node rotate(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 1: Find the length of the list and get the tail node
        Node tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Calculate effective rotations needed
        k = k % length;
        if (k == 0) {
            return head; // No rotation needed
        }

        // Step 3: Find the new tail (which will be the (length - k)th node)
        Node newTail = head;
        for (int i = 1; i < length - k; i++) {
            newTail = newTail.next;
        }

        // Step 4: Perform the rotation
        Node newHead = newTail.next;
        newTail.next = null;
        newHead.prev = null;
        tail.next = head;
        head.prev = tail;

        return newHead;
    }

    // Utility method to print the list (for testing)
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Utility method to create a list from array (for testing)
    public static Node createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        return head;
    }
}
