package heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Lowest Value Challenge
You are given a 0-indexed integer array arr of even length, and there is also an initially empty array ans. Alice and Bob are playing a game with the following rules:

In each round, Alice and Bob each make one move.
First, Alice removes the minimum element from arr.
Then, Bob removes the minimum element from the remaining arr.
Next, Bob appends the element he removed to ans.
After that, Alice appends the element she removed to ans.
The game continues in this manner until arr is empty. Return the resulting array ans after the game ends.

Example 1:

nums = [6,1,9,2,8,3]
Output:

[2,1,6,3,9,8]
Explanation:

- In the first round, Alice removes 1 and then Bob removes 2. Bob appends 2 to ans, followed by Alice appending 1. So, ans = [2,1].
- At the beginning of the second round, arr = [6,9,8,3]. Alice removes 3 and then Bob removes 6. Bob appends 6 to ans, followed by Alice appending 3. So, ans = [2,1,6,3].
- In the final round, arr = [9,8]. Alice removes 8 and then Bob removes 9. Bob appends 9 to ans, followed by Alice appending 8. So, ans = [2,1,6,3,9,8].

Thus, the resulting array ans is [2,1,6,3,9,8].
Example 2:

arr = [8, 6, 2, 4]
Output:

[4,2,8,6]
Explanation:

- Round 1, Alice removes 2 and then Bob removes 4. Bob appends 4 to ans followed by Alice appending 2. So, ans = [4,2].
- Round 2, Alice removes 6 and then Bob removes 8. Bob appends 8 to ans followed by Alice appending 6. So, ans = [4,2,8,6].
Thus, the resulting array ans is [4,2,8,6].
Constraints:

2 <= arr.length <= 100
1 <= arr[i] <= 100
arr.length % 2 == 0
The function should return the result.
 */
public class LowestValueChallenge {
    public static int[] getResultArray(int[] arr) {
        // Create a min-heap using PriorityQueue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all elements from arr to the min-heap
        for (int num : arr) {
            minHeap.add(num);
        }

        int[] ans = new int[arr.length];
        int ansIndex = 0;

        // Simulate the game until the heap is empty
        while (!minHeap.isEmpty()) {
            // Alice removes the minimum element
            int aliceRemoved = minHeap.poll();

            // Bob removes the minimum element
            int bobRemoved = minHeap.poll();

            // Bob appends his removed element to ans
            ans[ansIndex++] = bobRemoved;
            // Alice appends her removed element to ans
            ans[ansIndex++] = aliceRemoved;
        }

        return ans;
    }

    public static void main(String[] args) {
        // Example usage
        int[] arr1 = {6, 1, 9, 2, 8, 3};
        int[] result1 = getResultArray(arr1);
        System.out.println(Arrays.toString(result1)); // Output: [2, 1, 6, 3, 9, 8]

        int[] arr2 = {8, 6, 2, 4};
        int[] result2 = getResultArray(arr2);
        System.out.println(Arrays.toString(result2)); // Output: [4, 2, 8, 6]
    }
}
