package advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex01 {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/student";
        String user ="postgres";
        String passward ="postgress";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try{
            con = DriverManager.getConnection(url, user, passward);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);
            sql = """
                    DROP TABLE IF EXISTS colors;
                    CREATE TABLE colors(
                        id integer primary key,
                        name text
                    );
                    DROP TABLE IF EXISTS members;
                    CREATE TABLE members(
                        id serial primary key,
                        name text not null,
                        birth_day date,
                        gender varchar(1),
                        color_id integer 
                        REFERENCES colors (id))
                    """;
            int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータを報告しました。");
            con.commit();
        } catch(SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException e) {
                System.err.println("SQLの例外が発生しました");
                ex.printStackTrace();
            }
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
