package graphalgorithms.bfsdfs;

import java.util.*;

/*
Bulb Switches
There is a room with n bulbs labeled from 1 to n that all are turned on initially, and four buttons on the wall. Each of the four buttons has a different functionality where:

Button 1: Flips the status of all the bulbs. Button 2: Flips the status of all the bulbs with even labels (i.e., 2, 4, ...). Button 3: Flips the status of all the bulbs with odd labels (i.e., 1, 3, ...). Button 4: Flips the status of all the bulbs with a label j = 3k + 1 where k = 0, 1, 2, ... (i.e., 1, 4, 7, 10, ...). You must make exactly presses button presses in total. For each press, you may pick any of the four buttons to press.

Given the two integers n and presses, return the number of different possible statuses after performing all presses button presses.

Input Format:

n: The number of bulbs (1 ≤ n ≤ 1000).
presses: The number of button presses (0 ≤ presses ≤ 10^3).
Output Format:

Return an integer representing the number of different possible statuses of the bulbs after performing exactly presses button presses.
Example 1:
Input:

1 1
Output:

 2
Explanation
There is 1 bulb, initially turned on.
We have 1 press to perform. We can either:
Flip the status of all bulbs (Button 1) which will turn the bulb off.
Thus, there are 2 possible statuses for the bulb after 1 press: on or off.
Example 2:

Input:

2 1
Output:

3
Explanation
There are 2 bulbs, initially both turned on.
We have 1 press to perform. We can use:
Button 1: Flips both bulbs (both off).
Button 2: Flips the 2nd bulb (1st bulb remains on, 2nd bulb off).
Button 3: Flips the 1st bulb (1st bulb off, 2nd bulb on).
Button 4: Flips bulbs with labels of the form 3k + 1 (only the 1st bulb flips, 2nd bulb remains unchanged).
The possible distinct statuses after 1 press are:
Both bulbs on.
1st bulb on, 2nd bulb off.
1st bulb off, 2nd bulb on.
Both bulbs off.
Thus, there are 3 distinct possible statuses after 1 press.
Constraints
1 ≤ n ≤ 1000: Number of bulbs.
0 ≤ presses ≤ 1000: Number of button presses.
Note:The function should return the result. The driver code will handle printing the output.
 */
public class BulbSwitches {

        // BFS Solution - Explores all possible button press combinations
        public static int flipLights(int n, int presses) {
            // Initial state: all bulbs are on (represented as true)
            boolean[] initialState = new boolean[n];
            Arrays.fill(initialState, true);

            // Use BFS to explore all possible states
            Queue<State> queue = new LinkedList<>();
            Set<String> uniqueStates = new HashSet<>();
            Set<String> visited = new HashSet<>();

            // Start with initial state and 0 presses used
            queue.offer(new State(initialState, 0));
            visited.add(stateToString(initialState) + ",0");

            while (!queue.isEmpty()) {
                State current = queue.poll();

                // If we've used exactly 'presses' button presses, record this state
                if (current.pressesUsed == presses) {
                    uniqueStates.add(stateToString(current.bulbs));
                    continue;
                }

                // If we've used all presses, skip further exploration
                if (current.pressesUsed >= presses) {
                    continue;
                }

                // Try pressing each of the 4 buttons
                for (int button = 1; button <= 4; button++) {
                    boolean[] newState = current.bulbs.clone();
                    pressButton(newState, button);

                    String stateKey = stateToString(newState) + "," + (current.pressesUsed + 1);
                    if (!visited.contains(stateKey)) {
                        visited.add(stateKey);
                        queue.offer(new State(newState, current.pressesUsed + 1));
                    }
                }
            }

            return uniqueStates.size();
        }

        // Optimized Mathematical Solution
        // Key insight: pressing same button twice cancels out, so we only care about
        // whether each button is pressed odd or even number of times
        public static int flipLightsOptimized(int n, int presses) {
            Set<String> states = new HashSet<>();

            // We only need to consider pressing each button 0 or 1 times
            // because pressing twice cancels out
            for (int b1 = 0; b1 <= 1; b1++) {
                for (int b2 = 0; b2 <= 1; b2++) {
                    for (int b3 = 0; b3 <= 1; b3++) {
                        for (int b4 = 0; b4 <= 1; b4++) {
                            int totalPresses = b1 + b2 + b3 + b4;

                            // Check if this combination can achieve exactly 'presses' button presses
                            // We can achieve 'presses' if the difference is even and non-negative
                            if (totalPresses <= presses && (presses - totalPresses) % 2 == 0) {
                                boolean[] bulbs = new boolean[n];
                                Arrays.fill(bulbs, true); // Initially all on

                                // Apply button presses
                                if (b1 == 1) pressButton(bulbs, 1);
                                if (b2 == 1) pressButton(bulbs, 2);
                                if (b3 == 1) pressButton(bulbs, 3);
                                if (b4 == 1) pressButton(bulbs, 4);

                                states.add(stateToString(bulbs));
                            }
                        }
                    }
                }
            }

            return states.size();
        }

        // Apply the effect of pressing a specific button
        private static void pressButton(boolean[] bulbs, int button) {
            int n = bulbs.length;

            switch (button) {
                case 1: // Flip all bulbs
                    for (int i = 0; i < n; i++) {
                        bulbs[i] = !bulbs[i];
                    }
                    break;

                case 2: // Flip even-labeled bulbs (2, 4, 6, ...)
                    for (int i = 1; i < n; i += 2) { // 0-indexed, so i=1,3,5... are even-labeled
                        bulbs[i] = !bulbs[i];
                    }
                    break;

                case 3: // Flip odd-labeled bulbs (1, 3, 5, ...)
                    for (int i = 0; i < n; i += 2) { // 0-indexed, so i=0,2,4... are odd-labeled
                        bulbs[i] = !bulbs[i];
                    }
                    break;

                case 4: // Flip bulbs with label j = 3k + 1 (1, 4, 7, 10, ...)
                    for (int i = 0; i < n; i++) {
                        if ((i + 1) % 3 == 1) { // Convert 0-indexed to 1-indexed and check 3k+1
                            bulbs[i] = !bulbs[i];
                        }
                    }
                    break;
            }
        }

        // Convert bulb state array to string for comparison
        private static String stateToString(boolean[] bulbs) {
            StringBuilder sb = new StringBuilder();
            for (boolean bulb : bulbs) {
                sb.append(bulb ? '1' : '0');
            }
            return sb.toString();
        }

        // State class for BFS
        static class State {
            boolean[] bulbs;
            int pressesUsed;

            State(boolean[] bulbs, int pressesUsed) {
                this.bulbs = bulbs.clone();
                this.pressesUsed = pressesUsed;
            }
        }

        // DFS Alternative Solution
        public static int flipLightsDFS(int n, int presses) {
            boolean[] initialState = new boolean[n];
            Arrays.fill(initialState, true);

            Set<String> uniqueStates = new HashSet<>();
            Set<String> visited = new HashSet<>();

            dfs(initialState, presses, 0, uniqueStates, visited);
            return uniqueStates.size();
        }

        private static void dfs(boolean[] bulbs, int presses, int pressesUsed,
                                Set<String> uniqueStates, Set<String> visited) {
            // Create state key for memoization
            String stateKey = stateToString(bulbs) + "," + pressesUsed;
            if (visited.contains(stateKey)) {
                return;
            }
            visited.add(stateKey);

            // If we've used exactly the required number of presses
            if (pressesUsed == presses) {
                uniqueStates.add(stateToString(bulbs));
                return;
            }

            // If we've exceeded the number of presses, return
            if (pressesUsed > presses) {
                return;
            }

            // Try pressing each button
            for (int button = 1; button <= 4; button++) {
                boolean[] newState = bulbs.clone();
                pressButton(newState, button);
                dfs(newState, presses, pressesUsed + 1, uniqueStates, visited);
            }
        }

        // Ultra-Optimized Solution for large inputs
        // Based on the mathematical insight that only first 3 bulbs matter for the pattern
        public static int flipLightsUltraOptimized(int n, int presses) {
            // For efficiency, we only need to consider the first min(n, 3) bulbs
            // because the pattern repeats every 3 bulbs for button effects
            int effectiveN = Math.min(n, 3);
            Set<String> states = new HashSet<>();

            // Try all combinations of pressing each button 0 or 1 times
            for (int b1 = 0; b1 <= 1; b1++) {
                for (int b2 = 0; b2 <= 1; b2++) {
                    for (int b3 = 0; b3 <= 1; b3++) {
                        for (int b4 = 0; b4 <= 1; b4++) {
                            int totalPresses = b1 + b2 + b3 + b4;

                            // Check if we can achieve exactly 'presses' with this combination
                            if (totalPresses <= presses && (presses - totalPresses) % 2 == 0) {
                                boolean[] bulbs = new boolean[effectiveN];
                                Arrays.fill(bulbs, true);

                                // Apply button effects
                                if (b1 == 1) applyButton1(bulbs);
                                if (b2 == 1) applyButton2(bulbs);
                                if (b3 == 1) applyButton3(bulbs);
                                if (b4 == 1) applyButton4(bulbs);

                                states.add(stateToString(bulbs));
                            }
                        }
                    }
                }
            }

            return states.size();
        }

        // Optimized button press methods for small arrays
        private static void applyButton1(boolean[] bulbs) {
            for (int i = 0; i < bulbs.length; i++) {
                bulbs[i] = !bulbs[i];
            }
        }

        private static void applyButton2(boolean[] bulbs) {
            if (bulbs.length > 1) bulbs[1] = !bulbs[1]; // 2nd bulb (index 1)
            if (bulbs.length > 2) bulbs[2] = !bulbs[2]; // 3rd bulb (index 2) - actually 4th bulb in 1-indexed
        }

        private static void applyButton3(boolean[] bulbs) {
            if (bulbs.length > 0) bulbs[0] = !bulbs[0]; // 1st bulb (index 0)
            if (bulbs.length > 2) bulbs[2] = !bulbs[2]; // 3rd bulb (index 2)
        }

        private static void applyButton4(boolean[] bulbs) {
            if (bulbs.length > 0) bulbs[0] = !bulbs[0]; // 1st bulb (3*0 + 1 = 1)
            // For 3 bulbs, only index 0 is affected (1st bulb)
            // 4th bulb would be at index 3 (3*1 + 1 = 4), but we only consider first 3
        }

        // Test method
        public static void main(String[] args) {
            // Test cases from examples
            testCase(1, 1, 2);  // Example 1
            testCase(2, 1, 3);  // Example 2
            testCase(3, 1, 4);  // Additional test
            testCase(2, 2, 2);  // Additional test
            testCase(1000, 5, -1); // Large input test (expected unknown)
        }

        private static void testCase(int n, int presses, int expected) {
            System.out.println("\n=== Test Case ===");
            System.out.println("Bulbs: " + n + ", Presses: " + presses);

            long startTime = System.currentTimeMillis();
            int resultOptimized = flipLightsOptimized(n, presses);
            long optimizedTime = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            int resultUltraOptimized = flipLightsUltraOptimized(n, presses);
            long ultraOptimizedTime = System.currentTimeMillis() - startTime;

            System.out.println("Optimized Result: " + resultOptimized + " (Time: " + optimizedTime + "ms)");
            System.out.println("Ultra-Optimized Result: " + resultUltraOptimized + " (Time: " + ultraOptimizedTime + "ms)");

            if (expected != -1) {
                System.out.println("Expected: " + expected);
                System.out.println("Test " + (resultOptimized == expected ? "PASSED" : "FAILED"));
            }

            // For small inputs, also test BFS and DFS
            if (n <= 10 && presses <= 5) {
                startTime = System.currentTimeMillis();
                int resultBFS = flipLights(n, presses);
                long bfsTime = System.currentTimeMillis() - startTime;

                startTime = System.currentTimeMillis();
                int resultDFS = flipLightsDFS(n, presses);
                long dfsTime = System.currentTimeMillis() - startTime;

                System.out.println("BFS Result: " + resultBFS + " (Time: " + bfsTime + "ms)");
                System.out.println("DFS Result: " + resultDFS + " (Time: " + dfsTime + "ms)");
                System.out.println("All methods agree: " +
                        (resultBFS == resultOptimized && resultOptimized == resultUltraOptimized && resultUltraOptimized == resultDFS));
            }
        }
}
