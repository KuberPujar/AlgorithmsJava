package bitmanipulation.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
public class SubSets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int total = 1 << n; // 2^n subsets

        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();
            buildSubset(nums, mask, 0, subset, result);
        }
        return result;
    }

    // Backtracking logic for each bitmask
    private void buildSubset(int[] nums, int mask, int idx, List<Integer> curr, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }
        if ((mask & (1 << idx)) != 0) {
            curr.add(nums[idx]);
            buildSubset(nums, mask, idx + 1, curr, result);
            curr.remove(curr.size() - 1);
        } else {
            buildSubset(nums, mask, idx + 1, curr, result);
        }
    }

    public static void main(String[] args) {
        SubSets solution = new SubSets();
        int[] input = {1, 2, 3};
        List<List<Integer>> subsets = solution.subsets(input);
        System.out.println(subsets); // Output: [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
    }
}
