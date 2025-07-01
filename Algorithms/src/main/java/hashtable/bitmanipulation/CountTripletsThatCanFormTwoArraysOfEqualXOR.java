package hashtable.bitmanipulation;

import java.util.HashMap;

/*
Given an array of integers arr.

We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).

Let's define a and b as follows:

a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
Note that ^ denotes the bitwise-xor operation.

Return the number of triplets (i, j and k) Where a == b.



Example 1:

Input: arr = [2,3,1,6,7]
Output: 4
Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
Example 2:

Input: arr = [1,1,1,1,1]
Output: 10


Constraints:

1 <= arr.length <= 300
1 <= arr[i] <= 108
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXOR {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        int result = 0;
        int prefix = 0;

        // HashMaps to count prefix xor occurrences and their indices sum
        HashMap<Integer, Integer> countMap = new HashMap<>();
        HashMap<Integer, Integer> totalMap = new HashMap<>();

        // Initialize with prefix xor 0 at index -1
        countMap.put(0, 1);
        totalMap.put(0, 0);

        for (int i = 0; i < n; i++) {
            prefix ^= arr[i];

            // If prefix xor has been seen before, we can form triplets
            if (countMap.containsKey(prefix)) {
                // For each previous occurrence, all j in (previous index + 1 to i) are valid
                result += countMap.get(prefix) * i - totalMap.get(prefix);
            }

            // Update hash tables for current prefix xor
            countMap.put(prefix, countMap.getOrDefault(prefix, 0) + 1);
            totalMap.put(prefix, totalMap.getOrDefault(prefix, 0) + (i + 1));
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 6, 7};
        System.out.println("Output: " + countTriplets(arr1)); // 4

        int[] arr2 = {1, 1, 1, 1, 1};
        System.out.println("Output: " + countTriplets(arr2)); // 10
    }
}
