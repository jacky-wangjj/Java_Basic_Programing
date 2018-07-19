package main.java.Interview;

/**
 * Created by Administrator on 2018/7/19.
 */
public class ExceptionExec {
    public void test1() {
        try {
            //正常执行的代码。
            System.out.println("test1");
            if(true)
                throw new Exception("test Exception");
            System.out.println("2");
        } catch (Exception e) {
            //出错后执行的代码。
            System.out.println("3");
        } finally {
            //无论正常执行还是出错，都会执行的代码。
            System.out.println("4");
        }
    }

    public void test2() {
        try {
            //正常执行的代码。
            System.out.println("test2");
            //throw new Exception("test Exception");
            System.out.println("2");
        } catch (Exception e) {
            //出错后执行的代码。
            System.out.println("3");
        } finally {
            //无论正常执行还是出错，都会执行的代码。
            System.out.println("4");
        }
    }

    public static void main(String[] args) {
        ExceptionExec ee = new ExceptionExec();
        ee.test1();
        ee.test2();
    }
}
