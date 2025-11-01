package com.swappie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String message;
    private String errorCode;
    private T data;
    private final Instant timestamp = Instant.now();
    private String meta;
}
