package com.mobileshop.controller;

import com.mobileshop.entities.Shop;
import com.mobileshop.entities.Shopbrands;
import com.mobileshop.repos.ShopBrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ShopBrandController {

    @Autowired
    private ShopBrandRepo shopBrandRepo;

    @GetMapping("/all/shopbrands")
    public List<Shopbrands> getShopBrands()
    {
        return shopBrandRepo.findAll();
    }

    @PostMapping("/add/shopbrand")
    public Shopbrands addShopBrand(@RequestBody Shopbrands shopbrands)
    {
        shopBrandRepo.save(shopbrands);
        return shopbrands;
    }

    @PutMapping("update/brandprofit")
    public Shopbrands updateShopBrand(@RequestParam() int id, @RequestParam() double profit)
    {
        var s = shopBrandRepo.findById(id);
        if(s.isPresent())
        {
            var shopbrands = s.get();
            shopbrands.setProfitPer(profit);
            shopBrandRepo.save(shopbrands);
            return shopbrands;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No ShopBrand found with the given id");
    }

    @DeleteMapping("delete/shopbrand")
    public String deleteShop(@RequestParam() int id)
    {
        if(shopBrandRepo.findById(id).isPresent())
        {
            shopBrandRepo.deleteById(id);
            return "ShopBrand Deleted Successfully";
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No ShopBrand found with the given id");
    }


}
