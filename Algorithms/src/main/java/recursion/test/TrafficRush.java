package recursion.test;
/*
write  a java code for following scenario Traffic Rush
You're a traffic engineer stuck in a rush hour nightmare on an infinitely long highway. But it's not your typical gridlock – this road has only cars moving in either direction (left or right) or simply stalled out! Your job is to predict the chaos: how many collisions will occur before the dust settles?

Given a string direction (length <= 10^5) where each character is 'L' (left), 'R' (right), or 'S' (stopped), predict the total number of collisions that will happen on the road. Collisions occur when:

Two moving cars (one left, one right) meet – a head-on smash! Increases collisions by 2. A moving car slams into a stopped car – rear-end ouch! Increases collisions by 1. Remember, once a car collides, it stops and becomes an obstacle for future crashes. Cars never change direction or speed.

Example 1:
Scenario: "RLRSLL"

Collisions: 5 (2 for cars 0/1, 1 for cars 2/3, 1 for cars 3/4, 1 for cars 4/5)
Example 2:
Scenario: "LLRR"

Collisions: 0 (no head-on or rear-end collisions)
Input:
A single line containing the string directions, where each character can be:
'L': denotes a car moving left.
'R': denotes a car moving right.
'S': denotes a car staying stationary.
Output:
A single integer representing the total number of collisions that will occur on the road.
Constraints:
The length of directions (n) must be between 1 and 10^5 (inclusive).
Each character in directions must be either 'L', 'R', or 'S'.
 */
public class TrafficRush {

    private int collisions = 0;

    public int countCollisions(String directions) {
        if (directions.length() <= 1) {
            return 0;
        }

        // Process the first collision we find
        for (int i = 0; i < directions.length() - 1; i++) {
            char current = directions.charAt(i);
            char next = directions.charAt(i + 1);

            // Head-on collision
            if (current == 'R' && next == 'L') {
                collisions += 2;
                // Replace both with 'S' and process the new string
                String newDirections = directions.substring(0, i) + "SS" + directions.substring(i + 2);
                return countCollisions(newDirections);
            }
            // Rear-end collision (R into S)
            else if (current == 'R' && next == 'S') {
                collisions += 1;
                // Replace R with S and process
                String newDirections = directions.substring(0, i) + "S" + directions.substring(i + 1);
                return countCollisions(newDirections);
            }
            // Rear-end collision (L into S from right)
            else if (current == 'S' && next == 'L') {
                collisions += 1;
                // Replace L with S and process
                String newDirections = directions.substring(0, i + 1) + "S" + directions.substring(i + 2);
                return countCollisions(newDirections);
            }
        }

        // No more collisions found
        return collisions;
    }

    public static void main(String[] args) {
        TrafficRush predictor = new TrafficRush();

        // Example 1
        String scenario1 = "RLRSLL";
        System.out.println("Scenario: " + scenario1);
        System.out.println("Collisions: " + predictor.countCollisions(scenario1));
        predictor.collisions = 0; // Reset for next test

        // Example 2
        String scenario2 = "LLRR";
        System.out.println("Scenario: " + scenario2);
        System.out.println("Collisions: " + predictor.countCollisions(scenario2));
        predictor.collisions = 0;

        // Additional test case
        String scenario3 = "SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR";
        System.out.println("Scenario: " + scenario3);
        System.out.println("Collisions: " + predictor.countCollisions(scenario3));
    }
}