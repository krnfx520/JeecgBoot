package org.jeecg.common.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultMap implements Serializable {

    public ResultMap() {
    }

    public ResultMap(Object data) {
        this.data = data;
    }

    /**
     * @param status	状态
     * @param message	提示信息
     */
    public ResultMap(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * @param status	状态
     * @param message	提示信息
     * @param data		返回的数据对象
     */
    public ResultMap(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回编号：000标识成功，其他标识失败
     */
    private String status = "000";

    /**
     * 操作描述
     */
    private String message = "操作成功！";

    /**
     * 返回的数据对象
     */
    private Object data = null;

    /**
     * 返回数据的大小
     */
    private Integer total = 0;

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 设置状态码跟返回信息
     * @param status	状态码
     * @param message	操作信息
     */
    public ResultMap set(String status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    public ResultMap set(String uuid) {
        this.uuid = uuid;
        return this;
    }



    /**
     * 设置状态码、返回信息以及返回数据
     * @param status	状态码
     * @param message	操作信息
     * @param data		返回的数据
     */
    public ResultMap set(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        return this;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        if(message.length()>200){
            message="操作异常！";
        }
        this.message = message;

    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public ResultMap setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * data为map时设置键值
     * @return the data
     */
    @SuppressWarnings("unchecked")
    public ResultMap setMapData(Object key, Object val) {
        Map<Object, Object> dataMap;
        if (data instanceof Map) {
            dataMap = (Map<Object, Object>) data;
        } else {
            dataMap = new HashMap<Object, Object>();
            data = dataMap;
        }
        dataMap.put(key, val);
        return this;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public ResultMap setTotal(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 操作成功
     * @return
     */
    @JsonIgnore
    public static ResultMap success() {
        return new ResultMap();
    }

    /**
     * 操作成功
     * @param data 结果对象
     * @return
     */
    @JsonIgnore
    public static ResultMap success(Object data) {
        return new ResultMap(data);
    }

    /**
     * 操作失败
     * @return
     */
    @JsonIgnore
    public static ResultMap fail() {
        return new ResultMap("909", "操作失败！");
    }

    /**
     * 操作失败
     * @param data 结果对象
     * @return
     */
    @JsonIgnore
    public static ResultMap fail(Object data) {
        return new ResultMap("909", "操作失败！", data);
    }

    /**
     * 操作出错
     * @return
     */
    @JsonIgnore
    public static ResultMap error() {
        return new ResultMap("999", "操作出错！");
    }

    /**
     * 操作失败
     * @param data 结果对象
     * @return
     */
    @JsonIgnore
    public static ResultMap error(Object data) {
        return new ResultMap("999", "操作出错！", data);
    }

    /**
     *  操作失败
     * @param status	状态
     * @param message	描述
     * @return
     */
    @JsonIgnore
    public static ResultMap fail(String status, String message) {
        return new ResultMap(status, message);
    }

    /**
     *  操作失败
     * @param status	状态
     * @param message	描述
     * @param data 		结果对象
     * @return
     */
    @JsonIgnore
    public static ResultMap fail(String status, String message, Object data) {
        return new ResultMap(status, message, data);
    }

    /**
     *  校验是否操作成功-状态为000
     * @return
     */
    @JsonIgnore
    public boolean isSuccess() {
        return "000".equals(status);
    }

    /**
     *  校验是否出错-状态为999
     * @return
     */
    @JsonIgnore
    public boolean isError() {
        return "999".equals(status);
    }

}
