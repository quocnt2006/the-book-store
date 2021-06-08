/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quocnt.cart.Cart;
import quocnt.tbl_orderdetail.Tbl_OrderDetailDTO;

/**
 *
 * @author ASUS
 */
public class RemoveItemController extends HttpServlet {
    
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
                    
                    int bookId = Integer.parseInt(request.getParameter("txtBookId"));
        
                    BigDecimal totalValue = BigDecimal.ZERO;
                    
                    Cart cart = (Cart) session.getAttribute("CART");
                    boolean isEmpty = false;

                    if (cart != null) {
                        cart.removeItem(bookId);

                        if (cart.getItems() == null) {
                            isEmpty = true;
                        }

                        if (!isEmpty) {
                            //set total
                            for (Map.Entry<Integer, Tbl_OrderDetailDTO> item : cart.getItems().entrySet()) {
                                totalValue = totalValue.add(item.getValue().getTotal()).stripTrailingZeros();
                            }
                        }
                    }

                    if (isEmpty) {
                        cart = null;
                        totalValue = null;
                    }

                    session.setAttribute("CART", cart);
                    session.removeAttribute("DISCOUNTCODE");
                    session.removeAttribute("DISCOUNT");
                    session.setAttribute("TOTAL", totalValue);
                }
            }
        } finally {
            response.sendRedirect(url);
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
