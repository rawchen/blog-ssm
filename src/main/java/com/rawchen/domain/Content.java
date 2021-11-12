package com.rawchen.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Content implements Serializable {
    private Integer cid;

    private Integer cgid;

    private String categoryName;

    private String categorySlug;

    private int CommentCount;

    private String title;

    private String slug;

    private Date createdTime;

    private Date modifiedTime;

    private Integer contentOrder;

    private Integer authorId;

    private String contentType;

    private String contentStatus;

    private String password;

    private Integer views;

    private String tagList;

    private String contentText;

    private String thumb;

    private String description;

    private List<Tag> tList;

    private static final long serialVersionUID = 1L;

    public int getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(int commentCount) {
        CommentCount = commentCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCgid() {
        return cgid;
    }

    public void setCgid(Integer cgid) {
        this.cgid = cgid;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(Integer contentOrder) {
        this.contentOrder = contentOrder;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus == null ? null : contentStatus.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList == null ? null : tagList.trim();
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public List<Tag> gettList() {
        return tList;
    }

    public void settList(List<Tag> tList) {
        this.tList = tList;
    }

    @Override
    public String toString() {
        return "Content{" +
                "cid=" + cid +
                ", cgid=" + cgid +
                ", categoryName='" + categoryName + '\'' +
                ", categorySlug='" + categorySlug + '\'' +
                ", CommentCount=" + CommentCount +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", contentOrder=" + contentOrder +
                ", authorId=" + authorId +
                ", contentType='" + contentType + '\'' +
                ", contentStatus='" + contentStatus + '\'' +
                ", password='" + password + '\'' +
                ", views=" + views +
                ", tagList='" + tagList + '\'' +
                ", contentText='" + contentText + '\'' +
                ", thumb='" + thumb + '\'' +
                ", description='" + description + '\'' +
                ", tList=" + tList +
                '}';
    }
}