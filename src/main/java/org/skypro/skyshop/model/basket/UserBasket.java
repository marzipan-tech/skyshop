package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItems;
    private final double total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.total = findTotal(basketItems);
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public double getTotal() {
        return total;
    }

    private double findTotal(List<BasketItem> basketItems) {
        return basketItems
                .stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
