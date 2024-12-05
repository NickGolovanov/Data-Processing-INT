package com.example.nefix.genrealization.controller;

import java.util.List;
import java.util.Optional;

public interface ControllerBase<T, ID> {
    public List<T> getAll();
    public Optional<T> getById(ID id);
    public T create(T entity);
    public T update(ID id, T entity);
    public void delete(ID id);
}
