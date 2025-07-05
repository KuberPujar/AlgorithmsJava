package heaps.hashing;

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
        // Step 1: Count the frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Use a priority queue to get the top k frequent words
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue()) ?
                        b.getKey().compareTo(a.getKey()) :
                        a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 3: Extract the results from the heap
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentWords solution = new TopKFrequentWords();

        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
        int k1 = 2;
        System.out.println(solution.topKFrequent(words1, k1)); // Output: ["i", "love"]

        String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k2 = 4;
        System.out.println(solution.topKFrequent(words2, k2)); // Output: ["the", "is", "sunny", "day"]
    }
}
