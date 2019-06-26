package main.java.SortAlgorithm;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/7/20.
 */
public class Shell {
    /**
     * 希尔排序
     * 基于插入排序的算法，h有序数组
     * @param a
     */
    public void sort(int[] a) {
        int N = a.length;
        int h = 1;
        while(h<N/3) {
            h = 3 * h + 1;
        }
        while(h >= 1) {
            for(int i=h; i<N; i++) {//将数组变为h有序
                for(int j=i; j>=h; j-=h) {//将a[i]插入到a[i-h]、a[i-2*h]，a{i-3*h}中
                    if (a[j] < a[j - h]) {
                        exch(a, j, j - h);
                    }
                }
            }
            h = h/3;
        }
    }

    /**
     * 交换数组a中，i位置和j位置的元素
     * @param a
     * @param i
     * @param j
     */
    public void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     *
     * @param tags
     * @param a
     */
    public void resultPrint(String tags, int[] a) {
        System.out.println(tags);
        System.out.println(Arrays.toString(a));
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {9,2,5,6,3,2,1,10,4};
        Shell s = new Shell();
        s.resultPrint("before sort:", a);
        s.sort(a);
        s.resultPrint("after sort:", a);
    }
}
