package com.nanemo.from_csv_to_database.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TableNotFoundException extends RuntimeException {

    public TableNotFoundException(String message) {
        super(message);
    }

}
