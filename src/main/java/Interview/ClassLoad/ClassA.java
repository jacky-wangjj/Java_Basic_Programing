package main.java.Interview.ClassLoad;

/**
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class ClassA {
    public ClassA() {
        System.out.println("ClassA");
    }
    {
        System.out.println("I'm A class");
    }
    static {
        System.out.println("static A");
    }
}