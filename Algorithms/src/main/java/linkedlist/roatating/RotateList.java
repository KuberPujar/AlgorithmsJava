package linkedlist.roatating;
/*
Given the head of a linked list, rotate the list to the right by k places.



Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:


Input: head = [0,1,2], k = 4
Output: [2,0,1]


Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
 */
public class RotateList {
    /**
     * Definition for singly-linked list.
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

        /**
         * Rotates a linked list to the right by k places
         * Time Complexity: O(n) where n is the number of nodes
         * Space Complexity: O(1) - only using constant extra space
         */
        public ListNode rotateRight(ListNode head, int k) {
            // Handle edge cases
            if (head == null || head.next == null || k == 0) {
                return head;
            }

            // Step 1: Find the length of the list and get the tail
            int length = 1;
            ListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
                length++;
            }

            // Step 2: Optimize k (handle cases where k > length)
            k = k % length;
            if (k == 0) {
                return head; // No rotation needed
            }

            // Step 3: Find the new tail (length - k - 1 steps from head)
            ListNode newTail = head;
            for (int i = 0; i < length - k - 1; i++) {
                newTail = newTail.next;
            }

            // Step 4: The new head is the next node after new tail
            ListNode newHead = newTail.next;

            // Step 5: Break the connection and form the rotated list
            newTail.next = null;  // Break the original list
            tail.next = head;     // Connect old tail to old head

            return newHead;
        }

        /**
         * Alternative approach: Convert to circular list first
         */
        public ListNode rotateRightCircular(ListNode head, int k) {
            if (head == null || head.next == null || k == 0) {
                return head;
            }

            // Step 1: Make the list circular and find length
            int length = 1;
            ListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
                length++;
            }
            tail.next = head; // Make it circular

            // Step 2: Find the new tail position
            k = k % length;
            int stepsToNewTail = length - k;

            // Step 3: Find new tail and new head
            ListNode newTail = head;
            for (int i = 1; i < stepsToNewTail; i++) {
                newTail = newTail.next;
            }
            ListNode newHead = newTail.next;

            // Step 4: Break the circular connection
            newTail.next = null;

            return newHead;
        }

        /**
         * Approach using two pointers (alternative implementation)
         */
        public ListNode rotateRightTwoPointers(ListNode head, int k) {
            if (head == null || head.next == null || k == 0) {
                return head;
            }

            // First pass: get length
            int length = 0;
            ListNode current = head;
            while (current != null) {
                length++;
                current = current.next;
            }

            // Optimize k
            k = k % length;
            if (k == 0) return head;

            // Use two pointers with k distance apart
            ListNode fast = head;
            ListNode slow = head;

            // Move fast pointer k steps ahead
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }

            // Move both pointers until fast reaches the end
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }

            // Now slow is at the new tail position
            ListNode newHead = slow.next;
            slow.next = null;      // Break the list
            fast.next = head;      // Connect end to original head

            return newHead;
        }

        // Helper method to create a linked list from array (for testing)
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

        // Helper method to print linked list (for testing)
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

        // Helper method to copy a list (since rotation modifies the original)
        public static ListNode copyList(ListNode head) {
            if (head == null) return null;

            ListNode newHead = new ListNode(head.val);
            ListNode current = newHead;
            ListNode original = head.next;

            while (original != null) {
                current.next = new ListNode(original.val);
                current = current.next;
                original = original.next;
            }

            return newHead;
        }

        // Test the solution
        public static void main(String[] args) {
            RotateList solution = new RotateList();

            // Test case 1: [1,2,3,4,5], k = 2 -> [4,5,1,2,3]
            System.out.println("Test case 1:");
            ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
            System.out.print("Input: ");
            printList(head1);
            System.out.println("k = 2");
            ListNode result1 = solution.rotateRight(head1, 2);
            System.out.print("Output: ");
            printList(result1);
            System.out.println();

            // Test case 2: [0,1,2], k = 4 -> [2,0,1]
            System.out.println("Test case 2:");
            ListNode head2 = createList(new int[]{0, 1, 2});
            System.out.print("Input: ");
            printList(head2);
            System.out.println("k = 4");
            ListNode result2 = solution.rotateRight(head2, 4);
            System.out.print("Output: ");
            printList(result2);
            System.out.println();

            // Test case 3: Empty list
            System.out.println("Test case 3:");
            ListNode head3 = createList(new int[]{});
            System.out.print("Input: ");
            printList(head3);
            System.out.println("k = 1");
            ListNode result3 = solution.rotateRight(head3, 1);
            System.out.print("Output: ");
            printList(result3);
            System.out.println();

            // Test case 4: Single element
            System.out.println("Test case 4:");
            ListNode head4 = createList(new int[]{1});
            System.out.print("Input: ");
            printList(head4);
            System.out.println("k = 3");
            ListNode result4 = solution.rotateRight(head4, 3);
            System.out.print("Output: ");
            printList(result4);
            System.out.println();

            // Test case 5: k = 0 (no rotation)
            System.out.println("Test case 5:");
            ListNode head5 = createList(new int[]{1, 2, 3});
            System.out.print("Input: ");
            printList(head5);
            System.out.println("k = 0");
            ListNode result5 = solution.rotateRight(head5, 0);
            System.out.print("Output: ");
            printList(result5);
            System.out.println();

            // Test case 6: k equals length (full rotation)
            System.out.println("Test case 6:");
            ListNode head6 = createList(new int[]{1, 2, 3, 4});
            System.out.print("Input: ");
            printList(head6);
            System.out.println("k = 4 (equals length)");
            ListNode result6 = solution.rotateRight(head6, 4);
            System.out.print("Output: ");
            printList(result6);
            System.out.println();

            // Test case 7: Large k value
            System.out.println("Test case 7:");
            ListNode head7 = createList(new int[]{1, 2, 3});
            System.out.print("Input: ");
            printList(head7);
            System.out.println("k = 2000000000 (very large k)");
            ListNode result7 = solution.rotateRight(head7, 2000000000);
            System.out.print("Output: ");
            printList(result7);
            System.out.println();

            // Test alternative approaches
            System.out.println("Testing circular approach:");
            ListNode head8 = copyList(createList(new int[]{1, 2, 3, 4, 5}));
            System.out.print("Input: ");
            printList(head8);
            System.out.println("k = 2");
            ListNode result8 = solution.rotateRightCircular(head8, 2);
            System.out.print("Output: ");
            printList(result8);
            System.out.println();

            System.out.println("Testing two pointers approach:");
            ListNode head9 = copyList(createList(new int[]{1, 2, 3, 4, 5}));
            System.out.print("Input: ");
            printList(head9);
            System.out.println("k = 2");
            ListNode result9 = solution.rotateRightTwoPointers(head9, 2);
            System.out.print("Output: ");
            printList(result9);

        }
}
