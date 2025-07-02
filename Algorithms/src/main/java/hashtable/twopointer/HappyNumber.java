package hashtable.twopointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/*
Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.



Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false


Constraints:

1 <= n <= 231 - 1
 */
public class HappyNumber {
    /**
     * Helper method to calculate sum of squares of digits
     * Time Complexity: O(log n) where n is the number of digits
     */
    private int getSumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    /**
     * Approach 1: Hash Table + Single Pointer
     * Use HashSet to detect cycles
     * Time Complexity: O(k) where k is the number of iterations until cycle or 1
     * Space Complexity: O(k)
     */
    public boolean isHappy(int n) {
        HashSet<Integer> visited = new HashSet<>();

        while (n != 1 && !visited.contains(n)) {
            visited.add(n);
            n = getSumOfSquares(n);
        }

        return n == 1;
    }

    /**
     * Approach 2: Hash Table + Two Pointers (Sequence Tracking)
     * Use two pointers to track sequence while using hash table for validation
     * Time Complexity: O(k)
     * Space Complexity: O(k)
     */
    public boolean isHappyTwoPointer(int n) {
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<Integer> sequence = new ArrayList<>();

        int slow = n;
        int fast = n;

        while (slow != 1) {
            // Add to sequence and hash table
            sequence.add(slow);
            visited.add(slow);

            // Move slow pointer one step
            slow = getSumOfSquares(slow);

            // Move fast pointer two steps (if possible)
            fast = getSumOfSquares(fast);
            if (fast != 1) {
                fast = getSumOfSquares(fast);
            }

            // Check if we've reached 1
            if (slow == 1 || fast == 1) {
                return true;
            }

            // Check for cycle using hash table
            if (visited.contains(slow)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Approach 3: Floyd's Cycle Detection (Two Pointers) + Hash Table for Verification
     * Use slow/fast pointers to detect cycle, hash table to verify
     * Time Complexity: O(k)
     * Space Complexity: O(k)
     */
    public boolean isHappyFloyd(int n) {
        HashMap<Integer, Integer> visitedOrder = new HashMap<>();
        int slow = n;
        int fast = n;
        int step = 0;

        do {
            // Track order of visited numbers
            if (!visitedOrder.containsKey(slow)) {
                visitedOrder.put(slow, step++);
            }

            // Move pointers
            slow = getSumOfSquares(slow);
            fast = getSumOfSquares(getSumOfSquares(fast));

            // Check if either reached 1
            if (slow == 1 || fast == 1) {
                return true;
            }

        } while (slow != fast);

        // Cycle detected, check if it contains 1
        return false;
    }

    /**
     * Approach 4: Hash Table with Two Pointer Range Checking
     * Use hash table to store ranges and two pointers to check boundaries
     * Time Complexity: O(k)
     * Space Complexity: O(k)
     */
    public boolean isHappyRangeCheck(int n) {
        HashMap<Integer, List<Integer>> rangeMap = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();

        int current = n;
        int left = 0, right = 0;

        while (current != 1 && !visited.contains(current)) {
            visited.add(current);

            // Store in range map (group by first digit for demonstration)
            int firstDigit = Integer.parseInt(String.valueOf(current).substring(0, 1));
            rangeMap.computeIfAbsent(firstDigit, k -> new ArrayList<>()).add(current);

            current = getSumOfSquares(current);

            // Two pointer approach on the current range
            if (rangeMap.containsKey(firstDigit)) {
                List<Integer> range = rangeMap.get(firstDigit);
                left = 0;
                right = range.size() - 1;

                // Check if current number creates a pattern (demonstration)
                while (left <= right) {
                    if (range.get(left).equals(current) || range.get(right).equals(current)) {
                        // Potential cycle detected
                        break;
                    }
                    left++;
                    right--;
                }
            }
        }

        return current == 1;
    }

    /**
     * Approach 5: Optimized Two Pointer (Pure Floyd's - O(1) space)
     * Classic Floyd's cycle detection without hash table
     * Time Complexity: O(k)
     * Space Complexity: O(1)
     */
    public boolean isHappyOptimal(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = getSumOfSquares(slow);
            fast = getSumOfSquares(getSumOfSquares(fast));
        } while (slow != fast);

        return slow == 1;
    }

    /**
     * Approach 6: Hash Table + Bidirectional Two Pointers
     * Use hash table to store forward and backward sequences
     * Time Complexity: O(k)
     * Space Complexity: O(k)
     */
    public boolean isHappyBidirectional(int n) {
        HashMap<Integer, Integer> forwardMap = new HashMap<>();
        HashMap<Integer, Integer> backwardMap = new HashMap<>();

        int current = n;
        int step = 0;

        // Build forward sequence
        while (current != 1 && !forwardMap.containsKey(current)) {
            forwardMap.put(current, step);
            current = getSumOfSquares(current);
            step++;
        }

        if (current == 1) return true;

        // If cycle detected, use two pointers to analyze cycle
        int cycleStart = forwardMap.get(current);
        int cycleLength = step - cycleStart;

        // Build backward map from cycle detection point
        int backCurrent = current;
        for (int i = 0; i < cycleLength; i++) {
            backwardMap.put(backCurrent, i);
            backCurrent = getSumOfSquares(backCurrent);
        }

        // Two pointers to check if cycle contains 1
        int left = 0, right = cycleLength - 1;
        List<Integer> cycleValues = new ArrayList<>(backwardMap.keySet());

        while (left <= right) {
            if (cycleValues.get(left) == 1 || cycleValues.get(right) == 1) {
                return true;
            }
            left++;
            right--;
        }

        return false;
    }

    /**
     * Approach 7: Hash Table + Sliding Window Two Pointers
     * Use sliding window technique with hash table
     * Time Complexity: O(k)
     * Space Complexity: O(k)
     */
    public boolean isHappySlidingWindow(int n) {
        HashMap<Integer, Integer> positionMap = new HashMap<>();
        List<Integer> sequence = new ArrayList<>();

        int current = n;
        int position = 0;

        while (current != 1) {
            if (positionMap.containsKey(current)) {
                // Cycle detected - use two pointers on the cycle
                int cycleStart = positionMap.get(current);
                int left = cycleStart, right = sequence.size() - 1;

                // Check cycle with sliding window
                while (left < right) {
                    if (sequence.get(left) == 1 || sequence.get(right) == 1) {
                        return true;
                    }
                    left++;
                    right--;
                }
                return false;
            }

            positionMap.put(current, position);
            sequence.add(current);
            current = getSumOfSquares(current);
            position++;
        }

        return true;
    }

    // Test and demonstration methods
    public static void main(String[] args) {
        HappyNumber solution = new HappyNumber();

        System.out.println("=== Happy Number Problem ===\n");

        // Test Case 1: Happy number
        int n1 = 19;
        System.out.println("Test Case 1: n = " + n1);
        System.out.println("Tracing the sequence:");

        int temp = n1;
        for (int i = 0; i < 5 && temp != 1; i++) {
            int next = solution.getSumOfSquares(temp);
            System.out.println(temp + " -> " + next + " (sum of squares of digits)");
            temp = next;
        }

        System.out.println("\nResults:");
        System.out.println("Hash Table + Single Pointer: " + solution.isHappy(n1));
        System.out.println("Hash Table + Two Pointers: " + solution.isHappyTwoPointer(n1));
        System.out.println("Floyd's + Hash Table: " + solution.isHappyFloyd(n1));
        System.out.println("Optimal Two Pointer: " + solution.isHappyOptimal(n1));
        System.out.println("Bidirectional: " + solution.isHappyBidirectional(n1));
        System.out.println("Sliding Window: " + solution.isHappySlidingWindow(n1));
        System.out.println("Expected: true\n");

        // Test Case 2: Unhappy number
        int n2 = 2;
        System.out.println("Test Case 2: n = " + n2);
        System.out.println("Tracing the sequence:");

        temp = n2;
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < 10 && temp != 1 && !seen.contains(temp); i++) {
            seen.add(temp);
            int next = solution.getSumOfSquares(temp);
            System.out.println(temp + " -> " + next);
            temp = next;
        }
        if (seen.contains(temp)) {
            System.out.println("Cycle detected at: " + temp);
        }

        System.out.println("\nResults:");
        System.out.println("Hash Table + Single Pointer: " + solution.isHappy(n2));
        System.out.println("Hash Table + Two Pointers: " + solution.isHappyTwoPointer(n2));
        System.out.println("Floyd's + Hash Table: " + solution.isHappyFloyd(n2));
        System.out.println("Optimal Two Pointer: " + solution.isHappyOptimal(n2));
        System.out.println("Bidirectional: " + solution.isHappyBidirectional(n2));
        System.out.println("Sliding Window: " + solution.isHappySlidingWindow(n2));
        System.out.println("Expected: false\n");

        // Test Case 3: Edge cases
        System.out.println("Test Case 3: Edge cases");
        int[] testCases = {1, 7, 10, 23, 44};
        for (int n : testCases) {
            System.out.println("n = " + n + ": " + solution.isHappy(n));
        }

        // Performance comparison
        System.out.println("\n=== Algorithm Comparison ===");
        System.out.println("1. Hash Table + Single Pointer: O(k) time, O(k) space");
        System.out.println("2. Hash Table + Two Pointers: O(k) time, O(k) space");
        System.out.println("3. Floyd's + Hash Table: O(k) time, O(k) space");
        System.out.println("4. Range Check: O(k) time, O(k) space");
        System.out.println("5. Optimal Two Pointer: O(k) time, O(1) space ⭐");
        System.out.println("6. Bidirectional: O(k) time, O(k) space");
        System.out.println("7. Sliding Window: O(k) time, O(k) space");

        System.out.println("\nBest overall: Optimal Two Pointer (Floyd's cycle detection)");
        System.out.println("Best with hash table: Hash Table + Two Pointers");

        // Demonstrate cycle detection
        System.out.println("\n=== Cycle Analysis for n = 2 ===");
        demonstrateCycle(2);
    }

    public static void demonstrateCycle(int n) {
        HappyNumber solution = new HappyNumber();
        HashSet<Integer> visited = new HashSet<>();
        List<Integer> sequence = new ArrayList<>();

        int current = n;
        System.out.println("Sequence: ");

        while (!visited.contains(current)) {
            visited.add(current);
            sequence.add(current);
            System.out.print(current + " -> ");
            current = solution.getSumOfSquares(current);
        }

        System.out.println(current + " (cycle detected)");

        // Show the cycle
        int cycleStart = sequence.indexOf(current);
        System.out.print("Cycle: ");
        for (int i = cycleStart; i < sequence.size(); i++) {
            System.out.print(sequence.get(i) + " -> ");
        }
        System.out.println(current + " (repeats)");
    }
}
