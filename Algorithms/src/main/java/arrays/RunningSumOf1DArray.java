package arrays;

import java.util.Scanner;

public class RunningSumOf1DArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] nums = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }
        int[] result = runningSum(nums);
        System.out.println("Running sum of the array:");
        for (int j : result) {
            System.out.print(j + " ");
        }
    }

    private static int[] runningSum(int[] nums){
        int[] result=new int[nums.length];
        result[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            result[i]=result[i-1]+nums[i];
        }
        return result;
    }
}
