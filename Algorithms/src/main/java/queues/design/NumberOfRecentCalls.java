package queues.design;

import java.util.LinkedList;
import java.util.Queue;

/*
You have a RecentCounter class which counts the number of recent requests within a certain time frame.

Implement the RecentCounter class:

RecentCounter() Initializes the counter with zero recent requests.
int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.



Example 1:

Input
["RecentCounter", "ping", "ping", "ping", "ping"]
[[], [1], [100], [3001], [3002]]
Output
[null, 1, 2, 3, 3]

Explanation
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3


Constraints:

1 <= t <= 109
Each test case will call ping with strictly increasing values of t.
At most 104 calls will be made to ping.
 */
public class NumberOfRecentCalls {
    private Queue<Integer> requests;

    public NumberOfRecentCalls() {
        requests = new LinkedList<>();
    }

    public int ping(int t) {
        // Add the new request timestamp to the queue
        requests.offer(t);

        // Remove all requests that are outside the 3000ms window
        // Keep only requests in range [t - 3000, t]
        while (!requests.isEmpty() && requests.peek() < t - 3000) {
            requests.poll();
        }

        // Return the count of requests within the time window
        return requests.size();
    }

    public static void main(String[] args) {
        NumberOfRecentCalls recentCounter = new NumberOfRecentCalls();

        // Test case from the example
        System.out.println("ping(1): " + recentCounter.ping(1));     // Expected: 1
        System.out.println("ping(100): " + recentCounter.ping(100)); // Expected: 2
        System.out.println("ping(3001): " + recentCounter.ping(3001)); // Expected: 3
        System.out.println("ping(3002): " + recentCounter.ping(3002)); // Expected: 3

        // Additional test cases
        System.out.println("ping(3003): " + recentCounter.ping(3003)); // Expected: 3
        System.out.println("ping(6000): " + recentCounter.ping(6000)); // Expected: 2
    }
}
