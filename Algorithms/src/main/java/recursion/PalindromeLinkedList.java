package recursion;
/*
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.



Example 1:


Input: head = [1,2,2,1]
Output: true
Example 2:


Input: head = [1,2]
Output: false


Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9


Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    private ListNode front;

    public boolean isPalindrome(ListNode head) {
        front = head;
        return checkPalindrome(head);
    }

    private boolean checkPalindrome(ListNode current) {
        if (current == null) return true;
        boolean res = checkPalindrome(current.next);
        if (!res) return false;
        boolean isEqual = (front.val == current.val);
        front = front.next;
        return isEqual;
    }

    public static void main(String[] args) {
        PalindromeLinkedList palindromeChecker = new PalindromeLinkedList();

        // Example 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        System.out.println(palindromeChecker.isPalindrome(head1)); // Output: true

        // Example 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        System.out.println(palindromeChecker.isPalindrome(head2)); // Output: false
    }
}
