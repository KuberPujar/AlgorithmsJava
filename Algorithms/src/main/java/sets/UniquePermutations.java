package sets;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class UniquePermutations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        Set<String> permutations = new TreeSet<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        do {
            permutations.add(new String(chars));
        } while (nextPermutation(chars));

        for (String permutation : permutations) {
            System.out.println(permutation);
        }
    }

    private static boolean nextPermutation(char[] chars) {
        int i = chars.length - 2;
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = chars.length - 1;
        while (chars[j] <= chars[i]) {
            j--;
        }

        swap(chars, i, j);
        reverse(chars, i + 1, chars.length - 1);
        return true;
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            swap(chars, start, end);
            start++;
            end--;
        }
    }
}