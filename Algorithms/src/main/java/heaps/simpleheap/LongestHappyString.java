package heaps.simpleheap;

import java.util.PriorityQueue;

/*
A string s is called happy if it satisfies the following conditions:

s only contains the letters 'a', 'b', and 'c'.
s does not contain any of "aaa", "bbb", or "ccc" as a substring.
s contains at most a occurrences of the letter 'a'.
s contains at most b occurrences of the letter 'b'.
s contains at most c occurrences of the letter 'c'.
Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It is the only correct answer in this case.


Constraints:

0 <= a, b, c <= 100
a + b + c > 0
 */
public class LongestHappyString {

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Letter> maxHeap = new PriorityQueue<>((x, y) -> y.count - x.count);
        if (a > 0) maxHeap.offer(new Letter('a', a));
        if (b > 0) maxHeap.offer(new Letter('b', b));
        if (c > 0) maxHeap.offer(new Letter('c', c));

        StringBuilder sb = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            Letter first = maxHeap.poll();
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 1) == first.ch && sb.charAt(len - 2) == first.ch) {
                if (maxHeap.isEmpty()) break;
                Letter second = maxHeap.poll();
                sb.append(second.ch);
                second.count--;
                if (second.count > 0) maxHeap.offer(second);
                maxHeap.offer(first);
            } else {
                sb.append(first.ch);
                first.count--;
                if (first.count > 0) maxHeap.offer(first);
            }
        }
        return sb.toString();
    }

    private static class Letter {
        char ch;
        int count;
        Letter(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        LongestHappyString solution = new LongestHappyString();
        System.out.println(solution.longestDiverseString(1, 1, 7)); // Example 1
        System.out.println(solution.longestDiverseString(7, 1, 0)); // Example 2
    }
}
