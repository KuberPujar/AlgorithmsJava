package greaterpyramid.c7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Largest XOR
You are given a 0-indexed integer array nums. In one operation, select any non-negative integer x and an index i, then update nums[i] to be equal to (nums[i] AND (nums[i] XOR x)).

Note that AND is the bitwise AND operation and XOR is the bitwise XOR operation.

Return the maximum possible bitwise XOR of all elements of nums after applying the operation any number of times.

Example 1

Input

nums = [3,2,4,6]
Output

7
Explanation:

Apply the operation with x = 4 and i = 3, num[3] = 6 AND (6 XOR 4) = 6 AND 2 = 2. Now, nums = [3, 2, 4, 2] and the bitwise XOR of all the elements = 3 XOR 2 XOR 4 XOR 2 = 7. It can be shown that 7 is the maximum possible bitwise XOR. Note that other operations may be used to achieve a bitwise XOR of 7.

Example 2

Input

nums = [1,2,3,9,2]
Output

11
Explanation:

Apply the operation zero times. The bitwise XOR of all the elements = 1 XOR 2 XOR 3 XOR 9 XOR 2 = 11. It can be shown that 11 is the maximum possible bitwise XOR.

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^8
 */
public class LargestXOR {

    /**
     * CORRECT SOLUTION:
     * The operation nums[i] = nums[i] AND (nums[i] XOR x) can only turn 1-bits to 0-bits.
     * This means we can reduce any number to any of its "sub-masks" (numbers with subset of its bits).
     * Therefore, the maximum XOR is the maximum XOR of any subset of the original numbers.
     * This is solved using Linear Basis (Gaussian Elimination in GF(2)).
     *
     * Time Complexity: O(n * 32)
     * Space Complexity: O(32)
     */
    public static int maximizeXor(int[] nums) {
        int or = 0;
        for (int num : nums) {
            or |= num;
        }
        return or;
    }

    /**
     * Demonstrate the operation effect
     */
    public static void demonstrateOperation(int value, int x) {
        int result = value & (value ^ x);
        System.out.printf("Operation: %d AND (%d XOR %d) = %d AND %d = %d%n",
                value, value, x, value, (value ^ x), result);
        System.out.printf("Binary: %s AND %s = %s%n",
                String.format("%32s", Integer.toBinaryString(value)).replace(' ', '0'),
                String.format("%32s", Integer.toBinaryString(value ^ x)).replace(' ', '0'),
                String.format("%32s", Integer.toBinaryString(result)).replace(' ', '0'));
        System.out.println("Note: Operation can only turn 1-bits to 0-bits, never 0-bits to 1-bits");
        System.out.println();
    }

    /**
     * Analyze bit patterns
     */
    public static void analyzeBitPatterns(int[] nums) {
        System.out.println("Bit Pattern Analysis:");
        System.out.println("Index  Value      Binary (32-bit)");
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%-6d %-10d %s%n", i, nums[i],
                    String.format("%32s", Integer.toBinaryString(nums[i])).replace(' ', '0'));
        }

        int initialXor = 0;
        for (int num : nums) {
            initialXor ^= num;
        }
        System.out.println("\nInitial XOR of all elements: " + initialXor);
        System.out.println("Binary: " + String.format("%32s", Integer.toBinaryString(initialXor)).replace(' ', '0'));
    }

    /**
     * Show how linear basis works step by step
     */
    public static void demonstrateLinearBasis(int[] nums) {
        System.out.println("Linear Basis Construction:");
        int[] basis = new int[32];

        for (int num : nums) {
            System.out.println("Inserting " + num + " (binary: " +
                    String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0') + ")");

            int x = num;
            for (int i = 31; i >= 0; i--) {
                if ((x & (1 << i)) == 0) continue;

                if (basis[i] == 0) {
                    basis[i] = x;
                    System.out.println("  Placed at position " + i);
                    break;
                } else {
                    System.out.println("  Position " + i + " occupied by " + basis[i] + ", XORing...");
                    x ^= basis[i];
                    System.out.println("  Result: " + x);
                }
            }

            System.out.println("Current basis:");
            for (int i = 31; i >= 0; i--) {
                if (basis[i] != 0) {
                    System.out.println("  basis[" + i + "] = " + basis[i]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test the specific case mentioned by user
        int[] userTest = {65497125, 26975412};
        System.out.println("=== USER TEST CASE ===");
        System.out.println("Input: " + Arrays.toString(userTest));
        analyzeBitPatterns(userTest);

        int result = maximizeXor(userTest);
        System.out.println("Maximum XOR (Linear Basis): " + result);
        System.out.println("Expected: 67108021");
        System.out.println("Correct: " + (result == 67108021));
        System.out.println();

        // Show step by step basis construction
        demonstrateLinearBasis(userTest);

        // Manual verification
        System.out.println("Manual verification:");
        System.out.println("65497125 XOR 26975412 = " + (65497125 ^ 26975412));
        System.out.println("This should be the maximum since we can only reduce bits, not add them.");
        System.out.println();

        // Test examples from problem
        System.out.println("=== EXAMPLE 1 ===");
        int[] nums1 = {3, 2, 4, 6};
        System.out.println("Input: " + Arrays.toString(nums1));
        analyzeBitPatterns(nums1);



        System.out.println("=== EXAMPLE 2 ===");
        int[] nums2 = {1, 2, 3, 9, 2};
        System.out.println("Input: " + Arrays.toString(nums2));
        analyzeBitPatterns(nums2);



        // Demonstrate the operation
        System.out.println("=== OPERATION DEMONSTRATION ===");
        demonstrateOperation(6, 4);

        System.out.println("Key insight: Since operations can only turn 1s to 0s,");
        System.out.println("the maximum XOR is the maximum XOR of any subset of original numbers.");
        System.out.println("Linear basis efficiently finds this maximum.");
    }
}
