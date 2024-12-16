import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import javax.swing.JOptionPane;

/**
 * 数据库连接
 */
class MyDB {

    final static String DBName = "TSIv2";
    final static String user = "qqry";
    final static String password = "61";
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement pstmt = null;

    /**
     * 建立数据库的连接
     */
    MyDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载JDBC-MySQL驱动");
        } catch (Exception e) {
            System.out.println("无法加载JDBC-MySQL驱动");
        }

        con = null;
        // String url = "jdbc:mysql://localhost:3306/" + DBName
        String url = "jdbc:mysql://47.98.243.58:3306/" + DBName
                +
                "?useSSL=false&characterEncoding=utf-8&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";
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

    /**
     * 查找当前用户是否存在
     */
    Boolean findUser(String userName) {
        Boolean isExist = false;
        try {
            if (con != null && !con.isClosed()) {
                String query = "SELECT 1 FROM 用户表 WHERE 用户名 = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, userName);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    isExist = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "程序出错！未获取到数据库连接！", "警告",
                        JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("难不成是这里的报错？");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return isExist;
    }

    /**
     * 查询用户名密码是否正确
     */
    Boolean load(String username, String pwd) {
        Boolean isValid = false;
        String updateQuery = "UPDATE 用户表 SET 最后登入时间 = ? WHERE 用户名 = ?";

        try {
            if (con != null && !con.isClosed()) {
                String query = "SELECT * FROM 用户表 WHERE 用户名 = ? AND 密码 = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, pwd);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    isValid = true;
                    PreparedStatement pstmtUpdate = con.prepareStatement(updateQuery);
                    pstmtUpdate.setTimestamp(1, new Timestamp(System.currentTimeMillis()));

                    pstmtUpdate.setString(2, username);

                    int affectedRows = pstmtUpdate.executeUpdate();
                    if (affectedRows == 0) {
                        JOptionPane.showMessageDialog(null, "程序出错！无法更新最后登入时间！", "警告",
                                JOptionPane.ERROR_MESSAGE);

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "程序出错！未获取到数据库连接！", "警告",
                        JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isValid) {
            MyDbDate.setUserName(username);
            MyDbDate.setPwd(pwd);
        }
        return isValid;
    }

    /**
     * 存储mac地址表
     */
    public Boolean updateUserMacAddresses(String userName, List<String> macs) {
        Boolean isSuccess = false;
        String macAddressesString = convertMacListToString(macs);

        try {
            if (con != null && !con.isClosed()) {
                String updateQuery = "UPDATE 用户表 SET mac地址组 = ?, 是否自动登入 = TRUE WHERE 用户名 =?";
                pstmt = con.prepareStatement(updateQuery);
                pstmt.setString(1, macAddressesString);
                pstmt.setString(2, userName);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    isSuccess = true;
                } else {
                    JOptionPane.showMessageDialog(null, "程序出错！无法自动登录！", "警告",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "程序出错！未获取到数据库连接！", "警告",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * mac转换，仅在实现自动登入中被引用
     */
    private static String convertMacListToString(List<String> macs) {
        StringJoiner joiner = new StringJoiner(",");
        for (String mac : macs) {
            joiner.add(mac);
        }
        return joiner.toString();
    }

    /**
     * 用户注册
     */
    Boolean registerUser(String username, String pwd) {
        Boolean isRegister = false;
        String insertQuery = "INSERT INTO 用户表 (用户名, 密码, 角色, 注册时间, 最后登入时间,音乐开关,文本呈现速度, 是否自动登入, 成就数量) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
            pstmt.setString(1, username);
            pstmt.setString(2, pwd);
            pstmt.setString(3, "玩家");
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.setBoolean(6, true);
            pstmt.setInt(7, 1);
            pstmt.setBoolean(8, false);
            pstmt.setInt(9, 0);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("注册用户失败，未影响任何行");
            } else {
                isRegister = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return isRegister;
    }

    /**
     * 关闭数据库
     */
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

    /**
     * 将用户的自动登录置为false
     */
    public Boolean updateAutoLogin(String userName) {
        Boolean isSuccess = false;
        String updateQuery = "UPDATE 用户表 SET 是否自动登入 = ? WHERE 用户名 = ?";

        try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {

            pstmt.setBoolean(1, false);
            pstmt.setString(2, userName);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return isSuccess;
    }

    /**
     * 先查找是否自动登录，再查找mac地址是否匹配
     */
    public Boolean checkUserMacAddresses(String userName, List<String> macs) {
        Boolean isAuto = false;
        Boolean isMatch = false;
        String retrievedMacAddressesString = null;

        try {
            if (con != null && !con.isClosed()) {
                String selectQuery = "SELECT 是否自动登入 FROM 用户表 WHERE 用户名 = ?";
                try (PreparedStatement pstmt = con.prepareStatement(selectQuery)) {
                    pstmt.setString(1, userName);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            isAuto = rs.getBoolean("是否自动登入");
                            MyDbDate.setIsAutoLoad(isAuto);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "程序出错！未获取到数据库连接！", "警告",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (isAuto) {
                if (con != null && !con.isClosed()) {
                    String selectQuery = "SELECT mac地址组 FROM 用户表 WHERE 用户名 = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(selectQuery)) {
                        pstmt.setString(1, userName);
                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                retrievedMacAddressesString = rs.getString("mac地址组");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "程序出错！未获取到数据库连接！", "警告",
                            JOptionPane.ERROR_MESSAGE);
                }

                // 将从数据库检索到的MAC地址组字符串拆分为单个MAC地址，并与提供的列表进行比较
                if (retrievedMacAddressesString != null &&
                        !retrievedMacAddressesString.isEmpty()) {
                    String[] retrievedMacsArray = retrievedMacAddressesString.split(",");
                    Set<String> retrievedMacsSet = new HashSet<>(Arrays.asList(retrievedMacsArray));
                    Set<String> providedMacsSet = new HashSet<>(macs);

                    isMatch = !Collections.disjoint(retrievedMacsSet, providedMacsSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isMatch) {
            MyDbDate.setUserName(userName);
        }

        return isMatch;
    }

    // public static int hasOrNot(String listName, String[] value_names, String[]
    // values) {
    // String query = "SELECT * FROM " + listName + " WHERE ";

    // if (value_names.length != values.length) {
    // AException.Exception("无效参数！");
    // }

    // for (int i = 0; i < value_names.length; i++) {
    // query += value_names[i] + " = '" + values[i] + "'";

    // if (i != value_names.length - 1) {
    // query += " AND ";
    // }
    // }

    // System.out.println(query);

    // PreparedStatement preparedStatement;
    // try {
    // preparedStatement = con.prepareStatement(query);

    // ResultSet resultSet = preparedStatement.executeQuery(query);

    // if (resultSet.next()) {
    // return HAS;
    // }
    // } catch (SQLException e) {

    // }

    // return HAS_NO;
    // }
}
