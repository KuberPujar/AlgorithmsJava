package strings;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public static List<Integer> partitionLabels(String s) {
        // Store the last occurrence of each character
        int[] lastOccurrence = new int[26]; // Assuming only lowercase letters
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        List<Integer> partitions = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Update the end of the current partition
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);

            // If we've reached the end of the current partition
            if (i == end) {
                partitions.add(end - start + 1);
                start = end + 1;
            }
        }

        return partitions;
    }

    public static void main(String[] args) {
        String input = "ababcbacadefegdehijhklij";
        List<Integer> result = partitionLabels(input);

        System.out.println("Input: " + input);
        System.out.println("Partition sizes: " + result);
        // Output should be [9, 7, 8] for the given input
    }
}