package main.java.SortAlgorithm;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/7/20.
 */
public class Selection {
    /**
     * 选择排序
     * 依次从无序区中选取最小元素，排进有序区中
     * @param a
     */
    public void sort(int[] a) {
        int i, j, min;//i；递增的有序区，j：递减的无序区，min：无序区中最小元素索引
        for(i=0; i<a.length; i++) {
            min = i;//无序区最小元素索引
            for(j=i+1; j<a.length; j++) {
                if(a[j] < a[min]) {//无序区中查找最小元素
                    min = j;
                }
                exch(a, i, min);//i为递增的有序区
            }
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
        Selection s = new Selection();
        s.resultPrint("before sort:", a);
        s.sort(a);
        s.resultPrint("after sort:", a);
    }
}
