package linkedlist;
/*
Palindromic linked list
You are given the head of a singly linked list where each node contains a single digit (between 0 and 9). Your task is to determine whether the linked list forms a palindrome.

A linked list is said to be a palindrome if the sequence of values from the head to the tail is the same when read both forwards and backwards.

Input Format:

First line contains the number of nodes n.
Next n value contains value sof n nodes.
Output Format:

Return true if the linked list is a palindrome otherwise return false.
Example 1: Input:

head = [1,2,2,1]
Output:

true
Explanation:
The linked list represents the sequence [1, 2, 2, 1], which is a palindrome when read from left to right or right to left.

Example 2:

Input:

head = [1,2]
Output:

false
Explanation:
The linked list represents the sequence [1, 2], which is not a palindrome as it does not read the same backward as forward.

Constraints:
The number of nodes in the linked list is between 1 and 10^5.

Each node’s value is an integer between 0 and 9

Note:The function should return the result. The driver code will handle printing the output.
 */
public class PalindromicLinkedList {
    public static void main(String[] args) {
        // Example usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(head)); // Output: true

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);

        System.out.println(isPalindrome(head2)); // Output: false
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half of the linked list
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // Compare the first half with the reversed second half
        ListNode firstHalf = head;
        ListNode secondHalf = prev;

        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }
}

