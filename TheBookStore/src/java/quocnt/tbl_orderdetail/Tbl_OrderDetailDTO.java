/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_orderdetail;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
public class Tbl_OrderDetailDTO implements Serializable {
    
    private int id;
    private int bookId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;
    private int orderId;
    private String title;

    public Tbl_OrderDetailDTO() {
    }

    public Tbl_OrderDetailDTO(int id, int bookId, int quantity, BigDecimal unitPrice, BigDecimal total, int orderId, String title) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.orderId = orderId;
        this.title = title;
    }
    
    public Tbl_OrderDetailDTO(int quantity, BigDecimal unitPrice, BigDecimal total, String title) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.title = title;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the bookId
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitPrice.stripTrailingZeros();
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total.stripTrailingZeros();
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
