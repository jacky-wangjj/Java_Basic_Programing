package main.java.SortAlgorithm;

/**
 * Created by Administrator on 2018/7/20.
 */
public class Bubble {
    /**
     * 冒泡排序
     * 从小到大递增，先排最小元素。
     * 两两比较相邻元素，逆序则交换位置，直到没有元素交换为止
     * @param a
     */
    public void sort(int[] a) {
        for(int i=0; i<a.length-1; i++) {//排序总次数
            int flag = 0;//交换标志为0
            for(int j=a.length-1; j>i; j--) {//内循环控制排序的进行，先排定最小元素
                if(a[j] < a[j-1]) {//相邻元素比较，逆序交换，从小到大递增
                    exch(a, j, j-1);
                    flag = 1;//发生交换，将标志位置位
                }
            }
            if (flag == 0)//本次排序没有发生交换，则排序完毕，终止操作
                break;
        }
    }

    /**
     * 冒泡排序
     * 从小到大递增，先排最大元素。
     * 两两比较相邻元素，逆序则交换位置，直到没有元素交换为止
     * @param a
     */
    public void sort2(int[] a) {
        for(int i=1; i<a.length-1; i++) {//排序总次数
            int flag = 0;//交换标志为0
            for(int j=0; j<a.length-i; j++) {//内循环控制排序的进行，先排定最大元素
                if(a[j+1] < a[j]) {//相邻元素比较，逆序交换，从小到大递增
                    exch(a, j+1, j);
                    flag = 1;//发生交换，将标志位置位
                }
            }
            if (flag == 0)//本次排序没有发生交换，则排序完毕，终止操作
                break;
        }
    }

    /**
     * 冒泡排序
     * 从大到小递减，先排最小元素。
     * 两两比较相邻元素，逆序则交换位置，直到没有元素交换为止
     * @param a
     */
    public void sort3(int[] a) {
        for (int i=0; i<a.length-1; i++) {//排序总次数
            int flag =0;//交换标志位为0
            for (int j=a.length-1; j>i; j--) {//内循环控制排序的进行，先排定最小元素
                if (a[j] > a[j-1]) {//相邻元素比较，逆序交换，从大到小递减
                    exch(a, j, j-1);
                    flag = 1;//发生交换，将标志位置位
                }
            }
            if (flag == 0)//本次排序没有发生交换，则排序完毕，终止操作
                break;
        }
    }

    /**
     * 冒泡排序
     * 从大到小递减，先排最大元素。
     * 两两比较相邻元素，逆序则交换位置，直到没有元素交换为止
     * @param a
     */
    public void sort4(int[] a) {
        for (int i=1; i<a.length; i++) {//排序总次数
            int flag =0;//交换标志位为0
            for (int j=0; j<a.length-i; j++) {//内循环控制排序的进行，先排定最大元素
                if (a[j+1] > a[j]) {//相邻元素比较，逆序交换，从大到小递减
                    exch(a, j+1, j);
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
        int[] a2 = {9,2,5,6,3,2,1,10,4};
        s.resultPrint("before sort2:", a2);
        s.sort2(a2);
        s.resultPrint("after sort2", a2);
        int[] a3 = {9,2,5,6,3,2,1,10,4};
        s.resultPrint("before sort3:", a3);
        s.sort3(a3);
        s.resultPrint("after sort3", a3);
        int[] a4 = {9,2,5,6,3,2,1,10,4};
        s.resultPrint("before sort4:", a4);
        s.sort4(a4);
        s.resultPrint("after sort4", a4);
    }
}
