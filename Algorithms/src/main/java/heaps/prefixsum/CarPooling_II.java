package heaps.prefixsum;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

/*
There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.



Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true


Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105
 */
public class CarPooling_II {
    /**
     * Solution using Min-Heap (Priority Queue) - Most intuitive
     * Time: O(n log n), Space: O(n)
     */
    public static boolean carPoolingHeap(int[][] trips, int capacity) {
        // Create events: [location, passengerChange, priority]
        // priority: 0 for drop-off, 1 for pick-up (process drop-offs first)
        PriorityQueue<int[]> events = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]); // Sort by location
            return Integer.compare(a[2], b[2]); // Drop-off before pick-up at same location
        });

        // Add pick-up and drop-off events
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            events.offer(new int[]{from, passengers, 1});    // Pick-up event
            events.offer(new int[]{to, -passengers, 0});     // Drop-off event
        }

        int currentPassengers = 0;

        // Process events in order
        while (!events.isEmpty()) {
            int[] event = events.poll();
            int location = event[0];
            int passengerChange = event[1];

            currentPassengers += passengerChange;

            // Check if capacity is exceeded
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }

    /**
     * Solution using Prefix Sum Array - Most efficient for given constraints
     * Time: O(n + max_location), Space: O(max_location)
     */
    public static boolean carPoolingPrefixSum(int[][] trips, int capacity) {
        // Find the maximum location to determine array size
        int maxLocation = 0;
        for (int[] trip : trips) {
            maxLocation = Math.max(maxLocation, trip[2]);
        }

        // Difference array to track passenger changes
        int[] diff = new int[maxLocation + 1];

        // Record passenger changes
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            diff[from] += passengers;      // Pick up passengers
            diff[to] -= passengers;        // Drop off passengers
        }

        // Convert difference array to actual passenger count using prefix sum
        int currentPassengers = 0;
        for (int i = 0; i <= maxLocation; i++) {
            currentPassengers += diff[i];

            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }

    /**
     * Alternative Heap solution using separate heaps for active trips
     * Time: O(n log n), Space: O(n)
     */
    public static boolean carPoolingAlternativeHeap(int[][] trips, int capacity) {
        // Sort trips by start location
        Arrays.sort(trips, (a, b) -> Integer.compare(a[1], b[1]));

        // Min-heap to track end locations of active trips
        PriorityQueue<int[]> activeTrips = new PriorityQueue<>((a, b) ->
                Integer.compare(a[2], b[2])); // Sort by end location

        int currentPassengers = 0;

        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            // Remove trips that have ended before current trip starts
            while (!activeTrips.isEmpty() && activeTrips.peek()[2] <= from) {
                int[] endedTrip = activeTrips.poll();
                currentPassengers -= endedTrip[0];
            }

            // Add current trip
            activeTrips.offer(trip);
            currentPassengers += passengers;

            // Check capacity
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }

    /**
     * TreeMap solution for handling sparse locations efficiently
     * Time: O(n log n), Space: O(n)
     */
    public static boolean carPoolingTreeMap(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> passengerChanges = new TreeMap<>();

        // Record passenger changes at each location
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            passengerChanges.put(from, passengerChanges.getOrDefault(from, 0) + passengers);
            passengerChanges.put(to, passengerChanges.getOrDefault(to, 0) - passengers);
        }

        int currentPassengers = 0;

        // Process locations in order
        for (int change : passengerChanges.values()) {
            currentPassengers += change;

            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases
        int[][][] testTrips = {
                {{2,1,5},{3,3,7}},      // Example 1
                {{2,1,5},{3,3,7}},      // Example 2
                {{2,1,5},{3,5,7}},      // No overlap
                {{3,2,7},{3,7,9},{8,3,9}}, // Multiple trips
                {{9,3,4},{8,5,6},{6,2,8},{8,4,6},{4,4,8}}, // Complex case
                {{1,1,2},{2,2,3},{3,3,4}} // Sequential trips
        };

        int[] capacities = {4, 5, 5, 11, 12, 6};
        boolean[] expectedResults = {false, true, true, true, false, true};

        System.out.println("Car Pooling Problem - Multiple Solutions");
        System.out.println("=" .repeat(60));

        for (int i = 0; i < testTrips.length; i++) {
            int[][] trips = testTrips[i];
            int capacity = capacities[i];
            boolean expected = expectedResults[i];

            boolean result1 = carPoolingHeap(trips, capacity);
            boolean result2 = carPoolingPrefixSum(trips, capacity);
            boolean result3 = carPoolingAlternativeHeap(trips, capacity);
            boolean result4 = carPoolingTreeMap(trips, capacity);

            System.out.printf("Test %d: capacity = %d%n", i + 1, capacity);
            System.out.printf("  Trips: %s%n", Arrays.deepToString(trips));
            System.out.printf("  Expected: %s%n", expected);
            System.out.printf("  Heap Solution:      %s %s%n", result1, result1 == expected ? "✓" : "✗");
            System.out.printf("  Prefix Sum:         %s %s%n", result2, result2 == expected ? "✓" : "✗");
            System.out.printf("  Alternative Heap:   %s %s%n", result3, result3 == expected ? "✓" : "✗");
            System.out.printf("  TreeMap Solution:   %s %s%n", result4, result4 == expected ? "✓" : "✗");
            System.out.printf("  All Match: %s%n%n",
                    (result1 == result2 && result2 == result3 && result3 == result4) ? "✓" : "✗");
        }

        System.out.println("Algorithm Analysis:");
        System.out.println("1. Heap Solution (Events):");
        System.out.println("   - Time: O(n log n), Space: O(n)");
        System.out.println("   - Most intuitive, handles all cases well");
        System.out.println();

        System.out.println("2. Prefix Sum Array:");
        System.out.println("   - Time: O(n + max_location), Space: O(max_location)");
        System.out.println("   - Most efficient for given constraints (max_location ≤ 1000)");
        System.out.println();

        System.out.println("3. Alternative Heap (Active Trips):");
        System.out.println("   - Time: O(n log n), Space: O(n)");
        System.out.println("   - Tracks active trips explicitly");
        System.out.println();

        System.out.println("4. TreeMap Solution:");
        System.out.println("   - Time: O(n log n), Space: O(n)");
        System.out.println("   - Best for sparse locations, automatic sorting");

        // Performance demonstration
        performanceDemo();
    }

    private static void performanceDemo() {
        System.out.println("\n" + "=" .repeat(60));
        System.out.println("Performance Demonstration");
        System.out.println("=" .repeat(60));

        // Generate test data
        int n = 1000;
        int[][] trips = new int[n][3];
        Random rand = new Random(42);

        for (int i = 0; i < n; i++) {
            int passengers = rand.nextInt(10) + 1;
            int from = rand.nextInt(500);
            int to = from + rand.nextInt(500) + 1;
            trips[i] = new int[]{passengers, from, to};
        }

        int capacity = 50;

        // Measure execution times
        long start, end;

        start = System.nanoTime();
        boolean result1 = carPoolingHeap(trips, capacity);
        end = System.nanoTime();
        System.out.printf("Heap Solution:      %s (%.3f ms)%n", result1, (end - start) / 1_000_000.0);

        start = System.nanoTime();
        boolean result2 = carPoolingPrefixSum(trips, capacity);
        end = System.nanoTime();
        System.out.printf("Prefix Sum:         %s (%.3f ms)%n", result2, (end - start) / 1_000_000.0);

        start = System.nanoTime();
        boolean result3 = carPoolingTreeMap(trips, capacity);
        end = System.nanoTime();
        System.out.printf("TreeMap Solution:   %s (%.3f ms)%n", result3, (end - start) / 1_000_000.0);

        System.out.printf("All results match: %s%n",
                (result1 == result2 && result2 == result3) ? "✓" : "✗");
    }
}

/*
Algorithm Explanations:

1. HEAP SOLUTION (Event-based):
   - Create events for pick-up and drop-off at each location
   - Process events in chronological order
   - Drop-offs processed before pick-ups at same location
   - Track running passenger count

2. PREFIX SUM SOLUTION:
   - Use difference array to mark passenger changes
   - Convert to actual counts using prefix sum
   - Most efficient for given constraints

3. ALTERNATIVE HEAP (Active Trips):
   - Sort trips by start location
   - Use heap to track when active trips end
   - Remove expired trips before adding new ones

4. TREEMAP SOLUTION:
   - Automatically sorts locations
   - Handles sparse locations efficiently
   - Good for real-world scenarios

Key Insights:
- All solutions handle the constraint that car only moves east
- Prefix sum is optimal for given constraints (locations ≤ 1000)
- Heap solutions are more general and handle any location range
- TreeMap is best for sparse, large location ranges

Time Complexities:
- Heap: O(n log n)
- Prefix Sum: O(n + max_location)
- TreeMap: O(n log n)

Space Complexities:
- All: O(n) or O(max_location) for prefix sum
*/

