package arrays.bitmanipulation;

import java.util.Scanner;

/*
write a java code for following scenario There is a hidden integer array arr that consists of n non-negative integers.

It was encoded into another integer array encoded of length n - 1, such that encoded[i] = arr[i] XOR arr[i + 1]. For example, if arr = [1,0,2,1], then encoded = [1,2,3].

You are given the encoded array. You are also given an integer first, that is the first element of arr, i.e. arr[0].

Return the original array arr. It can be proved that the answer exists and is unique.



Example 1:

Input: encoded = [1,2,3], first = 1
Output: [1,0,2,1]
Explanation: If arr = [1,0,2,1], then first = 1 and encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
Example 2:

Input: encoded = [6,2,7,3], first = 4
Output: [4,2,0,7,4]


Constraints:

2 <= n <= 104
encoded.length == n - 1
0 <= encoded[i] <= 105
0 <= first <= 105
 */
public class DecodeXORedArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the length of the encoded array: ");
        int n = scanner.nextInt();
        int[] encoded = new int[n - 1];
        System.out.println("Enter the first element: ");
        int first = scanner.nextInt();
        System.out.println("Enter the elements of the encoded array: ");
        for (int i = 0; i < n - 1; i++) {
            encoded[i] = scanner.nextInt();
        }

        scanner.close();
        int[] arr = decode(encoded, first);
        System.out.println("Decoded array: ");
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
    private static int[] decode(int[] encoded,int first){
        int n=encoded.length+1;
        int[] arr=new int[n];
        arr[0]=first;
        for(int i=1;i<n;i++){
            arr[i+1]=arr[i]^encoded[i];
        }
        return arr;
    }
}
