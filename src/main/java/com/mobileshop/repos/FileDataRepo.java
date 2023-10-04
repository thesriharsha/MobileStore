package com.mobileshop.repos;

import com.mobileshop.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepo extends JpaRepository<FileData, Integer> {
    Optional<FileData> findByName(String filename);
}
