package heaps;

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
    /**
     * Finds the kth largest factor of a given number n.
     *
     * @param n The number for which to find the factors.
     * @param k The kth largest factor to find.
     * @return The kth largest factor of n, or -1 if there are fewer than k factors.
     */
    public static int kthLargestFactor(int n, int k) {
        if (k <= 0) {
            return -1; // Handle invalid input for k
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min-heap to store the k largest factors

        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                // i is a factor
                minHeap.add(i);
                if (i != n / i) { // Avoid adding the same factor twice (except for perfect squares)
                    minHeap.add(n / i);
                }
                while (minHeap.size() > k)
                {
                    minHeap.poll();
                }
            }

        }
        if (minHeap.size() < k) {
            return -1;
        }

        return minHeap.peek();
    }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int result = kthLargestFactor(n, k);
            System.out.println(result);
            scanner.close();
        }
}
