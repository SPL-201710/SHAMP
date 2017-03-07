package co.edu.uniandes.shamp.dto;

import java.util.Date;

public class Successful {

    private Object data;
    private String message;
    private long timeStamp;
    private String title;

    public Successful() {
        this.timeStamp = new Date().getTime();
    }

    public Object getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public String getTitle() {
        return this.title;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setTimeStamp(final long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}