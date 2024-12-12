import java.sql.*;

class MyDB {

    MyDB() {

    }

}

// 数据库连接相关代码

public class DBConnection {
    public static Connection connectDB(String DBName, String user, String password) {
        // try {
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // System.out.println("成功加载JDBC-MySQL驱动");
        // } catch (Exception e) {
        // System.out.println("无法加载JDBC-MySQL驱动");
        // }

        Connection con = null;
        // String url = "jdbc:mysql://localhost:3306/" + DBName
        String url = "jdbc:mysql://47.98.243.58:3306/" + DBName
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
        dbc.connectDB("TSI2", "qqry", "61");
    }
}

class TransactionExample {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1. 获取数据库连接
            connection = DriverManager.getConnection("jdbc:your_database_url", "username", "password");

            // 2. 禁用自动提交
            connection.setAutoCommit(false);

            // 3. 创建 PreparedStatement
            String sql = "INSERT INTO your_table (column1, column2) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数并执行
            preparedStatement.setString(1, "value1");
            preparedStatement.setInt(2, 123);
            preparedStatement.executeUpdate();

            // 假设有另一个操作
            sql = "UPDATE your_table SET column2 = ? WHERE column1 = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 456);
            preparedStatement.setString(2, "value1");
            preparedStatement.executeUpdate();

            // 4. 提交事务
            connection.commit();
            System.out.println("Transaction committed successfully.");

        } catch (SQLException e) {
            // 5. 捕获异常并回滚事务
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Transaction rolled back due to an error.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // 6. 关闭资源
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}