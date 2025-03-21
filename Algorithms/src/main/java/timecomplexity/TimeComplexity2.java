package timecomplexity;

import java.util.Scanner;

public class TimeComplexity2 {
    private static int count=0;
    private static void print(){
        count++;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of execution: ");
        int n = scanner.nextInt();
        for(int i=n;i>0;i/=2){
            for(int j=0;j<=i;j++){
                    print();
            }
        }
        System.out.println(count);
    }
}
