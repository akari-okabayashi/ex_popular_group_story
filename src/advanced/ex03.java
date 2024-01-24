package advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ex03 {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/student";
        String user ="postgres";
        String passward ="postgress";

        String sql = """
            SELECT m.name, m.birth_day, m.gender, c.name as color_name
            FROM members as m
            INNER JOIN colors as c
            ON m.color_id = c.id
            ORDER BY birth_day;
            """;

        try(Connection con = DriverManager.getConnection(url, user, passward);
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            while(rs.next()) {
                String name = rs.getString("name");
                String birth_day = rs.getString("birth_day");
                String gender = rs.getString("gender");
                String color_name = rs.getString("color_name");
                System.out.print("name = " + name);
                System.out.print(" birth_day = " + birth_day);
                System.out.print(" gender = " + gender);
                System.out.print(" color = " + color_name);
                System.out.println();
            }
    } catch(SQLException ex) {
        System.err.println("SQL関連の例外が発生しました。");
        System.err.println("SQL = " + sql);
        ex.printStackTrace();
    } 
}
}
