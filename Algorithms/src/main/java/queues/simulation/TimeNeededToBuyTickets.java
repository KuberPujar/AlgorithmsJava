package queues.simulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.

You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].

Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.

Return the time taken for the person initially at position k (0-indexed) to finish buying tickets.



Example 1:

Input: tickets = [2,3,2], k = 2

Output: 6

Explanation:

The queue starts as [2,3,2], where the kth person is underlined.
After the person at the front has bought a ticket, the queue becomes [3,2,1] at 1 second.
Continuing this process, the queue becomes [2,1,2] at 2 seconds.
Continuing this process, the queue becomes [1,2,1] at 3 seconds.
Continuing this process, the queue becomes [2,1] at 4 seconds. Note: the person at the front left the queue.
Continuing this process, the queue becomes [1,1] at 5 seconds.
Continuing this process, the queue becomes [1] at 6 seconds. The kth person has bought all their tickets, so return 6.
Example 2:

Input: tickets = [5,1,1,1], k = 0

Output: 8

Explanation:

The queue starts as [5,1,1,1], where the kth person is underlined.
After the person at the front has bought a ticket, the queue becomes [1,1,1,4] at 1 second.
Continuing this process for 3 seconds, the queue becomes [4] at 4 seconds.
Continuing this process for 4 seconds, the queue becomes [] at 8 seconds. The kth person has bought all their tickets, so return 8.


Constraints:

n == tickets.length
1 <= n <= 100
1 <= tickets[i] <= 100
0 <= k < n
 */
public class TimeNeededToBuyTickets {
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<int[]> queue = new LinkedList<>();

        // Initialize queue with [originalIndex, remainingTickets]
        for (int i = 0; i < tickets.length; i++) {
            queue.offer(new int[]{i, tickets[i]});
        }

        int time = 0;

        while (!queue.isEmpty()) {
            time++;
            int[] current = queue.poll();
            int originalIndex = current[0];
            int remainingTickets = current[1];

            // Person buys one ticket
            remainingTickets--;

            // Check if this is the target person and they're done
            if (originalIndex == k && remainingTickets == 0) {
                return time;
            }

            // If person still needs more tickets, go back to end of queue
            if (remainingTickets > 0) {
                queue.offer(new int[]{originalIndex, remainingTickets});
            }
        }

        return time;
    }

    public static void main(String[] args) {
        TimeNeededToBuyTickets solution = new TimeNeededToBuyTickets();

        // Example 1: tickets = [2,3,2], k = 2
        int[] tickets1 = {2, 3, 2};
        int k1 = 2;
        int result1 = solution.timeRequiredToBuy(tickets1, k1);
        System.out.println("Example 1:");
        System.out.println("Input: tickets = " + Arrays.toString(tickets1) + ", k = " + k1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: 6");
        System.out.println();

        // Example 2: tickets = [5,1,1,1], k = 0
        int[] tickets2 = {5, 1, 1, 1};
        int k2 = 0;
        int result2 = solution.timeRequiredToBuy(tickets2, k2);
        System.out.println("Example 2:");
        System.out.println("Input: tickets = " + Arrays.toString(tickets2) + ", k = " + k2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: 8");
        System.out.println();

        // Additional test cases
        int[] tickets3 = {1, 1, 1, 1};
        int k3 = 3;
        int result3 = solution.timeRequiredToBuy(tickets3, k3);
        System.out.println("Additional Test 1:");
        System.out.println("Input: tickets = " + Arrays.toString(tickets3) + ", k = " + k3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: 4");
        System.out.println();

        int[] tickets4 = {1};
        int k4 = 0;
        int result4 = solution.timeRequiredToBuy(tickets4, k4);
        System.out.println("Additional Test 2:");
        System.out.println("Input: tickets = " + Arrays.toString(tickets4) + ", k = " + k4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: 1");
        System.out.println();

        // Detailed simulation for Example 1 to show the process
        System.out.println("Detailed simulation for Example 1:");
        simulateDetailed(tickets1, k1);
    }

    // Method to show detailed simulation of the process
    public static void simulateDetailed(int[] tickets, int k) {
        Queue<int[]> queue = new LinkedList<>();

        // Initialize queue
        for (int i = 0; i < tickets.length; i++) {
            queue.offer(new int[]{i, tickets[i]});
        }

        int time = 0;
        System.out.println("Initial queue state:");
        printQueue(queue, k, time);

        while (!queue.isEmpty()) {
            time++;
            int[] current = queue.poll();
            int originalIndex = current[0];
            int remainingTickets = current[1];

            // Person buys one ticket
            remainingTickets--;

            System.out.println("\nTime " + time + ":");
            System.out.println("Person " + originalIndex + " bought a ticket. Remaining: " + remainingTickets);

            // Check if this is the target person and they're done
            if (originalIndex == k && remainingTickets == 0) {
                System.out.println("Target person (originally at position " + k + ") finished buying tickets!");
                System.out.println("Total time: " + time);
                return;
            }

            // If person still needs more tickets, go back to end of queue
            if (remainingTickets > 0) {
                queue.offer(new int[]{originalIndex, remainingTickets});
            } else {
                System.out.println("Person " + originalIndex + " left the queue.");
            }

            printQueue(queue, k, time);
        }
    }

    private static void printQueue(Queue<int[]> queue, int targetK, int time) {
        System.out.print("Queue state: [");
        boolean first = true;
        for (int[] person : queue) {
            if (!first) System.out.print(", ");
            if (person[0] == targetK) {
                System.out.print("(" + person[1] + ")"); // Parentheses for target person
            } else {
                System.out.print(person[1]);
            }
            first = false;
        }
        System.out.println("]");
    }
}
