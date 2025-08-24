package dynamicprmng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfEratosthenes {

    /**
     * Implements the Sieve of Eratosthenes algorithm to find all prime numbers
     * up to a specified limit 'n'.
     *
     * @param n The upper limit (inclusive) for finding prime numbers.
     * @return A List of integers representing all prime numbers up to 'n'.
     * Returns an empty list if n is less than 2.
     */
    public List<Integer> sieve(int n) {
        List<Integer> primes = new ArrayList<>();

        // Handle edge cases: numbers less than 2 are not prime.
        if (n < 2) {
            return primes;
        }

        // Create a boolean array "isPrime[0..n]" and initialize
        // all entries it as true. A value in isPrime[i] will
        // finally be false if i is Not a prime, else true.
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // Assume all numbers are prime initially

        // 0 and 1 are not prime numbers
        isPrime[0] = false;
        isPrime[1] = false;

        // Start checking from p = 2.
        // We only need to iterate up to sqrt(n) because if a number 'k' has a
        // factor greater than sqrt(k), it must also have a factor smaller than sqrt(k).
        for (int p = 2; p * p <= n; p++) {
            // If isPrime[p] is still true, then it is a prime
            if (isPrime[p]) {
                // Mark all multiples of p (starting from p*p) as not prime.
                // Multiples less than p*p would have already been marked by smaller primes.
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // Collect all prime numbers into the result list
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        SieveOfEratosthenes sieveObj = new SieveOfEratosthenes();

        // Example 1: Finding primes up to 30
        int limit1 = 30;
        System.out.println("Prime numbers up to " + limit1 + ":");
        List<Integer> primes1 = sieveObj.sieve(limit1);
        System.out.println(primes1);
        System.out.println(); // New line for better readability

        // Example 2: Finding primes up to 100
        int limit2 = 100;
        System.out.println("Prime numbers up to " + limit2 + ":");
        List<Integer> primes2 = sieveObj.sieve(limit2);
        System.out.println(primes2);
        System.out.println();

        // Example 3: Finding primes up to 10 (small limit)
        int limit3 = 10;
        System.out.println("Prime numbers up to " + limit3 + ":");
        List<Integer> primes3 = sieveObj.sieve(limit3);
        System.out.println(primes3);
        System.out.println();

        // Example 4: Edge case - limit less than 2
        int limit4 = 1;
        System.out.println("Prime numbers up to " + limit4 + ":");
        List<Integer> primes4 = sieveObj.sieve(limit4);
        System.out.println(primes4);
    }
}
