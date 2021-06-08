/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@MultipartConfig
public class ServletDispatcherController extends HttpServlet {
    
    private final String INIT_SEARCH_PAGE_CONTROLLER = "InitSearchPageController";
    private final String SEARCH_CONTROLLER = "SearchController";
    private final String LOGIN_CONTROLLER = "LoginController";
    private final String CREATE_BOOK_CONTROLLER = "CreateBookController";
    private final String UPDATE_BOOK_CONTROLLER = "UpdateBookController";
    private final String DELETE_CONTROLLER = "DeleteController";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    private final String UPDATE_ITEM_CONTROLLER = "UpdateItemController";
    private final String CONFIRM_CONTROLLER = "ConfirmController";
    private final String REMOVE_ITEM_CONTROLLER = "RemoveItemController";
    private final String INIT_DISCOUNT_CONTROLLER = "InitDiscountController";
    private final String CREATE_DISCOUNT_CONTROLLER = "CreateDiscountController";
    private final String SUBMIT_DISCOUNT_CONTROLLER = "SubmitDiscountController";
    private final String INIT_HISTORY_CONTROLLER = "InitHistoryController";
    private final String SEARCH_HISTORY_CONTROLLER = "SearchHistoryController";

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
        
        String button = request.getParameter("btAction");
        String url = INIT_SEARCH_PAGE_CONTROLLER;
        
        try {
            if (button != null) {
                switch (button) {
                    case "Search":
                        url = SEARCH_CONTROLLER;
                        break;
                    case "Login":
                        url = LOGIN_CONTROLLER;
                        break;
                    case "Logout":
                        url = LOGOUT_CONTROLLER;
                        break;
                    case "Create":
                        url = CREATE_BOOK_CONTROLLER;
                        break;
                    case "Update":
                        url = UPDATE_BOOK_CONTROLLER;
                        break;
                    case "Delete":
                        url = DELETE_CONTROLLER;
                        break;
                    case "Add to Cart":
                        url = ADD_TO_CART_CONTROLLER;
                        break;
                    case "Update Item":
                        url = UPDATE_ITEM_CONTROLLER;
                        break;   
                    case "Confirm":
                        url = CONFIRM_CONTROLLER;
                        break;
                    case "RemoveItem":
                        url = REMOVE_ITEM_CONTROLLER;
                        break;
                    case "Create new discount":
                        url = INIT_DISCOUNT_CONTROLLER;
                        break;
                    case "Create discount code":
                        url = CREATE_DISCOUNT_CONTROLLER;
                        break;
                    case "Use code":
                        url = SUBMIT_DISCOUNT_CONTROLLER;
                        break;
                    case "History":
                        url = INIT_HISTORY_CONTROLLER;
                        break;
                    case "Search history":
                        url = SEARCH_HISTORY_CONTROLLER;
                        break;
                    default:
                        break;
                }
            }
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
