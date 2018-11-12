package JSON;

/**
 * Created by wangjj on 2018/11/12.
 */
public class Student {
    private String name;
    private int id;
    private String addr;
    private String[] hobby;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getAddr() {
        return addr;
    }
    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }
    public String[] getHobby() {
        return hobby;
    }

    public static void main(String[] args) {

    }
}
