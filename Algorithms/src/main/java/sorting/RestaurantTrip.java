package sorting;

import java.util.Scanner;

public class RestaurantTrip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Friends: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        System.out.println("Enter the Food: ");
        for (int i = 0; i < n; i++) {
            a[i]=sc.nextInt();
        }
        System.out.println("Enter the Amount:");
        for (int i = 0; i < n; i++) {
            b[i]=sc.nextInt();
        }
        sc.close(); // Close the scanner to prevent resource leak

        maximumDayVisitToRestaurant(a,b);
    }

    private static void maximumDayVisitToRestaurant(int[] a,int[] b) {
        int n=a.length;
        for(int i=0;i<n;i++){
            b[i]=b[i]-a[i];
        }
        sort(b);
        int i=0,j=n-1,ans=0;
        while(i<j){
            if(b[i]+b[j]>0){
                ans++;
                i++;
                j--;
            }
            else {
                i++;
            }
        }
        System.out.println(ans);

    }

    private static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
