package com.sckj.common;

import java.util.List;

public class Page {
    private int start;
    private int limit;
    private List<String> condition;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


}
