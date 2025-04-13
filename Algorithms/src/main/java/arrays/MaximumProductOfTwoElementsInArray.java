package arrays;
/*
Maximum Product of Two Elements in an Array:

Given the array of integers nums, you will choose two different indices i and j of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).


Example 1:

Input: nums = [3,4,5,2]
Output: 12
Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12.
Example 2:

Input: nums = [1,5,4,5]
Output: 16
Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.
Example 3:

Input: nums = [3,7]
Output: 12


Constraints:

2 <= nums.length <= 500
1 <= nums[i] <= 10^3
 */
import java.util.Scanner;

public class MaximumProductOfTwoElementsInArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array:");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        mergeSort(arr, 0, n - 1);

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            if(arr[i]>max1){
                max2=max1;
                max1=arr[i];
            }else if(arr[i]>max2){
                max2=arr[i];
            }
        }
        System.out.println("Maximum product of two elements in the array is: " + (max1 - 1) * (max2 - 1));
    }

    private static void mergeSort(int[] arr, int left, int right){
        if(left<right){
            int mid=left+(right-left)/2;
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right){
        int n1=mid-left+1;
        int n2=right-mid;

        int[] L=new int[n1];
        int[] R=new int[n2];

        for(int i=0;i<n1;i++){
            L[i]=arr[left+i];
        }
        for(int j=0;j<n2;j++){
            R[j]=arr[mid+1+j];
        }

        int i=0,j=0,k=left;

        while(i<n1 && j<n2){
            if(L[i]<=R[j]){
                arr[k++]=L[i++];
            }else{
                arr[k++]=R[j++];
            }
        }

        while(i<n1){
            arr[k++]=L[i++];
        }

        while(j<n2){
            arr[k++]=R[j++];
        }
    }
}
