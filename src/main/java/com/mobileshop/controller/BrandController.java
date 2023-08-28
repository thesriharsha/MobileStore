package com.mobileshop.controller;

import com.mobileshop.entities.Brand;
import com.mobileshop.repos.BrandRepo;
import com.mobileshop.repos.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private BrandRepo brandRepo;

    @GetMapping("/all/brands")
    public List<Brand> getBrand()
    {
        return brandRepo.findAll();
    }

    @PostMapping("/add/brand")
    public String addBrandp(@RequestBody Brand brand)
    {
        brandRepo.save(brand);
        return brand.getName();
    }

    @PutMapping("update/name")
    public Brand updateBrand(@RequestParam() int id, @RequestParam() String name)
    {
        var s = brandRepo.findById(id);
        if(s.isPresent())
        {
            var brand = s.get();
            brand.setName(name);
            brandRepo.save(brand);
            return brand;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Brand found with the given id");
    }

    @DeleteMapping("delete/brand")
    public String deleteBrand(@RequestParam() int id)
    {
        if(brandRepo.findById(id).isPresent())
        {
            brandRepo.deleteById(id);
            return "Brand Deleted Successfully";
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No Brand found with the given id");
    }

}
