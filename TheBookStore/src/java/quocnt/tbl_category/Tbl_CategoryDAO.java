/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_category;

import java.io.Serializable;
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
public class Tbl_CategoryDAO implements Serializable {
    
    List<Tbl_CategoryDTO> listCategory;
    
    public List<Tbl_CategoryDTO> getList() {
        return listCategory;
    }
    
    public void setList(List<Tbl_CategoryDTO> list) {
        this.listCategory = list;
    }
    
    public void loadCategory() throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "Select id, categoryName from Tbl_Category";
                
                preStm = con.prepareStatement(sql);
                rs = preStm.executeQuery();
                
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String categoryName = rs.getString("categoryName");
                    
                    Tbl_CategoryDTO dto = new Tbl_CategoryDTO(id, categoryName);
                    if (listCategory == null) {
                        listCategory = new ArrayList<>();
                    }
                    listCategory.add(dto);
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
    
    public int getCategoryId(String categoryName) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        int id = -1;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "select id from Tbl_Category "
                        + "where categoryName = ?";
                
                preStm = con.prepareStatement(sql);
                preStm.setString(1, categoryName);
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
    
}
