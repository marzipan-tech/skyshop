package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;
    @InjectMocks
    private BasketService basketService;

    @Test
    public void givenNoSuchProduct_WhenAddById_ThenThrowException() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> basketService.addProductById(id));
    }

    @Test
    public void whenAddingExistingProduct_ThenCallsAddProduct() {
        ProductBasket mockProductBasket = Mockito.mock(ProductBasket.class);
        UUID id = UUID.randomUUID();
        mockProductBasket.addProduct(id);
        verify(mockProductBasket).addProduct(id);
    }

    @Test
    public void whenProductBasketIsEmpty_ThenGetUserBasketReturnsEmptyBasket() {
        when(productBasket.getProductBasket()).thenReturn(new HashMap<>());
        assertTrue(basketService.getUserBasket().getBasketItems().isEmpty());
    }

    @Test
    public void whenProductBasketHasProducts_ThenGetUserBasketReturnsProperBasket() {
        UUID id = UUID.randomUUID();
        Product someProduct = new FixPriceProduct(id, "some product");
        when(productBasket.getProductBasket()).thenReturn(Map.of(id, 3));
        when(storageService.getProductById(id)).thenReturn(Optional.of(someProduct));
        assertThat(basketService.getUserBasket().getBasketItems()).extracting(BasketItem::getProduct).contains(someProduct);
    }
}
