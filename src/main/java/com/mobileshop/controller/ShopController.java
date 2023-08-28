package com.mobileshop.controller;

import com.mobileshop.entities.Shop;
import com.mobileshop.repos.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopRepo shopRepo;

    @GetMapping("/all/shops")
    public List<Shop> getShops()
    {
        return shopRepo.findAll();
    }

    @PostMapping("/add/shop")
    public String addShop(@RequestBody Shop shop)
    {
        shopRepo.save(shop);
        return shop.getName();
    }

    @PutMapping("update/shopprofit")
    public Shop updateShop(@RequestParam() int id, @RequestParam() double profit)
    {
        var s = shopRepo.findById(id);
        if(s.isPresent())
        {
            var shop = s.get();
            shop.setProfitPer(profit);
            shopRepo.save(shop);
            return shop;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Shop found with the given id");
    }

    @DeleteMapping("delete/shop")
    public String deleteShop(@RequestParam() int id)
    {
        if(shopRepo.findById(id).isPresent())
        {
            shopRepo.deleteById(id);
            return "Shop Deleted Successfully";
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No Shop found with the given id");
    }



}
