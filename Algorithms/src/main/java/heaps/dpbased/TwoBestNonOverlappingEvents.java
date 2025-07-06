package heaps.dpbased;

import java.util.Arrays;
import java.util.Comparator;

/*
You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.



Example 1:


Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
Example 2:

Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.
Example 3:


Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.


Constraints:

2 <= events.length <= 105
events[i].length == 3
1 <= startTimei <= endTimei <= 109
1 <= valuei <= 106
 */
public class TwoBestNonOverlappingEvents {
    public int maxTwoEvents(int[][] events) {
        // Sort events by start time
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        int n = events.length;

        // Precompute max values from each position to end
        int[] maxFromHere = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            maxFromHere[i] = Math.max(events[i][2], maxFromHere[i + 1]);
        }

        int maxSum = 0;

        for (int i = 0; i < n; i++) {
            int[] curr = events[i];
            int currVal = curr[2];

            // Find the first event that starts after current ends
            int left = i + 1, right = n;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (events[mid][0] > curr[1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // Add current value with max value from remaining events
            if (left < n) {
                maxSum = Math.max(maxSum, currVal + maxFromHere[left]);
            }

            // Also consider just the current event by itself
            maxSum = Math.max(maxSum, currVal);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        TwoBestNonOverlappingEvents solution = new TwoBestNonOverlappingEvents();
        int[][] events = {{1, 3, 2}, {4, 5, 2}, {2, 4, 3}};
        int result = solution.maxTwoEvents(events);
        System.out.println("Maximum sum of two non-overlapping events: " + result); // Output: 4
    }
}
