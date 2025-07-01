package hashtable.binarysearch;

import java.util.Arrays;
import java.util.HashMap;

/*
You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.

Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.



Example 1:


Input: flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
Output: [1,2,2,2]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.
Example 2:


Input: flowers = [[1,10],[3,3]], people = [3,3,2]
Output: [2,2,1]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.


Constraints:

1 <= flowers.length <= 5 * 104
flowers[i].length == 2
1 <= starti <= endi <= 109
1 <= people.length <= 5 * 104
1 <= people[i] <= 109
 */
public class NumberOfFlowersInFullBloom {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        // Create separate arrays for start and end times
        int[] starts = new int[flowers.length];
        int[] ends = new int[flowers.length];

        for (int i = 0; i < flowers.length; i++) {
            starts[i] = flowers[i][0];
            ends[i] = flowers[i][1];
        }

        // Sort the start and end times
        Arrays.sort(starts);
        Arrays.sort(ends);

        // Prepare the result array
        int[] result = new int[people.length];

        // For each person's time, calculate the number of flowers in bloom
        for (int i = 0; i < people.length; i++) {
            int time = people[i];
            // Number of flowers that have started blooming by this time
            int started = binarySearchUpperBound(starts, time);
            // Number of flowers that have already ended blooming by this time
            int ended = binarySearchLowerBound(ends, time);
            // The difference gives the number of currently blooming flowers
            result[i] = started - ended;
        }

        return result;
    }

    // Helper method to find the first index where element > target (number of elements <= target)
    private int binarySearchUpperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // Helper method to find the first index where element >= target (number of elements < target)
    private int binarySearchLowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // Alternative solution with hash table for memoization (optimization for repeated queries)
    public int[] fullBloomFlowersWithMemo(int[][] flowers, int[] people) {
        // Create separate arrays for start and end times
        int[] starts = new int[flowers.length];
        int[] ends = new int[flowers.length];

        for (int i = 0; i < flowers.length; i++) {
            starts[i] = flowers[i][0];
            ends[i] = flowers[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int[] result = new int[people.length];
        HashMap<Integer, Integer> memo = new HashMap<>();

        for (int i = 0; i < people.length; i++) {
            int time = people[i];

            // Check memoization first
            if (memo.containsKey(time)) {
                result[i] = memo.get(time);
                continue;
            }

            int started = binarySearchUpperBound(starts, time);
            int ended = binarySearchLowerBound(ends, time);
            int count = started - ended;

            result[i] = count;
            memo.put(time, count);
        }

        return result;
    }

    public static void main(String[] args) {
        NumberOfFlowersInFullBloom solution = new NumberOfFlowersInFullBloom();

        // Example 1
        int[][] flowers1 = {{1,6},{3,7},{9,12},{4,13}};
        int[] people1 = {2,3,7,11};
        System.out.println(Arrays.toString(solution.fullBloomFlowers(flowers1, people1))); // [1,2,2,2]

        // Example 2
        int[][] flowers2 = {{1,10},{3,3}};
        int[] people2 = {3,3,2};
        System.out.println(Arrays.toString(solution.fullBloomFlowers(flowers2, people2))); // [2,2,1]
    }
}
