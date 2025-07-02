package bitmanipulation.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :

p[0] = start
p[i] and p[i+1] differ by only one bit in their binary representation.
p[0] and p[2^n -1] must also differ by only one bit in their binary representation.


Example 1:

Input: n = 2, start = 3
Output: [3,2,0,1]
Explanation: The binary representation of the permutation is (11,10,00,01).
All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]
Example 2:

Input: n = 3, start = 2
Output: [2,6,7,5,4,0,1,3]
Explanation: The binary representation of the permutation is (010,110,111,101,100,000,001,011).


Constraints:

1 <= n <= 16
0 <= start < 2 ^ n
 */
public class CircularPermutationInBinaryRepresentation {

    public List<Integer> circularPermutation(int n, int start) {
        int size = 1 << n;
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[size];
        result.add(start);
        visited[start] = true;
        if (backtrack(n, result, visited, start, size)) {
            return result;
        }
        return new ArrayList<>(); // Should not happen for valid input
    }

    private boolean backtrack(int n, List<Integer> result, boolean[] visited, int curr, int size) {
        if (result.size() == size) {
            // Check if last and first differ by one bit
            return Integer.bitCount(result.get(0) ^ result.get(size - 1)) == 1;
        }
        for (int i = 0; i < n; i++) {
            int next = curr ^ (1 << i); // Flip i-th bit
            if (!visited[next]) {
                visited[next] = true;
                result.add(next);
                if (backtrack(n, result, visited, next, size)) {
                    return true;
                }
                result.remove(result.size() - 1);
                visited[next] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CircularPermutationInBinaryRepresentation solution = new CircularPermutationInBinaryRepresentation();
        int n = 2;
        int start = 3;
        List<Integer> permutation = solution.circularPermutation(n, start);
        System.out.println(permutation); // Output: [3, 2, 0, 1] or any valid circular permutation
    }
}
