/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import quocnt.tbl_book.Tbl_BookDAO;
import quocnt.tbl_category.Tbl_CategoryDAO;

/**
 *
 * @author ASUS
 */
@MultipartConfig
@WebServlet(name = "CreateBookController", urlPatterns = {"/CreateBookController"})
public class CreateBookController extends HttpServlet {
    
    private final String CREATE_BOOK_PAGE = "createNewBook.jsp";
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
                if (isAdmin) {
                    url = CREATE_BOOK_PAGE;
                    
                    String title = request.getParameter("txtTitle");
                    String description = request.getParameter("txtDescription");
                    String price = request.getParameter("txtPrice");
                    String author = request.getParameter("txtAuthor");
                    String categoryName = request.getParameter("txtCategory");
                    String quantity = request.getParameter("txtQuantity");
                    boolean checkImage;
                    
                    //upload photo
                    Part uploadPart = request.getPart("imageSource");
                    String imageName;
                    String imageType;
                    BufferedImage bi;
                    String savePath;
                    String saveDbPath;
                    
                    imageName = uploadPart.getSubmittedFileName();
                    int lastIndexImage = imageName.lastIndexOf("\\");
                    imageName = imageName.substring(lastIndexImage + 1);
                    int lastIndextype = imageName.lastIndexOf(".");
                    imageType = imageName.substring(lastIndextype + 1);
                    savePath = request.getServletContext().getRealPath("");
                    InputStream is = uploadPart.getInputStream();
                    bi = ImageIO.read(is);
                    
                    if (!imageType.equals("png") && !imageType.equals("jpg") && !imageType.equals("jpeg")) {
                        checkImage = false;
                        request.setAttribute("ERRORTYPE", "This file is not image file!");
                    } else {
                        checkImage = true;
                    }
                     
                    if (checkImage) {
                        saveDbPath = ".\\IMG\\" +imageName;
                    
                        ImageIO.write(bi, imageType, new File(savePath + "\\IMG" + imageName));
                        File file = new File(savePath).getParentFile().getParentFile();
                        ImageIO.write(bi, imageType, new File(file.getAbsoluteFile() + "\\web\\IMG\\" + imageName));
                        Thread.sleep(500);

                        Tbl_CategoryDAO cDao = new Tbl_CategoryDAO();
                        int categoryId = cDao.getCategoryId(categoryName);

                        Tbl_BookDAO bDao = new Tbl_BookDAO();
                        boolean result = bDao.createBook(title, price, author, categoryId, saveDbPath, quantity, description);

                        if (result) {
                            url = INIT_SEARCH_PAGE_CONTROLLER;
                        }
                    }
                }
            }
        } catch (InterruptedException ex) {
            log("CreateBookController_InterruptedException " + ex.getMessage());
        } catch (NamingException ex) {
            log("CreateBookController_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateBookController_SQLException " + ex.getMessage());
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
