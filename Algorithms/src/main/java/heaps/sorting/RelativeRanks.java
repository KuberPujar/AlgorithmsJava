package heaps.sorting;

import java.util.*;

/*
You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.

The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:

The 1st place athlete's rank is "Gold Medal".
The 2nd place athlete's rank is "Silver Medal".
The 3rd place athlete's rank is "Bronze Medal".
For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
Return an array answer of size n where answer[i] is the rank of the ith athlete.



Example 1:

Input: score = [5,4,3,2,1]
Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
Example 2:

Input: score = [10,3,8,9,4]
Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].



Constraints:

n == score.length
1 <= n <= 104
0 <= score[i] <= 106
All the values in score are unique.
 */
public class RelativeRanks {

    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];

        // Create array of indices and sort by score in descending order
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices based on scores in descending order
        Arrays.sort(indices, (a, b) -> Integer.compare(score[b], score[a]));

        // Assign ranks based on sorted order
        for (int i = 0; i < n; i++) {
            int athleteIndex = indices[i];
            int rank = i + 1; // 1-based ranking

            if (rank == 1) {
                result[athleteIndex] = "Gold Medal";
            } else if (rank == 2) {
                result[athleteIndex] = "Silver Medal";
            } else if (rank == 3) {
                result[athleteIndex] = "Bronze Medal";
            } else {
                result[athleteIndex] = String.valueOf(rank);
            }
        }

        return result;
    }

    // Alternative approach using a custom class for better readability
    public String[] findRelativeRanksWithClass(int[] score) {
        int n = score.length;
        String[] result = new String[n];

        // Create list of athlete objects
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            athletes.add(new Athlete(i, score[i]));
        }

        // Sort by score in descending order
        Collections.sort(athletes, (a, b) -> Integer.compare(b.score, a.score));

        // Assign ranks
        for (int i = 0; i < n; i++) {
            Athlete athlete = athletes.get(i);
            int rank = i + 1;

            String rankString = getRankString(rank);
            result[athlete.index] = rankString;
        }

        return result;
    }

    // Alternative approach using HashMap for score-to-rank mapping
    public String[] findRelativeRanksWithMap(int[] score) {
        int n = score.length;
        String[] result = new String[n];

        // Create a copy of scores and sort in descending order
        Integer[] sortedScores = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedScores[i] = score[i];
        }
        Arrays.sort(sortedScores, Collections.reverseOrder());

        // Create mapping from score to rank
        Map<Integer, String> scoreToRank = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int rank = i + 1;
            scoreToRank.put(sortedScores[i], getRankString(rank));
        }

        // Fill result array using the mapping
        for (int i = 0; i < n; i++) {
            result[i] = scoreToRank.get(score[i]);
        }

        return result;
    }

    // Helper method to get rank string
    private String getRankString(int rank) {
        switch (rank) {
            case 1: return "Gold Medal";
            case 2: return "Silver Medal";
            case 3: return "Bronze Medal";
            default: return String.valueOf(rank);
        }
    }

    // Helper class for the alternative approach
    static class Athlete {
        int index;
        int score;

        public Athlete(int index, int score) {
            this.index = index;
            this.score = score;
        }
    }

    // Test method
    public static void main(String[] args) {
        RelativeRanks sol = new RelativeRanks();

        // Example 1: score = [5,4,3,2,1]
        int[] score1 = {5, 4, 3, 2, 1};
        System.out.println("Example 1:");
        System.out.println("Input: " + Arrays.toString(score1));
        System.out.println("Output: " + Arrays.toString(sol.findRelativeRanks(score1)));
        System.out.println("Expected: [Gold Medal, Silver Medal, Bronze Medal, 4, 5]");
        System.out.println("With Class: " + Arrays.toString(sol.findRelativeRanksWithClass(score1)));
        System.out.println("With Map: " + Arrays.toString(sol.findRelativeRanksWithMap(score1)));
        System.out.println();

        // Example 2: score = [10,3,8,9,4]
        int[] score2 = {10, 3, 8, 9, 4};
        System.out.println("Example 2:");
        System.out.println("Input: " + Arrays.toString(score2));
        System.out.println("Output: " + Arrays.toString(sol.findRelativeRanks(score2)));
        System.out.println("Expected: [Gold Medal, 5, Bronze Medal, Silver Medal, 4]");
        System.out.println("With Class: " + Arrays.toString(sol.findRelativeRanksWithClass(score2)));
        System.out.println("With Map: " + Arrays.toString(sol.findRelativeRanksWithMap(score2)));
        System.out.println();

        // Additional test cases
        System.out.println("Additional test cases:");

        // Single athlete
        int[] score3 = {100};
        System.out.println("Single athlete: " + Arrays.toString(sol.findRelativeRanks(score3)));

        // Two athletes
        int[] score4 = {100, 50};
        System.out.println("Two athletes: " + Arrays.toString(sol.findRelativeRanks(score4)));

        // Edge case with large numbers
        int[] score5 = {1000000, 999999, 999998, 999997};
        System.out.println("Large numbers: " + Arrays.toString(sol.findRelativeRanks(score5)));
    }
}
