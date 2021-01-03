package com.yoyling.domain;

import java.io.Serializable;

public class Category implements Serializable {
    private Integer cgid;

    private String cgName;

    private String cgSlug;

    private static final long serialVersionUID = 1L;

    public Integer getCgid() {
        return cgid;
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
}