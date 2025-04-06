package arrays;

public class MaximumXORForQueries {
    public static int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int[] result = new int[n];
        int xorSum = 0;

        // Calculate prefix XOR array
        for (int i = 0; i < n; i++) {
            xorSum ^= nums[i];
            // The maximum possible XOR is (2^maximumBit - 1)
            // We need to find k such that (xorSum ^ k) is maximized
            // The optimal k is simply (~xorSum) masked with maximumBit bits
            int mask = (1 << maximumBit) - 1;
            result[n - 1 - i] = (~xorSum) & mask;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 3};
        int maximumBit = 2;

        System.out.println("Input array:");
        printArray(nums);
        System.out.println("Maximum bit: " + maximumBit);

        int[] result = getMaximumXor(nums, maximumBit);

        System.out.println("\nMaximum XOR for each query:");
        printArray(result);
    }

    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}