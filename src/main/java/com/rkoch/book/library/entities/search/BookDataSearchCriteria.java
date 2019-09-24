package com.rkoch.book.library.entities.search;

import java.time.Year;

/**
 *
 * @author rkoch
 */
public class BookDataSearchCriteria {
    
    private String author;
    private String title;
    private Year year;

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
}
