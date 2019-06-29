package main.java.Interview;

/**
 * 题目：
 * 删除字符串中的"b"和"ac"，即保证删除后的结果中不出现"b"和"ac"
 * 例如：acbac ==> ""; aaac ==> aa; ababc ==> a
 *
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class DeleteBandACFromString {

    public String deleteChar(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        while (i < sb.length()) {
            if ('b' == sb.charAt(i)) {
                sb.delete(i, i + 1);
                if (i >= 1) {
                    i--;
                    continue;
                }
            } else {
                if (i < sb.length() - 1) {
                    if ('a' == sb.charAt(i) && 'c' == sb.charAt(i + 1)) {
                        sb.delete(i, i + 2);
                        if (i >= 1) {
                            i--;
                            continue;
                        }
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "acbac";
        String s2 = "aaac";
        String s3 = "ababc";
        DeleteBandACFromString d = new DeleteBandACFromString();
        String r1 = d.deleteChar(s1);
        String r2 = d.deleteChar(s2);
        String r3 = d.deleteChar(s3);
        System.out.println(s1 + " ==> " + r1);
        System.out.println(s2 + " ==> " + r2);
        System.out.println(s3 + " ==> " + r3);
    }
}