package com.lpu.quiz_service.model;

public class PaymentResponse {
    private String status;
    private String message;
    private String sessionId;
    private String sessionUrl;

    public PaymentResponse() {
    }

    public PaymentResponse(String status, String message, String sessionId, String sessionUrl) {
        this.status = status;
        this.message = message;
        this.sessionId = sessionId;
        this.sessionUrl = sessionUrl;
    }

    // Getters and Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionUrl() {
        return sessionUrl;
    }

    public void setSessionUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
    }

    // Static Builder method
    public static PaymentResponse create(String status, String message, String sessionId, String sessionUrl) {
        return new PaymentResponse(status, message, sessionId, sessionUrl);
    }
}
