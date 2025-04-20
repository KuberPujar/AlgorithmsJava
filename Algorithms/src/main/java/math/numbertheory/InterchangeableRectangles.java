package math.numbertheory;

import java.util.HashMap;
import java.util.Map;

/*
You are given n rectangles represented by a 0-indexed 2D integer array rectangles, where rectangles[i] = [widthi, heighti] denotes the width and height of the ith rectangle.

Two rectangles i and j (i < j) are considered interchangeable if they have the same width-to-height ratio. More formally, two rectangles are interchangeable if widthi/heighti == widthj/heightj (using decimal division, not integer division).

Return the number of pairs of interchangeable rectangles in rectangles.



Example 1:

Input: rectangles = [[4,8],[3,6],[10,20],[15,30]]
Output: 6
Explanation: The following are the interchangeable pairs of rectangles by index (0-indexed):
- Rectangle 0 with rectangle 1: 4/8 == 3/6.
- Rectangle 0 with rectangle 2: 4/8 == 10/20.
- Rectangle 0 with rectangle 3: 4/8 == 15/30.
- Rectangle 1 with rectangle 2: 3/6 == 10/20.
- Rectangle 1 with rectangle 3: 3/6 == 15/30.
- Rectangle 2 with rectangle 3: 10/20 == 15/30.
Example 2:

Input: rectangles = [[4,5],[7,8]]
Output: 0
Explanation: There are no interchangeable pairs of rectangles.


Constraints:

n == rectangles.length
1 <= n <= 105
rectangles[i].length == 2
1 <= widthi, heighti <= 105
 */
public class InterchangeableRectangles {
    public static void main(String[] args) {
        int[][] rectangles = {{4, 8}, {3, 6}, {10, 20}, {15, 30}};
        System.out.println(interchangeableRectangles(rectangles));

        int[][] rectangles2 = {{4, 5}, {7, 8}};
        System.out.println(interchangeableRectangles(rectangles2));
    }

    private static long interchangeableRectangles(int[][] rectangles) {
        Map<String, Long> ratioCount = new HashMap<>();
        long count = 0;

        for (int[] rectangle : rectangles) {
            int width = rectangle[0];
            int height = rectangle[1];
            int gcd = gcd(width, height);
            String ratio = (width / gcd) + ":" + (height / gcd);
            ratioCount.put(ratio, ratioCount.getOrDefault(ratio, 0L) + 1);
        }

        for (long freq : ratioCount.values()) {
            count += (freq * (freq - 1)) / 2;
        }

        return count;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
