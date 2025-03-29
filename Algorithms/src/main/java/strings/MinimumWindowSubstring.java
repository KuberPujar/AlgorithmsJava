package strings;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Array to keep a count of all the unique characters in t.
        int[] dictT = new int[128];
        for (int i = 0; i < t.length(); i++) {
            dictT[t.charAt(i)]++;
        }

        // Number of unique characters in t that need to be present in the window.
        int required = 0;
        for (int count : dictT) {
            if (count > 0) {
                required++;
            }
        }

        // Left and Right pointer
        int l = 0, r = 0;

        // Formed is used to keep track of how many unique characters in t
        // are present in the current window in their desired frequency.
        int formed = 0;

        // Array to keep a count of all the unique characters in the current window.
        int[] windowCounts = new int[128];

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            windowCounts[c]++;

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT[c] > 0 && windowCounts[c] == dictT[c]) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                // The character at the position pointed by the `left` pointer is no longer a part of the window.
                windowCounts[c]--;
                if (dictT[c] > 0 && windowCounts[c] < dictT[c]) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done contracting.
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t)); // Output: "BANC"
    }
}