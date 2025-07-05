A binary tree in which the values of nodes in the left subtree are less than the value of the parent node, and the values of nodes in the right subtree are greater.
# Binary Search Tree (BST)
A Binary Search Tree (BST) is a data structure that maintains the properties of a binary tree while ensuring that for each node:
- The left subtree contains only nodes with values less than the node's value.
- The right subtree contains only nodes with values greater than the node's value.
- Both the left and right subtrees are also binary search trees.
- The left and right subtrees can be empty.
- The left and right subtrees can have any number of nodes, including zero.
- The root node is the topmost node in the tree, and it can have a value that is less than or greater than its children.
# Properties of Binary Search Trees
1. **In-order Traversal**: When traversed in-order (left, root, right), the nodes of a BST will yield values in ascending order.
2. **Search Operation**: Searching for a value in a BST can be done efficiently, with a time complexity of O(h), where h is the height of the tree. In a balanced BST, this is O(log n), where n is the number of nodes.
3. **Insertion**: Inserting a new value into a BST involves finding the appropriate position for the new node while maintaining the BST properties. The time complexity is O(h) in the worst case, which can be O(log n) for balanced trees.
4. **Deletion**: Deleting a node from a BST can be more complex, especially if the node has two children. The time complexity is O(h) in the worst case, which can be O(log n) for balanced trees.
5. **Height**: The height of a BST is the length of the longest path from the root to a leaf node. In a balanced BST, the height is O(log n), while in an unbalanced BST, it can be O(n).
6. **Balance**: A balanced BST maintains a height that is logarithmic relative to the number of nodes, ensuring efficient operations. Common balanced BSTs include AVL trees and Red-Black trees.
7. **Traversal**: Common traversal methods for BSTs include in-order, pre-order, and post-order traversals. In-order traversal yields sorted values, while pre-order and post-order traversals can be used for various applications like serialization and deserialization.
8. **Applications**: BSTs are used in various applications, including databases for indexing, search engines for efficient lookups, and in-memory data structures for maintaining sorted data.
9. **Self-Balancing**: Some BSTs, like AVL trees and Red-Black trees, automatically maintain balance during insertions and deletions to ensure that the height remains logarithmic relative to the number of nodes.
10. **Complexity**: The average time complexity for search, insert, and delete operations in a balanced BST is O(log n), while in an unbalanced BST, it can degrade to O(n) in the worst case.
11. **Duplicates**: BSTs typically do not allow duplicate values, but variations can be implemented to handle duplicates by allowing them in a specific subtree (e.g., left for duplicates).
12. **Node Structure**: Each node in a BST contains a value, a reference to the left child, and a reference to the right child. The left child contains values less than the node's value, and the right child contains values greater than the node's value.
13. **Root Node**: The root node is the topmost node in the tree, and it serves as the entry point for all operations. It can have a value that is less than or greater than its children.
14. **Leaf Nodes**: Leaf nodes are nodes that do not have any children. They are the endpoints of the tree and can be found at various levels depending on the structure of the BST.
15. **Subtrees**: Each node in a BST can be considered the root of its own subtree, which follows the same properties as the entire tree. The left subtree contains nodes with values less than the node's value, and the right subtree contains nodes with values greater than the node's value.
16. **Traversal Order**: The order in which nodes are visited during traversal can affect the output. In-order traversal visits nodes in ascending order, while pre-order and post-order traversals have different visitation orders that can be useful for specific applications.
17. **Memory Usage**: The memory usage of a BST is O(n), where n is the number of nodes, as each node requires space for its value and references to its children.
18. **Iterative vs Recursive**: BST operations can be implemented using either iterative or recursive approaches. Recursive implementations are often simpler and more intuitive, while iterative implementations can be more efficient in terms of memory usage, especially for deep trees.
19. **Threaded BST**: A threaded binary search tree is a variation where null pointers are used to point to the in-order predecessor and successor of each node, allowing for more efficient in-order traversal without using a stack or recursion.
20. **Augmented BST**: An augmented binary search tree is a BST that stores additional information in each node, such as the size of the subtree rooted at that node, allowing for more efficient operations like rank queries and order statistics.
21. **Persistent BST**: A persistent binary search tree is a version of a BST that allows access to previous versions of the tree after modifications. This is useful in applications where historical data needs to be preserved.
22. **Multi-way Search Trees**: Multi-way search trees, such as B-trees and B+ trees, are generalizations of binary search trees that allow for more than two children per node. They are commonly used in databases and file systems to maintain sorted data with efficient search, insert, and delete operations.
23. **Self-Adjusting BST**: Self-adjusting binary search trees, such as splay trees, automatically adjust their structure during operations to keep frequently accessed nodes near the root, improving access times for those nodes.
24. **Threaded BST**: A threaded binary search tree is a variation where null pointers are used to point to the in-order predecessor and successor of each node, allowing for more efficient in-order traversal without using a stack or recursion.
25. **Augmented BST**: An augmented binary search tree is a BST that stores additional information in each node, such as the size of the subtree rooted at that node, allowing for more efficient operations like rank queries and order statistics.
26. **Persistent BST**: A persistent binary search tree is a version of a BST that allows access to previous versions of the tree after modifications. This is useful in applications where historical data needs to be preserved.
27. **Multi-way Search Trees**: Multi-way search trees, such as B-trees and B+ trees, are generalizations of binary search trees that allow for more than two children per node. They are commonly used in databases and file systems to maintain sorted data with efficient search, insert, and delete operations.
28. **Self-Adjusting BST**: Self-adjusting binary search trees, such as splay trees, automatically adjust their structure during operations to keep frequently accessed nodes near the root, improving access times for those nodes.
29. **Threaded BST**: A threaded binary search tree is a variation where null pointers are used to point to the in-order predecessor and successor of each node, allowing for more efficient in-order traversal without using a stack or recursion.
30. **Augmented BST**: An augmented binary search tree is a BST that stores additional information in each node, such as the size of the subtree rooted at that node, allowing for more efficient operations like rank queries and order statistics.
31. **Persistent BST**: A persistent binary search tree is a version of a BST that allows access to previous versions of the tree after modifications. This is useful in applications where historical data needs to be preserved.
32. **Multi-way Search Trees**: Multi-way search trees, such as B-trees and B+ trees, are generalizations of binary search trees that allow for more than two children per node. They are commonly used in databases and file systems to maintain sorted data with efficient search, insert, and delete operations.
33. **Self-Adjusting BST**: Self-adjusting binary search trees, such as splay trees, automatically adjust their structure during operations to keep frequently accessed nodes near the root, improving access times for those nodes.
34. **Threaded BST**: A threaded binary search tree is a variation where null pointers are used to point to the in-order predecessor and successor of each node, allowing for more efficient in-order traversal without using a stack or recursion.
35. **Augmented BST**: An augmented binary search tree is a BST that stores additional information in each node, such as the size of the subtree rooted at that node, allowing for more efficient operations like rank queries and order statistics.
36. **Persistent BST**: A persistent binary search tree is a version of a BST that allows access to previous versions of the tree after modifications. This is useful in applications where historical data needs to be preserved.
37. **Multi-way Search Trees**: Multi-way search trees, such as B-trees and B+ trees, are generalizations of binary search trees that allow for more than two children per node. They are commonly used in databases and file systems to maintain sorted data with efficient search, insert, and delete operations.

# Example of a Binary Search Tree in Java
```java
class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    // Insert a new value into the BST
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode node, int value) {
        if (node == null) {
            node = new TreeNode(value);
            return node;
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }
        return node;
    }

    // Search for a value in the BST
    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }
        return value < node.value ? searchRec(node.left, value) : searchRec(node.right, value);
    }

    // In-order traversal of the BST
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.value + " ");
            inOrderRec(node.right);
        }
    }
}
```
// Example usage
```java
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("In-order traversal of the BST:");
        bst.inOrder(); // Output: 20 30 40 50 60 70 80

        System.out.println("\nSearching for 40: " + bst.search(40)); // Output: true
        System.out.println("Searching for 90: " + bst.search(90)); // Output: false
    }
}
```
// Example usage
```java
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("In-order traversal of the BST:");
        bst.inOrder(); // Output: 20 30 40 50 60 70 80

        System.out.println("\nSearching for 40: " + bst.search(40)); // Output: true
        System.out.println("Searching for 90: " + bst.search(90)); // Output: false
    }
}
```
```java
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("In-order traversal of the BST:");
        bst.inOrder(); // Output: 20 30 40 50 60 70 80

        System.out.println("\nSearching for 40: " + bst.search(40)); // Output: true
        System.out.println("Searching for 90: " + bst.search(90)); // Output: false
    }
}
```java