package main.java.SortAlgorithm;

public class Quick {

    /**
     * 快速排序
     * 递归排序，每次排定一个元素，再分别在元素左右两个子区间中递归排序
     * @param a
     * @param low
     * @param high
     */
    public void sort(int[] a, int low, int high) {
        if(low >= high) return;
        int p = partition(a, low, high);
        sort(a, low, p - 1);
        sort(a, p + 1, high);
    }

    /**
     * 关键函数，查找一个元素的最终位置
     * @param a
     * @param low
     * @param high
     * @return
     */
    public int partition(int[] a, int low, int high) {
        int l = low, h = high+1;
        int t = a[low];//选取第一个元素作为待排元素，使用low位置元素与其他元素比较。
        while(true) {
            while(a[++l] < t) if(l==high) break;//从左侧l+1位置元素开始查找大于等于待排元素的位置l
            while(t < a[--h]) if(h==low) break;//从右侧最后一个元素开始查找小于等于待排元素的位置h
            if(l >= h) break;
            exch(a, l, h);//显然l和h位置的元素位置不对，因此交换两元素的位置
        }
        exch(a, low, h);//交换h位置元素和第一个元素，即将待排元素放入排定位置
        return h;
    }
/*
    public int partition(int[] a, int low, int high) {
        int l = low, h = high+1;
        int t = a[low];//选取第一个元素作为待排元素，使用low位置元素与其他元素比较。
        while(l < h) {
            while(l < h && t < a[--h]);//从右侧最后一个元素开始选取小于待排元素
            a[l] = a[h];//将小于待排元素的右侧元素放入l位置，此时h位置空闲。
            while(l < h && a[++l] < t);//从左侧l+1位置元素开始选取大于待排元素
            a[h] = a[l];//将大于待排元素的左侧元素放入h位置，此时新l位置空闲。
        }
        a[l] = t;//位置l为待排定元素位置，将待排元素放入l位置。
        return l;
    }
*/
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
    public static void main(String args[]) {
        int[] a = {9,2,5,6,3,2,1,10,4};
        Quick q = new Quick();
        q.resultPrint("before sort:", a);
        q.sort(a, 0, a.length-1);
        q.resultPrint("after sort:", a);
    }
}