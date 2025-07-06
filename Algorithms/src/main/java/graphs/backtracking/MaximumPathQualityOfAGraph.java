package graphs.backtracking;

import java.util.*;

/*
There is an undirected graph with n nodes numbered from 0 to n - 1 (inclusive). You are given a 0-indexed integer array values where values[i] is the value of the ith node. You are also given a 0-indexed 2D integer array edges, where each edges[j] = [uj, vj, timej] indicates that there is an undirected edge between the nodes uj and vj, and it takes timej seconds to travel between the two nodes. Finally, you are given an integer maxTime.

A valid path in the graph is any path that starts at node 0, ends at node 0, and takes at most maxTime seconds to complete. You may visit the same node multiple times. The quality of a valid path is the sum of the values of the unique nodes visited in the path (each node's value is added at most once to the sum).

Return the maximum quality of a valid path.

Note: There are at most four edges connected to each node.



Example 1:


Input: values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
Output: 75
Explanation:
One possible path is 0 -> 1 -> 0 -> 3 -> 0. The total time taken is 10 + 10 + 10 + 10 = 40 <= 49.
The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 + 43 = 75.
Example 2:


Input: values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
Output: 25
Explanation:
One possible path is 0 -> 3 -> 0. The total time taken is 10 + 10 = 20 <= 30.
The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 = 25.
Example 3:


Input: values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
Output: 7
Explanation:
One possible path is 0 -> 1 -> 3 -> 1 -> 0. The total time taken is 10 + 13 + 13 + 10 = 46 <= 50.
The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 + 4 = 7.


Constraints:

n == values.length
1 <= n <= 1000
0 <= values[i] <= 108
0 <= edges.length <= 2000
edges[j].length == 3
0 <= uj < vj <= n - 1
10 <= timej, maxTime <= 100
All the pairs [uj, vj] are unique.
There are at most four edges connected to each node.
The graph may not be connected.
 */
public class MaximumPathQualityOfAGraph {
    //Java solution using backtracking to find the maximum quality path that starts and ends at node 0 within the given maxTime:
    /*
    Explanation:

    Graph Construction:

        We build an adjacency list to represent the graph, where each node points to its neighbors along with the travel time.

    Backtracking Approach:

        Start at node 0 with time 0 and an empty set of visited nodes.

        For each node:

            Add its value to the quality if it's the first visit

            Update the maximum quality if we're back at node 0

            Recursively explore all neighbors that can be reached within the remaining time

    Optimizations:

        We pass a new copy of the visited set to each recursive call to avoid modification issues

        Early termination when time exceeds maxTime

        Takes advantage of the constraint that each node has at most 4 edges

Key Features:

    Time Complexity: O(4^d) where d is the depth of recursion (practical due to maxTime constraint)

    Space Complexity: O(n + e) for storing the graph and recursion stack

    Correctness:

        Properly tracks unique node visits

        Ensures path starts and ends at node 0

        Respects the maxTime constraint

    Edge Cases:

        Handles disconnected graphs

        Works with maximum constraint values
     */
    private int maxQuality;
    private int[] values;
    private List<List<int[]>> adj;
    private int maxTime;
    //for memoization
    private Map<String, Integer> memo;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        this.values = values;
        this.maxTime = maxTime;
        this.maxQuality = 0;

        // Build adjacency list
        int n = values.length;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];
            adj.get(u).add(new int[]{v, time});
            adj.get(v).add(new int[]{u, time});
        }

        // Start backtracking from node 0
        backtrack(0, 0, new HashSet<>(), 0);

        return maxQuality;
    }

    private void backtrack(int currentNode, int currentTime, Set<Integer> visited, int currentQuality) {
        // Add current node's value if not already visited
        boolean isNewNode = !visited.contains(currentNode);
        if (isNewNode) {
            currentQuality += values[currentNode];
            visited.add(currentNode);
        }

        // If we're back at node 0, update max quality
        if (currentNode == 0) {
            maxQuality = Math.max(maxQuality, currentQuality);
        }

        // Explore all neighbors
        for (int[] neighborInfo : adj.get(currentNode)) {
            int neighbor = neighborInfo[0];
            int time = neighborInfo[1];

            // Only proceed if we have enough time
            if (currentTime + time <= maxTime) {
                backtrack(neighbor, currentTime + time, new HashSet<>(visited), currentQuality);
            }
        }
    }

    //For larger inputs, we could add memoization to avoid redundant calculations:
    public int maximalPathQuality1(int[] values, int[][] edges, int maxTime) {
        this.values = values;
        this.maxTime = maxTime;
        this.maxQuality = 0;
        this.memo = new HashMap<>();

        // Build adjacency list (same as before)
        // ...

        backtrack1(0, 0, new HashSet<>(), 0);
        return maxQuality;
    }

    private void backtrack1(int currentNode, int currentTime, Set<Integer> visited, int currentQuality) {
        String key = currentNode + ":" + currentTime + ":" + visited.toString();
        if (memo.containsKey(key)) {
            return;
        }

        boolean isNewNode = !visited.contains(currentNode);
        if (isNewNode) {
            currentQuality += values[currentNode];
            visited.add(currentNode);
        }

        // If we're back at node 0, update max quality
        if (currentNode == 0) {
            maxQuality = Math.max(maxQuality, currentQuality);
        }

        // Explore all neighbors
        for (int[] neighborInfo : adj.get(currentNode)) {
            int neighbor = neighborInfo[0];
            int time = neighborInfo[1];

            // Only proceed if we have enough time
            if (currentTime + time <= maxTime) {
                backtrack(neighbor, currentTime + time, new HashSet<>(visited), currentQuality);
            }
        }

            memo.put(key, maxQuality);
    }

    public static void main(String[] args) {
        MaximumPathQualityOfAGraph  maximumPathQualityOfAGraph=new MaximumPathQualityOfAGraph();
        //Input: values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
        int[] values={0,32,10,43};
        int[][] edges={{0,1,10},{1,2,15},{0,3,10}};
        int maxTime=49;
        int output= maximumPathQualityOfAGraph.maximalPathQuality(values,edges,maxTime);
        System.out.println(output);//Output: 75


        //Input: values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
        int[] values1={5,10,15,20};
        int[][] edges1={{0,1,10},{1,2,10},{0,3,10}};
        int maxTime1=30;
        int output1= maximumPathQualityOfAGraph.maximalPathQuality(values1,edges1,maxTime1);
        System.out.println(output1);//Output: 25

        //Input: values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
        int[] values2={1,2,3,4};
        int[][] edges2={{0,1,10},{1,2,11},{2,3,12},{1,3,13}};
        int maxTime2=50;
        int output2= maximumPathQualityOfAGraph.maximalPathQuality1(values2,edges2,maxTime2);
        System.out.println(output2); //Output: 7

    }
}
