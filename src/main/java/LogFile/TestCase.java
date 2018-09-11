package main.java.LogFile;

import java.io.FileWriter;
import java.io.IOException;

import main.java.Reflect.TestReflect;
import org.junit.Test;

/**
 * Created by Administrator on 2018/9/11.
 */
public class TestCase {
    /**
     * 同步向指定文件尾部写入字符串
     */
    public void testAppendMethodB(String fileName,String content) throws IOException{
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *调用上面同步写方法
     */
    @Test
    public void testWriteTOFile() throws IOException{
        String fileName = "d:\\logger.log";
        String content="tableaA|device_number|13701010";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<100000;i++){
            sb.append(content).append(i).append(";\n");
        }
        content=sb.toString();
        long startTime=System.currentTimeMillis();
        testAppendMethodB(fileName,content);
        long endTime=System.currentTimeMillis();
        System.out.println("总消耗时间："+(endTime-startTime));
    }
    /**
     * 异步调用写方法
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testAsyncWriteTOFile() throws IOException, InterruptedException{
        String content="tableaA|device_number|13701010";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<100000;i++){
            sb.append(content).append(i).append(";\n");
        }
        content=sb.toString();
        System.out.println("start write...");
        new AychWriter(content).start();
        System.out.println("write over...");
        Thread.sleep(30000); //重要，如果主线程挂了，调用线程也停止了
        System.out.println("main Thread over");
    }

    public static void main(String[] args) {
        TestCase tc = new TestCase();
        try {
            tc.testWriteTOFile();
            tc.testAsyncWriteTOFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

