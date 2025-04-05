package queues;
/*
Reverse elements of queue
You are given a queue and an integer 'k'. Your task is to reverse the order of the first 'k' elements of the queue.

Input Format:

The first line contains two integers 'n' (the size of the array) and 'k' (the number of elements to be reversed).
The second line contains 'n' space-separated integers representing the elements of the queue.
Output Format:

Return the updated queue.
Sample Input 1:

5 3
1 2 3 4 5
Sample Output 1:

3 2 1 4 5
Explanation:

The first 3 elements of the queue [1, 2, 3, 4, 5] are reversed to become [3, 2, 1, 4, 5].

Constraints:

1 ≤ k ≤ 10^6
1 ≤ n ≤ 10^6
1 ≤ q[i] ≤ 10^6 (elements of the queue)
Note:The function should return the updated queue.
 */
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class ReverseKElementsInQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            queue.add(scanner.nextInt());
        }

        // Reverse first k elements
        queue = reverseKElements(queue, k);

        // Print the result
        printQueue(queue);
    }

    public static Queue<Integer> reverseKElements(Queue<Integer> queue, int k) {
        if (queue.isEmpty() || k <= 0 || k > queue.size()) {
            return queue;
        }

        Stack<Integer> stack = new Stack<>();

        // Push first k elements into stack
        for (int i = 0; i < k; i++) {
            stack.push(queue.poll());
        }

        // Pop from stack and enqueue back to queue (reversing order)
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        // Move remaining (n-k) elements to back of queue
        for (int i = 0; i < queue.size() - k; i++) {
            queue.add(queue.poll());
        }

        return queue;
    }

    public static void printQueue(Queue<Integer> queue) {
        for (int element : queue) {
            System.out.print(element + " ");
        }
    }
}