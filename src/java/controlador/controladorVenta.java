/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.daoVenta;
import modelos.venta;
import modelos.detalleVenta;
import static dao.daoVenta.SQLException;
import static dao.daoVenta.idVenta;

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Baez
 */
public class controladorVenta extends HttpServlet {

    daoVenta objDaoVen = new daoVenta(); 
    private static final Logger logger = Logger.getLogger(controladorVenta.class.getName());
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet controladorVenta</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet controladorVenta at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        String action = request.getParameter("action");
        
        if (action == null) {
            save(request, response);
            return;
        }
        
        switch(action) {
            case "saveVenta":
                save(request, response);
            break;
            
            default:
                save(request, response);
            break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void save(HttpServletRequest request, HttpServletResponse response) {
        try {            
            int id = Integer.parseInt(request.getParameter("cliente"));
            String[] item_id = request.getParameterValues("item_id[]");
            String[] cantidad = request.getParameterValues("cantidad[]");            
                                             
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
            
            if (item_id == null || item_id.length == 0) {               
                request.getSession().setAttribute("mensajeError", "Error: La factura debe tener productos para ser creada");
                response.sendRedirect("venta");
            } else {                                                           
                venta objVen = new venta();
                objVen.setDescripcion("Descripcion_" + formateador2.format(new Date()));                
                objVen.setCreateAt(formateador.format(new Date()));
                objVen.setIdCliente(id);
                
                if (objDaoVen.save(objVen) == true) {                  
                    request.getSession().setAttribute("mensajeExito", "Venta creada con éxito");                                
                    response.sendRedirect("venta");
                } else {
                    request.getSession().setAttribute("mensajeError", "No se pudo crear venta");
                    request.getSession().setAttribute("SQLException", SQLException);
                    response.sendRedirect("venta");
                }                
                
                for (int i=0; i<item_id.length; i++) {                                        
                    detalleVenta objDetVen = new detalleVenta();
                    
                    objDetVen.setCantidad(Integer.parseInt(cantidad[i]));
                    objDetVen.setIdVenta(idVenta);
                    objDetVen.setIdProducto(Integer.parseInt(item_id[i]));
                    
                    objDaoVen.saveDetalleVenta(objDetVen);
                    
                    logger.info("ID: " + item_id[i] + ", Cantidad: " + cantidad[i]);
                }
            }            
        } catch (IOException ex) {
            Logger.getLogger(controladorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
