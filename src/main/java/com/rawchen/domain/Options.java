package com.rawchen.domain;

import java.io.Serializable;

public class Options implements Serializable {
    private String name;

    private String value;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    @Override
    public String toString() {
        return "Options{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}