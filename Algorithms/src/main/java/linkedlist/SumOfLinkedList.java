package linkedlist;
/*
Sum of Linked lists
You are given two non-empty linked lists representing two non-negative integers.
The most significant digit comes first and each of their nodes contains a single digit.
 Add the two numbers and return the sum as a linked list. You may assume the two numbers do
  not contain any leading zero, except the number 0 itself.

Input Format:
The input consists of multiple lines:
1. The first line contains two integers, l1 and l2, representing the number of nodes
 in the first and second linked lists, respectively.
2. The second line contains l1 integers, the digits of the first number and each
digit separated by a space.
3. The third line contains l2 integers, the digits of the second number and each
digit separated by a space.
Output Format:
Return the sum of the numbers as a linked list (head as 2 in this case).
Example
3 4

L1 = [2,0,1] and L2 = [1,9,9,9]

Sum = 201 + 1999 = 2200

So you need to return 2200 as a linked list.
Constraints:
The number of nodes in each linked list is in the range [1, 100].

0 <= Node.val <= 9

It is guaranteed that the list represents a number that does not have leading zeros.

Note: The function should return the result.
 */

import java.util.Stack;

public class SumOfLinkedList {
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        // Push all elements of l1 into stack1
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        // Push all elements of l2 into stack2
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode result = null;

        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int sum = carry;

            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }

            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }

            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = result;
            result = newNode;
        }

        return result;
    }

    // Helper method to create a linked list from an array
    public static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        for (int num : arr) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Example usage
        int[] arr1 = {2, 0, 1};
        int[] arr2 = {1, 9, 9, 9};

        ListNode l1 = createList(arr1);
        ListNode l2 = createList(arr2);
        ListNode result = addTwoNumbers(l1, l2);

        printList(result); // Output: 2200
    }
}
