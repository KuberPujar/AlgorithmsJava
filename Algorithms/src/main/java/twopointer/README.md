# Two Pointer

## Introduction

### Two Pointer

The Two Pointer Algorithm is a popular approach used in solving programming problems that involve arrays or linked lists. It involves using two pointers that are initialised to different positions in the array or linked list, and then moving them towards each other in a certain way to solve the problem.
One of the primary applications of the Two Pointer is the Sliding Window Technique, which isused to find a continuous subarray or substring in an array or string that satisfies certain conditions. Another common application is to find pairs or triplets of elements in an array that meet certain criteria.

#### Why Learn Two Pointers?
The Two Pointer Algorithm is particularly useful for solving problems that require a linear or linearithmic solution, as it often reduces the time complexity of the solution from O(n^2) to O(n). It can also be used for sorting an array in linear time, which is faster than most other sorting algorithms.
While the Two Pointer is a powerful technique, it does have some limitations. It is not always applicable to all problems and may not always be the optimal solution. However, it remains a valuable tool in the toolkit of any programmer who deals with arrays and linked lists.

#### What is a Two-Pointer Approach?
As the name suggests we will keep track using two pointers, where pointers are nothing but indexes of an array. These two pointers are moved on the data set which could be an array, string or even a linked list and their movement is defined accordingly. In many problem we have to analyze each element of the array compared to its other elements.
To solve problems like these we usually start from the first index and loop through the array one or more times depending on our implementation. Sometimes, we also have to create a temporary array depending on our problem’s requirements.
The above approach might give us the correct result, but it likely won’t give us the most space- and time-efficient solution.
As a result, it is often good to consider whether our problem can be solved efficiently by using the two-pointers approach.
In the two-pointer approach, pointers refer to an array’s indexes. By using pointers, we can process two elements per loop, instead of just one.
Example Usage
Let us try to understand it with some basic examples to see how it works exactly.
Subarray Sum
As the first example, consider a problem where we are given an array of n positive integers and a target sum x, and we want to find a subarray whose sum is x or report that there is no such subarray.
For example, the array

contains a subarray whose sum is 8:
##### Brute Force Way
One simple way of achieving that would be to generate all possible subarrays and out of those subarrays check if anyone has the required sum. The checking of the sum can be done as we are generating the subarrays. Basically if you think about the generation of subarray you have to traverse at every starting point of a subarray and from there generate all the possible ones.You can accomplish that by iterating on all the starting points and for each starting point traverse on all the ending points. This would give you the sum of each possible subarray and at any point if you find the required sum, you can tell that a subarray with sum k exists.
Let us visualize that in the example that was provided
In the array check food the sum 8.

The first step is to iterate on all the starting points

Now traverse on every ending point from that starting point.

Similarly you will do until you reach the end.

Now when you have all the subarrays starting from 1 you can now look at all the subarrays starting from the next index.

And so on, eventually you will come across to the conclusion whether there exists a subarray with required sum or not. If you try to implement this approach it would basically require you to find all the subarrays in the worst case scenario, which are nothing but n*(n+1)/2 and would require us to run two loops, one to traverse all the starting points and an inner loop to traverse on all the ending points of that particular start. The Time Complexity for that would be O(n^2).
##### Two Pointer Way
This problem can be solved in O(n) time by using the two pointers method. The idea is to maintain pointers that point to the first and last value of a subarray. On each turn, the left pointer moves one step to the right, and the right pointer moves to the right as long as the resulting subarray sum is at most x. If the sum becomes exactly x, a solution has been found.
As an example, consider the following array and a target sum x = 8:

The initial subarray contains the values 1, 3 and 2 whose sum is 6:

Then, the left pointer moves one step to the right. The right pointer does not move, because otherwise the subarray sum would exceed x.

Again, the left pointer moves one step to the right, and this time the right pointer moves three steps to the right. The subarray sum is 2 + 5 + 1 = 8, so a subarray whose sum is x has been found.

The running time of the algorithm depends on the number of steps the right pointer moves. While there is no useful upper bound on how many steps the pointer can move on a single turn. We know that the pointer moves a total of O(n) steps during the algorithm, because it only moves to the right. Since both the left and right pointer move O(n) steps during the algorithm, the algorithm works in O(n) time.
### Variations of Two Pointer
Two pointer is a constructive algorithm, so there is no fixed way of how pointers are going to be moving or where they will be placed initially but there are a range of different variations of this that can be applied to a range of problems:

**__Starting from both ends of the array__**, a left pointer at the start (0) and a right pointer at the end of the array.

**__Slow and fast pointer__** , one pointer moving slow (1 step for instance) and another moving fast (2 steps or variable number of steps).

**__Starting from 2 different arrays__**, for merging etc.
**__Split array and then start a pointer from each.__**

**__2 slow pointers starting from the beginning of the array__**, variable / sliding window technique.