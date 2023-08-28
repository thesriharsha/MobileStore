package com.mobileshop.controller;

import com.mobileshop.entities.Model;
import com.mobileshop.repos.*;
import com.mobileshop.service.BillCalculationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Controller {

    @Autowired
    private BillCalculationServiceImp billCalculationServiceImp;

    @GetMapping("/Generated-BillValue")
    public double getBill(@RequestParam int modelId, @RequestParam int shopId)
    {
        return billCalculationServiceImp.finalPrice(modelId,shopId);
    }
//
//    @Autowired
//    private ShopRepo shopRepo;
//    @Autowired
//    private ShopBrandRepo shopBrandRepo;
//    @Autowired
//    private BrandRepo brandRepo;
//    @Autowired
//    private PurchaseRepo purchaseRepo;
//    @Autowired
//    private ModelRepo modelRepo;
//
//    @GetMapping("/mobilePrice")
//    public double finalPrice(@RequestParam int modelId,@RequestParam int shopId)
//    {
//        Model model = modelRepo.findById(modelId).orElse(null);
//        if(model == null)
//        {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Model not Available");
//        }
//        double modelPrice = model.getPrice();
//        int brand = model.getBrand().getId();
//
//        double brandProfit = shopBrandRepo.getProfit(shopId,brand);
//        double shopProfit = shopRepo.getProfit(shopId);
//
//        return (modelPrice*(1+(brandProfit/100)))*(1+(shopProfit/100));
//
//
//    }
//
////    @GetMapping("brandprofit")
////    public double brandProfit(@RequestParam int brandId, @RequestParam int shopId)
////    {
////        return shopBrandRepo.getProfit(shopId,brandId);
////    }
//
////    @GetMapping("shopprofits")
////    public Double shopProfit(@RequestParam int shopId)
////    {
////
////        var c =  shopRepo.findProfitPerById(shopId);
////        if(c.isPresent())
////        {
////            return  c.get();
////        }
////        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Shop not present with the given id");
    }
//
//}


