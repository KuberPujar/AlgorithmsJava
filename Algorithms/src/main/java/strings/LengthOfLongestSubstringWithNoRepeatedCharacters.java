package strings;

        public class LengthOfLongestSubstringWithNoRepeatedCharacters {
            public static int lengthOfLongestSubstring(String s) {
                int n = s.length();
                int maxLength = 0;
                int left = 0;
                int[] lastSeen = new int[256]; // Assuming ASCII characters
                for (int i = 0; i < 256; i++) {
                    lastSeen[i] = -1;
                }

                for (int right = 0; right < n; right++) {
                    char rightChar = s.charAt(right);
                    if (lastSeen[rightChar] != -1) {
                        left = Math.max(left, lastSeen[rightChar] + 1);
                    }
                    lastSeen[rightChar] = right;
                    maxLength = Math.max(maxLength, right - left + 1);
                }

                return maxLength;
            }

            public static void main(String[] args) {
                String s = "Heycoachsuper30";
                System.out.println(lengthOfLongestSubstring(s)); // Output: 11
            }
        }