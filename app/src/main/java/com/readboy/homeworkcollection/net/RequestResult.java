package com.readboy.homeworkcollection.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestResult implements Serializable {
    @SerializedName("F_data")
    private Object data;

    @SerializedName("F_responseMsg")
    private String responseMessage;

    @SerializedName("F_responseNo")
    private String responseNumber;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseNumber() {
        return responseNumber;
    }

    public void setResponseNumber(String responseNumber) {
        this.responseNumber = responseNumber;
    }
}
