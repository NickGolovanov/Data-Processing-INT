package com.example.nefix.genrealization.service;

import java.util.List;
import java.util.Optional;

public interface ServiceBase<T, ID> {
    public List<T> findAll();
    public Optional<T> findById(ID id);
    public T save(T entity);
    public T update(ID id, T entity);
    public void deleteById(ID id);
}
