A tree data structure in which each node can have more than two children.
# N-ary Tree
An N-ary tree is a tree data structure where each node can have at most N children. This is a generalization of binary trees, where each node can have up to two children.
N-ary trees are useful for representing hierarchical data structures, such as file systems, organizational structures, and more.
# Characteristics
- **Node**: Each element in the tree is called a node.
- **Root**: The topmost node in the tree is called the root.
- **Leaf Node**: A node with no children is called a leaf node.
- **Subtree**: A subtree is a tree formed by a node and all its descendants.
- **Height**: The height of a tree is the length of the longest path from the root to a leaf node.
- **Depth**: The depth of a node is the length of the path from the root to that node.
- **Degree**: The degree of a node is the number of children it has.
- **Level**: The level of a node is defined by its depth, with the root at level 0.
- **Sibling**: Nodes that share the same parent are called siblings.
- **Ancestor**: A node is an ancestor of another node if it is on the path from the root to that node.
- **Descendant**: A node is a descendant of another node if it is on the path from that node to a leaf node.
- **Path**: A path is a sequence of nodes and edges connecting a node with a descendant.
- **Forest**: A collection of one or more disjoint trees is called a forest.
- **Binary Tree**: A special case of an N-ary tree where N is 2, meaning each node can have at most two children.
- **Full N-ary Tree**: An N-ary tree is full if every node has either 0 or N children.
- **Complete N-ary Tree**: An N-ary tree is complete if all levels are fully filled except possibly for the last level, which is filled from left to right.
- **Balanced N-ary Tree**: An N-ary tree is balanced if the height of the left and right subtrees of any node differ by at most one.
- **Height of N-ary Tree**: The height of an N-ary tree is the maximum depth of any node in the tree.
- **Size of N-ary Tree**: The size of an N-ary tree is the total number of nodes in the tree.
- **Traversal**: Common traversal methods for N-ary trees include:
  - **Pre-order Traversal**: Visit the root, then recursively visit each child.
  - **Post-order Traversal**: Recursively visit each child, then visit the root.
  - **Level-order Traversal**: Visit nodes level by level from top to bottom.
  - **Depth-first Search (DFS)**: Explore as far as possible along each branch before backtracking.
  - **Breadth-first Search (BFS)**: Explore all neighbors at the present depth prior to moving on to nodes at the next depth level.
  - **In-order Traversal**: Not typically applicable to N-ary trees as it is specific to binary trees.
  - **Reverse Level-order Traversal**: Visit nodes from bottom to top, level by level.
  - **Zigzag Level-order Traversal**: Alternate the direction of traversal at each level.
  - **Boundary Traversal**: Visit the boundary nodes of the tree in a specific order (left boundary, leaves, right boundary).
  - **Spiral Level-order Traversal**: Traverse the tree in a spiral order, alternating between left-to-right and right-to-left at each level.
  - **Vertical Order Traversal**: Traverse the tree vertically, grouping nodes by their horizontal distance from the root.
  - **Diagonal Traversal**: Traverse the tree diagonally, grouping nodes by their diagonal distance from the root.
  - **K-distance Traversal**: Traverse nodes that are at a specific distance K from the root.
  - **Kth Smallest/Largest Element**: Find the Kth smallest or largest element in the N-ary tree.
  - **Lowest Common Ancestor (LCA)**: Find the lowest common ancestor of two nodes in the N-ary tree.
  - **Diameter of N-ary Tree**: The longest path between any two nodes in the N-ary tree.
  - **Level-wise Traversal**: Traverse the tree level by level, collecting nodes at each level.
  - **Kth Ancestor**: Find the Kth ancestor of a given node in the N-ary tree.
  - **Kth Sibling**: Find the Kth sibling of a given node in the N-ary tree.
  - **Kth Child**: Find the Kth child of a given node in the N-ary tree.
  - **Kth Level**: Find all nodes at the Kth level of the N-ary tree.
- **Kth Node**: Find the Kth node in a specific traversal order (e.g., pre-order, post-order).
- **Kth Level Nodes**: Find all nodes at the Kth level of the N-ary tree.
- **Kth Ancestor**: Find the Kth ancestor of a given node in the N-ary tree.
- **Kth Sibling**: Find the Kth sibling of a given node in the N-ary tree.
- **Kth Child**: Find the Kth child of a given node in the N-ary tree.
- **Kth Level**: Find all nodes at the Kth level of the N-ary tree.
- **Kth Node in Pre-order**: Find the Kth node in pre-order traversal of the N-ary tree.
- **Kth Node in Post-order**: Find the Kth node in post-order traversal of the N-ary tree.
- **Kth Node in Level-order**: Find the Kth node in level-order traversal of the N-ary tree.
- **Kth Node in Spiral Order**: Find the Kth node in spiral order traversal of the N-ary tree.
- **Kth Node in Vertical Order**: Find the Kth node in vertical order traversal of the N-ary tree.
- **Kth Node in Diagonal Order**: Find the Kth node in diagonal order traversal of the N-ary tree.
- **Kth Node in Boundary Order**: Find the Kth node in boundary order traversal of the N-ary tree.
- **Kth Node in Zigzag Order**: Find the Kth node in zigzag order traversal of the N-ary tree.
- **Kth Node in Reverse Level-order**: Find the Kth node in reverse level-order traversal of the N-ary tree.
- **Kth Node in Depth-first Search**: Find the Kth node in depth-first search traversal of the N-ary tree.



# Example
```java
class NaryTreeNode {
    int val;
    List<NaryTreeNode> children;

    NaryTreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}
class NaryTree {
    NaryTreeNode root;

    NaryTree(int val) {
        this.root = new NaryTreeNode(val);
    }

    void addChild(NaryTreeNode parent, int val) {
        parent.children.add(new NaryTreeNode(val));
    }

    // Example of pre-order traversal
    void preOrderTraversal(NaryTreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        for (NaryTreeNode child : node.children) {
            preOrderTraversal(child);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        NaryTree tree = new NaryTree(1);
        NaryTreeNode root = tree.root;
        tree.addChild(root, 2);
        tree.addChild(root, 3);
        tree.addChild(root, 4);
        tree.addChild(root.children.get(0), 5);
        tree.addChild(root.children.get(0), 6);

        System.out.println("Pre-order Traversal:");
        tree.preOrderTraversal(tree.root); // Output: 1 2 5 6 3 4
    }
}
```
# Use Cases
- **File Systems**: Representing directories and files where each directory can have multiple subdirectories and files.
- **Organizational Structures**: Representing company hierarchies where each employee can have multiple subordinates.
- **Game Development**: Representing game scenes where each scene can have multiple objects or entities.
- **XML/HTML Parsing**: Representing the structure of XML or HTML documents where each tag can have multiple child tags.
- **Decision Trees**: Representing decision-making processes where each decision can lead to multiple outcomes.
- **Network Topology**: Representing network structures where each node can have multiple connections to other nodes.
- **Social Networks**: Representing relationships where each user can have multiple friends or connections.
- **Data Structures**: Representing complex data structures like tries, where each node can have multiple children representing different characters or values.
- **Search Engines**: Representing the structure of web pages where each page can link to multiple other pages.
- **Artificial Intelligence**: Representing game states or decision-making processes where each state can lead to multiple possible actions or outcomes.
- **Machine Learning**: Representing decision trees or random forests where each node can have multiple branches based on different features or conditions.
- **Database Indexing**: Representing multi-level indexes where each index can point to multiple records or entries.
- **Content Management Systems**: Representing articles or pages where each page can have multiple sections or components.
- **Configuration Management**: Representing configuration settings where each setting can have multiple options or values.
- **Data Visualization**: Representing hierarchical data structures for visualization purposes, such as tree maps or sunburst charts.
- **Web Development**: Representing the structure of web applications where each component can have multiple child components or elements.
- **Content Delivery Networks**: Representing the distribution of content across multiple servers where each server can have multiple content items.
- **Cloud Computing**: Representing cloud resources where each resource can have multiple associated services or components.
- **Distributed Systems**: Representing the architecture of distributed systems where each node can have multiple connections to other nodes.
- **Blockchain**: Representing transactions where each block can have multiple transactions associated with it.
- **Internet of Things (IoT)**: Representing IoT devices where each device can have multiple sensors or components.
- **Robotics**: Representing robot structures where each robot can have multiple joints or components.
- **Computer Vision**: Representing image hierarchies where each image can have multiple regions or features.
- **Natural Language Processing**: Representing sentence structures where each sentence can have multiple phrases or clauses.
- **Geographic Information Systems (GIS)**: Representing geographic data where each location can have multiple attributes or features.
- **Simulation**: Representing simulation environments where each entity can have multiple attributes or behaviors.
- **Data Analysis**: Representing complex datasets where each data point can have multiple attributes or dimensions.
- **Network Security**: Representing security policies where each policy can have multiple rules or conditions.
- **Cryptography**: Representing cryptographic keys where each key can have multiple associated values or properties.
- **Artificial Neural Networks**: Representing neural networks where each neuron can have multiple connections to other neurons.
- **Genetic Algorithms**: Representing populations where each individual can have multiple genes or traits.
- **Evolutionary Algorithms**: Representing solutions where each solution can have multiple components or parameters.
- **Data Compression**: Representing compressed data structures where each segment can have multiple encoded values.
- **Data Encryption**: Representing encrypted data where each block can have multiple encrypted values.
- **Data Integrity**: Representing checksums or hashes where each checksum can validate multiple data segments.
- **Data Backup**: Representing backup structures where each backup can contain multiple files or directories.
- **Data Recovery**: Representing recovery points where each point can restore multiple data segments.
- **Data Migration**: Representing migration paths where each path can involve multiple data transformations or transfers.
- **Data Synchronization**: Representing synchronization processes where each process can involve multiple data sources or targets.
- **Data Archiving**: Representing archived data where each archive can contain multiple data items or records.
- **Data Warehousing**: Representing data warehouses where each warehouse can contain multiple datasets or tables.
- **Data Mining**: Representing data mining processes where each process can involve multiple data sources or techniques.

# References
- [Wikipedia - N-ary Tree](https://en.wikipedia.org/wiki/N-ary_tree)
- [GeeksforGeeks - N-ary Tree](https://www.geeksforgeeks.org/n-ary-tree-data-structure/)
- [LeetCode - N-ary Tree](https://leetcode.com/tag/n-ary-tree/)
- [Java Documentation - N-ary Tree](https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html)
- [Java Tutorials - N-ary Tree](https://docs.oracle.com/javase/tutorial/collections/algorithms/index.html)
- [Java Collections Framework - N-ary Tree](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)
- [Java Data Structures - N-ary Tree](https://www.javatpoint.com/data-structures-tutorial)
- [Java Programming - N-ary Tree](https://www.javatpoint.com/java-programming-tutorial)
- [Java Algorithms - N-ary Tree](https://www.javatpoint.com/java-algorithms-tutorial)
- [Java Coding - N-ary Tree](https://www.javatpoint.com/java-coding-interview-questions)
- [Java Coding Challenges - N-ary Tree](https://www.javatpoint.com/java-coding-challenges)
- [Java Coding Exercises - N-ary Tree](https://www.javatpoint.com/java-coding-exercises)
- [Java Coding Problems - N-ary Tree](https://www.javatpoint.com/java-coding-problems)
- [Java Coding Solutions - N-ary Tree](https://www.javatpoint.com/java-coding-solutions)
- [Java Coding Examples - N-ary Tree](https://www.javatpoint.com/java-coding-examples)
- [Java Coding Techniques - N-ary Tree](https://www.javatpoint.com/java-coding-techniques)
- [Java Coding Patterns - N-ary Tree](https://www.javatpoint.com/java-coding-patterns)
- [Java Coding Strategies - N-ary Tree](https://www.javatpoint.com/java-coding-strategies)
- [Java Coding Best Practices - N-ary Tree](https://www.javatpoint.com/java-coding-best-practices)
- [Java Coding Standards - N-ary Tree](https://www.javatpoint.com/java-coding-standards)
- [Java Coding Guidelines - N-ary Tree](https://www.javatpoint.com/java-coding-guidelines)
- [Java Coding Principles - N-ary Tree](https://www.javatpoint.com/java-coding-principles)
- [Java Coding Conventions - N-ary Tree](https://www.javatpoint.com/java-coding-conventions)
- [Java Coding Techniques - N-ary Tree](https://www.javatpoint.com/java-coding-techniques)
- [Java Coding Patterns - N-ary Tree](https://www.javatpoint.com/java-coding-patterns)
- [Java Coding Strategies - N-ary Tree](https://www.javatpoint.com/java-coding-strategies)
- [Java Coding Best Practices - N-ary Tree](https://www.javatpoint.com/java-coding-best-practices)

# Additional Resources
- [N-ary Tree Visualization](https://www.cs.usfca.edu/~galles/visualization/NaryTree.html)
- [N-ary Tree Traversal Algorithms](https://www.geeksforgeeks.org/n-ary-tree-traversals/)
- [N-ary Tree Problems on LeetCode](https://leetcode.com/tag/n-ary-tree/)
- [N-ary Tree Implementation in Java](https://www.baeldung.com/java-n-ary-tree)
- [N-ary Tree Data Structure in Java](https://www.javatpoint.com/n-ary-tree-data-structure-in-java)
- [N-ary Tree Operations](https://www.geeksforgeeks.org/n-ary-tree-operations/)

# N-ary Tree in Java
```java
import java.util.ArrayList;
import java.util.List;
class NaryTreeNode {
    int val;
    List<NaryTreeNode> children;

    NaryTreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}
class NaryTree {
    NaryTreeNode root;

    NaryTree(int val) {
        this.root = new NaryTreeNode(val);
    }

    void addChild(NaryTreeNode parent, int val) {
        parent.children.add(new NaryTreeNode(val));
    }

    // Example of pre-order traversal
    void preOrderTraversal(NaryTreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        for (NaryTreeNode child : node.children) {
            preOrderTraversal(child);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        NaryTree tree = new NaryTree(1);
        NaryTreeNode root = tree.root;
        tree.addChild(root, 2);
        tree.addChild(root, 3);
        tree.addChild(root, 4);
        tree.addChild(root.children.get(0), 5);
        tree.addChild(root.children.get(0), 6);

        System.out.println("Pre-order Traversal:");
        tree.preOrderTraversal(tree.root); // Output: 1 2 5 6 3 4
    }
}
```
