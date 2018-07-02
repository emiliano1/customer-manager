package com.test.project.controller;

import com.test.project.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Optional;

@Component
public abstract class BaseController<T, I extends Serializable> implements Serializable {

    private BaseService<T, I> baseService;

    @Autowired
    public BaseController(BaseService<T, I> baseService) {
        this.baseService = baseService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<?> index(@PageableDefault Pageable pageable) {
        Page<T> list = baseService.getAll(pageable);
        if (list.hasContent()) {
            return ResponseEntity.ok(list.getContent());
        }
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable I id) {
        return ResponseEntity.ok(baseService.get(id));
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody T entity, BindingResult errors)
            throws NoSuchFieldException, IllegalAccessException {
        if (errors.hasErrors()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        T savedEntity = baseService.create(entity);
        if (Optional.ofNullable(savedEntity).isPresent()) {
            Field id = savedEntity.getClass().getDeclaredField("id");
            id.setAccessible(true);
            Object value = id.get(savedEntity);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + value)
                    .buildAndExpand(value).toUri();
            return ResponseEntity.created(location).body(savedEntity);
        }
        return ResponseEntity.badRequest().body("Error trying to persist.");
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> putPatch(@PathVariable I id, @Validated @RequestBody T entity, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        T updatedEntity = baseService.update(id, entity);

        if (Optional.ofNullable(updatedEntity).isPresent()) {
            return ResponseEntity.ok(updatedEntity);
        }

        return ResponseEntity.badRequest().body("Error trying to update.");
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable I id) {

        if (Optional.ofNullable(id).isPresent() && baseService.delete(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.unprocessableEntity().build();
    }

    public abstract BaseService<T, I> getService();
}