package org.xy.mysqldemo.persistence.service;

import com.google.common.collect.Lists;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.xy.mysqldemo.persistence.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author wangxinyu
 * @date 2020/12/2
 */
public abstract class BaseService<T extends Persistable<ID>, ID> {

    private BaseRepository<T, ID> repository;

    public BaseService(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public long count() {
        return repository.count();
    }

    public long count(Predicate predicate) {
        return repository.count(predicate);
    }

    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Transactional
    public void deleteAll(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<T> findAll(Predicate predicate) {
        return Lists.newArrayList(repository.findAll(predicate));
    }

    public List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        return Lists.newArrayList(repository.findAll(predicate, orders));
    }

    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    public List<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    public Optional<T> findOptionalOne(ID id) {
        return repository.findById(id);
    }

    public Optional<T> findOptionalOne(Predicate predicate) {
        return repository.findOne(predicate);
    }

    public T findOne(ID id) {
        return repository.findById(id).orElse(null);
    }

    public T findOne(Predicate predicate) {
        return repository.findOne(predicate).orElse(null);
    }

    public void flush() {
        repository.flush();
    }

    @Transactional
    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }

    @Transactional
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    @Transactional
    public T saveAndFlush(T entity) {
        return repository.saveAndFlush(entity);
    }

}
