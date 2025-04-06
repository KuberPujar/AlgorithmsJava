package arrays;

import java.util.Scanner;

public class DecodeXoredArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int n = scanner.nextInt();
        int[] encoded = new int[n];
        System.out.print("Enter the elements of the encoded array: ");
        for (int i = 0; i < n; i++) {
            encoded[i] = scanner.nextInt();
        }
        System.out.print("Enter the first element of the original array: ");
        int first = scanner.nextInt();
        int[] decoded = decode(encoded, first);
        for(int i:decoded){
            System.out.print(i+" ");
        }
    }

    private static int[] decode(int[] encoded,int first){
        int[] original = new int[encoded.length + 1];
        original[0] = first; // The first element is given

        for (int i = 0; i < encoded.length; i++) {
            // Each subsequent element is XOR of previous original and current encoded
            original[i + 1] = original[i] ^ encoded[i];
        }

        return original;
    }
}
