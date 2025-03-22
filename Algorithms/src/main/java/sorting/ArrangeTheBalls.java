package sorting;

import java.util.Scanner;

public class ArrangeTheBalls {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        char[] a = new char[n];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            a[i] = sc.next().charAt(0);
        }
        sc.close(); // Close the scanner to prevent resource leak
        print(arrangeBalls(a));
    }

    private static char[] arrangeBalls(char[] arr) {
        int saffronCount=0,whiteCount=0,greenCount=0;
        for(char c:arr){
            if(c=='S'){
                saffronCount++;
            }
            else if(c=='W'){
                whiteCount++;
            }
            else if(c=='G'){
                greenCount++;
            }
        }
int index=0;
        for(int i=0;i<saffronCount;i++){
            arr[index++]='S';
        }
        for(int i=0;i<whiteCount;i++){
            arr[index++]='W';
        }
        for(int i=0;i<greenCount;i++){
            arr[index++]='G';
        }
        return arr;
    }
    private static void print(char[] a){
        for (char aa: a){
            System.out.print(aa+" ");
        }
    }
}
