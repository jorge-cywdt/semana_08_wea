/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.daoUsuario;
import modelos.usuario;
import metodos_Encriptar.metodoSHA512;
import static dao.daoUsuario.SQLException;

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Baez
 */
public class controladorAcceso extends HttpServlet {

    daoUsuario objDaoUsu = new daoUsuario();
    
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
//            out.println("<title>Servlet controladorAcceso</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet controladorAcceso at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        String action = request.getParameter("signIn");
        
        if (action == null) {
            findByUsername(request, response);
            return;
        }
        
        switch(action) {
            case "acceso":
                findByUsername(request, response);
            break;
            
            default:
                findByUsername(request, response);
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

    private void findByUsername(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = metodoSHA512.getMetodoSHA512(request.getParameter("password"));       
            String passwordLength = request.getParameter("password");
            int posta = 0;
                        
            for (usuario usu:objDaoUsu.findByUsername(username, password)) {                        
                if (usu.getUsername().equals(username) && usu.getPassword().equals(password)) {
                    posta = 1;
                } else {
                    posta = 2;
                }
            }

            if (passwordLength.length() < 5) {
                request.getSession().setAttribute("mensajeError", "La contraseña debe tener al menos 5 carácteres");
                response.sendRedirect("acceso");   
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("password", null);
            } else {
                if (posta == 1) {
                    request.getSession().setAttribute("mensajeExito", "Ha iniciado sesión con éxito");
                    response.sendRedirect("venta");
                    request.getSession().setAttribute("username", username);
                    request.getSession().setAttribute("password", password);
                } else {
                    request.getSession().setAttribute("mensajeError", "Lo sentimos, no hemos podido validar sus credenciales");
                    request.getSession().setAttribute("SQLException", SQLException);
                    response.sendRedirect("acceso");
                    request.getSession().setAttribute("username", null);
                    request.getSession().setAttribute("password", null);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(controladorAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
