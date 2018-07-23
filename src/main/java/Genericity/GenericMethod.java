package main.java.Genericity;

/**
 * Created by Administrator on 2018/7/23.
 */
public class GenericMethod {
    public <E> void printArray(E[] array) {
        for (E ele: array) {
            System.out.printf("%s ", ele);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 1.2, 1.3, 1.4, 1.5};
        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
        GenericMethod gm = new GenericMethod();
        gm.printArray(intArray);
        gm.printArray(doubleArray);
        gm.printArray(charArray);
    }
}
