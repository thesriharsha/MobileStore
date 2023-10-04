package com.mobileshop.controller;

import com.mobileshop.custom.exception.ControllerException;
import com.mobileshop.custom.exception.EmptyInputException;
import com.mobileshop.custom.exception.IdNotFoundException;
import com.mobileshop.entities.Shop;
import com.mobileshop.repos.ShopRepo;
import com.mobileshop.service.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private ShopServiceImpl shopService;

    @GetMapping("/all/shops")
    public ResponseEntity<?> getShops()
    {
        try{
            var shops = shopService.getAllShops();
            return new ResponseEntity<List<Shop>>(shops,HttpStatus.OK);
        }
        catch (EmptyInputException e)
        {
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            ControllerException ce = new ControllerException("111","Something went wrong in Controller");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("shopbyId")
    public ResponseEntity<?> shopById(@RequestParam() int id)throws IdNotFoundException
    {
            var shop = shopService.getShopById(id);
            return new ResponseEntity<Shop>(shop,HttpStatus.OK);
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
