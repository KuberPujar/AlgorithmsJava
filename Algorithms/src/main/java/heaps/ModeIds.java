package heaps;

import java.util.*;

/*
Mode ID's
Given two integer arrays, arr and freq, each of length n, you are tasked with tracking the most frequent IDs in a dynamic collection that changes over time. Here’s how the arrays work:

arr[i] represents an ID at step
freq[i] indicates the change in the count of arr[i] at step i.
For each step i:

If freq[i] is positive, it means freq[i] instances of arr[i] are added to the collection.
If freq[i] is negative, it means -freq[i] instances of arr[i] are removed from the collection.
Your goal is to construct an array ans of length n where each element ans[i] contains the count of the most frequent ID in the collection after step i. If the collection is empty at any step, ans[i] should be 0 for that step.

Example1:
Input:

 arr = [5,5,3], freq = [2,-2,1]
Output:

[2,0,1]
Explanation:

After step 0, we have 2 IDs with the value of 5. So ans[0] = 2.
After step 1, there are no IDs. So ans[1] = 0.
After step 2, we have 1 ID with the value of 3. So ans[2] = 1.
Example2:
Input:

 arr = [2,3,2,1], freq = [3,2,-3,1]
Output:

[3,3,2,2]
Explanation:

After step 0, we have 3 IDs with the value of 2. So ans[0] = 3.
After step 1, we have 3 IDs with value of 2 and 2 IDs with the value of 3. So ans[1] = mode of IDs = 3.
After step 2, we have 2 ID with the value of 3. So ans[2] = 2.
After step 3, we have 2 IDs with value of 2 and 1 ID with the value of 1. So ans[3] = mode of IDs = 2.
Constraints:

1 <= arr.length == freq.length <= 105
1 <= arr[i] <= 105
-105 <= freq[i] <= 105
freq[i] != 0
The input is generated such that the occurrences of an ID will not be negative in any step.
The function should return the result.
 */
public class ModeIds {
    public static void main(String[] args){
        // Example usage
        int[] arr = {5, 5, 3};
        int[] freq = {2, -2, 1};
        List<Long> result = getModeIds(arr, freq);
        for (long res : result) {
            System.out.print(res + " ");
        }

    }

    public static List<Long> getModeIds(int[] arr, int[] freq) {
        int n = arr.length;
        List<Long> ans = new ArrayList<>();
        HashMap<Integer, Integer> counts = new HashMap<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < n; i++) {
            int id = arr[i];
            int delta = freq[i];
            int oldCount = counts.getOrDefault(id, 0);
            int newCount = oldCount + delta;

            if (newCount > 0) {
                counts.put(id, newCount);
                maxHeap.offer(new int[]{newCount, id});
            } else {
                counts.remove(id);
            }

            // Remove invalid entries from the top of the heap
            while (!maxHeap.isEmpty()) {
                int[] current = maxHeap.peek();
                int currentCount = current[0];
                int currentId = current[1];
                if (counts.getOrDefault(currentId, 0) != currentCount) {
                    maxHeap.poll();
                } else {
                    break;
                }
            }

            ans.add(Long.parseLong(String.valueOf(maxHeap.isEmpty() ? 0 : maxHeap.peek()[0])));
        }

        return ans;
    }
}
