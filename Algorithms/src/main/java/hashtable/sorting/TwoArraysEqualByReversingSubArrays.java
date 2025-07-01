package hashtable.sorting;

import java.util.*;

/*
You are given two integer arrays of equal length target and arr. In one step, you can select any non-empty subarray of arr and reverse it. You are allowed to make any number of steps.

Return true if you can make arr equal to target or false otherwise.



Example 1:

Input: target = [1,2,3,4], arr = [2,4,1,3]
Output: true
Explanation: You can follow the next steps to convert arr to target:
1- Reverse subarray [2,4,1], arr becomes [1,4,2,3]
2- Reverse subarray [4,2], arr becomes [1,2,4,3]
3- Reverse subarray [4,3], arr becomes [1,2,3,4]
There are multiple ways to convert arr to target, this is not the only way to do so.
Example 2:

Input: target = [7], arr = [7]
Output: true
Explanation: arr is equal to target without any reverses.
Example 3:

Input: target = [3,7,9], arr = [3,7,11]
Output: false
Explanation: arr does not have value 9 and it can never be converted to target.


Constraints:

target.length == arr.length
1 <= target.length <= 1000
1 <= target[i] <= 1000
1 <= arr[i] <= 1000
 */
public class TwoArraysEqualByReversingSubArrays {
    public static boolean canBeEqual(int[] target, int[] arr) {
        // Step 1: Count frequencies using HashMap
        HashMap<Integer, Integer> mapTarget = new HashMap<>();
        HashMap<Integer, Integer> mapArr = new HashMap<>();

        for (int num : target) {
            mapTarget.put(num, mapTarget.getOrDefault(num, 0) + 1);
        }
        for (int num : arr) {
            mapArr.put(num, mapArr.getOrDefault(num, 0) + 1);
        }

        // Step 2: Extract and sort the keys
        List<Integer> keysTarget = new ArrayList<>(mapTarget.keySet());
        List<Integer> keysArr = new ArrayList<>(mapArr.keySet());
        Collections.sort(keysTarget);
        Collections.sort(keysArr);

        // Step 3: Compare sorted keys and frequencies
        if (!keysTarget.equals(keysArr)) return false;
        for (int key : keysTarget) {
            if (!mapTarget.get(key).equals(mapArr.get(key))) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] target1 = {1, 2, 3, 4};
        int[] arr1 = {2, 4, 1, 3};
        System.out.println("Output: " + canBeEqual(target1, arr1)); // true

        int[] target2 = {7};
        int[] arr2 = {7};
        System.out.println("Output: " + canBeEqual(target2, arr2)); // true

        int[] target3 = {3, 7, 9};
        int[] arr3 = {3, 7, 11};
        System.out.println("Output: " + canBeEqual(target3, arr3)); // false
    }
}
