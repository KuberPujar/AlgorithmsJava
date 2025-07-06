package heaps.stackbased;

import java.util.Stack;

/*
There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [positioni, speedi] represents:

positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that positioni < positioni+1.
speedi is the initial speed of the ith car in meters per second.
For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.

Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.



Example 1:

Input: cars = [[1,2],[2,1],[4,3],[7,2]]
Output: [1.00000,-1.00000,3.00000,-1.00000]
Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.
Example 2:

Input: cars = [[3,4],[5,4],[6,3],[9,1]]
Output: [2.00000,1.00000,1.50000,-1.00000]


Constraints:

1 <= cars.length <= 105
1 <= positioni, speedi <= 106
positioni < positioni+1
 */
public class CarFleet_II {

    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] result = new double[n];
        Stack<Integer> stack = new Stack<>();

        // Initialize all collision times to -1 (no collision)
        for (int i = 0; i < n; i++) {
            result[i] = -1.0;
        }

        // Process cars from right to left
        for (int i = n - 1; i >= 0; i--) {
            int position = cars[i][0];
            int speed = cars[i][1];

            // Check if current car can collide with cars in stack
            while (!stack.isEmpty()) {
                int j = stack.peek();
                int nextPosition = cars[j][0];
                int nextSpeed = cars[j][1];

                // Current car is faster than next car (potential collision)
                if (speed > nextSpeed) {
                    double collisionTime = (double)(nextPosition - position) / (speed - nextSpeed);

                    // If next car hasn't collided yet, or we collide before its collision
                    if (result[j] == -1 || collisionTime <= result[j]) {
                        result[i] = collisionTime;
                        break;
                    }
                }

                // Remove cars we'll never collide with
                stack.pop();
            }

            // Push current car to stack
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        CarFleet_II carFleetII = new CarFleet_II();
        int[][] cars1 = {{1, 2}, {2, 1}, {4, 3}, {7, 2}};
        double[] result1 = carFleetII.getCollisionTimes(cars1);
        for (double time : result1) {
            System.out.printf("%.5f ", time);
        }
        System.out.println();

        int[][] cars2 = {{3, 4}, {5, 4}, {6, 3}, {9, 1}};
        double[] result2 = carFleetII.getCollisionTimes(cars2);
        for (double time : result2) {
            System.out.printf("%.5f ", time);
        }
    }
}
