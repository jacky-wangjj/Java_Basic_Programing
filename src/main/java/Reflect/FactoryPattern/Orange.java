package main.java.Reflect.FactoryPattern;

/**
 * Created by Administrator on 2018/7/23.
 */
public class Orange implements Fruit {
    public void eat() {
        System.out.println(this.getClass().getName());
        System.out.println("Orange");
    }
}
