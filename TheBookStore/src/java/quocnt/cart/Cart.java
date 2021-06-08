/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quocnt.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import quocnt.tbl_orderdetail.Tbl_OrderDetailDTO;

/**
 *
 * @author ASUS
 */
public class Cart implements Serializable {
    
    private Map<Integer, Tbl_OrderDetailDTO> items;

    public Cart() {
    }

    public Cart(Map<Integer, Tbl_OrderDetailDTO> items) {
        this.items = items;
    }

    /**
     * @return the items
     */
    public Map<Integer, Tbl_OrderDetailDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Map<Integer, Tbl_OrderDetailDTO> items) {
        this.items = items;
    }
    
    public void addItemToCart(int bookId, Tbl_OrderDetailDTO detail) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        
        if (this.items.containsKey(bookId)) {
            
        } else {
            this.items.put(bookId, detail);
        }
    }
    
    public void addMore(int bookId, Tbl_OrderDetailDTO detail) {
        for (Map.Entry<Integer, Tbl_OrderDetailDTO> item : items.entrySet()) {
            if (bookId == item.getKey()) {
                // get old item
                Tbl_OrderDetailDTO dto = (Tbl_OrderDetailDTO) item.getValue();

                // set new amount
                detail.setQuantity(detail.getQuantity() + dto.getQuantity());

                // set new total
                BigDecimal newTotal = dto.getTotal().add(detail.getTotal());
                detail.setTotal(newTotal);
                
                this.items.put(bookId, detail);
                break;
            }
        }
    }
    
    public void removeItem(int bookId) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(bookId)) {
            this.items.remove(bookId);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
    public void updateItemInCart(int bookId, Tbl_OrderDetailDTO detail) {
        for (Map.Entry<Integer, Tbl_OrderDetailDTO> item: items.entrySet()) {
            if (bookId == item.getKey()) {
                this.items.put(bookId, detail);
                break;
            }
        }
    }
    
}
