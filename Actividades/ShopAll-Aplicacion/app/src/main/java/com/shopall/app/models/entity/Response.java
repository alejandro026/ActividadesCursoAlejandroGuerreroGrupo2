package com.shopall.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4105336149302869605L;

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private boolean success;


    @Getter @Setter
    private String message;

    private transient List<T> list = null;

    @Getter @Setter
    private T data;

    public List<T> getList() {
        List<T> tmp = null;
        if(this.list !=null) {
            tmp =new ArrayList<T>(this.list);
        }
        return tmp;
    }

    public void setList(List<T> tmp) {
        if(tmp != null) {
            this.list = new ArrayList<>(tmp);
        }
    }

}