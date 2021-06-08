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
import quocnt.tbl_orderdetail.Tbl_OrderDetailDTO;

/**
 *
 * @author ASUS
 */
public class UpdateItemController extends HttpServlet {
    
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
        
                    int bookId = Integer.parseInt(request.getParameter("txtBookId"));

                    BigDecimal totalValue = BigDecimal.ZERO;Cart cart = (Cart) session.getAttribute("CART");
            
                    if (cart != null) {
                        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));

                        if (quantity == 0) {
                            cart.removeItem(bookId);
                        } else {
                            Tbl_OrderDetailDTO oDto = cart.getItems().get(bookId);
                            Tbl_BookDAO bDao = new Tbl_BookDAO();
                            int quantityInStock = bDao.getQuantity(bookId);

                            if (quantity > quantityInStock) {
                                String errStr = "The "
                                        + oDto.getTitle()
                                        + " quantity chosen is greater than the quantity in stock! Stock: "
                                        + quantityInStock;
                                request.setAttribute("QUANERR", errStr);
                            } else {
                                BigDecimal pricePerBook = oDto.getTotal().divide(BigDecimal.valueOf((long) oDto.getQuantity()));
                                oDto.setQuantity(quantity);
                                BigDecimal total = pricePerBook.multiply(BigDecimal.valueOf((long) quantity));                    
                                oDto.setTotal(total);
                                cart.updateItemInCart(bookId, oDto);
                            }
                        }

                        //set total
                        for (Map.Entry<Integer, Tbl_OrderDetailDTO> item : cart.getItems().entrySet()) {
                            totalValue = totalValue.add(item.getValue().getTotal()).stripTrailingZeros();
                        }

                        session.setAttribute("CART", cart);
                        session.removeAttribute("DISCOUNTCODE");
                        session.removeAttribute("DISCOUNT");
                        session.setAttribute("TOTAL", totalValue);
                    }
                }
            }
        } catch (NumberFormatException ex) {
            String errStr = "Please input the number";
            request.setAttribute("QUANERR", errStr);
        } catch (NamingException ex) {
            log("UpdateItemController_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateItemController_SQLException " + ex.getMessage());
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
