package heaps.treebased;

import java.util.*;

/*
You are part of a university admissions office and need to keep track of the kth highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.

You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and continuously returns the kth highest test score after a new score has been submitted. More specifically, we are looking for the kth highest score in the sorted list of all scores.

Implement the KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
int add(int val) Adds a new test score val to the stream and returns the element representing the kth largest element in the pool of test scores so far.


Example 1:

Input:
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]

Output: [null, 4, 5, 5, 8, 8]

Explanation:

KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3); // return 4
kthLargest.add(5); // return 5
kthLargest.add(10); // return 5
kthLargest.add(9); // return 8
kthLargest.add(4); // return 8

Example 2:

Input:
["KthLargest", "add", "add", "add", "add"]
[[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]

Output: [null, 7, 7, 7, 8]

Explanation:

KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
kthLargest.add(2); // return 7
kthLargest.add(10); // return 7
kthLargest.add(9); // return 7
kthLargest.add(9); // return 8


Constraints:

0 <= nums.length <= 104
1 <= k <= nums.length + 1
-104 <= nums[i] <= 104
-104 <= val <= 104
At most 104 calls will be made to add.
 */




public class KthLargestElementInAStream {

    // Solution 1: Min-Heap approach (Most Efficient)
    private PriorityQueue<Integer> minHeap;
    private TreeMap<Integer, Integer> treeMap;
    private TreeNode bstRoot;
    private PriorityQueue<Integer> maxHeap;
    private TreeSet<int[]> treeSet;
    private int k;
    private int treeSetIndex;
    private String strategy;

    /**
     * Constructor with strategy selection
     * @param k the kth largest element to track
     * @param nums initial array of numbers
     * @param strategy "minheap", "treemap", "bst", "maxheap", or "treeset"
     */
    public KthLargestElementInAStream(int k, int[] nums, String strategy) {
        this.k = k;
        this.strategy = strategy.toLowerCase();
        this.treeSetIndex = 0;

        switch (this.strategy) {
            case "minheap":
                initMinHeap(nums);
                break;
            case "treemap":
                initTreeMap(nums);
                break;
            case "bst":
                initBST(nums);
                break;
            case "maxheap":
                initMaxHeap(nums);
                break;
            case "treeset":
                initTreeSet(nums);
                break;
            default:
                // Default to min-heap
                this.strategy = "minheap";
                initMinHeap(nums);
        }
    }

    /**
     * Default constructor using optimal min-heap strategy
     */
    public KthLargestElementInAStream(int k, int[] nums) {
        this(k, nums, "minheap");
    }

    // ============ MIN-HEAP IMPLEMENTATION ============
    private void initMinHeap(int[] nums) {
        this.minHeap = new PriorityQueue<>();
        for (int num : nums) {
            addMinHeap(num);
        }
    }

    private int addMinHeap(int val) {
        minHeap.offer(val);

        // Keep only k largest elements
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        return minHeap.peek();
    }

    // ============ TREEMAP IMPLEMENTATION ============
    private void initTreeMap(int[] nums) {
        this.treeMap = new TreeMap<>();
        for (int num : nums) {
            addTreeMap(num);
        }
    }

    private int addTreeMap(int val) {
        treeMap.put(val, treeMap.getOrDefault(val, 0) + 1);
        return findKthLargestTreeMap();
    }

    private int findKthLargestTreeMap() {
        int count = 0;

        // Traverse from largest to smallest
        for (Map.Entry<Integer, Integer> entry : treeMap.descendingMap().entrySet()) {
            count += entry.getValue();
            if (count >= k) {
                return entry.getKey();
            }
        }

        return -1; // Should never reach here
    }

    // ============ CUSTOM BST IMPLEMENTATION ============
    private void initBST(int[] nums) {
        this.bstRoot = null;
        for (int num : nums) {
            addBST(num);
        }
    }

    private int addBST(int val) {
        bstRoot = insertBST(bstRoot, val);
        return findKthLargestBST(bstRoot, k);
    }

    private TreeNode insertBST(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }

        if (val == node.val) {
            node.freq++;
        } else if (val < node.val) {
            node.left = insertBST(node.left, val);
        } else {
            node.right = insertBST(node.right, val);
        }

        node.count = getCount(node.left) + getCount(node.right) + node.freq;
        return node;
    }

    private int getCount(TreeNode node) {
        return node == null ? 0 : node.count;
    }

    private int findKthLargestBST(TreeNode node, int k) {
        if (node == null) return -1;

        int rightCount = getCount(node.right);

        if (k <= rightCount) {
            return findKthLargestBST(node.right, k);
        } else if (k > rightCount + node.freq) {
            return findKthLargestBST(node.left, k - rightCount - node.freq);
        } else {
            return node.val;
        }
    }

    // ============ MAX-HEAP IMPLEMENTATION ============
    private void initMaxHeap(int[] nums) {
        this.maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int num : nums) {
            maxHeap.offer(num);
        }
    }

    private int addMaxHeap(int val) {
        maxHeap.offer(val);

        // Extract k-1 largest elements to get kth largest
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < k - 1; i++) {
            temp.add(maxHeap.poll());
        }

        if(maxHeap.isEmpty()) {
            return -1; // Not enough elements
        }
        // The kth largest element is now at the top of the max-heap
        int kthLargest = maxHeap.peek();

        // Put back the extracted elements
        for (int num : temp) {
            maxHeap.offer(num);
        }

        return kthLargest;
    }

    // ============ TREESET IMPLEMENTATION ============
    private void initTreeSet(int[] nums) {
        // TreeSet with custom comparator: first by value (descending), then by index
        this.treeSet = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(b[0], a[0]); // Descending by value
            return Integer.compare(a[1], b[1]); // Ascending by index
        });

        for (int num : nums) {
            addTreeSet(num);
        }
    }

    private int addTreeSet(int val) {
        treeSet.add(new int[]{val, treeSetIndex++});

        // Find kth largest
        int count = 0;
        for (int[] entry : treeSet) {
            count++;
            if (count == k) {
                return entry[0];
            }
        }

        return -1; // Should never reach here
    }

    // ============ PUBLIC ADD METHOD ============
    public int add(int val) {
        switch (strategy) {
            case "minheap":
                return addMinHeap(val);
            case "treemap":
                return addTreeMap(val);
            case "bst":
                return addBST(val);
            case "maxheap":
                return addMaxHeap(val);
            case "treeset":
                return addTreeSet(val);
            default:
                return addMinHeap(val);
        }
    }

    // ============ UTILITY METHODS ============
    public String getStrategy() {
        return strategy;
    }

    public int getK() {
        return k;
    }

    public int size() {
        switch (strategy) {
            case "minheap":
                return minHeap.size();
            case "treemap":
                return treeMap.values().stream().mapToInt(Integer::intValue).sum();
            case "bst":
                return getCount(bstRoot);
            case "maxheap":
                return maxHeap.size();
            case "treeset":
                return treeSet.size();
            default:
                return 0;
        }
    }

    // ============ INNER CLASSES ============
    private class TreeNode {
        int val;
        int freq;
        int count;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.freq = 1;
            this.count = 1;
        }
    }

    // ============ MAIN METHOD FOR TESTING ============
    public static void main(String[] args) {
        System.out.println("Kth Largest Element - University Admissions System");
        System.out.println("=" .repeat(65));

        // Test Case 1
        System.out.println("Test Case 1: k=3, initial=[4,5,8,2]");
        testAllStrategies(3, new int[]{4, 5, 8, 2}, new int[]{3, 5, 10, 9, 4});

        System.out.println("\nTest Case 2: k=4, initial=[7,7,7,7,8,3]");
        testAllStrategies(4, new int[]{7, 7, 7, 7, 8, 3}, new int[]{2, 10, 9, 9});

        System.out.println("\nTest Case 3: k=1, initial=[1,2,3]");
        testAllStrategies(1, new int[]{1, 2, 3}, new int[]{4, 5, 6});

        System.out.println("\nTest Case 4: k=2, initial=[]");
        testAllStrategies(2, new int[]{}, new int[]{1, 2, 3, 4});

        // Performance comparison
        performanceComparison();
    }

    private static void testAllStrategies(int k, int[] initial, int[] additions) {
        String[] strategies = {"minheap", "treemap", "bst", "maxheap", "treeset"};

        System.out.printf("Initial array: %s%n", Arrays.toString(initial));
        System.out.printf("Adding values: %s%n", Arrays.toString(additions));
        System.out.println();

        // Initialize all strategies
        KthLargestElementInAStream[] solutions = new KthLargestElementInAStream[strategies.length];
        for (int i = 0; i < strategies.length; i++) {
            solutions[i] = new KthLargestElementInAStream(k, initial.clone(), strategies[i]);
        }

        System.out.printf("%-12s", "Add Value");
        for (String strategy : strategies) {
            System.out.printf("%-10s", strategy.toUpperCase());
        }
        System.out.println();
        System.out.println("-" .repeat(62));

        for (int val : additions) {
            System.out.printf("%-12d", val);
            for (KthLargestElementInAStream solution : solutions) {
                int result = solution.add(val);
                System.out.printf("%-10d", result);
            }
            System.out.println();
        }
    }

    private static void performanceComparison() {
        System.out.println("\n" + "=" .repeat(65));
        System.out.println("Performance Analysis");
        System.out.println("=" .repeat(65));

        int k = 100;
        int[] initial = new int[1000];
        Random rand = new Random(42);

        // Generate random initial data
        for (int i = 0; i < initial.length; i++) {
            initial[i] = rand.nextInt(20000) - 10000;
        }

        // Test additions
        int[] additions = new int[1000];
        for (int i = 0; i < additions.length; i++) {
            additions[i] = rand.nextInt(20000) - 10000;
        }

        System.out.println("Testing with k=" + k + ", initial size=" + initial.length +
                ", additions=" + additions.length);
        System.out.println();

        String[] strategies = {"minheap", "treemap", "bst", "maxheap", "treeset"};

        for (String strategy : strategies) {
            long start = System.nanoTime();
            KthLargestElementInAStream solution = new KthLargestElementInAStream(k, initial.clone(), strategy);
            for (int val : additions) {
                solution.add(val);
            }
            long end = System.nanoTime();
            System.out.printf("%-12s: %.2f ms%n", strategy.toUpperCase(),
                    (end - start) / 1_000_000.0);
        }

        System.out.println();
        System.out.println("Algorithm Comparison:");
        System.out.println("1. MINHEAP: O(log k) per add - BEST for this problem");
        System.out.println("2. TREEMAP: O(log n) per add - Good for range queries");
        System.out.println("3. BST: O(log n) avg, O(n) worst per add - Educational");
        System.out.println("4. MAXHEAP: O(k + log n) per add - Inefficient for large k");
        System.out.println("5. TREESET: O(log n) per add - Clean but more overhead");
        System.out.println();
        System.out.println("Recommendation: Use MINHEAP for optimal performance!");

        // Usage examples
        System.out.println("\n" + "=" .repeat(65));
        System.out.println("Usage Examples");
        System.out.println("=" .repeat(65));

        System.out.println("// Default (optimal) usage:");
        System.out.println("KthLargest kth = new KthLargest(3, new int[]{4, 5, 8, 2});");
        System.out.println("int result = kth.add(3); // returns 4");
        System.out.println();

        System.out.println("// Specific strategy usage:");
        System.out.println("KthLargest kth = new KthLargest(3, new int[]{4, 5, 8, 2}, \"treemap\");");
        System.out.println("int result = kth.add(3); // returns 4");
        System.out.println();

        System.out.println("// Strategy information:");
        System.out.println("System.out.println(\"Strategy: \" + kth.getStrategy());");
        System.out.println("System.out.println(\"K value: \" + kth.getK());");
        System.out.println("System.out.println(\"Current size: \" + kth.size());");
    }
}

/*
COMPREHENSIVE ALGORITHM ANALYSIS:

1. MIN-HEAP APPROACH (OPTIMAL):
   - Maintains only k largest elements
   - Time: O(log k) per add operation
   - Space: O(k)
   - Best for this specific problem

2. TREEMAP APPROACH:
   - Maintains all elements with frequencies
   - Time: O(log n) per add operation
   - Space: O(n)
   - Good for additional operations like range queries

3. CUSTOM BST APPROACH:
   - Maintains count information in each node
   - Time: O(log n) average, O(n) worst case
   - Space: O(n)
   - Educational value, shows tree manipulation

4. MAX-HEAP APPROACH:
   - Extracts k-1 elements to find kth largest
   - Time: O(k + log n) per add operation
   - Space: O(n)
   - Inefficient for large k

5. TREESET APPROACH:
   - Uses balanced BST with custom comparator
   - Time: O(log n) per add operation
   - Space: O(n)
   - Clean implementation but more overhead

REAL-WORLD APPLICATIONS:
- University admissions: Dynamic cutoff calculation
- Gaming leaderboards: Top k players
- Stock trading: Top k performing stocks
- System monitoring: Top k resource consumers
- Social media: Top k trending topics

USAGE RECOMMENDATIONS:
- Use default constructor for optimal performance (min-heap)
- Use specific strategy constructor for educational purposes
- Use TreeMap strategy if you need additional range operations
- Avoid MaxHeap strategy for large k values

FEATURES:
- Single class with multiple strategies
- Strategy pattern implementation
- Comprehensive testing framework
- Performance benchmarking
- Utility methods for introspection
- Real-world context and examples
*/

