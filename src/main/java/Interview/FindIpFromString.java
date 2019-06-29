package main.java.Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 题目：找出一个字符串中的所有IP地址并打印，注意：不能使用正则表达式
 *
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class FindIpFromString {
    /**
     * 连续考查三个"."，如果中间两个是整数，且左右两个都有整数
     *
     * @param ipString
     * @return
     */
    public static List<String> getIps(String ipString) {
        List<String> ips = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] t = ipString.split("\\.");
        //4个一组进行判断
        for (int i = 0; i < t.length - 3; i++) {
            if (check(t[i + 1]) && check(t[i + 2])) {//中间两个符合
                int flag = 0;
                int len1 = t[i].length();
                int n1 = len1 >= 3 ? 3 : len1;
                for (int j = 0; j < n1; j++) {
                    String t1 = t[i].substring(len1 - 3 + j, len1);
                    if (check(t1)) {
                        if (!"0".equals(t1)) {//ip首个位置不能是0
                            sb.append(t1);
                            flag++;
                            break;
                        }
                    }
                }
                sb.append("." + t[i + 1] + "." + t[i + 2] + ".");
                int len4 = t[i + 3].length();
                int n4 = len4 >= 3 ? 3 : len4;
                for (int j = 0; j < n4; j++) {
                    String t4 = t[i + 3].substring(0, 3 - j);
                    if (check(t4)) {
                        sb.append(t4);
                        flag++;
                        break;
                    }
                }
                if (2 == flag) {
                    ips.add(sb.toString());
                    sb.delete(0, sb.length());
                } else {
                    sb.delete(0, sb.length());
                }
            }
        }
        return ips;
    }

    public static boolean check(String s) {
        //首先判断字符是否是纯数字，若不是则直接返回false
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        //纯数字转换成整数，判断是否0~255
        int n = Integer.parseInt(s);
        if (n < 256 && n >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 使用正则表达式提取字符串中的ip地址
     *
     * @param ipString
     * @return java.util.List<java.lang.String>
     *
     * @author wangjj17@lenovo.com
     * @date 2019/6/29
     */
    public static List<String> getIpsReg(String ipString) {
        String regEx = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        List<String> ips = new ArrayList<String>();
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ipString);
        while (m.find()) {
            String result = m.group();
            ips.add(result);
        }
        return ips;
    }

    public static void main(String[] args) {
        String ips = "!254.254.254.254 0.0.0.1localhost192.168.2.1localhost";
        System.out.println(getIpsReg(ips));
        System.out.println(getIps(ips));
    }
}