package com.suka.springboot.superstudent.exception;

// Custom ExceptionResponse JSON entity, basically like the Student entity
public class ExceptionResponse {

    private int status;
    private long timeStamp;
    private String message;

    public ExceptionResponse() {}

    public ExceptionResponse(int status, long timestamp, String message) {
        this.status = status;
        this.timeStamp = timestamp;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
