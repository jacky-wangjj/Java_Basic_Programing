package main.java.LeetCode._3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author wangjj17@lenovo.com
 * @date 2019/7/1
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int num = 0;//记录最长子串长度
        int current = 0;//记录当前子串长度
        char[] arr = s.toCharArray();
        LinkedList<Character> temp = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            if (!temp.contains(arr[i])) {
                temp.add(arr[i]);
                current = temp.size();
                if (current > num) {
                    num = current;
                }
            } else {
                //新增字符与原字符串中有重复字符，则删除原子串中重复字符及在它之前的字符，与新增字符组成新的子串
                temp.add(arr[i]);
                int first = temp.indexOf(arr[i]);
                for (int j = 0; j < first; j++) {
                    temp.remove();
                }
                temp.remove();
            }
        }
        return num;
    }

    /**
     * 结果：
     * abcabcbb ---> 3
     * bbbbb ---> 1
     * pwwkew ---> 3
     *
     * @param args
     */
    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        Solution s = new Solution();
        System.out.println(s1 + " ---> " + s.lengthOfLongestSubstring(s1));
        System.out.println(s2 + " ---> " + s.lengthOfLongestSubstring(s2));
        System.out.println(s3 + " ---> " + s.lengthOfLongestSubstring(s3));
    }
}