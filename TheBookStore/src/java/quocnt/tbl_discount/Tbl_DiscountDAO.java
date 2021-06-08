/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_discount;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import quocnt.dbutils.DBUtils;

/**
 *
 * @author ASUS
 */
public class Tbl_DiscountDAO implements Serializable {
    
    public boolean checkDiscountCodeExist(int discountCode) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select code from Tbl_Discount "
                        + "where code = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, discountCode);
                
                rs = preStm.executeQuery();
                
                if (rs.next()) {
                    return true;
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
        
        return false;
        
    }
    
    public boolean createDiscount(int code, BigDecimal discountValue) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "insert into Tbl_Discount(code, discount, isUsed) "
                        + "values(?, ?, 0)";
                
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, code);
                preStm.setBigDecimal(2, discountValue);
                
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
    
    public boolean checkDiscountCode(int discountCode) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select code from Tbl_Discount "
                        + "where code = ? and isUsed = 0";
                
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, discountCode);
                
                rs = preStm.executeQuery();
                
                if (rs.next()) {
                    return true;
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
        
        return false;
        
    }
    
    public BigDecimal getDiscountValue(int discountCode) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        BigDecimal discountValue = BigDecimal.ZERO;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select discount from Tbl_Discount "
                        + "where code = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, discountCode);
                
                rs = preStm.executeQuery();
                
                if (rs.next()) {
                    discountValue = rs.getBigDecimal("discount");
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
        
        return discountValue;
        
    }
    
    public boolean useDiscountCode(int code, String userId) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "update Tbl_Discount "
                        + "set cusId = ?, isUsed = 1 "
                        + "where code = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, userId);
                preStm.setInt(2, code);
                
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
    
}
