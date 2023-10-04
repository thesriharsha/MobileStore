package com.mobileshop.service;

import com.mobileshop.entities.FileData;
import com.mobileshop.repos.FileDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private FileDataRepo fileDataRepo;

    private final String Folder = "D:\\Valley\\Working with files\\";


    public String uploadImagetoFile(MultipartFile file) throws IOException{

        String filePath = Folder+file.getOriginalFilename();
        FileData fileData = fileDataRepo.save(FileData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filepath(filePath)
                .build());
        file.transferTo(new File(filePath));

        return "File uploaded Successfully : " + filePath;
    }

    public byte[] downloadImage(String filename) throws IOException {
        Optional<FileData> fileData = fileDataRepo.findByName(filename);
        String filePath = fileData.get().getFilepath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;


    }





}
