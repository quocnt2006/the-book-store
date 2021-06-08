/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_orderdetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import quocnt.dbutils.DBUtils;

/**
 *
 * @author ASUS
 */
public class Tbl_OrderDetailDAO implements Serializable{
    
    public boolean insertOrderDetail(
            int bookId,
            String title,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal itemTotal,
            int orderId
    ) throws SQLException, NamingException {
        
        Connection con = null;
        PreparedStatement preStm = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "insert into "
                        + "Tbl_OrderDetail(bookId, title, quantity, unitPrice, total, orderId) "
                        + "values(?, ?, ?, ?, ?, ?)";
                
                preStm = con.prepareStatement(sql);
                preStm.setInt(1, bookId);
                preStm.setString(2, title);
                preStm.setInt(3, quantity);
                preStm.setBigDecimal(4, unitPrice);
                preStm.setBigDecimal(5, itemTotal);
                preStm.setInt(6, orderId);
                
                int row = preStm.executeUpdate();
                
                if(row > 0) {
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
