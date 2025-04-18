package arrays.binarysearch;

import java.util.Arrays;

/*
Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]


Example 1:

Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
Example 2:

Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.


Constraints:

2 <= arr.length <= 500
-103 <= arr[i] <= 103

public boolean checkIfExist(int[] arr) {
        for(int i=0;i<arr.length;i++)
            if(arr[i]%2==0 && findHalf(arr,arr[i]/2,i)) return true;
        return false;
    }
    public boolean findHalf(int[] arr, int target, int indexOfDouble){
        for(int i=0;i<arr.length;i++)
            if(arr[i]==target && i!=indexOfDouble) return true;
        return false;
    }
 */
public class CheckIfNandItsDoubleExist {
    public static void main(String[] args) {
        int[] arr1 = {10, 2, 5, 3};
        System.out.println(checkIfExist(arr1)); // Output: true

        int[] arr2 = {3, 1, 7, 11};
        System.out.println(checkIfExist(arr2)); // Output: false
    }

    private static boolean checkIfExist(int[] arr) {
        //Speacial case: check if atleast two zeros
        int zeroCount=0;
        for(int num:arr){
            if(num==0) zeroCount++;
            if(zeroCount>=2) return true;
        }

        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=0 && binarySearch(arr,arr[i]*2,i)){
                return true;
            }
        }
        return false;
    }

    private static boolean binarySearch(int[] arr,int target,int excludeIndex){
        int left=0;
        int right=arr.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(arr[mid]==target && mid!=excludeIndex){
                return true;
            }
            if(arr[mid]<target){
                left=mid+1;
            }
            else {
                right=mid-1;
            }
        }
        return false;
    }
}
