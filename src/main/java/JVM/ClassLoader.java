package main.java.JVM;

/**
 * Created by Administrator on 2018/8/29.
 */
public class ClassLoader {
    /*
     * 获取当前classpath路径
     */
    public String getClassPath() {
        String classpath = this.getClass().getClassLoader().getResource("").toString();
        return classpath;
    }
    public static void main(String[] args) {
        ClassLoader cl = new ClassLoader();
        String classpath = cl.getClassPath();
        System.out.printf(classpath);
    }
}
