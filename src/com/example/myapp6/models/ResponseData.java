package com.example.myapp6.models;

import java.io.Serializable;

/**
 * Created by pc on 2016/6/1.
 */
public class ResponseData implements Serializable {
    private String msg;
    private Boolean success;
    private String ctx;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCtx() {
        return ctx;
    }

    public void setCtx(String sada) {
        this.ctx = ctx;
    }
}
