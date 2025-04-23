package com.github.wellls.dscommerce.services.exceptions;

public class DatabaseIntegrityException extends RuntimeException {
    public DatabaseIntegrityException(String message) {
        super(message);
    }

    public DatabaseIntegrityException() {
        super("Database exception");
    }
}
