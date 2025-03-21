package sorting;

import java.util.Scanner;

public class SortThePermutation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Array Size:");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for(int i=0;i<n;i++){
            arr[i] = scanner.nextInt();
        }
        System.out.println(sortThePermutation(arr));
    }

    private static boolean sortThePermutation(int[] arr){
        boolean isSorted = false;
        for(int i=1;i<arr.length-2;i++){
            if(arr[i]>arr[i-1] && arr[i]>arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                isSorted=true;
            }
        }
        return isSorted;
    }
}
