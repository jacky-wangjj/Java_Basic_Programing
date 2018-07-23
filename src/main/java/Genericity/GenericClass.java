package main.java.Genericity;

/**
 * 泛型类的声明与非泛型类的声明，除了在类名后面添加类型参数声明部分。
 * 和泛型方法一样，泛型类的类型参数声明部分也包含一个或多个类型参数，
 * 参数使用逗号隔开。一个泛型参数，也被称为一个类型变量，
 * 是用于指定一个泛型类型名称的标识符。
 * Created by Administrator on 2018/7/23.
 */
public class GenericClass<T> {
    private T t;
    public void add(T t) {
        this.t = t;
    }
    public T get() {
        return t;
    }
    public static void main(String[] args) {
        GenericClass<Integer> integerGC = new GenericClass<Integer>();
        GenericClass<String> stringGC = new GenericClass<String>();

        integerGC.add(new Integer(10));
        stringGC.add(new String("hello world"));
        System.out.printf("Integer value: %d\n", integerGC.get());
        System.out.printf("String value: %s\n", stringGC.get());
    }
}
