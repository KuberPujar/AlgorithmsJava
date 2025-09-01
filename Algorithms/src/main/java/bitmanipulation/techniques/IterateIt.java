package bitmanipulation.techniques;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Description
Consider the following pseudocode, run on an array A=[a0 , a1, a2, a3……] of length 'n' :
rep := 0
while A not empty:
    B := []
    for x in A, y in A:
        if x != y: append absolute_value(x - y) to B
    A := B
    rep := rep + 1

Given the values of 'n' and array 'A' , compute and print the final value of 'rep' after the pseudocode above terminates; if the loop will never terminate, print -1 instead.
Constraint
1 <= n <= 105
1 <= ai <= 5 × 104 for all 1 <= i <= n
Constraint Analysis
For the given constraints (1 <= n <= 105, 1 <= ai <= 5 × 104), the possible time complexities for the provided pseudocode could be:
O(n^3)
The pseudocode involves a nested loop where, for each pair of elements in array A, it performs a comparison and appends the absolute difference to array B. The worst-case time complexity is cubic due to the nested loops.
O(n^2 log n)
If the array A is sorted or certain optimizations are made, the inner loop's complexity could be reduced to O(log n), resulting in an overall time complexity of O(n^2 log n).
O(n^2)
Considering the worst-case scenario where no optimizations are made, the time complexity would be quadratic due to the nested loops.
Intuition
The program iteratively processes the input array, identifying consecutive numbers and normalising them by dividing by their GCD.
It checks if the normalised numbers form a consecutive sequence starting from 1.
If not, it updates the differences between the numbers and repeats the process.
The loop continues until a consecutive sequence is found, and the result is the total number of iterations.
 */
public class IterateIt {
    //traditional approach
    public static int solve(List<Integer> A) {
        int rep = 0;
        while (!A.isEmpty()) {
            List<Integer> B = new ArrayList<>();
            int n = A.size();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        B.add(Math.abs(A.get(i) - A.get(j)));
                    }
                }
            }
            if (B.isEmpty()) break;
            A = B;
            rep++;
        }
        return rep;
    }

    public static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public static void findMinimumGroups() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        boolean[] c = new boolean[50009];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            c[a[i]] = true;
        }

        int ret = 0;
        while (true) {
            List<Integer> v = new ArrayList<>();
            for (int i = 1; i <= 50000; i++) {
                if (c[i]) {
                    v.add(i);
                    c[i] = false;
                }
            }
            if (v.size() == 0) break;

            int g = v.get(0);
            for (int x : v) {
                g = gcd(g, x);
            }
            for (int i = 0; i < v.size(); i++) {
                v.set(i, v.get(i) / g);
            }
            v.sort(Integer::compareTo);
            boolean ok = true;
            for (int i = 0; i < v.size(); i++) {
                if (v.get(i) != i + 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println(ret + 1);
                return;
            }
            for (int i = 1; i < v.size(); i++) {
                c[v.get(i) - v.get(i - 1)] = true;
            }
            ret++;
        }
    }

    public static void main(String[] args) {
        // Example usage of the traditional approach
        List<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(2);
        A.add(3);
        //int result = solve(A);
        //System.out.println("Number of iterations (traditional approach): " + result);

        // Example usage of the optimized approach
        System.out.println("Enter the number of elements followed by the elements:");
        findMinimumGroups();

    }
}
