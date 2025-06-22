package linkedlist.hashing;
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

import linkedlist.ListNode;

import java.util.HashSet;

public class IntersectionOfTwoLinkedList {
    /**
     * Finds the node at which the two singly linked lists intersect.
     *
     * @param headA The head of the first linked list.
     * @param headB The head of the second linked list.
     * @return The intersecting node, or null if there is no intersection.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Create a HashSet to store nodes from the first linked list.
        // Using a HashSet allows for O(1) average time complexity for add and contains operations.
        HashSet<ListNode> visitedNodes = new HashSet<>();

        // Traverse the first linked list (headA) and add all its nodes to the HashSet.
        // Each node itself (its memory address) is stored, not just its value,
        // which is crucial for identifying the exact same node reference.
        ListNode currentA = headA;
        while (currentA != null) {
            visitedNodes.add(currentA);
            currentA = currentA.next;
        }

        // Traverse the second linked list (headB).
        // For each node in headB, check if it exists in our HashSet of nodes from headA.
        ListNode currentB = headB;
        while (currentB != null) {
            // If the current node from headB is found in the HashSet,
            // it means this is the first common node (the intersection point).
            if (visitedNodes.contains(currentB)) {
                return currentB; // Return the intersecting node.
            }
            currentB = currentB.next;
        }

        // If we reach this point, it means no common node was found after traversing
        // the entire second linked list.
        return null; // Return null, indicating no intersection.
    }

    // Main method for testing the solution
    public static void main(String[] args) {
        IntersectionOfTwoLinkedList solver = new IntersectionOfTwoLinkedList();

        // --- Example 1: Intersecting at '8' ---
        // List A: 4 -> 1 -> 8 -> 4 -> 5
        // List B: 5 -> 6 -> 1 -> /
        // Intersection starts at node with value 8.

        // Create the common tail
        ListNode commonTail = new ListNode(8);
        commonTail.next = new ListNode(4);
        commonTail.next.next = new ListNode(5);

        // Create List A
        ListNode headA1 = new ListNode(4);
        headA1.next = new ListNode(1);
        headA1.next.next = commonTail; // Point to the common tail

        // Create List B
        ListNode headB1 = new ListNode(5);
        headB1.next = new ListNode(6);
        headB1.next.next = new ListNode(1);
        headB1.next.next.next = commonTail; // Point to the common tail

        System.out.println("--- Example 1 ---");
        ListNode intersection1 = solver.getIntersectionNode(headA1, headB1);
        if (intersection1 != null) {
            System.out.println("Intersected at value: " + intersection1.val); // Expected: 8
        } else {
            System.out.println("No intersection.");
        }
        System.out.println();


        // --- Example 2: Intersecting at '2' ---
        // List A: 1 -> 9 -> 1 -> 2 -> 4
        // List B: 3 -> /
        // Intersection starts at node with value 2.

        // Create the common tail
        ListNode commonTail2 = new ListNode(2);
        commonTail2.next = new ListNode(4);

        // Create List A
        ListNode headA2 = new ListNode(1);
        headA2.next = new ListNode(9);
        headA2.next.next = new ListNode(1);
        headA2.next.next.next = commonTail2; // Point to the common tail

        // Create List B
        ListNode headB2 = new ListNode(3);
        headB2.next = commonTail2; // Point to the common tail

        System.out.println("--- Example 2 ---");
        ListNode intersection2 = solver.getIntersectionNode(headA2, headB2);
        if (intersection2 != null) {
            System.out.println("Intersected at value: " + intersection2.val); // Expected: 2
        } else {
            System.out.println("No intersection.");
        }
        System.out.println();


        // --- Example 3: No intersection ---
        // List A: 2 -> 6 -> 4
        // List B: 1 -> 5

        ListNode headA3 = new ListNode(2);
        headA3.next = new ListNode(6);
        headA3.next.next = new ListNode(4);

        ListNode headB3 = new ListNode(1);
        headB3.next = new ListNode(5);

        System.out.println("--- Example 3 ---");
        ListNode intersection3 = solver.getIntersectionNode(headA3, headB3);
        if (intersection3 != null) {
            System.out.println("Intersected at value: " + intersection3.val);
        } else {
            System.out.println("No intersection."); // Expected: No intersection.
        }
        System.out.println();
    }
}
