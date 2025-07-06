package graphs.simplegraph;
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

        public int findJudge(int n, int[][] trust) {
            // Arrays to track trust counts
            int[] trustCount = new int[n + 1];  // How many people trust this person
            int[] trustedCount = new int[n + 1]; // How many people this person trusts

            for (int[] relation : trust) {
                int a = relation[0]; // truster
                int b = relation[1]; // trusted
                trustCount[b]++;
                trustedCount[a]++;
            }

            // Check for the judge condition:
            // 1. Trusted by everyone else (n-1 people)
            // 2. Trusts nobody
            for (int i = 1; i <= n; i++) {
                if (trustCount[i] == n - 1 && trustedCount[i] == 0) {
                    return i;
                }
            }

            return -1;
        }

        public int findJudgeSingleArray(int n, int[][] trust) {
            int[] netTrust = new int[n + 1]; // net trust balance

            for (int[] relation : trust) {
                netTrust[relation[0]]--; // truster loses a point
                netTrust[relation[1]]++; // trusted gains a point
            }

            for (int i = 1; i <= n; i++) {
                if (netTrust[i] == n - 1) {
                    return i;
                }
            }

            return -1;
        }

    public static void main(String[] args) {
        FindTheTownJudge finder = new FindTheTownJudge();

        // Example 1
        int n1 = 2;
        int[][] trust1 = {{1, 2}};
        System.out.println(finder.findJudge(n1, trust1)); // Output: 2

        // Example 2
        int n2 = 3;
        int[][] trust2 = {{1, 3}, {2, 3}};
        System.out.println(finder.findJudge(n2, trust2)); // Output: 3

        // Example 3
        int n3 = 3;
        int[][] trust3 = {{1, 3}, {2, 3}, {3, 1}};
        System.out.println(finder.findJudge(n3, trust3)); // Output: -1
    }
}
