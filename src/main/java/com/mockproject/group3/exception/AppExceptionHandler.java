package com.mockproject.group3.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mockproject.group3.dto.response.BaseApiResponse;

import jakarta.validation.ConstraintViolation;

@ControllerAdvice
public class AppExceptionHandler {
    private final static String MAX_ATTRIBUTE = "max";
    private final static String MIN_ATTRIBUTE = "min";
    private final static String SIZE_ATTRIBUTE = "size";
    private final static String VALUE_ATTRIBUTE = "value";
    private final static String ARRAY_ATTRIBUTE = "in";
    private final static String REGEXP_ATTRIBUTE = "regexp";
    private final static String NAME_ATTRIBUTE = "name";

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<BaseApiResponse<Void>> handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.badRequest()
                .body(new BaseApiResponse<>(errorCode.getStatusCode().value(), errorCode.getMessage()));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<BaseApiResponse<Void>> handlingAccessDeniedException(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZE;
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(new BaseApiResponse<>(errorCode.getCode(), errorCode.getMessage()));
    }

    // @SuppressWarnings("unchecked")
    // @ExceptionHandler(value = MethodArgumentNotValidException.class)
    // public ResponseEntity<BaseApiResponse<Void>>
    // handlingMethodArgumentNotValid(MethodArgumentNotValidException e) {
    // String key = e.getFieldError().getDefaultMessage();
    // ErrorCode errorCode;
    // Map<String, Object> attr = new HashMap<>();
    // System.out.println("KEY GOT: " + key);
    // String fieldErrorName = "";
    // try {
    // errorCode = ErrorCode.valueOf(key);
    // var constraintViolation =
    // e.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
    // attr = constraintViolation.getConstraintDescriptor().getAttributes();
    // fieldErrorName = constraintViolation.getPropertyPath().toString();
    // System.out.println(attr.toString());
    // } catch (IllegalArgumentException exc) {
    // errorCode = ErrorCode.OUT_OF_ERROR_HANDLE;
    // exc.printStackTrace();
    // }

    // String message = Objects.nonNull(attr) ?
    // mappingWithAttr(errorCode.getMessage(), fieldErrorName, attr)
    // : errorCode.getMessage();
    // return ResponseEntity.status(errorCode.getStatusCode())
    // .body(new BaseApiResponse<>(errorCode.getCode(), message));
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<BaseApiResponse<Void>> handlingUnsupportMethodException(
            HttpRequestMethodNotSupportedException e) {
        ErrorCode errorCode = ErrorCode.METHOD_UNSUPPORTED;
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(new BaseApiResponse<>(errorCode.getCode(), errorCode.getMessage()));
    }

    private String mappingWithAttr(String message, String fieldErrorName, Map<String, Object> attr) {
        String minValue = String.valueOf(attr.get(MIN_ATTRIBUTE));
        String maxValue = String.valueOf(attr.get(MAX_ATTRIBUTE));
        String sizeValue = String.valueOf(attr.get(SIZE_ATTRIBUTE));
        String valValue = String.valueOf(attr.get(VALUE_ATTRIBUTE));
        String regexpValue = String.valueOf(attr.get(REGEXP_ATTRIBUTE));
        String arrValue = "";
        if (attr.get(ARRAY_ATTRIBUTE) instanceof String[]) {
            arrValue = Arrays.toString((String[]) attr.get(ARRAY_ATTRIBUTE));
        } else if (attr.get(ARRAY_ATTRIBUTE) instanceof int[]) {
            arrValue = Arrays.toString((int[]) attr.get(ARRAY_ATTRIBUTE));
        }

        String format = "{%s}";

        message = message.replace(applyFormat(format, MIN_ATTRIBUTE), minValue);
        message = message.replace(applyFormat(format, MAX_ATTRIBUTE), maxValue);
        message = message.replace(applyFormat(format, SIZE_ATTRIBUTE), sizeValue);
        message = message.replace(applyFormat(format, VALUE_ATTRIBUTE), valValue);
        message = message.replace(applyFormat(format, ARRAY_ATTRIBUTE), arrValue);
        message = message.replace(applyFormat(format, REGEXP_ATTRIBUTE), regexpValue);
        message = message.replace(applyFormat(format, NAME_ATTRIBUTE), fieldErrorName);
        return message;
    }

    private String applyFormat(String format, String value) {
        return String.format(format, value);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseApiResponse<Void>> handlingOrtherException(Exception e) {
        ErrorCode errorCode = ErrorCode.UNKNOWN_ERROR;
        e.printStackTrace();
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(new BaseApiResponse<>(errorCode.getCode(), errorCode.getMessage()));
    }
}
