package com.ex.model.vo;

public class ResultVO {

    /**
     * 提示信息,给用户看的.
     */
    private long timestamp;
    /**
     * 提示码,给API调用者看的.其可根据提示码自己定义提示语.
     */
    private Integer status;

    /**
     * 提示信息,给用户看的.
     */
    private String message;


    public ResultVO() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 这是真实的数据.可能也存在错误和为空的数据
     */
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess(){
        return status == 200;
    }
}