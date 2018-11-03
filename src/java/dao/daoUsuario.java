
package dao;
import conexion.clsConexion;
import modelos.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class daoUsuario {
    
    clsConexion objCon = new clsConexion();                  
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public static String SQLException;
    
    public List<usuario> findByUsername(String username, String password) {        
        List<usuario> usu = new ArrayList<>();
        String sql = "select id,username,password from usuarios where username = '" + username + "' and password = '" + password + "' order by id";
        try {                       
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {   
                usuario objUsu = new usuario();
                objUsu.setId(rs.getInt("id"));                
                objUsu.setUsername(rs.getString("username"));
                objUsu.setPassword(rs.getString("password"));
                usu.add(objUsu);
            }
            cn.close();
            ps.close();
            rs.close();              
            cn = null;
            ps = null;
            rs = null;
            return usu;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en findByUsername");
            SQLException = ex.getMessage();
        }
        return null;
    }
    
}
