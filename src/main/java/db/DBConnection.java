package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DBConnection {
	public static Connection con = null;
    private static DBConnection instance = new DBConnection();

    public static DBConnection getInstance() {
        return instance;
    }

    public void connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databasename=QLLuongSanPham;encrypt=false";
        String user = "sa";
        String password = "giahuy1202.";
        con = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() {
        if (con != null)
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
    	SQLServerDataSource ds = new SQLServerDataSource();
    	ds.setEncrypt(false);
        try {
            DBConnection.getInstance().connect();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return con;

    }
}
