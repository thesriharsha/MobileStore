package com.mobileshop.controller;

import com.mobileshop.entities.Model;
import com.mobileshop.entities.Purchase;
import com.mobileshop.repos.ModelRepo;
import com.mobileshop.repos.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseRepo purchaseRepo;

    @GetMapping("/all/purchase")
    public List<Purchase> getPurchase()
    {
        return purchaseRepo.findAll();
    }

    @PostMapping("/add/purchase")
    public String addPurchase(@RequestBody Purchase purchase)
    {
        purchaseRepo.save(purchase);
        return purchase.getCustomerName();
    }

    @PutMapping("update/customername")
    public Purchase updatePurchase(@RequestParam() int id, @RequestParam() String name)
    {
        var s = purchaseRepo.findById(id);
        if(s.isPresent())
        {
            var purchase = s.get();
            purchase.setCustomerName(name);
            purchaseRepo.save(purchase);
            return purchase;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Purchase found with the given id");
    }

    @DeleteMapping("delete/purchase")
    public String deletePurchase(@RequestParam() int id)
    {
        if(purchaseRepo.findById(id).isPresent())
        {
            purchaseRepo.deleteById(id);
            return "Purchase Deleted Successfully";
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No Purchase found with the given id");
    }
}
