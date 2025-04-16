package arrays.twopointer;

import java.util.StringJoiner;

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
        String s="IDID";
        int[] arr=findPermutation(s);
        printArray(arr);
        System.out.println();
        String s1="III";
        int[] arr1=findPermutation(s1);
        printArray(arr1);
        System.out.println();
        String s2="DDI";
        int[] arr2=findPermutation(s2);
        printArray(arr2);
    }

    private static int[] findPermutation(String s) {
        int n = s.length();
        int[] arr = new int[n + 1];
        int low = 0, high = n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                arr[i] = low++;
            } else {
                arr[i] = high--;
            }
        }
        arr[n] = low;
        return arr;
    }



    private static void printArray(int[] arr){
        for(int i:arr){
            System.out.print(i+" ");
        }
    }
}
