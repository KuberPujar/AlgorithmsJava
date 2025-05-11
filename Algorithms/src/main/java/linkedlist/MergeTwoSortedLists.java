package linkedlist;
/*
merge two sorted linked list
You are given the heads of two sorted linked lists, list1 and list2.
Your task is to merge the two lists into one sorted list.
The merged list should be made by splicing together the nodes of list1 and
list2 in non-decreasing order.

Return the head of the merged sorted linked list.

Input Format:

list1: A sorted linked list in non-decreasing order.
list2: A sorted linked list in non-decreasing order.
Output Format:

Return the head of the merged sorted linked list.
Input 1`:

 list1 = [1,2,4], list2 = [1,3,4]
Output 1`:

[1,1,2,3,4,4]
Explanation
Merge the two lists: [1, 2, 4] and [1, 3, 4]
The merged list is: [1, 1, 2, 3, 4, 4]
Input 2:

list1 = [], list2 = [0]
Output 2:

 [0]
Explanation
Since list1 is empty, the result is simply list2.
Constraints
The number of nodes in both lists is between 0 and 50.

The value of each node in both lists is between -100 and 100.

Both list1 and list2 are already sorted in non-decreasing order.

Note:The function should return the result. T
he driver code will handle printing the output.
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        // Example usage
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(4);

        Node list2 = new Node(1);
        list2.next = new Node(3);
        list2.next.next = new Node(4);

        Node mergedList = mergeTwoLists(list1, list2);
        printList(mergedList);
    }

    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static Node mergeTwoLists(Node list1, Node list2) {
        // Create a dummy node to serve as the starting point
        Node dummy = new Node(-1);
        Node current = dummy;

        // Traverse both lists
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

        // Attach the remaining elements of list1 or list2
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
    }

    // Utility method to print the list (for testing)
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Utility method to create a list from array (for testing)
    public static Node createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }
}
