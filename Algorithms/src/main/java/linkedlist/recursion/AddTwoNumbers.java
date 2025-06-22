package linkedlist.recursion;

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
public class AddTwoNumbers {
// Main solution using iterative approach
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);  // Dummy node to simplify logic
        ListNode current = dummyHead;
        int carry = 0;

        // Process both lists while at least one has remaining digits or there's a carry
        while (l1 != null || l2 != null || carry != 0) {
            // Get values from current nodes (0 if node is null)
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            // Calculate sum and new carry
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            int digit = sum % 10;

            // Create new node with the digit
            current.next = new ListNode(digit);
            current = current.next;

            // Move to next nodes if they exist
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // Return the result (skip dummy head)
        return dummyHead.next;
    }
// Recursive solution with carry handling
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersWithCarry(l1, l2, 0);
    }

    private ListNode addTwoNumbersWithCarry(ListNode l1, ListNode l2, int carry) {
        // Base case: if both lists are null and no carry, we're done
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        // Get values from current nodes (0 if node is null)
        int val1 = (l1 != null) ? l1.val : 0;
        int val2 = (l2 != null) ? l2.val : 0;

        // Calculate sum and new carry
        int sum = val1 + val2 + carry;
        int newCarry = sum / 10;
        int digit = sum % 10;

        // Create current node with the digit
        ListNode currentNode = new ListNode(digit);

        // Get next nodes (null if current node is null)
        ListNode nextL1 = (l1 != null) ? l1.next : null;
        ListNode nextL2 = (l2 != null) ? l2.next : null;

        // Recursively process the rest of the lists
        currentNode.next = addTwoNumbersWithCarry(nextL1, nextL2, newCarry);

        return currentNode;
    }


// Alternative implementation with more explicit base cases
    public ListNode addTwoNumbersAlt(ListNode l1, ListNode l2) {
        return addHelper(l1, l2, 0);
    }

    private ListNode addHelper(ListNode l1, ListNode l2, int carry) {
        // Base case 1: All inputs are null/zero
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        // Base case 2: Only carry remains
        if (l1 == null && l2 == null && carry > 0) {
            return new ListNode(carry);
        }

        // Get current values
        int val1 = (l1 != null) ? l1.val : 0;
        int val2 = (l2 != null) ? l2.val : 0;

        // Calculate sum
        int total = val1 + val2 + carry;
        int digit = total % 10;
        int newCarry = total / 10;

        // Create result node
        ListNode result = new ListNode(digit);

        // Recursive call for next positions
        ListNode next1 = (l1 != null) ? l1.next : null;
        ListNode next2 = (l2 != null) ? l2.next : null;
        result.next = addHelper(next1, next2, newCarry);

        return result;
    }

    // Create linked list from array
    public static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    // Print linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    // Convert linked list to number (for verification)
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

    // Test both solutions
    public static void main(String[] args) {
        AddTwoNumbers solution = new AddTwoNumbers();

        // Test Case 1: [2,4,3] + [5,6,4] = [7,0,8]
        System.out.println("=== Test Case 1 ===");
        int[] arr1 = {2, 4, 3};  // represents 342
        int[] arr2 = {5, 6, 4};  // represents 465
        ListNode l1 = createList(arr1);
        ListNode l2 = createList(arr2);

        System.out.print("l1 = ");
        printList(l1);
        System.out.println("(represents " + listToNumber(l1) + ")");
        System.out.print("l2 = ");
        printList(l2);
        System.out.println("(represents " + listToNumber(l2) + ")");

        ListNode result1 = solution.addTwoNumbers(l1, l2);
        System.out.print("Result (Main): ");
        printList(result1);
        System.out.println("(represents " + listToNumber(result1) + ")");

        // Test alternative solution
        l1 = createList(arr1); // Recreate since lists are modified
        l2 = createList(arr2);
        ListNode altResult1 = solution.addTwoNumbersAlt(l1, l2);
        System.out.print("Result (Alt):  ");
        printList(altResult1);
        System.out.println("Expected: [7,0,8] (represents 807)");
        System.out.println();

        // Test Case 2: [0] + [0] = [0]
        System.out.println("=== Test Case 2 ===");
        int[] arr3 = {0};
        int[] arr4 = {0};
        ListNode l3 = createList(arr3);
        ListNode l4 = createList(arr4);

        System.out.print("l1 = ");
        printList(l3);
        System.out.print("l2 = ");
        printList(l4);

        ListNode result2 = solution.addTwoNumbers(l3, l4);
        System.out.print("Result: ");
        printList(result2);
        System.out.println("Expected: [0]");
        System.out.println();

        // Test Case 3: [9,9,9,9,9,9,9] + [9,9,9,9] = [8,9,9,9,0,0,0,1]
        System.out.println("=== Test Case 3 ===");
        int[] arr5 = {9, 9, 9, 9, 9, 9, 9};  // represents 9999999
        int[] arr6 = {9, 9, 9, 9};            // represents 9999
        ListNode l5 = createList(arr5);
        ListNode l6 = createList(arr6);

        System.out.print("l1 = ");
        printList(l5);
        System.out.println("(represents " + listToNumber(l5) + ")");
        System.out.print("l2 = ");
        printList(l6);
        System.out.println("(represents " + listToNumber(l6) + ")");

        ListNode result3 = solution.addTwoNumbers(l5, l6);
        System.out.print("Result: ");
        printList(result3);
        System.out.println("(represents " + listToNumber(result3) + ")");
        System.out.println("Expected: [8,9,9,9,0,0,0,1] (represents 10009998)");
        System.out.println();

        // Test Case 4: Different lengths with carry
        System.out.println("=== Test Case 4 ===");
        int[] arr7 = {9, 9};     // represents 99
        int[] arr8 = {9};        // represents 9
        ListNode l7 = createList(arr7);
        ListNode l8 = createList(arr8);

        System.out.print("l1 = ");
        printList(l7);
        System.out.println("(represents " + listToNumber(l7) + ")");
        System.out.print("l2 = ");
        printList(l8);
        System.out.println("(represents " + listToNumber(l8) + ")");

        ListNode result4 = solution.addTwoNumbers(l7, l8);
        System.out.print("Result: ");
        printList(result4);
        System.out.println("(represents " + listToNumber(result4) + ")");
        System.out.println("Expected: [8,0,1] (represents 108)");
        System.out.println();

        // Test Case 5: Single carry at the end
        System.out.println("=== Test Case 5 ===");
        int[] arr9 = {5};        // represents 5
        int[] arr10 = {5};       // represents 5
        ListNode l9 = createList(arr9);
        ListNode l10 = createList(arr10);

        System.out.print("l1 = ");
        printList(l9);
        System.out.print("l2 = ");
        printList(l10);

        ListNode result5 = solution.addTwoNumbers(l9, l10);
        System.out.print("Result: ");
        printList(result5);
        System.out.println("Expected: [0,1] (represents 10)");
    }
}
