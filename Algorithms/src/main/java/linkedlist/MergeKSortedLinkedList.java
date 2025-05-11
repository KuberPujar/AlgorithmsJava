package linkedlist;

import java.util.PriorityQueue;

/*
Merge k sorted linked list
You are given an integer k, representing the number of sorted linked lists.
Each linked list is represented by two lines:

The first line contains an integer n representing the number of elements
in the linked list. The second line contains n space-separated integers
 representing the sorted elements of the linked list. Your task is
  to merge all the k sorted linked lists into one sorted linked list
   and return its head.

Input Format:

The first line contains the integer k, the number of sorted linked lists.
The next 2 * k lines describe the linked lists.
Each linked list starts with an integer n, which is the number of
elements in that list.
Followed by n space-separated integers representing the sorted elements of the list.
Output Format:

Return the head of the merged sorted linked list.
Example 1:
Input:

2
3
1 4 5
3
3 4 6
Output:

[1,1,2,3,4,4,5,6]
Explanation
We have 2 sorted linked lists:
Linked list 1: 1 -> 4 -> 5
Linked list 2: 3 -> 4 -> 6
After merging the two sorted lists: 1 -> 3 -> 4 -> 4 -> 5 -> 6
Example 2:
Input:

0
Output:

[]
Explanation
Since there are no linked lists, the result is an empty list.
Constraints
0 ≤ k ≤ 10^3
0≤n≤50
Each element in the linked lists is in the range [-10^4, 10^4].
Note:
The merged linked list should be sorted in ascending order.

Note:The function should return the result. The driver code will handle printing the output.
*/
public class MergeKSortedLinkedList {
    public static void main(String[] args) {
        // Example usage
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(3);
        list2.next = new ListNode(4);
        list2.next.next = new ListNode(6);

        ListNode mergedList = mergeKLists(new ListNode[]{list1, list2});
        printList(mergedList);
    }

    private static class ListNode{
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the first node of each list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }

    // Helper method to create a linked list from an array
    private static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    // Helper method to print the linked list
    private static void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}
