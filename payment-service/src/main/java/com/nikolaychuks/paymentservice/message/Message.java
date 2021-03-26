package com.nikolaychuks.paymentservice.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Message<T> {

    private String type;
    private String id = UUID.randomUUID().toString(); // unique id of this message
    private Date time = new Date();
    private T data;
    private String datacontenttype = "application/json";
    private String specversion = "1.0";

    // Extension attributes
    private String traceid = UUID.randomUUID().toString(); // trace id, default: new unique
    private String correlationid; // id which can be used for correlation later if required

    public Message(String type, T payload) {
        this.type = type;
        this.data = payload;
    }

    public Message(String type, String traceid, T payload) {
        this.type = type;
        this.traceid = traceid;
        this.data = payload;
    }
}
