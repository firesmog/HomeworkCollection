package com.readboy.homeworkcollection.net;

import java.io.Serializable;

public class EBagRequestResult implements Serializable {
    private int F_responseNo;
    private int F_pwd_reset;
    private String F_responseMsg;
    private Object F_data;

    public int getF_responseNo() {
        return F_responseNo;
    }

    public void setF_responseNo(int f_responseNo) {
        F_responseNo = f_responseNo;
    }

    public int getF_pwd_reset() {
        return F_pwd_reset;
    }

    public void setF_pwd_reset(int f_pwd_reset) {
        F_pwd_reset = f_pwd_reset;
    }

    public String getF_responseMsg() {
        return F_responseMsg;
    }

    public void setF_responseMsg(String f_responseMsg) {
        F_responseMsg = f_responseMsg;
    }

    public Object getF_data() {
        return F_data;
    }

    public void setF_data(Object f_data) {
        F_data = f_data;
    }

    @Override
    public String toString() {
        return "EBagRequestResult{" +
                "F_responseNo=" + F_responseNo +
                ", F_pwd_reset=" + F_pwd_reset +
                ", F_responseMsg='" + F_responseMsg + '\'' +
                ", F_data=" + F_data +
                '}';
    }
}
