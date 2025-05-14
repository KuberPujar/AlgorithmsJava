package heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
Jumble for Treasure
You are on an adventure to find the lost treasure. You come across a cave and decide to enter in, inside you find an old tablet upon which some single digit numbers are written. When u dig you find a chest on which it is written that whoever can use all the numbers on the tablet to form two numbers whose sum is the minimum shall be able to open this chest. Your task is to find the code to open the chest.

Sample Input:

6
1 3 5 9 2 4
Sample output:

384
Explanation:
The two possible numbers that can be formed are 135 and 249 whose sum turns out to be minimum which is 384.

Constraints:

1 ≤ n ≤ 10^5

0 ≤ a[i] ≤ 9
The function should return the result.
 */
public class JumbleForTreasure {
    public static int findMinSum(int[] arr) {
        // Use a min-heap (PriorityQueue) to store the numbers
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
        }

        // Build the two numbers, num1 and num2
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        while (!minHeap.isEmpty()) {
            num1.append(minHeap.poll());
            if (!minHeap.isEmpty()) {
                num2.append(minHeap.poll());
            }
        }

        // Convert the strings to integers and calculate the sum.
        int sum = 0;
        if(num1.length() > 0)
            sum += Integer.parseInt(num1.toString());
        if(num2.length() > 0)
            sum += Integer.parseInt(num2.toString());
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numStrs = input.split(" ");
        int[] arr = new int[numStrs.length];
        for (int i = 0; i < numStrs.length; i++) {
            arr[i] = Integer.parseInt(numStrs[i]);
        }
        int result = findMinSum(arr);
        System.out.println(result);
        scanner.close();
    }
}
