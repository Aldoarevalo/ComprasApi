package Conexion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExtrasDB {

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:~/test";
    final String USER = "sa";
    final String PASS = "";
    Connection conn = null;
    Statement statement = null;
    String myStatement;
    ResultSet rs;
    DatabaseMetaData meta;
        private String msj;

    public void initializeDB() {

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database successfully...");
            
            statement = conn.createStatement();
            meta = conn.getMetaData();
            rs = meta.getTables(null, null, "EXTRAS", new String[]{"TABLE"});  // <--- Checks for existence of table "EXTRAS"

             if (rs == null) {
                msj = " CONEXION NO ESTABLECIDA - Parametros de conexión no válidos ";
            } else {
                msj = " CONEXION ESTABLECIDA ";    
            }
            if (!rs.next()) {

                System.out.println("Table doesn't exist yet... Creating...");
                String sql = "CREATE TABLE EXTRAS " +
                        "(column_1 VARCHAR(255), " +
                        " column_2 VARCHAR(255))";

                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void getDBValues() throws SQLException {
        rs = statement.executeQuery("SELECT * FROM EXTRAS");
        while (rs.next()) {
            //..... do something
        }
    }

    public void performQuery(String query) {
        try {
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}