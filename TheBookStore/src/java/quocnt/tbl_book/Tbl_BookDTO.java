/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.tbl_book;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
public class Tbl_BookDTO implements Serializable {
    
    private int id;
    private String title;
    private BigDecimal price;
    private String image;
    private String author;
    private String description;
    private int quantity;
    private String importDate;
    private String status;
    private String category;

    public Tbl_BookDTO() {
    }

    public Tbl_BookDTO(int id, String title, BigDecimal price, String image, String author, String description, int quantity, String importDate, String status, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
        this.importDate = importDate;
        this.status = status;
        this.category = category;
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
     * @return the price
     */
    public BigDecimal getPrice() {
        return price.stripTrailingZeros();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the importDate
     */
    public String getImportDate() {
        return importDate;
    }

    /**
     * @param importDate the importDate to set
     */
    public void setImportDate(String importDate) {
        this.importDate = importDate;
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

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
}
