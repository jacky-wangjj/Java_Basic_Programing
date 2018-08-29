package main.java.IO;

import java.io.*;

/**
 * Created by Administrator on 2018/8/29.
 */
public class IoInterface {
    private String convert() {
        StringBuffer str = new StringBuffer();//字节
        char[] buf = new char[1024];
        FileReader f = null;
        try {
            f = new FileReader("README.md");//字符
            while (f.read(buf)>0) {
                str.append(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }
    private void fileOperation() throws IOException {
        String file = "test.txt";
        String charset = "UTF-8";
        //写字符转换成字节流
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
        try {
            writer.write("this is the test content");
        } finally {
            writer.close();
        }
        //读取字节转换成字符
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[64];
        int count = 0;
        try {
            while ((count = reader.read(buf)) != -1) {
                System.out.println("count:"+count+" buf:"+buf.toString());
                buffer.append(buf, 0, count);
            }
        } finally {
            reader.close();
        }
        System.out.println("butter:"+buffer.toString());
    }
    public static void main(String[] args) {
        IoInterface io = new IoInterface();
        String str = io.convert();
        System.out.println(str);
        try {
            io.fileOperation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
