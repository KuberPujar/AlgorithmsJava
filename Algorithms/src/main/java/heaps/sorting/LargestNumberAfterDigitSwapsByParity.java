package heaps.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
You are given a positive integer num. You may swap any two digits of num that have the same parity (i.e. both odd digits or both even digits).

Return the largest possible value of num after any number of swaps.



Example 1:

Input: num = 1234
Output: 3412
Explanation: Swap the digit 3 with the digit 1, this results in the number 3214.
Swap the digit 2 with the digit 4, this results in the number 3412.
Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.
Example 2:

Input: num = 65875
Output: 87655
Explanation: Swap the digit 8 with the digit 6, this results in the number 85675.
Swap the first digit 5 with the digit 7, this results in the number 87655.
Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.


Constraints:

1 <= num <= 109
 */
public class LargestNumberAfterDigitSwapsByParity {

    public int largestInteger(int num) {
        // Convert number to string to work with individual digits
        String numStr = String.valueOf(num);
        int n = numStr.length();

        // Arrays to store odd and even digits
        List<Integer> oddDigits = new ArrayList<>();
        List<Integer> evenDigits = new ArrayList<>();

        // Separate digits by parity
        for (char c : numStr.toCharArray()) {
            int digit = c - '0';
            if (digit % 2 == 0) {
                evenDigits.add(digit);
            } else {
                oddDigits.add(digit);
            }
        }

        // Sort both lists in descending order to get largest digits first
        Collections.sort(oddDigits, Collections.reverseOrder());
        Collections.sort(evenDigits, Collections.reverseOrder());

        // Reconstruct the number
        StringBuilder result = new StringBuilder();
        int oddIndex = 0, evenIndex = 0;

        for (char c : numStr.toCharArray()) {
            int originalDigit = c - '0';
            if (originalDigit % 2 == 0) {
                // Use the next largest even digit
                result.append(evenDigits.get(evenIndex++));
            } else {
                // Use the next largest odd digit
                result.append(oddDigits.get(oddIndex++));
            }
        }

        return Integer.parseInt(result.toString());
    }

    // Alternative approach using arrays instead of lists
    public int largestIntegerWithArrays(int num) {
        String numStr = String.valueOf(num);
        int n = numStr.length();

        // Count digits for each parity
        int oddCount = 0, evenCount = 0;
        for (char c : numStr.toCharArray()) {
            int digit = c - '0';
            if (digit % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        // Arrays to store digits
        int[] oddDigits = new int[oddCount];
        int[] evenDigits = new int[evenCount];

        // Fill arrays
        int oddIdx = 0, evenIdx = 0;
        for (char c : numStr.toCharArray()) {
            int digit = c - '0';
            if (digit % 2 == 0) {
                evenDigits[evenIdx++] = digit;
            } else {
                oddDigits[oddIdx++] = digit;
            }
        }

        // Sort in descending order
        Arrays.sort(oddDigits);
        Arrays.sort(evenDigits);

        // Reverse to get descending order
        reverseArray(oddDigits);
        reverseArray(evenDigits);

        // Reconstruct number
        StringBuilder result = new StringBuilder();
        oddIdx = 0;
        evenIdx = 0;

        for (char c : numStr.toCharArray()) {
            int originalDigit = c - '0';
            if (originalDigit % 2 == 0) {
                result.append(evenDigits[evenIdx++]);
            } else {
                result.append(oddDigits[oddIdx++]);
            }
        }

        return Integer.parseInt(result.toString());
    }

    private void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // Test method
    public static void main(String[] args) {
        LargestNumberAfterDigitSwapsByParity sol = new LargestNumberAfterDigitSwapsByParity();

        // Example 1: num = 1234
        int num1 = 1234;
        System.out.println("Example 1:");
        System.out.println("Input: " + num1);
        System.out.println("Output: " + sol.largestInteger(num1));
        System.out.println("Expected: 3412");
        System.out.println("Array approach: " + sol.largestIntegerWithArrays(num1));
        System.out.println();

        // Example 2: num = 65875
        int num2 = 65875;
        System.out.println("Example 2:");
        System.out.println("Input: " + num2);
        System.out.println("Output: " + sol.largestInteger(num2));
        System.out.println("Expected: 87655");
        System.out.println("Array approach: " + sol.largestIntegerWithArrays(num2));
        System.out.println();

        // Additional test cases
        int[] testCases = {123, 246, 1357, 97531, 13579};
        System.out.println("Additional test cases:");
        for (int num : testCases) {
            System.out.println("Input: " + num + " -> Output: " + sol.largestInteger(num));
        }
    }
}
