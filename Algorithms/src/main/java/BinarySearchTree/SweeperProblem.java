package BinarySearchTree;
/*
Sweepers Problem
You have a set of sweepers assigned to clean different sections of a large park. Each section is represented as a node in a hierarchy, similar to a binary search tree, the city planner has given you an order in which the parks needs to be cleaned.

For example, given a task order [2, 1, 3], the parks would look like this.

      2
     / \
    1   3

The mayor wants you to figure out in how many ways can you assign your workers to get the work done in the same order as the city planner decided.
Input Format:

You are given an array of size n that represents the park no. all the values are unique and between 1 to n.
Output Format:

Number of ways you can assign the task to clean the park in the same order as provided by the city planner. Return the answer with modulo 10^9+7.
Example : 1

array = [2, 1, 3]

In the above example our park looks like this.

      2
     / \
    1   3

We can assign the task in this order [2, 3, 1] and get the same order.

      2
     / \
    1   3

Hence, there are two ways of assigning task so the answer should be 2.
Example - 2:

array = [1,3,2,4]

      1
       \
        3
       /  \
      2    4

The only way to get the same hierarchy is to order it like this [1, 3, 4, 2] which will look like this.

      1
       \
        3
       /  \
      2    4

So the answer here will be 2 since there are two ways to get that, one the original one and the othee that was shown above.
Constraints:

1 <= n <= 1000

1 <= ar[i] <= n (elements are permutations of n)

Note: The function should return the result.
 */
public class SweeperProblem {

    private static class Node {
        int val;
        Node left, right;

        Node(int item) {
            val = item;
            left = right = null;
        }
    }

    private static final int MOD = 1000000007;
    private static final int MAX_N = 1000;
    private static long[] fact = new long[MAX_N + 1];
    private static long[] invFact = new long[MAX_N + 1];

    static {
        precomputeFactorials();
    }

    private static void precomputeFactorials() {
        fact[0] = 1;
        for (int i = 1; i <= MAX_N; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[MAX_N] = pow(fact[MAX_N], MOD - 2);
        for (int i = MAX_N - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    private static long pow(long a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }

    public static int numberOfWays(int[] arr) {
        Node root = buildBST(arr);
        if (root == null) return 0;
        Result result = compute(root);
        return (int) result.ways;
    }

    private static Node buildBST(int[] arr) {
        if (arr.length == 0) return null;
        Node root = new Node(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            insert(root, arr[i]);
        }
        return root;
    }

    private static void insert(Node node, int val) {
        while (true) {
            if (val < node.val) {
                if (node.left == null) {
                    node.left = new Node(val);
                    break;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null) {
                    node.right = new Node(val);
                    break;
                } else {
                    node = node.right;
                }
            }
        }
    }

    private static class Result {
        long size;
        long ways;

        Result(long s, long w) {
            size = s;
            ways = w;
        }
    }

    private static Result compute(Node node) {
        if (node == null) {
            return new Result(0, 1);
        }
        Result left = compute(node.left);
        Result right = compute(node.right);
        long totalNodes = left.size + right.size;
        long comb = combination(totalNodes, left.size);
        long ways = (left.ways * right.ways) % MOD;
        ways = (ways * comb) % MOD;
        return new Result(1 + left.size + right.size, ways);
    }

    private static long combination(long n, long k) {
        if (n < 0 || k < 0 || k > n) return 0;
        return (fact[(int) n] * invFact[(int) k] % MOD) * invFact[(int) (n - k)] % MOD;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 5};
        System.out.println("Number of ways to sweep the tree: " + numberOfWays(arr));

        // Example with a different array
        int[] arr2 = {5, 3, 7, 1, 4, 6, 8};
        System.out.println("Number of ways to sweep the tree: " + numberOfWays(arr2));
    }
}
