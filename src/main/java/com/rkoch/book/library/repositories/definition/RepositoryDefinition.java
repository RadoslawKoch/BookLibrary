package com.rkoch.book.library.repositories.definition;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author rkoch
 * @param <T> Main Entity Datatype
 * @param <I> Entity Id
 */
public interface RepositoryDefinition<T,I> {
        
    T get(I id);
    
    List<T> get();
    
    T save(T obj);
    
    T delete(I id);
    
    List<T> search(List<Predicate<T>> preds);
    
    List<T> search(Predicate<T>... preds);
    
    Integer count();
}
