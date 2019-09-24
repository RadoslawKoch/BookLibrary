package com.rkoch.book.library.services.mocks;

import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author rkoch
 */
public class MockBookRepository implements RepositoryDefinition<Book,Long>{

    
    
    @Override
    public Book get(Long id) {
        if(id.equals(TestData.GET_BOOK.getId())){
            return TestData.GET_BOOK;
        }
        return null;
    }

    @Override
    public List<Book> get() {
        return TestData.GET_ALL_BOOKS;
    }

    @Override
    public Book save(Book obj) {
        return obj;
    }

    @Override
    public Book delete(Long id) {
        if(id.equals(TestData.GET_BOOK.getId())){
            return TestData.GET_BOOK;
        }
        return null;
    }

    @Override
    public List<Book> search(List<Predicate<Book>> preds) {
        for(Predicate<Book> x : preds){
            if(!x.test(TestData.GET_BOOK))
                return new ArrayList();
        }
        return TestData.GET_ALL_BOOKS;
    }

    @Override
    public List<Book> search(Predicate<Book>... preds) {
        return search(List.of(preds));
    }

    @Override
    public Integer count() {
        return 1;
    }    
}
