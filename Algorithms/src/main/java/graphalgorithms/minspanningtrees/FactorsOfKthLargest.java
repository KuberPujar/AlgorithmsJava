package graphalgorithms.minspanningtrees;

import java.util.*;

/*
FACTORS OF K TH LARGEST
You are given two positive integers n and k. A factor of an integer n is defined as an integer i where n % i == 0.

Consider a list of all factors of n sorted in ascending order, return the kth largest factor in this list or return -1 if n has less than k factors.

Input Format:
A single line containing two space-separated integers denoting the value of n and k respectively.

Output Format:
The kth largest factor of 'n'.

Example 1
Input:
12 3

Output:
4

Explanation:
The factors list is [1, 2, 3, 4, 6, 12], the 3rd largest factor is 4.

Example 2
Input:
36 3

Output:
12

Explanation:
To determine the factors of 36, we identify all integers that can divide 36 without leaving a remainder.
Factors list in ascending order: 1, 2, 3, 4, 6, 9, 12, 18, 36
Kth largest factor: Since we are looking for the 3rd largest factor, we sort the factors in descending order
3rd largest factor: The 3rd factor in this list is 12.
Constraints:
1<=k,n<=1000
Note:The function should return the result. The driver code will handle printing the output.
 */
public class FactorsOfKthLargest {
    // Main method to find kth largest factor
    public static int findKthLargestFactor(int n, int k) {
        // Find all factors of n
        List<Integer> factors = findAllFactors(n);

        // Check if we have enough factors
        if (factors.size() < k) {
            return -1;
        }

        // Sort factors in descending order to get largest factors first
        Collections.sort(factors, Collections.reverseOrder());

        // Return kth largest factor (k-1 index since 0-based indexing)
        return factors.get(k - 1);
    }

    // Helper method to find all factors of n
    private static List<Integer> findAllFactors(int n) {
        List<Integer> factors = new ArrayList<>();

        // Find factors by checking all numbers from 1 to sqrt(n)
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i); // Add the factor

                // Add the corresponding factor (n/i) if it's different from i
                if (i != n / i) {
                    factors.add(n / i);
                }
            }
        }

        return factors;
    }

    // Alternative optimized approach using priority queue (min-heap)
    public static int findKthLargestFactorOptimized(int n, int k) {
        // Use a min-heap to keep track of k largest factors
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Find factors and maintain k largest in heap
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                // Process factor i
                if (minHeap.size() < k) {
                    minHeap.offer(i);
                } else if (i > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(i);
                }

                // Process factor n/i if different from i
                if (i != n / i) {
                    int factor = n / i;
                    if (minHeap.size() < k) {
                        minHeap.offer(factor);
                    } else if (factor > minHeap.peek()) {
                        minHeap.poll();
                        minHeap.offer(factor);
                    }
                }
            }
        }

        // Check if we have k factors
        if (minHeap.size() < k) {
            return -1;
        }

        return minHeap.peek(); // Root of min-heap is kth largest
    }

    // Alternative method using TreeSet for automatic sorting
    public static int findKthLargestFactorTreeSet(int n, int k) {
        Set<Integer> factorSet = new TreeSet<>(Collections.reverseOrder());

        // Find all factors
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factorSet.add(i);
                if (i != n / i) {
                    factorSet.add(n / i);
                }
            }
        }

        // Check if we have enough factors
        if (factorSet.size() < k) {
            return -1;
        }

        // Convert to list and return kth element
        List<Integer> factors = new ArrayList<>(factorSet);
        return factors.get(k - 1);
    }

    // Method to display all factors (for debugging)
    public static void displayFactors(int n) {
        List<Integer> factors = findAllFactors(n);
        Collections.sort(factors);
        System.out.println("Factors of " + n + " in ascending order: " + factors);

        Collections.sort(factors, Collections.reverseOrder());
        System.out.println("Factors of " + n + " in descending order: " + factors);
    }

    // Test method
    public static void main(String[] args) {
        // Test case 1
        int n1 = 12, k1 = 3;
        System.out.println("Test Case 1:");
        System.out.println("n = " + n1 + ", k = " + k1);
        displayFactors(n1);

        int result1 = findKthLargestFactor(n1, k1);
        System.out.println("Kth largest factor: " + result1);
        System.out.println("Expected: 4");
        System.out.println();

        // Test case 2
        int n2 = 36, k2 = 3;
        System.out.println("Test Case 2:");
        System.out.println("n = " + n2 + ", k = " + k2);
        displayFactors(n2);

        int result2 = findKthLargestFactor(n2, k2);
        System.out.println("Kth largest factor: " + result2);
        System.out.println("Expected: 12");
        System.out.println();

        // Test case 3 - Edge case where k > number of factors
        int n3 = 7, k3 = 5;
        System.out.println("Test Case 3 (Edge case):");
        System.out.println("n = " + n3 + ", k = " + k3);
        displayFactors(n3);

        int result3 = findKthLargestFactor(n3, k3);
        System.out.println("Kth largest factor: " + result3);
        System.out.println("Expected: -1 (not enough factors)");
        System.out.println();

        // Test optimized versions
        System.out.println("Testing optimized versions:");
        int result1Opt = findKthLargestFactorOptimized(n1, k1);
        int result2Opt = findKthLargestFactorOptimized(n2, k2);
        System.out.println("Optimized Result 1: " + result1Opt);
        System.out.println("Optimized Result 2: " + result2Opt);

        int result1Tree = findKthLargestFactorTreeSet(n1, k1);
        int result2Tree = findKthLargestFactorTreeSet(n2, k2);
        System.out.println("TreeSet Result 1: " + result1Tree);
        System.out.println("TreeSet Result 2: " + result2Tree);
    }
}
