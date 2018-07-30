package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchExample {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/emp?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            PreparedStatement preparedStatement = conn.prepareStatement("insert into Employees values(?,?,?,?)");
            for (int i=0; i<100;i++){
                preparedStatement.setInt(1, 105 + i);
                preparedStatement.setInt(2,28 + i);
                preparedStatement.setString(3,"First" + i);
                preparedStatement.setString(4, "Second" + i);

                preparedStatement.addBatch();
            }


            preparedStatement.executeBatch();


            //STEP 6: Clean-up environment


            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("There are so thing wrong!");
    }
}
