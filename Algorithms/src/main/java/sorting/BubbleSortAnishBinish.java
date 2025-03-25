package sorting;

import java.util.Scanner;

public class BubbleSortAnishBinish {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("Enter the elements of the Anish elements: ");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println("Enter the elements of the Binish elements: ");
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        sc.close(); // Close the scanner to prevent resource leak
        int anish=bubbleSort(a) ;
        int binish=bubbleSort(b);
        if(anish>binish) {
            System.out.println("Binish");
        }
        else if(anish<binish) {
            System.out.println("Anish");
        }
        else {
            System.out.println("Tie");
        }

    }

    private static int bubbleSort(int[] a) {
        int n=a.length;
        int swapCount=0;
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    swapCount+=1;
                }
            }
        }
        return swapCount;
    }
}
