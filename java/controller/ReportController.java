package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: AdminController.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
@WebServlet(urlPatterns = {"/ReportController"})
public class ReportController extends HttpServlet {

    /**Stores the parameter**/
    private String param;
    /**Determines the category**/
    private int categoryID;

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
        param = request.getParameter("category");
        if (param.equals("jsp")) {
            categoryID = 1;
            response.sendRedirect("reports.jsp?categoryID=1");
        } else if (param.equals("java")) {
            categoryID = 4;
            response.sendRedirect("reports.jsp?categoryID=4");
        } else if (param.equals("html")) {
            categoryID = 5;
            response.sendRedirect("reports.jsp?categoryID=5");
        } else if (param.equals("linux")) {
            categoryID = 2;
            response.sendRedirect("reports.jsp?categoryID=2");
        } else if (param.equals("networking")) {
            categoryID = 3;
            response.sendRedirect("reports.jsp?categoryID=3");
        } else if (param.equals("sql")) {
            categoryID = 6;
            response.sendRedirect("reports.jsp?categoryID=6");
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
