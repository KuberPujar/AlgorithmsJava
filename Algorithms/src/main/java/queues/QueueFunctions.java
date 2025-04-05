package queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
Queue Functions
You are given an array of integers and another integer 'k'. Your task is to perform the following operations in the given order:

Implement a queue with integers from the given array.
Remove the first element of the queue.
Print the first element of the queue.
Find if the integer 'k' exists in the queue.
Finally, return the queue.
Input Format:

The first line contains two integers 'n' (the size of the array) and 'k' (the integer whose existence in the implemented queue has to be checked).
The second line contains 'n' space-separated integers representing the elements of the array.
Output Format:

Complete the operations and return the output (if it produces an output) of each operation on a new line.
Sample Input 1:

5 4
1 9 11 3 2
Sample Output 1:

9
No
9 11 3 2
Explanation:

The queue is initialized with the elements [1, 9, 11, 3, 2].
The first element (1) is removed, resulting in the queue [9, 11, 3, 2].
The first element (9) is printed.
The integer '4' is checked for existence in the queue and is not found, so "No" is printed.
The final state of the queue [9, 11, 3, 2] is returned.
Constraints:

1 ≤ k ≤ 10^6
2 ≤ n ≤ 10^6
1 ≤ ar[i] ≤ 10^6
Note: The function should print the intermediate results and return the final queue.
 */
public class QueueFunctions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<n;i++){
            queue.add(scanner.nextInt());
        }
        //remove first element
        if(!queue.isEmpty()){
            queue.poll();
        }
        //print first element
        if(!queue.isEmpty()){
            System.out.println(queue.peek());
        }

        //check if k exists in the queue
        if(queue.contains(k)){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
        //print the queue
        for(int i:queue){
            System.out.print(i+" ");
        }
        System.out.println();
    }

}
