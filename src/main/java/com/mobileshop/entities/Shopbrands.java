package com.mobileshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="shopbrands")
public class Shopbrands {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double profitPer;
    private  int shopId;
    private int brandId;
    @ManyToOne
    @JoinColumn(name="shopId", referencedColumnName = "id",insertable = false,updatable = false)
    @JsonIgnore
    private Shop shop;

    @ManyToOne
    @JoinColumn(name="brandId", referencedColumnName = "id",insertable = false,updatable = false)
    @JsonIgnore
    private Brand brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProfitPer() {
        return profitPer;
    }

    public void setProfitPer(double profitPer) {
        this.profitPer = profitPer;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
