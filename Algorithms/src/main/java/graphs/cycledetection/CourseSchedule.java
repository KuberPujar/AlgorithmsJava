package graphs.cycledetection;

import java.util.ArrayList;
import java.util.List;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] pre : prerequisites) graph[pre[1]].add(pre[0]);
        int[] state = new int[numCourses]; // 0=unvisited, 1=visiting, 2=visited
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && !dfs(i, graph, state)) return false;
        }
        return true;
    }

    private boolean dfs(int node, List<Integer>[] graph, int[] state) {
        state[node] = 1; // visiting
        for (int nei : graph[node]) {
            if (state[nei] == 1) return false; // cycle detected
            if (state[nei] == 0 && !dfs(nei, graph, state)) return false;
        }
        state[node] = 2; // visited
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        System.out.println(cs.canFinish(numCourses, prerequisites)); // Output: true

        numCourses = 2;
        prerequisites = new int[][]{{1, 0}, {0, 1}};
        System.out.println(cs.canFinish(numCourses, prerequisites)); // Output: false
    }
}
