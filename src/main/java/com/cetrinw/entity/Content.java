package com.cetrinw.entity;

import java.util.Date;

/**
 * Created by Cetrin Wang on 2016/4/20.
 * 抓取正文主体
 */
public class Content {
    private String id;
    private String title;
    private String content;
    private Date create_data;
    private String source;

    public Content() {
    }

    public Content(String id, String content, Date create_data, String source) {
        this.id = id;
        this.content = content;
        this.create_data = create_data;
        this.source = source;
    }

    public Content(String id, String title, String content, Date create_data, String source) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.create_data = create_data;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_data() {
        return create_data;
    }

    public void setCreate_data(Date create_data) {
        this.create_data = create_data;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
