package com.mobileshop.controller;

import com.mobileshop.service.BillCalculationServiceImp;
import com.mobileshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private BillCalculationServiceImp billCalculationServiceImp;

    @Autowired
    private StorageService storageService;

    @GetMapping("/Generated-BillValue")
//    @PreAuthorize("Admin")
    public double getBill(@RequestParam int modelId, @RequestParam int shopId)
    {
        return billCalculationServiceImp.finalPrice(modelId,shopId);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImageFile(@RequestParam("image")MultipartFile file) throws IOException{
        String uploadImage = storageService.uploadImagetoFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);

    }

    @GetMapping("/downloadImage/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException{
        byte[] imageData =storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png")).body(imageData);
    }




    }



