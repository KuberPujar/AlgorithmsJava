package hashtable.twopointer;
/*
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:


The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Custom Judge:

The inputs to the judge are given as follows (your program is not given these inputs):

intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
listA - The first linked list.
listB - The second linked list.
skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.



Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
Example 2:


Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
Example 3:


Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.


Constraints:

The number of nodes of listA is in the m.
The number of nodes of listB is in the n.
1 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
intersectVal is 0 if listA and listB do not intersect.
intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.


Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 */

import java.util.HashMap;
import java.util.HashSet;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class IntersectionOfTwoLinkedList {
    /**
     * Approach 1: Hash Table + Single Pointer
     * Store all nodes from first list in hash set, then traverse second list
     * Time Complexity: O(m + n)
     * Space Complexity: O(m)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        HashSet<ListNode> visitedNodes = new HashSet<>();

        // Traverse list A and store all nodes
        ListNode pointer = headA;
        while (pointer != null) {
            visitedNodes.add(pointer);
            pointer = pointer.next;
        }

        // Traverse list B and check if any node exists in hash set
        pointer = headB;
        while (pointer != null) {
            if (visitedNodes.contains(pointer)) {
                return pointer; // First intersection found
            }
            pointer = pointer.next;
        }

        return null; // No intersection
    }

    /**
     * Approach 2: Hash Table + Two Pointers (Simultaneous Traversal)
     * Use hash table to track visited nodes while using two pointers
     * Time Complexity: O(m + n)
     * Space Complexity: O(min(m, n))
     */
    public ListNode getIntersectionNodeTwoPointers(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        HashSet<ListNode> visitedFromA = new HashSet<>();
        HashSet<ListNode> visitedFromB = new HashSet<>();

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        // Traverse both lists simultaneously
        while (pointerA != null || pointerB != null) {
            // Check if current node from A was visited by B
            if (pointerA != null) {
                if (visitedFromB.contains(pointerA)) {
                    return pointerA;
                }
                visitedFromA.add(pointerA);
                pointerA = pointerA.next;
            }

            // Check if current node from B was visited by A
            if (pointerB != null) {
                if (visitedFromA.contains(pointerB)) {
                    return pointerB;
                }
                visitedFromB.add(pointerB);
                pointerB = pointerB.next;
            }
        }

        return null;
    }

    /**
     * Approach 3: Hash Map + Length Calculation + Aligned Pointers
     * Calculate lengths using hash map, then align pointers
     * Time Complexity: O(m + n)
     * Space Complexity: O(m + n)
     */
    public ListNode getIntersectionNodeAligned(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // Hash map to store node positions
        HashMap<ListNode, Integer> nodePositions = new HashMap<>();

        // Calculate length of list A and store positions
        ListNode pointer = headA;
        int lengthA = 0;
        while (pointer != null) {
            nodePositions.put(pointer, lengthA);
            lengthA++;
            pointer = pointer.next;
        }

        // Calculate length of list B
        pointer = headB;
        int lengthB = 0;
        while (pointer != null) {
            lengthB++;
            pointer = pointer.next;
        }

        // Align pointers based on length difference
        ListNode pointerA = headA;
        ListNode pointerB = headB;

        int diff = Math.abs(lengthA - lengthB);

        // Move the longer list's pointer ahead
        if (lengthA > lengthB) {
            for (int i = 0; i < diff; i++) {
                pointerA = pointerA.next;
            }
        } else {
            for (int i = 0; i < diff; i++) {
                pointerB = pointerB.next;
            }
        }

        // Now traverse both lists simultaneously
        while (pointerA != null && pointerB != null) {
            if (pointerA == pointerB) {
                return pointerA;
            }
            pointerA = pointerA.next;
            pointerB = pointerB.next;
        }

        return null;
    }

    /**
     * Approach 4: Optimal Two Pointer (O(1) space) - Follow-up solution
     * No hash table, pure pointer manipulation
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */
    public ListNode getIntersectionNodeOptimal(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        // When one pointer reaches end, redirect to other list's head
        // This ensures both pointers travel the same distance: m + n
        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        return pointerA; // Either intersection node or null
    }

    /**
     * Approach 5: Hash Table with Early Termination + Smart Pointers
     * Advanced hash table approach with optimization
     * Time Complexity: O(m + n)
     * Space Complexity: O(min(m, n))
     */
    public ListNode getIntersectionNodeOptimized(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // Use hash set for the shorter list to minimize space
        ListNode shortList, longList;

        // Quick length estimation (first few nodes)
        int countA = 0, countB = 0;
        ListNode tempA = headA, tempB = headB;

        // Count first 10 nodes to estimate which list is shorter
        while ((tempA != null || tempB != null) && Math.max(countA, countB) < 10) {
            if (tempA != null) {
                countA++;
                tempA = tempA.next;
            }
            if (tempB != null) {
                countB++;
                tempB = tempB.next;
            }
        }

        // Choose shorter list for hash set (heuristic)
        if (countA <= countB) {
            shortList = headA;
            longList = headB;
        } else {
            shortList = headB;
            longList = headA;
        }

        // Store shorter list in hash set
        HashSet<ListNode> shortListNodes = new HashSet<>();
        ListNode pointer = shortList;
        while (pointer != null) {
            shortListNodes.add(pointer);
            pointer = pointer.next;
        }

        // Traverse longer list and check for intersection
        pointer = longList;
        while (pointer != null) {
            if (shortListNodes.contains(pointer)) {
                return pointer;
            }
            pointer = pointer.next;
        }

        return null;
    }

    // Helper method to create test linked lists
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

    // Helper method to create intersection
    public static void createIntersection(ListNode headA, ListNode headB, int skipA, int skipB, ListNode intersectionNode) {
        // Navigate to skipA position in list A
        ListNode currentA = headA;
        for (int i = 0; i < skipA - 1; i++) {
            currentA = currentA.next;
        }

        // Navigate to skipB position in list B
        ListNode currentB = headB;
        for (int i = 0; i < skipB - 1; i++) {
            currentB = currentB.next;
        }

        // Create intersection
        currentA.next = intersectionNode;
        currentB.next = intersectionNode;
    }

    // Helper method to print list
    public static void printList(ListNode head, int maxNodes) {
        ListNode current = head;
        int count = 0;
        System.out.print("[");
        while (current != null && count < maxNodes) {
            System.out.print(current.val);
            if (current.next != null && count < maxNodes - 1) System.out.print(",");
            current = current.next;
            count++;
        }
        System.out.print("]");
    }

    // Test cases
    public static void main(String[] args) {
        IntersectionOfTwoLinkedList solution = new IntersectionOfTwoLinkedList();

        System.out.println("=== Linked List Intersection Problem ===\n");

        // Test Case 1: Example 1 from problem
        System.out.println("Test Case 1:");
        ListNode listA1 = createLinkedList(new int[]{4, 1});
        ListNode listB1 = createLinkedList(new int[]{5, 6, 1});
        ListNode intersection1 = createLinkedList(new int[]{8, 4, 5});

        // Create intersection at node with value 8
        listA1.next.next = intersection1;  // Connect after [4,1]
        listB1.next.next.next = intersection1;  // Connect after [5,6,1]

        System.out.print("List A: ");
        printList(listA1, 6);
        System.out.println();
        System.out.print("List B: ");
        printList(listB1, 6);
        System.out.println();

        ListNode result1 = solution.getIntersectionNode(listA1, listB1);
        System.out.println("Hash Table Result: " + (result1 != null ? "Intersected at '" + result1.val + "'" : "No intersection"));

        result1 = solution.getIntersectionNodeOptimal(listA1, listB1);
        System.out.println("Optimal Result: " + (result1 != null ? "Intersected at '" + result1.val + "'" : "No intersection"));
        System.out.println("Expected: Intersected at '8'\n");

        // Test Case 2: No intersection
        System.out.println("Test Case 2:");
        ListNode listA2 = createLinkedList(new int[]{2, 6, 4});
        ListNode listB2 = createLinkedList(new int[]{1, 5});

        System.out.print("List A: ");
        printList(listA2, 6);
        System.out.println();
        System.out.print("List B: ");
        printList(listB2, 6);
        System.out.println();

        ListNode result2 = solution.getIntersectionNodeTwoPointers(listA2, listB2);
        System.out.println("Two Pointers + Hash Result: " + (result2 != null ? "Intersected at '" + result2.val + "'" : "No intersection"));

        result2 = solution.getIntersectionNodeOptimal(listA2, listB2);
        System.out.println("Optimal Result: " + (result2 != null ? "Intersected at '" + result2.val + "'" : "No intersection"));
        System.out.println("Expected: No intersection\n");

        // Test Case 3: Single node intersection
        System.out.println("Test Case 3:");
        ListNode listA3 = createLinkedList(new int[]{1, 9, 1});
        ListNode listB3 = createLinkedList(new int[]{3});
        ListNode intersection3 = createLinkedList(new int[]{2, 4});

        listA3.next.next.next = intersection3;  // Connect after [1,9,1]
        listB3.next = intersection3;  // Connect after [3]

        System.out.print("List A: ");
        printList(listA3, 6);
        System.out.println();
        System.out.print("List B: ");
        printList(listB3, 6);
        System.out.println();

        ListNode result3 = solution.getIntersectionNodeOptimized(listA3, listB3);
        System.out.println("Optimized Hash Result: " + (result3 != null ? "Intersected at '" + result3.val + "'" : "No intersection"));
        System.out.println("Expected: Intersected at '2'\n");

        // Performance comparison
        System.out.println("=== Algorithm Comparison ===");
        System.out.println("1. Hash Table + Single Pointer: O(m+n) time, O(m) space");
        System.out.println("2. Hash Table + Two Pointers: O(m+n) time, O(min(m,n)) space");
        System.out.println("3. Hash Table + Aligned Pointers: O(m+n) time, O(m+n) space");
        System.out.println("4. Optimal Two Pointer: O(m+n) time, O(1) space ⭐");
        System.out.println("5. Optimized Hash Table: O(m+n) time, O(min(m,n)) space");
        System.out.println("\nBest for follow-up requirement: Optimal Two Pointer (O(1) space)");
        System.out.println("Best hash table approach: Optimized Hash Table (minimal space usage)");
    }
}
