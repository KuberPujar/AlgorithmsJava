package arrays;

import java.util.Scanner;

/*
Minimum Number of Moves to Seat Everyone:

There are n available seats and n students standing in a room. You are given an array seats of length n, where seats[i] is the position of the ith seat. You are also given the array students of length n, where students[j] is the position of the jth student.

You may perform the following move any number of times:

Increase or decrease the position of the ith student by 1 (i.e., moving the ith student from position x to x + 1 or x - 1)
Return the minimum number of moves required to move each student to a seat such that no two students are in the same seat.

Note that there may be multiple seats or students in the same position at the beginning.



Example 1:

Input: seats = [3,1,5], students = [2,7,4]
Output: 4
Explanation: The students are moved as follows:
- The first student is moved from position 2 to position 1 using 1 move.
- The second student is moved from position 7 to position 5 using 2 moves.
- The third student is moved from position 4 to position 3 using 1 move.
In total, 1 + 2 + 1 = 4 moves were used.
Example 2:

Input: seats = [4,1,5,9], students = [1,3,2,6]
Output: 7
Explanation: The students are moved as follows:
- The first student is not moved.
- The second student is moved from position 3 to position 4 using 1 move.
- The third student is moved from position 2 to position 5 using 3 moves.
- The fourth student is moved from position 6 to position 9 using 3 moves.
In total, 0 + 1 + 3 + 3 = 7 moves were used.
Example 3:

Input: seats = [2,2,6,6], students = [1,3,2,6]
Output: 4
Explanation: Note that there are two seats at position 2 and two seats at position 6.
The students are moved as follows:
- The first student is moved from position 1 to position 2 using 1 move.
- The second student is moved from position 3 to position 6 using 3 moves.
- The third student is not moved.
- The fourth student is not moved.
In total, 1 + 3 + 0 + 0 = 4 moves were used.


Constraints:

n == seats.length == students.length
1 <= n <= 100
1 <= seats[i], students[j] <= 100
 */
public class MinimumNoOfMovesToSeatEveryone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of seats and students: ");
        int n = scanner.nextInt();
        int[] seats = new int[n];
        int[] students = new int[n];
        System.out.println("Enter the seats: ");
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.nextInt();
        }
        System.out.println("Enter the students: ");
        for (int i = 0; i < n; i++) {
            students[i] = scanner.nextInt();
        }
        int result = minMovesToSeat(seats, students);
        System.out.println("Minimum number of moves to seat everyone: " + result);
    }

    private static int minMovesToSeat(int[] seats,int[] students){
        int n=seats.length;
        mergeSort(seats,0,seats.length-1);
        mergeSort(students,0,students.length-1);
        int moves=0;
        for(int i=0;i<n;i++){
           moves+=Math.abs(seats[i]-students[i]);
        }
        return moves;
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r-l)/2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }

   private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
