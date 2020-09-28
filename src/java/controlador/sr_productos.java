
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;

/**
 *
 * @author oscar_lopez
 */
public class sr_productos extends HttpServlet {
Producto producto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            producto = new Producto(Integer.valueOf(request.getParameter("txt_id")),Integer.valueOf(request.getParameter("txt_existencia")),Float.valueOf(request.getParameter("txt_pcosto")),Float.valueOf(request.getParameter("txt_pventa")),request.getParameter("txt_producto"),request.getParameter("txt_descripcion"),Integer.valueOf(request.getParameter("drop_producto")));
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_productos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sr_productos at " + request.getContextPath() + "</h1>");
            if("agregar".equals(request.getParameter("btn_agregar"))){
                if(producto.agregar()>0){
                response.sendRedirect("index.jsp");
            }else{
                out.println("<h1>ERROR</h1>");
            }   
            }
            if("modificar".equals(request.getParameter("btn_modificar"))){
                  if(producto.actualizar()>0){
                response.sendRedirect("index.jsp");
            }else{
                out.println("<h1>ERROR</h1>");
            }  
            }
              if("eliminar".equals(request.getParameter("btn_eliminar"))){
                  if(producto.eliminar()>0){
                response.sendRedirect("index.jsp");
            }else{
                out.println("<h1>ERROR</h1>");
            }  
            }
            
            out.println("</body>");
            out.println("</html>");
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

}
