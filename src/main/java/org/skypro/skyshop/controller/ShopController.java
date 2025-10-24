package org.skypro.skyshop.controller;

import model.article.Article;
import model.product.Product;
import org.skypro.skyshop.search.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopController {
    private final StorageService storageService;

    @Autowired
    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticles();
    }
}
