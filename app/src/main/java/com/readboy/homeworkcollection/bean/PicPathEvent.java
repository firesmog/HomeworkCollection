package com.readboy.homeworkcollection.bean;

public class PicPathEvent {
    private String path;
    private int eventType;


    public PicPathEvent() {
    }

    public PicPathEvent(String path, int eventType) {
        this.path = path;
        this.eventType = eventType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "PicPathEvent{" +
                "path='" + path + '\'' +
                ", eventType=" + eventType +
                '}';
    }
}
