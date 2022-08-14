package com.wkk.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/3/8
 */
public class DpgRequest {
    @JsonProperty("OperatorID")
    private String operatorID;
    @JsonProperty("Data")
    private String data;
    @JsonProperty("TimeStamp")
    private String timeStamp;
    @JsonProperty("Seq")
    private String seq;
    @JsonProperty("Sig")
    private String sig;

    public DpgRequest() {
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    @JsonIgnore
    public String getRequestId() {
        return this.timeStamp + this.seq;
    }

    @Override
    public String toString() {
        return "DpgRequest{" +
                "operatorID='" + operatorID + '\'' +
                ", data='" + data + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", seq='" + seq + '\'' +
                ", sig='" + sig + '\'' +
                '}';
    }
}
