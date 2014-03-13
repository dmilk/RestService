package ru.anrusstrans.test.domain;

import java.util.Date;

/**
 * Created by 1 on 11.03.14.
 */
public class Event {
    private Long id;

    private String title;
    private Date date;
    private String xxx;

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }

    public Event() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return date.toString();
    }
}
