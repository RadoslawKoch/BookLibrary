package com.rkoch.book.library.entities;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import com.rkoch.book.library.entities.definition.Entity;
import java.util.Objects;

/**
 *
 * @author rkoch
 */
public class BookData 
        implements Entity<Long>{
    
    //Manually Entered ISBN acting as PK
    private long id;
    private String author;
    private String title;
    private Year year = Year.now();
    
    //One to Many
    private final List<Book> books = new ArrayList();
    
    public BookData(long isbn,String author, String title) {
        this.id = isbn;
        this.author = author;
        this.title = title;
    }

    public BookData(long isbn,String author, String title, Year year) {
        this(isbn,author,title);
        this.year = year;
    }
    
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public BookStats getStats(){
        BookStats stats = new BookStats();
        long total = this.books.size();
        long available = this.books.stream()
                .distinct().filter(x->x.isAvaliable()).count();
        stats.setAvailable(available);
        stats.setLent(total-available);
        stats.setTotal(total);
        return stats;        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.year);
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
        final BookData other = (BookData) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BookData{" + "id=" + id + ", author=" + author + ", title=" + title + ", year=" + year + ", stats="+getStats()+ '}';
    }   
}
