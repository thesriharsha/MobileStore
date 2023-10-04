package com.mobileshop.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@Builder
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;
    private double profitPer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getProfitPer() {
        return profitPer;
    }

    public void setProfitPer(double profitPer) {
        this.profitPer = profitPer;
    }

    public Shop() {
    }

    public Shop( String name, String location, double profitPer) {
//        this.id = id;
        this.name = name;
        this.location = location;
        this.profitPer = profitPer;
    }

    public Shop( int id,String name, String location, double profitPer) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.profitPer = profitPer;
    }
}
