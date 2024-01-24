package advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex05 {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/student";
        String user ="postgres";
        String password ="postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            sql = """
                    DELETE FROM members
                     WHERE name = '櫻井翔';
                    DELETE FROM members 
                     WHERE name = '岡林朱莉';
                    """;
            pstmt = con.prepareStatement(sql);
            int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータを操作しました。");
        } catch (SQLException ex) {
            System.out.println("SQL = " + sql);
            ex.printStackTrace();
        } finally {
            try{
                if(con != null) {
                    con.close();
                }
                if(pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }            
    }
}
