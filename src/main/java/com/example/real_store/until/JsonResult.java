package com.example.real_store.until;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {

//    状态码
    private Integer state;

    private String massage;

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
        this.massage=e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }
}
