package main.java.Interview;

import java.util.Arrays;

/**
 * 题目：有序数组sort[]已经按顺序从小到大排列，在不分配任何新的集合存储空间的情况下
 * 对原数组去重，并返回新数组大小。
 * 例如数组Sort[2,3,3,5,6,6,9,10,10]
 *
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class SortArrayToSet {

    public int getSet(int[] sort) {
        int n = 0;//记录重复元素的个数
        int flag = 0;//标志是否有重复元素
        //从第0个位置到倒数第二个位置
        for (int i = 0; i < sort.length - 1; i++) {
            int k = i + 1;//如果i是重复元素，保留一个
            int j;
            //从第1个位置到动态最后一个位置，当移动n个元素后需调整
            for (j = i + 1; j < sort.length - n; j++) {
                if (sort[i] == sort[j]) {
                    n++;
                    flag = 1;
                } else {
                    break;
                }
            }
            //此时j的位置是不重复的元素，需要移动元素的开始位置。
            if (1 == flag) {
                while (j < sort.length - n) {
                    sort[k++] = sort[j++];
                }
            }
        }
        return sort.length - n;
    }

    public static void main(String[] args) {
        int[] sort = {2, 3, 3, 5, 6, 6, 9, 10, 10};
        SortArrayToSet s = new SortArrayToSet();
        int len = s.getSet(sort);
        System.out.println("sort set:" + len);
        System.out.println(Arrays.toString(Arrays.copyOf(sort, len)));
    }
}