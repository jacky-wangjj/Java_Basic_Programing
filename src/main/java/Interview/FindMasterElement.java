package main.java.Interview;

import java.util.ArrayList;

/**
 * 题目：找出数组中出现次数超过数组长度一半的元素
 * <p>
 * 我们要找的数字超过了数组长度的一半，那么我们可以这样做：
 * 利用两个辅助变量，一个记录数字出现的次数，一个记录数字；
 * 当后面的数字与其相等次数加1，不同减1，当次数为0时，记录数字就换成此时的数字。
 * 最终得到的数字有可能是所求，但是还不一定（例如1,2,3,4,5,6,1,1），
 * 还需要重新遍历一次数组，查看此值出现是否超过数组长度一半。
 *
 * @author wangjj17@lenovo.com
 * @date 2019/7/8
 */
public class FindMasterElement {
    int get(int A[], int n) {
        int result, cnt;
        result = A[0];
        cnt = 1;
        for (int i = 1; i < n; i++) {
            if (A[i] == result) {
                cnt++;
            } else if (cnt == 1) {
                result = A[i];
                cnt = 1;
            } else {
                cnt--;
            }
        }
        cnt = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] == result) {
                cnt++;
            }
        }
        if (cnt > (n / 2)) {
            return result;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        FindMasterElement f = new FindMasterElement();
        int result = f.get(a, a.length);
        System.out.println(result);
    }
}