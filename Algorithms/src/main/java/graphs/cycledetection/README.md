Cycle Detection in Graphs involves identifying the presence of cycles or loops in a graph, often accomplished using algorithms like DFS or Floyd's cycle-finding algorithm.
# Cycle Detection in Graphs
Cycle detection is a fundamental problem in graph theory, where the goal is to determine whether a given graph contains any cycles. A cycle is a path that starts and ends at the same vertex without traversing any edge more than once.
There are several algorithms to detect cycles in graphs, depending on whether the graph is directed or undirected.
## Algorithms for Cycle Detection
### 1. Depth-First Search (DFS)
DFS can be used to detect cycles in both directed and undirected graphs. The basic idea is to traverse the graph using DFS and keep track of visited nodes. If we encounter a node that is already in the current path, a cycle exists.
### 2. Union-Find (Disjoint Set Union)
The Union-Find algorithm can be used to detect cycles in undirected graphs. It works by maintaining a set of connected components and checking if two vertices belong to the same component before connecting them. If they do, a cycle is detected.
### 3. Floyd's Cycle-Finding Algorithm
Floyd's algorithm is typically used for linked lists but can be adapted for graphs. It uses two pointers (slow and fast) to traverse the graph. If the two pointers meet, a cycle exists.
### 4. Topological Sorting
Topological sorting can be used to detect cycles in directed graphs. If a topological sort is not possible (i.e., the graph has a cycle), it indicates the presence of a cycle.
## Implementation
### Example: Cycle Detection in Directed Graphs using DFS
```java
import java.util.*;
public class CycleDetectionDFS {
    private Map<Integer, List<Integer>> graph;
    private Set<Integer> visited;
    private Set<Integer> recStack;

    public CycleDetectionDFS() {
        graph = new HashMap<>();
        visited = new HashSet<>();
        recStack = new HashSet<>();
    }

    public void addEdge(int src, int dest) {
        graph.putIfAbsent(src, new ArrayList<>());
        graph.get(src).add(dest);
    }

    public boolean hasCycle() {
        for (int node : graph.keySet()) {
            if (detectCycle(node)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycle(int node) {
        if (recStack.contains(node)) {
            return true; // Cycle detected
        }
        if (visited.contains(node)) {
            return false; // Already visited
        }

        visited.add(node);
        recStack.add(node);

        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (detectCycle(neighbor)) {
                return true;
            }
        }

        recStack.remove(node);
        return false;
    }

    public static void main(String[] args) {
        CycleDetectionDFS cycleDetector = new CycleDetectionDFS();
        cycleDetector.addEdge(1, 2);
        cycleDetector.addEdge(2, 3);
        cycleDetector.addEdge(3, 1); // This creates a cycle

        System.out.println("Graph has cycle: " + cycleDetector.hasCycle());
    }
}
```
### Example: Cycle Detection in Undirected Graphs using Union-Find
```java
import java.util.*;
public class CycleDetectionUnionFind {
    private int[] parent;
    private int[] rank;

    public CycleDetectionUnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]); // Path compression
        }
        return parent[node];
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) {
            throw new IllegalStateException("Cycle detected!");
        }

        if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
        } else {
            parent[root2] = root1;
            rank[root1]++;
        }
    }

    public static void main(String[] args) {
        CycleDetectionUnionFind cycleDetector = new CycleDetectionUnionFind(5);
        cycleDetector.union(0, 1);
        cycleDetector.union(1, 2);
        cycleDetector.union(2, 0); // This creates a cycle

        try {
            cycleDetector.union(3, 4);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
```
### Example: Cycle Detection using Floyd's Algorithm
```java
import java.util.*;
public class CycleDetectionFloyd {
    private Map<Integer, List<Integer>> graph;

    public CycleDetectionFloyd() {
        graph = new HashMap<>();
    }

    public void addEdge(int src, int dest) {
        graph.putIfAbsent(src, new ArrayList<>());
        graph.get(src).add(dest);
    }

    public boolean hasCycle() {
        for (int start : graph.keySet()) {
            if (detectCycle(start)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycle(int start) {
        Set<Integer> slow = new HashSet<>();
        Set<Integer> fast = new HashSet<>();
        slow.add(start);
        fast.add(start);

        while (!fast.isEmpty()) {
            for (int node : slow) {
                for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                    if (fast.contains(neighbor)) {
                        return true; // Cycle detected
                    }
                    fast.add(neighbor);
                }
            }
            slow.addAll(fast);
            fast.clear();
        }
        return false;
    }

    public static void main(String[] args) {
        CycleDetectionFloyd cycleDetector = new CycleDetectionFloyd();
        cycleDetector.addEdge(1, 2);
        cycleDetector.addEdge(2, 3);
        cycleDetector.addEdge(3, 1); // This creates a cycle

        System.out.println("Graph has cycle: " + cycleDetector.hasCycle());
    }
}
```
### Example: Cycle Detection using Topological Sorting
```java
import java.util.*;
public class CycleDetectionTopological {
    private Map<Integer, List<Integer>> graph;
    private Set<Integer> visited;
    private Set<Integer> recStack;

    public CycleDetectionTopological() {
        graph = new HashMap<>();
        visited = new HashSet<>();
        recStack = new HashSet<>();
    }

    public void addEdge(int src, int dest) {
        graph.putIfAbsent(src, new ArrayList<>());
        graph.get(src).add(dest);
    }

    public boolean hasCycle() {
        for (int node : graph.keySet()) {
            if (detectCycle(node)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycle(int node) {
        if (recStack.contains(node)) {
            return true; // Cycle detected
        }
        if (visited.contains(node)) {
            return false; // Already visited
        }

        visited.add(node);
        recStack.add(node);

        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (detectCycle(neighbor)) {
                return true;
            }
        }

        recStack.remove(node);
        return false;
    }

    public static void main(String[] args) {
        CycleDetectionTopological cycleDetector = new CycleDetectionTopological();
        cycleDetector.addEdge(1, 2);
        cycleDetector.addEdge(2, 3);
        cycleDetector.addEdge(3, 1); // This creates a cycle

        System.out.println("Graph has cycle: " + cycleDetector.hasCycle());
    }
}
```
### Conclusion
Cycle detection is a crucial aspect of graph algorithms, with various methods available depending on the type of graph. The choice of algorithm can significantly affect performance and complexity, especially for large graphs. Understanding these algorithms is essential for solving problems related to cycles in graphs effectively.
### References
- [Cycle Detection in Graphs - GeeksforGeeks](https://www.geeksforgeeks.org/detect-cycle-in-a-graph/)
- [Cycle Detection in Directed Graphs - LeetCode](https://leetcode.com/problems/course-schedule/)
- [Cycle Detection in Undirected Graphs - LeetCode](https://leetcode.com/problems/graph-valid-tree/)
- [Union-Find Algorithm - Wikipedia](https://en.wikipedia.org/wiki/Disjoint-set_data_structure)
- [Floyd's Cycle-Finding Algorithm - Wikipedia](https://en.wikipedia.org/wiki/Cycle_detection)
- [Topological Sorting - Wikipedia](https://en.wikipedia.org/wiki/Topological_sorting)
- [Graph Theory - Wikipedia](https://en.wikipedia.org/wiki/Graph_theory)
- [Graph Algorithms - Wikipedia](https://en.wikipedia.org/wiki/Graph_algorithm)
- [Cycle Detection Algorithms - Wikipedia](https://en.wikipedia.org/wiki/Cycle_detection_algorithm)
- [Graph Traversal Algorithms - Wikipedia](https://en.wikipedia.org/wiki/Graph_traversal)
- [Depth-First Search - Wikipedia](https://en.wikipedia.org/wiki/Depth-first_search)
- [Breadth-First Search - Wikipedia](https://en.wikipedia.org/wiki/Breadth-first_search)
- [Graph Representation - Wikipedia](https://en.wikipedia.org/wiki/Graph_representation)
- [Graph Data Structures - Wikipedia](https://en.wikipedia.org/wiki/Graph_data_structure)
- [Graph Theory Basics - Wikipedia](https://en.wikipedia.org/wiki/Graph_theory#Basics)
- [Graph Algorithms Overview - Wikipedia](https://en.wikipedia.org/wiki/Graph_algorithm#Overview)
- [Graph Cycle Detection - Wikipedia](https://en.wikipedia.org/wiki/Cycle_detection)
- [Graph Theory Applications - Wikipedia](https://en.wikipedia.org/wiki/Graph_theory#Applications)
- [Graph Algorithms in Java - GeeksforGeeks](https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/)
- [Graph Algorithms in Python - GeeksforGeeks](https://www.geeksforgeeks.org/python-graph-data-structure-and-algorithms/)
- [Graph Algorithms in C++ - GeeksforGeeks](https://www.geeksforgeeks.org/c-graph-data-structure-and-algorithms/)