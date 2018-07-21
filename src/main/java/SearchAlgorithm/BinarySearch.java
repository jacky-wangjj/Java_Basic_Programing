package main.java.SearchAlgorithm;

/**
 * 二分查找
 */
public class BinarySearch {
    /**
     *
     * @param a
     * @param val
     * @return
     */
    public int search(int[] a, int val) {
        int low = 0; int high = a.length - 1;
        //return binRecursion(a, val, low, high);
        return bin(a, val);
    }

    /**
     * 递归二分查找
     * @param a
     * @param val
     * @param low
     * @param high
     * @return
     */
    public int binRecursion(int[] a, int val, int low, int high) {
        if(low >= high)
            return -1;
        int mid = (low + high)/2;
        if (val == a[mid])
            return mid;
        else if (val < a[mid])
            binRecursion(a, val, low, --mid);//low到mid-1之间查找
        else
            binRecursion(a, val, ++mid, high);//mid+1到high之间查找
        return -1;
    }

    /**
     * 非递归二分查找
     * @param a
     * @param val
     * @return
     */
    public int bin(int[] a, int val) {
        int low = 0;
        int high = a.length - 1;
        while(low <= high) {
            int mid = low + (high-low)/2;
            if       (val < a[mid])  high = mid - 1;
            else if (val > a[mid])  low = mid + 1;
            else                     return mid;
        }
        return -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {1,3,5,6,9,10};
        BinarySearch b = new BinarySearch();
        int result = b.search(a, 4);
        System.out.println("result:"+result);
        result = b.search(a, 5);
        System.out.println("result:"+result);
    }
}