package com.rkoch.book.library.services.mocks;

import com.rkoch.book.library.entities.BookOrder;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author rkoch
 */
public class MockBookOrderRepository implements RepositoryDefinition<BookOrder,Long> {

    @Override
    public BookOrder get(Long id) {
        if(id.equals(TestData.GET_BOOK_ORDER.getId())){
            return TestData.GET_BOOK_ORDER;
        }
        return null;
    }

    @Override
    public List<BookOrder> get() {
        return TestData.GET_ALL_ORDERS;
    }

    @Override
    public BookOrder save(BookOrder obj) {
        return obj;
    }

    @Override
    public BookOrder delete(Long id) {
        if(id.equals(TestData.GET_BOOK_ORDER.getId())){
            return TestData.GET_BOOK_ORDER;
        }
        return null;
    }

    @Override
    public List<BookOrder> search(List<Predicate<BookOrder>> preds) {
        for(Predicate<BookOrder> x : preds){
            if(!x.test(TestData.GET_BOOK_ORDER))
                return new ArrayList();
        }
        return TestData.GET_ALL_ORDERS;
    }

    @Override
    public List<BookOrder> search(Predicate<BookOrder>... preds) {
        return search(List.of(preds));
    }

    @Override
    public Integer count() {
        return 1;
    } 
}
