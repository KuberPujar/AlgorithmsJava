package arrays;


import java.util.Scanner;

public class FindHighestAltitude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        int[] gain=new int[n];
        for(int i=0;i<n;i++){
            gain[i]=scanner.nextInt();
        }
        int result=largestAltitude(gain);
        System.out.println(result);
    }

    private static int largestAltitude(int[] gain){
        int max=0;
        int sum=0;
        for (int j : gain) {
            sum += j;
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
