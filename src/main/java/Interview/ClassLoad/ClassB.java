package main.java.Interview.ClassLoad;

/**
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class ClassB extends ClassA {
    public ClassB() {
        System.out.println("ClassB");
    }
    {
        System.out.println("I'm B class");
    }
    static {
        System.out.println("static B");
    }

    /**
     * 结果：
     * static A
     * static B
     * -----------main start------------
     * I'm A class
     * ClassA
     * I'm B class
     * ClassB
     * -----------main end--------------
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("-----------main start------------");
        new ClassB();
        System.out.println("-----------main end--------------");
    }

}