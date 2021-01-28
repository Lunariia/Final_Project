package com.epam.pharmacy.dao;

import com.epam.pharmacy.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> extends AutoCloseable {

    List<T> getAll() throws DaoException;

    Optional<T> getById(long id) throws DaoException;

    Long add(T element) throws DaoException;

    void delete(long id) throws DaoException;

    @Override
    void close() throws Exception;
}
