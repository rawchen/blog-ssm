package com.yoyling.domain;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private Integer id;

    private String ua;

    private String browserName;

    private String osName;

    private String apiPath;

    private String ip;

    private String referer;

    private Date accessTime;

    private Integer contentId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua == null ? null : ua.trim();
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName == null ? null : browserName.trim();
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName == null ? null : osName.trim();
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath == null ? null : apiPath.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer == null ? null : referer.trim();
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", ua='" + ua + '\'' +
                ", browserName='" + browserName + '\'' +
                ", osName='" + osName + '\'' +
                ", apiPath='" + apiPath + '\'' +
                ", ip='" + ip + '\'' +
                ", referer='" + referer + '\'' +
                ", accessTime=" + accessTime +
                ", contentId=" + contentId +
                '}';
    }
}