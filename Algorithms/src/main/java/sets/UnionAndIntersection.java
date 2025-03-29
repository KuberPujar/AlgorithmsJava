package sets;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashSet;

public class UnionAndIntersection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] array1 = new int[n];
        int[] array2 = new int[m];

        for (int i = 0; i < n; i++) {
            array1[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            array2[i] = scanner.nextInt();
        }
        scanner.close();

        printUnionAndIntersection(array1, array2);
    }

    public static void printUnionAndIntersection(int[] array1, int[] array2) {
        Set<Integer> unionSet = new TreeSet<>();
        Set<Integer> intersectionSet = new LinkedHashSet<>();

        for (int num : array1) {
            unionSet.add(num);
        }

        for (int num : array2) {
            if (unionSet.contains(num)) {
                intersectionSet.add(num);
            }
            unionSet.add(num);
        }

        // Print union
        for (int num : unionSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Print intersection
        for (int num : intersectionSet) {
            System.out.print(num + " ");
        }
    }
}