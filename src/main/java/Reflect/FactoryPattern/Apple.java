package main.java.Reflect.FactoryPattern;

import main.java.Reflect.FactoryPattern.Fruit;

/**
 * Created by Administrator on 2018/7/23.
 */
public class Apple implements Fruit {
    public void eat() {
        System.out.println(this.getClass().getName());
        System.out.println("Apple");
    }
}
