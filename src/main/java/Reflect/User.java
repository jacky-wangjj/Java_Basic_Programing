package main.java.Reflect;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/23.
 */
public class User implements Serializable {
    private int age;
    private String name;
    public User() {
        super();
    }
    public User(String name) {
        super();
        this.name = name;
    }
    public User(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return "User [age="+age+",name="+name+"]";
    }
    public static void main(String[] args) {

    }
}
