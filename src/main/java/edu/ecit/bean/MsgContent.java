package edu.ecit.bean;

import java.io.Serializable;
import java.util.Date;


public class MsgContent implements Serializable{

    //消息ID
    private Long id;
    //信息
    private String message;
    //标题
    private String title;
    //创建时间
    private Date createDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
