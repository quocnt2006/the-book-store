/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_book;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quocnt.dbutils.DBUtils;

/**
 *
 * @author ASUS
 */
public class Tbl_BookDAO implements Serializable {
    
    List<Tbl_BookDTO> listBook;
    
    public List<Tbl_BookDTO> getList() {
        return listBook;
    }
    
    public void setList(List<Tbl_BookDTO> list) {
        this.listBook = list;
    }
    
    public void loadAll() throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select Tbl_Book.id, title, price, image, author, "
                        + "Tbl_Category.categoryName, description, quantity, importDate, status "
                        + "from Tbl_Book, Tbl_Category "
                        + "where status='Active' and Tbl_Book.categoryId=Tbl_Category.id and quantity > 0";
                
                preStm = con.prepareStatement(sql);
                rs = preStm.executeQuery();
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    BigDecimal price = rs.getBigDecimal("price");
                    String image = rs.getString("image");
                    String author = rs.getString("author");
                    String category = rs.getString("categoryName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    String importdate = rs.getString("importDate");
                    String status = rs.getString("status");
                    
                    Tbl_BookDTO dto = new Tbl_BookDTO(id, title, price, image, author, description, quantity, importdate, status, category);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }
    
    public void searchByName(String searchValue) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select Tbl_Book.id, title, price, image, author, "
                        + "Tbl_Category.categoryName, description, quantity, importDate, status "
                        + "from Tbl_Book, Tbl_Category "
                        + "where status='Active' and Tbl_Book.categoryId=Tbl_Category.id and quantity > 0 "
                        + "and title like ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, "%" + searchValue + "%");
                rs = preStm.executeQuery();
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    BigDecimal price = rs.getBigDecimal("price");
                    String image = rs.getString("image");
                    String author = rs.getString("author");
                    String category = rs.getString("categoryName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    String importdate = rs.getString("importDate");
                    String status = rs.getString("status");
                    
                    Tbl_BookDTO dto = new Tbl_BookDTO(id, title, price, image, author, description, quantity, importdate, status, category);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }
    
    public void searchByRangePrice(float min, float max) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql;
                
                if (min != -1 && max != -1) {
                    sql = "select Tbl_Book.id, title, price, image, author, "
                        + "Tbl_Category.categoryName, description, quantity, importDate, status "
                        + "from Tbl_Book, Tbl_Category "
                        + "where status='Active' and Tbl_Book.categoryId=Tbl_Category.id and quantity > 0 "
                        + "and price >= ? and price <= ?";
                    
                    preStm = con.prepareStatement(sql);
                    preStm.setFloat(1, min);
                    preStm.setFloat(2, max);
                    rs = preStm.executeQuery();
                } else {
                    if (min == -1) {
                        sql = "select Tbl_Book.id, title, price, image, author, "
                            + "Tbl_Category.categoryName, description, quantity, importDate, status "
                            + "from Tbl_Book, Tbl_Category "
                            + "where status='Active' and Tbl_Book.categoryId=Tbl_Category.id and quantity > 0 "
                            + "and price <= ?";
                        preStm = con.prepareStatement(sql);
                        preStm.setFloat(1, max);
                        rs = preStm.executeQuery();
                    } else{
                        sql = "select Tbl_Book.id, title, price, image, author, "
                        + "Tbl_Category.categoryName, description, quantity, importDate, status "
                        + "from Tbl_Book, Tbl_Category "
                        + "where status='Active' and Tbl_Book.categoryId=Tbl_Category.id and quantity > 0 "
                        + "and price >= ?";
                        preStm = con.prepareStatement(sql);
                        preStm.setFloat(1, min);
                        rs = preStm.executeQuery();
                    }
                }
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    BigDecimal price = rs.getBigDecimal("price");
                    String image = rs.getString("image");
                    String author = rs.getString("author");
                    String category = rs.getString("categoryName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    String importdate = rs.getString("importDate");
                    String status = rs.getString("status");
                    
                    Tbl_BookDTO dto = new Tbl_BookDTO(id, title, price, image, author, description, quantity, importdate, status, category);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    } 
    
    public void searchByCategory(String categoryValue) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select Tbl_Book.id, title, price, image, author, "
                        + "Tbl_Category.categoryName, description, quantity, importDate, status "
                        + "from Tbl_Book, Tbl_Category "
                        + "where status='Active' and Tbl_Book.categoryId=Tbl_Category.id and quantity > 0 "
                        + "and Tbl_Category.categoryName=?";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, categoryValue);
                rs = preStm.executeQuery();
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    BigDecimal price = rs.getBigDecimal("price");
                    String image = rs.getString("image");
                    String author = rs.getString("author");
                    String category = rs.getString("categoryName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    String importdate = rs.getString("importDate");
                    String status = rs.getString("status");
                    
                    Tbl_BookDTO dto = new Tbl_BookDTO(id, title, price, image, author, description, quantity, importdate, status, category);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }
    
    public boolean createBook(
            String title, 
            String price, 
            String author, 
            int category,
            String photo,
            String quantity,
            String description
        ) throws SQLException, NamingException {
    
        Connection con = null;
        PreparedStatement preStm = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "insert into Tbl_Book(title, description,"
                        + " price, author, categoryId, quantity, image, status) "
                        + "values(?,?,?,?,?,?,?, 'Active')";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, title);
                preStm.setString(2, description);
                preStm.setString(3, price);
                preStm.setString(4, author);
                preStm.setInt(5, category);
                preStm.setString(6, quantity);
                preStm.setString(7, photo);
                
                int row = preStm.executeUpdate();
                
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (preStm  != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
            
        return false;
    }
    
    public boolean updateBook(
            String id,
            String title, 
            String price, 
            String author, 
            int category,
            String photo,
            String quantity,
            String description,
            String date
        ) throws SQLException, NamingException {
    
        Connection con = null;
        PreparedStatement preStm = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "update Tbl_Book set "
                        + "title = ?, "
                        + "description = ?, "
                        + "price = ?, "
                        + "author = ?, "
                        + "importDate = ?, "
                        + "quantity = ?, "
                        + "image = ?, "
                        + "categoryId = ? "
                        + "where id = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, title);
                preStm.setString(2, description);
                preStm.setString(3, price);
                preStm.setString(4, author);
                preStm.setString(5, date);
                preStm.setString(6, quantity);
                preStm.setString(7, photo);
                preStm.setInt(8, category);
                preStm.setString(9, id);
                
                int row = preStm.executeUpdate();
                
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (preStm  != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
            
        return false;
        
    }
    
    public boolean deleteBook(String id) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "update Tbl_Book set "
                        + "status = 'Inactive' "
                        + "where id = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, id);
                
                int row = preStm.executeUpdate();
                
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return false;
        
    }
    
    public int getQuantity(int bookId) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        int quantity = 0;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select quantity from Tbl_Book where id = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, bookId);
                
                rs = preStm.executeQuery();
                
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            } 
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return quantity;
        
    }
    
    public boolean setQuantity(int bookId, int quantity) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "update Tbl_Book set "
                        + "quantity = ? "
                        + "where id = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, quantity);
                preStm.setInt(2, bookId);
                
                int row = preStm.executeUpdate();
                
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (preStm  != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
            
        return false;
        
    }
}
