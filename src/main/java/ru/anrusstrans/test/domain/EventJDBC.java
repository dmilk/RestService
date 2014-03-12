package ru.anrusstrans.test.domain;

/**
 * Created by 1 on 12.03.14.
 */
public class EventJDBC {
    private Long id;

    private String description;

    public EventJDBC() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
