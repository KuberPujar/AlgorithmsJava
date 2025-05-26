package graphs;

import java.util.*;
import java.util.stream.Collectors;

/*
its result.

Input Format:
First Line: Two integers nandq, separated by a space, where nis the number of equations andq is the number of queries.
Next n Lines: Each line contains two strings aandb representing an equation a/b.
Following Line: n floating-point numbers representing the values of the given n equations.
Next q Lines: Each line contains two strings candd representing a query c/d.
Output Format:
A list of floating-point numbers representing the answers to the given queries. If a query cannot be evaluated, its result is -1.0.
Examples:
Input:

2 5
a b
b c
2.0 3.0
a c
b a
a e
a a
x x
Output:

[6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given equations are a/b = 2.0 and b/c = 3.0.

For the query a/c, we can evaluate as a/b * b/c = 2.0 * 3.0 = 6.0.

For b/a, it's the inverse of a/b, so 1/2.0 = 0.5.

The query a/ecannot be evaluated since there's no information aboute, resulting in -1.0.

For a/a, any variable divided by itself is 1.0.

Since xis not in the equations,x/xalso cannot be evaluated, resulting in-1.0.

Input:

3 3
x y
y z
z w
2.5
3.0
4.0
x w
y x
z w
Output:

[ 30.00000,0.40000,4.00000 ]
Explanation:
x/w : Combine equations = x/y x y/z x z/w = 30.0
similarly y/x = 0.4
and z/w = 4.0
Constraints:
1 <= n <= 20
1 <= q <= 100
Equation forms: "a/b = k" where 1 <= |a|, |b| <= 5.
Each query is of the form "c/d" where 1 <= |c|, |d| <= 5.
Note:The function should return the result. The driver code will handle printing the output.
 */
public class EvaluateDivision {
    /**
     * Calculates the results of division queries based on a set of known equations.
     *
     * @param equations A list of equations, where each equation is a list of two strings [numerator, denominator].
     * @param values    An array of floating-point numbers, where values[i] is the result of equations[i].
     * @param queries   A list of queries, where each query is a list of two strings [numerator, denominator].
     * @return An array of floating-point numbers representing the answers to the queries. -1.0 for un-evaluatable queries.
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // 1. Build the graph
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val =Double.valueOf(values[i]);

            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());

            graph.get(u).put(v, val);
            graph.get(v).put(u, 1.0 / val);
        }

        double[] resultsArray = new double[queries.size()];
        int resultIndex = 0;

        // 2. Process queries
        for (List<String> query : queries) {
            String c = query.get(0); // numerator of query
            String d = query.get(1); // denominator of query

            if (!graph.containsKey(c) || !graph.containsKey(d)) {
                resultsArray[resultIndex++] = -1.0;
                continue;
            }

            if (c.equals(d)) {
                resultsArray[resultIndex++] = 1.0;
                continue;
            }

            Set<String> visited = new HashSet<>();
            double ans = dfs(c, d, 1.0, visited, graph);
            resultsArray[resultIndex++] = ans;
        }
        return resultsArray;
    }

    /**
     * Performs Depth First Search to find a path from 'current' node to 'target' node.
     *
     * @param current   The current node in the DFS traversal.
     * @param target    The target node we are trying to reach.
     * @param product   The accumulated product of edge weights along the current path.
     * @param visited   A set of nodes already visited in the current DFS path.
     * @param graph     The graph representing variable relationships.
     * @return The product of edge weights if a path is found, otherwise -1.0.
     */
    private static double dfs(String current, String target, double product, Set<String> visited, Map<String, Map<String, Double>> graph) {
        if (current.equals(target)) {
            return product;
        }

        visited.add(current);
        Map<String, Double> neighbors = graph.get(current); // Will not be null due to prior checks

        if (neighbors != null) { // Should always be true if current is in graph
            for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
                String neighborNode = entry.getKey();
                double edgeValue = entry.getValue();

                if (!visited.contains(neighborNode)) {
                    double result = dfs(neighborNode, target, product * edgeValue, visited, graph);
                    if (result != -1.0) {
                        // No need to remove current from visited here, as this path was successful.
                        // The removal happens during backtracking if all paths from 'current' fail.
                        return result;
                    }
                }
            }
        }

        // Backtracking: Remove 'current' from visited if no path was found through it from this call
        visited.remove(current);
        return -1.0;
    }


    // Main method for local testing, mimicking driver code behavior based on problem description.
    public static void main(String[] args) {
        // Example 1 from problem description
        System.out.println("Example 1:");
        List<List<String>> equations1 = new ArrayList<>();
        equations1.add(Arrays.asList("a", "b"));
        equations1.add(Arrays.asList("b", "c"));
        double[] values1 = {2.0, 3.0};
        List<List<String>> queries1 = new ArrayList<>();
        queries1.add(Arrays.asList("a", "c"));
        queries1.add(Arrays.asList("b", "a"));
        queries1.add(Arrays.asList("a", "e"));
        queries1.add(Arrays.asList("a", "a"));
        queries1.add(Arrays.asList("x", "x"));

        double[] results1 = calcEquation(equations1, values1, queries1);
        // Output formatting to match example (5 decimal places)
        System.out.print("[");
        for (int i = 0; i < results1.length; i++) {
            System.out.printf("%.5f", results1[i]);
            if (i < results1.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
        // Expected: [6.00000,0.50000,-1.00000,1.00000,-1.00000]

        System.out.println("\nExample 2 from problem description:");
        List<List<String>> equations2 = new ArrayList<>();
        equations2.add(Arrays.asList("x", "y"));
        equations2.add(Arrays.asList("y", "z"));
        equations2.add(Arrays.asList("z", "w"));
        double[] values2 = {2.5, 3.0, 4.0};
        List<List<String>> queries2 = new ArrayList<>();
        queries2.add(Arrays.asList("x", "w"));
        queries2.add(Arrays.asList("y", "x"));
        queries2.add(Arrays.asList("z", "w"));

        double[] results2 = calcEquation(equations2, values2, queries2);
        System.out.print("[");
        for (int i = 0; i < results2.length; i++) {
            System.out.printf("%.5f", results2[i]);
            if (i < results2.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
        // Expected: [30.00000,0.40000,4.00000]
    }
}
