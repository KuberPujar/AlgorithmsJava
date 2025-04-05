package queues;
/*
Queue Implementation
You are given an array of 'n' elements. Implement these elements in a queue and return that queue. In Python, you can use from collections import deque.

Input Format:

The first line contains an integer 'n' (1 ≤ n ≤ 10^5), the number of elements in the array.
The second line contains 'n' space-separated integers (1 ≤ Number to be pushed ≤ 10^5).
Output Format:

Return the queue with the elements in the same order as they appear in the input array.
Sample Input 1:

5
1 3 6 9 8
Sample Output 1:

1 3 6 9 8
Explanation:

The elements are enqueued in the same order as they appear in the input array.

Constraints:

1 ≤ n ≤ 10^5
1 ≤ Number to be pushed ≤ 10^5
Note: The function should return the queue.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class QueueImplementation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        //int[] elements = new int[n];
        Vector<Integer> elements=new Vector<>(n);
        for (int i = 0; i < n; i++) {
            elements.add(scanner.nextInt());
        }

        // Create and populate the queue
        Queue<Integer> queue = createQueue(elements);

        // Print the queue elements
        printQueue(queue);
    }

    public static Queue<Integer> createQueue(Vector<Integer> elements) {
        return new LinkedList<>(elements);
    }

    public static void printQueue(Queue<Integer> queue) {
        while (!queue.isEmpty()) {
            System.out.print(queue.poll());
            if (!queue.isEmpty()) {
                System.out.print(" ");
            }
        }
    }
}
