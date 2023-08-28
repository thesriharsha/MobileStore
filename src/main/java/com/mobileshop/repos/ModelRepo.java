package com.mobileshop.repos;

import com.mobileshop.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<Model,Integer> {
}
