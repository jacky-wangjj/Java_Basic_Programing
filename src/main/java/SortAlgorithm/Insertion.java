package main.java.SortAlgorithm;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/7/20.
 */
public class Insertion {
    /**
     * 直接插入排序
     * 将无序区中待排元素a[i]和有序区中元素循环比较，
     * a[i]元素小于a[j-1]元素，交换两元素位置。
     * @param a
     */
    public void sort(int[] a) {
        int i, j;
        for(i=1; i<a.length; i++) {//i为待排的无序区元素
            for(j=i; j>0; j--) {//j为有序区中范围
                if(a[j] < a[j-1]) {
                    exch(a, j, j-1);
                }
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
        Insertion s = new Insertion();
        s.resultPrint("before sort:", a);
        s.sort(a);
        s.resultPrint("after sort:", a);
    }
}
