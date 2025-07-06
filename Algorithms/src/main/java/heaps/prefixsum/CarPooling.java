package heaps.prefixsum;

import java.util.*;

/*
There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersipassengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
Return true* if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise*.

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true


Constraints:
* 1 <= trips.length <= 1000
* trips[i].length == 3
* 1 <= numPassengersi <= 100
* 0 <= fromi < toi <= 1000
* 1 <= capacity <= 105
 */
public class CarPooling {
        // Approach 1: Using Prefix Sum with Array (Most Efficient)
        public boolean carPooling(int[][] trips, int capacity) {
            // Find the maximum location to determine array size
            int maxLocation = 0;
            for (int[] trip : trips) {
                maxLocation = Math.max(maxLocation, trip[2]);
            }

            // Create difference array to track passenger changes
            int[] diff = new int[maxLocation + 1];

            // Process each trip
            for (int[] trip : trips) {
                int passengers = trip[0];
                int from = trip[1];
                int to = trip[2];

                // Add passengers at pickup location
                diff[from] += passengers;
                // Remove passengers at drop-off location
                diff[to] -= passengers;
            }

            // Check if capacity is exceeded at any point
            int currentPassengers = 0;
            for (int i = 0; i <= maxLocation; i++) {
                currentPassengers += diff[i];
                if (currentPassengers > capacity) {
                    return false;
                }
            }

            return true;
        }

        // Approach 2: Using TreeMap (More Memory Efficient for Sparse Data)
        public boolean carPoolingTreeMap(int[][] trips, int capacity) {
            // TreeMap to store location -> passenger change
            TreeMap<Integer, Integer> passengerChanges = new TreeMap<>();

            // Process each trip
            for (int[] trip : trips) {
                int passengers = trip[0];
                int from = trip[1];
                int to = trip[2];

                // Add passengers at pickup location
                passengerChanges.put(from, passengerChanges.getOrDefault(from, 0) + passengers);
                // Remove passengers at drop-off location
                passengerChanges.put(to, passengerChanges.getOrDefault(to, 0) - passengers);
            }

            // Check capacity at each location
            int currentPassengers = 0;
            for (int change : passengerChanges.values()) {
                currentPassengers += change;
                if (currentPassengers > capacity) {
                    return false;
                }
            }

            return true;
        }

        // Approach 3: Using Priority Queue (Heap-based approach)
        public boolean carPoolingHeap(int[][] trips, int capacity) {
            // Sort trips by start time
            Arrays.sort(trips, (a, b) -> Integer.compare(a[1], b[1]));

            // Priority queue to track when passengers get off (sorted by end time)
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

            int currentPassengers = 0;

            for (int[] trip : trips) {
                int passengers = trip[0];
                int from = trip[1];
                int to = trip[2];

                // Remove passengers who have reached their destination
                while (!heap.isEmpty() && heap.peek()[2] <= from) {
                    currentPassengers -= heap.poll()[0];
                }

                // Add current trip passengers
                currentPassengers += passengers;
                heap.offer(trip);

                // Check if capacity is exceeded
                if (currentPassengers > capacity) {
                    return false;
                }
            }

            return true;
        }

        // Approach 4: Event-based approach using custom Event class
        static class Event {
            int location;
            int passengerChange;

            Event(int location, int passengerChange) {
                this.location = location;
                this.passengerChange = passengerChange;
            }
        }

        public boolean carPoolingEvents(int[][] trips, int capacity) {
            List<Event> events = new ArrayList<>();

            // Create events for each trip
            for (int[] trip : trips) {
                int passengers = trip[0];
                int from = trip[1];
                int to = trip[2];

                events.add(new Event(from, passengers));     // Pickup
                events.add(new Event(to, -passengers));      // Drop-off
            }

            // Sort events by location, drop-offs before pickups at same location
            events.sort((a, b) -> {
                if (a.location != b.location) {
                    return Integer.compare(a.location, b.location);
                }
                return Integer.compare(a.passengerChange, b.passengerChange);
            });

            // Process events
            int currentPassengers = 0;
            for (Event event : events) {
                currentPassengers += event.passengerChange;
                if (currentPassengers > capacity) {
                    return false;
                }
            }

            return true;
        }

        // Helper method to demonstrate the algorithm step by step
        public void demonstrateAlgorithm(int[][] trips, int capacity) {
            System.out.println("=== Car Pooling Algorithm Demonstration ===");
            System.out.println("Capacity: " + capacity);
            System.out.println("Trips: " + Arrays.deepToString(trips));

            // Find max location
            int maxLocation = 0;
            for (int[] trip : trips) {
                maxLocation = Math.max(maxLocation, trip[2]);
            }

            // Create difference array
            int[] diff = new int[maxLocation + 1];

            System.out.println("\nProcessing trips:");
            for (int[] trip : trips) {
                int passengers = trip[0];
                int from = trip[1];
                int to = trip[2];

                System.out.printf("Trip: %d passengers from %d to %d\n", passengers, from, to);
                diff[from] += passengers;
                diff[to] -= passengers;
            }

            System.out.println("\nDifference array: " + Arrays.toString(diff));

            // Calculate prefix sum and check capacity
            System.out.println("\nLocation-wise passenger count:");
            int currentPassengers = 0;
            boolean possible = true;

            for (int i = 0; i <= maxLocation; i++) {
                currentPassengers += diff[i];
                System.out.printf("Location %d: %d passengers", i, currentPassengers);

                if (currentPassengers > capacity) {
                    System.out.printf(" (EXCEEDS CAPACITY!)");
                    possible = false;
                }
                System.out.println();
            }

            System.out.println("\nResult: " + (possible ? "POSSIBLE" : "NOT POSSIBLE"));
        }

        public static void main(String[] args) {
            CarPooling solution = new CarPooling();

            // Test case 1
            int[][] trips1 = {{2,1,5}, {3,3,7}};
            int capacity1 = 4;
            System.out.println("Test 1:");
            solution.demonstrateAlgorithm(trips1, capacity1);
            System.out.println("Array approach: " + solution.carPooling(trips1, capacity1));
            System.out.println("TreeMap approach: " + solution.carPoolingTreeMap(trips1, capacity1));
            System.out.println("Heap approach: " + solution.carPoolingHeap(trips1, capacity1));
            System.out.println("Events approach: " + solution.carPoolingEvents(trips1, capacity1));

            System.out.println("\n" + "=".repeat(60) + "\n");

            // Test case 2
            int[][] trips2 = {{2,1,5}, {3,3,7}};
            int capacity2 = 5;
            System.out.println("Test 2:");
            solution.demonstrateAlgorithm(trips2, capacity2);
            System.out.println("Array approach: " + solution.carPooling(trips2, capacity2));
            System.out.println("TreeMap approach: " + solution.carPoolingTreeMap(trips2, capacity2));
            System.out.println("Heap approach: " + solution.carPoolingHeap(trips2, capacity2));
            System.out.println("Events approach: " + solution.carPoolingEvents(trips2, capacity2));

            System.out.println("\n" + "=".repeat(60) + "\n");

            // Test case 3 - Edge case
            int[][] trips3 = {{3,2,7}, {3,7,9}, {8,3,9}};
            int capacity3 = 11;
            System.out.println("Test 3:");
            solution.demonstrateAlgorithm(trips3, capacity3);
            System.out.println("Array approach: " + solution.carPooling(trips3, capacity3));
            System.out.println("TreeMap approach: " + solution.carPoolingTreeMap(trips3, capacity3));
            System.out.println("Heap approach: " + solution.carPoolingHeap(trips3, capacity3));
            System.out.println("Events approach: " + solution.carPoolingEvents(trips3, capacity3));

            System.out.println("\n" + "=".repeat(60) + "\n");

            // Performance comparison
            System.out.println("Performance Analysis:");
            System.out.println("1. Array Approach: O(n + max_location) time, O(max_location) space");
            System.out.println("2. TreeMap Approach: O(n log n) time, O(n) space");
            System.out.println("3. Heap Approach: O(n log n) time, O(n) space");
            System.out.println("4. Events Approach: O(n log n) time, O(n) space");
            System.out.println("\nRecommendation: Use Array approach for dense data, TreeMap for sparse data");
        }
}
