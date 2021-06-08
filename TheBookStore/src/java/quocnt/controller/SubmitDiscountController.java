/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quocnt.tbl_discount.Tbl_DiscountDAO;

/**
 *
 * @author ASUS
 */
public class SubmitDiscountController extends HttpServlet {

    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String INIT_SEARCH_PAGE_CONTROLLER = "InitSearchPageController";
    
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
        
        String url = INIT_SEARCH_PAGE_CONTROLLER;
        
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("USER") != null) {
                boolean isAdmin = (boolean) session.getAttribute("ADMIN");
                if (!isAdmin) {
                    url = VIEW_CART_PAGE;
        
                    // input discount code 
                    int discountCode;
                    try {
                        discountCode = Integer.parseInt(request.getParameter("txtDiscountCode"));
                    } catch(NumberFormatException ex) {
                        discountCode = 0;
                    }
            
                    Tbl_DiscountDAO dDao = new Tbl_DiscountDAO();
                    boolean result = dDao.checkDiscountCode(discountCode);

                    if (result) {
                        BigDecimal discountValue = dDao.getDiscountValue(discountCode);
                        BigDecimal total = (BigDecimal) session.getAttribute("TOTAL");
                        total = total.divide(BigDecimal.valueOf(100));
                        total = total.multiply(BigDecimal.valueOf(100).subtract(discountValue)).stripTrailingZeros();
                        String mess = "Discount code is accepted!";

                        session.setAttribute("TOTAL", total);
                        session.setAttribute("DISCOUNTCODE", discountCode);
                        session.setAttribute("DISCOUNT", discountValue);
                        request.setAttribute("TRUECODE", mess);
                    } else {
                        String mess = "Discount code is unaccepted!";
                        request.setAttribute("WRONGCODE", mess);
                    }
                }
            }
        } catch (NamingException ex) {
            log("SubmitDiscountController_NamingException " + ex.getMessage());
        } catch (SQLException ex ) {
            log("SubmitDiscountController_SQLException " +ex.getMessage());
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
