package graphs.dfs;

import java.util.List;
import java.util.Stack;

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
public class KeyAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, 0, visited);

        // Check if all rooms were visited
        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false;
            }
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int currentRoom, boolean[] visited) {
        visited[currentRoom] = true;

        // Visit all rooms that we have keys for
        for (int key : rooms.get(currentRoom)) {
            if (!visited[key]) {
                dfs(rooms, key, visited);
            }
        }
    }

    //Iterative DFS approach
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        visited[0] = true;

        while (!stack.isEmpty()) {
            int currentRoom = stack.pop();

            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true;
                    stack.push(key);
                }
            }
        }

        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        KeyAndRooms keyAndRooms = new KeyAndRooms();
        List<List<Integer>> rooms1 = List.of(List.of(1), List.of(2), List.of(3), List.of());
        System.out.println(keyAndRooms.canVisitAllRooms(rooms1)); // Output: true

        List<List<Integer>> rooms2 = List.of(List.of(1, 3), List.of(3, 0, 1), List.of(2), List.of(0));
        System.out.println(keyAndRooms.canVisitAllRooms(rooms2)); // Output: false
    }
}
