package com.rkoch.book.library.entities;

import java.util.ArrayList;
import java.util.List;
import com.rkoch.book.library.entities.definition.Entity;

/**
 *
 * @author rkoch
 */
public class Book 
    implements Entity<Long> {
       
    //Autogenerated PK
    private long id;
    
    //FK (ISBN)
    private BookData data;
    private CoverType coverType;
    private boolean avaliable = true;
    
    //One To Many
    private final List<BookOrder> orders = new ArrayList();

    public Book(BookData data, CoverType coverType) {
        this.data = data;
        this.coverType = coverType;
    }
    
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public BookData getData() {
        return data;
    }

    public void setData(BookData data) {
        this.data = data;
    }

    public CoverType getCoverType() {
        return coverType;
    }

    public void setCoverType(CoverType coverType) {
        this.coverType = coverType;
    }

    public boolean isAvaliable() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    public List<BookOrder> getOrders() {
        return orders;
    }
    
    public Customer getCustomer(){
        if(this.avaliable)
            return null;
        return this.orders.get(this.orders.size()-1).getCustomer();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
  
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", data=" + data
                + ", coverType=" + coverType 
                + ", avaliable=" + avaliable +","+ (avaliable?"":this.getCustomer())+'}';
    }
}
