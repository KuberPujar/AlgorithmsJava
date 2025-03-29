package strings;

import java.util.HashMap;

public class LargestSubStringWithKUniqueCharacters {
    public static int longestKSubstr(String S, int K) {
        if (K == 0) return -1;

        int n = S.length();
        int maxLength = -1;
        int left = 0;
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char rightChar = S.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);

            while (charCount.size() > K) {
                char leftChar = S.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }

            if (charCount.size() == K) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String S = "aabacbebebe";
        int K = 3;
        System.out.println(longestKSubstr(S, K)); // Output: 7
    }
}
