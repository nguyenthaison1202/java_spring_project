package com.mockproject.group3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseApiResponse<T> {
    private int code;
    private String message;
    private T payload;

    public BaseApiResponse() {
    }

    public BaseApiResponse(int code, String message, T payload) {
        this.code = code;
        this.message = message;
        this.payload = payload;
    }

    public BaseApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
