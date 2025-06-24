package stacks.twopointer;

import java.util.Stack;

/*
In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.



Example 1:


Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6.
Example 2:


Input: head = [4,2,2,3]
Output: 7
Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
Example 3:


Input: head = [1,100000]
Output: 100001
Explanation:
There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.


Constraints:

The number of nodes in the list is an even integer in the range [2, 105].
1 <= Node.val <= 105
 */
public class MaximumTwinSumOfLinkedList {

    // Definition for singly-linked list
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

        /**
         * Finds the maximum twin sum using monotonic stack approach.
         *
         * Algorithm:
         * 1. First pass: Push first half of linked list values onto stack
         * 2. Second pass: Pop from stack while traversing second half
         * 3. Calculate twin sums and track maximum
         *
         * Twin relationship: node i is twin of node (n-1-i)
         * So when we're at position i in second half, we pop the corresponding
         * twin from the first half stored in stack.
         *
         * Time Complexity: O(n) - single pass through the linked list
         * Space Complexity: O(n/2) = O(n) - stack stores first half of nodes
         */
        public int pairSum(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ListNode current = head;

            // First pass: Find the length and push first half to stack
            int length = 0;
            ListNode temp = head;
            while (temp != null) {
                length++;
                temp = temp.next;
            }

            int halfLength = length / 2;

            // Push first half values onto stack
            for (int i = 0; i < halfLength; i++) {
                stack.push(current.val);
                current = current.next;
            }

            int maxTwinSum = 0;

            // Second pass: Process second half and calculate twin sums
            for (int i = halfLength; i < length; i++) {
                // Pop the twin value from first half
                int twinValue = stack.pop();
                int twinSum = current.val + twinValue;
                maxTwinSum = Math.max(maxTwinSum, twinSum);
                current = current.next;
            }

            return maxTwinSum;
        }

        /**
         * Alternative approach: Single pass with stack
         * More elegant solution that doesn't require calculating length first
         */
        public int pairSumOptimized(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ListNode slow = head;
            ListNode fast = head;

            // Use two pointers to find middle while pushing first half to stack
            while (fast != null && fast.next != null) {
                stack.push(slow.val);
                slow = slow.next;
                fast = fast.next.next;
            }

            int maxTwinSum = 0;

            // Now slow is at the start of second half
            // Pop from stack to get twins from first half
            while (slow != null) {
                int twinSum = slow.val + stack.pop();
                maxTwinSum = Math.max(maxTwinSum, twinSum);
                slow = slow.next;
            }

            return maxTwinSum;
        }

        // Helper method to create linked list from array
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

        // Helper method to print linked list
        public static void printLinkedList(ListNode head) {
            System.out.print("[");
            while (head != null) {
                System.out.print(head.val);
                if (head.next != null) System.out.print(",");
                head = head.next;
            }
            System.out.println("]");
        }

        // Test method
        public static void main(String[] args) {
            MaximumTwinSumOfLinkedList solution = new MaximumTwinSumOfLinkedList();

            // Test Case 1: [5,4,2,1]
            System.out.println("=== Test Case 1 ===");
            int[] values1 = {5, 4, 2, 1};
            ListNode head1 = createLinkedList(values1);
            System.out.print("Input: ");
            printLinkedList(head1);

            int result1 = solution.pairSum(head1);
            int result1Opt = solution.pairSumOptimized(createLinkedList(values1));

            System.out.println("Output (Method 1): " + result1);
            System.out.println("Output (Optimized): " + result1Opt);
            System.out.println("Expected: 6");
            System.out.println("Twin pairs: (0,3)=5+1=6, (1,2)=4+2=6");
            System.out.println();

            // Test Case 2: [4,2,2,3]
            System.out.println("=== Test Case 2 ===");
            int[] values2 = {4, 2, 2, 3};
            ListNode head2 = createLinkedList(values2);
            System.out.print("Input: ");
            printLinkedList(head2);

            int result2 = solution.pairSum(head2);
            int result2Opt = solution.pairSumOptimized(createLinkedList(values2));

            System.out.println("Output (Method 1): " + result2);
            System.out.println("Output (Optimized): " + result2Opt);
            System.out.println("Expected: 7");
            System.out.println("Twin pairs: (0,3)=4+3=7, (1,2)=2+2=4");
            System.out.println();

            // Test Case 3: [1,100000]
            System.out.println("=== Test Case 3 ===");
            int[] values3 = {1, 100000};
            ListNode head3 = createLinkedList(values3);
            System.out.print("Input: ");
            printLinkedList(head3);

            int result3 = solution.pairSum(head3);
            int result3Opt = solution.pairSumOptimized(createLinkedList(values3));

            System.out.println("Output (Method 1): " + result3);
            System.out.println("Output (Optimized): " + result3Opt);
            System.out.println("Expected: 100001");
            System.out.println("Twin pairs: (0,1)=1+100000=100001");
            System.out.println();

            // Additional Test Case: [1,2,3,4,5,6]
            System.out.println("=== Additional Test Case ===");
            int[] values4 = {1, 2, 3, 4, 5, 6};
            ListNode head4 = createLinkedList(values4);
            System.out.print("Input: ");
            printLinkedList(head4);

            int result4 = solution.pairSum(head4);
            int result4Opt = solution.pairSumOptimized(createLinkedList(values4));

            System.out.println("Output (Method 1): " + result4);
            System.out.println("Output (Optimized): " + result4Opt);
            System.out.println("Expected: 7");
            System.out.println("Twin pairs: (0,5)=1+6=7, (1,4)=2+5=7, (2,3)=3+4=7");
        }
    }

/*
 * Detailed Walkthrough for Example 1: [5,4,2,1]
 *
 * Twin relationships for n=4:
 * - Node 0 (value=5) is twin of Node 3 (value=1) → sum = 6
 * - Node 1 (value=4) is twin of Node 2 (value=2) → sum = 6
 *
 * Stack-based approach:
 * 1. Push first half [5,4] onto stack: stack = [5,4] (bottom to top)
 * 2. Process second half [2,1]:
 *    - At node 2 (value=2): pop 4, twin sum = 2+4 = 6
 *    - At node 3 (value=1): pop 5, twin sum = 1+5 = 6
 * 3. Maximum twin sum = max(6,6) = 6
 *
 * Optimized approach using two pointers:
 * - Fast pointer moves 2 steps, slow pointer moves 1 step
 * - When fast reaches end, slow is at middle
 * - Push values while moving slow pointer to middle
 * - Then process second half with slow pointer
 */
