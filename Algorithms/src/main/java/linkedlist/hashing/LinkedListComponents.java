package linkedlist.hashing;

import linkedlist.ListNode;

import java.util.HashSet;

/*
You are given the head of a linked list containing unique integer values and an integer array nums that is a subset of the linked list values.

Return the number of connected components in nums where two values are connected if they appear consecutively in the linked list.



Example 1:


Input: head = [0,1,2,3], nums = [0,1,3]
Output: 2
Explanation: 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
Example 2:


Input: head = [0,1,2,3,4], nums = [0,3,1,4]
Output: 2
Explanation: 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.


Constraints:

The number of nodes in the linked list is n.
1 <= n <= 104
0 <= Node.val < n
All the values Node.val are unique.
1 <= nums.length <= n
0 <= nums[i] < n
All the values of nums are unique.
 */
public class LinkedListComponents {
    /**
     * Given the head of a linked list containing unique integer values and an integer array nums
     * that is a subset of the linked list values, returns the number of connected components in nums
     * where two values are connected if they appear consecutively in the linked list.
     *
     * @param head The head of the linked list.
     * @param nums An array of integers that is a subset of the linked list values.
     * @return The number of connected components.
     */
    public int numComponents(ListNode head, int[] nums) {
        // Step 1: Store all elements from the 'nums' array into a HashSet for efficient O(1) average time lookups.
        HashSet<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }

        int connectedComponents = 0; // Initialize a counter for connected components
        ListNode current = head;     // Start traversing the linked list from the head

        // Step 2: Iterate through the linked list to find connected components.
        // We count a new component when we encounter a node whose value is in 'numsSet',
        // and its preceding node's value (if any) was NOT in 'numsSet',
        // or it's the very first node of the list.
        // A simpler way is to count when a number in nums is followed by a number not in nums
        // or the end of the list.
        while (current != null) {
            // Check if the current node's value is present in our numsSet.
            if (numsSet.contains(current.val)) {
                // If the current node's value is in numsSet, we need to determine if it's
                // the start of a new component or part of an existing one.
                // A component ends (and thus we count it) if:
                // 1. There is no next node (current.next is null), meaning this is the last node in the list.
                // 2. The next node's value is NOT in numsSet, meaning the sequence of connected values breaks here.
                if (current.next == null || !numsSet.contains(current.next.val)) {
                    connectedComponents++; // Increment the count as we've found the end of a connected component.
                }
            }
            current = current.next; // Move to the next node in the linked list.
        }

        return connectedComponents; // Return the total number of connected components found.
    }

    // Main method for testing the solution
    public static void main(String[] args) {
        LinkedListComponents solver = new LinkedListComponents();

        // --- Example 1: head = [0,1,2,3], nums = [0,1,3] --> Output: 2
        // Components: [0, 1] and [3]
        ListNode head1 = new ListNode(0);
        head1.next = new ListNode(1);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(3);
        int[] nums1 = {0, 1, 3};
        System.out.println("--- Example 1 ---");
        System.out.println("Input List: [0,1,2,3], nums: [0,1,3]");
        System.out.println("Number of connected components: " + solver.numComponents(head1, nums1)); // Expected: 2
        System.out.println();

        // --- Example 2: head = [0,1,2,3,4], nums = [0,3,1,4] --> Output: 2
        // Components: [0, 1] and [3, 4]
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(2);
        head2.next.next.next = new ListNode(3);
        head2.next.next.next.next = new ListNode(4);
        int[] nums2 = {0, 3, 1, 4};
        System.out.println("--- Example 2 ---");
        System.out.println("Input List: [0,1,2,3,4], nums: [0,3,1,4]");
        System.out.println("Number of connected components: " + solver.numComponents(head2, nums2)); // Expected: 2
        System.out.println();

        // --- Additional Test Case: Single component ---
        ListNode head3 = new ListNode(10);
        head3.next = new ListNode(20);
        head3.next.next = new ListNode(30);
        int[] nums3 = {10, 20, 30};
        System.out.println("--- Additional Test Case 1 (Single Component) ---");
        System.out.println("Input List: [10,20,30], nums: [10,20,30]");
        System.out.println("Number of connected components: " + solver.numComponents(head3, nums3)); // Expected: 1
        System.out.println();

        // --- Additional Test Case: Disconnected elements ---
        ListNode head4 = new ListNode(0);
        head4.next = new ListNode(1);
        head4.next.next = new ListNode(2);
        head4.next.next.next = new ListNode(3);
        head4.next.next.next.next = new ListNode(4);
        int[] nums4 = {0, 2, 4};
        System.out.println("--- Additional Test Case 2 (Disconnected Elements) ---");
        System.out.println("Input List: [0,1,2,3,4], nums: [0,2,4]");
        System.out.println("Number of connected components: " + solver.numComponents(head4, nums4)); // Expected: 3
        System.out.println();

        // --- Additional Test Case: Empty nums array (edge case) ---
        ListNode head5 = new ListNode(0);
        head5.next = new ListNode(1);
        int[] nums5 = {}; // Empty nums array
        System.out.println("--- Additional Test Case 3 (Empty nums) ---");
        System.out.println("Input List: [0,1], nums: []");
        System.out.println("Number of connected components: " + solver.numComponents(head5, nums5)); // Expected: 0
        System.out.println();

        // --- Additional Test Case: All values not in nums ---
        ListNode head6 = new ListNode(0);
        head6.next = new ListNode(1);
        head6.next.next = new ListNode(2);
        int[] nums6 = {10, 11}; // Values not in list
        System.out.println("--- Additional Test Case 4 (No match) ---");
        System.out.println("Input List: [0,1,2], nums: [10,11]");
        System.out.println("Number of connected components: " + solver.numComponents(head6, nums6)); // Expected: 0
        System.out.println();
    }
}
