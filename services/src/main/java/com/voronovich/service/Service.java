package com.voronovich.service;

import java.io.Serializable;

/**
 * Common abstract methods for every service entity layer
 *
 * @param <T> Entity
 * @author Dmitry V
 * @version 1.0
 */
public interface Service<T> {

    /**
     * Method saves or updates entity
     *
     * @param t entity
     */
    void saveOrUpdate(T t);

    /**
     * Method deletes entity
     *
     * @param t entity
     */
    void delete(T t);

    /**
     * Method reads entity
     *
     * @param id - entity id
     * @return Entity
     */
    T get(Serializable id);
}
