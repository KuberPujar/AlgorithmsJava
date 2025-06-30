package queues.simulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
You are given an integer array deck. There is a deck of cards where every card has a unique integer. The integer on the ith card is deck[i].

You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.

You will do the following steps repeatedly until all cards are revealed:

Take the top card of the deck, reveal it, and take it out of the deck.
If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.
If there are still unrevealed cards, go back to step 1. Otherwise, stop.
Return an ordering of the deck that would reveal the cards in increasing order.

Note that the first entry in the answer is considered to be the top of the deck.



Example 1:

Input: deck = [17,13,11,2,3,5,7]
Output: [2,13,3,11,5,17,7]
Explanation:
We get the deck in the order [17,13,11,2,3,5,7] (this order does not matter), and reorder it.
After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
We reveal 13, and move 17 to the bottom.  The deck is now [17].
We reveal 17.
Since all the cards revealed are in increasing order, the answer is correct.
Example 2:

Input: deck = [1,1000]
Output: [1,1000]


Constraints:

1 <= deck.length <= 1000
1 <= deck[i] <= 106
All the values of deck are unique.
 */
public class RivealCardsInIncreasingOrder {
    /**
     * This method takes an array of unique integers representing a deck of cards
     * and returns an ordering of the deck that would reveal the cards in increasing order.
     *
     * The logic works by simulating the revealing process in reverse.
     * 1. We know the final revealed sequence should be the cards sorted in increasing order.
     * 2. We sort the input deck to get this target sequence.
     * 3. We use a queue to keep track of the *indices* in our result array where the cards should be placed.
     * 4. We iterate through the sorted cards:
     * a. We take the next available index from the front of the queue. This is where the current smallest card goes.
     * b. If the queue is not empty, we simulate the "move the next card to the bottom" step. In our reverse simulation, this means taking the index that *would* have been filled next and moving it to the back of the queue.
     * 5. This process correctly places the cards in the result array to achieve the desired reveal order.
     *
     * @param deck An array of unique integers representing the cards.
     * @return An array representing the ordered deck.
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        // The number of cards in the deck.
        int n = deck.length;

        // Sort the deck to know which card to place next (always the smallest available).
        Arrays.sort(deck);

        // A queue to simulate the positions in the deck. It will hold the indices
        // of the result array `res`.
        Queue<Integer> indexQueue = new LinkedList<>();

        // Initialize the queue with indices 0, 1, 2, ..., n-1.
        for (int i = 0; i < n; i++) {
            indexQueue.add(i);
        }

        // The result array that will hold the final ordered deck.
        int[] result = new int[n];

        // Iterate through the sorted cards and place them in the result array
        // at the positions determined by the queue simulation.
        for (int card : deck) {
            // 1. Reveal: Get the index for the current card from the front of the queue.
            int indexToPlace = indexQueue.poll();
            result[indexToPlace] = card;

            // 2. Move next card to bottom: If there are still indices in the queue,
            // move the next one to the back.
            if (!indexQueue.isEmpty()) {
                int nextIndexToMove = indexQueue.poll();
                indexQueue.add(nextIndexToMove);
            }
        }

        return result;
    }

    /**
     * Main method to test the deckRevealedIncreasing function.
     */
    public static void main(String[] args) {
        RivealCardsInIncreasingOrder solution = new RivealCardsInIncreasingOrder();

        // Example 1
        int[] deck1 = {17, 13, 11, 2, 3, 5, 7};
        System.out.println("Input deck 1: " + Arrays.toString(deck1));
        int[] result1 = solution.deckRevealedIncreasing(deck1);
        // Expected output: [2, 13, 3, 11, 5, 17, 7]
        System.out.println("Ordered deck 1: " + Arrays.toString(result1));
        System.out.println("--------------------");


        // Example 2
        int[] deck2 = {1, 1000};
        System.out.println("Input deck 2: " + Arrays.toString(deck2));
        int[] result2 = solution.deckRevealedIncreasing(deck2);
        // Expected output: [1, 1000]
        System.out.println("Ordered deck 2: " + Arrays.toString(result2));
        System.out.println("--------------------");

        // Example 3
        int[] deck3 = {1,2,3,4,5};
        System.out.println("Input deck 3: " + Arrays.toString(deck3));
        int[] result3 = solution.deckRevealedIncreasing(deck3);
        // Expected output: [1, 5, 2, 4, 3]
        System.out.println("Ordered deck 3: " + Arrays.toString(result3));
    }
}
