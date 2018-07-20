package main.java.SortAlgorithm;

/**
 * Created by Administrator on 2018/7/20.
 */
public class Bubble {
    /**
     * 冒泡排序
     * 两两比较相邻元素，逆序则交换位置，直到没有元素交换为止
     * @param a
     */
    public void sort(int[] a) {
        int flag = 0;//交换标志为0
        for(int i=0; i<a.length-1; i++) {//排序总次数
            for(int j=a.length-1; j>i; j--) {//内循环控制排序的进行
                if(a[j] < a[j-1]) {//相邻元素比较，逆序交换
                    exch(a, j, j-1);
                    flag = 1;//发生交换，将标志位置位
                }
            }
            if (flag == 0)//本次排序没有发生交换，则排序完毕，终止操作
                break;
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
        for(int i=0; i<a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {9,2,5,6,3,2,1,10,4};
        Bubble s = new Bubble();
        s.resultPrint("before sort:", a);
        s.sort(a);
        s.resultPrint("after sort:", a);
    }
}
