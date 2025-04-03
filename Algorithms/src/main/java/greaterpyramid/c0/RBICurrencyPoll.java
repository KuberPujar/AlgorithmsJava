package greaterpyramid.c0;

import java.util.ArrayList;
import java.util.List;

public class RBICurrencyPoll {
    public static List<Integer> findReleasedDenominations(int[] votes) {
        int n = votes.length;
        List<Integer> result = new ArrayList<>();

        // First pass: Mark counts using the array indices
        for (int i = 0; i < n; i++) {
            int value = votes[i];
            // Convert to 0-based index (without Math.abs)
            int index = (value > 0 ? value : -value) - 1;

            if (votes[index] > 0) {
                // Mark first occurrence by making it negative
                votes[index] = -votes[index];
            } else {
                // Increment count (stored as negative)
                votes[index]--;
            }
        }

        // Second pass: Check counts and collect results
        for (int i = 0; i < n; i++) {
            int count;
            if (votes[i] < 0) {
                count = -votes[i] - 1;
            } else {
                count = votes[i] - 1;
            }

            if (count > 1) {
                result.add(i + 1); // Convert back to denomination value
            }

            // Restore original value (without Math.abs)
            if (votes[i] < 0) {
                votes[i] = -votes[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[] votes1 = {89,16,40,71,19,67,3,78,6,97,92,18,45,26,1,42,40,46,4,72,33,36,29,65,98,99,25,10,57,98,20,36,30,85,9,80,12,1,96,21,25,80,15,29,5,63,40,72,76,5,2,40,96,1,70,80,93,22,78,14,50,75,29,79,40,35,97,31,91,64,46,23,82,35,47,83,92,72,36,76,60,89,64,2,55,47,76,88,24,44,80,76,60,49,39,15,45,63,35,26};
        System.out.println(findReleasedDenominations(votes1)); // Output: [1, 2]

        int[] votes2 = {3, 4, 3, 1};
        System.out.println(findReleasedDenominations(votes2)); // Output: [3]

        int[] votes3 = {1, 2, 3, 4};
        System.out.println(findReleasedDenominations(votes3)); // Output: []

        int[] votes4 = {1, 1, 1, 1};
        System.out.println(findReleasedDenominations(votes4)); // Output: [1]
    }
}
