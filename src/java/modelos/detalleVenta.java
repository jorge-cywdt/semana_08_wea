
package modelos;
public class detalleVenta {

    int id;
    int cantidad;
    int idVenta;
    int idProducto;

    public detalleVenta() {
    }
    
    public detalleVenta(int id, int cantidad, int idVenta, int idProducto) {
        this.id = id;
        this.cantidad = cantidad;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }    
    
}
