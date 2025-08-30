package bitmanipulation.techniques;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
Print all possible subsets of a given array. For example, if the array provided is a = {1, 2, 3}, then its possible subsets are:

{}, {1}, {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3}, which are in total 8.

 */
public class GenerateAllPossibleSets {
    /*
    With the use of a traditional backtracking approach we can solve this where at every turn we either choose to include the element or do not choose to include.
     */
    static void generateSubsets(List<Integer> nums, List<List<Integer>> subsets, List<Integer> current, int index) {
        if (index == nums.size()) {
            subsets.add(new ArrayList<>(current));
            return;
        }

        // Include the current element
        current.add(nums.get(index));
        generateSubsets(nums, subsets, current, index + 1);

        // Backtrack and exclude the current element
        current.removeLast();
        generateSubsets(nums, subsets, current, index + 1);
    }

    static List<List<Integer>> subsets(List<Integer> nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generateSubsets(nums, subsets, current, 0);
        return subsets;
    }

    /*
    The bit-manipulation approach is easier as compared to backtracking. If we see we only have two choices, either we choose an element or we do not choose the element, since there are two choices that assign these values binary representation where 1 means choose and 0 means not choose. Now there will be 2^n subsets possible and we can have a unique binary representation for all these numbers.
     In the bit-manipulation approach also we are generating all the subsets and storing those subset in a subsets array whose size can be of potentially n. So the space and time complexity is the same which is O(nx2^n), but it is useful because there will be no overhead of recursive calls.

Time Complexity: O(n x 2^n)

Space Complexity: O(n x 2^n)
     */

    static void possibleSubsets(int[] nums, int N) {
        for (int i = 0; i < (1 << N); ++i) {
            for (int j = 0; j < N; ++j) {
                if ((i & (1 << j)) != 0) {
                    System.out.print(nums[j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int N = nums.length;
        possibleSubsets(nums, N);

        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }

        List<List<Integer>> result = subsets(numList);
        System.out.println(result);
    }
}
