package bitmanipulation.recursion;
/*
Alice and Bob are opponents in an archery competition. The competition has set the following rules:

Alice first shoots numArrows arrows and then Bob shoots numArrows arrows.
The points are then calculated as follows:
The target has integer scoring sections ranging from 0 to 11 inclusive.
For each section of the target with score k (in between 0 to 11), say Alice and Bob have shot ak and bk arrows on that section respectively. If ak >= bk, then Alice takes k points. If ak < bk, then Bob takes k points.
However, if ak == bk == 0, then nobody takes k points.
For example, if Alice and Bob both shot 2 arrows on the section with score 11, then Alice takes 11 points. On the other hand, if Alice shot 0 arrows on the section with score 11 and Bob shot 2 arrows on that same section, then Bob takes 11 points.

You are given the integer numArrows and an integer array aliceArrows of size 12, which represents the number of arrows Alice shot on each scoring section from 0 to 11. Now, Bob wants to maximize the total number of points he can obtain.

Return the array bobArrows which represents the number of arrows Bob shot on each scoring section from 0 to 11. The sum of the values in bobArrows should equal numArrows.

If there are multiple ways for Bob to earn the maximum total points, return any one of them.



Example 1:


Input: numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
Output: [0,0,0,0,1,1,0,0,1,2,3,1]
Explanation: The table above shows how the competition is scored.
Bob earns a total point of 4 + 5 + 8 + 9 + 10 + 11 = 47.
It can be shown that Bob cannot obtain a score higher than 47 points.
Example 2:


Input: numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
Output: [0,0,0,0,0,0,0,0,1,1,1,0]
Explanation: The table above shows how the competition is scored.
Bob earns a total point of 8 + 9 + 10 = 27.
It can be shown that Bob cannot obtain a score higher than 27 points.


Constraints:

1 <= numArrows <= 105
aliceArrows.length == bobArrows.length == 12
0 <= aliceArrows[i], bobArrows[i] <= numArrows
sum(aliceArrows[i]) == numArrows
 */
public class MaximumPointsInArcheryCompetition {

    private int maxScore = 0;
    private int[] best = new int[12];

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        dfs(11, numArrows, aliceArrows, new int[12], 0);
        return best;
    }

    private void dfs(int idx, int arrowsLeft, int[] aliceArrows, int[] bobArrows, int score) {
        if (idx < 0) {
            if (score > maxScore) {
                maxScore = score;
                System.arraycopy(bobArrows, 0, best, 0, 12);
                // Assign remaining arrows to section 0 if any left
                if (arrowsLeft > 0) best[0] += arrowsLeft;
            }
            return;
        }
        // Option 1: Bob tries to win this section
        int need = aliceArrows[idx] + 1;
        if (arrowsLeft >= need) {
            bobArrows[idx] = need;
            dfs(idx - 1, arrowsLeft - need, aliceArrows, bobArrows, score + idx);
            bobArrows[idx] = 0; // backtrack
        }
        // Option 2: Bob skips this section
        dfs(idx - 1, arrowsLeft, aliceArrows, bobArrows, score);
    }

    public static void main(String[] args) {
        MaximumPointsInArcheryCompetition competition = new MaximumPointsInArcheryCompetition();
        int numArrows = 9;
        int[] aliceArrows = {1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0};
        int[] bobArrows = competition.maximumBobPoints(numArrows, aliceArrows);

        System.out.print("Bob's arrows: ");
        for (int arrows : bobArrows) {
            System.out.print(arrows + " ");
        }
    }
}
