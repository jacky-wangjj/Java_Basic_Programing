package main.java.DB;

import main.java.DB.SiteConfig;
import java.sql.*;

/**
 * Created by Administrator on 2018/7/20.
 */
public class PostgreSQLJDBC {

    public static void main(String[] args) {
        String driver = SiteConfig.get("postgresql.jdbc.driver");
        String host = SiteConfig.get("postgresql.jdbc.host");
        String port = SiteConfig.get("postgresql.jdbc.port");
        String database = SiteConfig.get("postgresql.jdbc.database");
        String URL = "jdbc:postgresql://"+host+":"+port+"/"+database;
        String user = SiteConfig.get("postgresql.jdbc.username");
        String pwd = SiteConfig.get("postgresql.jdbc.password");

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(URL, user, pwd);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            String sql = "select * from clusterservices;";
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
