package com.rkoch.book.library.repositories.impl;

import com.rkoch.book.library.entities.BookData;


/**
 *
 * @author rkoch
 */
public class BookDataRepository 
        extends BaseRepository<BookData>{
      
    private static final BookDataRepository INSTANCE = new BookDataRepository();
    
    private BookDataRepository(){}
    
    public static BookDataRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public BookData save(BookData obj) {
        return this.dataSource.put(obj.getId(), obj);
    }   
}
