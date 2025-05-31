package graphalgorithms.bfsdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
Minimum Broadcast Time
Calculate the minimum time required for a signal to reach all nodes in a directed network, starting from a specified node. Each connection in the network has an associated signal delay.

Input Format:

node: An integer representing the number of nodes in the network (1 <= nodes <= 100).
edges: A list of directed edges where each edge is represented as [source, destination, delay]. It denotes a directed connection from source to destination with a signal delay of delay units of time.
startNode: An integer representing the starting node for the signal broadcast (1 <= startNode <= nodes).
Output Format:

An integer representing the minimum time required for the signal to reach all nodes. If it's not possible to reach all nodes from the start node, return -1.
Examples:

Input:

nodes = 4
edges = [[2, 1, 1], [2, 3, 1], [3, 4, 1]]
startNode = 2
Output:
2

Explanation:
The signal travels from Node 2 to Nodes 1 and 3 with a delay of 1 unit each. Then, from Node 3 to Node 4 with another 1 unit of delay. Thus, the total time taken is 2 units.

Input:

nodes = 5
edges = [[1, 2, 1], [2, 3, 2], [3, 4, 3], [4, 5, 4], [5, 1, 5]]
startNode = 1
Output:
10

Explanation: The signal sequentially travels through all nodes, taking a total of 10 units of time.

Constraints:

1 <= nodes <= 100
0 <= len(edges) <= 5,000
1 <= delay on each edge <= 100`
Note:The function should return the result. The driver code will handle printing the output.
 */
public class MinimumBroadcastTime {
    @SuppressWarnings("unchecked")
    public static int minBroadcastTime(int nodes, List<List<Integer>> edges, int startNode) {
        int[] minTime = new int[nodes + 1];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[startNode] = 0;

        List<int[]>[] graph = new ArrayList[nodes + 1];
        for (int i = 1; i <= nodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);
            graph[u].add(new int[]{v, w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, startNode});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int node = current[1];

            if (time > minTime[node]) continue;

            for (int[] neighbor : graph[node]) {
                int nextNode = neighbor[0];
                int delay = neighbor[1];
                int newTime = time + delay;
                if (newTime < minTime[nextNode]) {
                    minTime[nextNode] = newTime;
                    pq.offer(new int[]{newTime, nextNode});
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= nodes; i++) {
            if (minTime[i] == Integer.MAX_VALUE) {
                return -1;
            }
            if (minTime[i] > maxTime) {
                maxTime = minTime[i];
            }
        }

        return maxTime;
    }

    public static void main(String[] args) {
        // Example usage
        int nodes = 4;
        List<List<Integer>> edges = Arrays.asList(
            Arrays.asList(2, 1, 1),
            Arrays.asList(2, 3, 1),
            Arrays.asList(3, 4, 1)
        );
        int startNode = 2;

        int result = minBroadcastTime(nodes, edges, startNode);
        System.out.println(result); // Output: 2
    }
}
