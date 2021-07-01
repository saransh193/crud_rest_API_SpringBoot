package com.restApi.rest.exceptionConfig;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String response;
    private int status;
    private String message;


    public ErrorDetails(Date timestamp,String response,int status, String message) {
        this.timestamp = timestamp;
        this.response = response;
        this.status = status;
        this.message = message;
    }
}
