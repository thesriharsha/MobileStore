package com.mobileshop.service;

import com.mobileshop.entities.Model;
import com.mobileshop.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


public interface BillCalculationService {

    double finalPrice(int modelId,int shopId);


}
