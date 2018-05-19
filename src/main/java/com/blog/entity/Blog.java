package com.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Blog implements Serializable {

	private int id;

	private String title;

	private String summary;

	private Date releaseDate;

	private int clickHit;

	private int replyHit;

	private String content;

	private int typeId;

    private String contentNoTag;

    private String releaseDateStr;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getClickHit() {
        return clickHit;
    }

    public int getReplyHit() {
        return replyHit;
    }

    public String getContent() {
        return content;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setClickHit(int clickHit) {
        this.clickHit = clickHit;
    }

    public void setReplyHit(int replyHit) {
        this.replyHit = replyHit;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }
}
