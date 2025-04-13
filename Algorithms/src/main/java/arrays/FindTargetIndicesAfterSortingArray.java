package arrays;
/*
Find Target Indices After Sorting Array:

You are given a 0-indexed integer array nums and a target element target.

A target index is an index i such that nums[i] == target.

Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.



Example 1:

Input: nums = [1,2,5,2,3], target = 2
Output: [1,2]
Explanation: After sorting, nums is [1,2,2,3,5].
The indices where nums[i] == 2 are 1 and 2.
Example 2:

Input: nums = [1,2,5,2,3], target = 3
Output: [3]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 3 is 3.
Example 3:

Input: nums = [1,2,5,2,3], target = 5
Output: [4]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 5 is 4.


Constraints:

1 <= nums.length <= 100
1 <= nums[i], target <= 100
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindTargetIndicesAfterSortingArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        System.out.print("Enter the elements of the array: ");
        for(int i=0;i<n;i++){
            nums[i]=scanner.nextInt();
        }
        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();
        List<Integer> list = targetIndices(nums, target);
        System.out.println("Target indices after sorting: "+list);
    }

    private static List<Integer> targetIndices(int[] nums, int target) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        int count = 0;

        // Sort the array
        mergeSort(nums, 0, n - 1);

        // Count occurrences of target
        for (int num : nums) {
            if (num == target) {
                count++;
            }
        }

        // Find indices of target
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }

        return result;
    }

    private static void mergeSort(int[] arr,int left,int right){
        if(left<right){
            int mid=left+(right-left)/2;
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }

    private static void merge(int[] arr,int l,int m,int r){
        int n1=m-l+1;
        int n2=r-m;

        int[] L=new int[n1];
        int[] R=new int[n2];

        for(int i=0;i<n1;i++){
            L[i]=arr[l+i];
        }
        for(int j=0;j<n2;j++){
            R[j]=arr[m+1+j];
        }

        int i=0,j=0,k=l;
        while(i<n1 && j<n2){
            if(L[i]<=R[j]){
                arr[k]=L[i];
                i++;
            }else{
                arr[k]=R[j];
                j++;
            }
            k++;
        }

        while(i<n1){
            arr[k]=L[i];
            i++;
            k++;
        }

        while(j<n2){
            arr[k]=R[j];
            j++;
            k++;
        }
    }
}
