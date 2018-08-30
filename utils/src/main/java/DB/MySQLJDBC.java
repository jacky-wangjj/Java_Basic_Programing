package DB;

import SiteConfig.SiteConfig;

import java.sql.*;

/**
 * Created by Administrator on 2018/7/20.
 */
public class MySQLJDBC {

    public static void main(String[] args) {
        String driver = SiteConfig.get("mysql.jdbc.driver");
        String host = SiteConfig.get("mysql.jdbc.host");
        String port = SiteConfig.get("mysql.jdbc.port");
        String URL = "jdbc:mysql://"+host+":"+port+"/datahub";
        String user = SiteConfig.get("mysql.jdbc.username");
        String pwd = SiteConfig.get("mysql.jdbc.password");

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(URL, user, pwd);
            Statement stmt = conn.createStatement();

            String sql = "show databases;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("catch exception:"+e);
        } finally {
            //关闭连接
        }
    }
}
