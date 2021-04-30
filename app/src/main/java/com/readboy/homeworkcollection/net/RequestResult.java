package com.readboy.homeworkcollection.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestResult implements Serializable {
    private int F_responseNo;
    private String F_responseMsg;
    private String F_imgs;

    public int getF_responseNo() {
        return F_responseNo;
    }

    public void setF_responseNo(int f_responseNo) {
        F_responseNo = f_responseNo;
    }

    public String getF_responseMsg() {
        return F_responseMsg;
    }

    public void setF_responseMsg(String f_responseMsg) {
        F_responseMsg = f_responseMsg;
    }

    public String getF_imgs() {
        return F_imgs;
    }

    public void setF_imgs(String f_imgs) {
        F_imgs = f_imgs;
    }

    @Override
    public String toString() {
        return "RequestResult{" +
                "F_responseNo=" + F_responseNo +
                ", F_responseMsg='" + F_responseMsg + '\'' +
                ", F_imgs='" + F_imgs + '\'' +
                '}';
    }
}
