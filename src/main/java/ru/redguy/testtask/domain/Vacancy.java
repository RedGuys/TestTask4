package ru.redguy.testtask.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long created;

    private String name;
    private String description;
    private String position;
    private int salary;
    private String experience;
    private String city;

    public Vacancy() {
        created = Instant.now().getEpochSecond();
    }

    public Vacancy(String name, String description, String position, int salary, String experience, String city) {
        this();
        this.name = name;
        this.description = description;
        this.position = position;
        this.salary = salary;
        this.experience = experience;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public Instant getCreated() {
        return Instant.ofEpochSecond(created);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public String getExperience() {
        return experience;
    }

    public String getCity() {
        return city;
    }
}
