package com.mockproject.group3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    UNKNOWN_ERROR(-1, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    OUT_OF_ERROR_HANDLE(-2, "Unknow Error Code", HttpStatus.BAD_REQUEST),
    METHOD_UNSUPPORTED(-3, "This method type is unsupport", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    UNAUTHORIZE(101, "Require login first", HttpStatus.UNAUTHORIZED),
    CATEGORY_NOTFOUND(102, "No category found", HttpStatus.BAD_REQUEST),
    INSTRUCTOR_NOTFOUND(103, "Invalid instructor. Please ensure you have instructor role", HttpStatus.BAD_REQUEST),
    INVALID_ID(104, "Invalid id, id cannot contains any character...", HttpStatus.BAD_REQUEST),
    COURSE_NOTFOUND(105, "Course not found", HttpStatus.BAD_REQUEST),
    ACTION_NOT_ALLOW(106, "You cannot access it", HttpStatus.FORBIDDEN),
    INCORRECT_PRICE(107, "Price cannot less than {value}", HttpStatus.BAD_REQUEST),
    INVALID_PAGE(108, "Page cannot less than {value}",
            HttpStatus.BAD_REQUEST),
    INVALID_PAGESIZE(109, "Page size must be {in}", HttpStatus.BAD_REQUEST),
    INVALID_PARAM(110, "Param {name} cannot accept. Expected: {regexp}", HttpStatus.BAD_REQUEST),
    SUBMIT_COURSE_FAIL(111, "Course cannot submit. Check status...", HttpStatus.BAD_REQUEST),
    FIELD_REQUIRED(112, "Field {name} is required.", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE(113, "Your fee is not enough.", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

}
