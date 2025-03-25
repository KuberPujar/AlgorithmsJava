package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CombineTheBooks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter books name in library-1: ");
        int n = sc.nextInt();
        List<String> inventory1 = new ArrayList<>();
        System.out.println("Enter the elements of the array: ");

        for (int i = 0; i < n; i++) {
            inventory1.add(sc.next());
        }
        System.out.println("Enter books name in library-2: ");
        List<String> inventory2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            inventory2.add(sc.next());
        }
        sc.close(); // Close the scanner to prevent resource leak
        combineTheBooks(inventory1, inventory2);
    }

    private static void combineTheBooks(List<String> inventory1, List<String> inventory2) {
        List<String> combinedInventory = new ArrayList<>();
        combinedInventory.addAll(inventory1);
        combinedInventory.addAll(inventory2);
        combinedInventory.sort(String::compareTo);
        for (String book : combinedInventory) {
            System.out.print(book + " ");
        }
    }
}
