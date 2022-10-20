package com.mockito.mock.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "arrival")
public class Arrival {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private String city;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

