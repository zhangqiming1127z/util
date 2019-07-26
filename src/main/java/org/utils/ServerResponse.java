package org.utils;

import java.io.Serializable;

public class ServerResponse<T> implements Serializable {
    private static final long serialVersionUID = -5086340582215218991L;
    private int code;
    private String msg;
    private T data;

    private ServerResponse(int code, String msg, T data){
        this.code =  code;
        this.msg = msg;
        this.data = data;
    }

    public static ServerResponse success(){
        return new ServerResponse (200,"success",null);
     }
    public static <T>ServerResponse<T> success(T data){
        return new ServerResponse(200,"success",data);
    }
    public  static ServerResponse error(){
        return  new ServerResponse(500,"error",null);
    }
    public  static ServerResponse error(IEmnu responseEmnu){
        return  new ServerResponse(responseEmnu.getCode(),responseEmnu.getMsg(),null);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
