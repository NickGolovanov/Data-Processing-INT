package com.example.nefix.genrealization.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E, ID> implements ServiceBase<E, ID> {
    protected final JpaRepository<E, ID> repository;

    protected BaseService(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    public List<E> findAll() {
        return this.repository.findAll();
    };

    public Optional<E> findById(ID id)
    {
        return this.repository.findById(id);
    };

    public E save(E entity) {
        return this.repository.save(entity);
    };

    public E update(ID id, E entity) throws RuntimeException {
        if (!this.repository.existsById(id)) {
            throw new RuntimeException("Entity not found with id: " + id);
        }
        return this.repository.save(entity);
    };

    public void deleteById(ID id) throws RuntimeException {
        if (!this.repository.existsById(id)) {
            throw new RuntimeException("Entity not found with id: " + id);
        }
        this.repository.deleteById(id);
    };
}