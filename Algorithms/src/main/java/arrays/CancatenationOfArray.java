package arrays;

import java.util.Scanner;

public class CancatenationOfArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();
        for(int nn:array(n,nums)){
            System.out.print(nn+" ");
        }
    }

    private static int[] arrayConcatenation(int n,int[] arr){
        int[] ans=new int[2*n];
        for(int i=0;i<n;i++){
            ans[i]=arr[i];
            ans[i+n]=arr[i+n];
        }
        return ans;
    }

    //efficient way
    private static int[] array(int n,int[] arr){
        int[] nums=new int[2*n];
        System.arraycopy(arr,0,nums,0,n);
        System.arraycopy(arr,0,nums,n,n);
        return nums;
    }
}
