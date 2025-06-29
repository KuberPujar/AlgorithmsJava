package stacks.binarytree;

import linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    /**
     * Approach 1: Using Stack - Push all values, then compare
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean isPalindromeStack(ListNode head) {
        if (head == null || head.next == null) return true;

        Stack<Integer> stack = new Stack<>();
        ListNode current = head;

        // Push all values to stack
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }

        // Compare with original list
        current = head;
        while (current != null) {
            if (current.val != stack.pop()) {
                return false;
            }
            current = current.next;
        }

        return true;
    }

    /**
     * Approach 2: Using Stack with Two Pointers (Optimized)
     * Push only first half to stack, compare with second half
     * Time Complexity: O(n)
     * Space Complexity: O(n/2) = O(n)
     */
    public boolean isPalindromeStackOptimized(ListNode head) {
        if (head == null || head.next == null) return true;

        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;

        // Find middle using two pointers and push first half to stack
        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        // If odd number of nodes, skip middle element
        if (fast != null) {
            slow = slow.next;
        }

        // Compare second half with stack
        while (slow != null) {
            if (slow.val != stack.pop()) {
                return false;
            }
            slow = slow.next;
        }

        return true;
    }

    /**
     * Approach 3: Convert to Array and use Stack
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean isPalindromeArray(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode current = head;

        // Convert to array
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        // Use stack to check palindrome
        Stack<Integer> stack = new Stack<>();
        int n = values.size();

        // Push first half
        for (int i = 0; i < n / 2; i++) {
            stack.push(values.get(i));
        }

        // Compare second half
        int start = (n % 2 == 0) ? n / 2 : n / 2 + 1;
        for (int i = start; i < n; i++) {
            if (values.get(i) != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Approach 4: O(1) Space Solution - Reverse Second Half
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean isPalindromeConstantSpace(ListNode head) {
        if (head == null || head.next == null) return true;

        // Find middle of linked list
        ListNode slow = head;
        ListNode fast = head;
        ListNode prevSlow = null;

        while (fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // For odd length, move slow one step further
        ListNode secondHalf = slow;
        if (fast != null) {
            secondHalf = slow.next;
        }

        // Reverse the second half
        ListNode reversedSecondHalf = reverseList(secondHalf);

        // Compare first half with reversed second half
        boolean isPalindrome = true;
        ListNode firstHalf = head;
        ListNode reversedHalfCopy = reversedSecondHalf;

        while (reversedHalfCopy != null) {
            if (firstHalf.val != reversedHalfCopy.val) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            reversedHalfCopy = reversedHalfCopy.next;
        }

        // Restore the original list (optional)
        reverseList(reversedSecondHalf);

        return isPalindrome;
    }

    /**
     * Helper method to reverse a linked list
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        return prev;
    }

    /**
     * Approach 5: Recursive with Stack-like behavior
     * Uses call stack instead of explicit stack
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion stack
     */
    private ListNode frontPointer;

    public boolean isPalindromeRecursive(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    /**
     * Helper method to create linked list from array
     */
    public ListNode createList(int[] values) {
        if (values == null || values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Helper method to print linked list
     */
    public void printList(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode current = head;

        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        System.out.println(values);
    }

    /**
     * Helper method to get length of linked list
     */
    public int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;

        while (current != null) {
            length++;
            current = current.next;
        }

        return length;
    }

    /**
     * Visualization method to show stack operations
     */
    public boolean isPalindromeWithVisualization(ListNode head) {
        System.out.println("=== Stack-based Palindrome Check Visualization ===");
        if (head == null || head.next == null) {
            System.out.println("Single node or empty list - returning true");
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;

        System.out.println("Phase 1: Finding middle and pushing first half to stack");
        int step = 1;

        while (fast != null && fast.next != null) {
            System.out.println("Step " + step + ": Push " + slow.val + " to stack");
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
            System.out.println("         Stack: " + stack);
            step++;
        }

        if (fast != null) {
            System.out.println("Odd length list - skipping middle element: " + slow.val);
            slow = slow.next;
        }

        System.out.println("\nPhase 2: Comparing second half with stack");
        step = 1;

        while (slow != null) {
            int stackTop = stack.pop();
            System.out.println("Step " + step + ": Compare " + slow.val + " with " + stackTop);
            if (slow.val != stackTop) {
                System.out.println("         Mismatch found! Not a palindrome.");
                return false;
            }
            System.out.println("         Match! Stack: " + stack);
            slow = slow.next;
            step++;
        }

        System.out.println("All comparisons successful - it's a palindrome!");
        return true;
    }

    /**
     * Test method with examples and performance comparison
     */
    public static void main(String[] args) {
        PalindromeLinkedList solution = new PalindromeLinkedList();

        // Example 1: [1,2,2,1] - Palindrome
        System.out.println("Example 1: [1,2,2,1]");
        ListNode head1 = solution.createList(new int[]{1, 2, 2, 1});
        solution.printList(head1);
        System.out.println("Stack Approach: " + solution.isPalindromeStack(head1));
        System.out.println("Stack Optimized: " + solution.isPalindromeStackOptimized(head1));
        System.out.println("Array Approach: " + solution.isPalindromeArray(head1));
        System.out.println("Constant Space: " + solution.isPalindromeConstantSpace(head1));
        System.out.println("Recursive: " + solution.isPalindromeRecursive(head1));

        // Example 2: [1,2] - Not palindrome
        System.out.println("\nExample 2: [1,2]");
        ListNode head2 = solution.createList(new int[]{1, 2});
        solution.printList(head2);
        System.out.println("Stack Approach: " + solution.isPalindromeStack(head2));
        System.out.println("Stack Optimized: " + solution.isPalindromeStackOptimized(head2));
        System.out.println("Constant Space: " + solution.isPalindromeConstantSpace(head2));

        // Additional test cases
        System.out.println("\nAdditional Test Cases:");

        // Single node
        ListNode head3 = solution.createList(new int[]{1});
        System.out.println("Single node [1]: " + solution.isPalindromeStack(head3));

        // Odd length palindrome
        ListNode head4 = solution.createList(new int[]{1, 2, 3, 2, 1});
        System.out.println("Odd palindrome [1,2,3,2,1]: " + solution.isPalindromeStack(head4));

        // Even length palindrome
        ListNode head5 = solution.createList(new int[]{1, 2, 3, 3, 2, 1});
        System.out.println("Even palindrome [1,2,3,3,2,1]: " + solution.isPalindromeStack(head5));

        // Visualization example
        System.out.println("\nVisualization for [1,2,2,1]:");
        ListNode visHead = solution.createList(new int[]{1, 2, 2, 1});
        solution.isPalindromeWithVisualization(visHead);

        // Performance comparison
        System.out.println("\nPerformance Test:");
        int[] largeArray = new int[10000];
        for (int i = 0; i < 5000; i++) {
            largeArray[i] = i % 10;
            largeArray[9999 - i] = i % 10;
        }
        ListNode largeHead = solution.createList(largeArray);

        long start = System.nanoTime();
        boolean result1 = solution.isPalindromeStack(largeHead);
        long time1 = System.nanoTime() - start;

        start = System.nanoTime();
        boolean result2 = solution.isPalindromeStackOptimized(largeHead);
        long time2 = System.nanoTime() - start;

        start = System.nanoTime();
        boolean result3 = solution.isPalindromeConstantSpace(largeHead);
        long time3 = System.nanoTime() - start;

        System.out.println("Stack (full): " + result1 + " (Time: " + time1 + " ns)");
        System.out.println("Stack (optimized): " + result2 + " (Time: " + time2 + " ns)");
        System.out.println("Constant space: " + result3 + " (Time: " + time3 + " ns)");

        System.out.println("\nSpace Complexity Comparison:");
        System.out.println("Stack (full): O(n) - stores all elements");
        System.out.println("Stack (optimized): O(n/2) - stores half elements");
        System.out.println("Constant space: O(1) - no extra space for values");
    }
}
