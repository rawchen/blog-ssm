package com.yoyling.domain;

import java.io.Serializable;

public class Contents implements Serializable {
    private Integer cid;

    private String title;

    private String slug;

    private String created;

    private String modified;

    private Integer contentsOrder;

    private Integer authorid;

    private String type;

    private String contentsStatus;

    private String contentsPassword;

    private String allowcomment;

    private Integer views;

    private String contentsText;

    private String thumb;

    private static final long serialVersionUID = 1L;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created == null ? null : created.trim();
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified == null ? null : modified.trim();
    }

    public Integer getContentsOrder() {
        return contentsOrder;
    }

    public void setContentsOrder(Integer contentsOrder) {
        this.contentsOrder = contentsOrder;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContentsStatus() {
        return contentsStatus;
    }

    public void setContentsStatus(String contentsStatus) {
        this.contentsStatus = contentsStatus == null ? null : contentsStatus.trim();
    }

    public String getContentsPassword() {
        return contentsPassword;
    }

    public void setContentsPassword(String contentsPassword) {
        this.contentsPassword = contentsPassword == null ? null : contentsPassword.trim();
    }

    public String getAllowcomment() {
        return allowcomment;
    }

    public void setAllowcomment(String allowcomment) {
        this.allowcomment = allowcomment == null ? null : allowcomment.trim();
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getContentsText() {
        return contentsText;
    }

    public void setContentsText(String contentsText) {
        this.contentsText = contentsText == null ? null : contentsText.trim();
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    @Override
    public String toString() {
        return "Contents{" +
                "cid=" + cid +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", contentsOrder=" + contentsOrder +
                ", authorid=" + authorid +
                ", type='" + type + '\'' +
                ", contentsStatus='" + contentsStatus + '\'' +
                ", contentsPassword='" + contentsPassword + '\'' +
                ", allowcomment='" + allowcomment + '\'' +
                ", views=" + views +
                ", contentsText='" + contentsText + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }
}