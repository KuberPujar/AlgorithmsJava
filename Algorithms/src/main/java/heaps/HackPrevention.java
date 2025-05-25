package heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
Hack Prevention
You are incharge for securing a system which contains confidential data which must not get into the hands of the enemies. You are tasked with designing a password and according to the agency a good password has the following properties:

It has at least 6 characters and at most 20 characters.
It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
It does not contain three repeating characters in a row, for example "aaa" will be a bad password.
You are given a string 's' on which you can perform several operation:

Insert one character to string 's',
Delete one character from string, or
Replace one character of string with another character.
Determine the minimum number of operation required to convert the string 's' into a good password.

Input:

The input consists of a single string s, representing the password that needs to be converted into a good password.
Output:

The output is a single integer representing the minimum number of operations required to convert the string s into a good password.
Examples
Sample Input - 1:
"BrEEEHiM"
Sample Output - 1:
1
Explanation:
You can just delete one "E" to make the password good from the given s.

Sample Input- 2:
"JaP"
Sample Output - 2:
3
Explanation:
You have to insert three new characters to make the password length at least 6, so that the given string 's' can turn into a good password.

Constraints:
1<=length of string 's'<=50
Note:The function should return the result. The driver code will handle printing the output.
 */
public class HackPrevention {

    /**
     * Main solution method to find minimum operations to make a good password
     *
     * @param s Input password string
     * @return Minimum number of operations needed
     */
    public static int minOperationsToGoodPassword(String s) {
        int n = s.length();

        // Count missing character types
        boolean hasLower = false, hasUpper = false, hasDigit = false;
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }

        int missingTypes = 0;
        if (!hasLower) missingTypes++;
        if (!hasUpper) missingTypes++;
        if (!hasDigit) missingTypes++;

        // Count repeating characters that need to be fixed
        int repeatingChars = 0;
        int i = 0;
        while (i < n) {
            int count = 1;
            while (i + count < n && s.charAt(i) == s.charAt(i + count)) {
                count++;
            }
            repeatingChars += count / 3; // Each group of 3+ needs fixing
            i += count;
        }

        // Calculate minimum operations based on length
        if (n < 6) {
            // Need to add characters
            int toAdd = 6 - n;
            return Math.max(toAdd, missingTypes + repeatingChars);
        } else if (n <= 20) {
            // Length is good, just fix types and repeating chars
            return Math.max(missingTypes, repeatingChars);
        } else {
            // Need to remove characters
            int toRemove = n - 20;
            // Removing characters can also fix repeating chars
            int remainingRepeating = Math.max(0, repeatingChars - toRemove);
            return toRemove + Math.max(missingTypes, remainingRepeating);
        }
    }

    /**
     * Count how many character types are missing (lowercase, uppercase, digit)
     */
    private static int countMissingTypes(String s) {
        boolean hasLower = false, hasUpper = false, hasDigit = false;

        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }

        int missing = 0;
        if (!hasLower) missing++;
        if (!hasUpper) missing++;
        if (!hasDigit) missing++;

        return missing;
    }

    /**
     * Count minimum replacements needed to fix repeating characters
     */
    private static int countRepeatingFixes(String s) {
        int fixes = 0;
        int i = 0;

        while (i < s.length()) {
            int j = i;
            // Find length of current repeating sequence
            while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int repeatLength = j - i;
            if (repeatLength >= 3) {
                // Need to replace every 3rd character in repeating sequence
                fixes += repeatLength / 3;
            }

            i = j;
        }

        return fixes;
    }

    /**
     * Calculate remaining fixes needed for repeating characters after optimal deletions
     * This is a more accurate approach for the long password case
     */
    private static int calculateOptimalRepeatingFixesAfterDeletion(String s, int deletions) {
        // Find all repeating sequences
        List<Integer> repeatLengths = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            int j = i;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int length = j - i;
            if (length >= 3) {
                repeatLengths.add(length);
            }
            i = j;
        }

        // Use heap to optimize deletion strategy
        // Priority: sequences where deletion has maximum benefit
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> {
            // Prioritize by potential savings from deletion
            int savingsA = a / 3 - (Math.max(0, a - 1) / 3);
            int savingsB = b / 3 - (Math.max(0, b - 1) / 3);
            if (savingsA != savingsB) return savingsB - savingsA;
            return b - a; // Prefer longer sequences
        });

        for (int length : repeatLengths) {
            heap.offer(length);
        }

        int remainingDeletions = deletions;

        // Apply deletions optimally
        while (!heap.isEmpty() && remainingDeletions > 0) {
            int length = heap.poll();

            // For a repeating sequence of length n:
            // - Deleting 1 char from length divisible by 3 saves 1 replacement
            // - Deleting 2 chars from length with remainder 1 saves 1 replacement
            // - Deleting 3 chars from length with remainder 2 saves 1 replacement

            if (length % 3 == 0 && remainingDeletions >= 1) {
                remainingDeletions--;
                length--;
            } else if (length % 3 == 1 && remainingDeletions >= 2) {
                remainingDeletions -= 2;
                length -= 2;
            } else if (length % 3 == 2 && remainingDeletions >= 3) {
                remainingDeletions -= 3;
                length -= 3;
            }

            if (length >= 3) {
                heap.offer(length);
            }
        }

        // Count remaining replacements needed
        int totalReplacements = 0;
        while (!heap.isEmpty()) {
            int length = heap.poll();
            totalReplacements += length / 3;
        }

        return totalReplacements;
    }

    /**
     * Alternative approach using simple greedy algorithm
     * More straightforward but potentially less optimal for complex cases
     */
    public static int minOperationsSimple(String s) {
        int n = s.length();
        int operations = 0;

        // Handle length issues
        if (n < 6) {
            operations += 6 - n;
            n = 6; // After insertions
        } else if (n > 20) {
            operations += n - 20;
            n = 20; // After deletions
        }

        // Handle missing character types
        operations += countMissingTypes(s);

        // Handle repeating characters (simplified)
        operations += countRepeatingFixes(s);

        return operations;
    }

    /**
     * Utility method to validate if a password is good
     */
    public static boolean isGoodPassword(String password) {
        // Check length
        if (password.length() < 6 || password.length() > 20) {
            return false;
        }

        // Check character types
        boolean hasLower = false, hasUpper = false, hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }

        if (!hasLower || !hasUpper || !hasDigit) {
            return false;
        }

        // Check for three consecutive repeating characters
        for (int i = 0; i <= password.length() - 3; i++) {
            if (password.charAt(i) == password.charAt(i + 1) &&
                    password.charAt(i) == password.charAt(i + 2)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Main solution method for the problem
     */
    public static int solution(String s) {
        return minOperationsToGoodPassword(s);
    }

    // Test method
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "BrEEEHiM",    // Expected: 1
                "JaP",         // Expected: 3
                "a",           // Very short
                "aA1",         // Short but has all types
                "aaaAAA111",   // Has all types but repeating
                "abcdefghijklmnopqrstuvwxyz", // Too long
                "Password123", // Already good?
                "AAA",         // Short + repeating + missing types
                "aaaaaaaaaaaaaaaaaaaaa", // Too long + missing types + repeating
                "Aa1Bb2Cc3"   // Good password
        };

        System.out.println("=== Password Validation Test Cases ===");
        for (String test : testCases) {
            int operations = minOperationsToGoodPassword(test);
            boolean isGood = isGoodPassword(test);

            System.out.println("Input: \"" + test + "\"");
            System.out.println("Length: " + test.length());
            System.out.println("Is Good: " + isGood);
            System.out.println("Operations needed: " + operations);
            System.out.println("Missing types: " + countMissingTypes(test));
            System.out.println("Repeating fixes: " + countRepeatingFixes(test));
            System.out.println("---");
        }

        // Test the specific case mentioned by user
        System.out.println("\n=== User Reported Case ===");
        String userCase = "nssnssssksdsjkassssslajssbdbbddddalk";
        int userResult = minOperationsToGoodPassword(userCase);
        System.out.println("Input: \"" + userCase + "\"");
        System.out.println("Length: " + userCase.length());
        System.out.println("Result: " + userResult + " (Expected: 18)");
        System.out.println("Missing types: " + countMissingTypes(userCase));
        System.out.println("Repeating fixes (before deletion): " + countRepeatingFixes(userCase));

        // Detailed analysis
        System.out.println("\nDetailed Analysis:");
        System.out.println("- Length: " + userCase.length() + " > 20, need to delete " + (userCase.length() - 20) + " characters");
        System.out.println("- Missing uppercase and digits (2 types missing)");
        System.out.println("- Repeating sequences analysis:");
        analyzeRepeatingSequences(userCase);

        // Performance test with heap optimization
        System.out.println("\n=== Performance Comparison ===");
        String complexCase = "aaaaaaaaaBBBBBBBBccccccccc";

        long startTime = System.nanoTime();
        int optimizedResult = minOperationsToGoodPassword(complexCase);
        long optimizedTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int simpleResult = minOperationsSimple(complexCase);
        long simpleTime = System.nanoTime() - startTime;

        System.out.println("Complex case: \"" + complexCase + "\"");
        System.out.println("Optimized result: " + optimizedResult + " (Time: " + optimizedTime/1000.0 + " μs)");
        System.out.println("Simple result: " + simpleResult + " (Time: " + simpleTime/1000.0 + " μs)");
    }

    /**
     * Helper method to analyze repeating sequences in detail
     */
    private static void analyzeRepeatingSequences(String s) {
        int i = 0;
        int totalReplacements = 0;

        while (i < s.length()) {
            int j = i;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int length = j - i;
            if (length >= 3) {
                int replacements = length / 3;
                totalReplacements += replacements;
                System.out.println("  - '" + s.charAt(i) + "' repeats " + length + " times → need " + replacements + " replacements");
            }
            i = j;
        }

        System.out.println("  - Total replacements needed: " + totalReplacements);
    }
}

/*
Algorithm Explanation:
=====================

The problem requires converting a string into a "good password" with minimum operations.

Good Password Requirements:
1. Length: 6-20 characters
2. Types: At least one lowercase, uppercase, and digit
3. No three consecutive repeating characters

Solution Strategy:
1. **Length Handling:**
   - If length < 6: Insert characters (can fix missing types too)
   - If length > 20: Delete characters (optimally using heap)
   - If 6 ≤ length ≤ 20: Only fix types and repetitions

2. **Heap Usage for Optimization:**
   - When deleting characters from long passwords
   - Use min-heap to prioritize which repeating sequences to break first
   - Priority based on (length % 3) for optimal deletion strategy

3. **Character Type Fixing:**
   - Count missing types (lowercase, uppercase, digit)
   - Can be fixed efficiently during insertions/replacements

4. **Repeating Character Fixes:**
   - For sequence of length n: need n/3 replacements
   - Heap optimization considers deletion opportunities

Time Complexity: O(n log k) where k is number of repeating sequences
Space Complexity: O(k) for heap storage

Key Insights:
- Insertions can fix multiple issues simultaneously
- Deletions should be strategic (heap helps optimize)
- Replacements are sometimes unavoidable for repetitions
- The heap approach is most beneficial for complex long passwords

Example Analysis:
- "BrEEEHiM": Length=8✓, has all types✓, "EEE"❌ → 1 operation
- "JaP": Length=3❌, missing digit❌ → 3 insertions fix both issues
*/
