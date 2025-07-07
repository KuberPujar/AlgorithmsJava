package graphs.cycledetection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*


Hint
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */
public class CourseSchedule_II {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] pre : prerequisites) graph[pre[1]].add(pre[0]);
        int[] state = new int[numCourses]; // 0=unvisited, 1=visiting, 2=visited
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && !dfs(i, graph, state, order)) return new int[0];
        }
        Collections.reverse(order);
        return order.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int node, List<Integer>[] graph, int[] state, List<Integer> order) {
        state[node] = 1; // visiting
        for (int nei : graph[node]) {
            if (state[nei] == 1) return false; // cycle detected
            if (state[nei] == 0 && !dfs(nei, graph, state, order)) return false;
        }
        state[node] = 2; // visited
        order.add(node);
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule_II solution = new CourseSchedule_II();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] order = solution.findOrder(numCourses, prerequisites);
        for (int course : order) {
            System.out.print(course + " ");
        }
        // Output: [0, 1, 2, 3] or [0, 2, 1, 3]
    }
}
