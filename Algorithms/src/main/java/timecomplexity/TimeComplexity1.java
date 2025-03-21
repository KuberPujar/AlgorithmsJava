package timecomplexity;

import java.util.Scanner;

public class TimeComplexity1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of execution: ");
        int n = scanner.nextInt();
        int sum=0;
        for(int i=0;i<n;i++){
            for(int j=i;j>=0;j--){
                for(int k=i;k>=i-1;k--){
                    sum++;
                    print();
                }
            }
        }
        System.out.println(sum);
    }

    private static void print(){
    }
}
