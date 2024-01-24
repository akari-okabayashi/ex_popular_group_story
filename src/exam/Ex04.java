package exam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex04 {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            sql = """
                    DELETE FROM members WHERE name = '大野智'
                    INSERT INTO members(name, birth_day, gender, color_id)
                    VALUES('岡林朱莉','1997-02-08','女',1);
                    """;
            pstmt = con.prepareStatement(sql);
            int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータを操作しました。");
        }catch(SQLException ex) {
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
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
