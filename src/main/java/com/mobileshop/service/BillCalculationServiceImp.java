package com.mobileshop.service;

import com.mobileshop.entities.Model;
import com.mobileshop.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BillCalculationServiceImp implements BillCalculationService{
    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private ShopBrandRepo shopBrandRepo;
    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private PurchaseRepo purchaseRepo;
    @Autowired
    private ModelRepo modelRepo;

//    @GetMapping("/Generated-BillValue")
    @Override
    public double finalPrice(int modelId, int shopId)
    {
        Model model = modelRepo.findById(modelId).orElse(null);
        if(model == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Model not Available");
        }
        double modelPrice = model.getPrice();
        int brand = model.getBrandId();
        Double sP =   shopRepo.getProfit(shopId).orElse(null);
        if(sP == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no Shop with the given Id");
        }
        double shopProfit = sP;
        Double bP = shopBrandRepo.getProfit(shopId,brand).orElse(null);
        if(bP == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given Brand is not Available at the given Shop");
        }

        double brandProfit = bP;

        return (modelPrice*(1+(brandProfit/100)))*(1+(shopProfit/100));
    }
}

