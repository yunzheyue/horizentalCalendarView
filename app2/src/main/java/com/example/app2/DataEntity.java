package com.example.app2;

/**
 * autour : lbing
 * date : 2018/4/13 0013 09:18
 * className :
 * version : 1.0
 * description :
 */


public class DataEntity {

    private String content;
    private int currentMonth;
    private String preCount;
    private String sufCount;

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public String getPreCount() {
        return preCount == null ? "" : preCount;
    }

    public void setPreCount(String preCount) {
        this.preCount = preCount;
    }

    public String getSufCount() {
        return sufCount == null ? "" : sufCount;
    }

    public void setSufCount(String sufCount) {
        this.sufCount = sufCount;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
