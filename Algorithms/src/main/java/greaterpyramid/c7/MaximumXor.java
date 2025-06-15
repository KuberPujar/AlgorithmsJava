package greaterpyramid.c7;

import java.util.HashSet;
import java.util.Set;

/*
Maximum xor pair
Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
Or
You are tasked with finding the maximum XOR (exclusive OR) value that can be achieved by XORing any two elements in a given integer array nums. The XOR operation is defined as a bitwise operation where 0 XOR 0 = 0, 1 XOR 0 = 1, 0 XOR 1 = 1, and 1 XOR 1 = 0.

Input Format:

The first line of input contains an integer n, representing the number of elements in the array.
The second line contains nspace-separated integers, the elements of the arraynums.
Output Format:

Output a single integer, the maximum XOR value that can be obtained by XORing any two elements in the array.
Sample Input 1:

6
3 10 5 25 2 8
Sample Output 1:

28
Explanation:

The maximum result is obtained by XORing 5 and 25, which equals 28 (5 XOR 25 = 28).
Sample Input 2:

12
14 70 53 83 49 91 36 80 92 51 66 70
Sample Output 2:

127
Explanation:

The maximum result is obtained by XORing any appropriate pair of numbers from the given array that yields the result of 127.
Constraints:

1 <= nums.length <= 10000
0 <= nums[i] <= 100000000
 */
public class MaximumXor {
    public static int findMaximumXOR(int[] nums) {
        int maxXor = 0;
        int mask = 0;

        for (int i = 31; i >= 0; i--) {
            mask |= (1 << i);
            Set<Integer> prefixes = new HashSet<>();

            for (int num : nums) {
                prefixes.add(num & mask);
            }

            int candidate = maxXor | (1 << i);
            for (int prefix : prefixes) {
                if (prefixes.contains(candidate ^ prefix)) {
                    maxXor = candidate;
                    break;
                }
            }
        }

        return maxXor;
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println("Maximum XOR: " + findMaximumXOR(nums)); // Output: 28
    }
}
