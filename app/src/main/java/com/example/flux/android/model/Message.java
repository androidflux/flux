package com.example.flux.android.model;

/**
 * Created by ntop on 18/12/15.
 */
public class Message {
    private String mText;

    public Message(){}

    public Message(String text) {
        mText = text;
    }

    public String getMessage() {
        return mText;
    }

    public void setMessage(String text) {
        mText = text;
    }
}
