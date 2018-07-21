package main.java.SearchAlgorithm;

/**
 * 顺序查找
 */
public class SequentialSearch {
    /**
     * 遍历数组元素查找val的位置
     * @param a
     * @param val
     * @return
     */
    public int search(int[] a, int val) {
        for(int i=0; i<a.length; i++) {
            if(val == a[i])
                return i;
        }
        return -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {1,3,5,6,9,10};
        SequentialSearch b = new SequentialSearch();
        int result = b.search(a, 4);
        System.out.println("result:"+result);
        result = b.search(a, 5);
        System.out.println("result:"+result);
    }
}