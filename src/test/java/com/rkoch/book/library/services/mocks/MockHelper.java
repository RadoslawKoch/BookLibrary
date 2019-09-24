package com.rkoch.book.library.services.mocks;

import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.entities.BookData;
import com.rkoch.book.library.entities.CoverType;

/**
 *
 * @author rkoch
 */
public class MockHelper {
    
    public static Book getBook(BookData data, CoverType type){
        Book book = new Book(data,type);
        book.setAvaliable(true);
        book.setCoverType(CoverType.SOFT);
        book.setData(data);
        return book;
    }
    
    public static BookData getBookData(String title, String author, long isbn){
        return new BookData(isbn,author,title);
    }
}
