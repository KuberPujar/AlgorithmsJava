package greaterpyramid.c2;
/*
Group Anangrams
Given an array of strings strs, group the anagrams together.Print the group of anagrams with a single space between them and each group in new line.Also, Print output the list of anagrams in lexicographically sorted order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Input Format:
first line contains a single integer n (size of the array).
second line contains n strings.
Output Format:
Print the group of anagrams with a single space between them

Each group in new line
Constraints:
1 <= n <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
Example:
Input:
6
eat tea tan ate nat bat

Output:
ate eat tea
bat
nat tan

Explanation:
As ate comes before bat so the group of anagrams of ate should come before group of anagrams of bat. same for others.

NOTE : You have to return the output.
 */
import java.util.*;

public class GroupAnagram {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String str : strs) {
            // Sort the characters of the string to create a key
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);

            // Add the original string to the corresponding group
            if (!anagramGroups.containsKey(sortedStr)) {
                anagramGroups.put(sortedStr, new ArrayList<>());
            }
            anagramGroups.get(sortedStr).add(str);
        }

        // Prepare the result with groups sorted lexicographically
        List<List<String>> result = new ArrayList<>(anagramGroups.values());

        // Sort each group internally
        for (List<String> group : result) {
            Collections.sort(group);
        }

        // Sort the groups by their first element lexicographically
        result.sort(Comparator.comparing(List::getFirst));

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strs = new String[n];

        for (int i = 0; i < n; i++) {
            strs[i] = scanner.next();
        }

        List<List<String>> groupedAnagrams = groupAnagrams(strs);

        // Print the results in required format
        for (List<String> group : groupedAnagrams) {
            for (int i = 0; i < group.size(); i++) {
                System.out.print(group.get(i));
                if (i < group.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}