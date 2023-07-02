package com.nanemo.from_csv_to_database.exception;

public class ValidateFileNameException extends Exception {
    public ValidateFileNameException() {
    }

    public ValidateFileNameException(String message) {
        super(message);
    }

    public ValidateFileNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateFileNameException(Throwable cause) {
        super(cause);
    }

}
