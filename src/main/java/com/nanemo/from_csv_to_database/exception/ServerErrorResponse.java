package com.nanemo.from_csv_to_database.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ServerErrorResponse extends ErrorResponse {
    private int status;
    private String error;
    private String method;
    private Map<String, String> headers;

    public ServerErrorResponse(LocalDateTime timestamp,
                               String message,
                               String path,
                               int status,
                               String error,
                               String method,
                               Map<String, String> headers) {
        super(timestamp, message, path);
        this.status = status;
        this.error = error;
        this.method = method;
        this.headers = headers;
    }
}
