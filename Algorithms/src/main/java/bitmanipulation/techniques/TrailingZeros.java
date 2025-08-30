package bitmanipulation.techniques;
/*
Given an integer A, the task is to count and return the number of trailing zeroes in its binary representation.

Constraints
0 <= A <= 109

Constraint Analysis
For the given constraints ( 0 <= A <= 109), the possible time complexities for solving the problem of counting trailing zeros could range from O(log A) to O(1). Here's a breakdown:

O(log A):

If the solution involves repeatedly dividing A by 10 until it becomes 0, the time complexity will be logarithmic in the value of A.

O(1):

If the solution relies on bitwise operations or other constant-time operations, the time complexity can be constant.

Hints To Solve The Problems
Call connected, dnp, add interaction without a call view only. enough info to be able to dial the leads

Check the last bit

If it is 1 , break instantly

Otherwise keep incrementing count because the last bit is zero.
 */
public class TrailingZeros {
    //traditional approach
    /*
    Intuition
Convert the integer into the binary representation as a string

Iterate through the characters from the right until the first '1' is encountered.

As soon as a '1' is encountered while iterating from right to left, the loop terminates.

The count at this point represents the number of trailing zeroes in the binary representation of the original integer A.

Code Walkthrough
Initialize Counter
count = 0

Initializes a counter variable count to zero. This variable will be used to count the trailing zeroes.

A counter variable, count, is initialized to zero. This counter is pivotal in the algorithm, serving as a tally for the number of trailing zeros encountered in the binary string. Its initial value is set to zero to accurately reflect the count of trailing zeros as the algorithm progresses through the binary string.

Iterate Through Binary String from Right to Left
For i from length(binaryString) - 1 to 0:

Initiates a loop that iterates through the characters of the binaryString from the rightmost (least significant bit) to the leftmost.

The core of the algorithm is a for-loop that iterates through the binaryString in reverse order, starting from the last character (rightmost bit) to the first character (leftmost bit). This reverse traversal is deliberate, targeting the direct examination of trailing zeros without the need to inspect the entire string. It optimizes the search process, focusing solely on the segment of the binary string where trailing zeros are located.

Check for '0' Characters
If binaryString[i] is '0'

Checks if the current character at position i in the binary string is '0'.

If true, increments the count variable.

Within the loop, a conditional check is performed on each character of the binary string. If the character at the current position, i, is a 0, it signifies the discovery of a trailing zero. Consequently, the count variable is incremented. This incrementation is a critical operation, as it updates the count to reflect the number of trailing zeros identified up to that point in the iteration.

Exit Loop When '1' is Encountered
Else: Break

Exits the loop when the first '1' is encountered in the binary string.

The iteration continues until a 1 is encountered, signaling the end of the trailing zeros. This encounter prompts an else branch that executes a break statement. This break is strategic, effectively halting the loop and preventing further iteration. It's based on the understanding that once a 1 is found, no additional zeros to its left (in the binary string) will count as trailing zeros. Thus, it conservatively conserves computational resources by terminating the search early.

Return Count
Return count

Returns the final count of trailing zeroes.

Upon completion of the loop (and thus, the termination condition being met or the entire binary string being traversed), the algorithm concludes by returning the count variable. This variable now holds the total number of trailing zeros in the binary representation of the integer A, offering a precise count that has been meticulously tallied through the described process.

Time Complexity Analysis
The time complexity of the provided solve method can be analysed as follows:

Binary Conversion

String binaryString = Integer.toBinaryString(A);: The binary representation of an integer with n bits is obtained in O(n) time. Therefore, the binary conversion has a time complexity of O(log A), where A is the input integer.

Iteration through Binary String

for (int i = binaryString.length() - 1; i >= 0; i--): The loop iterates through each bit of the binary representation, which has a length of O(log A).

Each iteration involves constant-time operations (charAt and comparisons).

Therefore, the overall time complexity of the solve method is O(log A), where A is the input integer.
     */
    public static int solve(int A) {
        String binaryString = Integer.toBinaryString(A);
        int count = 0;
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            if (binaryString.charAt(i) == '0') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    //bit manipulation approach
    /*
    Intuition
The loop starts from the least significant bit and moves towards the most significant bit.

It checks each bit, and if a '1' is encountered, it means the trailing zeros have ended, and the loop breaks.

If a '0' is encountered, it increments the count, as '0' indicates a trailing zero.

The count represents the number of trailing zeros in the binary representation of the input integer.

Code Walkthrough
Initialize Counter
count = 0

Initializes a counter variable count to zero. This variable will be used to count the trailing zeroes.

The algorithm begins by initializing a counter variable, count, to zero. This counter is fundamental to the method's operation, as it is used to accumulate the number of trailing zeros found in the binary representation of A. Initializing count to zero ensures that the tally starts afresh, providing an accurate count as the algorithm progresses.

Iterate Through Bits of the Integer
For i from 0 to 31

Initiates a loop that iterates through the 32 bits of the integer A.

The next step involves a for-loop that iterates from 0 to 31, reflecting the 32 bits of the integer A. This loop is structured to sequentially access each bit of A, starting from the least significant bit (LSB) and moving towards the most significant bit (MSB). The iteration range is deliberately chosen to encompass all the bits of a standard 32-bit integer, ensuring that no bit is overlooked in the search for trailing zeros.

Check i-th Bit using Bitwise AND
If ((A >> i) & 1) == 1

Right shifts the bits of A by i positions and checks if the least significant bit after the shift is 1.

If true, it means the i-th bit is 1, and the loop is exited.

Within the loop, the algorithm employs a bitwise AND operation combined with a right shift to inspect each bit of A. Specifically, A is right-shifted i positions, and the result is ANDed with 1 ((A >> i) & 1). This operation isolates the i-th bit, moving it to the LSB position and masking off all other bits. The condition checks if this isolated bit is 1. If so, it indicates that the i-th bit is 1, marking the end of any trailing zeros since bits are examined from least to most significant.

Exit Loop When '1' is Encountered
Break

Exits the loop when the first '1' is encountered in the binary representation of the integer.

Upon encountering a 1 (when the condition ((A >> i) & 1) == 1 evaluates to true), the algorithm executes a break statement. This action prematurely exits the loop, as the discovery of a 1 signifies the end of trailing zeros. This break is crucial for the algorithm's efficiency, as it stops the iteration process once the objective (counting trailing zeros) is achieved, avoiding unnecessary checks of the remaining bits.

Increment Counter
Increment count

If the i-th bit is not 1, increments the count variable.

If the i-th bit is not 1 (indicating a 0), the loop continues, and the count variable is incremented. This incrementing occurs for each 0 encountered before the first 1, effectively tallying the number of trailing zeros in the binary representation of A.

Return Count
Return count

Returns the final count of trailing zeroes.

After the loop concludes (either from reaching the first 1 or completing all 32 iterations without finding a 1), the algorithm returns the count variable. This variable now holds the total count of trailing zeros in the integer A's 32-bit binary representation, providing a precise and direct measure of the number of zero bits following the LSB before the first occurrence of a 1.

Time Complexity Analysis
The time complexity of the provided solve method can be analysed as follows:

Loop Iteration

The loop runs for a constant number of iterations (32 times), regardless of the value of the input integer A.

Therefore, the loop iteration has a constant time complexity of O(1).

Bitwise Shift and Check Operations

Inside the loop, there are constant-time operations such as bitwise right shift (>>), bitwise AND (&), and equality checks.

These operations are performed a constant number of times (32 times), and each operation takes constant time.

Overall Time Complexity

The dominant factor in the time complexity is the loop iteration, which is a constant number of times (32 times) regardless of the size of the input integer A.

Therefore, the overall time complexity of the solve method is O(1).

In summary, the time complexity of the solve method is constant O(1). The loop runs for a fixed number of iterations (32 times), making the time complexity independent of the value of the input integer. The method provides an efficient solution for counting trailing zeros in the binary representation of a 32-bit integer.

Space Complexity Analysis
The space complexity of the provided function solve(A) is O(1).

The function uses a constant amount of space to store the variable count, which is an integer. Additionally, the loop variable i is also considered to have constant space complexity since it represents a single integer.
     */

    public static int solveBitManipulation(int A) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (((A >> i) & 1) == 1) {
                break;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 40;
        System.out.println(solve(n));
        System.out.println(solveBitManipulation(n));
    }
}
