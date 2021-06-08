/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_user;

import java.io.Serializable;
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
public class Tbl_UserDAO implements Serializable {
    
    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select id from Tbl_User "
                        + "where id = ? and password = ? and status = 'Active'";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, username);
                preStm.setString(2, password);
                
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
    
    public String getUsername(String id) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        String userName = "";
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select userName from Tbl_User "
                        + "where id = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, id);
                
                rs = preStm.executeQuery();
                
                if (rs.next()) {
                    userName = rs.getString("userName");
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
        
        return userName;
        
    }
    
    public boolean isAdmin(String id) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select isAdmin from Tbl_User "
                        + "where id = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, id);
                
                rs = preStm.executeQuery();
                
                if (rs.next()) {
                    result = rs.getBoolean("isAdmin");
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
        
        return result;
        
    }
    
}
