package linkedlist;
/*
You are tasked with implementing a function designNode that creates a new node with an integer data type variable data and a pointer next initialized to null. The function should take an initial value as input and set the data member of the newly created node to this value. The function should then return the integer value of the created node.

Input:

An integer, initialValue (1 <= initialValue <= 100), representing the initial value to be set for the data member of the node.
Output:

Return an integer representing the value of the created node.
Example 1:

Input:
42

Output:
42

Explanation:

A node is created with data set to the provided initialValue (42). The function returns the integer value of the created node, which is 42.

Note:
You are required to use the provided class structure and function signature. Ensure proper memory management to avoid memory leaks.

Constraints:

0 <= initialValue < INT_MAX
Note: The function should return the result.
 */
public class DesignNode {
    public static void main(String[] args) {
        int initialValue = 42; // Example input
        System.out.println(designNode(initialValue)); // Output the value of the created node
    }

    private static int designNode(int initialValue) {
        ListNode newNode = new ListNode(initialValue);
        return newNode.val; // Return the integer value of the created node
    }
}
