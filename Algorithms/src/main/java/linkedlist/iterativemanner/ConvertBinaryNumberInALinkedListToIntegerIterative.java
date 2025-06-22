package linkedlist.iterativemanner;

import linkedlist.ListNode;

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
public class ConvertBinaryNumberInALinkedListToIntegerIterative {
    /**
     * Iterative approach using bit shifting (most efficient)
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - constant extra space
     *
     * Algorithm:
     * 1. Start with result = 0
     * 2. For each node, left shift result by 1 bit (multiply by 2)
     * 3. Add current node's value to result
     * 4. This builds the decimal value from most significant bit to least
     */
    public int getDecimalValue(ListNode head) {
        int result = 0;
        ListNode current = head;

        while (current != null) {
            // Left shift by 1 bit (equivalent to multiplying by 2)
            result = result << 1;
            // Add current bit value
            result += current.val;
            // Move to next node
            current = current.next;
        }

        return result;
    }

    /**
     * Alternative approach using mathematical powers of 2
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - constant extra space
     */
    public int getDecimalValueMath(ListNode head) {
        int result = 0;
        ListNode current = head;

        while (current != null) {
            // Multiply previous result by 2 and add current bit
            result = result * 2 + current.val;
            current = current.next;
        }

        return result;
    }

    /**
     * Step-by-step approach for educational purposes
     * Shows the conversion process in detail
     */
    public int getDecimalValueStepByStep(ListNode head) {
        System.out.println("Converting binary linked list to decimal:");

        int result = 0;
        ListNode current = head;
        int position = 0;

        // Print the binary representation first
        System.out.print("Binary: ");
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val);
            temp = temp.next;
        }
        System.out.println();

        System.out.println("Step-by-step conversion:");

        while (current != null) {
            int oldResult = result;
            result = result << 1;  // Left shift (multiply by 2)
            result += current.val; // Add current bit

            System.out.printf("Step %d: %d << 1 + %d = %d + %d = %d\n",
                    position + 1, oldResult, current.val, oldResult << 1, current.val, result);

            current = current.next;
            position++;
        }

        System.out.println("Final decimal value: " + result);
        System.out.println();

        return result;
    }

    /**
     * Alternative implementation using string building (less efficient but intuitive)
     */
    public int getDecimalValueUsingString(ListNode head) {
        StringBuilder binaryStr = new StringBuilder();
        ListNode current = head;

        // Build binary string
        while (current != null) {
            binaryStr.append(current.val);
            current = current.next;
        }

        // Convert binary string to decimal
        return Integer.parseInt(binaryStr.toString(), 2);
    }

    /**
     * Implementation using powers of 2 calculation
     * Time Complexity: O(n^2) due to power calculation - less efficient
     */
    public int getDecimalValuePowers(ListNode head) {
        // First, count the number of nodes to determine powers
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Convert using powers of 2
        int result = 0;
        current = head;
        int power = length - 1;

        while (current != null) {
            if (current.val == 1) {
                result += Math.pow(2, power);
            }
            power--;
            current = current.next;
        }

        return result;
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

    // Helper method to manually verify binary to decimal conversion
    public static int manualBinaryToDecimal(int[] binary) {
        int result = 0;
        int power = binary.length - 1;

        for (int bit : binary) {
            result += bit * Math.pow(2, power);
            power--;
        }

        return result;
    }

    // Test method with all examples
    public static void main(String[] args) {
        ConvertBinaryNumberInALinkedListToIntegerIterative solution = new ConvertBinaryNumberInALinkedListToIntegerIterative();

        // Example 1: [1,0,1] -> 5
        System.out.println("Example 1:");
        ListNode head1 = createList(new int[]{1, 0, 1});
        System.out.print("Input: ");
        printList(head1);
        System.out.println(" (Binary: 101)");

        int result1 = solution.getDecimalValue(head1);
        System.out.println("Output: " + result1);
        System.out.println("Verification: 1×2² + 0×2¹ + 1×2⁰ = 4 + 0 + 1 = 5");
        System.out.println();

        // Example 2: [0] -> 0
        System.out.println("Example 2:");
        ListNode head2 = createList(new int[]{0});
        System.out.print("Input: ");
        printList(head2);
        System.out.println(" (Binary: 0)");

        int result2 = solution.getDecimalValue(head2);
        System.out.println("Output: " + result2);
        System.out.println();

        // Additional test cases
        System.out.println("Additional Test Cases:");

        // Single bit 1
        System.out.println("Single bit [1]:");
        ListNode head3 = createList(new int[]{1});
        System.out.print("Input: ");
        printList(head3);
        int result3 = solution.getDecimalValue(head3);
        System.out.println(" -> Output: " + result3);

        // Longer binary number [1,1,1,1]
        System.out.println("Four bits [1,1,1,1]:");
        ListNode head4 = createList(new int[]{1, 1, 1, 1});
        System.out.print("Input: ");
        printList(head4);
        int result4 = solution.getDecimalValue(head4);
        System.out.println(" -> Output: " + result4 + " (Binary: 1111 = 15)");

        // Mixed binary [1,0,0,1,0,1]
        System.out.println("Six bits [1,0,0,1,0,1]:");
        ListNode head5 = createList(new int[]{1, 0, 0, 1, 0, 1});
        System.out.print("Input: ");
        printList(head5);
        int result5 = solution.getDecimalValue(head5);
        System.out.println(" -> Output: " + result5 + " (Binary: 100101 = 37)");

        // All zeros except last [0,0,0,1]
        System.out.println("Leading zeros [0,0,0,1]:");
        ListNode head6 = createList(new int[]{0, 0, 0, 1});
        System.out.print("Input: ");
        printList(head6);
        int result6 = solution.getDecimalValue(head6);
        System.out.println(" -> Output: " + result6 + " (Binary: 0001 = 1)");

        System.out.println("\n" + "=".repeat(50));

        // Step-by-step demonstration
        System.out.println("Step-by-step demonstration:");
        ListNode demoHead = createList(new int[]{1, 0, 1, 1});
        System.out.print("Input: ");
        printList(demoHead);
        System.out.println();
        solution.getDecimalValueStepByStep(demoHead);

        // Compare different approaches
        System.out.println("Comparing different approaches for [1,1,0,1]:");
        ListNode compareHead = createList(new int[]{1, 1, 0, 1});
        System.out.print("Input: ");
        printList(compareHead);
        System.out.println();

        System.out.println("Bit shifting approach: " +
                solution.getDecimalValue(createList(new int[]{1, 1, 0, 1})));
        System.out.println("Mathematical approach: " +
                solution.getDecimalValueMath(createList(new int[]{1, 1, 0, 1})));
        System.out.println("String approach: " +
                solution.getDecimalValueUsingString(createList(new int[]{1, 1, 0, 1})));
        System.out.println("Powers approach: " +
                solution.getDecimalValuePowers(createList(new int[]{1, 1, 0, 1})));
        System.out.println("Manual verification: " +
                manualBinaryToDecimal(new int[]{1, 1, 0, 1}));
    }
}
