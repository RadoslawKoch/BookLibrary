package com.rkoch.book.library.services.impl;

import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import com.rkoch.book.library.services.definitions.AvailabilityServiceDefinition;
import java.util.List;


/**
 *
 * @author rkoch
 */
public class AvailabilityService 
    implements AvailabilityServiceDefinition{
    
    private final RepositoryDefinition<Book,Long> repo;
    
    public AvailabilityService(RepositoryDefinition<Book,Long> repo){
        this.repo = repo;
    }
    
    @Override
    public boolean isBookAvailable(long isbn){
        List<Book> books = 
                this.repo.search(x->x.getData().getId()==isbn && x.isAvaliable());
        return !books.isEmpty();
    }   
}
