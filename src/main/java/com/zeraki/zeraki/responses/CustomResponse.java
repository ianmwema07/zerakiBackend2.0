package com.zeraki.zeraki.responses;

public class CustomResponse {

    private String responseMessage;

    private Object responseObject;

    public CustomResponse(String responseMessage, Object responseObject) {

        this.responseMessage = responseMessage;
        this.responseObject = responseObject;
    }


    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }
}
