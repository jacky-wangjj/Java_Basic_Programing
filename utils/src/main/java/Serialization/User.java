package Serialization;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/6.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -5449514119063231844L;

    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
