package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.service.BasketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("shop/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProductById(id);
        return "*Продукт успешно добавлен*";
    }

    @GetMapping("shop/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}
