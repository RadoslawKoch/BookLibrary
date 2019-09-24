package com.rkoch.book.library.entities;

/**
 *
 * @author rkoch
 */
public class BookStats {
    
    private long total;
    private long available;
    private long lent;
    
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public long getLent() {
        return lent;
    }

    public void setLent(long lent) {
        this.lent = lent;
    }

    @Override
    public String toString() {
        return "BookStats{" + "total=" + total + ", available=" + available + ", lent=" + lent + '}';
    }   
    
    
}
