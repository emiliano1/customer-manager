package com.test.project.service;

import com.test.project.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Component
public abstract class BaseService<T, I extends Serializable> {

    /**
     * @param pageable instance of pageable params
     * @return Page instance of T
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Page<T> getAll(Pageable pageable) {
        return Optional.of(getRepository().findAll(pageable)).orElse(null);
    }

    /**
     * @param id ID of entity
     * @return Instance of T
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public T get(I id) {
        return Optional.of(getRepository().getOne(id)).orElse(null);
    }

    /**
     * @param entity T Instance
     * @return Instance of T created
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public T create(T entity) {
        if (Optional.ofNullable(entity).isPresent()) {
            return getRepository().save(entity);
        } else {
            return null;
        }
    }

    /**
     * @param id ID entity
     * @return Instance of T updated
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public T update(I id, T entity) {
        if (Optional.ofNullable(id).isPresent()) {
            return Optional.of(getRepository().save(entity)).orElse(null);
        }
        return null;
    }

    /**
     * @param id T ID
     * @return boolean indicator if T was deleted or not
     */
    @Secured({"ROLE_ADMIN"})
    public boolean delete(I id) {
        if (Optional.ofNullable(id).isPresent()) {
            return getRepository().existsById(id);
        }
        return false;
    }

    public abstract BaseRepository<T, I> getRepository();
}
