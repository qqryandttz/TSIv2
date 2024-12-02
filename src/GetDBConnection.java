import java.sql.*;

public class GetDBConnection {

    public static Connection connectDB(String DBName, String id, String p) {
        Connection con = null;
        String uri = "jdbc:mysql://localhost:3306/" + DBName
                + "?useSSL=false&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// 加载JDBC-MySQL驱动
        } catch (Exception e) {
            System.out.println("加载失败！");
        }
        try {
            con = DriverManager.getConnection(uri, id, p); // 连接代码
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("链接失败！");
        }
        return con;
    }
}