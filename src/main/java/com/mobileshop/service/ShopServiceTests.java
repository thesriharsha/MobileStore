//package com.mobileshop.service;
//
//import com.mobileshop.entities.Shop;
//import com.mobileshop.repos.ShopRepo;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class ShopServiceTests {
//
//    @Mock
//    private ShopRepo shopRepo;
//
//    @InjectMocks
//    private ShopServiceImpl shopService;
//
//    @Test
//    public void ShopServiceImpl_addShop_ReturnsShop(){
//
//        // Arrange
//        Shop shop = Shop.builder().name("BigC")
//                .location("Dabagardens")
//                .profitPer(13).build();
//
//        //Act
//        Shop savedShop = shopService.addShop(shop);
//
//        //Assert
//        Assertions.assertThat(savedShop).isNotNull();
//        Assertions.assertThat(savedShop.getId()).isGreaterThan(0);
//
//
//    }
//
//
//}
