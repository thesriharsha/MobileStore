package com.mobileshop.service;

import com.mobileshop.entities.Shop;

import java.util.List;

public interface ShopService {

    public Shop addShop(Shop shop);

    public List<Shop> getAllShops();

    public Shop getShopById(int id);
}
