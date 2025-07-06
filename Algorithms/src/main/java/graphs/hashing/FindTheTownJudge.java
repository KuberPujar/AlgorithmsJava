package graphs.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi. If a trust relationship does not exist in trust array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.



Example 1:

Input: n = 2, trust = [[1,2]]
Output: 2
Example 2:

Input: n = 3, trust = [[1,3],[2,3]]
Output: 3
Example 3:

Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1


Constraints:

1 <= n <= 1000
0 <= trust.length <= 104
trust[i].length == 2
All the pairs of trust are unique.
ai != bi
1 <= ai, bi <= n
 */
public class FindTheTownJudge {
    //Counting Approach
    public int findJudge1(int n, int[][] trust) {
        int[] trustCounts = new int[n + 1]; // Index 0 unused

        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];
            trustCounts[truster]--; // Person trusts someone
            trustCounts[trusted]++; // Person is trusted
        }

        for (int i = 1; i <= n; i++) {
            // Judge condition: trusted by n-1 people and trusts nobody
            if (trustCounts[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }

    //Backtracking approach
    public int findJudge(int n, int[][] trust) {
        // Build trust relationships
        Map<Integer, Set<Integer>> trustMap = new HashMap<>();
        Set<Integer> possibleJudges = new HashSet<>();

        // Initialize all people as potential judges
        for (int i = 1; i <= n; i++) {
            possibleJudges.add(i);
        }

        // Process trust relationships
        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];

            // Person who trusts someone cannot be judge
            possibleJudges.remove(truster);

            // Build trust map
            trustMap.putIfAbsent(trusted, new HashSet<>());
            trustMap.get(trusted).add(truster);
        }

        // Backtracking to verify potential judges
        for (int candidate : possibleJudges) {
            if (isJudge(candidate, n, trustMap)) {
                return candidate;
            }
        }

        return -1;
    }

    private boolean isJudge(int candidate, int n, Map<Integer, Set<Integer>> trustMap) {
        // Judge must be trusted by everyone else
        Set<Integer> trusters = trustMap.getOrDefault(candidate, new HashSet<>());
        return trusters.size() == n - 1;
    }

    public static void main(String[] args) {
        FindTheTownJudge findTheTownJudge=new FindTheTownJudge();
       // Input: n = 2, trust = [[1,2]]
        int n=2;
        int[][] trust={{1,2}};
       int output= findTheTownJudge.findJudge1(n,trust);//Output: 2
        System.out.println(output);
        //Input: n = 3, trust = [[1,3],[2,3]]
        int n1=3;
        int[][] trust1={{1,3},{2,3}};
       int output1= findTheTownJudge.findJudge2(n1,trust1);//Output: 3
        System.out.println(output1);
        //Input: n = 3, trust = [[1,3],[2,3],[3,1]]
        int n2=3;
        int[][] trust2={{1,3},{2,3},{3,1}};
       int output2= findTheTownJudge.findJudge(n2,trust2);//Output: -1
        System.out.println(output2);
    }

    //Using hashing
    public int findJudge2(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) return 1; // Edge case

        Set<Integer> trusters = new HashSet<>();
        Map<Integer, Integer> trustedCount = new HashMap<>();

        // Process all trust relationships
        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];

            trusters.add(truster); // People who trust someone
            trustedCount.put(trusted, trustedCount.getOrDefault(trusted, 0) + 1);
        }

        // Check potential judges (people who don't trust anyone)
        for (int i = 1; i <= n; i++) {
            if (!trusters.contains(i) && trustedCount.getOrDefault(i, 0) == n - 1) {
                return i;
            }
        }

        return -1;
    }
}
/*
Explanation:

    Optimal Solution (Counting Approach):

        We maintain an array where we:

            Decrement count when a person trusts someone

            Increment count when a person is trusted

        The judge will have count = n-1 (trusted by everyone else) and won't appear as a truster

    Backtracking Solution:

        First eliminate all people who trust others (they can't be judges)

        For remaining candidates, verify they're trusted by all others

        This is less efficient (O(n) vs O(n + trust.length)) but demonstrates backtracking

Example Walkthrough (Example 2):

Input: n = 3, trust = [[1,3],[2,3]]

    Possible judges starts as {1,2,3}

    After processing trust relationships:

        Remove 1 (trusts 3)

        Remove 2 (trusts 3)

        Possible judges = {3}

    Verify 3 is trusted by 1 and 2 → true → return 3

The counting solution is preferred for efficiency, but the backtracking version shows how you might approach it with that technique.
 */
