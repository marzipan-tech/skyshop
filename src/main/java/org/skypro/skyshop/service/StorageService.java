package org.skypro.skyshop.service;

import model.article.Article;
import model.product.DiscountedProduct;
import model.product.FixPriceProduct;
import model.product.Product;
import model.product.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {
    private final Map<UUID, Product> storedProducts;
    private final Map<UUID, Article> storedArticles;

    public StorageService(Map<UUID, Product> storedProducts, Map<UUID, Article> storedArticles) {
        this.storedProducts = new HashMap<>();
        this.storedArticles = new HashMap<>();
        fillInTestData();
    }

    public Collection<Product> getAllProducts() {
        return storedProducts.values();
    }

    public Collection<Article> getAllArticles() {
        return storedArticles.values();
    }

    private void fillInTestData() {
        Product bread = new SimpleProduct(UUID.randomUUID(), "Хлеб", 100);
        Product milk = new DiscountedProduct(UUID.randomUUID(),"Молоко", 12, 200);
        Product cheese = new SimpleProduct(UUID.randomUUID(),"Сыр", 140);
        Product butter = new SimpleProduct(UUID.randomUUID(),"Масло", 430);
        Product biscuit = new SimpleProduct(UUID.randomUUID(),"Печенье", 230);
        Product rice = new SimpleProduct(UUID.randomUUID(),"Рис", 150);
        Product flour = new DiscountedProduct(UUID.randomUUID(),"Мука", 5, 105);
        Product flour2 = new DiscountedProduct(UUID.randomUUID(),"Мука новая", 5, 105);
        Product cream = new FixPriceProduct(UUID.randomUUID(),"Сливки");
        Product potato = new DiscountedProduct(UUID.randomUUID(),"Картофель", 100, 90);
        Product tomato = new DiscountedProduct(UUID.randomUUID(),"Помидоры", 0, 150);
        storedProducts.put(bread.getId(), bread);
        storedProducts.put(milk.getId(), milk);
        storedProducts.put(cheese.getId(), cheese);
        storedProducts.put(butter.getId(), butter);
        storedProducts.put(biscuit.getId(), biscuit);
        storedProducts.put(rice.getId(), rice);
        storedProducts.put(flour2.getId(), flour2);
        storedProducts.put(flour.getId(), flour);
        storedProducts.put(cream.getId(), cream);
        storedProducts.put(potato.getId(), potato);
        storedProducts.put(tomato.getId(), tomato);
        Article flourMakfa = new Article(UUID.randomUUID(),"Новая мука", "«Макфа». Высший сорт");
        Article breadBorodinskiyKolomenskoe = new Article(UUID.randomUUID(),"Бородинский хлеб", "БКХ «Коломенское». В нарезке.");
        Article breadRzhanoyKolomenskoe = new Article(UUID.randomUUID(),"Ржаной хлеб", "БКХ «Коломенское». В нарезке.");
        Article milkProstokvashino32 = new Article(UUID.randomUUID(),"Молоко, 3,2%", "«Простоквашино». Пастеризованное");
        Article milkZdravushka20 = new Article(UUID.randomUUID(),"Молоко, 2,0%", "«Здравушка». Пастеризованное");
        Article creamVkusnoteevo33 = new Article(UUID.randomUUID(),"Сливки, 33%", "«Вкуснотеево». Для взбивания");
        storedArticles.put(flourMakfa.getId(), flourMakfa);
        storedArticles.put(breadBorodinskiyKolomenskoe.getId(), breadBorodinskiyKolomenskoe);
        storedArticles.put(breadRzhanoyKolomenskoe.getId(), breadRzhanoyKolomenskoe);
        storedArticles.put(milkProstokvashino32.getId(), milkProstokvashino32);
        storedArticles.put(milkZdravushka20.getId(), milkZdravushka20);
        storedArticles.put(creamVkusnoteevo33.getId(), creamVkusnoteevo33);
    }
}

