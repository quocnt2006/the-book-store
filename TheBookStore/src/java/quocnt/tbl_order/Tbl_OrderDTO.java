/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
public class Tbl_OrderDTO implements Serializable {
    
    private int id;
    private String orderDate;
    private String orderDay;
    private String bookId;
    private String title;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;
    private BigDecimal totalOrder;

    public Tbl_OrderDTO() {
    }

    public Tbl_OrderDTO(int id, String orderDate, String orderDay, String bookId, String title, int quantity, BigDecimal unitPrice, BigDecimal total, BigDecimal totalOrder) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderDay = orderDay;
        this.bookId = bookId;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.totalOrder = totalOrder;
    }
    
    public Tbl_OrderDTO(int id, String orderDate, String orderDay, String bookId, String title, int quantity, BigDecimal unitPrice, BigDecimal total) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderDay = orderDay;
        this.bookId = bookId;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public Tbl_OrderDTO(int id, String orderDate, String bookId, String title, int quantity, BigDecimal unitPrice, BigDecimal total) {
        this.id = id;
        this.orderDate = orderDate;
        this.bookId = bookId;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
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
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the orderDay
     */
    public String getOrderDay() {
        return orderDay;
    }

    /**
     * @param orderDay the orderDay to set
     */
    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }

    /**
     * @return the bookId
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
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
     * @return the totalOrder
     */
    public BigDecimal getTotalOrder() {
        return totalOrder;
    }

    /**
     * @param totalOrder the totalOrder to set
     */
    public void setTotalOrder(BigDecimal totalOrder) {
        this.totalOrder = totalOrder;
    }
    
}
