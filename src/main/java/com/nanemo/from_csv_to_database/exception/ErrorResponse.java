package com.nanemo.from_csv_to_database.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String path;

}
