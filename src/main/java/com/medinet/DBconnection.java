import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    static Connection con;

    public static Connection getConnection() {

        try {

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database URL
            String url = "jdbc:mysql://localhost:3306/medinet_hms";

            // Username & Password
            String user = "root";
            String password = "Priya@123";

            // Create Connection
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully");

        } 
        catch(ClassNotFoundException | SQLException e) {
    e.printStackTrace();
}
        return con;
    }
}