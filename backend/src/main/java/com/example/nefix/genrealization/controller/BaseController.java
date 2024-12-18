package com.example.nefix.genrealization.controller;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.genrealization.service.ServiceBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<T, ID> implements ControllerBase<T, ID>
{
    protected final BaseService<T, ID> service;

    protected BaseController(BaseService<T, ID> service)
    {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll()
    {
        List<T> entities = service.findAll();

        if (entities.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id)
    {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity)
    {
        T savedEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity)
    {
        try
        {
            T updatedEntity = service.update(id, entity);
            return ResponseEntity.ok(updatedEntity);
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id)
    {
        try
        {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
