package graphalgorithms.greedy;

import java.util.*;

/*
Minimum Watching Posts
In the nearby famous national park "Howls and laughs", it has just been confirmed that a strange creature has just arrived and people are going nuts and everybody wants to see that. The park thus wants to set up some watching posts for this brand new animal and this task has been assigned to you. You have to determine how many minimum watching posts has to be set up so that everyone can comfortably watch the animal sleep, play and make sounds.

To help you out you are also given the arrival times and departure times of each of the watcher who have prebooked for this show. A watchpost can at most support one person. Based on this data determine the minimum number of watchposts that have to be set up.

Sample Input:

n = 5
Intervals : [[1,3], [4,5], [2,4], [1,6], [1,4]]

Sample Output:

4
Explanation:

At time 2, there will be 4 people who would want to see the animal and thus we would need atleast 4 watchposts.
Constraints:

1<=n<=10^4

1<=ar[i]<=10^6
 */
public class MinimumWatchingPosts {
    // Event class to represent arrival and departure events
    static class Event {
        int time;
        int type; // 1 for arrival, -1 for departure

        Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }

    public static int minWatchingPosts(List<List<Integer>> intervals,int n) {
        if (intervals == null || n == 0) {
            return 0;
        }

        List<Event> events = new ArrayList<>();

        // Create events for each interval
        for (List<Integer> interval : intervals) {
            events.add(new Event(interval.get(0), 1));  // arrival
            events.add(new Event(interval.get(1), -1)); // departure
        }

        // Sort events by time
        // If times are equal, process departures before arrivals
        events.sort((a, b) -> {
            if (a.time == b.time) {
                return a.type - b.type; // -1 comes before 1
            }
            return a.time - b.time;
        });

        int currentWatchers = 0;
        int maxWatchingPosts = 0;

        // Process events in chronological order
        for (Event event : events) {
            currentWatchers += event.type;
            maxWatchingPosts = Math.max(maxWatchingPosts, currentWatchers);
        }

        return maxWatchingPosts;
    }

    // Alternative approach using sweep line with coordinate compression
    public static int minWatchingPostsAlternative(List<List<Integer>> intervals,int n) {
        if (intervals == null || n == 0) {
            return 0;
        }

        // Get all unique time points
        Set<Integer> timePoints = new HashSet<>();
        for (List<Integer> interval : intervals) {
            timePoints.add(interval.get(0));
            timePoints.add(interval.get(1));
        }

        List<Integer> sortedTimes = new ArrayList<>(timePoints);
        Collections.sort(sortedTimes);

        int maxOverlap = 0;

        // Check overlap at each time point
        for (int time : sortedTimes) {
            int count = 0;
            for (List<Integer> interval : intervals) {
                if (interval.get(0) <= time && time < interval.get(1)) {
                    count++;
                }
            }
            maxOverlap = Math.max(maxOverlap, count);
        }

        return maxOverlap;
    }

    public static void main(String[] args) {
        // Test case 1
        List<List<Integer>> intervals1 = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(4, 5),
                Arrays.asList(2, 4),
                Arrays.asList(1, 6),
                Arrays.asList(1, 4)
        );
        System.out.println("Test Case 1:");
        System.out.println("Intervals: " + intervals1);
        System.out.println("Minimum Watching Posts: " + minWatchingPosts(intervals1, intervals1.size()));
        System.out.println("Alternative Solution: " + minWatchingPostsAlternative(intervals1, intervals1.size()));
        System.out.println();

        // Test case 2
        List<List<Integer>> intervals2 = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4)
        );
        System.out.println("Test Case 2:");
        System.out.println("Intervals: " + intervals2);
        System.out.println("Minimum Watching Posts: " + minWatchingPosts(intervals2, intervals2.size()));
        System.out.println();

        // Test case 3
        List<List<Integer>> intervals3 = Arrays.asList(Arrays.asList(1, 5),Arrays.asList(2, 6),Arrays.asList(3, 7));

        System.out.println("Test Case 3:");
        System.out.println("Intervals: " + intervals3);
        System.out.println("Minimum Watching Posts: " + minWatchingPosts(intervals3, intervals3.size()));
        System.out.println();

        // Detailed explanation for test case 1
        explainSolution(intervals1);
    }

    public static void explainSolution(List<List<Integer>> intervals) {
        System.out.println("=== Detailed Explanation ===");
        System.out.println("Intervals: " + intervals);

        List<Event> events = new ArrayList<>();
        for (List<Integer> interval : intervals) {
            events.add(new Event(interval.get(0), 1));
            events.add(new Event(interval.get(1), -1));
        }

        events.sort((a, b) -> {
            if (a.time == b.time) {
                return a.type - b.type;
            }
            return a.time - b.time;
        });

        System.out.println("\nProcessing events:");
        int currentWatchers = 0;
        int maxWatchingPosts = 0;

        for (Event event : events) {
            currentWatchers += event.type;
            String eventType = event.type == 1 ? "Arrival" : "Departure";
            System.out.printf("Time %d: %s -> Current watchers: %d\n",
                    event.time, eventType, currentWatchers);
            maxWatchingPosts = Math.max(maxWatchingPosts, currentWatchers);
        }

        System.out.println("\nMaximum concurrent watchers: " + maxWatchingPosts);
        System.out.println("Therefore, minimum watching posts needed: " + maxWatchingPosts);
    }
}
