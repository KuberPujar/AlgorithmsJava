package greaterpyramid.c1;
/*
Chocolate Distribution
Dholu and Bholu are great fans of odd numbers, that's why they want to divide the chocolate in such a way that each of the two parts weighs odd number of grams, at the same time it is not obligatory that the parts are equal. The boys are extremely excited to start eating their chocolates as soon as possible, that's why you should help them and find out, if they can divide the chocolate in the way they want. For sure, each of them should get a part of positive weight.

Input Format
The first (and the only) input line contains integer number c (2 < c ≤ 100) — the weight of the chocolate bought by the boys.

Output Format
Print YES, if the boys can divide the chocolate into two parts, each of them weighing odd number of grams; and NO in the opposite case

Sample Input
5
Sample Output
NO
Explanation
In this case, the weight of the chocolate is 5. The condition for dividing the chocolate into two parts, each weighing an odd number of grams, is not satisfied. Hence, the output is "NO."
Constraints
2< c ≤ 100:
The weight of the chocolate bought by the boys.
Note: The function is expected to print the result.
 */

import java.util.Scanner;

public class ChacolateDistribution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();

        // Check if the weight can be divided into two odd parts
        if (c > 2 && c % 2 == 0) {
            // The only way to divide into two odd parts is if the total is even and greater than 2
            // Because odd + odd = even, and both parts must be at least 1
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}