package com.order.api.SellApi.infra;

import com.order.api.SellApi.domain.PayamentMethod;
import com.order.api.SellApi.dto.response.StandardResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.InvalidFormatException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardResponseDTO<String>> handleInvalidJson(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String message = "Read body error.";
        if (ex.getCause() instanceof InvalidFormatException ife) {
            if (ife.getTargetType().equals(PayamentMethod.class)) {
                String acceptedValues = Arrays.stream(PayamentMethod.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));
                
                message = "Invalid value to payament method. Accept values: [" + acceptedValues + "]";
            }
        }
        return buildResponseEntity(HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponseDTO<String>> handleGenericException(Exception ex, HttpServletRequest request) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }

    private ResponseEntity<StandardResponseDTO<String>> buildResponseEntity(HttpStatus status, String message, HttpServletRequest request) {
        Long startTime = (Long) request.getAttribute("startTime");
        String duration = startTime != null ? (System.currentTimeMillis() - startTime) + "ms" : "N/A";

        StandardResponseDTO<String> response = new StandardResponseDTO<>(
                status.value(),
                request.getRequestURI(),
                duration,
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(status).body(response);
    }
}
