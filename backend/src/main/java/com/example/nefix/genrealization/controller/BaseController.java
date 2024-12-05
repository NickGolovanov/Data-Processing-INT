package com.example.nefix.genrealization.controller;

import com.example.nefix.genrealization.service.ServiceBase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<T, ID> implements ControllerBase<T, ID> {
    private final ServiceBase<T, ID> service;

    protected BaseController(ServiceBase<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> getAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Optional<T> getById(@PathVariable ID id) {
        return service.findById(id);
    }

    @PostMapping
    public T create(@RequestBody T entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @RequestBody T entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        service.deleteById(id);
    }
}
