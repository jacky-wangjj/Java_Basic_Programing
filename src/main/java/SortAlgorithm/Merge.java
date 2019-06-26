package main.java.SortAlgorithm;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/7/20.
 */
public class Merge {
    private static int[] aux;

    /**
     * 归并排序
     * @param a
     */
    public void sort(int[] a) {
        aux = new int[a.length];
        sort(a, 0, a.length-1);
    }

    /**
     * 将数组中的元素，分成左右两部分，分别对左右两部分进行排序，
     * 然后将两个有序的子数组，合并成一个数组；
     * @param a
     */
    public void sort(int[] a, int low, int high) {
        if(high <= low) return;
        int mid = low + (high - low)/2;
        sort(a, low, mid);//将左半边排序
        sort(a, mid+1, high);//将右半边排序
        merge(a, low, mid, high);
    }

    /**
     * 合并两个有序的子数组，使用一个缓冲数组，存储待排序的两个子数组。
     * 依次比较两个子数组中的元素，选取较小的元素，递增的排序到原数组a中。
     * @param a
     * @param low
     * @param mid
     * @param high
     */
    public void merge(int[] a, int low, int mid, int high) {
        int i = low, j = mid+1;
        for(int k = low; k <= high; k++)//将a[low...high]复制到aux[low...high]
            aux[k] = a[k];
        for(int k = low; k <= high; k++) {//归并回到a[low...high]
            if       (i>mid)           a[k] = aux[j++];//左半边已经排完
            else if (j>high)          a[k] = aux[i++];//右半边已经排完
            else if (aux[j] < aux[i]) a[k] = aux[j++];//左半边元素大于右半边元素，排右半边元素
            else                      a[k] = aux[i++];//右半边元素大于左半边元素，排左半边元素
        }
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
        Merge s = new Merge();
        s.resultPrint("before sort:", a);
        s.sort(a);
        s.resultPrint("after sort:", a);
    }
}
