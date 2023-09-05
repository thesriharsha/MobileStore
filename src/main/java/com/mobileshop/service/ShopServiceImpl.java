package com.mobileshop.service;

import com.mobileshop.custom.exception.EmptyInputException;
import com.mobileshop.custom.exception.IdNotFoundException;
import com.mobileshop.entities.Shop;
import com.mobileshop.repos.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepo shopRepo;

    @Override
    public Shop addShop(Shop shop) {


            if (shop.getName().isEmpty()) {
                throw new EmptyInputException("101", "Please send proper name");
            }
        try {
            shopRepo.save(shop);
            return shop;
            }
        catch (IllegalArgumentException e){
            throw new EmptyInputException("102","given shop details are not sufficient"+ e.getMessage());
        }
        catch (Exception e)
        {
            throw new EmptyInputException("103","Something is wrong"+e.getMessage());
        }

    }

    @Override
    public List<Shop> getAllShops() {

        List<Shop> shops;
        try {
            shops = shopRepo.findAll();
        } catch (Exception e) {
            throw new EmptyInputException("105", "Something is wrong" + e.getMessage());
        }
        if (shops.isEmpty()) {
            throw new EmptyInputException("104", "There are no shops to display");
        }
        return shops;

    }

    @Override
    public Shop getShopById(int id) {
        try {
            return shopRepo.findById(id).get();
        }
        catch (NoSuchElementException e)
        {
            throw new IdNotFoundException("107","Given shop id doesn't exists"+e.getMessage());
        }
        catch (IllegalArgumentException e) {
            throw new EmptyInputException("106", "Given shop id is null, please send a integer" +e.getMessage());
        }

    }
}
