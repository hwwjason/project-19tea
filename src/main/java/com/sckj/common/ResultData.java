package com.sckj.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sckj.enums.ResultStatusEnum;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ResultData {
    private Object data;
    private String status = ResultStatusEnum.SUCESS.toString();
    private String message = "成功";
    private String path;

    public ResultData(Object data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ResultData(Object data) {
        this.data = data;
    }

    public ResultData() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
