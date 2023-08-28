package com.mobileshop.controller;

import com.mobileshop.entities.Model;
import com.mobileshop.repos.ModelRepo;
import com.mobileshop.repos.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ModelController {

    @Autowired
    private ModelRepo modelRepo;

    @GetMapping("/all/model")
    public List<Model> getModel()
    {
        return modelRepo.findAll();
    }

    @PostMapping("/add/model")
    public String addModel(@RequestBody Model model)
    {
        modelRepo.save(model);
        return model.getName();
    }

    @PutMapping("update/price")
    public Model updateModel(@RequestParam() int id, @RequestParam() double price)
    {
        var s = modelRepo.findById(id);
        if(s.isPresent())
        {
            var model = s.get();
            model.setPrice(price);
            modelRepo.save(model);
            return model;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Model found with the given id");
    }

    @DeleteMapping("delete/model")
    public String deleteModel(@RequestParam() int id)
    {
        if(modelRepo.findById(id).isPresent())
        {
            modelRepo.deleteById(id);
            return "Model Deleted Successfully";
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No Model found with the given id");
    }
}
