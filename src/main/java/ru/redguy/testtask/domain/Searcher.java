package ru.redguy.testtask.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Searcher {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String fio;
    private String city;
    private String position;

    public Searcher() {
    }

    public Searcher(String email, String fio, String city, String position) {
        this.email = email;
        this.fio = fio;
        this.city = city;
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }
}
