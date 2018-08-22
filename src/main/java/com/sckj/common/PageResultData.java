package com.sckj.common;

import com.sckj.enums.ResultStatusEnum;

import java.util.List;

public class PageResultData {
    private List<Object> datas;
    private String status = ResultStatusEnum.SUCESS.toString();
    private String message = "分页查询成功";
    private int totalPage;
    private int currentPage;

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
