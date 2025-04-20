package strings.twopointer;
/*
A permutation perm of n + 1 integers of all the integers in the range [0, n] can be represented as a string s of length n where:

s[i] == 'I' if perm[i] < perm[i + 1], and
s[i] == 'D' if perm[i] > perm[i + 1].
Given a string s, reconstruct the permutation perm and return it. If there are multiple valid permutations perm, return any of them.



Example 1:

Input: s = "IDID"
Output: [0,4,1,3,2]
Example 2:

Input: s = "III"
Output: [0,1,2,3]
Example 3:

Input: s = "DDI"
Output: [3,2,0,1]


Constraints:

1 <= s.length <= 105
s[i] is either 'I' or 'D'.
 */
public class DiStringMatch {
    public static void main(String[] args) {
        String s = "IDID";
        int[] result = diStringMatch(s);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    private static int[] diStringMatch(String s) {
        int n = s.length();
        int[] perm = new int[n + 1];
        int left = 0;
        int right = n;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                perm[i] = left++;
            } else {
                perm[i] = right--;
            }
        }
        perm[n] = left; // or right, they're equal at this point

        return perm;
    }
}
