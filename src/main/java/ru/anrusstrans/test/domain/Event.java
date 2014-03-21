package ru.anrusstrans.test.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 11.03.14.
 */
@Entity
@Table(name = "EVENTS")
public class Event {
    @Id
    @GeneratedValue
//    @GeneratedValue(generator = "native")
//    @Column(name = "event_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "event_date")
    private Date date;

    private Set participants = new HashSet();

    public Set getParticipants() {
        return participants;
    }

    public void setParticipants(Set participants) {
        this.participants = participants;
    }

    public Event() {
    }

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

//    @Override
//    public int hashCode() {
//        System.out.println("Enter hashCode " + getTitle());
//        return (getId().hashCode() + getTitle().hashCode());
//    }
}
