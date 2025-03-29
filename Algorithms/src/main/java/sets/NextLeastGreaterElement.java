package sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NextLeastGreaterElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(scanner.nextInt());
        }
        scanner.close();

        List<Integer> result = findNextLeastGreaterElements(nums, n);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static ArrayList<Integer> findNextLeastGreaterElements(List<Integer> nums, int n) {
          ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int nextGreater = Integer.MAX_VALUE;
            for (int j = i + 1; j < n; j++) {
                if (nums.get(j) > nums.get(i) && nums.get(j) < nextGreater) {
                    nextGreater = nums.get(j);
                }
            }
            result.add((nextGreater == Integer.MAX_VALUE) ? -1 : nextGreater);
        }

        return result;
    }
}