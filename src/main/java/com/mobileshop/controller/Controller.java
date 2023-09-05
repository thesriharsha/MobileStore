package com.mobileshop.controller;

import com.mobileshop.service.BillCalculationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private BillCalculationServiceImp billCalculationServiceImp;

    @GetMapping("/Generated-BillValue")
//    @PreAuthorize("Admin")
    public double getBill(@RequestParam int modelId, @RequestParam int shopId)
    {
        return billCalculationServiceImp.finalPrice(modelId,shopId);
    }
    }



