package main.java.Reflect;

import main.java.Reflect.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/23.
 */
public class TestReflect {
    /**
     * 通过一个对象获取完整的包名和类名
     * @param object
     * @return
     */
    public String getClassName(Object object) {
        String className = object.getClass().getName();
        return className;
    }

    /**
     * 实例化Class类对象
     */
    public void newClassObject() {
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        try {
            class1 = Class.forName("main.java.Reflect.TestReflect");
            class2 = new TestReflect().getClass();
            class3 = TestReflect.class;
            System.out.printf("Class Name: %s%n", class1.getName());
            System.out.printf("Class Name: %s%n", class2.getName());
            System.out.printf("Class Name: %s%n", class3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个对象的父类与实现的接口
     */
    public void getParentClassAndIntes() {
        try {
            Class<?> clazz =Class.forName("main.java.Reflect.User");
            Class<?> parentClass = clazz.getSuperclass();//获取父类
            System.out.printf("clazz的父类为：%s%n", parentClass.getName());
            Class<?> intes[] = clazz.getInterfaces();//获取所有的接口
            System.out.println("clazz实现的接口有：");
            for (int i=0; i<intes.length; i++) {
                System.out.println((i+1) + ": "+intes[i].getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用构造函数实例化对象
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public void newUserObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = null;
        clazz = Class.forName("main.java.Reflect.User");
        User user = (User) clazz.newInstance();//第一种方法，实例化默认构造方法
        user.setAge(20);
        user.setName("Rollen");
        System.out.println(user.toString());
        Constructor<?> cons[] = clazz.getConstructors();//第二种方法，取得全部构造函数，使用构造函数赋值
        for (int i=0; i< cons.length; i++) {
            Class<?> clazzs[] = cons[i].getParameterTypes();//获取构造函数参数类型
            System.out.printf("cons[%d] (", i);
            for (int j=0; j<clazzs.length; j++) {
                System.out.printf(clazzs[j].getName()+"  ");
            }
            System.out.printf(")%n");
        }
        user = (User) cons[1].newInstance("Rollen");//使用第一个构造函数实例化对象user
        System.out.println(user.toString());
        user = (User) cons[0].newInstance(10, "Rellen");//使用第二个构造函数实例化对象user
        System.out.println(user.toString());
    }

    /**
     * 使用反射机制调用方法
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public void callMethod() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = Class.forName("main.java.Reflect.User");
        Object object = clazz.newInstance();
        Method method = clazz.getMethod("setName", String.class);//调用User类中的setName方法
        method.invoke(object, "Jacky");
        method = clazz.getMethod("setAge", int.class);//调用User类中的setAge方法
        method.invoke(object, 10);
        method = clazz.getMethod("toString");//调用User类中的toString方法
        System.out.println(method.invoke(object));
        System.out.println(object.toString());
    }
    public static void main(String[] args) {
        TestReflect t = new TestReflect();
        System.out.printf("Class Name: %s%n", t.getClassName(t));
        t.newClassObject();
        t.getParentClassAndIntes();
        try {
            t.newUserObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            t.callMethod();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
