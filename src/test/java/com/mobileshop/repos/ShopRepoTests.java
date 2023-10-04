package com.mobileshop.repos;


import com.mobileshop.entities.Shop;
import com.mobileshop.service.ShopServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
public class ShopRepoTests {

    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private ShopServiceImpl shopService;


    @Test
    public void ShopServiceImpl_addShop_ReturnsShop(){

        // Arrange
        Shop shop = new Shop(5,"Cellpoint","Allipuram",14);

        //Act
        Shop savedShop = shopService.addShop(shop);

        //Assert
        Assertions.assertThat(savedShop).isNotNull();
        Assertions.assertThat(savedShop.getId()).isGreaterThan(0);


    }


    @Test
    public void ShopRepo_getAll_ReturnShop(){
        //Act
        List<Shop>  shops = shopService.getAllShops();

        //Assert
        Assertions.assertThat(shops.isEmpty()).isFalse();
//        Assertions.assertThat(savedShop.getId()).isGreaterThan(0);
    }
}
