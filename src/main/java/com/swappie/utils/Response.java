package com.swappie.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String message;
    private HttpStatus status;
    private String errorCode;
    private T data;
}