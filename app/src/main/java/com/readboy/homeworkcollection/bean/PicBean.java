package com.readboy.homeworkcollection.bean;

public class PicBean {
    private String path;
    private boolean choose;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    @Override
    public String toString() {
        return "PicBean{" +
                "path='" + path + '\'' +
                ", choose=" + choose +
                '}';
    }
}
