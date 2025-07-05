package heaps.counting;

import java.util.*;

/*
Given an array of strings words and an integer k, return the k most frequent strings.

Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.



Example 1:

Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:

Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.


Constraints:

1 <= words.length <= 500
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
k is in the range [1, The number of unique words[i]]


Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Use a min-heap with custom comparator
        // Min-heap because we want to remove the least frequent/lexicographically larger words
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
            int freqA = frequencyMap.get(a);
            int freqB = frequencyMap.get(b);

            // If frequencies are different, less frequent word has higher priority (min-heap)
            if (freqA != freqB) {
                return freqA - freqB;
            }
            // If frequencies are same, lexicographically larger word has higher priority
            return b.compareTo(a);
        });

        // Step 3: Process each unique word
        for (String word : frequencyMap.keySet()) {
            minHeap.offer(word);
            // If heap size exceeds k, remove the word with lowest priority
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 4: Extract words from heap and reverse to get correct order
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        // Reverse to get highest frequency first, then lexicographically smaller
        Collections.reverse(result);
        return result;
    }

    // Alternative approach using max-heap (simpler logic but less efficient for large datasets)
    public List<String> topKFrequentMaxHeap(String[] words, int k) {
        // Count frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Use max-heap with proper comparator
        PriorityQueue<String> maxHeap = new PriorityQueue<>((a, b) -> {
            int freqA = frequencyMap.get(a);
            int freqB = frequencyMap.get(b);

            // Higher frequency first
            if (freqA != freqB) {
                return freqB - freqA;
            }
            // Same frequency: lexicographically smaller first
            return a.compareTo(b);
        });

        // Add all words to max-heap
        maxHeap.addAll(frequencyMap.keySet());

        // Extract top k words
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(maxHeap.poll());
        }

        return result;
    }

    // Bucket sort approach for optimal O(n) space when k is small
    public List<String> topKFrequentBucketSort(String[] words, int k) {
        // Count frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Create buckets for each frequency
        List<List<String>> buckets = new ArrayList<>();
        for (int i = 0; i <= words.length; i++) {
            buckets.add(new ArrayList<>());
        }

        // Place words in appropriate buckets
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        // Sort each bucket lexicographically
        for (List<String> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Extract top k words from highest frequency buckets
        List<String> result = new ArrayList<>();
        for (int i = buckets.size() - 1; i >= 0 && result.size() < k; i--) {
            for (String word : buckets.get(i)) {
                if (result.size() < k) {
                    result.add(word);
                }
            }
        }

        return result;
    }

    // Test the solution
    public static void main(String[] args) {
        TopKFrequentWords solution = new TopKFrequentWords();

        // Test Example 1
        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
        int k1 = 2;
        List<String> result1 = solution.topKFrequent(words1, k1);
        System.out.println("Example 1 - Input: " + Arrays.toString(words1) + ", k = " + k1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: [i, love]");
        System.out.println();

        // Test Example 2
        String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k2 = 4;
        List<String> result2 = solution.topKFrequent(words2, k2);
        System.out.println("Example 2 - Input: " + Arrays.toString(words2) + ", k = " + k2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: [the, is, sunny, day]");
        System.out.println();

        // Test edge case with same frequency
        String[] words3 = {"apple", "banana", "apple", "cherry", "banana", "date"};
        int k3 = 3;
        List<String> result3 = solution.topKFrequent(words3, k3);
        System.out.println("Edge case - Input: " + Arrays.toString(words3) + ", k = " + k3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: [apple, banana, cherry] (lexicographical order for same frequency)");
        System.out.println();

        // Compare with max-heap approach
        System.out.println("Max-heap approach result: " + solution.topKFrequentMaxHeap(words1, k1));
        System.out.println("Bucket sort approach result: " + solution.topKFrequentBucketSort(words1, k1));
    }
}

/*
Algorithm Explanation:

1. **Frequency Counting**:
   - Use HashMap to count frequency of each word
   - Time: O(n), Space: O(n)

2. **Min-Heap Approach (Optimal)**:
   - Use min-heap of size k with custom comparator
   - Comparator logic:
     * Lower frequency has higher priority (for removal)
     * Same frequency: lexicographically larger has higher priority
   - Time: O(n log k), Space: O(k) for heap + O(n) for map

3. **Comparator Logic**:
   - For min-heap: We want to remove less frequent words first
   - If frequencies equal: remove lexicographically larger words first
   - Final result needs to be reversed to get correct order

4. **Alternative Approaches**:
   - Max-heap: Simpler logic but O(n log n) for heap operations
   - Bucket sort: O(n) time but uses more space

Time Complexity: O(n log k) - meets the follow-up requirement
Space Complexity: O(n) for frequency map + O(k) for heap

Key Points:
- Min-heap maintains exactly k elements
- Custom comparator handles both frequency and lexicographical ordering
- Result is reversed to get final correct order
- Handles edge cases where multiple words have same frequency
*/
