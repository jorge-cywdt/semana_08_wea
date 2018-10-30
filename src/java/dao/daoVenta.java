
package dao;
import conexion.clsConexion;
import modelos.venta;
import modelos.detalleVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class daoVenta {
    
    clsConexion objCon = new clsConexion();                  
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public static String SQLException;
    public static int idVenta;
    
    public boolean save(venta obj) {                
        String sql = "insert into ventas (descripcion, observacion, create_at, id_cliente)"
                   + "values('" + obj.getDescripcion() + "'," + null + ",'" + obj.getCreateAt() + "'," + obj.getIdCliente() + ")";
        try {            
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();            
            
            cn.close();
            ps.close();
            cn = null;
            ps = null;
            findByLastId();
            return true;            
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            System.out.println("Error en Insertar");            
            SQLException = ex.getMessage();
        }
        return false;
    }
    
    public boolean saveDetalleVenta(detalleVenta obj) {
        String sql = "insert into detalle_ventas (cantidad, id_venta, id_producto)"
                   + "values(" + obj.getCantidad() + "," + obj.getIdVenta() + "," + obj.getIdProducto() + ")";
        try {
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();            
            
            cn.close();
            ps.close();
            cn = null;
            ps = null;           
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            System.out.println("Error en Insertar");            
            SQLException = ex.getMessage();
        }
        return false;
    }
    
    public void findByLastId() {
        String sql = "select id from ventas order by id desc limit 1";                
        try {
            cn = objCon.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
                        
            while (rs.next()) {                
                idVenta = rs.getInt("id");
            }                                          
            cn.close();
            ps.close();
            rs.close();
            cn = null;
            ps = null;
            rs = null;
        } catch (SQLException ex) {                 
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            System.out.println("Error en Cargar");
            SQLException = ex.getMessage();
        }
    }
    
}
