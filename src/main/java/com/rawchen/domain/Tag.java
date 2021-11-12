package com.rawchen.domain;

import java.io.Serializable;

public class Tag implements Serializable {
    private Integer tid;

    private String name;

    private Integer count;

    private static final long serialVersionUID = 1L;

    public Tag(Integer tid, String name, Integer count) {
        this.tid = tid;
        this.name = name;
        this.count = count;
    }

    public Tag(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public Tag(){
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}