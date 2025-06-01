package graphalgorithms.bfsdfs;

import java.util.*;

/*
Water & Jug Problem
You are given two jugs with capacities x liters and y liters, respectively. You have an infinite supply of water available. You must determine whether it is possible to measure exactly target liters of water using the following operations:

Fill either jug completely with water. Completely empty either jug. Pour water from one jug into another until the receiving jug is full, or the transferring jug is empty. Return whether you can measure exactly target liters.

Input Format:

Three integers x, y, and target are provided:
x: capacity of the first jug (1 ≤ x ≤ 10^6)
y: capacity of the second jug (1 ≤ y ≤ 10^6)
target: the desired amount of water (0 ≤ target ≤ 10^6).
Output Format:

Return true if you can measure exactly target liters using the two jugs, otherwise return false.

Example 1:

Input 1:

x = 3, y = 5
target = 4
Output 1:

true
Explanation:

Fill the 5-liter jug.
Transfer 3 liters from the 5-liter jug to the 3-liter jug.
Now the 5-liter jug has 2 liters. Empty the 3-liter jug and transfer the 2 liters from the 5-liter jug to the 3-liter jug.
Fill the 5-liter jug again, and transfer 1 liter to the 3-liter jug. The 5-liter jug will now have exactly 4 liters.
Example 2:

Input 2:

 x = 2, y = 6
target = 5
Output 2:

false
Explanation
You have two jugs with capacities of 2 liters and 6 liters.
You need to measure exactly 5 liters, but the problem here is that there’s no way to achieve this exact amount using the given operations.
The key idea is that you can measure target liters of water if and only if target is a multiple of the greatest common divisor (GCD) of the two jug capacities and target is less than or equal to the sum of the two capacities (x + y).
Constraints
1 ≤ x ≤ 10^6: Capacity of the first jug.
1 ≤ y ≤ 10^6: Capacity of the second jug.
0 ≤ target ≤ 10^6: The amount of water to be measured.
Note: The function should print the result.
 */
public class WaterANdJug {
    // BFS Solution - Explores all possible states
    public static boolean canMeasureWaterBFS(int x, int y, int target) {
        // Edge case
        if (target > x + y) {
            return false;
        }

        if (target == 0) {
            return true;
        }

        // BFS to explore all possible states
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Start with both jugs empty
        queue.offer(new int[]{0, 0}); // {jug1_water, jug2_water}
        visited.add("0,0");

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int jug1 = current[0];
            int jug2 = current[1];

            // Check if we've reached the target
            if (jug1 == target || jug2 == target || jug1 + jug2 == target) {
                return true;
            }

            // Generate all possible next states
            List<int[]> nextStates = getNextStates(jug1, jug2, x, y);

            for (int[] nextState : nextStates) {
                String stateKey = nextState[0] + "," + nextState[1];
                if (!visited.contains(stateKey)) {
                    visited.add(stateKey);
                    queue.offer(nextState);
                }
            }
        }

        return false;
    }

    // Generate all possible next states from current state
    private static List<int[]> getNextStates(int jug1, int jug2, int x, int y) {
        List<int[]> states = new ArrayList<>();

        // 1. Fill jug1 completely
        states.add(new int[]{x, jug2});

        // 2. Fill jug2 completely
        states.add(new int[]{jug1, y});

        // 3. Empty jug1
        states.add(new int[]{0, jug2});

        // 4. Empty jug2
        states.add(new int[]{jug1, 0});

        // 5. Pour from jug1 to jug2
        int pourAmount1to2 = Math.min(jug1, y - jug2);
        states.add(new int[]{jug1 - pourAmount1to2, jug2 + pourAmount1to2});

        // 6. Pour from jug2 to jug1
        int pourAmount2to1 = Math.min(jug2, x - jug1);
        states.add(new int[]{jug1 + pourAmount2to1, jug2 - pourAmount2to1});

        return states;
    }

    // Mathematical Solution - Using GCD property (More Efficient)
    public static boolean canMeasureWaterMath(int x, int y, int target) {
        // Edge cases
        if (target > x + y) {
            return false;
        }

        if (target == 0) {
            return true;
        }

        if (x == 0) {
            return target == y;
        }

        if (y == 0) {
            return target == x;
        }

        // Mathematical approach: target must be divisible by GCD(x, y)
        // and target must be <= x + y
        int gcd = gcd(x, y);
        return target % gcd == 0;
    }

    // Helper method to calculate GCD
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // DFS Solution (Alternative approach)
    public static boolean canMeasureWaterDFS(int x, int y, int target) {
        if (target > x + y) {
            return false;
        }

        if (target == 0) {
            return true;
        }

        Set<String> visited = new HashSet<>();
        return dfs(0, 0, x, y, target, visited);
    }

    private static boolean dfs(int jug1, int jug2, int x, int y, int target, Set<String> visited) {
        // Check if target is reached
        if (jug1 == target || jug2 == target || jug1 + jug2 == target) {
            return true;
        }

        String state = jug1 + "," + jug2;
        if (visited.contains(state)) {
            return false;
        }
        visited.add(state);

        // Try all possible operations
        return dfs(x, jug2, x, y, target, visited) ||          // Fill jug1
                dfs(jug1, y, x, y, target, visited) ||          // Fill jug2
                dfs(0, jug2, x, y, target, visited) ||          // Empty jug1
                dfs(jug1, 0, x, y, target, visited) ||          // Empty jug2
                dfs(jug1 - Math.min(jug1, y - jug2),
                        jug2 + Math.min(jug1, y - jug2), x, y, target, visited) || // Pour jug1 to jug2
                dfs(jug1 + Math.min(jug2, x - jug1),
                        jug2 - Math.min(jug2, x - jug1), x, y, target, visited);   // Pour jug2 to jug1
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        testCase(3, 5, 4, true);   // Example 1
        testCase(2, 6, 5, false);  // Example 2
        testCase(1, 2, 3, true);   // Additional test
        testCase(3, 5, 8, true);   // Additional test
        testCase(2, 4, 3, false);  // Additional test
        testCase(0, 0, 0, true);   // Edge case
    }

    private static void testCase(int x, int y, int target, boolean expected) {
        System.out.println("\n=== Test Case ===");
        System.out.println("Jug capacities: " + x + "L, " + y + "L");
        System.out.println("Target: " + target + "L");
        System.out.println("Expected: " + expected);

        boolean resultBFS = canMeasureWaterBFS(x, y, target);
        boolean resultMath = canMeasureWaterMath(x, y, target);
        boolean resultDFS = canMeasureWaterDFS(x, y, target);

        System.out.println("BFS Result: " + resultBFS);
        System.out.println("Math Result: " + resultMath);
        System.out.println("DFS Result: " + resultDFS);
        System.out.println("All methods agree: " + (resultBFS == resultMath && resultMath == resultDFS));
        System.out.println("Test " + (resultBFS == expected ? "PASSED" : "FAILED"));
    }

    // Solution method as requested (prints the result)
    public static void solveProblem(int x, int y, int target) {
        boolean result = canMeasureWaterMath(x, y, target); // Using efficient mathematical approach
        System.out.println(result);
    }
}

// Step-by-step solution tracer (for educational purposes)
class WaterJugSolutionTracer {
    static class State {
        int jug1, jug2, step;
        String operation;

        State(int jug1, int jug2, int step, String operation) {
            this.jug1 = jug1;
            this.jug2 = jug2;
            this.step = step;
            this.operation = operation;
        }
    }

    public static void traceWaterJugSolution(int x, int y, int target) {
        if (target > x + y) {
            System.out.println("Impossible: target exceeds total capacity");
            return;
        }

        if (target == 0) {
            System.out.println("Solution: Start with both jugs empty");
            return;
        }

        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, State> parent = new HashMap<>();

        State start = new State(0, 0, 0, "Start with both jugs empty");
        queue.offer(start);
        visited.add("0,0");

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.jug1 == target || current.jug2 == target ||
                    current.jug1 + current.jug2 == target) {
                printSolutionPath(current, parent);
                return;
            }

            // Generate next states with operations
            addNextState(queue, visited, parent, current, x, current.jug2,
                    "Fill jug1 (" + x + "L)");
            addNextState(queue, visited, parent, current, current.jug1, y,
                    "Fill jug2 (" + y + "L)");
            addNextState(queue, visited, parent, current, 0, current.jug2,
                    "Empty jug1");
            addNextState(queue, visited, parent, current, current.jug1, 0,
                    "Empty jug2");

            // Pour operations
            int pour1to2 = Math.min(current.jug1, y - current.jug2);
            addNextState(queue, visited, parent, current,
                    current.jug1 - pour1to2, current.jug2 + pour1to2,
                    "Pour " + pour1to2 + "L from jug1 to jug2");

            int pour2to1 = Math.min(current.jug2, x - current.jug1);
            addNextState(queue, visited, parent, current,
                    current.jug1 + pour2to1, current.jug2 - pour2to1,
                    "Pour " + pour2to1 + "L from jug2 to jug1");
        }

        System.out.println("No solution exists");
    }

    private static void addNextState(Queue<State> queue, Set<String> visited,
                                     Map<String, State> parent, State current,
                                     int newJug1, int newJug2, String operation) {
        String stateKey = newJug1 + "," + newJug2;
        if (!visited.contains(stateKey)) {
            visited.add(stateKey);
            State newState = new State(newJug1, newJug2, current.step + 1, operation);
            queue.offer(newState);
            parent.put(stateKey, current);
        }
    }

    private static void printSolutionPath(State target, Map<String, State> parent) {
        List<State> path = new ArrayList<>();
        State current = target;

        while (current != null) {
            path.add(current);
            String key = current.jug1 + "," + current.jug2;
            current = parent.get(key);
        }

        Collections.reverse(path);

        System.out.println("Solution found in " + (path.size() - 1) + " steps:");
        for (int i = 0; i < path.size(); i++) {
            State state = path.get(i);
            System.out.println("Step " + i + ": " + state.operation +
                    " -> Jug1: " + state.jug1 + "L, Jug2: " + state.jug2 + "L");
        }
    }
}
