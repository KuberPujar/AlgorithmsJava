package graphs.unionfind;

import java.util.*;

/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Double> weight = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Initialize Union-Find structure
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double value = values[i];

            if (!parent.containsKey(u)) {
                parent.put(u, u);
                weight.put(u, 1.0);
            }
            if (!parent.containsKey(v)) {
                parent.put(v, v);
                weight.put(v, 1.0);
            }

            union(u, v, value);
        }

        // Process queries
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);

            if (!parent.containsKey(x) || !parent.containsKey(y)) {
                results[i] = -1.0;
                continue;
            }

            String rootX = find(x);
            String rootY = find(y);

            if (!rootX.equals(rootY)) {
                results[i] = -1.0;
            } else {
                results[i] = weight.get(x) / weight.get(y);
            }
        }

        return results;
    }

    private String find(String node) {
        if (!parent.get(node).equals(node)) {
            String origParent = parent.get(node);
            parent.put(node, find(origParent));
            weight.put(node, weight.get(node) * weight.get(origParent));
        }
        return parent.get(node);
    }

    private void union(String u, String v, double value) {
        String rootU = find(u);
        String rootV = find(v);

        if (rootU.equals(rootV)) return;

        parent.put(rootU, rootV);
        weight.put(rootU, value * weight.get(v) / weight.get(u));
    }

    public static void main(String[] args) {
        //Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        List<List<String>> equations=List.of(List.of("a","b"),List.of("b","c"),List.of("bc","cd"));
        double[] values={1.5,2.5,5.0};
        List<List<String>> queries=List.of(List.of("a","c"),List.of("c","b"),List.of("bc","cd"),List.of("cd","bc"));
        EvaluateDivision evaluateDivision=new EvaluateDivision();
        double[] doubles = evaluateDivision.calcEquation(equations, values, queries);
        printResults(doubles);//Output: [3.75000,0.40000,5.00000,0.20000]
    }

    public static void printResults(double[] results) {
        System.out.print("[");
        for (int i = 0; i < results.length; i++) {
            System.out.printf("%.5f", results[i]);
            if (i < results.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
