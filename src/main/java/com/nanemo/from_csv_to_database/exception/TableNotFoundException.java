package com.nanemo.from_csv_to_database.exception;

public class TableNotFoundException extends RuntimeException {
    private final int code;

    public TableNotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
