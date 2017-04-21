package trangtrunghoangphuc.model;

import java.io.Serializable;

/**
 * Created by Administrator on 4/22/2017.
 */

public class SMS implements Serializable {
    private String sender;
    private String date;
    private String body;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public SMS() {
    }

    public SMS(String sender, String date, String body) {
        this.sender = sender;
        this.date = date;
        this.body = body;
    }
}
