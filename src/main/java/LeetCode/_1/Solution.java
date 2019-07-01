package main.java.LeetCode._1;

import java.util.Arrays;

/**
 * @author wangjj17@lenovo.com
 * @date 2019/7/1
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 结果：
     * [-3, 4, 3, 90] -- 0 ---> [0, 2]
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
//        int[] nums = {0, 7, 11, 0};
//        int target = 0;
        int[] nums = {-3, 4, 3, 90};
        int target = 0;
        int[] result = new Solution().twoSum(nums, target);
        System.out.println(Arrays.toString(nums) + " -- " + target + " ---> " + Arrays.toString(result));
    }
}