/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.QuizDB;

/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: LoginController.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet
{

    /**
     * QuizDB object*
     */
    private QuizDB quiz;
    /**
     * ResultSet object*
     */
    private ResultSet results;
    /**
     * User object*
     */
    private User user;
    /**
     * Stores the value of button clicked*
     */
    private String button;
    /**
     * Stores the message to be sent*
     */
    private String message;
    /**
     * Stores the value of links *
     */
    private String param;

    @Override
    public void init () throws ServletException
    {
        try {
            quiz = new QuizDB();
            quiz.connect();
            quiz.open("SELECT * FROM User");
            results = quiz.getResultSet();
            user = new User();
        }
        catch (SQLException e) {

        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        param = request.getParameter("action");
        try {
            //Session
            HttpSession session = request.getSession(false);
            //Listener to buttons
            button = request.getParameter("button");

            //Login
            if ("login".equals(button)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                user.setUsername(username);
                user.setPassword(password);
                quiz.login(user);
                quiz.isAdmin(user);

                if (user.isValid()) {
                    quiz.isAdmin(user);
                    quiz.getUserID(user, username);
                    if (user.getIsAdmin() == false) {
                        session = request.getSession(true);
                        session.setAttribute("userName", user.getUsername());
                        session.setAttribute("userID", user.getId());
                        response.sendRedirect("index.jsp");
                    }
                    else if (user.getIsAdmin() == true) {
                        session = request.getSession(true);
                        session.setAttribute("userName", user.getUsername());
                        session.setAttribute("isAdmin", user.getIsAdmin());
                        response.sendRedirect("admin.jsp");
                    }
                    quiz.close();
                }
                else if (!user.isValid()) {
                    message = "Login Failed";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                //Redirects to register page
            }
            else if ("register".equals(button)) {
                request.setAttribute("userName", user.getUsername());
                request.getRequestDispatcher("register.jsp").forward(request, response);
                //Register
            }
            else if ("regSubmit".equals(button)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                user.setUsername(username);
                user.setPassword(password);
                quiz.registerUser(user);

                message = "Successfully Registered";
                request.setAttribute("message", message);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                //Logout
            }
            else if (param.equals("logout")) {
                session = request.getSession(false);
                session.removeAttribute("userName");
                session.removeAttribute("userID");
                session.removeAttribute("isAdmin");
                session.getMaxInactiveInterval();
                //quiz.close();
                message = "See you again!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("logout.jsp").forward(request, response);
            }
        }
        catch (Exception e) {

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
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
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
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo ()
    {
        return "Short description";
    }// </editor-fold>

}
