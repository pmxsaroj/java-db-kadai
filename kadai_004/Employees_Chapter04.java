package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {
    public static void main(String [] args) {

        Connection con = null;
        Statement statement = null;

        try {
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/challenge_java",
                    "root",
                    "root"
            );
            System.out.println("データベース接続成功 :" + con);

            statement = con.createStatement();
            String dropTable = "DROP TABLE IF EXISTS employees";
            statement.executeUpdate(dropTable);

            String sql = """
                    CREATE TABLE employees (
                        id SERIAL NOT NULL PRIMARY KEY,
                        name VARCHAR(60) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        age INT,
                        address VARCHAR(255)
                    );
                    """;
            int count = statement.executeUpdate(sql);
            System.out.println("社員テーブルを作成しました:更新レコード数=" + count);
        } catch (SQLException e) {
            System.out.println("error:" + e.getMessage());
        } finally {
            if (statement != null){
                try { statement.close();} catch (SQLException ignore){}
            }

            if (con != null) {
                try {con.close();} catch (SQLException ignore){}
            }
        }
    }
}
