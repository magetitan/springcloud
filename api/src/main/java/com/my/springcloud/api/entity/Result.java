package com.my.springcloud.api.entity;
/**
 * 
 * @author zgp
 *
 * @param <T>
 */
public class Result<T> {
	/**
	 * 状态 0：成功 
	 */
    public String status;
    public String message;
    public T data;
    public static <E> Result<E> success() {
        return success(null);
    }
    public static <E> Result<E> success(String message) {
        return success(message, null);
    }
    public static <E> Result<E> success(String message,E data){
        return new Result<E>("0", message, data);
    }
    public static<E> Result<E> failed(String message){
        return new Result<E>("1",message);
    }
    public static<E> Result<E> failed(String message,E data){
        return new Result<E>("1",message,data);
    }

    public Result() {
        
    }
    
    public Result(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
    public Result(String status, String message, T data) {
        super();
        this.status = status;
        this.message = message;
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
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
