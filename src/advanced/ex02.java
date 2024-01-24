package advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex02 {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/student";
        String user ="postgres";
        String password ="postgres";
    
        String sql = """
                INSERT INTO colors(id, name)
                VALUES(1,'blue'),
                       (2,'red'),
                       (3,'green'),
                       (4,'yellow'),
                       (5,'purple'),
                       (6,'orange');
                """;
    
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
    
            con.setAutoCommit(false); // 自動コミットを無効にする
    
            int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータを報告しました。");
    
            con.commit(); // トランザクションをコミットする
    
        } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        }
}