package com.rkoch.book.library.repositories.impl;

import com.rkoch.book.library.entities.BookOrder;
import java.util.concurrent.atomic.AtomicLong;


/**
 *
 * @author rkoch
 */
public class BookOrderRepository 
        extends BaseRepository<BookOrder> {
    
    private static final BookOrderRepository INSTANCE = new BookOrderRepository();
    
    private static final AtomicLong COUNTER = new AtomicLong(1);
    
    private BookOrderRepository(){}
    
    public static BookOrderRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public BookOrder save(BookOrder obj) {
        Long id = COUNTER.getAndIncrement();
        obj.setId(id);
        this.dataSource.put(id, obj);
        return obj;
    }
}
