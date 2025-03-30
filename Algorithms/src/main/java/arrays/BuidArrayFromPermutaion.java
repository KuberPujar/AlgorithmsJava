package arrays;

import java.util.Scanner;

public class BuidArrayFromPermutaion {
    public static int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }

    public static int[] buildArrayWithoutUsingExtraSpace(int[]arr){
        int n=arr.length;
        for(int i=0;i<n;i++){
            arr[i]=arr[i]+(arr[arr[i]]%n)*n;
        }

        for(int i=0;i<n;i++){
            arr[i]=arr[i]/n;
        }
        return arr;
    }
    public static void main(String[] args) {
        // Example usage
        int[] nums = {0, 2, 1, 5, 3, 4};
        int[] result = buildArrayWithoutUsingExtraSpace(nums);

        System.out.print("Input: [");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + (i < nums.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        System.out.print("Output: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i < result.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}
