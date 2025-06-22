package linkedlist.iterativemanner;

import linkedlist.ListNode;

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.



Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbersIterative {
    /**
     * Iterative approach to add two numbers represented as linked lists
     * Time Complexity: O(max(m, n)) where m and n are lengths of the two lists
     * Space Complexity: O(max(m, n)) for the result list
     *
     * Algorithm:
     * 1. Use a dummy head to simplify result list construction
     * 2. Iterate through both lists simultaneously
     * 3. At each position, add corresponding digits plus any carry
     * 4. Create new node with sum % 10, update carry to sum / 10
     * 5. Continue until both lists are exhausted and no carry remains
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Create dummy head to simplify list construction
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // Continue while there are digits in either list or carry exists
        while (l1 != null || l2 != null || carry != 0) {
            // Get values from current nodes (0 if node is null)
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            // Calculate sum of current digits plus carry
            int sum = val1 + val2 + carry;

            // Create new node with the ones digit of sum
            current.next = new ListNode(sum % 10);
            current = current.next;

            // Update carry for next iteration
            carry = sum / 10;

            // Move to next nodes if they exist
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // Return the actual head (skip dummy)
        return dummyHead.next;
    }

    /**
     * Alternative implementation with more explicit handling
     */
    public ListNode addTwoNumbersVerbose(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // Process while both lists have nodes
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            current.next = new ListNode(sum % 10);
            carry = sum / 10;

            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        // Process remaining nodes in l1
        while (l1 != null) {
            int sum = l1.val + carry;
            current.next = new ListNode(sum % 10);
            carry = sum / 10;

            current = current.next;
            l1 = l1.next;
        }

        // Process remaining nodes in l2
        while (l2 != null) {
            int sum = l2.val + carry;
            current.next = new ListNode(sum % 10);
            carry = sum / 10;

            current = current.next;
            l2 = l2.next;
        }

        // Handle final carry if exists
        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummyHead.next;
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
        boolean first = true;
        while (current != null) {
            if (!first) {
                System.out.print(",");
            }
            System.out.print(current.val);
            first = false;
            current = current.next;
        }
        System.out.print("]");
    }

    // Helper method to convert list to number for verification
    public static long listToNumber(ListNode head) {
        long number = 0;
        long multiplier = 1;

        while (head != null) {
            number += head.val * multiplier;
            multiplier *= 10;
            head = head.next;
        }

        return number;
    }

    // Test method with all examples
    public static void main(String[] args) {
        AddTwoNumbersIterative solution = new AddTwoNumbersIterative();

        // Example 1: [2,4,3] + [5,6,4] = [7,0,8] (342 + 465 = 807)
        System.out.println("Example 1:");
        ListNode l1_1 = createList(new int[]{2, 4, 3});
        ListNode l2_1 = createList(new int[]{5, 6, 4});
        System.out.print("l1 = ");
        printList(l1_1);
        System.out.print(" (represents " + listToNumber(createList(new int[]{2, 4, 3})) + ")");
        System.out.println();
        System.out.print("l2 = ");
        printList(l2_1);
        System.out.print(" (represents " + listToNumber(createList(new int[]{5, 6, 4})) + ")");
        System.out.println();

        ListNode result1 = solution.addTwoNumbers(l1_1, l2_1);
        System.out.print("Result: ");
        printList(result1);
        System.out.print(" (represents " + listToNumber(result1) + ")");
        System.out.println("\n");

        // Example 2: [0] + [0] = [0]
        System.out.println("Example 2:");
        ListNode l1_2 = createList(new int[]{0});
        ListNode l2_2 = createList(new int[]{0});
        System.out.print("l1 = ");
        printList(l1_2);
        System.out.println();
        System.out.print("l2 = ");
        printList(l2_2);
        System.out.println();

        ListNode result2 = solution.addTwoNumbers(l1_2, l2_2);
        System.out.print("Result: ");
        printList(result2);
        System.out.println("\n");

        // Example 3: [9,9,9,9,9,9,9] + [9,9,9,9] = [8,9,9,9,0,0,0,1]
        System.out.println("Example 3:");
        ListNode l1_3 = createList(new int[]{9, 9, 9, 9, 9, 9, 9});
        ListNode l2_3 = createList(new int[]{9, 9, 9, 9});
        System.out.print("l1 = ");
        printList(l1_3);
        System.out.print(" (represents " + listToNumber(createList(new int[]{9, 9, 9, 9, 9, 9, 9})) + ")");
        System.out.println();
        System.out.print("l2 = ");
        printList(l2_3);
        System.out.print(" (represents " + listToNumber(createList(new int[]{9, 9, 9, 9})) + ")");
        System.out.println();

        ListNode result3 = solution.addTwoNumbers(l1_3, l2_3);
        System.out.print("Result: ");
        printList(result3);
        System.out.print(" (represents " + listToNumber(result3) + ")");
        System.out.println("\n");

        // Additional test cases
        System.out.println("Additional Test Cases:");

        // Different lengths
        System.out.println("Different lengths: [1] + [9,9] = [0,0,1]");
        ListNode l1_4 = createList(new int[]{1});
        ListNode l2_4 = createList(new int[]{9, 9});
        System.out.print("l1 = ");
        printList(l1_4);
        System.out.print(", l2 = ");
        printList(l2_4);
        System.out.print(" -> Result: ");
        ListNode result4 = solution.addTwoNumbers(l1_4, l2_4);
        printList(result4);
        System.out.println();

        // Carry propagation
        System.out.println("Carry propagation: [5] + [5] = [0,1]");
        ListNode l1_5 = createList(new int[]{5});
        ListNode l2_5 = createList(new int[]{5});
        System.out.print("l1 = ");
        printList(l1_5);
        System.out.print(", l2 = ");
        printList(l2_5);
        System.out.print(" -> Result: ");
        ListNode result5 = solution.addTwoNumbers(l1_5, l2_5);
        printList(result5);
        System.out.println();
    }
}
