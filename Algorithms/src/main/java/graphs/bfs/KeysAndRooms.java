package graphs.bfs;

import java.util.*;

/*
There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.



Example 1:

Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation:
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.
Example 2:

Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.


Constraints:

n == rooms.length
2 <= n <= 1000
0 <= rooms[i].length <= 1000
1 <= sum(rooms[i].length) <= 3000
0 <= rooms[i][j] < n
All the values of rooms[i] are unique.
 */
public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        // Start with room 0 (the only initially unlocked room)
        queue.offer(0);
        visited[0] = true;
        int visitedCount = 1;

        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();

            // Visit all rooms that we have keys for
            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true;
                    visitedCount++;
                    queue.offer(key);

                    // Early exit if we've visited all rooms
                    if (visitedCount == n) {
                        return true;
                    }
                }
            }
        }

        // Check if all rooms were visited
        return visitedCount == n;
    }

    // Alternative BFS implementation using a Set to track visited rooms
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited.add(0);

        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();

            for (int key : rooms.get(currentRoom)) {
                if (!visited.contains(key)) {
                    visited.add(key);
                    queue.offer(key);

                    // Early exit if all rooms visited
                    if (visited.size() == rooms.size()) {
                        return true;
                    }
                }
            }
        }

        return visited.size() == rooms.size();
    }

    public static void main(String[] args) {
        KeysAndRooms solution = new KeysAndRooms();
        List<List<Integer>> rooms1 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList()
        );
        System.out.println(solution.canVisitAllRooms(rooms1)); // Output: true

        List<List<Integer>> rooms2 = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(3, 0, 1),
                Arrays.asList(2),
                Arrays.asList(0)
        );
        System.out.println(solution.canVisitAllRooms(rooms2)); // Output: false
    }
}
