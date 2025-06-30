package queues.hashing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.



Example 1:

Input: s = "leetcode"

Output: 0

Explanation:

The character 'l' at index 0 is the first character that does not occur at any other index.

Example 2:

Input: s = "loveleetcode"

Output: 2

Example 3:

Input: s = "aabb"

Output: -1



Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
 */
public class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> frequency = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            queue.offer(new Pair(c, i));
        }

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            if (frequency.get(current.character) == 1) {
                return current.index;
            }
        }

        return -1;
    }

    class Pair {
        char character;
        int index;

        Pair(char c, int i) {
            character = c;
            index = i;
        }
    }

    public static void main(String[] args) {
        FirstUniqueCharacterInAString solution = new FirstUniqueCharacterInAString();
        System.out.println(solution.firstUniqChar("leetcode")); // Output: 0
        System.out.println(solution.firstUniqChar("loveleetcode")); // Output: 2
        System.out.println(solution.firstUniqChar("aabb")); // Output: -1
    }
}
