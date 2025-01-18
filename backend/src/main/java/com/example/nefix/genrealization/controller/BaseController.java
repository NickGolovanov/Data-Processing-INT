package com.example.nefix.genrealization.controller;

import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import com.example.nefix.genrealization.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<E, ID> implements ControllerBase<E, ID>
{
    protected final BaseService<E, ID> service;

    protected BaseController(BaseService<E, ID> service)
    {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<E>>> getAll()
    {
        try
        {
            List<E> entities = service.findAll();

            if (entities.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(
                    new ApiResponse<>(entities, null)
            );
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Entity not found: " + e.getMessage())));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<E>> getById(@PathVariable ID id)
    {
        E entity = service.findById(id).orElse(null);

        if (entity == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, new ErrorResponse("Entity not found")));
        }

        return ResponseEntity.ok(new ApiResponse<>(entity, null));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<E>> create(@Valid @RequestBody E entity)
    {
        try
        {
            E savedEntity = service.save(entity);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(savedEntity, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<E>> update(@PathVariable ID id, @Valid @RequestBody E entity)
    {
        try
        {
            E updatedEntity = service.update(id, entity);

            return ResponseEntity.ok(new ApiResponse<>(updatedEntity, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, new ErrorResponse("Entity not found or error updating entity: " + e.getMessage())));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable ID id)
    {
        try
        {
            service.deleteById(id);

            return ResponseEntity.noContent().build();
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, new ErrorResponse("Entity not found or error deleting entity: " + e.getMessage())));
        }
    }
}
