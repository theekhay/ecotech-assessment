package com.ecotech.assessment.models;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
public class ResponseModel<T> {

    private String status;
    private String message;
    private T data;

    public ResponseModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseModel(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}