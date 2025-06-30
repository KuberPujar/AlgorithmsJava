package queues.simulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:

If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the i​​​​​​th sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the j​​​​​​th student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.



Example 1:

Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
Output: 0
Explanation:
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.
Example 2:

Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
Output: 3


Constraints:

1 <= students.length, sandwiches.length <= 100
students.length == sandwiches.length
sandwiches[i] is 0 or 1.
students[i] is 0 or 1.
 */
public class NumberOfStudentsUnableToEatLunch {

    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> studentQueue = new LinkedList<>();

        // Initialize queue with student preferences
        for (int student : students) {
            studentQueue.offer(student);
        }

        int sandwichIndex = 0; // Points to top of sandwich stack
        int rotations = 0; // Count how many students have been rotated without eating

        while (!studentQueue.isEmpty() && sandwichIndex < sandwiches.length) {
            // If student at front wants the current sandwich
            if (studentQueue.peek() == sandwiches[sandwichIndex]) {
                studentQueue.poll(); // Student takes sandwich and leaves
                sandwichIndex++; // Move to next sandwich
                rotations = 0; // Reset rotation counter
            } else {
                // Student doesn't want current sandwich, go to back of queue
                int student = studentQueue.poll();
                studentQueue.offer(student);
                rotations++;

                // If all remaining students have been rotated without eating,
                // none of them want the current sandwich
                if (rotations == studentQueue.size()) {
                    break;
                }
            }
        }

        return studentQueue.size(); // Number of students who couldn't eat
    }

    public int countStudentsOptimized(int[] students, int[] sandwiches) {
        // Count preferences
        int[] count = new int[2]; // count[0] = students wanting circular, count[1] = students wanting square

        for (int student : students) {
            count[student]++;
        }

        // Process sandwiches from top of stack
        for (int sandwich : sandwiches) {
            if (count[sandwich] > 0) {
                count[sandwich]--; // A student takes this sandwich
            } else {
                // No student wants this sandwich, remaining students can't eat
                break;
            }
        }

        return count[0] + count[1]; // Total students who couldn't eat
    }

    public static void main(String[] args) {
        NumberOfStudentsUnableToEatLunch solution = new NumberOfStudentsUnableToEatLunch();

        // Example 1: students = [1,1,0,0], sandwiches = [0,1,0,1]
        int[] students1 = {1, 1, 0, 0};
        int[] sandwiches1 = {0, 1, 0, 1};

        System.out.println("Example 1:");
        System.out.println("Students: " + Arrays.toString(students1));
        System.out.println("Sandwiches: " + Arrays.toString(sandwiches1));
        System.out.println("Queue Solution: " + solution.countStudents(students1, sandwiches1));
        System.out.println("Optimized Solution: " + solution.countStudents(students1, sandwiches1));
        System.out.println("Expected: 0\n");

        // Example 2: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
        int[] students2 = {1, 1, 1, 0, 0, 1};
        int[] sandwiches2 = {1, 0, 0, 0, 1, 1};

        System.out.println("Example 2:");
        System.out.println("Students: " + Arrays.toString(students2));
        System.out.println("Sandwiches: " + Arrays.toString(sandwiches2));
        System.out.println("Queue Solution: " + solution.countStudents(students2, sandwiches2));
        System.out.println("Optimized Solution: " + solution.countStudents(students2, sandwiches2));
        System.out.println("Expected: 3\n");

        // Additional test cases
        int[] students3 = {1, 1, 1, 1};
        int[] sandwiches3 = {0, 0, 0, 0};

        System.out.println("Additional Test 1 (All students want square, all sandwiches circular):");
        System.out.println("Students: " + Arrays.toString(students3));
        System.out.println("Sandwiches: " + Arrays.toString(sandwiches3));
        System.out.println("Queue Solution: " + solution.countStudents(students3, sandwiches3));
        System.out.println("Optimized Solution: " + solution.countStudents(students3, sandwiches3));
        System.out.println("Expected: 4\n");

        // Detailed simulation for Example 1
        System.out.println("Detailed simulation for Example 1:");
        simulateDetailed(students1, sandwiches1);
    }

    // Method to show detailed step-by-step simulation
    public static void simulateDetailed(int[] students, int[] sandwiches) {
        Queue<Integer> studentQueue = new LinkedList<>();

        // Initialize queue
        for (int student : students) {
            studentQueue.offer(student);
        }

        int sandwichIndex = 0;
        int rotations = 0;
        int step = 0;

        System.out.println("Initial state:");
        System.out.println("Students queue: " + studentQueue);
        System.out.println("Sandwiches stack: " + Arrays.toString(sandwiches));
        System.out.println("Current sandwich (top): " + sandwiches[sandwichIndex]);
        System.out.println();

        while (!studentQueue.isEmpty() && sandwichIndex < sandwiches.length) {
            step++;
            System.out.println("Step " + step + ":");

            int frontStudent = studentQueue.peek();
            System.out.println("Front student preference: " + frontStudent);
            System.out.println("Current sandwich: " + sandwiches[sandwichIndex]);

            if (frontStudent == sandwiches[sandwichIndex]) {
                studentQueue.poll();
                sandwichIndex++;
                rotations = 0;
                System.out.println("✓ Student takes sandwich and leaves!");
                System.out.println("Students remaining: " + studentQueue);
                if (sandwichIndex < sandwiches.length) {
                    System.out.println("Next sandwich: " + sandwiches[sandwichIndex]);
                }
            } else {
                int student = studentQueue.poll();
                studentQueue.offer(student);
                rotations++;
                System.out.println("✗ Student goes to back of queue");
                System.out.println("Students queue: " + studentQueue);
                System.out.println("Rotations without eating: " + rotations);

                if (rotations == studentQueue.size()) {
                    System.out.println("All remaining students have been rotated - deadlock!");
                    break;
                }
            }
            System.out.println();
        }

        System.out.println("Final result:");
        System.out.println("Students who couldn't eat: " + studentQueue.size());
        if (!studentQueue.isEmpty()) {
            System.out.println("Remaining students: " + studentQueue);
        }
    }
}
