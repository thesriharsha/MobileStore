package com.mobileshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "shopusers")
public class Shopuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int shopId;
    private int userId;

    @ManyToOne
    @JoinColumn(name="shopId", referencedColumnName = "id",insertable = false,updatable = false)
    @JsonIgnore
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    public int getId() {
        return id;
    }


    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Shopuser() {
    }

    public Shopuser(int id, int shopId, int userId) {
        this.id = id;
        this.shopId = shopId;
        this.userId = userId;
    }
}

