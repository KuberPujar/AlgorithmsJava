package stacks.monotonikstack;

import java.util.Arrays;
import java.util.Stack;

/*
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.



Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {

        /**
         * Finds the number of days to wait for a warmer temperature using monotonic stack.
         *
         * Algorithm:
         * 1. Use a stack to store indices of temperatures in decreasing order
         * 2. For each temperature, pop all indices with smaller temperatures
         * 3. Calculate the difference between current index and popped indices
         * 4. Push current index to stack
         *
         * Time Complexity: O(n) - each element is pushed and popped at most once
         * Space Complexity: O(n) - stack can store up to n elements in worst case
         */
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int[] answer = new int[n];

            // Monotonic decreasing stack storing indices
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < n; i++) {
                // While stack is not empty and current temperature is greater
                // than temperature at index stored at top of stack
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    int prevIndex = stack.pop();
                    // Calculate days difference
                    answer[prevIndex] = i - prevIndex;
                }

                // Push current index to stack
                stack.push(i);
            }

            // Remaining indices in stack will have answer[i] = 0 (default value)
            return answer;
        }

        // Test method
        public static void main(String[] args) {
            DailyTemperatures solution = new DailyTemperatures();

            // Test Case 1
            int[] temps1 = {73, 74, 75, 71, 69, 72, 76, 73};
            int[] result1 = solution.dailyTemperatures(temps1);
            System.out.println("Input: " + Arrays.toString(temps1));
            System.out.println("Output: " + Arrays.toString(result1));
            System.out.println("Expected: [1, 1, 4, 2, 1, 1, 0, 0]");
            System.out.println();

            // Test Case 2
            int[] temps2 = {30, 40, 50, 60};
            int[] result2 = solution.dailyTemperatures(temps2);
            System.out.println("Input: " + Arrays.toString(temps2));
            System.out.println("Output: " + Arrays.toString(result2));
            System.out.println("Expected: [1, 1, 1, 0]");
            System.out.println();

            // Test Case 3
            int[] temps3 = {30, 60, 90};
            int[] result3 = solution.dailyTemperatures(temps3);
            System.out.println("Input: " + Arrays.toString(temps3));
            System.out.println("Output: " + Arrays.toString(result3));
            System.out.println("Expected: [1, 1, 0]");
            System.out.println();

            // Edge Case: Single element
            int[] temps4 = {75};
            int[] result4 = solution.dailyTemperatures(temps4);
            System.out.println("Input: " + Arrays.toString(temps4));
            System.out.println("Output: " + Arrays.toString(result4));
            System.out.println("Expected: [0]");
            System.out.println();

            // Edge Case: Decreasing temperatures
            int[] temps5 = {90, 80, 70, 60};
            int[] result5 = solution.dailyTemperatures(temps5);
            System.out.println("Input: " + Arrays.toString(temps5));
            System.out.println("Output: " + Arrays.toString(result5));
            System.out.println("Expected: [0, 0, 0, 0]");
        }
    }

    /*
     * Detailed Walkthrough for Example 1: [73,74,75,71,69,72,76,73]
     *
     * i=0, temp=73: stack=[], push 0 → stack=[0]
     * i=1, temp=74: 74>73, pop 0, answer[0]=1-0=1, push 1 → stack=[1]
     * i=2, temp=75: 75>74, pop 1, answer[1]=2-1=1, push 2 → stack=[2]
     * i=3, temp=71: 71<75, push 3 → stack=[2,3]
     * i=4, temp=69: 69<71, push 4 → stack=[2,3,4]
     * i=5, temp=72: 72>69, pop 4, answer[4]=5-4=1
     *                72>71, pop 3, answer[3]=5-3=2
     *                72<75, push 5 → stack=[2,5]
     * i=6, temp=76: 76>72, pop 5, answer[5]=6-5=1
     *                76>75, pop 2, answer[2]=6-2=4
     *                push 6 → stack=[6]
     * i=7, temp=73: 73<76, push 7 → stack=[6,7]
     *
     * Final answer: [1,1,4,2,1,1,0,0]
     */

