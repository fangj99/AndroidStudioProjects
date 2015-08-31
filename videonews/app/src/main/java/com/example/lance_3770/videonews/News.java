package com.example.lance_3770.videonews;

/**
 * Created by lance-3770 on 7/5/2015.
 */
public class News {

    private Integer id;
    private String title;
    private Integer timelength;

    public News(Integer id, String title, Integer timelength) {
        this.id = id;
        this.title = title;
        this.timelength = timelength;
    }

    public News() {
    }

    public Integer getTimelength() {
        return timelength;
    }

    public void setTimelength(Integer timelength) {
        this.timelength = timelength;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




}
