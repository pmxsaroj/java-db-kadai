package kadai_10;

import java.sql.*;
import java.util.Scanner;

public class Scores_Chapter10 {
    public static void main(String[] args) {

        Connection con = null;
        Statement statement = null;

        try {

//            getConnection メソッド
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/challenge_java",
                    "postgres",
                    "Sb7276"
            );
            System.out.println("データベース接続成功：" + con);

//            createStatementメソッド
            statement = con.createStatement();


            System.out.println("レコード更新を実行します");
            String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";

//            executeUpdateメソッド
            statement.executeUpdate(sql);
            System.out.println("1件のレコードが更新されました");
//          　　
//              並び替え
            sql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
            System.out.println("数学・英語の点数が高い順に並べ替えました");

//            executeQueryメソッド
            ResultSet result = statement.executeQuery(sql);

//            whileクラス
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int mathScore = result.getInt("score_math");
                int englishScore = result.getInt("score_english");
                System.out.println(result.getRow() + "件目 :" + "ID = " + id +
                        "／氏名=" + name + "／数学=" + mathScore +
                        "／英語=" + englishScore);
            }
        } catch (SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
}
