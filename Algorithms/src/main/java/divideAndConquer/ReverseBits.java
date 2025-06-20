package divideAndConquer;
/*
Reverse bits of a given 32 bits unsigned integer.

Note:

Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.


Example 1:

Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
Example 2:

Input: n = 11111111111111111111111111111101
Output:   3221225471 (10111111111111111111111111111111)
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.


Constraints:

The input must be a binary string of length 32


Follow up: If this function is called many times, how would you optimize it?
 */
public class ReverseBits {
    // Solution 1: Bit by Bit Reversal - O(32) = O(1) time, O(1) space
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            // Extract the least significant bit of n
            int bit = n & 1;

            // Shift result left and add the extracted bit
            result = (result << 1) | bit;

            // Shift n right to process next bit
            n >>>= 1; // Unsigned right shift
        }

        return result;
    }

    // Solution 2: Using StringBuilder (Alternative approach)
    public int reverseBitsString(int n) {
        StringBuilder sb = new StringBuilder();

        // Convert to 32-bit binary string
        for (int i = 0; i < 32; i++) {
            sb.append((n >>> i) & 1);
        }

        // Parse the reversed binary string back to integer
        return (int) Long.parseLong(sb.toString(), 2);
    }

    // Solution 3: Divide and Conquer (More efficient for multiple calls)
    public int reverseBitsDivideConquer(int n) {
        // Swap every two bits
        n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);

        // Swap every four bits
        n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);

        // Swap every eight bits
        n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);

        // Swap every sixteen bits
        n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);

        // Swap the two halves
        n = (n >>> 16) | (n << 16);

        return n;
    }

    // Solution 4: Lookup Table (Optimal for frequent calls - Follow-up)
    private static final int[] REVERSE_TABLE = new int[256];

    static {
        // Precompute reverse of all 8-bit numbers
        for (int i = 0; i < 256; i++) {
            REVERSE_TABLE[i] = reverseByte(i);
        }
    }

    private static int reverseByte(int b) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            result = (result << 1) | (b & 1);
            b >>>= 1;
        }
        return result;
    }

    public int reverseBitsLookup(int n) {
        // Process 8 bits at a time using lookup table
        return (REVERSE_TABLE[n & 0xFF] << 24) |
                (REVERSE_TABLE[(n >>> 8) & 0xFF] << 16) |
                (REVERSE_TABLE[(n >>> 16) & 0xFF] << 8) |
                (REVERSE_TABLE[(n >>> 24) & 0xFF]);
    }

    // Solution 5: Using Integer.reverse() - Java built-in (Not recommended for interviews)
    public int reverseBitsBuiltIn(int n) {
        return Integer.reverse(n);
    }

    // Helper methods for testing and visualization
    public static String toBinaryString32(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            sb.append((n >>> i) & 1);
        }
        return sb.toString();
    }

    public static String formatBinaryString(String binary) {
        // Add spaces every 4 bits for readability
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binary.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                sb.append(" ");
            }
            sb.append(binary.charAt(i));
        }
        return sb.toString();
    }

    // Performance testing method
    public void performanceTest(int n, int iterations) {
        System.out.println("\n=== Performance Test with " + iterations + " iterations ===");

        long startTime, endTime;

        // Test bit-by-bit approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            reverseBits(n);
        }
        endTime = System.nanoTime();
        System.out.println("Bit-by-bit: " + (endTime - startTime) / 1000000.0 + " ms");

        // Test divide and conquer
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            reverseBitsDivideConquer(n);
        }
        endTime = System.nanoTime();
        System.out.println("Divide & Conquer: " + (endTime - startTime) / 1000000.0 + " ms");

        // Test lookup table
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            reverseBitsLookup(n);
        }
        endTime = System.nanoTime();
        System.out.println("Lookup Table: " + (endTime - startTime) / 1000000.0 + " ms");
    }

    public static void main(String[] args) {
        ReverseBits solution = new ReverseBits();

        // Example 1: 43261596 (00000010100101000001111010011100)
        int n1 = 43261596;
        System.out.println("=== Example 1 ===");
        System.out.println("Input:  " + n1);
        System.out.println("Binary: " + formatBinaryString(toBinaryString32(n1)));

        int result1 = solution.reverseBits(n1);
        System.out.println("Output: " + result1);
        System.out.println("Binary: " + formatBinaryString(toBinaryString32(result1)));
        System.out.println("Expected: 964176192\n");

        // Example 2: -3 (11111111111111111111111111111101)
        int n2 = -3;
        System.out.println("=== Example 2 ===");
        System.out.println("Input:  " + n2);
        System.out.println("Binary: " + formatBinaryString(toBinaryString32(n2)));

        int result2 = solution.reverseBits(n2);
        System.out.println("Output: " + result2);
        System.out.println("Binary: " + formatBinaryString(toBinaryString32(result2)));
        System.out.println("Expected: -1073741825\n");

        // Test all methods give same result
        System.out.println("=== Algorithm Verification ===");
        int testNum = 43261596;
        System.out.println("Testing with input: " + testNum);
        System.out.println("Bit-by-bit:       " + solution.reverseBits(testNum));
        System.out.println("String approach:  " + solution.reverseBitsString(testNum));
        System.out.println("Divide & Conquer: " + solution.reverseBitsDivideConquer(testNum));
        System.out.println("Lookup Table:     " + solution.reverseBitsLookup(testNum));
        System.out.println("Built-in:         " + solution.reverseBitsBuiltIn(testNum));

        // Edge cases
        System.out.println("\n=== Edge Cases ===");
        System.out.println("0: " + solution.reverseBits(0));
        System.out.println("-1: " + solution.reverseBits(-1));
        System.out.println("1: " + solution.reverseBits(1));
        System.out.println("Integer.MAX_VALUE: " + solution.reverseBits(Integer.MAX_VALUE));
        System.out.println("Integer.MIN_VALUE: " + solution.reverseBits(Integer.MIN_VALUE));

        // Performance comparison
        solution.performanceTest(43261596, 1000000);

        // Demonstration of bit manipulation concepts
        System.out.println("\n=== Bit Manipulation Explanation ===");
        demonstrateBitOperations();

        System.out.println("\n=== Algorithm Analysis ===");
        System.out.println("1. Bit-by-bit: Simple, O(32) time, good for understanding");
        System.out.println("2. Divide & Conquer: Fewer operations, parallel bit processing");
        System.out.println("3. Lookup Table: Best for frequent calls, O(1) after preprocessing");
        System.out.println("4. For the follow-up: Use lookup table with 256-entry cache");
    }

    private static void demonstrateBitOperations() {
        int n = 43261596;
        System.out.println("Original number: " + n);
        System.out.println("Binary: " + formatBinaryString(toBinaryString32(n)));
        System.out.println();

        System.out.println("Step-by-step bit extraction:");
        int temp = n;
        for (int i = 0; i < 8 && temp != 0; i++) {
            int bit = temp & 1;
            System.out.println("Step " + i + ": Extract bit " + bit +
                    " (remaining: " + formatBinaryString(toBinaryString32(temp >>> 1)) + ")");
            temp >>>= 1;
        }
        System.out.println("...(continuing for all 32 bits)");

        System.out.println("\nKey operations used:");
        System.out.println("n & 1        : Extract least significant bit");
        System.out.println("result << 1  : Shift result left to make room");
        System.out.println("result | bit : Add the extracted bit");
        System.out.println("n >>> 1      : Unsigned right shift (preserves sign bit handling)");
    }
}

/*
Algorithm Explanations:

1. Bit-by-Bit Reversal:
   - Extract each bit from right to left using (n & 1)
   - Build result by shifting left and adding extracted bit
   - Use unsigned right shift (>>>) to handle negative numbers correctly
   - Time: O(32) = O(1), Space: O(1)

2. Divide and Conquer:
   - Swap bits in pairs, then 4-bit groups, then 8-bit, 16-bit, and finally 32-bit
   - Uses bit masks to isolate and swap groups of bits
   - More efficient as it processes multiple bits simultaneously
   - Time: O(1) with fewer operations, Space: O(1)

3. Lookup Table (Follow-up optimization):
   - Precompute reverse of all 8-bit numbers (0-255)
   - Split 32-bit number into four 8-bit chunks
   - Look up reverse of each chunk and combine
   - Time: O(1) per call after O(256) preprocessing, Space: O(256)

Key Insights:
- Java handles signed integers using 2's complement
- Unsigned right shift (>>>) is crucial for negative numbers
- For frequent calls, lookup table provides best performance
- Divide and conquer reduces number of operations from 32 to ~10

Bit Manipulation Patterns:
- n & 1: Extract least significant bit
- n >>> 1: Unsigned right shift
- result << 1: Shift left to make room for new bit
- result | bit: Set bit at specific position
- Mask patterns: 0xAAAAAAAA, 0x55555555, etc. for group operations

The lookup table approach answers the follow-up question optimally by trading
space (256 integers) for time (constant per call after preprocessing).
*/