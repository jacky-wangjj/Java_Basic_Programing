package main.java.StringOperations;

/**
 * Created by Administrator on 2018/7/19.
 */
public class CompareVersion {
    /**
     * 传入string s，判断s中是否有字符，若有返回字符的位置；没有返回len；
     * @param s
     * @return
     */
    public int existChar(String s) {
        int len = s.length();
        int i = 0;
        while(i < len)
        {
            if((s.charAt(i)-'9') > 0) {
                return i;
            }
            i++;
        }
        return len;
    }
    /**
     * 字符串s1和s2比较，s1与s2相等返回0，s1大于s2，返回正数，s1小于s2，返回负数。
     * @param s1
     * @param s2
     * @return
     * @throws Exception
     */
    public int stringCompare(String s1, String s2) throws Exception {
        if (s1 == null || s2 == null) {
            throw new Exception("mycompareTo error:illegal params.");
        }
        int len1 = s1.length();
        int len2 = s2.length();
        int len = Math.min(len1, len2);

        int k = 0;
        while(k < len)
        {
            if(s1.charAt(k) != s2.charAt(k)) {
                return s1.charAt(k)-s2.charAt(k);
            }
            k++;
        }
        return len1 - len2;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * 规则：
     * 1。先将version字符串，以‘.’为分隔符，解析到字符串数组中，
     * 2。依次遍历每个字符串数组中的元素，确认字符串中是否有字母存在，
     * 3.字符串中不存在字母即纯数字时，将字符串转换成整数比较大小，或是先比较数字字符串的长度，长度长的大，长度一致时，依次比较字符串中字符的大小，字符串大的大。
     * 4.字符串中存在字母时，分别解析出数字和字母，然后先比较数字大小，再比较字母大小。
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        int l1 = 0;
        int l2 = 0;
        int tl1 = 0;
        int tl2 = 0;
        String v1num, v2num;

        while (idx < minLength && diff == 0)
        {

            l1 = versionArray1[idx].length();
            l2 = versionArray2[idx].length();
            tl1 = existChar(versionArray1[idx]);
            tl2 = existChar(versionArray2[idx]);
            try {
                if(tl1 == tl2) {//先比较是否都不含字母，或含字母的字符串数字位数相同；位数相同时，可按字符串进行比较。
                    if((diff = l1 -l2) == 0 && (diff = stringCompare(versionArray1[idx], versionArray2[idx])) == 0)
                        ++idx;
                } else {//字符串中含有的数字位数不同，位数多的大。
                    v1num = versionArray1[idx].substring(0, tl1);
                    v2num = versionArray2[idx].substring(0, tl2);
                    diff = v1num.length() - v2num.length();
                }
            } catch (Exception e) {
                System.out.println("illegal params.");
            }
        }

        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    public void PrintResult(String v1, String v2) {
        try {
            int r = compareVersion(v1, v2);
            if (r > 0) {
                System.out.println(v1 +" greater than "+ v2);
            } else if (r < 0) {
                System.out.println(v1 +" less than "+ v2);
            } else {
                System.out.println(v1 +" equal "+ v2);
            }
        } catch (Exception e) {
            System.out.println("compareVersion throw exception");
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("this is a version string compare case.");
        CompareVersion cv = new CompareVersion();
        try {
            cv.PrintResult("2.3.1a", "2.3.1b");
            cv.PrintResult("2.3.1a", "2.3.1");
            cv.PrintResult("2.3.1", "2.3.1");
            cv.PrintResult("2.3.1a", "2.3");
            cv.PrintResult("10.3.1", "9.3.1");

            cv.PrintResult("2.3.10", "2.3.1b");
            cv.PrintResult("2.3.9a", "2.3.10");
        } catch (Exception e) {
            System.out.println("compareVersion throw exception");
        }
    }
}
