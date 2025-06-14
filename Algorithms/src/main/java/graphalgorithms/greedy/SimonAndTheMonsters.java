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

public class SimonAndTheMonsters {
    static class Pair {
        int points;
        int timer;

        Pair(int points, int timer) {
            this.points = points;
            this.timer = timer;
        }
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public static int solve(int[] points, int[] timer, int n) {
        if (n == 0) {
            return 0;
        }

        int maxDeadline = 0;
        for (int t : timer) {
            if (t > maxDeadline) {
                maxDeadline = t;
            }
        }

        int[] parent = new int[maxDeadline + 1];
        for (int i = 0; i <= maxDeadline; i++) {
            parent[i] = i;
        }

        java.util.List<Pair> monsters = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            monsters.add(new Pair(points[i], timer[i]));
        }

        java.util.Collections.sort(monsters, (a, b) -> b.points - a.points);

        int totalPoints = 0;
        for (Pair monster : monsters) {
            int d = monster.timer;
            if (d > maxDeadline) {
                d = maxDeadline;
            }
            int slot = find(parent, d);
            if (slot > 0) {
                totalPoints += monster.points;
                parent[slot] = find(parent, slot - 1);
            }
        }

        return totalPoints;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] points = new int[n];
        int[] timer = new int[n];

        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            timer[i] = scanner.nextInt();
        }

        int result = solve(points, timer, n);
        System.out.println(result);
    }
}
