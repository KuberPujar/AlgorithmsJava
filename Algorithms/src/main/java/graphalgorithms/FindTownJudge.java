package graphalgorithms;

import java.util.*;

/*
In a town, there are n people labeled from 1 to n. There is a rumour that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody. Everybody (except for the town judge) trusts the town judge. There is exactly one person that satisfies properties 1 and 2. You are given an array trust where trust[i] = [a, b] representing that the person labelled a trust the person labelled b. If a trust relationship does not exist in the trust array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

Example 1:

Input:

n = 2, trust = [[1,2]]
Output:

2
Example 2:

Input:

n = 3, trust = [[1,3],[2,3]]
Output:

3
Example 3:

Input:

n = 3, trust = [[1,3],[2,3],[3,1]]
Output:

-1
Constraints:

1 <= n <= 1000
0 <= trust.length <= 10^4
trust[i].length == 2
All the pairs of trust are unique.
a != b
1 <= a, b <= n
 */
public class FindTownJudge {
    /**
     * Approach 1: Count Trusts and Trusted By
     * Time Complexity: O(trust.length + n)
     * Space Complexity: O(n)
     *
     * Logic: The judge should trust nobody (trustCount[judge] = 0)
     * and be trusted by everyone else (trustedBy[judge] = n-1)
     */
    public static int findJudgeCountingApproach(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) {
            return 1; // Only one person, they are the judge by default
        }

        int[] trustCount = new int[n + 1];      // How many people each person trusts
        int[] trustedByCount = new int[n + 1];  // How many people trust each person

        // Process all trust relationships
        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];

            trustCount[truster]++;      // Person 'truster' trusts one more person
            trustedByCount[trusted]++;  // Person 'trusted' is trusted by one more person
        }

        // Find the judge: trusts nobody (0) and is trusted by everyone else (n-1)
        for (int person = 1; person <= n; person++) {
            if (trustCount[person] == 0 && trustedByCount[person] == n - 1) {
                return person;
            }
        }

        return -1; // No judge found
    }

    /**
     * Approach 2: Single Pass with Net Trust Score
     * Time Complexity: O(trust.length + n)
     * Space Complexity: O(n)
     *
     * Logic: For each person, calculate net trust score = trustedBy - trusts
     * Judge should have score = n-1 (trusted by all n-1 others, trusts nobody)
     */
    public static int findJudgeNetScore(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) {
            return 1;
        }

        int[] netTrustScore = new int[n + 1];

        // Calculate net trust score for each person
        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];

            netTrustScore[truster]--;  // Trusting someone decreases your score
            netTrustScore[trusted]++;  // Being trusted increases your score
        }

        // Judge should have net score = n-1
        for (int person = 1; person <= n; person++) {
            if (netTrustScore[person] == n - 1) {
                return person;
            }
        }

        return -1;
    }

    /**
     * Approach 3: Set-based Approach
     * Time Complexity: O(trust.length + n)
     * Space Complexity: O(n)
     *
     * Logic: Use sets to track who trusts whom, then validate judge conditions
     */
    public static int findJudgeSetBased(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) {
            return 1;
        }

        Set<Integer> trustsSomeone = new HashSet<>();  // People who trust at least one person
        Map<Integer, Set<Integer>> trustedBy = new HashMap<>();  // Who trusts each person

        // Initialize trustedBy map
        for (int i = 1; i <= n; i++) {
            trustedBy.put(i, new HashSet<>());
        }

        // Process trust relationships
        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];

            trustsSomeone.add(truster);
            trustedBy.get(trusted).add(truster);
        }

        // Find the judge
        for (int person = 1; person <= n; person++) {
            // Judge trusts nobody and is trusted by everyone else
            if (!trustsSomeone.contains(person) &&
                    trustedBy.get(person).size() == n - 1) {
                return person;
            }
        }

        return -1;
    }

    /**
     * Approach 4: Graph-based Approach (for educational purposes)
     * Time Complexity: O(trust.length + n)
     * Space Complexity: O(n + trust.length)
     */
    public static int findJudgeGraphBased(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) {
            return 1;
        }

        // Build adjacency list representation
        Map<Integer, List<Integer>> outgoing = new HashMap<>();  // Who each person trusts
        Map<Integer, List<Integer>> incoming = new HashMap<>();  // Who trusts each person

        // Initialize maps
        for (int i = 1; i <= n; i++) {
            outgoing.put(i, new ArrayList<>());
            incoming.put(i, new ArrayList<>());
        }

        // Build the graph
        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];

            outgoing.get(truster).add(trusted);
            incoming.get(trusted).add(truster);
        }

        // Find the judge
        for (int person = 1; person <= n; person++) {
            if (outgoing.get(person).isEmpty() &&  // Trusts nobody
                    incoming.get(person).size() == n - 1) {  // Trusted by everyone else
                return person;
            }
        }

        return -1;
    }

    /**
     * Helper method to validate if a person is indeed the judge
     */
    public static boolean validateJudge(int n, int[][] trust, int candidate) {
        if (candidate == -1) return false;

        Set<Integer> trustedBy = new HashSet<>();
        boolean trustsSomeone = false;

        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];

            if (truster == candidate) {
                trustsSomeone = true;  // Judge trusts someone - invalid
            }
            if (trusted == candidate) {
                trustedBy.add(truster);
            }
        }

        return !trustsSomeone && trustedBy.size() == n - 1;
    }

    /**
     * Helper method to print trust relationships for visualization
     */
    public static void printTrustRelationships(int n, int[][] trust) {
        System.out.println("Trust relationships in the town:");
        if (trust.length == 0) {
            System.out.println("No trust relationships exist.");
            return;
        }

        for (int[] relation : trust) {
            System.out.println("Person " + relation[0] + " trusts Person " + relation[1]);
        }
        System.out.println();
    }

    /**
     * Main method to test all approaches
     */
    public static void main(String[] args) {
        System.out.println("=== Town Judge Problem ===\n");

        // Test cases
        TestCase[] testCases = {
                new TestCase("Example 1", 2, new int[][]{{1, 2}}, 2),
                new TestCase("Example 2", 3, new int[][]{{1, 3}, {2, 3}}, 3),
                new TestCase("Example 3", 3, new int[][]{{1, 3}, {2, 3}, {3, 1}}, -1),
                new TestCase("Single person", 1, new int[][]{}, 1),
                new TestCase("No trust relationships", 4, new int[][]{}, -1),
                new TestCase("Everyone trusts everyone", 3, new int[][]{{1, 2}, {1, 3}, {2, 1}, {2, 3}, {3, 1}, {3, 2}}, -1),
                new TestCase("Large case", 5, new int[][]{{1, 4}, {2, 4}, {3, 4}, {5, 4}}, 4)
        };

        for (TestCase tc : testCases) {
            System.out.println("=== " + tc.name + " ===");
            System.out.println("n = " + tc.n);
            System.out.println("trust = " + Arrays.deepToString(tc.trust));
            printTrustRelationships(tc.n, tc.trust);

            // Test all approaches
            int result1 = findJudgeCountingApproach(tc.n, tc.trust);
            int result2 = findJudgeNetScore(tc.n, tc.trust);
            int result3 = findJudgeSetBased(tc.n, tc.trust);
            int result4 = findJudgeGraphBased(tc.n, tc.trust);

            System.out.println("Counting Approach: " + result1);
            System.out.println("Net Score Approach: " + result2);
            System.out.println("Set-based Approach: " + result3);
            System.out.println("Graph-based Approach: " + result4);
            System.out.println("Expected: " + tc.expected);

            // Validate results
            boolean allMatch = (result1 == result2 && result2 == result3 && result3 == result4);
            boolean correctResult = (result1 == tc.expected);

            System.out.println("All approaches match: " + allMatch);
            System.out.println("Result is correct: " + correctResult);

            if (result1 != -1) {
                System.out.println("Validation check: " + validateJudge(tc.n, tc.trust, result1));
            }

            System.out.println();
        }

        // Performance analysis
        System.out.println("=== Performance Analysis ===");
        System.out.println("All approaches have:");
        System.out.println("Time Complexity: O(trust.length + n)");
        System.out.println("Space Complexity: O(n)");
        System.out.println();
        System.out.println("Recommended approach: Net Score (most elegant and efficient)");
        System.out.println("Alternative: Counting Approach (most intuitive)");
    }

    /**
     * Helper class for test cases
     */
    static class TestCase {
        String name;
        int n;
        int[][] trust;
        int expected;

        TestCase(String name, int n, int[][] trust, int expected) {
            this.name = name;
            this.n = n;
            this.trust = trust;
            this.expected = expected;
        }
    }
}

/*
 * Algorithm Explanation:
 *
 * The Town Judge Problem is essentially finding a node in a directed graph with:
 * 1. In-degree = n-1 (everyone trusts the judge)
 * 2. Out-degree = 0 (judge trusts nobody)
 *
 * Four approaches implemented:
 *
 * 1. Counting Approach:
 *    - Track how many people each person trusts (out-degree)
 *    - Track how many people trust each person (in-degree)
 *    - Find person with out-degree=0 and in-degree=n-1
 *
 * 2. Net Score Approach:
 *    - Calculate net score = in-degree - out-degree
 *    - Judge should have score = n-1
 *    - Most elegant solution
 *
 * 3. Set-based Approach:
 *    - Use sets to track relationships
 *    - Good for understanding the logic
 *
 * 4. Graph-based Approach:
 *    - Build explicit adjacency lists
 *    - Educational value for graph problems
 *
 * Edge Cases Handled:
 * - Single person (n=1): They are the judge
 * - No trust relationships: No judge unless n=1
 * - Circular trust: No judge possible
 * - Multiple potential judges: Only one valid judge allowed
 *
 * Time Complexity: O(trust.length + n) for all approaches
 * Space Complexity: O(n) for all approaches
 */