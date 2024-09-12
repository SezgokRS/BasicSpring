package com.simple_form.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TextModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String header;
    String text;
    LocalDateTime timeAdded;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }
}
