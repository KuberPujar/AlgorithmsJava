package linkedlist.recursion;

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
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy head to simplify edge cases
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Compare and merge while both lists have nodes
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

        // Append remaining nodes from the non-empty list
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        // Return the merged list (skip dummy head)
        return dummy.next;
    }

// Recursive Solution
    public ListNode mergeTwoListsRc(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Choose the smaller head and recursively merge the rest
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRc(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRc(list1, list2.next);
            return list2;
        }
    }

// Alternative Recursive Solution with explicit node creation
    public ListNode mergeTwoListsRcExpNode(ListNode list1, ListNode list2) {
        return mergeHelper(list1, list2);
    }

    private ListNode mergeHelper(ListNode l1, ListNode l2) {
        // Base cases: if one list is empty, return the other
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // Create result node and determine which value to use
        ListNode result;

        if (l1.val <= l2.val) {
            result = new ListNode(l1.val);
            result.next = mergeHelper(l1.next, l2);
        } else {
            result = new ListNode(l2.val);
            result.next = mergeHelper(l1, l2.next);
        }

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

    // Convert array to string for display
    public static String arrayToString(int[] arr) {
        if (arr.length == 0) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    // Test all solutions
    public static void main(String[] args) {
       MergeTwoSortedLists solution = new MergeTwoSortedLists();

        // Test Case 1: [1,2,4] + [1,3,4] = [1,1,2,3,4,4]
        System.out.println("=== Test Case 1 ===");
        int[] arr1 = {1, 2, 4};
        int[] arr2 = {1, 3, 4};

        System.out.println("list1 = " + arrayToString(arr1));
        System.out.println("list2 = " + arrayToString(arr2));

        ListNode list1 = createList(arr1);
        ListNode list2 = createList(arr2);
        ListNode result1 = solution.mergeTwoLists(list1, list2);
        System.out.print("Iterative Result: ");
        printList(result1);

        list1 = createList(arr1); // Recreate lists
        list2 = createList(arr2);
        ListNode result2 = solution.mergeTwoListsRc(list1, list2);
        System.out.print("Recursive Result: ");
        printList(result2);

        list1 = createList(arr1);
        list2 = createList(arr2);
        ListNode result3 = solution.mergeTwoLists(list1, list2);
        System.out.print("Recursive Alt:    ");
        printList(result3);
        System.out.println("Expected: [1,1,2,3,4,4]");
        System.out.println();

        // Test Case 2: [] + [] = []
        System.out.println("=== Test Case 2 ===");
        int[] arr3 = {};
        int[] arr4 = {};

        System.out.println("list1 = " + arrayToString(arr3));
        System.out.println("list2 = " + arrayToString(arr4));

        ListNode list3 = createList(arr3);
        ListNode list4 = createList(arr4);
        ListNode result4 = solution.mergeTwoLists(list3, list4);
        System.out.print("Result: ");
        printList(result4);
        System.out.println("Expected: []");
        System.out.println();

        // Test Case 3: [] + [0] = [0]
        System.out.println("=== Test Case 3 ===");
        int[] arr5 = {};
        int[] arr6 = {0};

        System.out.println("list1 = " + arrayToString(arr5));
        System.out.println("list2 = " + arrayToString(arr6));

        ListNode list5 = createList(arr5);
        ListNode list6 = createList(arr6);
        ListNode result5 = solution.mergeTwoLists(list5, list6);
        System.out.print("Result: ");
        printList(result5);
        System.out.println("Expected: [0]");
        System.out.println();

        // Test Case 4: Different lengths
        System.out.println("=== Test Case 4 ===");
        int[] arr7 = {1, 2, 3, 4, 5};
        int[] arr8 = {1, 1, 1};

        System.out.println("list1 = " + arrayToString(arr7));
        System.out.println("list2 = " + arrayToString(arr8));

        ListNode list7 = createList(arr7);
        ListNode list8 = createList(arr8);
        ListNode result6 = solution.mergeTwoLists(list7, list8);
        System.out.print("Result: ");
        printList(result6);
        System.out.println("Expected: [1,1,1,1,2,3,4,5]");
        System.out.println();

        // Test Case 5: No overlap
        System.out.println("=== Test Case 5 ===");
        int[] arr9 = {1, 2, 3};
        int[] arr10 = {4, 5, 6};

        System.out.println("list1 = " + arrayToString(arr9));
        System.out.println("list2 = " + arrayToString(arr10));

        ListNode list9 = createList(arr9);
        ListNode list10 = createList(arr10);
        ListNode result7 = solution.mergeTwoLists(list9, list10);
        System.out.print("Result: ");
        printList(result7);
        System.out.println("Expected: [1,2,3,4,5,6]");
        System.out.println();

        // Test Case 6: Single nodes
        System.out.println("=== Test Case 6 ===");
        int[] arr11 = {2};
        int[] arr12 = {1};

        System.out.println("list1 = " + arrayToString(arr11));
        System.out.println("list2 = " + arrayToString(arr12));

        ListNode list11 = createList(arr11);
        ListNode list12 = createList(arr12);
        ListNode result8 = solution.mergeTwoLists(list11, list12);
        System.out.print("Result: ");
        printList(result8);
        System.out.println("Expected: [1,2]");
    }
}
