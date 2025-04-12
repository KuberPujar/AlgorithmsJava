package recursion;

import java.util.Scanner;

/*
Fair Distribution of Cookies
You have an array called cookies, where cookies[i] represents the number of cookies in the ith bag. Additionally, you're given an integer k, which indicates the number of children
to distribute all the bags of cookies to. Each bag's cookies must be given entirely to one child; they can't be divided.

The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.

Return the minimum unfairness of all distributions.

Input Format:

The first line contains two integers n and k representing the number of bags of cookies and the number of children, respectively.
The second line contains n space-separated integers representing the number of cookies in each bag.
Output Format:

A single integer denoting the minimum unfairness of all distributions.
Sample Input 1:

8 3
6 1 3 2 2 4 1 2
Sample Output 1:

7
Explanation:

One optimal distribution is [6,1], [3,2,2], and [4,1,2].

The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
The unfairness of the distribution is max(7,7,7) = 7. It can be shown that there is no distribution with an unfairness less than 7.

Sample Input 2:

5 2
8 15 10 20 8
Sample Output 2:

31
Explanation:

One optimal distribution is [8,15,8] and [10,20].

The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
The unfairness of the distribution is max(31,30) = 31. It can be shown that there is no distribution with an unfairness less than 31.

Constraints:

(2 <= cookies.length <= 8)
(1 <= cookies[i] <= 10^5)
(2 <= k <= cookies.length)
Note: The function should return the result.
 */
public class FairDistributionOfCookies {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] cookies = new int[n];
        for (int i = 0; i < n; i++) {
            cookies[i] = scanner.nextInt();
        }
        System.out.println(minUnfairness(cookies, k));
    }

    private static int minUnfairness(int[] cookies, int k) {
        int n = cookies.length;
        int[] child = new int[k];
        return distributeCookies(cookies, 0, child, k);
    }

    private static int distributeCookies(int[] cookies, int index, int[] child, int k) {
        if (index == cookies.length) {
            int max = 0;
            for (int i = 0; i < k; i++) {
                max = Math.max(max, child[i]);
            }
            return max;
        }

        int minUnfairness = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            child[i] += cookies[index];
            minUnfairness = Math.min(minUnfairness, distributeCookies(cookies, index + 1, child, k));
            child[i] -= cookies[index];
        }

        return minUnfairness;
    }
}
