package com.epam.pharmacy.logic.validator;

public interface Validator<T> {

    boolean isValid(T obj);
}
