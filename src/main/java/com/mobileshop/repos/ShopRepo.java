package com.mobileshop.repos;

import com.mobileshop.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShopRepo extends JpaRepository<Shop,Integer> {

    //    public Optional<Double> findProfitPerById(int shopId);
    @Query("select profitPer from Shop where id=:shopId")
    public Optional<Double> getProfit(@Param("shopId") int shopId);
}
