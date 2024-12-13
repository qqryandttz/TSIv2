import java.sql.*;

/**
 * 数据库连接
 */
class MyDB {

    final static String DBName = "TSIv2";
    final static String user = "root";
    final static String password = "61";
    static Connection con;

    PreparedStatement pstmt = null;

    public static void main(String[] args) {
        // 测试load方法
        MyDB myDB = new MyDB();
        Boolean result = myDB.load("qqry", "qqry");
        System.out.println("登录验证结果: " + result);
    }

    MyDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载JDBC-MySQL驱动");
        } catch (Exception e) {
            System.out.println("无法加载JDBC-MySQL驱动");
        }

        con = null;
        String url = "jdbc:mysql://localhost:3306/" + DBName
        // String url = "jdbc:mysql://47.98.243.58:3306/" + DBName
                + "?useSSL=false&characterEncoding=utf-8&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        try {
            con = DriverManager.getConnection(url, user, password);
            if (con != null && !con.isClosed())
                System.out.println("成功连接数据库");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("发生错误！");
        }
    }

    public Connection getCon() {
        return con;
    }

    void creatTable(Connection con, String tableName) {

        // PreparedStatement pstmt = con.prepareStatement();

        try {
            java.sql.Statement statement = con.createStatement();
            String sql = "CREATE TABLE qqry (id INT PRIMARY KEY, name VARCHAR(50))";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("发生错误！");
        }

    }

    void siwu(Connection con) {

        try {

            con.setAutoCommit(false);

            String sql = "INSERT INTO your_table (column1, column2) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "value1");
            pstmt.setInt(2, 123);
            pstmt.executeUpdate();

            sql = "UPDATE your_table SET column2 = ? WHERE column1 = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 456);
            pstmt.setString(2, "value1");
            pstmt.executeUpdate();

            con.commit();
            System.out.println("Transaction committed successfully.");

        } catch (SQLException e) {
            try {
                con.rollback();
                System.out.println("Transaction rolled back due to an error.");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 6. 关闭资源

        }

    }

    // 验证登录信息的方法
    public Boolean load(String login, String pwd) {
        Boolean isValid = false;

        try {
            // if (con != null && !con.isClosed()) {
            System.out.println("有进来吗？");
            String query = "SELECT * FROM 用户表 WHERE 用户名 = ? AND 密码 = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2, pwd);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }

            rs.close();
            pstmt.close();
            // }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //stopDB();
        }

        return isValid;
    }

    void stopDB() {

        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException closeEx) {
            closeEx.printStackTrace();
        }

    }

}
