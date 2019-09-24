package com.rkoch.book.library.services.mocks;

import com.rkoch.book.library.entities.BookData;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author rkoch
 */
public class MockBookDataRepository implements RepositoryDefinition<BookData,Long>{

    @Override
    public BookData get(Long id) {
        if(id.equals(TestData.EXISTING_BOOK_ISBN))
            return TestData.GET_BOOK_DATA;
        return null;
    }

    @Override
    public List<BookData> get() {
        return TestData.GET_ALL_BOOK_DATA;
    }

    @Override
    public BookData save(BookData obj) {
        return obj;
    }

    @Override
    public BookData delete(Long id) {
        if(id.equals(TestData.EXISTING_BOOK_ISBN))
            return TestData.GET_BOOK_DATA;
        return null;
    }

    @Override
    public List<BookData> search(List<Predicate<BookData>> preds) {
        for(Predicate<BookData> x : preds){
            if(!x.test(TestData.GET_BOOK_DATA))
                return new ArrayList();
        }
        return TestData.GET_ALL_BOOK_DATA;
    }

    @Override
    public List<BookData> search(Predicate<BookData>... preds) {
        return search(List.of(preds));
    }

    @Override
    public Integer count() {
        return 1;
    }  
}
