package com.epam.pharmacy.logic.exception;

import com.epam.pharmacy.logic.exception.ServiceException;

public class PageNotFoundException extends ServiceException {

    public PageNotFoundException() {
        super();
    }

    public PageNotFoundException(String message) {
        super(message);
    }

    public PageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageNotFoundException(Throwable cause) {
        super(cause);
    }
}
