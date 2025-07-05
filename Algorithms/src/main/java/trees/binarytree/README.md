A tree data structure in which each node has at most two children.
# Binary Tree
A binary tree is a hierarchical data structure in which each node has at most two children, referred to as the left child and the right child. This structure is widely used in computer science for various applications, including searching, sorting, and representing hierarchical data.
## Characteristics
- **Node**: The fundamental part of a binary tree, containing data and references to its children.
- **Root**: The topmost node in the tree, which has no parent.
- **Leaf**: A node that has no children.
- **Height**: The length of the longest path from the root to a leaf node.
- **Depth**: The length of the path from the root to a specific node.
- **Subtree**: A tree consisting of a node and all its descendants.
- **Full Binary Tree**: Every node other than the leaves has two children.
- **Complete Binary Tree**: All levels are fully filled except possibly the last level, which is filled from left to right.
- **Balanced Binary Tree**: The height of the left and right subtrees of any node differ by at most one.
- **Binary Search Tree (BST)**: A binary tree where the left child is less than the parent node and the right child is greater than the parent node.
- **Traversal**: The process of visiting all the nodes in a specific order, such as in-order, pre-order, or post-order.
- **Height-balanced**: A binary tree where the height of the left and right subtrees of any node differ by at most one.
- **Depth-first Search (DFS)**: A traversal method that explores as far as possible along each branch before backtracking.
- **Breadth-first Search (BFS)**: A traversal method that explores all the nodes at the present depth prior to moving on to nodes at the next depth level.
## Common Operations
- **Insertion**: Adding a new node to the tree while maintaining its properties.
- **Deletion**: Removing a node from the tree while maintaining its properties.
- **Searching**: Finding a node with a specific value in the tree.
- **Traversal**: Visiting all nodes in a specific order (in-order, pre-order, post-order, level-order).
- **Balancing**: Adjusting the tree to maintain its balanced properties after insertions or deletions.
- **Finding Height**: Calculating the height of the tree or a specific subtree.
- **Finding Depth**: Calculating the depth of a specific node in the tree.
- **Finding Lowest Common Ancestor (LCA)**: Identifying the lowest node that is an ancestor of two given nodes in the tree.
- **Finding Diameter**: Calculating the longest path between any two nodes in the tree.
- **Finding Kth Smallest/Largest Element**: Identifying the Kth smallest or largest element in a binary search tree.
- **Checking Balanced**: Determining if the tree is height-balanced.
- **Checking Symmetry**: Determining if the tree is a mirror image of itself.
- **Finding Path Sum**: Checking if there exists a path from the root to a leaf such that the sum of the values along the path equals a given target.
- **Finding All Paths**: Identifying all paths from the root to the leaves in the tree.
- **Finding Maximum/Minimum Value**: Identifying the maximum or minimum value in the tree.
- **Finding Inorder Successor/Predecessor**: Identifying the next or previous node in the in-order traversal of the tree.
- **Finding Level of a Node**: Determining the level (or depth) of a specific node in the tree.
- **Finding All Leaves**: Identifying all leaf nodes in the tree.
- **Finding All Nodes at a Given Level**: Identifying all nodes that are at a specific level in the tree.
- **Finding All Ancestors of a Node**: Identifying all ancestor nodes of a specific node in the tree.
- **Finding All Descendants of a Node**: Identifying all descendant nodes of a specific node in the tree.
- **Finding All Nodes with a Given Value**: Identifying all nodes that contain a specific value in the tree.
- **Finding All Nodes at a Given Depth**: Identifying all nodes that are at a specific depth in the tree.
- **Finding All Nodes in a Given Range**: Identifying all nodes whose values fall within a specified range.
- **Finding All Nodes with a Given Property**: Identifying all nodes that satisfy a specific property or condition.
- **Finding All Nodes with a Given Depth**: Identifying all nodes that are at a specific depth in the tree.
- **Finding All Nodes with a Given Height**: Identifying all nodes that are at a specific height in the tree.
- **Finding All Nodes with a Given Value**: Identifying all nodes that contain a specific value in the tree.
- **Finding All Nodes with a Given Property**: Identifying all nodes that satisfy a specific property or condition.
- **Finding All Nodes with a Given Depth**: Identifying all nodes that are at a specific depth in the tree.
- **Finding All Nodes with a Given Height**: Identifying all nodes that are at a specific height in the tree.
- **Finding All Nodes with a Given Value**: Identifying all nodes that contain a specific value in the tree.
- **Finding All Nodes with a Given Property**: Identifying all nodes that satisfy a specific property or condition.
        
## Example  Implementations  
- **Insertion**: Adding a new node to the binary tree while maintaining its properties.
- **Deletion**: Removing a node from the binary tree while maintaining its properties.
- **Searching**: Finding a node with a specific value in the binary tree.
- **Traversal**: Visiting all nodes in the binary tree in a specific order (in-order, pre-order, post-order, level-order).
- **Balancing**: Adjusting the binary tree to maintain its balanced properties after insertions or deletions.
- **Finding Height**: Calculating the height of the binary tree or a specific subtree.
- **Finding Depth**: Calculating the depth of a specific node in the binary tree.
- **Finding Lowest Common Ancestor (LCA)**: Identifying the lowest node that is an ancestor of two given nodes in the binary tree.
- **Finding Diameter**: Calculating the longest path between any two nodes in the binary tree.
- **Finding Kth Smallest/Largest Element**: Identifying the Kth smallest or largest element in a binary search tree.
- **Checking Balanced**: Determining if the binary tree is height-balanced.
- **Checking Symmetry**: Determining if the binary tree is a mirror image of itself.
- **Finding Path Sum**: Checking if there exists a path from the root to a leaf such that the sum of the values along the path equals a given target.
- **Finding All Paths**: Identifying all paths from the root to the leaves in the binary tree.
- **Finding Maximum/Minimum Value**: Identifying the maximum or minimum value in the binary tree.
- **Finding Inorder Successor/Predecessor**: Identifying the next or previous node in the in-order traversal of the binary tree.
- **Finding Level of a Node**: Determining the level (or depth) of a specific node in the binary tree.
- **Finding All Leaves**: Identifying all leaf nodes in the binary tree.
- **Finding All Nodes at a Given Level**: Identifying all nodes that are at a specific level in the binary tree.
- **Finding All Ancestors of a Node**: Identifying all ancestor nodes of a specific node in the binary tree.
- **Finding All Descendants of a Node**: Identifying all descendant nodes of a specific node in the binary tree.
- **Finding All Nodes with a Given Value**: Identifying all nodes that contain a specific value in the binary tree.
- **Finding All Nodes at a Given Depth**: Identifying all nodes that are at a specific depth in the binary tree.
- **Finding All Nodes in a Given Range**: Identifying all nodes whose values fall within a specified range in the binary tree.
- **Finding All Nodes with a Given Property**: Identifying all nodes that satisfy a specific property or condition in the binary tree.
- **Finding All Nodes with a Given Depth**: Identifying all nodes that are at a specific depth in the binary tree.
- **Finding All Nodes with a Given Height**: Identifying all nodes that are at a specific height in the binary tree.
- **Finding All Nodes with a Given Value**: Identifying all nodes that contain a specific value in the binary tree.
- **Finding All Nodes with a Given Property**: Identifying all nodes that satisfy a specific property or condition in the binary tree.
- **Finding All Nodes with a Given Depth**: Identifying all nodes that are at a specific depth in the binary tree.
- **Finding All Nodes with a Given Height**: Identifying all nodes that are at a specific height in the binary tree.
- **Finding All Nodes with a Given Value**: Identifying all nodes that contain a specific value in the binary tree.
- **Finding All Nodes with a Given Property**: Identifying all nodes that satisfy a specific property or condition in the binary tree.
- **Finding All Nodes with a Given Depth**: Identifying all nodes that are at a specific depth in the binary tree.
- **Finding All Nodes with a Given Height**: Identifying all nodes that are at a specific height in the binary tree.
- **Finding All Nodes with a Given Value**: Identifying all nodes that contain a specific value in the binary tree.
- **Finding All Nodes with a Given Property**: Identifying all nodes that satisfy a specific property or condition in the binary tree.
- **Finding All Nodes with a Given Depth**: Identifying all nodes that are at a specific depth in the binary tree.
- **Finding All Nodes with a Given Height**: Identifying all nodes that are at a specific height in the binary tree.
- **Finding All Nodes with a Given Value**: Identifying all nodes that contain a specific value in the binary tree.



