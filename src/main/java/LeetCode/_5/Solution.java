package main.java.LeetCode._5;

/**
 * @author wangjj17@lenovo.com
 * @date 2019/7/1
 */
public class Solution {
    public String findPalindrome(String s, int left, int right) {
        int n = s.length();
        int l = left;
        int r = right;
        //从最中间开发查找，比较两侧字符是否相同
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        String longest = "";
        String str;
        for (int i = 0; i < n - 1; i++) {
            //匹配babad
            str = findPalindrome(s, i, i);
            if (str.length() > longest.length()) {
                longest = str;
            }
            //匹配cbbd
            str = findPalindrome(s, i, i + 1);
            if (str.length() > longest.length()) {
                longest = str;
            }
        }
        return longest;
    }

    /**
     * 结果：
     * babad ---> bab
     * cbbd ---> bb
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        String s1 = "babad";
        String s2 = "cbbd";
        System.out.println(s1 + " ---> " + s.longestPalindrome(s1));
        System.out.println(s2 + " ---> " + s.longestPalindrome(s2));
    }
}