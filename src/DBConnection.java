import java.sql.*;

//数据库连接相关代码

public class DBConnection {
    public static Connection connectDB(String DBName, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载JDBC-MySQL驱动");
        } catch (Exception e) {
            System.out.println("无法加载JDBC-MySQL驱动");
        }

        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/" + DBName
                + "?useSSL=false&characterEncoding=utf-8&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        try {
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("成功连接数据库");

            Statement statement = con.createStatement();
            String sql = "CREATE TABLE qqry (id INT PRIMARY KEY, name VARCHAR(50))";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("发生错误！");
        }
        return con;

    }

}

class zixin {
    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        dbc.connectDB("teaching", "root", "61");
    }
}

class DBD {

    public static void main(String args[]) {
        Connection con;
        Statement sql;
        ResultSet rs;
        con = GetDBConnection.connectDB("teaching", "root", "61");
        if (con == null)
            return;
        String sqlStr = "select * from mess order by birthday";
        try {
            sql = con.createStatement();
            rs = sql.executeQuery(sqlStr);
            while (rs.next()) {
                String number = rs.getString(1);
                String name = rs.getString(2);
                Date date = rs.getDate(3);
                float height = rs.getFloat(4);
                System.out.printf("%s\t", number);
                System.out.printf("%s\t", name);
                System.out.printf("%s\t", date);
                System.out.printf("%.2f\n", height);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

class GetDBConnection {

    public static Connection connectDB(String DBName, String id, String p) {
        Connection con = null;
        String uri = "jdbc:mysql://localhost:3306/" + DBName
                + "?useSSL=false&characterEncoding=utf-8&allowPublicKeyRetrieval=true&serverTimezone=UTC";
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
