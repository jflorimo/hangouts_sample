package com.jflorimo.ft_hangouts;

/**
 * Created by jflorimo on 1/6/16.
 */
public class Message {

    private int id;
    private String number;
    private String message;
    private int sender;

    public Message(String number, int sender, String message) {
        this.number = number;
        this.sender = sender;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }
}
