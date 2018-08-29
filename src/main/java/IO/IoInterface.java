package main.java.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Administrator on 2018/8/29.
 */
public class IoInterface {
    private void convert() {
        StringBuffer str = new StringBuffer();
        char[] buf = new char[1024];
        FileReader f = null;
        try {
            f = new FileReader("README.md");
            while (f.read(buf)>0) {
                str.append(buf);
            }
            str.toString();
            System.out.printf(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IoInterface io = new IoInterface();
        io.convert();
    }
}
