package stacks.recursion;

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
    // Definition for singly-linked list.
// This class represents a node in a singly-linked list.
    class ListNode {
        int val; // Value of the node
        ListNode next; // Pointer to the next node in the list
        ListNode() {} // Default constructor
        ListNode(int val) { this.val = val; } // Constructor with value
        ListNode(int val, ListNode next) { this.val = val; this.next = next; } // Constructor with value and next node
    }

    class MaxTwinSumOfLinkedList {
        /**
         * Calculates the maximum twin sum of a singly-linked list with even length.
         * The i-th node's twin is the (n-1-i)-th node.
         * This solution uses an explicit Stack to store the values of the second half of the list
         * (populated recursively) and then iteratively calculates the twin sums.
         *
         * @param head The head of the linked list.
         * @return The maximum twin sum found.
         */
        public int maxTwinSum(ListNode head) {
            // Step 1: Calculate the length of the list (n).
            // This is necessary to correctly identify the starting node of the second half.
            int n = 0;
            ListNode temp = head;
            while (temp != null) {
                n++;
                temp = temp.next;
            }

            // Step 2: Find the starting node of the second half of the list.
            // For an even length 'n', the second half starts at index n/2.
            ListNode secondHalfStart = head;
            for (int i = 0; i < n / 2; i++) {
                secondHalfStart = secondHalfStart.next;
            }

            // Step 3: Recursively populate a stack with the values from the second half of the list.
            // The goal is for `stack.pop()` to yield s[n-1], then s[n-2], ..., down to s[n/2].
            // To achieve this, the stack needs to contain values [s[n/2], s[n/2+1], ..., s[n-1]]
            // from bottom to top.
            Stack<Integer> stack = new Stack<>();
            // The helper function is designed to push nodes in the order they appear
            // in the sub-list (pre-order push behavior).
            populateStackForTwins(secondHalfStart, stack);

            // Step 4: Calculate the maximum twin sum by iterating through the first half
            // and pairing with elements popped from the stack.
            int maxTwinSum = 0;
            ListNode current = head; // Start from the beginning of the list (first half)

            // Iterate exactly n/2 times, as there are n/2 twin pairs.
            for (int i = 0; i < n / 2; i++) {
                // Get the value from the current node in the first half.
                int valFromFront = current.val;
                // Get the twin's value by popping from the stack.
                // Due to `populateStackForTwins`, this will correctly give s[n-1], then s[n-2], etc.
                int valFromBack = stack.pop();

                // Calculate the current twin sum.
                int currentTwinSum = valFromFront + valFromBack;

                // Update the overall maximum twin sum found so far.
                maxTwinSum = Math.max(maxTwinSum, currentTwinSum);

                // Move to the next node in the first half.
                current = current.next;
            }

            // Return the final maximum twin sum.
            return maxTwinSum;
        }

        /**
         * Helper function to recursively traverse a sub-list and push node values onto a stack.
         * This function uses a pre-order-like traversal for pushing:
         * it pushes the current node's value first, then recurses.
         * When called on the second half of the linked list (e.g., [2, 1]),
         * this will populate the stack as [2, 1] (bottom to top), meaning
         * `stack.pop()` will correctly yield 1, then 2, which matches the required twin order.
         *
         * @param node The current node being processed in the recursion (start of sub-list).
         * @param stack The stack to which node values will be pushed.
         */
        private void populateStackForTwins(ListNode node, Stack<Integer> stack) {
            // Base case: If the current node is null, we have reached the end of the sub-list.
            if (node == null) {
                return;
            }

            // Push the current node's value onto the stack.
            // This is a "pre-order" push relative to the sub-list traversal.
            stack.push(node.val);

            // Recursively call for the next node in the sub-list.
            populateStackForTwins(node.next, stack);
        }

        public static void main(String[] args) {
            MaxTwinSumOfLinkedList solution = new MaxTwinSumOfLinkedList();
            // Example usage:
            ListNode head = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));
            int result = solution.maxTwinSum(head);
            System.out.println("Maximum Twin Sum: " + result); // Output: 6
        }
}
