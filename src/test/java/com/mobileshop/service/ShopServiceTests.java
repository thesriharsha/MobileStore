package com.mobileshop.service;

import com.mobileshop.custom.exception.IdNotFoundException;
import com.mobileshop.entities.Shop;
import com.mobileshop.repos.ShopRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.when;
@SpringBootTest
public class ShopServiceTests {


    @Mock
    private static ShopRepo shopRepo;
//
    private static ShopServiceImpl shopService;
    @BeforeEach
     void setUp(){
//         shopRepo = Mockito.mock(ShopRepo.class);
        shopService=new ShopServiceImpl(shopRepo);
    }

    @Test
     void ShopService_AddShops_Shop(){

        // Arrange
        Shop shop = new Shop("BigC","Dabagardens",13);

        //Act
        when(shopRepo.save(shop)).thenReturn(shop);
        Shop savedShop = shopService.addShop(shop);

        //Assert
        Assertions.assertThat(savedShop).isNotNull();

    }

    @Test
    void ShopService_getShops(){

        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop("Cellpoint","Allipuram",14));
        shops.add(new Shop("Big C","Rama talkies",11));
        shops.add(new Shop("Mega","Dabagardens",12));
        shops.add(new Shop("Sangeetha","New Colony",15));

        when(shopRepo.findAll()).thenReturn(shops);
        assertEquals(4,shopService.getAllShops().size());
    }

    @Test
    void ShopService_GetById()throws IdNotFoundException {
              Shop shop = new Shop(5,"Cellpoint","Allipuram",14);

        when(shopRepo.findById(5)).thenReturn(Optional.of(shop));
        assertEquals(shop,shopService.getShopById(5),"This method should return the Shop with id 5");

    }

    // To use assertthrows
    @Test
    @DisplayName("Just a test case") //Displays this name instead of the Method name
    @Disabled // This helps us to avoid this test from running and skips this test!
    void ShopService_test(){

        boolean isServer = false;
        assumeTrue(isServer); // Give programmatic control
        // This test will be run only when the assumeTrue is True.
        assertThrows(IllegalArgumentException.class,() -> shopService.test("harsha"));
    }







}
