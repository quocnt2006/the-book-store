/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_user;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Tbl_UserDTO implements Serializable {
    
    private String id;
    private String password;
    private String userName;
    private boolean isAdmin;
    private String status;

    public Tbl_UserDTO() {
    }

    public Tbl_UserDTO(String id, String password, String userName, boolean isAdmin, String status) {
        this.id = id;
        this.password = password;
        this.userName = userName;
        this.isAdmin = isAdmin;
        this.status = status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
