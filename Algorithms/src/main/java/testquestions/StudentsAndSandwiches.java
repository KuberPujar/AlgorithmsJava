package testquestions;
/*
Number of students unable to eat lunch
The school cafeteria offers circular and square sandwiches at lunch break, represented by numbers 0 and 1, respectively. All students stand in a queue, expressing their preference for either square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students, and these sandwiches are placed in a stack. The lunch process unfolds as follows:

If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue. Otherwise, they will leave the sandwich on the top of the stack and go to the end of the queue. This process continues until none of the students in the queue want to take the top sandwich, rendering them unable to eat.

You are given two integer arrays, students and sandwiches, where sandwiches[i] represents the type (0 or 1) of the i​​​​​​th sandwich in the stack, and students[j] represents the preference (0 or 1) of the j​​​​​​th student in the initial queue. Your task is to return the number of students who are unable to eat.

Input:

students: A list of integers representing the preferences of students (0 or 1).
sandwiches: A list of integers representing the types of sandwiches in the stack (0 or 1).
Output:

An integer representing the number of students unable to eat.

Examples:
Example:1

students = [1, 1, 1, 0, 0, 1]
sandwiches = [1, 0, 0, 0, 1, 1]
Output:
3

Example2:

students = [1, 1, 0, 0] sandwiches = [0, 1, 0, 1]

Output:

0

Constraints:

1 <= students.length, sandwiches.length <= 100
students.length == sandwiches.length
sandwiches[i] is 0 or 1.
students[i] is 0 or 1.
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StudentsAndSandwiches {
    public static void main(String[] args) {
        int[] students = {1, 1, 1, 0, 0, 1};
        int[] sandwiches = {1, 0, 0, 0, 1, 1};
        System.out.println(countStudents(students, sandwiches));
    }

    public static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> studentQueue = new LinkedList<>();
        Stack<Integer> sandwichStack = new Stack<>();

        // Initialize queue with student preferences
        for (int student : students) {
            studentQueue.add(student);
        }

        // Initialize stack with sandwiches (push in reverse order)
        for (int i = sandwiches.length - 1; i >= 0; i--) {
            sandwichStack.push(sandwiches[i]);
        }

        int unableToEat = 0;
        int consecutiveMisses = 0;

        while (!studentQueue.isEmpty() && !sandwichStack.isEmpty()) {
            if (studentQueue.peek().equals(sandwichStack.peek())) {
                // Student takes the sandwich
                studentQueue.remove();
                sandwichStack.pop();
                consecutiveMisses = 0; // Reset consecutive misses
            } else {
                // Student goes to end of queue
                studentQueue.add(studentQueue.remove());
                consecutiveMisses++;

                // If all students have cycled without finding a match
                if (consecutiveMisses >= studentQueue.size()) {
                    unableToEat = studentQueue.size();
                    break;
                }
            }
        }

        return unableToEat;
    }
}
