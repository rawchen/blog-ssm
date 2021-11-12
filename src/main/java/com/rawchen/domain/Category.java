package com.rawchen.domain;

import java.io.Serializable;

public class Category implements Serializable {
    private Integer cgid;

    private String cgName;

    private String cgSlug;

    private int count;

    public Integer getCgid() {
        return cgid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCgid(Integer cgid) {
        this.cgid = cgid;
    }

    public String getCgName() {
        return cgName;
    }

    public void setCgName(String cgName) {
        this.cgName = cgName == null ? null : cgName.trim();
    }

    public String getCgSlug() {
        return cgSlug;
    }

    public void setCgSlug(String cgSlug) {
        this.cgSlug = cgSlug == null ? null : cgSlug.trim();
    }

    @Override
    public String toString() {
        return "Category{" +
                "cgid=" + cgid +
                ", cgName='" + cgName + '\'' +
                ", cgSlug='" + cgSlug + '\'' +
                ", count=" + count +
                '}';
    }
}