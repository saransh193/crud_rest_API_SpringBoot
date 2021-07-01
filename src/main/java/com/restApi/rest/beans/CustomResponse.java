package com.restApi.rest.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomResponse {
    private String response;
    private int status;
    private String message;

    public CustomResponse(String response, int status, String message) {
        this.response = response;
        this.status = status;
        this.message = message;
    }
}
