package heaps.binarysearch;

import java.util.*;

/*
You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.



Example 1:


Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
Example 2:

Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.
Example 3:


Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.


Constraints:

2 <= events.length <= 105
events[i].length == 3
1 <= startTimei <= endTimei <= 109
1 <= valuei <= 106
 */
public class TwoBestNonOverlappingEvents {

    // Custom class to represent an event
    static class Event {
        int start, end, value;

        Event(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("[%d,%d,%d]", start, end, value);
        }
    }

    /**
     * Solution using Binary Search Tree (TreeMap)
     * Time Complexity: O(n log n) for sorting + O(n log n) for BST operations
     * Space Complexity: O(n) for the BST
     */
    public static int maxTwoEvents(int[][] events) {
        // Convert to Event objects and sort by end time
        List<Event> eventList = new ArrayList<>();
        for (int[] event : events) {
            eventList.add(new Event(event[0], event[1], event[2]));
        }

        // Sort events by end time
        eventList.sort((a, b) -> Integer.compare(a.end, b.end));

        // TreeMap (BST) to store end time -> maximum value up to that time
        TreeMap<Integer, Integer> bst = new TreeMap<>();
        int maxSum = 0;

        for (Event event : eventList) {
            // Option 1: Take only this event
            maxSum = Math.max(maxSum, event.value);

            // Option 2: Take this event + best previous non-overlapping event
            // Find the latest event that ends before current event starts
            Map.Entry<Integer, Integer> prevEntry = bst.floorEntry(event.start - 1);
            if (prevEntry != null) {
                maxSum = Math.max(maxSum, event.value + prevEntry.getValue());
            }

            // Update BST with current event
            // Calculate the maximum value achievable up to current event's end time
            int maxValueUpToNow = event.value;
            if (!bst.isEmpty()) {
                maxValueUpToNow = Math.max(maxValueUpToNow, bst.lastEntry().getValue());
            }
            bst.put(event.end, maxValueUpToNow);
        }

        return maxSum;
    }

    /**
     * Enhanced BST solution with better tracking of maximum values
     */
    public static int maxTwoEventsEnhanced(int[][] events) {
        // Convert and sort by end time
        Event[] eventArray = new Event[events.length];
        for (int i = 0; i < events.length; i++) {
            eventArray[i] = new Event(events[i][0], events[i][1], events[i][2]);
        }

        Arrays.sort(eventArray, (a, b) -> Integer.compare(a.end, b.end));

        // TreeMap to store end time -> maximum value achievable up to that time
        TreeMap<Integer, Integer> maxValueBST = new TreeMap<>();
        int result = 0;

        for (Event event : eventArray) {
            // Option 1: Take only current event
            result = Math.max(result, event.value);

            // Option 2: Take current event + best previous non-overlapping event
            // Use BST to find the maximum value from events ending before current starts
            Map.Entry<Integer, Integer> bestPrev = maxValueBST.floorEntry(event.start - 1);
            if (bestPrev != null) {
                result = Math.max(result, event.value + bestPrev.getValue());
            }

            // Update BST with maximum value achievable up to current event's end time
            int maxUpToNow = event.value;
            if (!maxValueBST.isEmpty()) {
                maxUpToNow = Math.max(maxUpToNow, maxValueBST.lastEntry().getValue());
            }
            maxValueBST.put(event.end, maxUpToNow);
        }

        return result;
    }

    /**
     * Custom BST implementation for demonstration
     */
    static class CustomBST {
        class Node {
            int endTime;
            int maxValue;
            Node left, right;

            Node(int endTime, int maxValue) {
                this.endTime = endTime;
                this.maxValue = maxValue;
            }
        }

        private Node root;

        // Insert or update node
        public void insert(int endTime, int maxValue) {
            root = insertRec(root, endTime, maxValue);
        }

        private Node insertRec(Node node, int endTime, int maxValue) {
            if (node == null) {
                return new Node(endTime, maxValue);
            }

            if (endTime < node.endTime) {
                node.left = insertRec(node.left, endTime, maxValue);
            } else if (endTime > node.endTime) {
                node.right = insertRec(node.right, endTime, maxValue);
            } else {
                // Update existing node
                node.maxValue = Math.max(node.maxValue, maxValue);
            }

            return node;
        }

        // Find maximum value for events ending at or before given time
        public int findMaxValueUpTo(int time) {
            return findMaxValueUpToRec(root, time, 0);
        }

        private int findMaxValueUpToRec(Node node, int time, int maxSoFar) {
            if (node == null) {
                return maxSoFar;
            }

            if (node.endTime <= time) {
                // This node and its right subtree are valid
                maxSoFar = Math.max(maxSoFar, node.maxValue);
                return findMaxValueUpToRec(node.right, time, maxSoFar);
            } else {
                // Only left subtree might be valid
                return findMaxValueUpToRec(node.left, time, maxSoFar);
            }
        }

        // In-order traversal for debugging
        public void inorder() {
            inorderRec(root);
            System.out.println();
        }

        private void inorderRec(Node node) {
            if (node != null) {
                inorderRec(node.left);
                System.out.print("(" + node.endTime + "," + node.maxValue + ") ");
                inorderRec(node.right);
            }
        }
    }

    /**
     * Solution using custom BST implementation
     */
    public static int maxTwoEventsCustomBST(int[][] events) {
        Event[] eventArray = new Event[events.length];
        for (int i = 0; i < events.length; i++) {
            eventArray[i] = new Event(events[i][0], events[i][1], events[i][2]);
        }

        Arrays.sort(eventArray, (a, b) -> Integer.compare(a.end, b.end));

        CustomBST bst = new CustomBST();
        int result = 0;

        for (Event event : eventArray) {
            // Option 1: Take only current event
            result = Math.max(result, event.value);

            // Option 2: Take current event + best previous non-overlapping event
            int prevMaxValue = bst.findMaxValueUpTo(event.start - 1);
            if (prevMaxValue > 0) {
                result = Math.max(result, event.value + prevMaxValue);
            }

            // Update BST
            int maxUpToNow = Math.max(event.value, bst.findMaxValueUpTo(event.end));
            bst.insert(event.end, maxUpToNow);
        }

        return result;
    }

    // Helper method to print events
    public static void printEvents(int[][] events) {
        System.out.print("[");
        for (int i = 0; i < events.length; i++) {
            System.out.print("[" + events[i][0] + "," + events[i][1] + "," + events[i][2] + "]");
            if (i < events.length - 1) System.out.print(",");
        }
        System.out.print("]");
    }

    // Method to demonstrate BST operations
    public static void demonstrateBSTOperations(int[][] events) {
        System.out.println("=== BST Operations Demo ===");
        System.out.print("Events: ");
        printEvents(events);
        System.out.println();

        // Convert and sort events
        Event[] eventArray = new Event[events.length];
        for (int i = 0; i < events.length; i++) {
            eventArray[i] = new Event(events[i][0], events[i][1], events[i][2]);
        }

        Arrays.sort(eventArray, (a, b) -> Integer.compare(a.end, b.end));

        System.out.println("Events sorted by end time:");
        for (Event event : eventArray) {
            System.out.println("  " + event);
        }

        TreeMap<Integer, Integer> bst = new TreeMap<>();
        System.out.println("\nProcessing events with BST:");
        int maxSum = 0;

        for (Event event : eventArray) {
            System.out.println("Processing event: " + event);

            // Option 1: Take only current event
            int option1 = event.value;
            maxSum = Math.max(maxSum, option1);
            System.out.println("  Option 1 (current event only): " + option1);

            // Option 2: Find best previous event
            Map.Entry<Integer, Integer> prevEntry = bst.floorEntry(event.start - 1);
            if (prevEntry != null) {
                int option2 = event.value + prevEntry.getValue();
                maxSum = Math.max(maxSum, option2);
                System.out.println("  Option 2 (current + best previous): " + event.value + " + " +
                        prevEntry.getValue() + " = " + option2);
            } else {
                System.out.println("  Option 2: No valid previous event found");
            }

            // Update BST
            int maxValueUpToNow = event.value;
            if (!bst.isEmpty()) {
                maxValueUpToNow = Math.max(maxValueUpToNow, bst.lastEntry().getValue());
            }
            bst.put(event.end, maxValueUpToNow);

            System.out.println("  Current max sum: " + maxSum);
            System.out.println("  BST state: " + bst);
            System.out.println();
        }

        System.out.println("Final result: " + maxSum);
    }

    public static void main(String[] args) {
        // Test Example 1
        int[][] events1 = {{1,3,2},{4,5,2},{2,4,3}};
        System.out.println("Example 1:");
        System.out.print("Input: events = ");
        printEvents(events1);
        System.out.println();

        int result1 = maxTwoEvents(events1);
        System.out.println("Output (TreeMap BST): " + result1);

        int result1Custom = maxTwoEventsCustomBST(events1);
        System.out.println("Output (Custom BST): " + result1Custom);
        System.out.println("Expected: 4");
        System.out.println();

        // Test Example 2
        int[][] events2 = {{1,3,2},{4,5,2},{1,5,5}};
        System.out.println("Example 2:");
        System.out.print("Input: events = ");
        printEvents(events2);
        System.out.println();

        int result2 = maxTwoEvents(events2);
        System.out.println("Output (TreeMap BST): " + result2);

        int result2Custom = maxTwoEventsCustomBST(events2);
        System.out.println("Output (Custom BST): " + result2Custom);
        System.out.println("Expected: 5");
        System.out.println();

        // Test Example 3
        int[][] events3 = {{1,5,3},{1,5,1},{6,6,5}};
        System.out.println("Example 3:");
        System.out.print("Input: events = ");
        printEvents(events3);
        System.out.println();

        int result3 = maxTwoEvents(events3);
        System.out.println("Output (TreeMap BST): " + result3);

        int result3Custom = maxTwoEventsCustomBST(events3);
        System.out.println("Output (Custom BST): " + result3Custom);
        System.out.println("Expected: 8");
        System.out.println();

        // Test the bug case
        int[][] events4 = {{10,83,53},{63,87,45},{97,100,32},{51,61,16}};
        System.out.println("Bug Test Case:");
        System.out.print("Input: events = ");
        printEvents(events4);
        System.out.println();

        int result4 = maxTwoEvents(events4);
        System.out.println("Output (TreeMap BST): " + result4);

        int result4Custom = maxTwoEventsCustomBST(events4);
        System.out.println("Output (Custom BST): " + result4Custom);
        System.out.println("Expected: 85");
        System.out.println();

        // Demonstrate BST operations
        demonstrateBSTOperations(events4);

        // Performance analysis
        System.out.println("=== Performance Analysis ===");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("  - Sorting: O(n log n)");
        System.out.println("  - BST operations: O(n log n) total");
        System.out.println("Space Complexity: O(n) for BST storage");
        System.out.println();
        System.out.println("BST Properties:");
        System.out.println("  - TreeMap uses Red-Black tree (self-balancing BST)");
        System.out.println("  - Guaranteed O(log n) for search, insert, delete");
        System.out.println("  - floorEntry() finds largest key ≤ given key");
        System.out.println("  - Maintains sorted order of keys");
        System.out.println();
        System.out.println("Key Insight:");
        System.out.println("  - Sort events by end time");
        System.out.println("  - Use BST to quickly find best previous non-overlapping event");
        System.out.println("  - Track maximum value achievable up to each time point");
    }
}
