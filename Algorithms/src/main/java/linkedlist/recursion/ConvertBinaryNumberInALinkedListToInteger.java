package linkedlist.recursion;

import linkedlist.ListNode;

import java.util.Arrays;

/*
Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.

The most significant bit is at the head of the linked list.



Example 1:


Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10
Example 2:

Input: head = [0]
Output: 0


Constraints:

The Linked List is not empty.
Number of nodes will not exceed 30.
Each node's value is either 0 or 1.
 */
// Approach 1: Left Shift Method(most efficient)
public class ConvertBinaryNumberInALinkedListToInteger {
    public int getDecimalValueLS(ListNode head) {
        int result = 0;

        while (head != null) {
            // Left shift result by 1 bit and add current bit
            result = (result << 1) | head.val;
            head = head.next;
        }

        return result;
    }

// Approach 2: Iterative - Multiplication Method
    public int getDecimalValueIM(ListNode head) {
        int result = 0;

        while (head != null) {
            // Equivalent to result = result * 2 + head.val
            result = result * 2 + head.val;
            head = head.next;
        }

        return result;
    }


// Approach 3: Recursive Approach
    public int getDecimalValueRC(ListNode head) {
        return getDecimalHelper(head, getLength(head) - 1);
    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    private int getDecimalHelper(ListNode head, int power) {
        if (head == null) {
            return 0;
        }

        // Current bit value = head.val * 2^power
        int currentValue = head.val * (int) Math.pow(2, power);

        // Add current value + recursive call for rest
        return currentValue + getDecimalHelper(head.next, power - 1);
    }

// Approach 4: Elegant Recursive (without calculating length first)
    public int getDecimalValue(ListNode head) {
        return getDecimalValue(head, 0);
    }

    private int getDecimalValue(ListNode head, int currentValue) {
        if (head == null) {
            return currentValue;
        }

        // Shift current value left and add new bit
        return getDecimalValue(head.next, (currentValue << 1) | head.val);
    }

// Approach 5: Using StringBuilder and Integer.parseInt
    public int getDecimalValueSB(ListNode head) {
        StringBuilder binary = new StringBuilder();

        while (head != null) {
            binary.append(head.val);
            head = head.next;
        }

        return Integer.parseInt(binary.toString(), 2);
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

    // Convert array to binary string for display
    public static String arrayToBinaryString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int bit : arr) {
            sb.append(bit);
        }
        return sb.toString();
    }

    // Calculate expected decimal value from binary array
    public static int calculateExpected(int[] arr) {
        int result = 0;
        for (int bit : arr) {
            result = (result << 1) | bit;
        }
        return result;
    }

    // Test all solutions
    public static void main(String[] args) {
        ConvertBinaryNumberInALinkedListToInteger solution = new ConvertBinaryNumberInALinkedListToInteger();

        // Test Case 1: [1,0,1] = 5
        System.out.println("=== Test Case 1 ===");
        int[] arr1 = {1, 0, 1};
        ListNode head1 = createList(arr1);

        System.out.print("Input: ");
        printList(head1);
        System.out.println("Binary: " + arrayToBinaryString(arr1) + " (base 2)");
        System.out.println("Expected: " + calculateExpected(arr1));

        head1 = createList(arr1); // Recreate for each test
        System.out.println("Left Shift:       " + solution.getDecimalValueLS(head1));

        head1 = createList(arr1);
        System.out.println("Multiplication:   " + solution.getDecimalValueIM(head1));

        head1 = createList(arr1);
        System.out.println("Recursive:        " + solution.getDecimalValueRC(head1));

        head1 = createList(arr1);
        System.out.println("Rec. Elegant:     " + solution.getDecimalValue(head1));

        head1 = createList(arr1);
        System.out.println("StringBuilder:    " + solution.getDecimalValueSB(head1));
        System.out.println();

        // Test Case 2: [0] = 0
        System.out.println("=== Test Case 2 ===");
        int[] arr2 = {0};
        ListNode head2 = createList(arr2);

        System.out.print("Input: ");
        printList(head2);
        System.out.println("Binary: " + arrayToBinaryString(arr2) + " (base 2)");
        System.out.println("Expected: " + calculateExpected(arr2));

        head2 = createList(arr2);
        System.out.println("Result: " + solution.getDecimalValue(head2));
        System.out.println();

        // Test Case 3: [1] = 1
        System.out.println("=== Test Case 3 ===");
        int[] arr3 = {1};
        ListNode head3 = createList(arr3);

        System.out.print("Input: ");
        printList(head3);
        System.out.println("Binary: " + arrayToBinaryString(arr3) + " (base 2)");
        System.out.println("Expected: " + calculateExpected(arr3));

        head3 = createList(arr3);
        System.out.println("Result: " + solution.getDecimalValue(head3));
        System.out.println();

        // Test Case 4: [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0] = 18560
        System.out.println("=== Test Case 4 (Longer) ===");
        int[] arr4 = {1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0};
        ListNode head4 = createList(arr4);

        System.out.print("Input: ");
        printList(head4);
        System.out.println("Binary: " + arrayToBinaryString(arr4) + " (base 2)");
        System.out.println("Expected: " + calculateExpected(arr4));

        head4 = createList(arr4);
        System.out.println("Left Shift: " + solution.getDecimalValue(head4));

        head4 = createList(arr4);
        System.out.println("Recursive:  " + solution.getDecimalValueRC(head4));
        System.out.println();

        // Test Case 5: All 1s - [1,1,1,1,1] = 31
        System.out.println("=== Test Case 5 (All 1s) ===");
        int[] arr5 = {1, 1, 1, 1, 1};
        ListNode head5 = createList(arr5);

        System.out.print("Input: ");
        printList(head5);
        System.out.println("Binary: " + arrayToBinaryString(arr5) + " (base 2)");
        System.out.println("Expected: " + calculateExpected(arr5));

        head5 = createList(arr5);
        System.out.println("Result: " + solution.getDecimalValue(head5));
        System.out.println();

        // Test Case 6: Maximum case (30 bits)
        System.out.println("=== Test Case 6 (Edge Case - Large Number) ===");
        int[] arr6 = new int[10]; // 10 bits for demonstration
        Arrays.fill(arr6, 1); // All 1s
        ListNode head6 = createList(arr6);

        System.out.print("Input: ");
        printList(head6);
        System.out.println("Binary: " + arrayToBinaryString(arr6) + " (base 2)");
        System.out.println("Expected: " + calculateExpected(arr6));

        head6 = createList(arr6);
        System.out.println("Result: " + solution.getDecimalValue(head6));
    }
}
