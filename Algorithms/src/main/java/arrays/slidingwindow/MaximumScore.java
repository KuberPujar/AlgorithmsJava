package arrays.slidingwindow;
/*
There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.



Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.
Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.


Constraints:

1 <= cardPoints.length <= 105
1 <= cardPoints[i] <= 104
1 <= k <= cardPoints.length
 */
public class MaximumScore {
    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        System.out.println(maxScore(cardPoints, k));
    }

    private static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;

        // Calculate the sum of the first k cards (left window)
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += cardPoints[i];
        }

        int maxSum = windowSum;

        // Slide the window from left to right
        // We take i cards from the end and (k-i) from the beginning
        for (int i = 1; i <= k; i++) {
            // Remove the rightmost card of the left window
            windowSum -= cardPoints[k - i];
            // Add the rightmost card from the remaining array
            windowSum += cardPoints[n - i];
            // Update the maximum sum
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
