package com.rkoch.book.library.repositories.impl;

import com.rkoch.book.library.entities.Book;
import java.util.concurrent.atomic.AtomicLong;


/**
 *
 * @author rkoch
 */
public class BookRepository extends BaseRepository<Book> {
    
    
    private final static BookRepository INSTANCE = new BookRepository();
    
    private static final AtomicLong COUNTER = new AtomicLong(1);
    
//    private BookRepository(){}
    
    public static BookRepository getInstance(){
        return INSTANCE;
    }
    
    @Override
    public Book save(Book obj) {
        Long id = COUNTER.getAndIncrement();
        obj.setId(id);
        this.dataSource.put(id, obj);
        return obj;
    }
}
