package hashtable.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
write a java code for following scenario using hash table with two pointer approach Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]


Example 1:

Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
Example 2:

Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.


Constraints:

2 <= arr.length <= 500
-103 <= arr[i] <= 103
 */
public class CheckIfNAndItsDoubleExists {

        /**
         * Two Pointer + Hash Table Approach
         * Sort array while keeping track of original indices using hash table
         * Time Complexity: O(n log n) due to sorting
         * Space Complexity: O(n) for hash table
         */
        public boolean checkIfExist(int[] arr) {
            // Create array of indices and sort by values
            Integer[] indices = new Integer[arr.length];
            for (int i = 0; i < arr.length; i++) {
                indices[i] = i;
            }

            // Sort indices based on array values
            Arrays.sort(indices, (i, j) -> Integer.compare(arr[i], arr[j]));

            // Use two pointers on sorted array
            for (int i = 0; i < arr.length; i++) {
                int target = 2 * arr[indices[i]];

                // Binary search or two pointer search for target
                int left = 0, right = arr.length - 1;

                while (left <= right) {
                    int currentVal = arr[indices[left]];
                    int rightVal = arr[indices[right]];

                    if (currentVal == target && indices[left] != indices[i]) {
                        return true;
                    }
                    if (rightVal == target && indices[right] != indices[i]) {
                        return true;
                    }

                    // Move pointers based on comparison
                    if (currentVal < target) {
                        left++;
                    } else if (rightVal > target) {
                        right--;
                    } else {
                        // Found target but same index, need to move pointers
                        if (left == right) break;
                        if (currentVal == target) left++;
                        if (rightVal == target) right--;
                    }
                }
            }

            return false;
        }

        /**
         * Enhanced Two Pointer + Hash Table Approach
         * More systematic two-pointer implementation
         * Time Complexity: O(n log n)
         * Space Complexity: O(n)
         */
        public boolean checkIfExistTwoPointer(int[] arr) {
            // Create value-index pairs and sort by value
            int n = arr.length;
            int[][] valueIndex = new int[n][2];

            for (int i = 0; i < n; i++) {
                valueIndex[i][0] = arr[i]; // value
                valueIndex[i][1] = i;      // original index
            }

            // Sort by value
            Arrays.sort(valueIndex, (a, b) -> Integer.compare(a[0], b[0]));

            // Two pointer approach on sorted array
            int left = 0, right = 0;

            while (left < n) {
                int currentVal = valueIndex[left][0];
                int target = 2 * currentVal;

                // Move right pointer to find target
                while (right < n && valueIndex[right][0] < target) {
                    right++;
                }

                // Check if target found and indices are different
                if (right < n && valueIndex[right][0] == target) {
                    if (valueIndex[left][1] != valueIndex[right][1]) {
                        return true;
                    }

                    // Handle duplicates - check next occurrences
                    int tempRight = right + 1;
                    while (tempRight < n && valueIndex[tempRight][0] == target) {
                        if (valueIndex[left][1] != valueIndex[tempRight][1]) {
                            return true;
                        }
                        tempRight++;
                    }
                }

                left++;
            }

            return false;
        }

        /**
         * Hash Table + Two Pointer for specific pairs
         * Use hash table to store complements, then use two pointers to verify
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public boolean checkIfExistHashTwoPointer(int[] arr) {
            // Hash table to store value -> list of indices
            HashMap<Integer, List<Integer>> valueToIndices = new HashMap<>();

            // Populate hash table
            for (int i = 0; i < arr.length; i++) {
                valueToIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
            }

            // Check each element
            for (int i = 0; i < arr.length; i++) {
                int current = arr[i];
                int target = 2 * current;

                if (valueToIndices.containsKey(target)) {
                    List<Integer> targetIndices = valueToIndices.get(target);

                    // Use two pointers on the indices list
                    int left = 0, right = targetIndices.size() - 1;

                    while (left <= right) {
                        int leftIdx = targetIndices.get(left);
                        int rightIdx = targetIndices.get(right);

                        // Check if any of these indices is different from current index
                        if (leftIdx != i) return true;
                        if (rightIdx != i && left != right) return true;

                        left++;
                        right--;
                    }
                }
            }

            return false;
        }

        /**
         * Pure Two Pointer Approach (after sorting)
         * Time Complexity: O(n log n)
         * Space Complexity: O(n)
         */
        public boolean checkIfExistPureTwoPointer(int[] arr) {
            // Create sorted copy with original indices
            class Pair {
                int value, index;
                Pair(int v, int i) { value = v; index = i; }
            }

            Pair[] pairs = new Pair[arr.length];
            for (int i = 0; i < arr.length; i++) {
                pairs[i] = new Pair(arr[i], i);
            }

            Arrays.sort(pairs, (a, b) -> Integer.compare(a.value, b.value));

            // Two pointers to find if any element is double of another
            for (int i = 0; i < arr.length; i++) {
                int target = 2 * pairs[i].value;
                int left = 0, right = arr.length - 1;

                // Binary search using two pointers
                while (left <= right) {
                    int mid = left + (right - left) / 2;

                    if (pairs[mid].value == target && pairs[mid].index != pairs[i].index) {
                        return true;
                    } else if (pairs[mid].value < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            return false;
        }

        // Test cases
        public static void main(String[] args) {
            CheckIfNAndItsDoubleExists solution = new CheckIfNAndItsDoubleExists();

            // Test Case 1
            int[] arr1 = {10, 2, 5, 3};
            System.out.println("Test 1: " + Arrays.toString(arr1));
            System.out.println("Two Pointer Result: " + solution.checkIfExistTwoPointer(arr1));
            System.out.println("Hash + Two Pointer Result: " + solution.checkIfExistHashTwoPointer(arr1));
            System.out.println("Pure Two Pointer Result: " + solution.checkIfExistPureTwoPointer(arr1));
            System.out.println("Expected: true (10 == 2 * 5)\n");

            // Test Case 2
            int[] arr2 = {3, 1, 7, 11};
            System.out.println("Test 2: " + Arrays.toString(arr2));
            System.out.println("Two Pointer Result: " + solution.checkIfExistTwoPointer(arr2));
            System.out.println("Hash + Two Pointer Result: " + solution.checkIfExistHashTwoPointer(arr2));
            System.out.println("Pure Two Pointer Result: " + solution.checkIfExistPureTwoPointer(arr2));
            System.out.println("Expected: false\n");

            // Test Case 3: Edge case with zeros
            int[] arr3 = {0, 0};
            System.out.println("Test 3: " + Arrays.toString(arr3));
            System.out.println("Two Pointer Result: " + solution.checkIfExistTwoPointer(arr3));
            System.out.println("Hash + Two Pointer Result: " + solution.checkIfExistHashTwoPointer(arr3));
            System.out.println("Pure Two Pointer Result: " + solution.checkIfExistPureTwoPointer(arr3));
            System.out.println("Expected: true (0 == 2 * 0)\n");

            // Test Case 4: Negative numbers
            int[] arr4 = {-2, 0, 10, -19, 4, 6, -8};
            System.out.println("Test 4: " + Arrays.toString(arr4));
            System.out.println("Two Pointer Result: " + solution.checkIfExistTwoPointer(arr4));
            System.out.println("Hash + Two Pointer Result: " + solution.checkIfExistHashTwoPointer(arr4));
            System.out.println("Pure Two Pointer Result: " + solution.checkIfExistPureTwoPointer(arr4));
            System.out.println("Expected: false\n");

            // Test Case 5: With duplicates
            int[] arr5 = {4, -7, 8, 4, 6};
            System.out.println("Test 5: " + Arrays.toString(arr5));
            System.out.println("Two Pointer Result: " + solution.checkIfExistTwoPointer(arr5));
            System.out.println("Hash + Two Pointer Result: " + solution.checkIfExistHashTwoPointer(arr5));
            System.out.println("Pure Two Pointer Result: " + solution.checkIfExistPureTwoPointer(arr5));
            System.out.println("Expected: true (8 == 2 * 4)\n");

            // Performance demonstration
            System.out.println("=== Algorithm Comparison ===");
            System.out.println("1. Two Pointer + Hash Table: O(n log n) time, O(n) space");
            System.out.println("2. Hash Table + Two Pointer: O(n) time, O(n) space");
            System.out.println("3. Pure Two Pointer: O(n log n) time, O(n) space");
            System.out.println("\nBest approach: Hash Table + Two Pointer for optimal time complexity");
        }
}
