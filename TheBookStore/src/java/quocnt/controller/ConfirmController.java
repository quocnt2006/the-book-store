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
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quocnt.cart.Cart;
import quocnt.tbl_book.Tbl_BookDAO;
import quocnt.tbl_discount.Tbl_DiscountDAO;
import quocnt.tbl_order.Tbl_OrderDAO;
import quocnt.tbl_orderdetail.Tbl_OrderDetailDAO;
import quocnt.tbl_orderdetail.Tbl_OrderDetailDTO;

/**
 *
 * @author ASUS
 */
public class ConfirmController extends HttpServlet {
    
    private final String INIT_SEARCH_PAGE_CONTROLLER = "InitSearchPageController";
    private final String VIEW_CART_PAGE = "viewCart.jsp";

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
                    Cart cart = (Cart) session.getAttribute("CART");

                    if (cart != null) {
                        String userId = (String) session.getAttribute("USERID");
                        BigDecimal total = (BigDecimal) session.getAttribute("TOTAL");
                        Tbl_OrderDAO oDao = new Tbl_OrderDAO();

                        boolean result = oDao.insertOrder(userId, total);

                        if (result) {
                            int lastedOrderId = oDao.getLastestId();

                            // insert order detail
                            for (Map.Entry<Integer, Tbl_OrderDetailDTO> item : cart.getItems().entrySet()) {
                                Tbl_OrderDetailDAO odDao = new Tbl_OrderDetailDAO();

                                String title = item.getValue().getTitle();
                                int bookId = item.getKey();
                                int quantity = item.getValue().getQuantity();
                                BigDecimal unitPrice = item.getValue().getUnitPrice();
                                BigDecimal itemTotal = item.getValue().getTotal();

                                odDao.insertOrderDetail(bookId, title, quantity, unitPrice, itemTotal, lastedOrderId);
                            }

                            // update quantity of books
                            for (Map.Entry<Integer, Tbl_OrderDetailDTO> item : cart.getItems().entrySet()) {
                                Tbl_BookDAO bDao = new Tbl_BookDAO();

                                int bookId = item.getKey();
                                int quantityInCart = item.getValue().getQuantity();
                                int quantityInStock = bDao.getQuantity(bookId);
                                int newQuantity = quantityInStock - quantityInCart;

                                bDao.setQuantity(bookId, newQuantity);
                            }

                            Tbl_DiscountDAO dDao = new Tbl_DiscountDAO();

                            if (session.getAttribute("DISCOUNTCODE") != null) {
                                int discountCode = (int) session.getAttribute("DISCOUNTCODE");
                                boolean isUsed = dDao.useDiscountCode(discountCode, userId);
                                if (isUsed) {
                                    session.removeAttribute("DISCOUNTCODE");
                                    session.removeAttribute("DISCOUNT");
                                }
                            }
                        }

                        cart = null;
                        session.setAttribute("CART", cart);
                        url = INIT_SEARCH_PAGE_CONTROLLER;
                    }
                }
            }
        } catch (NamingException ex) {
            log("ConfirmController_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("ConfirmController_SQLException " + ex.getMessage());
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
