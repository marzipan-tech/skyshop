package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final StorageService storageService;
    private final ProductBasket productBasket;

    public BasketService(StorageService storageService, ProductBasket productBasket) {
        this.storageService = storageService;
        this.productBasket = productBasket;
    }

    public void addProductById(UUID id) {
        storageService.getProductById(id).orElseThrow(NoSuchProductException::new);
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> UserBasketItems = productBasket.getProductBasket().entrySet()
                .stream()
                .map(m -> new BasketItem(storageService.getProductById(m.getKey()).orElseThrow(NoSuchProductException::new), m.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new UserBasket(UserBasketItems);
    }
}
