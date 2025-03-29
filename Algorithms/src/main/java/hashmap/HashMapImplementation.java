package hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMapImplementation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Map<Integer, Integer> frequencyMap = getFrequencyMap(arr);
        // The driver code will handle the printing
        System.out.println(frequencyMap);
    }

    public static Map<Integer, Integer> getFrequencyMap(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        return frequencyMap;
    }
}
