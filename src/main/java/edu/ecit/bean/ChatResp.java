package edu.ecit.bean;

import java.io.Serializable;

/**
 * 聊天回复
 */
public class ChatResp implements Serializable{

    private String msg;
    private String from;

    public ChatResp() {

    }

    public ChatResp(String msg, String from) {
        this.msg = msg;
        this.from = from;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
