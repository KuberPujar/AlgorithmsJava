package heaps.dpbased;

import java.util.PriorityQueue;

/*
A super ugly number is a positive integer whose prime factors are in the array primes.

Given an integer n and an array of integers primes, return the nth super ugly number.

The nth super ugly number is guaranteed to fit in a 32-bit signed integer.



Example 1:

Input: n = 12, primes = [2,7,13,19]
Output: 32
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
Example 2:

Input: n = 1, primes = [2,3,5]
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].


Constraints:

1 <= n <= 105
1 <= primes.length <= 100
2 <= primes[i] <= 1000
primes[i] is guaranteed to be a prime number.
All the values of primes are unique and sorted in ascending order.
 */
public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1; // The first super ugly number is always 1

        // Create a min-heap to always get the next smallest super ugly number
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Initialize the heap with the first multiples of each prime
        for (int i = 0; i < primes.length; i++) {
            minHeap.offer(new int[]{primes[i], primes[i], 0});
        }

        for (int i = 1; i < n; i++) {
            // Get the smallest number from the heap
            int[] current = minHeap.peek();
            ugly[i] = current[0];

            // Process all numbers in heap that equal the current ugly number
            while (!minHeap.isEmpty() && minHeap.peek()[0] == ugly[i]) {
                int[] next = minHeap.poll();
                int prime = next[1];
                int index = next[2] + 1;
                minHeap.offer(new int[]{prime * ugly[index], prime, index});
            }
        }

        return ugly[n - 1];
    }

    // Alternative DP-based approach without using a heap
    public int nthSuperUglyNumberWithoutHeap(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1; // First super ugly number

        int[] pointers = new int[primes.length]; // Pointers for each prime

        for (int i = 1; i < n; i++) {
            // Find the next smallest super ugly number
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * ugly[pointers[j]]);
            }
            ugly[i] = min;

            // Move the pointers forward for primes that contributed to this min
            for (int j = 0; j < primes.length; j++) {
                if (primes[j] * ugly[pointers[j]] == min) {
                    pointers[j]++;
                }
            }
        }

        return ugly[n - 1];
    }

    public static void main(String[] args) {
        SuperUglyNumber solution = new SuperUglyNumber();
        int n = 12;
        int[] primes = {2, 7, 13, 19};
        System.out.println("The " + n + "th super ugly number is: " + solution.nthSuperUglyNumber(n, primes));

        // Testing the alternative DP-based approach
        System.out.println("Using DP without heap: " + solution.nthSuperUglyNumberWithoutHeap(n, primes));
    }
}
