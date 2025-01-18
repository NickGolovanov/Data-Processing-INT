package com.example.nefix.genrealization.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E, ID> implements ServiceBase<E, ID>
{
    protected final JpaRepository<E, ID> repository;
    private final List<String> ignoreProperties;

    protected BaseService(JpaRepository<E, ID> repository, List<String> ignoreProperties)
    {
        this.repository = repository;
        this.ignoreProperties = ignoreProperties;
    }

    public List<E> findAll()
    {
        return this.repository.findAll();
    }

    public Optional<E> findById(ID id)
    {
        return this.repository.findById(id);
    }

    public E save(E entity)
    {
        return this.repository.save(entity);
    }

    public E update(ID id, E entity) throws RuntimeException
    {
        E existingEntity = this.repository.findById(id).orElseThrow(() ->
                new RuntimeException("Entity not found with id: " + id)
        );

        BeanUtils.copyProperties(entity, existingEntity, ignoreProperties.toArray(new String[0]));

        return this.repository.save(existingEntity);
    }

    public void deleteById(ID id) throws RuntimeException
    {
        if (!this.repository.existsById(id))
        {
            throw new RuntimeException("Entity not found with id: " + id);
        }
        this.repository.deleteById(id);
    }
}