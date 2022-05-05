package com.example.real_store.until;

import lombok.Data;

import java.io.Serializable;


@Data
public class JsonResult<E> implements Serializable {

//    状态码
    private Integer state;

    private String message;

    private E data;

    public JsonResult(){
        super();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }

    public JsonResult(Throwable e){
        super();
        this.message =e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }
}
