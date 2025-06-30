package queues.simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.

The rules of the game are as follows:

Start at the 1st friend.
Count the next k friends in the clockwise direction including the friend you started at. The counting wraps around the circle and may count some friends more than once.
The last friend you counted leaves the circle and loses the game.
If there is still more than one friend in the circle, go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
Else, the last friend in the circle wins the game.
Given the number of friends, n, and an integer k, return the winner of the game.



Example 1:


Input: n = 5, k = 2
Output: 3
Explanation: Here are the steps of the game:
1) Start at friend 1.
2) Count 2 friends clockwise, which are friends 1 and 2.
3) Friend 2 leaves the circle. Next start is friend 3.
4) Count 2 friends clockwise, which are friends 3 and 4.
5) Friend 4 leaves the circle. Next start is friend 5.
6) Count 2 friends clockwise, which are friends 5 and 1.
7) Friend 1 leaves the circle. Next start is friend 3.
8) Count 2 friends clockwise, which are friends 3 and 5.
9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.
Example 2:

Input: n = 6, k = 5
Output: 1
Explanation: The friends leave in this order: 5, 4, 6, 2, 3. The winner is friend 1.


Constraints:

1 <= k <= n <= 500


Follow up:

Could you solve this problem in linear time with constant space?
 */
public class WinnerOfCircularGame {

        // Queue-based simulation approach
        public int findTheWinner(int n, int k) {
            Queue<Integer> queue = new LinkedList<>();

            // Initialize queue with friends numbered 1 to n
            for (int i = 1; i <= n; i++) {
                queue.offer(i);
            }

            // Simulate the elimination process
            while (queue.size() > 1) {
                // Count k friends (including current), eliminate the k-th one
                for (int i = 0; i < k - 1; i++) {
                    // Move the first k-1 friends to the back
                    queue.offer(queue.poll());
                }

                // Remove the k-th friend (current front of queue)
                int eliminated = queue.poll();
                System.out.println("Friend " + eliminated + " is eliminated. Remaining: " + queue);
            }

            // Return the last remaining friend
            return queue.poll();
        }

        // Optimized mathematical solution (Josephus formula)
        public int findTheWinnerOptimized(int n, int k) {
            // Josephus problem: J(n,k) = (J(n-1,k) + k) % n
            // Base case: J(1,k) = 0 (0-indexed)
            int result = 0;
            for (int i = 2; i <= n; i++) {
                result = (result + k) % i;
            }
            return result + 1; // Convert to 1-indexed
        }

    // Detailed simulation class for better understanding
        public int findTheWinnerWithSteps(int n, int k) {
            Queue<Integer> queue = new LinkedList<>();

            // Initialize queue
            for (int i = 1; i <= n; i++) {
                queue.offer(i);
            }

            int round = 1;
            System.out.println("Initial circle: " + queue);
            System.out.println("Starting from friend 1, counting " + k + " friends each round\n");

            while (queue.size() > 1) {
                System.out.println("Round " + round + ":");
                System.out.println("Current circle: " + queue);
                System.out.print("Starting from friend " + queue.peek() + ", counting " + k + ": ");

                // Show the counting process
                List<Integer> counted = new ArrayList<>();
                Queue<Integer> tempQueue = new LinkedList<>(queue);

                for (int i = 0; i < k; i++) {
                    counted.add(tempQueue.peek());
                    tempQueue.offer(tempQueue.poll());
                }
                System.out.print(counted + " -> ");

                // Actually eliminate
                for (int i = 0; i < k - 1; i++) {
                    queue.offer(queue.poll());
                }

                int eliminated = queue.poll();
                System.out.println("Friend " + eliminated + " eliminated");

                if (!queue.isEmpty()) {
                    System.out.println("Next starting position: Friend " + queue.peek());
                }
                System.out.println();

                round++;
            }

            int winner = queue.poll();
            System.out.println("Winner: Friend " + winner);
            return winner;
        }

        public static void main(String[] args) {
            WinnerOfCircularGame solution = new WinnerOfCircularGame();

            System.out.println("=== JOSEPHUS PROBLEM - FIND THE WINNER ===\n");

            // Example 1: n = 5, k = 2
            System.out.println("EXAMPLE 1: n = 5, k = 2");
            System.out.println("Expected: 3\n");

            System.out.println("--- Detailed Simulation ---");
            int result1 = solution.findTheWinnerWithSteps(5, 2);

            System.out.println("\n--- Queue Solution ---");
            int queueResult1 = solution.findTheWinner(5, 2);
            System.out.println("Queue Solution Result: " + queueResult1);

            System.out.println("\n--- Optimized Mathematical Solution ---");
            int optimizedResult1 = solution.findTheWinnerOptimized(5, 2);
            System.out.println("Optimized Result: " + optimizedResult1);

            System.out.println("\n" + "=".repeat(50) + "\n");

            // Example 2: n = 6, k = 5
            System.out.println("EXAMPLE 2: n = 6, k = 5");
            System.out.println("Expected: 1\n");

            System.out.println("--- Detailed Simulation ---");
            int result2 = solution.findTheWinnerWithSteps(6, 5);

            System.out.println("\n--- Queue Solution ---");
            int queueResult2 = solution.findTheWinner(6, 5);
            System.out.println("Queue Solution Result: " + queueResult2);

            System.out.println("\n--- Optimized Mathematical Solution ---");
            int optimizedResult2 = solution.findTheWinnerOptimized(6, 5);
            System.out.println("Optimized Result: " + optimizedResult2);

            System.out.println("\n" + "=".repeat(50) + "\n");

            // Additional test cases
            System.out.println("ADDITIONAL TEST CASES:\n");

            int[][] testCases = {
                    {1, 1}, // Edge case: only one friend
                    {2, 1}, // Two friends, eliminate every 1st
                    {3, 3}, // Three friends, eliminate every 3rd
                    {7, 3}, // Classic Josephus example
                    {10, 4} // Larger example
            };

            for (int[] testCase : testCases) {
                int n = testCase[0];
                int k = testCase[1];

                System.out.println("Test: n = " + n + ", k = " + k);
                int queueResult = solution.findTheWinner(n, k);
                int optimizedResult = solution.findTheWinnerOptimized(n, k);

                System.out.println("Queue Result: " + queueResult);
                System.out.println("Optimized Result: " + optimizedResult);
                System.out.println("Match: " + (queueResult == optimizedResult ? "✓" : "✗"));
                System.out.println();
            }

            // Performance comparison
            System.out.println("PERFORMANCE COMPARISON:\n");
            performanceTest();
        }

        private static void performanceTest() {
            WinnerOfCircularGame solution = new WinnerOfCircularGame();
            int n = 500;
            int k = 250;

            System.out.println("Testing with n = " + n + ", k = " + k);

            // Queue-based approach
            long startTime = System.nanoTime();
            int queueResult = solution.findTheWinner(n, k);
            long queueTime = System.nanoTime() - startTime;

            // Optimized approach
            startTime = System.nanoTime();
            int optimizedResult = solution.findTheWinnerOptimized(n, k);
            long optimizedTime = System.nanoTime() - startTime;

            System.out.println("Queue Solution: " + queueResult + " (Time: " + queueTime / 1_000_000.0 + " ms)");
            System.out.println("Optimized Solution: " + optimizedResult + " (Time: " + optimizedTime / 1_000_000.0 + " ms)");
            System.out.println("Results match: " + (queueResult == optimizedResult ? "✓" : "✗"));
            System.out.println("Speedup: " + String.format("%.2fx", (double) queueTime / optimizedTime));
        }
}
