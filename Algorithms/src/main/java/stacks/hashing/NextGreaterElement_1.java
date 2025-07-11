package stacks.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
write a java code using stack with hashing techniques for following scenario
The **next greater element** of some element `x` in an array is the **first greater** element that is **to the right**of `x` in the same array.
You are given two **distinct 0-indexed** integer arrays `nums1` and `nums2`, where `nums1` is a subset of `nums2`.
For each `0 <= i < nums1.length`, find the index `j`such that `nums1[i] == nums2[j]` and determine the **next greater element** of `nums2[j]` in `nums2`. If there is no next greater element, then the answer for this query is `-1`.
Return *an array *`ans`* of length *`nums1.length`* such that *`ans[i]`* is the ****next greater element**** as described above.*
 
**Example 1:**

```
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.

```

**Example 2:**

```
Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.

```

 
**Constraints:**
* `1 <= nums1.length <= nums2.length <= 1000`
* `0 <= nums1[i], nums2[i] <= 104`
* All integers in `nums1` and `nums2` are **unique**.
* All the integers of `nums1` also appear in `nums2`.
 
**Follow up:**Could you find an`O(nums1.length + nums2.length)`solution?
 */
public class NextGreaterElement_1 {
    /**
     * Finds the next greater element for each element in nums1 in nums2.
     *
     * @param nums1 The first array.
     * @param nums2 The second array.
     * @return An array containing the next greater element for each element in nums1.
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        // Process nums2 to build next greater element mapping
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                map.put(nums2[stack.pop()], nums2[i]);
            }
            stack.push(i);
        }

        // Build result for nums1
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }

        return ans;
    }

    public static void main(String[] args) {
        NextGreaterElement_1 solution = new NextGreaterElement_1();

        // Example 1
        int[] nums1_1 = {4, 1, 2};
        int[] nums2_1 = {1, 3, 4, 2};
        int[] ans_1 = solution.nextGreaterElement(nums1_1, nums2_1);
        System.out.println("Example 1: " + Arrays.toString(ans_1)); // Output: [-1, 3, -1]

        // Example 2
        int[] nums1_2 = {2, 4};
        int[] nums2_2 = {1, 2, 3, 4};
        int[] ans_2 = solution.nextGreaterElement(nums1_2, nums2_2);
        System.out.println("Example 2: " + Arrays.toString(ans_2)); // Output: [3, -1]
    }
}
