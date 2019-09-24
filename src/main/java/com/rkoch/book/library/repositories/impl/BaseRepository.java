package com.rkoch.book.library.repositories.impl;

import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import com.rkoch.book.library.entities.definition.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *
 * @author rkoch
 * @param <T>
 */
public abstract class BaseRepository<T extends Entity<Long>> 
        implements RepositoryDefinition<T,Long>{

    protected final Map<Long,T> dataSource = new ConcurrentHashMap();

    @Override
    public T get(Long id){
        return this.dataSource.get(id);
    }
    
    @Override
    public List<T> get(){
        return new ArrayList(this.dataSource.values());
    }
    
    @Override
    public abstract T save(T obj);
    
    @Override
    public T delete(Long id){
        T obj = this.dataSource.get(id);
        this.dataSource.remove(id);
        return obj;
    }
    
    @Override
    public List<T> search(List<Predicate<T>> preds){
        Stream<T> stream = this.dataSource.values().stream();
        for(var x : preds){
            stream = stream.filter(x);
            System.out.println(x);
        }
        return stream.collect(Collectors.toList());
    }
    
    @Override
    public List<T> search(Predicate<T>... preds){
        return search(List.of(preds));
    }

    @Override
    public Integer count(){
        return this.dataSource.size();
    }  
}
