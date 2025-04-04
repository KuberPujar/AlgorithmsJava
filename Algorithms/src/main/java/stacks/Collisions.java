package stacks;
/*
Collisions
We are given an integer array asteroids of size N representing asteroids in a row. For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed. Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are of the same size, both will explode. Two asteroids moving in the same direction will never meet.

Input Format:

The first line contains an integer N (the size of the array).
The second line contains N space-separated integers representing the array asteroids.
Output Format:

Return the state of the asteroids after all collisions.
Sample Input 1:

3
3 5 -3
Sample Output 1:

3 5
Explanation:

The asteroid 5 and -3 collide resulting in 5. The 5 and 3 never collide.

Sample Input 2:

2
10 -10
Sample Output 2:

[]
Explanation:

The asteroid 10 and -10 collide resulting in both exploding.

Constraints:

(1 <= N <= 10^5)
(-1000 <= asteroids[i] <= 1000)
(asteroids[i]!=0)
Note: The function should return the result.
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Collisions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] asteroids = new int[N];

        for (int i = 0; i < N; i++) {
            asteroids[i] = scanner.nextInt();
        }

        int[] result = asteroidCollision(asteroids);
        System.out.println(Arrays.toString(result));
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            if (stack.isEmpty() || asteroid > 0) {
                stack.push(asteroid);
            } else {
                while (true) {
                    int top = stack.peek();
                    if (top < 0) {
                        stack.push(asteroid);
                        break;
                    }
                    if (top == -asteroid) {
                        stack.pop();
                        break;
                    }
                    if (top > -asteroid) {
                        break;
                    }
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(asteroid);
                        break;
                    }
                }
            }
        }

        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
}