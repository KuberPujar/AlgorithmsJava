package searching.binarysearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
You are given a 0-indexed integer array nums and a target element target.

A target index is an index i such that nums[i] == target.

Return a list of the target indices of nums after sorting nums in non-decreasing order.
If there are no target indices, return an empty list. The returned list must be sorted in
 increasing order.



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
public class FindTargetIndicesAfterSortingArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.print("Enter the target element: ");
        int target = scanner.nextInt();
        List<Integer> result = targetIndices(nums, target);
        System.out.println("Target indices after sorting: " + result);
    }

    private static List<Integer> targetIndices(int[] nums,int target){
        List<Integer> result=new ArrayList<>();
        mergeSort(nums,0,nums.length-1);
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                result.add(i);
            }
        }
        return result;
    }

    private static void mergeSort(int[] nums,int left,int right){
        if(left<right){
            int mid=left+(right-left)/2;
            mergeSort(nums,left,mid);
            mergeSort(nums,mid+1,right);
            merge(nums,left,mid,right);
        }
    }

    private static void merge(int[] nums,int left,int mid,int right){
        // Calculate the size of the two subarrays to be merged
        int n1=mid-left+1;
        int n2=right-mid;
        // Create temporary arrays to hold the elements of the two subarrays
        int[] leftArray=new int[n1];
        int[] rightArray=new int[n2];
        // Copy the elements of the left subarray into the temporary array
        System.arraycopy(nums, left, leftArray, 0, n1);
        // Copy the elements of the right subarray into the temporary array
        System.arraycopy(nums, mid + 1, rightArray, 0, n2);
        // Merge the two subarrays back into the original array
        int i=0,j=0,k=left;
        while(i<n1 && j<n2){
            if(leftArray[i]<=rightArray[j]){
                nums[k++]=leftArray[i++];
            }else{
                nums[k++]=rightArray[j++];
            }
        }
        // Copy any remaining elements from the left subarray
        while(i<n1){
            nums[k++]=leftArray[i++];
        }
        // Copy any remaining elements from the right subarray
        while(j<n2){
            nums[k++]=rightArray[j++];
        }
    }
}
