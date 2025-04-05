package testquestions;
/*
First Unique Number
Given a non-empty list of elements, return the sequentially first element which occurred only once in the array.

Input:
The first line contains N, N is the number of input elements. The second line contains N space-separated input integers.

Output:
Return the first Non-Repeating Element in the array. If there is no element with a frequency 1 print -1.

Constraints:
1 <= N <= 100000
0 <= A[i] <= 10^9
Example 1
Input:

4
9 6 7 6
Output:

9
Explanation:
In the given input, 9 and 7 have a frequency of 1 but since 9 came first so output is 9.

Example 2
Input:

5
7 6 1 6 1
Output

7
Explanation:
In the given input, only 7 has a frequency of 1 .
 */
import java.util.*;

public class FirstUniqueNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        // Find first unique number
        int result = findFirstUnique(arr);
        System.out.println(result);
    }

    public static int findFirstUnique(int[] v) {
        for (int i = 0; i < v.length; i++) {
            boolean isUnique = true;

            // Check all elements after current
            for (int j = i + 1; j < v.length; j++) {
                if (v[i] == v[j]) {
                    isUnique = false;
                    break;
                }
            }

            // Check all elements before current (only if we haven't already seen this number)
            if (isUnique) {
                for (int k = 0; k < i; k++) {
                    if (v[i] == v[k]) {
                        isUnique = false;
                        break;
                    }
                }
            }

            if (isUnique) {
                return v[i];
            }
        }

        return -1;  // No unique number found
    }

    public static int findFirstUniqueWithQueue(int[] arr) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        // First pass: count frequencies and populate queue
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            queue.add(num);
        }

        // Second pass: find first unique in queue
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (frequencyMap.get(current) == 1) {
                return current;
            }
        }

        return -1; // No unique number found
    }
}