package BinarySearchTree;
/*
Book Allocation Problem
You have N books, each with A[i] number of pages. M students need to be allocated contiguous books, with each student getting at least one book. Out of all the permutations, the goal is to find the permutation where the student with the most books allocated to him gets the minimum number of pages, out of all possible permutations.

Note:
Return -1 if a valid assignment is not possible, and allotment should be in contiguous order (see the explanation for better understanding).

Input Format:

The first line contains an integer N, representing the number of books.
The second line contains N space-separated integers, where each integer A[i] represents the number of pages in the i-th book.
The third line contains an integer M, representing the number of students.
Output Format:

Print a single integer representing the minimum possible maximum number of pages that can be allocated to a student.

Input 1:

4
12 34 67 90
2
Output 1:

113
`Explanation:
Allocation can be done in the following ways:

{12} and {34, 67, 90} Maximum Pages = 191
{12, 34} and {67, 90} Maximum Pages = 157
{12, 34, 67} and {90} Maximum Pages =113.
Therefore, the minimum of these cases is 113,
which is selected as the output.

Input 2:

3
15 17 20
2
Output 2:

32
`Explanation:
Given the array of books {15, 17, 20} and 2 students, the goal is to distribute the books in a way that the maximum pages one student has to read is as small as possible. Here are the possible ways to allocate:

Assign {15} to the first student and {17, 20} to the second student and Maximum Pages = 37
Assign {15, 17} to the first student and {20} to the second student and Maximum Pages = 32
Out of these possible allocations, the minimum possible maximum pages is 32, which means this is the most optimal way to distribute the books between the two students. Thus, the output is 32.

Expected Time Complexity:
O(NlogN)

Expected Auxilliary Space:
O(1)

Constraints:
1 <= N <= 10^5

1 <= A [ i ] <= 10^6

1 <= M <= 10^5

Note:The function should return the result. The driver code will handle printing the output.
 */
public class BookAllocation {
    public static void main(String[] args) {
        int[] books = {12, 34, 67, 90};
        int students = 2;
        System.out.println(findPages(books,books.length, students));
    }

    public static int findPages(int[] A, int N, int M) {
        if (M > N) {
            return -1;
        }
        int low = 0;
        int high = 0;
        for (int i = 0; i < N; i++) {
            low = Math.max(low, A[i]);
            high += A[i];
        }
        int result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isFeasible(A, N, M, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    private static boolean isFeasible(int[] A, int N, int M, int mid) {
        int studentsRequired = 1;
        int pagesAllocated = 0;

        for (int i = 0; i < N; i++) {
            if (pagesAllocated + A[i] > mid) {
                studentsRequired++;
                pagesAllocated = A[i];
                if (studentsRequired > M) {
                    return false;
                }
            } else {
                pagesAllocated += A[i];
            }
        }
        return true;
    }
}
