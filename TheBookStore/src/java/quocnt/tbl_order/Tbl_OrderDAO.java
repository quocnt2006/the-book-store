/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_order;

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
public class Tbl_OrderDAO implements Serializable {
    
    List<Tbl_OrderDTO> listOrder;
    
    public List<Tbl_OrderDTO> getList() {
        return listOrder;
    }
    
    public boolean insertOrder(
            String cusId,
            BigDecimal total
            ) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "insert into Tbl_Order(cusId, total) "
                        + "values (?, ?)";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, cusId);
                preStm.setBigDecimal(2, total);
                
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
    
    public int getLastestId() throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        int id = 0;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select id=max(id) from Tbl_Order";
                
                preStm = con.prepareStatement(sql);
                
                rs = preStm.executeQuery();
                
                if (rs.next()) {
                    id = rs.getInt("id");
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
        
        return id;
        
    }
    
    public void loadAllOrder(String userId) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select o.total as totalOrder, o.orderDate, o.orderDay, o.id, od.bookID, od.title, od.quantity,od.unitPrice, od.total "
                        + "from Tbl_Order o, Tbl_OrderDetail od "
                        + "where o.id = od.orderId and o.cusId = ? "
                        + "order by o.orderDate DESC";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, userId);
                
                rs = preStm.executeQuery();
                
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String orderDate = rs.getString("orderDate");
                    String orderDay = rs.getString("orderDay");
                    String bookId = rs.getString("bookId");
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    BigDecimal unitPrice = rs.getBigDecimal("unitPrice");
                    BigDecimal total = rs.getBigDecimal("total");
                    BigDecimal totalOrder = rs.getBigDecimal("totalOrder");
                    
                    
                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    
                    Tbl_OrderDTO dto = new Tbl_OrderDTO(id, orderDate, orderDay, bookId, title, quantity, unitPrice, total, totalOrder);
                    listOrder.add(dto);
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
    
    public void searchByName(String userId, String searchValue) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select o.total as totalOrder, o.orderDate, o.orderDay, o.id, od.bookID, od.title, od.quantity,od.unitPrice, od.total "
                        + "from Tbl_Order o, Tbl_OrderDetail od "
                        + "where o.id = od.orderId and o.cusId = ? "
                        + "and od.title like ? "
                        + "order by o.orderDate DESC";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, userId);
                preStm.setString(2, "%" + searchValue + "%");
                
                rs = preStm.executeQuery();
                
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String orderDate = rs.getString("orderDate");
                    String orderDay = rs.getString("orderDay");
                    String bookId = rs.getString("bookId");
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    BigDecimal unitPrice = rs.getBigDecimal("unitPrice");
                    BigDecimal total = rs.getBigDecimal("total");
                    BigDecimal totalOrder = rs.getBigDecimal("totalOrder");
                    
                    
                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    
                    Tbl_OrderDTO dto = new Tbl_OrderDTO(id, orderDate, orderDay, bookId, title, quantity, unitPrice, total, totalOrder);
                    listOrder.add(dto);
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
    
    public void searchByDate(String userId, String date) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select o.total as totalOrder, o.orderDate, o.orderDay, o.id, od.bookID, od.title, od.quantity,od.unitPrice, od.total "
                        + "from Tbl_Order o, Tbl_OrderDetail od "
                        + "where o.id = od.orderId and o.cusId = ? "
                        + "and o.orderDay = ? "
                        + "order by o.orderDate DESC";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, userId);
                preStm.setString(2, date);
                
                rs = preStm.executeQuery();
                
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String orderDate = rs.getString("orderDate");
                    String orderDay = rs.getString("orderDay");
                    String bookId = rs.getString("bookId");
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    BigDecimal unitPrice = rs.getBigDecimal("unitPrice");
                    BigDecimal total = rs.getBigDecimal("total");
                    BigDecimal totalOrder = rs.getBigDecimal("totalOrder");
                    
                    
                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    
                    Tbl_OrderDTO dto = new Tbl_OrderDTO(id, orderDate, orderDay, bookId, title, quantity, unitPrice, total, totalOrder);
                    listOrder.add(dto);
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
    
} 
