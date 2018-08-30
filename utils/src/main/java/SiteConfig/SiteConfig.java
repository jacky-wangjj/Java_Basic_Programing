package SiteConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2018/7/20.
 */
public class SiteConfig {
    protected static Properties prop = null;

    static {
        prop = loadProperties("../site.properties");
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    private static Properties loadProperties(String proPath) {
        InputStream in = null;
        Properties props = new Properties();
        try {
            in = SiteConfig.class.getResourceAsStream(proPath);
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            System.out.println("read properties file failed");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }

    public static void main(String[] args) {
        String host = SiteConfig.get("postgresql.jdbc.password");
        System.out.println(host);
    }
}
