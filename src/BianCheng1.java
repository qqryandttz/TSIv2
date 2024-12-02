import java.sql.*;

public class BianCheng1 {

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