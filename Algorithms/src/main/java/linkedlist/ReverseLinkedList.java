package linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Given a linked list of N nodes, your task is to reverse the list.

Input Format:
A singly linked list of N nodes, where N is the number of nodes in the linked list. The linked list is provided as a sequence of N integer values, where each value represents the val of a node, and nodes are connected in the order they are given.

Output Format:
The reversed linked list as a sequence of integers, now ordered from the last node in the input list to the first.

Input:
LinkedList: 2->7->8->9->10

Output:
Reversed LinkedList: 10 9 8 7 2

Explanation:

The original linked list consists of the nodes with values 2, 7, 8, 9, and 10, connected in that order.
When we reverse the linked list, we need to change the direction of the links between the nodes.
The last node (10) becomes the new head of the list, followed by 9, then 8, 7, and finally 2, which becomes the last node in the reversed list.
Thus, the reversed linked list is 10 9 8 7 2.
Example:

Input:

LinkedList: 1->2->3->4->5->6

Output:
Reversed LinkedList: 6 5 4 3 2 1

Constraints:

1 <= N <= 10^4
Note: The function should return the linked list.
 */
public class ReverseLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private static ListNode createLinkedList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int num : arr) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine().trim();
        String[] parts = inputLine.split("->");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        ListNode head = createLinkedList(arr);
        ListNode reversedHead = reverseList(head);
        List<Integer> result = new ArrayList<>();
        ListNode curr = reversedHead;
        while (curr != null) {
            result.add(curr.val);
            curr = curr.next;
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i != result.size() - 1) {
                System.out.print(" ");
            }
        }
    }
}
