Strongly Connected Components are subgraphs in a directed graph where every vertex is reachable from every other vertex, typically identified using algorithms like Tarjan's or Kosaraju's algorithm.
# Strongly Connected Components
This package contains implementations of algorithms to find strongly connected components (SCCs) in directed graphs. SCCs are subgraphs where every vertex is reachable from every other vertex within the same subgraph.
## Algorithms
- **Tarjan's Algorithm**: This algorithm uses depth-first search (DFS) to find SCCs in a directed graph. It maintains a stack to keep track of the nodes and uses low-link values to identify the root of each SCC.
- **Kosaraju's Algorithm**: This algorithm involves two passes of DFS. The first pass computes the finishing times of nodes, and the second pass processes nodes in decreasing order of their finishing times to discover SCCs.
- **Gabow's Algorithm**: This algorithm is similar to Tarjan's but uses a different approach to manage the stack and low-link values, providing an efficient way to find SCCs.
- **Path-based Algorithm**: This algorithm identifies SCCs by exploring paths in the graph and grouping nodes based on reachability, providing an alternative method to find SCCs without relying on DFS.
- **Component-based Algorithm**: This algorithm builds components based on the reachability of nodes, allowing for a more structured approach to finding SCCs in directed graphs.
- **Biconnected Components**: This algorithm finds biconnected components in undirected graphs, which are maximal subgraphs where any two vertices are connected by two disjoint paths, ensuring robustness in connectivity.
- **Bridges**: This algorithm identifies bridges in a graph, which are edges that, when removed, increase the number of connected components, thus playing a crucial role in understanding the structure of the graph.
- **Articulation Points**: This algorithm finds articulation points in a graph, which are vertices that, when removed, increase the number of connected components, highlighting critical nodes in the graph's connectivity.
- **Directed Bridges**: This algorithm identifies directed bridges in a directed graph, which are edges that, when removed, increase the number of strongly connected components, providing insights into the graph's structure.
- **Directed Articulation Points**: This algorithm finds directed articulation points in a directed graph, which are vertices that, when removed, increase the number of strongly connected components, helping to identify critical nodes in the directed graph's connectivity.
- **Directed Biconnected Components**: This algorithm finds directed biconnected components in a directed graph, which are maximal subgraphs where any two vertices are connected by two disjoint paths, ensuring robustness in directed connectivity.
- **Directed Bridges and Articulation Points**: This algorithm combines the identification of directed bridges and articulation points, providing a comprehensive view of critical edges and vertices in a directed graph.
- **Directed Component-based Algorithm**: This algorithm builds directed components based on the reachability of nodes in a directed graph, allowing for a structured approach to finding SCCs in directed graphs.
- **Directed Path-based Algorithm**: This algorithm identifies SCCs in directed graphs by exploring paths and grouping nodes based on reachability, providing an alternative method to find SCCs without relying on DFS.
- **Directed Gabow's Algorithm**: This algorithm is a directed version of Gabow's algorithm, efficiently finding SCCs in directed graphs using a stack and low-link values, similar to Tarjan's approach but with a directed focus.
- **Directed Kosaraju's Algorithm**: This algorithm is a directed version of Kosaraju's algorithm, which uses two passes of DFS to find SCCs in directed graphs, ensuring that all nodes are processed in the correct order based on their finishing times.
- **Directed Tarjan's Algorithm**: This algorithm is a directed version of Tarjan's algorithm, using depth-first search to find SCCs in directed graphs, maintaining a stack and low-link values to identify the root of each SCC efficiently.
- **Directed Component-based Algorithm**: This algorithm builds directed components based on the reachability of nodes in a directed graph, allowing for a structured approach to finding SCCs in directed graphs.
- **Directed Path-based Algorithm**: This algorithm identifies SCCs in directed graphs by exploring paths and grouping nodes based on reachability, providing an alternative method to find SCCs without relying on DFS.
- **Directed Gabow's Algorithm**: This algorithm is a directed version of Gabow's algorithm, efficiently finding SCCs in directed graphs using a stack and low-link values, similar to Tarjan's approach but with a directed focus.
- **Directed Kosaraju's Algorithm**: This algorithm is a directed version of Kosaraju's algorithm, which uses two passes of DFS to find SCCs in directed graphs, ensuring that all nodes are processed in the correct order based on their finishing times.
- **Directed Tarjan's Algorithm**: This algorithm is a directed version of Tarjan's algorithm, using depth-first search to find SCCs in directed graphs, maintaining a stack and low-link values to identify the root of each SCC efficiently.

## Usage
To use the algorithms in this package, you can create an instance of the graph and call the respective algorithm method to find the strongly connected components. Each algorithm typically returns a list of SCCs, where each SCC is represented as a collection of vertices.
## Example
```java
import graphs.stronglyconnectedcomponents.TarjanSCC;
import graphs.stronglyconnectedcomponents.Graph;
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        // Add vertices and edges to the graph
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);
        
        TarjanSCC tarjan = new TarjanSCC(graph);
        List<Set<Integer>> sccs = tarjan.findSCCs();
        
        System.out.println("Strongly Connected Components: " + sccs);
    }
}
```
This example demonstrates how to create a directed graph, add edges, and use Tarjan's algorithm to find and print the strongly connected components of the graph.
## Dependencies
This package may depend on standard Java libraries for data structures like `List`, `Set`, and `Map`. Ensure you have the necessary imports in your Java files to utilize these data structures effectively.
## Performance
The performance of the algorithms in this package varies based on the specific algorithm used and the structure of the graph. Generally, Tarjan's and Kosaraju's algorithms run in O(V + E) time, where V is the number of vertices and E is the number of edges in the graph. Gabow's algorithm also operates within similar time complexity bounds, making these algorithms efficient for large directed graphs.
## Contributing
Contributions to this package are welcome! If you have improvements, optimizations, or additional algorithms to suggest, please submit a pull request or open an issue in the repository. Ensure that your code adheres to the project's coding standards and includes appropriate tests for any new functionality.