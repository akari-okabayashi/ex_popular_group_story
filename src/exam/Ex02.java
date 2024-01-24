package exam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex02 {
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
                INSERT INTO members(name, birth_day, gender, color_id)
                VALUES('大野智','1980-11-26','男',1);
                INSERT INTO members(name, birth_day, gender, color_id)
                VALUES('櫻井翔','1982-01-25','男',2);
                INSERT INTO members(name, birth_day, gender, color_id)
                VALUES('相葉雅紀','1982-12-24','男',3);
                INSERT INTO members(name, birth_day, gender, color_id)
                VALUES('二宮和也','1983-06-17','男',4);
                INSERT INTO members(name, birth_day, gender, color_id)
                VALUES('松本潤','1983-08-30','男',5);
                """;
            pstmt = con.prepareStatement(sql);
            int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータをアップしました。");
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
