package com.example.nefix.genrealization.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID> implements ServiceBase<T, ID> {
    private final JpaRepository<T, ID> repository;

    protected BaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    };

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    };

    public T save(T entity) {
        return repository.save(entity);
    };

    public T update(ID id, T entity) throws RuntimeException {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found with id: " + id);
        }
        return repository.save(entity);
    };

    public void deleteById(ID id) throws RuntimeException {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found with id: " + id);
        }
        repository.deleteById(id);
    };
}
