
package modelos;
public class venta {
    
    int id;
    String descripcion;
    String observacion;
    String createAt;
    int idCliente;

    public venta() {
    }

    public venta(int id, String descripcion, String observacion, String createAt, int idCliente) {
        this.id = id;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.createAt = createAt;
        this.idCliente = idCliente;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }           
    
}
