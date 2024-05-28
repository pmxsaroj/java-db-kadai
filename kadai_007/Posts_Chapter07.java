package kadai_007;

import java.sql.*;

public class Posts_Chapter07 {
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

            System.out.println("データベース接続成功"+ con);
            System.out.println("レコード追加を実行します");

//            createStatement メソッド
            statement = con.createStatement();

            System.out.println("ユーザーIDが1002のレコードを検索しました");

//            executeUpdate メソッド
            String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES "
                    + "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13), "
                    + "(1002, '2023-02-08', 'お疲れ様です！', 12), "
                    + "(1003, '2023-02-09', '今日も頑張ります！', 18), "
                    + "(1001, '2023-02-09', '無理は禁物ですよ！', 17), "
                    + "(1002, '2023-02-10', '明日から連休ですね！', 20)";

            int count = statement.executeUpdate(sql);
            System.out.println(count + "件のレコードが追加されました");
//            executeQuery　メソッド
            sql = "SELECT * FROM Posts WHERE user_id = 1002;";
            ResultSet result = statement.executeQuery(sql);

//              ResultSetクラス
            while (result.next()) {
                int userId = result.getInt("user_id");
                Date postedAt = result.getDate("posted_at");
                String postContent = result.getString("post_content");
                int likes = result.getInt("likes");
                System.out.println(result.getRow() + "件目　：=" + "役橋日時" + postedAt + "/" + postContent + "いいね数=" + likes);
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

