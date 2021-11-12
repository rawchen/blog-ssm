package com.rawchen.domain;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Integer coid;

    private Integer cid;

    private Date created;

    private String createdDisplay;

    private String author;

    private Integer authorid;

    private String mail;

    private String url;

    private String ip;

    private Integer parent;

    private String agent;

    private String text;

    private String parentNickName;

    private String contentSlug;

    private static final long serialVersionUID = 1L;

    public String getContentSlug() {
        return contentSlug;
    }

    public void setContentSlug(String contentSlug) {
        this.contentSlug = contentSlug;
    }

    public Integer getCoid() {
        return coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedDisplay() {
        return createdDisplay;
    }

    public void setCreatedDisplay(String createdDisplay) {
        this.createdDisplay = createdDisplay;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getParentNickName() {
        return parentNickName;
    }

    public void setParentNickName(String parentNickName) {
        this.parentNickName = parentNickName;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "coid=" + coid +
                ", cid=" + cid +
                ", created=" + created +
                ", createdDisplay='" + createdDisplay + '\'' +
                ", author='" + author + '\'' +
                ", authorid=" + authorid +
                ", mail='" + mail + '\'' +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", parent=" + parent +
                ", agent='" + agent + '\'' +
                ", text='" + text + '\'' +
                ", parentNickName='" + parentNickName + '\'' +
                ", contentSlug='" + contentSlug + '\'' +
                '}';
    }
}