package queues.recursion;

import java.util.LinkedList;
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
public class FindWinnerOfCircularGameRC {
    /**
     * Solves the Josephus problem by simulating the game using a Queue.
     * This approach is intuitive and directly follows the game's rules.
     * Time Complexity: O(n * k)
     * Space Complexity: O(n)
     *
     * @param n The number of friends.
     * @param k The counting step.
     * @return The winner of the game.
     */
    public int findTheWinnerWithQueue(int n, int k) {
        // Create a queue and add all friends (numbered 1 to n).
        Queue<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        // Continue until only one friend is left in the queue.
        while (circle.size() > 1) {
            // Simulate counting k-1 friends by moving them from the front to the back.
            for (int i = 0; i < k - 1; i++) {
                int friendToMove = circle.poll();
                circle.add(friendToMove);
            }
            // The k-th friend is now at the front of the queue; remove them.
            circle.poll();
        }

        // The last remaining friend is the winner.
        return circle.poll();
    }

    /**
     * Solves the Josephus problem using a recursive approach with a Queue.
     * This method initializes the queue and calls a recursive helper to perform the simulation.
     * Time Complexity: O(n * k)
     * Space Complexity: O(n) for the queue and O(n) for the recursion stack.
     *
     * @param n The number of friends.
     * @param k The counting step.
     * @return The winner of the game.
     */
    public int findTheWinnerWithQueueRecursive(int n, int k) {
        Queue<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }
        return solveWithQueueRecursive(circle, k);
    }

    /**
     * A private helper method that performs the recursive simulation with a queue.
     *
     * @param circle The queue representing the friends currently in the circle.
     * @param k      The counting step.
     * @return The winner of the game.
     */
    private int solveWithQueueRecursive(Queue<Integer> circle, int k) {
        // Base case: If only one friend is left, they are the winner.
        if (circle.size() == 1) {
            return circle.peek();
        }

        // Recursive step:
        // 1. Simulate the counting by moving k-1 friends to the back of the queue.
        for (int i = 0; i < k - 1; i++) {
            circle.add(circle.poll());
        }

        // 2. The k-th friend is now at the front; remove them.
        circle.poll();

        // 3. Make a recursive call with the smaller circle.
        return solveWithQueueRecursive(circle, k);
    }

    /**
     * Solves the Josephus problem using a purely mathematical recursive approach.
     * The problem has a recursive structure where the solution for n people
     * can be derived from the solution for n-1 people.
     *
     * @param n The number of friends.
     * @param k The counting step.
     * @return The winner of the game.
     */
    public int findTheWinnerRecursive(int n, int k) {
        // The recursive helper works with 0-based indexing, so we add 1 to the final result.
        return solveRecursive(n, k) + 1;
    }

    /**
     * Helper function for the mathematical recursive solution. It uses 0-based indexing.
     * The formula josephus(n, k) = (josephus(n-1, k) + k) % n is used.
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to recursion call stack.
     */
    private int solveRecursive(int n, int k) {
        // Base case: If there's only one person, their index is 0.
        if (n == 1) {
            return 0;
        }
        // Recursive step: Find the winner's position in a circle of n-1 people,
        // then adjust the position for the current circle of n people.
        return (solveRecursive(n - 1, k) + k) % n;
    }

    /**
     * Solves the Josephus problem in linear time with constant space.
     * This is the most efficient solution, converting the recursive formula
     * into an iterative one.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param n The number of friends.
     * @param k The counting step.
     * @return The winner of the game.
     */
    public int findTheWinnerOptimal(int n, int k) {
        // The winner's position in a 0-indexed circle.
        int winnerPosition = 0;

        // Iterate from a circle of 2 people up to n people.
        // In each step, we calculate the winner's position based on the previous step.
        for (int i = 2; i <= n; i++) {
            winnerPosition = (winnerPosition + k) % i;
        }

        // Convert the 0-indexed position to a 1-indexed friend number.
        return winnerPosition + 1;
    }

    /**
     * Main method to test the different solutions.
     */
    public static void main(String[] args) {
       // JosephusProblem game = new JosephusProblem();
FindWinnerOfCircularGameRC game = new FindWinnerOfCircularGameRC();
        // Example 1: n = 5, k = 2
        int n1 = 5, k1 = 2;
        System.out.println("--- Game 1: n = " + n1 + ", k = " + k1 + " ---");
        System.out.println("Winner (Queue): " + game.findTheWinnerWithQueue(n1, k1));
        System.out.println("Winner (Queue + Recursive): " + game.findTheWinnerWithQueueRecursive(n1, k1));
        System.out.println("Winner (Mathematical Recursive): " + game.findTheWinnerRecursive(n1, k1));
        System.out.println("Winner (Optimal): " + game.findTheWinnerOptimal(n1, k1));
        System.out.println();

        // Example 2: n = 6, k = 5
        int n2 = 6, k2 = 5;
        System.out.println("--- Game 2: n = " + n2 + ", k = " + k2 + " ---");
        System.out.println("Winner (Queue): " + game.findTheWinnerWithQueue(n2, k2));
        System.out.println("Winner (Queue + Recursive): " + game.findTheWinnerWithQueueRecursive(n2, k2));
        System.out.println("Winner (Mathematical Recursive): " + game.findTheWinnerRecursive(n2, k2));
        System.out.println("Winner (Optimal): " + game.findTheWinnerOptimal(n2, k2));
    }
}
