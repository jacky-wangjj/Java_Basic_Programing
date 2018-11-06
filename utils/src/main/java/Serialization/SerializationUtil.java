package Serialization;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by wangjj on 2018/11/6.
 */
public class SerializationUtil {
    public static final Logger log = Logger.getLogger(SerializationUtil.class);

    public byte[] serialize(Serializable object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            log.error(e, e);
        }
        return null;
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return clazz.cast(ois.readObject());
        } catch (Exception e) {
            log.error(e, e);
        }
        return null;
    }

    public static void main(String[] args) {
        User user = new User("root", "123456");
        SerializationUtil serializationUtil = new SerializationUtil();
        byte[] bytes = serializationUtil.serialize(user);
        log.info("bytes:"+bytes.toString());
        log.info("bytes:"+new String(bytes));
        User deserialize_user = serializationUtil.deserialize(bytes, User.class);
        log.info("name:"+deserialize_user.getName());
        log.info("password:"+deserialize_user.getPassword());
    }
}
