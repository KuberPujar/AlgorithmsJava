package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Course Planner
You are given numCourses courses labelled from 0 to numCourses - 1. You are also given an array of prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi before you can take course ai.

For example, the pair [0, 1] means that to take course 0, you must first complete course 1.

Write a function to determine if it is possible to finish all courses. In other words, return true if there are no cycles in the course dependencies (i.e., the course prerequisites form a Directed Acyclic Graph (DAG)). Otherwise, return false.

Input Format:

The first line contains an integer numCourses representing the number of courses. and contains an integer prerequisites.length representing the number of prerequisite pairs..
Each of the following lines contains two integers ai and bi representing a prerequisite relationship where course bi must be completed before course ai.
Output Format:

Return true if it is possible to finish all courses (i.e., there are no cycles in the course dependencies), otherwise return false.
Example:

Input 1:

numCourses = 2
prerequisites = [[1,0]]
Output 1:

true
Explanation:
You can finish both courses. To take course 1, you should have finished course 0. So it is possible.

Input 2:

numCourses = 6
prerequisites = [[1,0], [2,1], [3,2], [4,3], [5,4], [1,5]]
Output 2:

false
Explanation:
You can complete all 6 courses because the prerequisites form a directed acyclic graph (DAG). Each course has a clear path of prerequisites without any cycles. Therefore, it's possible to finish all courses.

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
Note:The function should return the result. The driver code will handle printing the output.
 */
public class CoursePlanner {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];

        // Build the graph and in-degree array
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            graph.get(preCourse).add(course);
            inDegree[course]++;
        }

        // Queue for courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();

        // Add all courses with no prerequisites to the queue
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;

            // Decrease the in-degree of neighboring courses
            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If count equals numCourses, all courses can be finished
        return count == numCourses;
    }

    //main method for testing
    public static void main(String[] args) {
        int numCourses = 6;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}, {4, 3}, {5, 4}, {1, 5}};

        boolean result = canFinish(numCourses, prerequisites);
        System.out.println(result); // Output: false
    }
}
