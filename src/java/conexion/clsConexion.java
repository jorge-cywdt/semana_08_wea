
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class clsConexion {
    
    private Connection connection;
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Driver de MySQL
            String myDB = "jdbc:mysql://localhost:3306/wea?useTimezone=true&serverTimezone=UTC"; //Url de MySQL
            String user = "root";
            String password = "";
            
            connection = DriverManager.getConnection(myDB,user,password);
            
            return connection;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error estableciendo conexión con la base de datos");            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(clsConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
