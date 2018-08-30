package main.java.Encode;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Encode {
    final protected static char[] hexCode = "0123456789ABCDEF".toCharArray();
/*
    private void toHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexCode[v >>> 4];
            hexChars[j * 2 + 1] = hexCode[v & 0x0F];
        }
        String hex = new String(hexChars);
        System.out.println(hex);
    }
*/
    public void toHex(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        System.out.println("TO Hex: "+r.toString());
    }

    public void encoding() {
        String name = "I am 军师";
        //toHex(name.toCharArray());
        try {
            byte[] iso8859 = name.getBytes("ISO-8859-1");
            toHex(iso8859);
            byte[] gb2312 = name.getBytes("GB2312");
            toHex(gb2312);
            byte[] gbk = name.getBytes("GBK");
            toHex(gbk);
            byte[] utf16 = name.getBytes("UTF-16");
            toHex(utf16);
            byte[] utf8 = name.getBytes("UTF-8");
            toHex(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void coverter() throws UnsupportedEncodingException {
        String s = "这是一段中文字符串";
        byte[] b =s.getBytes("UTF-8");//String转字节数组
        toHex(b);
        System.out.println("Text: "+s);
        System.out.println("Text [Byte Format]: "+b);
        System.out.println("Text [Byte Format]: "+b.toString());
        String n = new String(b, "UTF-8");//字节数组转String
        System.out.println("Text Decrypted: "+n);
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(s);
        CharBuffer charBuffer = charset.decode(byteBuffer);
        System.out.println("ByteBuffer: "+byteBuffer.array());
        System.out.println("CharBuffer: "+charBuffer);
    }

    public static void main(String[] args) {
        Encode encode = new Encode();
        //encode.encoding();
        try {
            encode.coverter();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}