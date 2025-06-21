package linkedlist.removing;

import linkedlist.ListNode;

/*
There is a singly-linked list head and we want to delete a node node in it.

You are given the node to be deleted node. You will not be given access to the first node of head.

All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.

Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:

The value of the given node should not exist in the linked list.
The number of nodes in the linked list should decrease by one.
All the values before node should be in the same order.
All the values after node should be in the same order.
Custom testing:

For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
We will build the linked list and pass the node to your function.
The output will be the entire list after calling your function.


Example 1:


Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
Example 2:


Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.


Constraints:

The number of the nodes in the given list is in the range [2, 1000].
-1000 <= Node.val <= 1000
The value of each node in the list is unique.
The node to be deleted is in the list and is not a tail node.
 */
public class DeleteNodeInLinkedList {
    /**
     * Deletes the given node from the linked list
     * Key insight: Since we can't access the previous node, we copy the next node's
     * value to current node and delete the next node instead
     *
     * @param node - the node to be deleted (guaranteed not to be the last node)
     */
    public void deleteNode(ListNode node) {
        // Copy the value from the next node to the current node
        node.val = node.next.val;

        // Skip the next node (effectively deleting it)
        node.next = node.next.next;
    }

    /**
     * Alternative implementation with more explicit steps for clarity
     */
    public void deleteNodeVerbose(ListNode node) {
        // Step 1: Get reference to the next node
        ListNode nextNode = node.next;

        // Step 2: Copy the value from next node to current node
        node.val = nextNode.val;

        // Step 3: Copy the next pointer from next node to current node
        node.next = nextNode.next;

        // Step 4: The nextNode is now effectively deleted
        // (In languages like C++, you would free the memory here)
    }

    // Helper method to create a linked list from array (for testing)
    public static ListNode createLinkedList(int[] arr) {
        if (arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    // Helper method to find a node with specific value (for testing)
    public static ListNode findNode(ListNode head, int val) {
        while (head != null) {
            if (head.val == val) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    // Helper method to print a linked list (for testing)
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(",");
            }
            head = head.next;
        }
        System.out.println("]");
    }

    // Helper method to create a copy of the list (for testing multiple approaches)
    public static ListNode copyList(ListNode head) {
        if (head == null) return null;

        ListNode newHead = new ListNode(head.val);
        ListNode current = newHead;
        head = head.next;

        while (head != null) {
            current.next = new ListNode(head.val);
            current = current.next;
            head = head.next;
        }

        return newHead;
    }

    // Test method
    public static void main(String[] args) {
        DeleteNodeInLinkedList solution = new DeleteNodeInLinkedList();

        // Test Example 1: head = [4,5,1,9], node = 5
        System.out.println("Example 1:");
        ListNode head1 = createLinkedList(new int[]{4, 5, 1, 9});
        System.out.print("Original list: ");
        printList(copyList(head1));

        ListNode nodeToDelete1 = findNode(head1, 5);
        System.out.println("Deleting node with value: " + nodeToDelete1.val);
        solution.deleteNode(nodeToDelete1);

        System.out.print("After deletion: ");
        printList(head1);

        // Test Example 2: head = [4,5,1,9], node = 1
        System.out.println("\nExample 2:");
        ListNode head2 = createLinkedList(new int[]{4, 5, 1, 9});
        System.out.print("Original list: ");
        printList(copyList(head2));

        ListNode nodeToDelete2 = findNode(head2, 1);
        System.out.println("Deleting node with value: " + nodeToDelete2.val);
        solution.deleteNode(nodeToDelete2);

        System.out.print("After deletion: ");
        printList(head2);

        // Test Edge Case: Delete first node
        System.out.println("\nEdge Case - Delete First Node:");
        ListNode head3 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original list: ");
        printList(copyList(head3));

        ListNode nodeToDelete3 = findNode(head3, 1); // First node
        System.out.println("Deleting node with value: " + nodeToDelete3.val);
        solution.deleteNode(nodeToDelete3);

        System.out.print("After deletion: ");
        printList(head3);

        // Test Edge Case: Delete middle node
        System.out.println("\nEdge Case - Delete Middle Node:");
        ListNode head4 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original list: ");
        printList(copyList(head4));

        ListNode nodeToDelete4 = findNode(head4, 3); // Middle node
        System.out.println("Deleting node with value: " + nodeToDelete4.val);
        solution.deleteNode(nodeToDelete4);

        System.out.print("After deletion: ");
        printList(head4);

        // Test Edge Case: Delete second-to-last node
        System.out.println("\nEdge Case - Delete Second-to-Last Node:");
        ListNode head5 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original list: ");
        printList(copyList(head5));

        ListNode nodeToDelete5 = findNode(head5, 4); // Second-to-last node
        System.out.println("Deleting node with value: " + nodeToDelete5.val);
        solution.deleteNode(nodeToDelete5);

        System.out.print("After deletion: ");
        printList(head5);

        // Test with verbose method
        System.out.println("\n--- Testing Verbose Method ---");
        ListNode head6 = createLinkedList(new int[]{10, 20, 30, 40});
        System.out.print("Original list: ");
        printList(copyList(head6));

        ListNode nodeToDelete6 = findNode(head6, 20);
        System.out.println("Deleting node with value: " + nodeToDelete6.val);
        solution.deleteNodeVerbose(nodeToDelete6);

        System.out.print("After deletion: ");
        printList(head6);

        // Demonstrate the algorithm step by step
        System.out.println("\n--- Algorithm Step-by-Step Demonstration ---");
        ListNode demo = createLinkedList(new int[]{1, 2, 3, 4});
        ListNode nodeToDelete = findNode(demo, 2);

        System.out.print("Before: ");
        printList(copyList(demo));
        System.out.println("Node to delete: " + nodeToDelete.val);
        System.out.println("Next node value: " + nodeToDelete.next.val);

        System.out.println("\nStep 1: Copy next node's value to current node");
        System.out.println("node.val = node.next.val  // " + nodeToDelete.val + " = " + nodeToDelete.next.val);

        System.out.println("\nStep 2: Skip the next node");
        System.out.println("node.next = node.next.next");

        solution.deleteNode(nodeToDelete);
        System.out.print("\nAfter: ");
        printList(demo);
    }
}
