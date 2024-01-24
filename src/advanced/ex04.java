package advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex04 {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;sql = """
            DELETE FROM members WHERE name = '大野智';
            INSERT INTO members(name, birth_day, gender, color_id)
            VALUES('岡林朱莉','1997-02-08','女',6);
            """;

        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = con.prepareStatement(sql);) {
            
                int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータを操作しました。");
        }catch(SQLException ex) {
            System.out.println("SQL = " + sql);
            ex.printStackTrace();
        }
    }
}
