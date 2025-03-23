package sorting;

import java.util.Scanner;

/*
Anish, Binish and Vivek are playing a game. They have n piles of stones where (n%3=0). In each turn you can select three piles from the total piles. Anish takes the maximum, then Vivek takes the second maximum and then Binish takes the third one.

Help Vivek get the maximum number of stones possible and return that number.
 */
public class MaximumStones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close(); // Close the scanner to prevent resource leak
        maximumStones(n, a);
    }

    private static void maximumStones(int n, int[] stones) {
        int sum = 0;
        sort(stones);
        for (int i = 0; i < n; i += 3) {
            int[] temp = new int[3];
            temp[0] = stones[i];
            temp[1] = stones[i + 1];
            temp[2] = stones[i + 2];
            sortSubArray(temp);
            sum += temp[1];
        }
        System.out.println(sum);
    }

    private static void sort(int[] a) {
        for (int i = 0; i < 3; i++) {
                int minIndex = i;
                if (a[i+1] < a[minIndex]) {
                    minIndex=i+1;
                    int temp=a[i];
                    a[i]=a[minIndex];
                    a[minIndex]=temp;
            }
        }
    }
    private static int[] sortSubArray(int[] temp){
        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp.length-i-1;j++){
                if(temp[j]<temp[j+1]){
                    int temp1=temp[j];
                    temp[j]=temp[j+1];
                    temp[j+1]=temp1;
                }
            }
        }
       return temp;
    }

}

