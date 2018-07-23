package main.java.Reflect.FactoryPattern;

/**
 * Created by Administrator on 2018/7/23.
 */
public class Factory {
    public static Fruit getInstance(String ClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Fruit f = null;
        f = (Fruit) Class.forName(ClassName).newInstance();
        return f;
    }
    public static void main(String[] args) {
        Fruit f = null;
        Factory factory = new Factory();
        try {
            f = factory.getInstance("main.java.Reflect.FactoryPattern.Orange");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (f != null)
            f.eat();
    }
}
