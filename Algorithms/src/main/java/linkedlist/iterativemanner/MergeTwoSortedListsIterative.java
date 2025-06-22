package linkedlist.iterativemanner;

import linkedlist.ListNode;

/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.



Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]


Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedListsIterative {
    /**
     * Iterative approach to merge two sorted linked lists
     * Time Complexity: O(m + n) where m and n are lengths of the two lists
     * Space Complexity: O(1) - only using constant extra space
     *
     * Algorithm:
     * 1. Create a dummy head to simplify list construction
     * 2. Use a current pointer to track the end of merged list
     * 3. Compare heads of both lists and attach smaller node
     * 4. Move the pointer of the list from which node was taken
     * 5. Continue until one list is exhausted
     * 6. Attach remaining nodes from the non-empty list
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create dummy head to simplify edge cases
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge nodes while both lists have elements
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                // Attach node from list1 and move list1 pointer
                current.next = list1;
                list1 = list1.next;
            } else {
                // Attach node from list2 and move list2 pointer
                current.next = list2;
                list2 = list2.next;
            }
            // Move current pointer to the newly attached node
            current = current.next;
        }

        // Attach remaining nodes (at most one list will have remaining nodes)
        if (list1 != null) {
            current.next = list1;
        } else if (list2 != null) {
            current.next = list2;
        }

        // Return the actual head (skip dummy)
        return dummy.next;
    }

    /**
     * Alternative implementation with more explicit null checking
     */
    public ListNode mergeTwoListsExplicit(ListNode list1, ListNode list2) {
        // Handle edge cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge while both lists have nodes
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach remaining part of the non-empty list
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    /**
     * Step-by-step implementation for educational purposes
     */
    public ListNode mergeTwoListsStepByStep(ListNode list1, ListNode list2) {
        System.out.println("Starting merge process...");

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int step = 1;

        while (list1 != null && list2 != null) {
            System.out.printf("Step %d: Comparing %d and %d\n",
                    step, list1.val, list2.val);

            if (list1.val <= list2.val) {
                System.out.printf("  Choosing %d from list1\n", list1.val);
                current.next = list1;
                list1 = list1.next;
            } else {
                System.out.printf("  Choosing %d from list2\n", list2.val);
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
            step++;
        }

        // Attach remaining nodes
        if (list1 != null) {
            System.out.println("Attaching remaining nodes from list1");
            current.next = list1;
        } else if (list2 != null) {
            System.out.println("Attaching remaining nodes from list2");
            current.next = list2;
        }

        System.out.println("Merge complete!\n");
        return dummy.next;
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

    // Helper method to get list length for verification
    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    // Test method with all examples
    public static void main(String[] args) {
        MergeTwoSortedListsIterative solution = new MergeTwoSortedListsIterative();

        // Example 1: [1,2,4] + [1,3,4] = [1,1,2,3,4,4]
        System.out.println("Example 1:");
        ListNode list1_1 = createList(new int[]{1, 2, 4});
        ListNode list2_1 = createList(new int[]{1, 3, 4});
        System.out.print("list1 = ");
        printList(list1_1);
        System.out.println();
        System.out.print("list2 = ");
        printList(list2_1);
        System.out.println();

        ListNode result1 = solution.mergeTwoLists(list1_1, list2_1);
        System.out.print("Merged: ");
        printList(result1);
        System.out.println("\n");

        // Example 2: [] + [] = []
        System.out.println("Example 2:");
        ListNode list1_2 = createList(new int[]{});
        ListNode list2_2 = createList(new int[]{});
        System.out.print("list1 = ");
        printList(list1_2);
        System.out.println();
        System.out.print("list2 = ");
        printList(list2_2);
        System.out.println();

        ListNode result2 = solution.mergeTwoLists(list1_2, list2_2);
        System.out.print("Merged: ");
        printList(result2);
        System.out.println("\n");

        // Example 3: [] + [0] = [0]
        System.out.println("Example 3:");
        ListNode list1_3 = createList(new int[]{});
        ListNode list2_3 = createList(new int[]{0});
        System.out.print("list1 = ");
        printList(list1_3);
        System.out.println();
        System.out.print("list2 = ");
        printList(list2_3);
        System.out.println();

        ListNode result3 = solution.mergeTwoLists(list1_3, list2_3);
        System.out.print("Merged: ");
        printList(result3);
        System.out.println("\n");

        // Additional test cases
        System.out.println("Additional Test Cases:");

        // Different lengths
        System.out.println("Different lengths: [1,5] + [2,3,4,6]");
        ListNode list1_4 = createList(new int[]{1, 5});
        ListNode list2_4 = createList(new int[]{2, 3, 4, 6});
        System.out.print("list1 = ");
        printList(list1_4);
        System.out.print(", list2 = ");
        printList(list2_4);
        System.out.print(" -> Merged: ");
        ListNode result4 = solution.mergeTwoLists(list1_4, list2_4);
        printList(result4);
        System.out.println();

        // All elements from one list come first
        System.out.println("Sequential merge: [1,2,3] + [4,5,6]");
        ListNode list1_5 = createList(new int[]{1, 2, 3});
        ListNode list2_5 = createList(new int[]{4, 5, 6});
        System.out.print("list1 = ");
        printList(list1_5);
        System.out.print(", list2 = ");
        printList(list2_5);
        System.out.print(" -> Merged: ");
        ListNode result5 = solution.mergeTwoLists(list1_5, list2_5);
        printList(result5);
        System.out.println();

        // Duplicate values
        System.out.println("With duplicates: [1,1,1] + [1,1,1]");
        ListNode list1_6 = createList(new int[]{1, 1, 1});
        ListNode list2_6 = createList(new int[]{1, 1, 1});
        System.out.print("list1 = ");
        printList(list1_6);
        System.out.print(", list2 = ");
        printList(list2_6);
        System.out.print(" -> Merged: ");
        ListNode result6 = solution.mergeTwoLists(list1_6, list2_6);
        printList(result6);
        System.out.println();

        // Step-by-step demonstration
        System.out.println("\nStep-by-step demonstration:");
        ListNode list1_demo = createList(new int[]{1, 3, 5});
        ListNode list2_demo = createList(new int[]{2, 4, 6});
        System.out.print("list1 = ");
        printList(list1_demo);
        System.out.print(", list2 = ");
        printList(list2_demo);
        System.out.println();
        ListNode resultDemo = solution.mergeTwoListsStepByStep(list1_demo, list2_demo);
        System.out.print("Final result: ");
        printList(resultDemo);
        System.out.println();
    }
}
