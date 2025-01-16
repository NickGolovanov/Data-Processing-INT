package com.example.nefix.genrealization.service;

import java.util.List;
import java.util.Optional;

public interface ServiceBase<E, ID> {
    List<E> findAll();
    Optional<E> findById(ID id);
    E save(E entity);
    E update(ID id, E entity);
    void deleteById(ID id);
}
