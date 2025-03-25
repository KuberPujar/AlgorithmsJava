package sorting;

import java.util.Scanner;

public class SpecailPairsOfScores {
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
        System.out.println(specialPairsOfScores(a));
    }

    private static int specialPairsOfScores(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(arr[i]>arr[j]){
                    System.out.println("("+arr[i]+","+arr[j]+")");
                    count++;
                }
            }
        }
        return count;
    }
}
