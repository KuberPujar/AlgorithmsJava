package twopointer;
/*
Given an integer array move all the zeros to the end of the array. while maintaining the relative order of the non-zero elements. Do this without using any extra space.
For example: arr = [9, 3, 0, 2, 0, 4, 5, 0]
So your array should look like this : [9, 3, 2, 4, 5, 0, 0, 0], note that the relative ordering of the other elements is still maintained.
 */
public class MoveZeros {
    //Brute Force Approach
    /*
    One straightforward way of doing this would be to pick every element that is zero and then shift all the elements after that by 1 left and then put the element at the last. Do this operation for every element that is zero and eventually you will have all the zeros at the last. It works in the same way as deletion of elements works on arrays.
For example, consider the array: [1, 0, 2, 4, 0, 6]
Now, according to the brute force way you will one by one send all the zeroes to the end
The initial subarray contains the values 1, 3 and 2 whose sum is 6:

Now you will place this zero at the end and move all the other elements to the left ( Remember that we have to maintain the relative ordering as well.

In a similar manner you will do for every zero and eventually you will have all the zeroes at the end.
The code for it is given below:
function moveZeroes(nums):
                  n = length of nums
                  i = 0
                  while i < n:
                      if nums[i] == 0:  // Find a zero element
                          for j from i+1 to n-1:
                              nums[j-1] = nums[j]
                          nums[n-1] = 0  // Place zero at the end of the array
                          n = n - 1  // Reduce the size of the array to ignore the moved zero
                      else:
                          i = i + 1  // Increment i only if nums[i] is not zero
Since in the worst case we might encounter a number of zeroes at the start of the array in which case we will have to shift ‘n’ elements for all the zeroes which in the worst case could be n.
Thus we are doing ‘n’ amount of operation ‘n’ times so the time complexity will O(n^2) and remember that we are doing this in place so no extra space is used.
Time and Space Complexity
Time Complexity : O(n^2)
Space Complexity : O(1)
     */

    public static void moveZeros(int[] nums){
        int n=nums.length;
        int i=0;
        while(i<n){
            if(nums[i]==0){
                for(int j=i+1;j<n;j++){
                    nums[j-1]=nums[j];
                }
                nums[n-1]=0;
                n=n-1;
            }
            else {
                i=i+1;
            }
        }
    }

    //Two Pointer Approach
    /*
    One very easy way of solving this problem would be to make another array and then copy every element except for the zeros and then fill all the last values with zero. However, we are not allowed to use any extra space and thus have to do this in place. Also, we need to maintain the relative ordering of the elements. One way of doing that would be to declare two pointers ‘slow’ and ‘fast’. initialize the fast and slow pointer, to the same position, the first index. The trick here is to let the slow pointer move if and only if it’s not a 0, and the fast pointer will move 1 step each loop. This way, the slow will only ever walk as far as there are non-zeros in the array, and the fast will walk all the way to the end.

Initialize a pointer to keep track of the position to insert non-zero elements. Iterate through the array, if the element is non-zero, place it at the current i position and increment i.
Once all non-zero elements are placed at the beginning, fill the remaining positions from i to the end of the array with zeros.
Below is the implementation for it.

function movezeroes(nums):

   slow = 0
    for fast from 0 to len(nums) - 1:
        if nums[fast] not equal to 0:
            swap nums[slow] and nums[fast]
            slow += 1
Both your fast and slow pointers move in the forward direction. Your fast pointer goes all the way to the end of the array and never goes back. Similarly your slow pointer too moves in the forward direction. So, in the worst case it will go for the length of the array and no extra space is utilized.

Time and Space Complexity

Time Complexity : O(n)
Space Complexity : O(1)
     */

    public static void moveZerosTwoPointer(int[] nums){
        int slow=0;
        for(int fast=0;fast<nums.length;fast++){
            if(nums[fast]!=0){
                //swap
                int temp=nums[slow];
                nums[slow]=nums[fast];
                nums[fast]=temp;
                slow++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr={9,3,0,2,0,4,5,0};
        moveZerosTwoPointer(arr);
        for(int i:arr){
            System.out.print(i+" ");
        }

        int[] arr1={9,3,0,2,0,4,5,0};
        moveZeros(arr1);
        System.out.println();
        for(int i:arr1) {
            System.out.print(i + " ");
            }
    }
}
