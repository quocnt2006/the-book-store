/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quocnt.tbl_user.Tbl_UserDAO;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {
    
    private final String INIT_SEARCH_PAGE_CONTROLLER = "InitSearchPageController";
    private final String LOGIN_ERROR_PAGE = "login.jsp";

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
        PrintWriter out = response.getWriter();
        
        String url = LOGIN_ERROR_PAGE;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        
        try {
            Tbl_UserDAO dao = new Tbl_UserDAO();
            boolean result = dao.checkLogin(username, password);
            
            if (result) {
                url = INIT_SEARCH_PAGE_CONTROLLER;
                String user = dao.getUsername(username);
                boolean isAdmin = dao.isAdmin(username);
                
                HttpSession session = request.getSession();
                session.setAttribute("USERID", username);
                session.setAttribute("USER", user);
                session.setAttribute("ADMIN", isAdmin);
            } else {
                String errorMess = "Usernane or password is wrong!";
                request.setAttribute("ERRORLOGIN", errorMess);
                url = LOGIN_ERROR_PAGE;
            }
        } catch (NamingException ex) {
            log("LoginController_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginController_SQLException " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
