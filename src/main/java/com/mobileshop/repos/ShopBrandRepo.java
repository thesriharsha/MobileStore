package com.mobileshop.repos;

import com.mobileshop.entities.Shopbrands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShopBrandRepo extends JpaRepository<Shopbrands,Integer> {



    @Query("select profitPer from Shopbrands where shop.id=:shopId and brand.id=:brandId")
    public Optional<Double> getProfit(@Param("shopId") int shopId, @Param("brandId") int brandId);

}
