package com.epam.pharmacy.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> getAll();

    Optional<T> getById(Long id);

    void add(T element);

    void delete(T element);
}
