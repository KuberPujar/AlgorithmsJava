package linkedlist.stack;

import linkedlist.ListNode;
import linkedlist.iterativemanner.AddTwoNumbersIterative;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.



Example 1:


Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]
Example 2:

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [8,0,7]
Example 3:

Input: l1 = [0], l2 = [0]
Output: [0]


Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.


Follow up: Could you solve it without reversing the input lists?
 */
public class AddTwoNumbersUsingStack {
    /**
     * Add two numbers represented as linked lists using stack approach
     * Most significant digit comes first, so we use stacks to process from right to left
     * Time Complexity: O(max(m, n)) where m and n are lengths of the lists
     * Space Complexity: O(m + n) for the stacks
     *
     * @param l1 First number as linked list
     * @param l2 Second number as linked list
     * @return Sum as linked list
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Step 1: Push all digits into stacks to reverse the order
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        // Push digits from l1 into stack1
        ListNode current1 = l1;
        while (current1 != null) {
            stack1.push(current1.val);
            current1 = current1.next;
        }

        // Push digits from l2 into stack2
        ListNode current2 = l2;
        while (current2 != null) {
            stack2.push(current2.val);
            current2 = current2.next;
        }

        // Step 2: Add digits from right to left using stacks
        ListNode result = null;  // Will build result from right to left
        int carry = 0;

        // Process while there are digits in either stack or carry exists
        while (!stack1.empty() || !stack2.empty() || carry > 0) {
            int digit1 = stack1.empty() ? 0 : stack1.pop();
            int digit2 = stack2.empty() ? 0 : stack2.pop();

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            int digit = sum % 10;

            // Create new node and add to front of result (since we're building backwards)
            ListNode newNode = new ListNode(digit);
            newNode.next = result;
            result = newNode;
        }

        return result;
    }
}

// Alternative solution with detailed step tracking
class SolutionDetailed {
    /**
     * Add two numbers with detailed step-by-step process visualization
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        System.out.println("=== Step-by-step Addition Process ===");

        // Build stacks
        System.out.print("Building stack1: ");
        ListNode current = l1;
        while (current != null) {
            stack1.push(current.val);
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println("-> Stack1: " + stack1);

        System.out.print("Building stack2: ");
        current = l2;
        while (current != null) {
            stack2.push(current.val);
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println("-> Stack2: " + stack2);

        // Addition process
        ListNode result = null;
        int carry = 0;
        int step = 1;

        System.out.println("\nAddition steps (right to left):");
        while (!stack1.empty() || !stack2.empty() || carry > 0) {
            int digit1 = stack1.empty() ? 0 : stack1.pop();
            int digit2 = stack2.empty() ? 0 : stack2.pop();

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            int digit = sum % 10;

            System.out.printf("Step %d: %d + %d + %d(carry) = %d, digit=%d, carry=%d%n",
                    step++, digit1, digit2, (sum - digit1 - digit2), sum, digit, carry);

            // Add to front of result
            ListNode newNode = new ListNode(digit);
            newNode.next = result;
            result = newNode;
        }

        return result;
    }
}

// Helper class for testing and utility functions
class LinkedListHelper {
    /**
     * Create a linked list from array of values
     */
    public static ListNode createLinkedList(int[] values) {
        if (values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Convert linked list to array for easy printing and verification
     */
    public static int[] linkedListToArray(ListNode head) {
        if (head == null) return new int[0];

        List<Integer> result = new ArrayList<>();
        ListNode current = head;

        while (current != null) {
            result.add(current.val);
            current = current.next;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Print linked list as number format
     */
    public static void printAsNumber(ListNode head) {
        if (head == null) {
            System.out.print("0");
            return;
        }

        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            current = current.next;
        }
    }

    /**
     * Print linked list as array format
     */
    public static void printLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    /**
     * Print array
     */
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    /**
     * Convert linked list to actual number (for verification of small numbers)
     */
    public static long toNumber(ListNode head) {
        long number = 0;
        ListNode current = head;
        while (current != null) {
            number = number * 10 + current.val;
            current = current.next;
        }
        return number;
    }
}

// Test class to demonstrate the solution
class TestAddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbersUsingStack solution = new AddTwoNumbersUsingStack();
        SolutionDetailed detailedSolution = new SolutionDetailed();

        System.out.println("=== Add Two Numbers II Using Stack ===\n");

        // Test Example 1: [7,2,4,3] + [5,6,4] = [7,8,0,7]
        System.out.println("Example 1:");
        int[] input1_1 = {7, 2, 4, 3};  // represents 7243
        int[] input1_2 = {5, 6, 4};     // represents 564

        System.out.print("l1 = ");
        LinkedListHelper.printArray(input1_1);
        System.out.print("l2 = ");
        LinkedListHelper.printArray(input1_2);
        System.out.println("Expected: [7,8,0,7] (7243 + 564 = 7807)");

        ListNode l1_1 = LinkedListHelper.createLinkedList(input1_1);
        ListNode l2_1 = LinkedListHelper.createLinkedList(input1_2);
        ListNode result1 = solution.addTwoNumbers(l1_1, l2_1);

        System.out.print("Output: ");
        LinkedListHelper.printLinkedList(result1);
        System.out.print("Verification: ");
        System.out.println(LinkedListHelper.toNumber(LinkedListHelper.createLinkedList(input1_1)) +
                " + " + LinkedListHelper.toNumber(LinkedListHelper.createLinkedList(input1_2)) +
                " = " + LinkedListHelper.toNumber(result1));
        System.out.println();

        // Test Example 2: [2,4,3] + [5,6,4] = [8,0,7]
        System.out.println("Example 2:");
        int[] input2_1 = {2, 4, 3};  // represents 243
        int[] input2_2 = {5, 6, 4};  // represents 564

        System.out.print("l1 = ");
        LinkedListHelper.printArray(input2_1);
        System.out.print("l2 = ");
        LinkedListHelper.printArray(input2_2);
        System.out.println("Expected: [8,0,7] (243 + 564 = 807)");

        ListNode l1_2 = LinkedListHelper.createLinkedList(input2_1);
        ListNode l2_2 = LinkedListHelper.createLinkedList(input2_2);
        ListNode result2 = solution.addTwoNumbers(l1_2, l2_2);

        System.out.print("Output: ");
        LinkedListHelper.printLinkedList(result2);
        System.out.print("Verification: ");
        System.out.println(LinkedListHelper.toNumber(LinkedListHelper.createLinkedList(input2_1)) +
                " + " + LinkedListHelper.toNumber(LinkedListHelper.createLinkedList(input2_2)) +
                " = " + LinkedListHelper.toNumber(result2));
        System.out.println();

        // Test Example 3: [0] + [0] = [0]
        System.out.println("Example 3:");
        int[] input3_1 = {0};
        int[] input3_2 = {0};

        System.out.print("l1 = ");
        LinkedListHelper.printArray(input3_1);
        System.out.print("l2 = ");
        LinkedListHelper.printArray(input3_2);
        System.out.println("Expected: [0]");

        ListNode l1_3 = LinkedListHelper.createLinkedList(input3_1);
        ListNode l2_3 = LinkedListHelper.createLinkedList(input3_2);
        ListNode result3 = solution.addTwoNumbers(l1_3, l2_3);

        System.out.print("Output: ");
        LinkedListHelper.printLinkedList(result3);
        System.out.println();

        // Test with carry propagation: [9,9] + [1] = [1,0,0]
        System.out.println("Additional Test - Carry Propagation:");
        int[] input4_1 = {9, 9};  // represents 99
        int[] input4_2 = {1};     // represents 1

        System.out.print("l1 = ");
        LinkedListHelper.printArray(input4_1);
        System.out.print("l2 = ");
        LinkedListHelper.printArray(input4_2);
        System.out.println("Expected: [1,0,0] (99 + 1 = 100)");

        ListNode l1_4 = LinkedListHelper.createLinkedList(input4_1);
        ListNode l2_4 = LinkedListHelper.createLinkedList(input4_2);
        ListNode result4 = solution.addTwoNumbers(l1_4, l2_4);

        System.out.print("Output: ");
        LinkedListHelper.printLinkedList(result4);
        System.out.print("Verification: ");
        System.out.println(LinkedListHelper.toNumber(LinkedListHelper.createLinkedList(input4_1)) +
                " + " + LinkedListHelper.toNumber(LinkedListHelper.createLinkedList(input4_2)) +
                " = " + LinkedListHelper.toNumber(result4));
        System.out.println();

        // Detailed solution example
        System.out.println("=== Detailed Step-by-Step Example ===");
        ListNode l1_detailed = LinkedListHelper.createLinkedList(new int[]{7, 2, 4, 3});
        ListNode l2_detailed = LinkedListHelper.createLinkedList(new int[]{5, 6, 4});
        ListNode resultDetailed = detailedSolution.addTwoNumbers(l1_detailed, l2_detailed);

        System.out.print("Final Result: ");
        LinkedListHelper.printLinkedList(resultDetailed);

        // Algorithm explanation
        System.out.println("\n=== Algorithm Explanation ===");
        System.out.println("1. Push all digits from both linked lists into separate stacks");
        System.out.println("2. Pop digits from both stacks simultaneously (right to left processing)");
        System.out.println("3. Add corresponding digits plus carry from previous step");
        System.out.println("4. Create new nodes and build result list from right to left");
        System.out.println("5. Handle remaining carry at the end");
        System.out.println("6. Time: O(max(m,n)), Space: O(m+n) for stacks");
    }
}
