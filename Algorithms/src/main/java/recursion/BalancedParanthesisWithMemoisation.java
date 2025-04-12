package recursion;

import java.util.Scanner;

public class BalancedParanthesisWithMemoisation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(calculateBalancedParanthesis(n));
    }

    private static int calculateBalancedParanthesis(int n) {
        int[] memo = new int[n + 1];
        if(n<=1){
            return 1;
        }

        if(memo[n]!=0){
            return memo[n];
        }

        int result=0;
        for(int i=0;i<n;i++){
            result+=calculateBalancedParanthesis(i)* calculateBalancedParanthesis(n-i-1);
        }
        memo[n]=result;
        return result;
    }
}
