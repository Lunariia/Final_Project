package com.epam.pharmacy.dao;

import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    List<T> getAll() throws DaoException;

    Optional<T> getById(long id) throws DaoException;

    Long add(T element) throws DaoException;

    void delete(long id) throws DaoException;

}
