package dynamicprmng;
/*
Getting in faang companies
A learner at HeyCoach wants to grab a job in a FAANG company, so they start following a schedule (learning DSA, practicing questions, and learning CS fundamentals).
Each day, they perform one of these three activities and can’t perform the same activity on two consecutive days. Each activity has some merit points on each day.
These merit points will decide whether they are eligible to get into a FAANG company or not. The learner wants to improve everything to get a good job as early as possible.
Can you help the learner in finding out the maximum merit points they can earn?

Note: Try to see if you can solve using brute force first then optimize your solution

Input Format:

The first line contains a single integer ( n ) denoting the number of days.
The next ( n ) lines contain three space-separated integers denoting the merit values of each of the three activities on that day.
Output Format:

Return the maximum amount of merit points achievable.
Sample Input 1:

3
1 2 5
3 1 1
3 3 3
Sample Output 1:

11
Explanation 1:

On day 1, the learner can choose activity 3 (5 points). On day 2, they can choose activity 1 (3 points). On day 3, they can choose activity 3 (3 points). Total merit points = 5 + 3 + 3 = 11.

Sample Input 2:

4
1 2 3
3 2 1
1 2 3
3 2 1
Sample Output 2:

12
Explanation 2:

On day 1, the learner can choose activity 3 (3 points). On day 2, they can choose activity 1 (3 points). On day 3, they can choose activity 3 (3 points). On day 4, they can choose activity 1 (3 points). Total merit points = 3 + 3 + 3 + 3 = 12.

Constraints:

( 1 <= N <= 10000 )
( 1 <= Value of PointsArray <= 100 )
Note: The function should return the result.
 */
/*
Brute force solution
public class FAANGMeritPoints {

    // Brute force recursive solution
    public static int maxMeritPoints(int[][] points) {
        return helper(points, 0, -1);
    }

    private static int helper(int[][] points, int day, int lastActivity) {
        if (day == points.length) {
            return 0;
        }

        int max = 0;
        for (int activity = 0; activity < 3; activity++) {
            if (activity != lastActivity) {
                int current = points[day][activity] + helper(points, day + 1, activity);
                max = Math.max(max, current);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] points1 = {
            {1, 2, 5},
            {3, 1, 1},
            {3, 3, 3}
        };
        System.out.println(maxMeritPoints(points1)); // Output: 11

        int[][] points2 = {
            {1, 2, 3},
            {3, 2, 1},
            {1, 2, 3},
            {3, 2, 1}
        };
        System.out.println(maxMeritPoints(points2)); // Output: 12
    }
}
 */
public class FAANGMeritPoints {

    // Optimized DP solution
    public static int maxMeritPoints(int[][] points) {
        if (points.length == 0) return 0;

        int n = points.length;
        int[][] dp = new int[n][3];

        // Initialize first day
        dp[0][0] = points[0][0];
        dp[0][1] = points[0][1];
        dp[0][2] = points[0][2];

        for (int day = 1; day < n; day++) {
            // For activity 0 on current day, can come from activity 1 or 2 of previous day
            dp[day][0] = points[day][0] + Math.max(dp[day-1][1], dp[day-1][2]);

            // For activity 1 on current day, can come from activity 0 or 2 of previous day
            dp[day][1] = points[day][1] + Math.max(dp[day-1][0], dp[day-1][2]);

            // For activity 2 on current day, can come from activity 0 or 1 of previous day
            dp[day][2] = points[day][2] + Math.max(dp[day-1][0], dp[day-1][1]);
        }

        return Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2]));
    }

    // Space optimized DP solution (O(1) space)
    public static int maxMeritPointsOptimized(int[][] points) {
        if (points.length == 0) return 0;

        int prev0 = points[0][0];
        int prev1 = points[0][1];
        int prev2 = points[0][2];

        for (int day = 1; day < points.length; day++) {
            int curr0 = points[day][0] + Math.max(prev1, prev2);
            int curr1 = points[day][1] + Math.max(prev0, prev2);
            int curr2 = points[day][2] + Math.max(prev0, prev1);

            prev0 = curr0;
            prev1 = curr1;
            prev2 = curr2;
        }

        return Math.max(prev0, Math.max(prev1, prev2));
    }

    public static void main(String[] args) {
        int[][] points1 = {
                {1, 2, 5},
                {3, 1, 1},
                {3, 3, 3}
        };
        System.out.println(maxMeritPoints(points1)); // Output: 11
        System.out.println(maxMeritPointsOptimized(points1)); // Output: 11

        int[][] points2 = {
                {1, 2, 3},
                {3, 2, 1},
                {1, 2, 3},
                {3, 2, 1}
        };
        System.out.println(maxMeritPoints(points2)); // Output: 12
        System.out.println(maxMeritPointsOptimized(points2)); // Output: 12
    }
}