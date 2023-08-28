package com.mobileshop.repos;

import com.mobileshop.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {
}
