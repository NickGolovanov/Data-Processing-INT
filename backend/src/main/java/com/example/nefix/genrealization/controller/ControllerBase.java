package com.example.nefix.genrealization.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ControllerBase<T, ID> {
    public ResponseEntity<List<T>> getAll();
    public ResponseEntity<T> getById(ID id);
    public ResponseEntity<T> create(T entity);
    public ResponseEntity<T> update(ID id, T entity);
    public ResponseEntity<Void> delete(ID id);
}
