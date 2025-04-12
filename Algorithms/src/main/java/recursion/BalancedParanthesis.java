package recursion;
/*
Balanced Paranthesis
Given a number n representing a pair of parentheses (closed and open), your task is to print the total number of balanced parenthesis pairs possible. A balanced parenthesis refers to the proper nesting and matching of open and close parentheses in an expression. An expression is considered balanced if every opening parenthesis has a corresponding closing parenthesis.

For Example:

Balanced: (), ((())), ()()(), (())()
Unbalanced: (, ((), )()(, (((
Input Format:

The first line contains the integer n denoting the number of pairs of parentheses.
Output Format:

A single integer denoting the total number of balanced parenthesis pairs possible.
Sample Input 1:

3
Sample Output 1:

5
Explanation:

The possible balanced parenthesis pairs are: ((())), ()()(), (())(), ()((), (()()).

Constraints:

(1 <= n <= 12)
Note: The function should return the result.
 */
import java.util.Scanner;

public class BalancedParanthesis {
    /*
    To solve this problem using recursion, we can leverage the properties of Catalan numbers,
    which count the number of valid balanced parentheses combinations for n pairs.
     The recursive formula for Catalan numbers is:
         n−1
    C(n)= ∑  C(i)×C(n−i−1)
         i=0
    where C(0)=1.

Approaches:

Base Case: If n is 0 or 1, return 1 because there's exactly one way to have zero or
one pair of parentheses (empty string or "()").
Recursive Case: For each n, sum the products of Catalan numbers for
 all possible splits of n-1 pairs into left and right subtrees.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(calculateBalancedParanthesis(n));
    }

    private static int calculateBalancedParanthesis(int n){
        if(n<=1){
            return 1;
        }

        int result=0;
       for(int i=0;i<n;i++){
           result+=calculateBalancedParanthesis(i) * calculateBalancedParanthesis(n-i-1);
       }
       return result;
    }
}
