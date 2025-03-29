package searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MaxRepeatedMaxElementInArray {
    public static void main(String[] args) {
        List<Integer> arr = List.of(2, 2, 6, 0, 2, 0, 9, 8, 3, 1, 2, 4, 8, 7, 1, 1, 6, 7, 2, 8, 8, 3, 9, 8, 1, 7, 3, 2, 0, 1, 7, 4, 1, 2, 7, 6, 2, 6, 2, 1, 0, 7, 3, 3, 4, 7, 5, 7, 1, 7);
        int maxElement = maxElementFrequency(arr);
        System.out.println("The maximum repeated element is: " + maxElement);
    }

    public static int maxElementFrequency(List<Integer> a) {
        int maxElement = Integer.MIN_VALUE;
        int frequency = 0;

        for (int num : a) {
            if (num > maxElement) {
                maxElement = num;
                frequency = 1;
            } else if (num == maxElement) {
                frequency++;
            }
        }

        return frequency;
    }
}
