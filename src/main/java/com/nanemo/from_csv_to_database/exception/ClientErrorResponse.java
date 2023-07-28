package com.nanemo.from_csv_to_database.exception;

import java.time.LocalDateTime;

public class ClientErrorResponse extends ErrorResponse {
    public ClientErrorResponse(LocalDateTime timestamp, String message, String path) {
        super(timestamp, message, path);
    }
}
