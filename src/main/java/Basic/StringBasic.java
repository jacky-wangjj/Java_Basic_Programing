package main.java.Basic;

public class StringBasic {
    public String[] StringSplit(String str, String sign, int limit) {
        return str.split(sign, limit);
    }
    public static void main(String[] args) {
        StringBasic sb = new StringBasic();
        String[] StrArray = sb.StringSplit("id, name, tel, adress", ",", 3);
        for (String str : StrArray) {
            System.out.println(str.trim());
            System.out.println(str.equals("id"));
        }

    }
}