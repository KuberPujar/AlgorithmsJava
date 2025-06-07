package graphalgorithms.greedy;
/*
Simon and the monsters
Simon is playing a game where he has a sword a blow of which can kill any monster on the screen. There are 'n' monsters on the screen and every monster when killed provides a[i], a being the array of points and i being the ith monster on the screen.
However, there is a twist, each monster must be killed on or before a time associated with that particular monster, if killed after that, it will yield zero points. Help Simon develop a strategy to gain the maximum amount of points (Return the maximum amount of points possible).

Input Format:

A single integer n denoting the number of monsters.
The second line represents an array containing the points that the monsters yield when killed.
The third line represents an array containing the time until which each monster must be killed to get points.
Output Format:

Return a single integer representing the maximum achievable points.

Sample Input:

3
50 80 90
1 2 2
Sample Output:

170

Explanation:

There are 3 monsters with point values [50, 80, 90] and time constraints [1, 2, 2].

To maximize points, Simon should kill:

Monster 2 (80) at time 1,
Then Monster 3 (90) attime 2.
The maximum points achievable is 170 (if he kills Monster 2 and Monster 3).
Sample Input:

5
100 19 27 25 15
2 1 2 1 3
Sample Output:

142

Explanation:

Kill Monster 1 (100 points) at time 1.
Kill Monster 3 (27 points) at time 2.
Kill Monster 5 (15 points) at time 3.

The total achievable points by killing Monsters 1, 3, and 5 is 142.
Constraints:

1<=n<=1000

1<=a[i]<=10^4

Note:The function should return the result. The driver code will handle printing the output.
 */

import java.util.Arrays;
import java.util.Scanner;

class Monster {
    int points;
    int deadline;
    int index;

    Monster(int points, int deadline, int index) {
        this.points = points;
        this.deadline = deadline;
        this.index = index;
    }
}

public class SimonAndTheMonsters {
    public static int maxPoints(int n, int[] points, int[] deadlines) {
        // Create array of monsters
        Monster[] monsters = new Monster[n];
        for (int i = 0; i < n; i++) {
            monsters[i] = new Monster(points[i], deadlines[i], i);
        }

        // Sort monsters by points in descending order (greedy choice)
        Arrays.sort(monsters, (a, b) -> Integer.compare(b.points, a.points));

        // Find maximum deadline to determine time slots
        int maxDeadline = 0;
        for (int deadline : deadlines) {
            maxDeadline = Math.max(maxDeadline, deadline);
        }

        // Array to track which time slots are occupied
        boolean[] timeSlots = new boolean[maxDeadline + 1];
        int totalPoints = 0;

        // Process monsters in order of decreasing points
        for (Monster monster : monsters) {
            // Try to schedule this monster at the latest possible time before its deadline
            for (int time = monster.deadline; time >= 1; time--) {
                if (!timeSlots[time]) {
                    // Schedule this monster at this time
                    timeSlots[time] = true;
                    totalPoints += monster.points;
                    break;
                }
            }
        }

        return totalPoints;
    }

    // Alternative approach using Union-Find for better efficiency (optional)
    public static int maxPointsOptimized(int n, int[] points, int[] deadlines) {
        Monster[] monsters = new Monster[n];
        for (int i = 0; i < n; i++) {
            monsters[i] = new Monster(points[i], deadlines[i], i);
        }

        // Sort by points in descending order
        Arrays.sort(monsters, (a, b) -> Integer.compare(b.points, a.points));

        int maxDeadline = Arrays.stream(deadlines).max().orElse(0);

        // Union-Find to efficiently find next available slot
        int[] parent = new int[maxDeadline + 2];
        for (int i = 0; i <= maxDeadline + 1; i++) {
            parent[i] = i;
        }

        int totalPoints = 0;

        for (Monster monster : monsters) {
            int availableSlot = find(parent, monster.deadline);
            if (availableSlot > 0) {
                totalPoints += monster.points;
                // Union with next slot (mark this slot as used)
                parent[availableSlot] = availableSlot - 1;
            }
        }

        return totalPoints;
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    // Method to show the detailed solution
    public static void showDetailedSolution(int n, int[] points, int[] deadlines) {
        Monster[] monsters = new Monster[n];
        for (int i = 0; i < n; i++) {
            monsters[i] = new Monster(points[i], deadlines[i], i + 1);
        }

        Arrays.sort(monsters, (a, b) -> Integer.compare(b.points, a.points));

        int maxDeadline = Arrays.stream(deadlines).max().orElse(0);
        boolean[] timeSlots = new boolean[maxDeadline + 1];
        int totalPoints = 0;

        System.out.println("Greedy selection process:");
        System.out.println("Monsters sorted by points (descending):");

        for (Monster monster : monsters) {
            System.out.printf("Monster %d: %d points, deadline %d\n",
                    monster.index, monster.points, monster.deadline);
        }

        System.out.println("\nScheduling process:");

        for (Monster monster : monsters) {
            boolean scheduled = false;
            for (int time = monster.deadline; time >= 1; time--) {
                if (!timeSlots[time]) {
                    timeSlots[time] = true;
                    totalPoints += monster.points;
                    System.out.printf("Kill Monster %d (%d points) at time %d\n",
                            monster.index, monster.points, time);
                    scheduled = true;
                    break;
                }
            }
            if (!scheduled) {
                System.out.printf("Cannot schedule Monster %d (%d points) within deadline %d\n",
                        monster.index, monster.points, monster.deadline);
            }
        }

        System.out.println("Total points: " + totalPoints);
    }

    // Driver code for testing
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Test case 1
        System.out.println("=== Test Case 1 ===");
        int n1 = 3;
        int[] points1 = {50, 80, 90};
        int[] deadlines1 = {1, 2, 2};
        System.out.println("Points: " + Arrays.toString(points1));
        System.out.println("Deadlines: " + Arrays.toString(deadlines1));
        System.out.println("Max Points: " + maxPoints(n1, points1, deadlines1));
        System.out.println();
        showDetailedSolution(n1, points1, deadlines1);
        System.out.println();

        // Test case 2
        System.out.println("=== Test Case 2 ===");
        int n2 = 5;
        int[] points2 = {100, 19, 27, 25, 15};
        int[] deadlines2 = {2, 1, 2, 1, 3};
        System.out.println("Points: " + Arrays.toString(points2));
        System.out.println("Deadlines: " + Arrays.toString(deadlines2));
        System.out.println("Max Points: " + maxPoints(n2, points2, deadlines2));
        System.out.println();
        showDetailedSolution(n2, points2, deadlines2);
        System.out.println();

        // Interactive input
        System.out.println("=== Interactive Mode ===");
        System.out.print("Enter number of monsters: ");
        int n = sc.nextInt();

        int[] points = new int[n];
        int[] deadlines = new int[n];

        System.out.println("Enter points for each monster:");
        for (int i = 0; i < n; i++) {
            points[i] = sc.nextInt();
        }

        System.out.println("Enter deadlines for each monster:");
        for (int i = 0; i < n; i++) {
            deadlines[i] = sc.nextInt();
        }

        System.out.println("Maximum points achievable: " + maxPoints(n, points, deadlines));

        sc.close();
    }
}
